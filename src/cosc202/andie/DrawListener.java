package cosc202.andie;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.event.MouseInputListener;

import java.util.ArrayList;

/**
 * <p>
 * GitHub CoPilot was used to generate some of this  code.
 * NetBeans was used to generate setter and getter methods
 * 
 * This class implements the necessary methods from the MouseMotionListener interface
 * to capture mouse events related to drawing on a panel.
 * It provides methods to track mouse movement, button states, and store coordinate data.
 * 
 * </p>
 * 
 * @author Dyrel Lumiwes
 */
public class DrawListener implements MouseInputListener {
    private static int startingX = 0;
    private static int startingY = 0;
    private static int currentX = 0;
    private static int currentY = 0;
    private static int endingX = 0;
    private static int endingY = 0;
    Rectangle2D highlightedArea = null;
    Point dragStartPoint;
    Point dragEndPoint;

    private static boolean running = false;
    private static boolean entered = false;
    private static boolean pressed = false;
    private static ArrayList<Integer> xPolyCoords = new ArrayList<Integer>();
    private static ArrayList<Integer> yPolyCoords = new ArrayList<Integer>();
  

    /**
     * 
     */
    public DrawListener() {
    };

    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**Once pressed, set pressed boolean to true.
     * If the listener is running and the mouse is on the listened to panel, set the start x and y coordinates
     * of the mouse.
     */
    @Override
    public void mousePressed(MouseEvent e) { 
        pressed = true;
        if (running && entered && DrawActions.setting == DrawActions.SCRIBBLE) {
            setStartingX(e.getX());
            setStartingY(e.getY());
            DrawListener.xPolyCoords = new ArrayList<Integer>();
            DrawListener.yPolyCoords = new ArrayList<Integer>();
        
        } else if(running && entered){
            setStartingX(e.getX());
            setStartingY(e.getY());
        }

        //
        double scaleFactor = 1 / (Andie.imagePanel.getZoom());
        int x = (int) (e.getX() * scaleFactor);
        int y = (int) (e.getY() * scaleFactor);
        dragStartPoint = new Point(x, y);
        dragEndPoint = dragStartPoint;
        //




    }


    /**
     * <p>
     * Once the mouse is released, set pressed boolean to false.
     * If the listener is running and the mouse is on the listened to panel, set the end x and y coordinates
     * of the mouse.
     * </p>
     * 
     * @param e
     * 
         */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (DrawListener.running && DrawListener.entered) {
           
            Andie.imagePanel.getImage().apply(new Draw(DrawActions.setting, DrawActions.colour, DrawActions.thickness));
            
            ImageAction.target.repaint();
            ImageAction.target.revalidate();
            running = false;
            pressed = false;
        Andie.imagePanel.clearSelection();          
        }

      


