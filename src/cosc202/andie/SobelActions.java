package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class SobelActions {
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    public SobelActions() {
        actions = new ArrayList<Action>();
        actions.add(new SobelHorizontalAction("Sobel Horizontal", null, "Applies a sobel filter horizontally",
                KeyEvent.VK_1));
        actions.add(new SobelVerticalAction("Sobel Vertical", null, "Applies a sobel filter vertically",
        KeyEvent.VK_2));

    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Sobel");

        for (Action action : actions) {

            fileMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.SHIFT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));

        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to apply sobel vertically.
     * </p>
     * 
     * @see SobelVertical
     */
    public class SobelVerticalAction extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);

        }

        /**
         * <p>
         * Callback for when SobelVertical is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelVertical is triggered.
         * It applys the sobel simulation to the image vertically.
         * {@link SobelVertical}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new SobelVertical());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SobelHorizontalAction extends ImageAction {
        /**
         * <p>
         * Create a new SobelHorizontal action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SobelHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);

        }

        /**
         * <p>
         * Callback for when SobelHorinzontal is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelHorizontal is triggered.
         * It applys the sobel simulation to the image horizontally .
         * {@link SobelHorizontal}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

    try{
            target.getImage().apply(new SobelHorizontal());
            target.repaint();
            target.getParent().revalidate();
        
            
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        }
    }

}
