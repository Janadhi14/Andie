package cosc202.andie;

import java.awt.*;
import java.util.Locale;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.*;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
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
public class Andie {
    private static LanguageActions languageActions;
    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     */
    static JFrame frame;

    ;

    static ImagePanel imagePanel = new ImagePanel();

    private static void createAndShowGUI() throws Exception {

        // Set up the main GUI frame
        frame = new JFrame("ANDIE");

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());
        // Actions that affect the rotation of the image
        RotationActions imageActions = new RotationActions();
        menuBar.add(imageActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions for changing the language of the application
        languageActions = new LanguageActions(frame);
        menuBar.add(languageActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Actions that affect the macros of an image
        MacroActions macroAction = new MacroActions();
        menuBar.add(macroAction.createMenu());

        // Actions that affect the size of the image
        ResizeActions resizeActions = new ResizeActions();
        menuBar.add(resizeActions.createMenu());

        ToolBar toolBar1 = new ToolBar();
        // ToolBarWithDraw toolBar1 = new ToolBarWithDraw();

        frame.add(toolBar1, BorderLayout.NORTH);

        DrawActions drawActions = new DrawActions();
        menuBar.add(drawActions.createMenu());

        EmbossActions j = new EmbossActions();
        menuBar.add(j.createMenu());

        SobelActions sobelActions = new SobelActions();
        menuBar.add(sobelActions.createMenu());

        frame.setJMenuBar(menuBar);
        frame.pack();

        frame.setVisible(true);

    }

    /**
     * Translates language of ANDIE
     * 
     * @param locale the locale
     */
    public static void translate(Locale locale) {
        languageActions.updateComponentTexts(frame.getRootPane(), locale);
        frame.repaint();
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
    
    
    public static void adjustPanel(){
        frame.pack();
    }
}