        /*else

        if (dragEndPoint != null && dragStartPoint != null) {
            try {
                double scaleFactor = 1 / (Andie.imagePanel.getZoom());
                int x = (int) (e.getX() * scaleFactor);
                int y = (int) (e.getY() * scaleFactor);
                highlightedArea = createRect(dragStartPoint.x, dragStartPoint.y, x, y);
                dragStartPoint = null;
                dragEndPoint = null;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }*/
        clear();
    }

    /**
     * <p>
     * This is used to track the mouse's current position.
     * If the listener is running and the mouse is on the listened to panel, set the end x and y coordinates
     * of the mouse.
     * </p>
     * 
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressed &&  entered && running && DrawActions.setting == DrawActions.SCRIBBLE) {
            setCurrentX(e.getX());
            setCurrentY(e.getY());
            xPolyCoords.add(currentX);
            yPolyCoords.add(currentY);
        } else if(pressed &&  entered && running ){
            setCurrentX(e.getX());
            setCurrentY(e.getY());
        }
        
        /*else{
        double scaleFactor = 1 / (Andie.imagePanel.getZoom());
        int x = (int) (e.getX() * scaleFactor);
        int y = (int) (e.getY() * scaleFactor);
        dragEndPoint = new Point(x, y);
        highlightedArea = createRect(dragStartPoint.x, dragStartPoint.y, x, y);
        }*/
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
     * Invoked when the mouse enters a component.
     * 
     * @param e The MouseEvent that occurred.
     */

    @Override
    public void mouseEntered(MouseEvent e) {
        entered = true;
    }

    /**
     * Invoked when the mouse exits a component.
     * 
     * @param e The MouseEvent that occurred.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        entered = false;
    }

   



    @Override
    public void mouseMoved(MouseEvent e) {

    }

      /**
     * Retrieves the entered state of the mouse.
     * 
     * @return true if the mouse is inside the component, false otherwise.
     */
    public static boolean isEntered() {
        return entered;
    }

    /**
     * Sets the entered state of the mouse.
     * 
     * @param entered The entered state of the mouse.
     */
    public static void setEntered(boolean entered) {
        DrawListener.entered = entered;
    }

    /**
     * Retrieves the pressed state of the mouse button.
     * 
     * @return true if the mouse button is pressed, false otherwise.
     */
    public static boolean isPressed() {
        return pressed;
    }

    /**
     * Retrieves the running state of the drawing operation.
     * 
     * @return true if the drawing operation is in progress, false otherwise.
     */
    public static boolean isRunning() {
        return running;
    }

    /**
     * Sets the running state of the drawing operation.
     * @param running The running state of the drawing operation.
     */
    public static void setRunning(boolean running) {
        DrawListener.running = running;
    }

     /**
     * Sets whether mouse is pressed.
     * 
     * @param pressed Whether mouse is pressed
     */
    public static void setPressed(boolean pressed) {
        DrawListener.pressed = pressed;
    }

    /**
     * Retrieves the starting X-coordinate of the drawing operation.
     * 
     * @return The starting X-coordinate.
     */
    public static int getStartingX() {
        return startingX;
    }

     /**
     * Sets the starting X-coordinate of the drawing operation.
     * 
     * @param startX The starting X-coordinate.
     */
    public static void setStartingX(int startX) {
        DrawListener.startingX = startX;
    }


    /**
     * Retrieves the starting Y-coordinate of the drawing operation.
     * 
     * @return The starting Y-coordinate.
     */
    public static int getStartingY() {
        return startingY;
    }

      /**
     * Sets the starting Y-coordinate of the drawing operation.
     * 
     * @param startY The starting Y-coordinate.
     */
    public static void setStartingY(int startY) {
        DrawListener.startingY = startY;
    }

  /**
     * Retrieves the current X-coordinate of the drawing operation.
     * 
     * @return The current X-coordinate.
     */
    public static int getCurrentX() {
        return currentX;
    }

 /**
     * Sets the current X-coordinate of the drawing operation.
     * 
     * @param currentX The current X-coordinate.
     */
    public static void setCurrentX(int currentX) {
        DrawListener.currentX = currentX;
    }
  /**
     * Retrieves the current Y-coordinate of the drawing operation.
     * 
     * @return The current Y-coordinate.
     */
    public static int getCurrentY() {
        return currentY;
    }
 /**
     * Sets the current Y-coordinate of the drawing operation.
     * 
     * @param currentY The current Y-coordinate.
     */
    public static void setCurrentY(int currentY) {
        DrawListener.currentY = currentY;
    }
   /**
     * Retrieves the list of X-coordinates for the drawing operation.
     * 
     * @return The list of X-coordinates.
     */
    public static ArrayList<Integer> getxPolyCoords() {
        return xPolyCoords;
    }
/**
     * Retrieves the list of Y-coordinates for the drawing operation.
     * 
     * @return The list of Y-coordinates.
     */
    public static ArrayList<Integer> getyPolyCoords() {
        return yPolyCoords;
    }

   /**
    * This clears the current list and sets the mouse and cooridantes back to default
    *
    */
    public static void clear() {
        DrawListener.xPolyCoords = new ArrayList<>();
        DrawListener.yPolyCoords = new ArrayList<>();
     
        endingX = 0;
        endingY = 0;
        startingX = 0;
        startingY = 0;
        currentX = 0;
        currentY = 0;
       
    }
}