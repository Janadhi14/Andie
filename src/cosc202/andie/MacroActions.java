package cosc202.andie;

import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.Action;

public class MacroActions {
    protected ArrayList<Action> actionsMenu; 
    

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     * 
     * @author Janadhi Dissanayake 
     */
    public MacroActions() {
        actionsMenu = new ArrayList<Action>();
        actionsMenu.add(new MacroStartAction("Start-Macro", null, "Starts Macro recording ", Integer.valueOf(KeyEvent.VK_R)));
        actionsMenu.add(new MacroStopAction("Stop-Macro", null, "Stops Macro recording and saves .macro file", Integer.valueOf(KeyEvent.VK_S)));
        actionsMenu.add(new MacroImportAction("Import-Macro", null, "Import a saved Macro into Andie and apply it", Integer.valueOf(KeyEvent.VK_J)));
    
    }

     /**
     * <p>
     * Creates a JMenu with the Macro actions.
     * </p>
     *
     * @return The JMenu with the Macro actions.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Macro");

        for(Action action: actionsMenu) {
          fileMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.CTRL_DOWN_MASK));

        }

        return fileMenu;
    }


    /**
     * <p>
     * macroStartAction Class
     * This class defines the action to start recording a Macro.
     * </p>
     * 
     */

    public class MacroStartAction extends ImageAction{

        /**
         * <p>
         * Triggered when the MacroStartAction is activated.
         * </p>
         *
         * <p>
         * This method sets the macroRunning flag of EditableImage to true.
         * </p>
         * @param name The name of the action.
         * @param icon An icon to use to represent the action.
         * @param desc A brief description of the action.
         * @param mnemonic A mnemonic key to use as a shortcut.
         */

        MacroStartAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
        }
        /**
         * @param e The event triggering this callback.
         
         */

        public void actionPerformed(ActionEvent e) {
          EditableImage.macroRunning = true;
        }

    }

    /**
     * <p>
     * MacroStopAction Class
     * This class defines the action to stop recording a Macro.
     * </p>
     */

    public class MacroStopAction extends ImageAction {

         /**
         * <p>
         * Triggered when the MacroStopAction is activated.
         * </p>
         *
         * <p>
         * This method sets the macroRunning flag of EditableImage to false and 
         * provides an opens up dialog for saving the macro.
         * </p>
         * @param name The name of the action.
         * @param icon An icon to use to represent the action. 
         * @param desc A brief description of the action.
         * @param mnemonic A mnemonic key to use as a shortcut.
         */

        MacroStopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * callback for when the MacroStopAction is activated.
         * </p>
         *
         * <p>
         * This method sets the macroRunning from EditableImage to false and 
         * shows an option to save the current Macro.
         * </p>
         *
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
                EditableImage.macroRunning = false; 
                if (EditableImage.countOperations == 0) {
                    JOptionPane.showMessageDialog(null, "You cannot save an empty macro.", "Error message", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                
                int option = JOptionPane.showOptionDialog(null, "Do you want to save the current Macro?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(option == JOptionPane.YES_OPTION){
                try {
                        JFileChooser fileChooser = new JFileChooser("./macros");
                        int result = fileChooser.showSaveDialog(target);
    
                        if (result == JFileChooser.APPROVE_OPTION) {
                            String imgFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                            target.getImage().saveMacro(imgFilepath);
                        }
                } catch (Exception j) {
                    System.out.println(j);
                    JOptionPane.showMessageDialog(null, "There was an error when trying to save the image.", "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    public class MacroImportAction extends ImageAction{

        /**
         * <p>
         * Triggered when the MacroImportAction is activated.
         * </p>
         *
         * @param name The name of the action.
         * @param icon An icon to use to represent the action.
         * @param desc A brief description of the action.
         * @param mnemonic A mnemonic key to use as a shortcut. 
         */

         MacroImportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.SHIFT_DOWN_MASK));
        }
        /**
        * <p>
        * callback for when the MacroImportAction is activated.
        * </p>
        *
        * <p>
        * This method opens up a file selection dialog which  
        * allows for loading of a presaved .macro file .
        * </p>
        *
        * @param e The event triggers this callback.
        */

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileToSelect = new JFileChooser("./macros");
            FileNameExtensionFilter fileType = new FileNameExtensionFilter("File types to choose from", "macro", "ops");
            fileToSelect.setFileFilter(fileType);
            int result = fileToSelect.showOpenDialog(target);

            // If the user selects a file then it need to apply the effects from the macro onto the new image.
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = fileToSelect.getSelectedFile().getCanonicalPath();
                    target.getImage().openMacro(path);
                    target.repaint();
                    target.getParent().revalidate();
                } catch (Exception j) {
                    JOptionPane.showMessageDialog(null, "Unable to repaint using macro", "Error message", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);

                }
                
    }



    }

}

}

    