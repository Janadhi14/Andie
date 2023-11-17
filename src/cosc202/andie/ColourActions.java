package cosc202.andie;

import java.util.*;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.*;

import javax.net.ssl.TrustManager;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, as well as adjustng
 * brightness/contrast
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills, Keira Malan
 * @version 1.0
 */
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessContrastAction("Brightness/Contrast", null, "Change the brightness and contrast",
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new SaturationAction("Saturation", null, "Change the saturation", Integer.valueOf(KeyEvent.VK_S)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Colour");

        for (Action action : actions) {
            // fileMenu.add(new JMenuItem(action));
            fileMenu.add(new JMenuItem(action)).setAccelerator(
                    KeyStroke.getKeyStroke((Integer) action.getValue("MnemonicKey"), InputEvent.SHIFT_DOWN_MASK));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         * @throws IllegalArgumentException If the input image is null.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().apply(new ConvertToGrey());
                target.repaint();
                target.getParent().revalidate();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * <p>
     * Action to change an image's brightness and contrast.
     * </p>
     * 
     * @see BrightnessContrast
     */
    public class BrightnessContrastAction extends ImageAction {

        /**
         * <p>
         * Create a new Brightness Contrast adjustment action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BrightnessContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the brightness/contrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessContrastAction is triggered.
         * It changes the brightness and contrast of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         * @throws IllegalArgumentException If the input image is null.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the contrast & brightness - ask the user.
            int brightness = 1;
            int constrast = 1;
            // Pop-up dialog box to ask for the brightness and contrast value.
            JOptionPane cPane = new JOptionPane();
            JSlider cSlider = getSlider(cPane);

            JOptionPane bPane = new JOptionPane();
            JSlider bSlider = getSlider(bPane);

            Object[] message = new Object[] { "Brightness Adjustment: ", bSlider,
                    "Contrast Adjustment: ", cSlider };
            int option = JOptionPane.showOptionDialog(null, message, "Change The Brightness/ Contrast of the Image",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                brightness = bSlider.getValue();
                constrast = cSlider.getValue();
            }

            try {
                target.getImage().apply(new BrightnessContrast(constrast, brightness));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

         /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */


        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(-100, 100,0); // Set the intial value to 0
            slider.setPreferredSize(new Dimension(600, 50));

            slider.setMajorTickSpacing(10);

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
     * <p>
     * Action to change an image's brightness and contrast.
     * </p>
     * 
     * @see Saturation
     */
    public class SaturationAction extends ImageAction {

        /**
         * <p>
         * Create a new SaturationAction adjustment action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SaturationAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the saturation action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SaturationAction is triggered.
         * It changes the Saturation of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         * @throws IllegalArgumentException If the input image is null.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                JFrame parent = new JFrame();
                // Determine the saturation - ask the user.
                int saturation = 0;

                // Pop-up dialog box to ask for the Saturation value.

                JOptionPane optionPane = new JOptionPane();

                JSlider slider = getSlider(optionPane);

                optionPane.setMessage(new Object[] { "Saturation Adjustment: ", slider });
                optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
                optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
                Dialog dialog = optionPane.createDialog(parent, "My Slider");
                dialog.setVisible(true);

                int sOption1 = slider.getValue();

                // Check the return value from the dialog box.
                // If a user cancels, the default percent is 0.
                saturation = sOption1;

                target.getImage().apply(new Saturation(saturation));
                target.repaint();
                target.getParent().revalidate();

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

         /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */


        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(-100, 100,0); // Set the intial value to 0
            slider.setPreferredSize(new Dimension(600, 50));
            slider.setMajorTickSpacing(20);

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
