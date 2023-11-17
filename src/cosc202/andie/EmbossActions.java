package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighborhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 */
public class EmbossActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Emboss menu actions.
     * </p>
     */
    public EmbossActions() {
        actions = new ArrayList<Action>();
        actions.add(new Emboss1Action("Top left emboss", null, "Applies a top left emboss filter", Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new Emboss2Action("Mid left emboss", null, "Applies a middle left emboss filter", Integer.valueOf(KeyEvent.VK_2)));
        actions.add(new Emboss3Action("Top mid emboss", null, "Applies a top mid emboss filter", Integer.valueOf(KeyEvent.VK_3)));
        actions.add(new Emboss4Action("Top right emboss", null, "Applies a top right emboss filter", Integer.valueOf(KeyEvent.VK_4)));
        actions.add(new Emboss5Action("Mid right emboss", null, "Applies a middle right emboss filter", Integer.valueOf(KeyEvent.VK_5)));
        actions.add(new Emboss6Action("Bottom right emboss", null, "Applies a bottom right emboss filter", Integer.valueOf(KeyEvent.VK_6)));
        actions.add(new Emboss7Action("Bottom middle emboss", null, "Applies a bottom middle emboss filter", Integer.valueOf(KeyEvent.VK_7)));
        actions.add(new Emboss8Action("Bottom left emboss", null, "Applies a bottom left emboss filter", Integer.valueOf(KeyEvent.VK_8)));


        //actions.add(new EmbossFilter5jAction("Emboss 5", null, "Apply the Emboss 5 filter", Integer.valueOf(KeyEvent.VK_5)));

        //actions.add(new EmbossFilter6jAction("Emboss 6", null, "Apply the Emboss 6 filter", Integer.valueOf(KeyEvent.VK_6)));
        
        //actions.add(new SobelKokjActionj("Sobel Horizontal", null, "Apply the Sobel Horizontal filter", Integer.valueOf(KeyEvent.VK_7)));
        
        //actions.add(new SobelVerticleKokActionj("Sobel Verticle", null, "Apply for the Sobel Verticle filter", Integer.valueOf(KeyEvent.VK_0)));
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }

    /**
     * <p>j
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Emboss");

        for(Action action: actions) {
           // fileMenu.add(new JMenuItem(action));
            fileMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.SHIFT_DOWN_MASK));

        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to apply emboss from the direction of top left to bottom right.
     * </p>
     * 
     *j
     */
    public class Emboss1Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss1Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * <p>
         * The actionPerformed is triggerd when EmbossFilter1
         * The initial execution of the emboss effect is from the top left of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
try{



    
            float[] j = {+1, 0, 0,
                0, 0, 0,
                0, 0, -1};
            target.getImage().apply(new Emboss1(j));
            target.repaint();
            target.getParent().revalidate();
}catch (Exception ex) {
    JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
        }
    }

    

  

    public class Emboss2Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss2Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * <p>
         * The actionPerformed method is triggered when Embossfilter2.
         * The intial execution of the emboss effect is from the mid left.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            
            float[] j = {0, 0, 0,
                +1, 0, -1,
                0, 0, 0};
            target.getImage().apply(new Emboss2(j));
            target.repaint();
            target.getParent().revalidate();
        }
    }
    public class Emboss3Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss3Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         *<p>
         * Create a new emboss filter action.
         * </p>
         * 
         * <p>
         * The actionPerformed method is triggered when EmbossFilter3.
         * Therefore, the initial execution of the emboss effect is from top mid of the image  .
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try{
                

                float[] j = {0, +1, 0,
                    0, 0, 0,
                    0, -1, 0};

            target.getImage().apply(new Emboss3(j));
            target.repaint();
            target.getParent().revalidate();
            }
            
            
            
            
            
        catch (Exception ex) {
                JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class Emboss4Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss4Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * <p>
         * The actionPerformed is triggered when EmbossFilter4.
         * Therefore, applying the emboss effect with initial filter execution from top right of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            
            
            
            try{
                float[] j= {0, 0, +1,
                    0, 0, 0,
                    -1, 0, 0};

            target.getImage().apply(new Emboss4(j));
            target.repaint();
            target.getParent().revalidate();
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class Emboss5Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss5Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * <p>
         * The action performed method is triggered for EmbossFilter5.
         * It applys the emboss simulation starting from mid right of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                float[] j = {0, 0, 0,
                    -1, 0, +1,
                    0, 0, 0};
            target.getImage().apply(new Emboss5(j));
            target.repaint();
            target.getParent().revalidate();
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    
    
   

    
    

    // public class Emboss4Action extends ImageAction {
    //     /**
    //      * <p>
    //      * Create a new emboss filter action.
    //      * </p>
    //      * 
    //      * @param name The name of the action (ignored if null).
    //      * @param icon An icon to use to represent the action (ignored if null).
    //      * @param desc A brief description of the action  (ignored if null).
    //      * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
    //      */
    //     Emboss4Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
    //         super(name, icon, desc, mnemonic);
    //         putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
    //     }




    
    public class Emboss6Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss6Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
         * 
         * 
         * <p>
         * This method is called whenever the EmbossFilter6 is triggered.
         * It applys the emboss simulation starting from botom right of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                float[] j = {-1, 0, 0,
                    0, 0, 0,
                    0, 0, +1};
            target.getImage().apply(new Emboss6(j));
            target.repaint();
            target.getParent().revalidate();
             } catch (Exception ex) {
                JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class Emboss7Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Emboss7Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

       /**
 * <p>
 * The method is executed when the action to emboss the image from the top left is initiated.
 * </p>
 * 
 * <p>
 * Specifically, this method gets activated whenever the EmbossFilter7 comes into play.
 * It imposes an emboss effect starting from the bottom middle of the image.
 * </p>
 * 
 * @param e The event that instigates this callback.
 */
 @Override

        public void actionPerformed(ActionEvent e) {

            
            try{
                float[] j = {0, -1, 0,
                    0, 0, 0,
                    0, +1, 0};
            target.getImage().apply(new Emboss7(j));
            target.repaint();
            target.getParent().revalidate();
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class Emboss8Action extends ImageAction {
        /**
         * <p>
         * Create a new emboss filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */

        Emboss8Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
 putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, 0));
        }

        /**
 * <p>
 * This is the invoked method when the top left emboss image action is executed.
 * </p>
 * 
 * <p>
 * When the EmbossFilter 8 is activated, this method is called to apply the emboss effect from the bottom left of the image.
 * </p>
 * 
 * @param e The event that initiated this callback.
 */
 @Override

       
        public void actionPerformed(ActionEvent e) {
            
            try{
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                float[] array = {0, 0, -1,
                    0, 0, 0,
                    +1, 0, 0};
                
            target.getImage().apply(new Emboss8(array));
            target.repaint();
            target.getParent().revalidate();



            

            }catch (Exception ex) {
                JOptionPane.showMessageDialog(target.getParent(), "Error applying the emboss filter: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
}

