package cosc202.andie;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.*;

//Chat GPT was used for this class to assist with java swing and Javadoc
/**
 * <p>
 * This class defines the actions for the Draw menu.
 * </p>
 * 
 */
public class DrawActions {
    public ArrayList<Action> actions;
    public static Color colour = Color.BLACK;
    /* 
    public static final String RECT = "RECT";
    public static final String FILLRECT = "FILLRECT";

    public static final String LINE = "LINE";
    public static final String FILLLINE = "FILLLINE";

    public static final String OVAL = "OVAL";
    public static final String FILLOVAL = "FILLOVAL";
    */
    public static final int RECT = 0;
    public static final int FILLRECT = 1;

    public static final int LINE = 2;

    public static final int OVAL = 3;
    public static final int FILLOVAL = 4;
    public static final int SCRIBBLE = 5;

    public static int setting;
    public static int thickness =1;



    /**
     * Constructor of Rotation Actions
     */
    public DrawActions() {
        actions = new ArrayList<Action>();

        actions.add(new ChooseColourAction("Choose colour", null, "Choose a colour", Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new ChangeThickness("Line Thickness", null, "Choose how thick your line is", Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new DrawRect("Draw Rect", null,"Draw Rectangle" , Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new DrawLine("Draw Line", null, "Draw Line", Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new DrawOval("Draw Oval", null, "Draw Oval", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FillRect("Fill Rect", null, "Fill Rectangle", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FillOval("Fill Oval", null, "Fill Oval", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new ScribbleAction("Scribble", null, "Go Crazy ", Integer.valueOf(KeyEvent.VK_S)));
    }


     /**
      * <p>
    * Creates a JMenu with the specified name and adds the specified actions to it.
    * Specifies an accelerator key in which when pressed
    * will look for another key to be pressed to trigger the action.
    *  </p>

   
    */
    public JMenu createMenu() {
        JMenu drawMenu = new JMenu("Draw");
        for (Action action : actions) {
            drawMenu.add(new JMenuItem(action));
          //  drawMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.ALT_DOWN_MASK));

        }
        return drawMenu;
    }


      /**
     * <p>
     * Action to Choose Colour.
     * </p>
     * 
     * @see ColourOptionBox
     */
    public class ChooseColourAction extends ImageAction{

        /**
         * <p>
         * Create a new choose colour action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ChooseColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.ALT_DOWN_MASK));
        }
  /**
         * <p>
         * Callback for when the choose colour action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ChooseColourAction is triggered.
         * Enables user to choose colour to draw with
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                ColourOptionBox frame = new ColourOptionBox("HSV", true, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



      /**
     * <p>
     * Action to Change Thickness of Brush
     * </p>
     * 
     * @see LineThickness
     */
     public class ChangeThickness extends ImageAction{

        /**
         * <p>
         * Open JSlider to change thickness of line
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ChangeThickness(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.ALT_DOWN_MASK));
        }
  /**
         * <p>
         * Callback for when the change thickness action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ChangeThickness is triggered.
         * Enables user to choose thickness to draw with
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                LineThickness frame2 = new LineThickness();
                thickness = LineThickness.getThickness();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     

 /**
     * Action to draw a rectangle.
     */


     public class DrawRect extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawRect(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
           
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.ALT_DOWN_MASK));
            
        }

       /**
         * 
         * <p> 
         * Callback for when the draw rectangle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawRect is triggered.
         * Starts drawing rectangles by setting the current shape type to rectangle.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                DrawListener.setRunning(true);
                setting = RECT;
                Andie.imagePanel.repaint();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

/**
     * Action to draw a line.
     */
    public class DrawLine extends ImageAction {

        /**
         * <p>
         * Create a new DrawLine action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawLine(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.ALT_DOWN_MASK));
        }

         /**
         * 
         * <p> 
         * Callback for when the draw line action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DrawLine is triggered.
         * Starts drawing lines by setting the current shape type to line.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
            DrawListener.setRunning(true);
            setting = LINE;
         //   LineThickness lt = new LineThickness();
            thickness = LineThickness.getThickness();

            Andie.imagePanel.repaint();
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

/**
     * Action to draw a oval.
     */

    public class DrawOval extends ImageAction {

        /**
         * <p>
         * Create a new draw oval action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawOval(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.ALT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the draw oval action is triggered.
         * </p>
         * 
         * 
         * <p>
         * This method is called whenever the DrawOval is triggered.
         * Starts drawing an oval by setting the current shape type to line.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
            DrawListener.setRunning(true);
            setting = OVAL;
            Andie.imagePanel.repaint();
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public class FillOval extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FillOval(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.ALT_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the fill oval action is triggered.
         * </p>
         * 
         * 
         * <p>
         * This method is called whenever the FillOval is triggered.
         * Starts drawing a filled oval by setting the current shape type to oval.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
            DrawListener.setRunning(true);
            setting = FILLOVAL;
            Andie.imagePanel.repaint();
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
            
            
            
            
            
            
        }}}


     /**
     * Action to fill an rect shape.
     */
    public class FillRect extends ImageAction {

        /**
         * <p>
         * Create a new fill-rect action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FillRect(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.ALT_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the fill rect action is triggered.
         * </p>
         * 
         * 
         * <p>
         * This method is called whenever the FillRect is triggered.
         * Starts drawing a filled rectangle by setting the current shape type to oval.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
            DrawListener.setRunning(true);

            setting = FILLRECT;
            Andie.imagePanel.repaint();
          // ColourOptionBox frame = new ColourOptionBox("HSV", true, false);
      //  ColourOptionBox frame = new ColourOptionBox("Choose Fill");

        }
        
        
        
    catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    }

/**
     * Action to draw with the mouse cursor
     */
    public class ScribbleAction extends ImageAction {

        /**
         * <p>
         * Create a new DrawLine action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ScribbleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.ALT_DOWN_MASK));
        }

         /**
         * 
         * <p> 
         * Callback for when the scirbble action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ScribbleACtion is triggered.
         * Starts drawing lines by setting the current shape type to line.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            DrawListener.setRunning(true);
            setting = SCRIBBLE;
            thickness = LineThickness.getThickness();
            Andie.imagePanel.repaint();
        }
    }



    }

