package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills , Dyrel Lumiwes, Raaid Taha
 * @version 1.0
 */
public class ImagePanel extends JPanel {
    



    
    public double ohye;
    /**
     * The image to display in the ImagePanel.
     */
    static EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */

    /**
     * Deselects mouse region
     */

     Rectangle2D highlightedArea = null;
     Point dragStartPoint;
     Point dragEndPoint;
 
     public void clearSelection() {
         highlightedArea = null;
         dragStartPoint = null;
         dragEndPoint = null;
     }
 

     
     public ImagePanel() {
        DrawListener pleaseWork = new DrawListener();
        this.addMouseListener(pleaseWork);
        this.addMouseMotionListener(pleaseWork);
        image = new EditableImage();
        scale = 1.0;
 
        /*if(!DrawListener.isRunning()){
        DrawListener crop = new DrawListener();
        this.addMouseListener(crop);
        this.addMouseMotionListener(crop);
        }*/  
            if(!DrawListener.isRunning()){
                
         this.addMouseListener(new MouseAdapter() {
             public void mousePressed(MouseEvent e) {
                 double scaleFactor = 1 / (scale);
                 int x = (int) (e.getX() * scaleFactor);
                 int y = (int) (e.getY() * scaleFactor);
                 dragStartPoint = new Point(x, y);
                 dragEndPoint = dragStartPoint;
 

                 
                 repaint();
             }
 
             public void mouseReleased(MouseEvent e) {
                 if (dragEndPoint != null && dragStartPoint != null) {
                     try {
                         double scaleFactor = 1 / (scale);
                         int x = (int) (e.getX() * scaleFactor);
                         int y = (int) (e.getY() * scaleFactor);
                         highlightedArea = createRect(dragStartPoint.x, dragStartPoint.y, x, y);
                         dragStartPoint = null;
                         dragEndPoint = null;
                         //DrawListener.setRunning(false);

                         repaint();
                     } catch (Exception ex) {
                         ex.printStackTrace();
                     }
                 }
             }
         });
 
         this.addMouseMotionListener(new MouseMotionAdapter() {
             public void mouseDragged(MouseEvent e) {
                 double scaleFactor = 1 / (scale);
                 int x = (int) (e.getX() * scaleFactor);
                 int y = (int) (e.getY() * scaleFactor);
                 dragEndPoint = new Point(x, y);
                 highlightedArea = createRect(dragStartPoint.x, dragStartPoint.y, x, y);
                 //DrawListener.setRunning(false);
                 repaint();
             }
         });
        }

     }
 
     public Rectangle2D.Float createRect(int x1, int y1, int x2, int y2) {
         if (x1 - x2 == 0 && y1 - y2 == 0) {
             return null;
         }
         return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2),
                 Math.abs(x1 - x2), Math.abs(y1 - y2));
     }
 
    public Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        if (x1 - x2 == 0 && y1 - y2 == 0) {
            return null;
        }
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
    

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }
    
    
    public Point getStartDrag() {
        return this.dragStartPoint;
    }

    public Rectangle2D GetMouseRectangle() {
        return this.highlightedArea;

    }

    public Point getEndDrag() {
        return this.dragEndPoint;
    }



    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    
     
     
     @Override
    
     public void paintComponent(Graphics g) {
    super.paintComponent(g);
    //This is where the image is drawn
    if (image.hasImage()) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.scale(scale, scale);
        g2.drawImage(image.getCurrentImage(), null, 0, 0);
        //If a draw selection is made then draw the selection
        if(DrawListener.isRunning()){
            DrawPanel d = new DrawPanel(DrawActions.setting, DrawActions.colour, DrawActions.thickness);
            d.paintComponent(g);
            repaint();
            revalidate();
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));


        } else if(DrawListener.isRunning()  ){
            DrawPanel d = new DrawPanel(DrawActions.setting, DrawActions.colour, DrawActions.thickness);
            d.paintComponent(g);
            repaint();
            revalidate();

        }
        //Else is there is no draw selection and user if freely using the mouse highlight area clicked
        else if (!DrawListener.isRunning() && highlightedArea != null ) {
            // Darken the background
            Rectangle2D bgRect = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
            Area bgArea = new Area(bgRect);
            bgArea.subtract(new Area(highlightedArea));
            g2.setPaint(new Color(0, 0, 0, (int) (0.5f * 255)));
            g2.fill(bgArea);
        
            // Draw the selection
          
            g2.setColor(new Color(255, 255, 255, (int) (0.8f * 255)));
            g2.fill(highlightedArea);
            g2.setColor(new Color(0, 0, 0, (int) (0.8f * 255)));
            g2.setStroke(new BasicStroke(4 / (float) scale));
            g2.draw(highlightedArea);
        
            // Set the cursor to crosshair while selecting
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
           
        } else {
            // Set the cursor back to default if not selecting
            setCursor(Cursor.getDefaultCursor());
        }
            
        g2.dispose();
    }
}
// Draw image by itself , images can be saved as theyre own image

}

