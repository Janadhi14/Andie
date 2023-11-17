package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications, 
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Millsj
 * @version 1.0
 */
public class FileActions  {
    
    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;
    javax.swing.filechooser.FileFilter openFilter=new javax.swing.filechooser.FileFilter() {
        public boolean accept (File file) {
            if (file.isDirectory()) {
                return true;
            }
            else {
                String extensions=file.getName().toLowerCase();
                return extensions.endsWith(".png") || extensions.endsWith(".jpeg")
                || extensions.endsWith(".jpg") || extensions.endsWith(".tiff");
                /*|| 
                extensions.endsWith("macro")
                || extensions.endsWith(".ops");*/
            }
        }
        public String getDescription () {
            return ".png, .jpeg, .jpg, .tiff";
        }
    };


    public static boolean changesMade = false;
    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    
    public FileActions() {
        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction("Open", null, "Open a file", Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction("Save", null, "Save the file", Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction("SaveAs", null, "Save a copy", Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileExitAction("Exit", null, "Exit the program", Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new FileExportAction("Export", null, "Exports the file", Integer.valueOf(KeyEvent.VK_X)));
    }

      /**
      * <p>
    * Creates a JMenu with the specified name and adds the specified actions to it.
    * Specifies an accelerator key in which when pressed
    * will look for another key to be pressed to trigger the action.
    *  </p>
    * @return The created JMenu.
    */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("File");

        for(Action action: actions) {
            // Add the action to the menu, and set the accelerator key
            //fileMenu.add(new JMenuItem(action));
            fileMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.CTRL_DOWN_MASK));
            /*if (action.getValue("Name") == "Export") {
                fileMenu.add(new JMenuItem(action)).setAccelerator(KeyStroke.getKeyStroke((Integer)action.getValue("MnemonicKey"),InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
                //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.CTRL_DOWN_MASK));

            }*/
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
     
    
     
     public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
           // putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();

            // Added by Dyrel 
            fileChooser.addChoosableFileFilter(openFilter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogTitle("Open");

            int result = fileChooser.showOpenDialog(target);
        
          
            // Check if an image is loaded and changes have been made
            if (target.getImage() != null && changesMade) {
                int resultj = JOptionPane.showConfirmDialog(
                        target,
                        "You have unsaved changes. Do you want to save them before opening a new image?",
                        "Unsaved Changes",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
    
               /**
                * <p>
                * Determines the action to take based on the user's response to the prompt for saving changes.
                * </p>
                *  <p>
                *
                * This method is called when the user has unsaved changes and attempts to open a new image.
                * It prompts the user to save the changes or discard them and then opens the new image
                * accordingly.
                * </p>
                * @param resultj The user's response to the prompt for saving changes.
                * @throws IOException If there is an error saving the changes.
                */
                switch (resultj) {
                    case JOptionPane.YES_OPTION:
                        // Save changes and continue with opening a new image
                        try {
                            
                        
                            
                            
                            JFileChooser fileChooserj = new JFileChooser();
                            int newresult = fileChooserj.showSaveDialog(target);


                            if (newresult == JFileChooser.APPROVE_OPTION) {
                                try {
                                    String imageFilepath = fileChooserj.getSelectedFile().getCanonicalPath();
                                    target.getImage().saveAs(imageFilepath);
                                    
                                    
                                    
                                    
                                
                                    
                                    changesMade = false;
                                } catch (Exception ex) {
                                    if(ex.getMessage().contains("cosc202.andie.SharpenFilter")){System.out.println("");}
                
                                    else{  
                                       
                                     }

                                    
                                    changesMade = true;
                                }
                            }
                            
                            if (result == JFileChooser.APPROVE_OPTION) {
                                try {
                                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                                    
                                    
                                    
                                  
                                    target.getImage().open(imageFilepath);
                                } catch (Exception ex) {

                                    JOptionPane.showMessageDialog(target, "Error opening file. Please select an image.",
                                            "Open Error", JOptionPane.ERROR_MESSAGE );
                                    ex.printStackTrace();
                                }
                            }
                        
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        break;
                    case JOptionPane.NO_OPTION:
                        // Continue with opening a new image without saving changes
                        break;
                    case JOptionPane.CANCEL_OPTION:
                    default:
                        // Cancel opening a new image
                        return;
                }
            }
           // Open the selected file as an image

           // Open the selected file as an image
    if (result == JFileChooser.APPROVE_OPTION) {
        try {
            String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
            target.getImage().open(imageFilepath);    
             
        } catch (Exception ex) {
            if (ex.getMessage().contains("cosc202.andie.SharpenFilter")) {
                System.out.println("");
            } else {
                JOptionPane.showMessageDialog(target, "Error opening file. Please select an image.",
                        "Open Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    // target.getImage().undo();
    target.repaint();
    target.getParent().revalidate();
    Andie.adjustPanel();
    
}
    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
          //  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            
            
            
            if (target.getImage() == null) {

            changesMade = false;
                JOptionPane.showMessageDialog(target, "No file open to save.",
                        "Save Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                target.getImage().save();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(target, "No file open to save.",
                        "Save Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.CTRL_DOWN_MASK));

        }

         /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (target.getImage() == null) {
                JOptionPane.showMessageDialog(target, "No file open to save.",
                        "Save Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            try {


                
                 
                String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                target.getImage().saveAs(imageFilepath);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(target, "No file open to save.",
                "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

        

    

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.CTRL_DOWN_MASK));

        }

         /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

     /**
     * <p>
     * Action to export an image as a jpg to a new file location.
     * </p>
     * 
     * @see EditableImage#export(String)
     */

     class FileExportAction extends ImageAction {


        /**
         * <p>
         * Create a new file-export-action.
         * </p>
         *  
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null)
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,KeyEvent.CTRL_DOWN_MASK));
        }
    
        /** 
         * <p>
         * Callback for when the file-export action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExportAction is triggered.
         * It opens a file chooser dialog for the user to select a new file location
         * and exports the edited image to the chosen file.
         * </p>
         * @param e The event triggering this callback. 
         */

       
         public void actionPerformed(ActionEvent e) {
             if (target.getImage() == null) {
                JOptionPane.showMessageDialog(target, "No file open to export.",
                        "Export Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
      
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);
        
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String outputFilePath = fileChooser.getSelectedFile().getCanonicalPath();
                    // Add the desired file extension if not already present
                    if (!outputFilePath.toLowerCase().endsWith(".jpg")) {
                        outputFilePath += ".jpg";
                    }
                    File outputFile = new File(outputFilePath);
        
                    // Ensure the file does not exist before creating it
                    if (!outputFile.exists()) {
                        outputFile.createNewFile();
                    }
        
                    target.getImage().export(outputFilePath);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(target, "No file open to export.",
                        "Export Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
    
    

    }
}
