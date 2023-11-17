package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * <p>
 * Actions provided by the Image menu.
 * These include flipping and rotating an image
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 *  Inspiration for javadoc come from built in javadoc
 * </p>
 * 
 * @author Dyrel Lumiwes
 * @version 2.0
 */
public class RotationActions {
    /** List of actions that can be performed */
    protected ArrayList<Action> actions;
    /**
     * Constructor of Rotation Actions
     */
    public RotationActions() {
        actions = new ArrayList<Action>();
        actions.add(new RotationRightAction("Rotate-Right", null, "Rotate Right", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotationLeftAction("Rotate-Left", null, "Rotate Left", Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new FlipHoriz("Flip-Horizontally", null, "Rotate", Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new FlipVert("Flip-Vertically", null, "Rotate", Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new ImageCropAction("Crop", null, "Crop the image", Integer.valueOf(KeyEvent.VK_C)));



    }
     /**
      * <p>
    * Creates a JMenu with the specified name and adds the specified actions to it.
    * Specifies an accelerator key in which when pressed
    * will look for another key to be pressed to trigger the action.
    *  </p>

 
    */
    public JMenu createMenu() {
        JMenu viewMenu = new JMenu("Image");
        for (Action action : actions) {
            viewMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.SHIFT_DOWN_MASK));
        }
        return viewMenu;
    }

    /**
     * Action to rotate image right.
     * 
     * @see Rotation
     */
    public class RotationRightAction extends ImageAction {

        /**
         * Create a new rotate right action.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        public RotationRightAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * Callback for when the Image "Rotate Right" operations action is triggered.
         * 
         * This method is called whenever the RotationRightAction is triggered. It rotates the image right
         * 
         * @param e The event triggering this callback.
         **/
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().apply(new Rotation(90));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error rotating image: " + " No image available", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


/**
     * <p>
     * Action to flip an image on its horizontal axis .
     * </p>
     * 
     * @see FlipActions
     */
    public class FlipHoriz extends ImageAction {
        
        /**
         * <p>
         * Create a new horizontal flip action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        public FlipHoriz(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
 
        /** <p>
         * Callback for when the  Flip Horizontally operation action is pressed.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipHoriz is triggered.
         * It flips the image on its horizontal axis
         * </p>
         * 
         * @param e The event triggering this callback.
         **/
        public void actionPerformed(ActionEvent e) {
            // Create and apply the action
            try{
               target.getImage().apply(new FlipActions(0));
                target.repaint();
                target.getParent().revalidate();
            }catch (Exception except){
                JOptionPane.showMessageDialog(null, "Error flipping image: " + " No image available", "Error", JOptionPane.ERROR_MESSAGE);
            }
                
        }

        
    }

    /**
     * <p>
     * Action to flip an image on its vertical axis .
     * </p>
     * @see FlipActions
     */
    public class FlipVert extends ImageAction {
        /**
         * Constructor for vertical flip action
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        
        public FlipVert(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        /** <p>
         * Callback for when the  Flip Vertically operation action is pressed.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipVert is triggered.
         * It flips the image on its vertical axis
         * </p>
         * 
         * @param e The event triggering this callback.
         **/
        public void actionPerformed(ActionEvent e) {
            try {
                // Create and apply the action
                target.getImage().apply(new FlipActions(1));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                // Display an error message to the user
                JOptionPane.showMessageDialog(target, "Error flipping image horizontally: " + " No image available",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        

        
    }
 /**
     * <p>
     * Action to rotate image left.
     * </p>
     * 
     * @see Rotation
     */
    public class RotationLeftAction extends ImageAction{
    /**
         * <p>
         * Create a new rotate left action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */   

        public RotationLeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        /** <p>
         * Callback for when the Image "Rotate Left" operations action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotationLeftAction is triggered.
         * It rotates the image left
         * </p>
         * 
         * @param e The event triggering this callback.
         **/
        public void actionPerformed(ActionEvent e) {
            try {
                // Create and apply the action
                target.getImage().apply(new Rotation(-90));
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                // Display an error message to the user
                JOptionPane.showMessageDialog(target, "Error flipping image horizontally: " + " No image available",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
        
        
    public class ImageCropAction extends ImageAction {

        /**
         * <p>
         * Create a new Crop action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */

        ImageCropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public boolean j = false;
       
        private MouseAdapter cropMouseListener;
 /**
         * <p>
         * Callback for when the Crop action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the ImageCropAction is triggered.
         * It activates mouse selection to be used to crop the image.
         * </p>
         *
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {

            try {

                if (cropMouseListener != null) {
                    target.removeMouseListener(cropMouseListener);
                }

                cropMouseListener = new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        try {
                            int response = JOptionPane.showConfirmDialog(null, "Do you want to crop the selected area?",
                                    "Confirm Crop", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                            if (response == JOptionPane.YES_OPTION) {
                                Rectangle2D m = target.GetMouseRectangle();
                                target.getImage().apply(new Crop(
                                        (int) m.getX(),
                                        (int) m.getY(),
                                        (int) m.getWidth(),
                                        (int) m.getHeight(),90));
                            }

                            target.clearSelection();
                            target.repaint();
                            target.getParent().revalidate();
                            target.removeMouseListener(this);

                            cropMouseListener = null;
                         //   DrawListener.setRunning( false);

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Filter Error",
                                    JOptionPane.ERROR_MESSAGE);
                            j = true;

                            return;

                        }

                    }
                };

                target.addMouseListener(cropMouseListener);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + "Please select the cropping region", "Filter Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (j == true) {

                return;

            }

        }
    }
    }
