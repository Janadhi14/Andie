package cosc202.andie;

import java.util.*;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * <p>
 * Actions provided by the Resize menu.
 * </p>
 * 
 * <p>
 * The resize menu contains actions that affect the size of each pixel directly
 * </p>
 * 
 * @author Bernice
 * @version 1.0
 */
public class ResizeActions {

    /** A list of actions for the Resize menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Resize menu actions.
     * </p>
     */

    public ResizeActions() {
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction("Resize", null, "resize", Integer.valueOf(KeyEvent.VK_R)));

    }

    /**
     * <p>
     * Create a menu containing the list of resize actions.
     * </p>
     * 
     * @return The resize menu UI element.
     */

    public JMenu createMenu() {
        JMenu viewMenu = new JMenu("Resize");
        for (Action action : actions) {
            viewMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        }

        return viewMenu;
    }

    /**
     * Create a Resize Action
     */
    public class ResizeAction extends ImageAction {
        /**
         * Constructor of new Resize Action
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        public ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);

        }

        /**
         * <p>
         * Callback for when the Image resize operations action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeOperations is triggered.
         * It changes the size of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int scale = 100;
            ;
            try {

                JFrame parent = new JFrame();

                JOptionPane optionPane = new JOptionPane();
                JSlider slider = getSlider(optionPane);

                JDialog dialog = new JDialog();
                dialog.setTitle("Resize Adjustment");
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setPreferredSize(new Dimension(600, 200)); // Adjust the width and height as needed

                Object[] message = new Object[] { " Resize the image to x% of  its original size:", slider };
                int option = JOptionPane.showOptionDialog(null, message, "Resize Adjustment",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

                // Check the return value from the dialog box.
                // If a user cancels, the default percent is 1.

                if (option == JOptionPane.CANCEL_OPTION) {
                    return;
                } else if (option == JOptionPane.OK_OPTION) {
                    scale = slider.getValue();

                }

               
                target.getImage().apply(new ResizeOperations(scale));
                target.repaint();
                target.getParent().revalidate();
                Andie.adjustPanel();
            } catch (Exception j) {
                // Show an error dialog with a message from the exception.
                showErrorDialog(target, j.getLocalizedMessage(), "Resize Error");

                System.out.println(j.getLocalizedMessage());
            }
        }

        private void showErrorDialog(Component parent, String errorMessage, String errorTitle) {
            JOptionPane.showMessageDialog(parent, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
        }
        
         /**
         * Creates and returns a JSlider component for use in the JOptionPane dialog.
         * 
         * @param optionPane The JOptionPane component associated with the slider.
         * @return The created JSlider component.
         */


        public JSlider getSlider(final JOptionPane optionPane) {
            JSlider slider = new JSlider(10, 500,100); // Set the initial value to 100
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
