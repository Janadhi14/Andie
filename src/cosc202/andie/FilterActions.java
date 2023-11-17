package cosc202.andie;

import java.util.*;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction("Mean-filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));

        actions.add(
                new SharpenFilterAction("Sharpen-filter", null, "Apply a sharpen ", Integer.valueOf(KeyEvent.VK_S)));

        actions.add(new GaussianFilterAction("Gaussian-filter", null, "Apply a Gaussian filter",
                Integer.valueOf(KeyEvent.VK_G)));

        actions.add(
                new MedianFilterAction("Median-filter", null, "Apply a median filter", Integer.valueOf(KeyEvent.VK_D)));
        

    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Filter");

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

           // fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */

    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the mean filter is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = 1;
            int offset = 0;

            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);

            JOptionPane optionPane = new JOptionPane();
            JSlider slider = getSlider(optionPane);

            Object[] message = new Object[] { "Enter Mean filter radius:", radiusSpinner,
                    "Change the brightest of the pixels by an offset of x", slider };
            int option = JOptionPane.showOptionDialog(null, message, "Mean Filter", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
                offset = slider.getValue();
            }

            try {
                target.getImage().apply(new MeanFilter(radius, offset));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Filter Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */

        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(-255, 255, 0);
            slider.setPreferredSize(new Dimension(600, 50));

            slider.setMajorTickSpacing(30);

            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            ChangeListener changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider theSlider = (JSlider) changeEvent.getSource();
                    if (!theSlider.getValueIsAdjusting()) {
                        optionPane.setInputValue(theSlider.getValue());
                    }
                }
            };
            slider.addChangeListener(changeListener);
            return slider;
        }
    }

    /**
     * Action to sharpen an image
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new sharpen-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the sharpen filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * It applies the {@link SharpenFilter} to the current image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the offset - ask the user.

            int offset = 0;

            JFrame parent = new JFrame();

            JOptionPane optionPane = new JOptionPane();
            JSlider slider = getSlider(optionPane);

            Object[] messages = {
                    "Change the brightest of the pixels by an offset of x",
                    slider,
                    "Pressing cancel will apply the filter without an offset",

            };
            optionPane.setMessage(messages);
            optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
            optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            Dialog dialog = optionPane.createDialog(parent, "Offset the Filter");

            // Increase the dialog size
            dialog.setPreferredSize(new Dimension(600, 200));
            dialog.pack();
            dialog.setVisible(true);

            int option1 = slider.getValue();
            // Check the return value from the dialog box.
            if (option1 == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option1 == JOptionPane.OK_OPTION) {
                offset = slider.getValue();
            }

            try {
                target.getImage().apply(new SharpenFilter(offset));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Filter Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        }

         /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */


        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(-255, 255, 0);
            slider.setPreferredSize(new Dimension(600, 50));

            slider.setMajorTickSpacing(30);

            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            ChangeListener changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider theSlider = (JSlider) changeEvent.getSource();
                    if (!theSlider.getValueIsAdjusting()) {
                        optionPane.setInputValue(theSlider.getValue());
                    }
                }
            };
            slider.addChangeListener(changeListener);
            return slider;
        }

    }

    /**
     * /**
     * <p>
     * Action to blur an image with a median filter.
     * </p>
     * 
     * @see MedianFilter
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Median filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = 1;
            int offset = 0;

            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);

            JOptionPane optionPane = new JOptionPane();
            JSlider slider = getSlider(optionPane);

            Object[] message = new Object[] { "Enter Median filter radius:", radiusSpinner,
                    "Change the brightest of the pixels by an offset of x", slider };
            int option = JOptionPane.showOptionDialog(null, message, "Median Filter", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
                offset = slider.getValue();
            }

            try {
                target.getImage().apply(new MedianFilter(radius, offset));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Filter Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

         /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */


        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(-255, 255, 0);
            slider.setPreferredSize(new Dimension(600, 50));

            slider.setMajorTickSpacing(30);

            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            ChangeListener changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider theSlider = (JSlider) changeEvent.getSource();
                    if (!theSlider.getValueIsAdjusting()) {
                        optionPane.setInputValue(theSlider.getValue());
                    }
                }
            };
            slider.addChangeListener(changeListener);
            return slider;
        }
    }

    /**
     * Action to create a Gaussian filter
     */
    public class GaussianFilterAction extends ImageAction {
        /**
         * Gaussian action constructor
         */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Gaussian Filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link Blurfilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int radius = 1;
            int offset = 0;

            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);

            JOptionPane optionPane = new JOptionPane();
            JSlider slider = getSlider(optionPane);

            Object[] message = new Object[] { "Enter Gaussian filter radius:", radiusSpinner,
                    "Change the brightest of the pixels by an offset of x:", slider };
            int option = JOptionPane.showOptionDialog(null, message, "Gaussian Filter", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
                offset = slider.getValue();
            }

            try {
                target.getImage().apply(new Blurfilter(radius, offset));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Filter Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

         /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */


        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(-255, 255, 0);
            slider.setPreferredSize(new Dimension(600, 50));

            slider.setMajorTickSpacing(30);

            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            ChangeListener changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                    JSlider theSlider = (JSlider) changeEvent.getSource();
                    if (!theSlider.getValueIsAdjusting()) {
                        optionPane.setInputValue(theSlider.getValue());
                    }
                }
            };
            slider.addChangeListener(changeListener);
            return slider;
        }

    }
}
