
package cosc202.andie;
import cosc202.andie.*;
import java.util.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;

/**
 * ToolBar
 */

public class ToolBarWithDraw extends JToolBar {

    private int iconScale = 20;

    // next up : figure out how to align them evenly to the left and scale with size
    public ToolBarWithDraw() {
        super(HORIZONTAL);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //setPreferredSize(new Dimension(50, 150));

        addButtons(this);
        

    }

    /**
     * Adds buttons to toolbar
     */
    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        FileActions fileActions = new FileActions();
        EditActions editActions = new EditActions();
        ViewActions viewActions = new ViewActions();
        FilterActions filterActions = new FilterActions();
        RotationActions imageActions = new RotationActions();
        DrawActions drawActions = new DrawActions();

        
        // create actions

        Action saveAction = fileActions.new FileSaveAction(null, null, null, null);
        Action undoAction = editActions.new UndoAction(null, null, null, null);
        Action redoAction = editActions.new RedoAction(null, null, null, null);
        Action zoomInAction = viewActions.new ZoomInAction(null, null, null, null);
        Action zoomOutAction = viewActions.new ZoomOutAction(null, null, null, null);
        Action ImageCropAction = imageActions.new ImageCropAction(null, null, null, null);
        Action drawRect = drawActions.new DrawRect("Draw Rect", null,"Draw Rectangle" , Integer.valueOf(KeyEvent.VK_R));
        Action drawLine = drawActions.new DrawLine("Draw Line", null,"Draw Line" , Integer.valueOf(KeyEvent.VK_L));
        Action drawOval = drawActions.new DrawOval(null, null,"Draw Oval" , Integer.valueOf(KeyEvent.VK_O));
        Action chooseColour = drawActions.new ChooseColourAction("Choose colour", null, "Choose a colour", Integer.valueOf(KeyEvent.VK_C));
        Action lineThickness = drawActions.new ChangeThickness("Line Thickness", null, "Choose how thick your line is", Integer.valueOf(KeyEvent.VK_T));
        Action scribble = drawActions.new ScribbleAction("Scribble", null, "Choose how thick your line is", Integer.valueOf(KeyEvent.VK_S));

        // make save button
        button = makeButton("save-icon", saveAction, "Save", "Save");
        toolBar.add(button);

        // make undo button
        button = makeButton("undo-icon", undoAction, "Undo", "Undo");
        toolBar.add(button);
        // make redo button
        button = makeButton("redo-icon", redoAction, "Redo", "Redo");
        toolBar.add(button);
        // make zoom in button
        button = makeButton("zoom-in-icon", zoomInAction, "Zoom In", "Zoom In");
        toolBar.add(button);        
        // make zoom out button
        button = makeButton("zoom-out-icon", zoomOutAction, "Zoom Out", "Zoom Out");
        toolBar.add(button);
        // make a crop button
        button = makeButton("crop-icon", ImageCropAction, "Crop", "Crop");
        toolBar.add(button);

        /*button = makeButton(null, drawRect, "Draw Rectangle", "Draw Rectangle");
        toolBar.add(button);
        button = makeButton(null, drawLine, "Draw Line", "Draw Line");
        toolBar.add(button);
        button = makeButton("oval-icon", drawOval, "Draw Oval", "Draw Oval");
        toolBar.add(button);

        button = makeButton(null, chooseColour, "Choose colour", "Choose colour");
        toolBar.add(button);
        button = makeButton(null, lineThickness, "Brush Size", "Brush Size");
        toolBar.add(button);
        */
        button = makeButton("pencil-icon", scribble, "Scirrlbe ", "Scribble ");
        toolBar.add(button);
     

    }
    /**
     * Make a button
     * @param imageName
     * @param a
     * @param toolTipText
     * @param altText
     * @return new button
     */
    protected JButton makeButton(String imageName,
                                Action a,
                                String toolTipText,
                                String altText) {
        
       
        JButton button = new JButton(a);
        button.setToolTipText(toolTipText);
        
        // Look for the image
        String imgLocation = imageName
                             + ".png";

        
        //attempt to get image icon
        URL imageURL = ToolBar.class.getResource(imgLocation);




        if (imageURL != null) {      //image found
            // resize icon first
            ImageIcon imgIcon = new ImageIcon(imageURL, altText);
            Image image = imgIcon.getImage(); // transform it to image so size can be changed

            Image newimg = image.getScaledInstance(iconScale, iconScale,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  

            button.setIcon(new ImageIcon(newimg, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                               + imgLocation);
        }
        return button;
    }
}
