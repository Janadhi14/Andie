
package cosc202.andie;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
// ChatGPT was used for JavaDoc and programming logic

/**
 * <p>
 * JPanel used for drawing various shapes with different settings.
 * </p>
 * 
 * <p>
 * This panel class is used to draw various shapes with different settings.
 * They take the current setting, color, and thickness of the brush as
 * parameters. The shapes are drawn using the Graphics2D class.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * <p>
 * Scribble Feature inspiration and reference accessed from 
 * https://stackoverflow.com/questions/23393967/smooth-out-drawn-lines-pencil-like-tool-java
 * DakkVader
 * </p>
 * @author Dyrel Lumiwes
 * @version 1.0
 */
public class DrawPanel extends JPanel {
    private Color colour = Color.BLACK;
    private int[] coordArray;
    private int[] lineArray;
    private int currentSetting = 0;
     int[] xPolyCoord, yPolyCoord;

 int thickness;

 /**
     * Constructs a DrawPanel object with the specified settings.
     *
     * @param currentSetting The current setting for the drawing operation.
     * @param colour The color to be used for drawing.
     * @param thickness The thickness of the brush.
     */
    public DrawPanel(int currentSetting, Color colour, int thickness) {
        this.currentSetting = currentSetting;
        this.colour = colour;
        this.coordArray = currentCoordinates();
        this.lineArray = lineCoordinates();
      this.thickness = thickness;
    }

    /**
     * Overrides the paintComponent method to draw the shapes with the specified settings.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(colour);
        g2.setStroke(new BasicStroke(thickness));
    
        if (DrawListener.isRunning()) {
            switch (currentSetting) {
                case 0:
                    drawRectangle(g2);
                    break;
                case 1:
                    drawFilledRectangle(g2);
                    break;
                case 2:
                    drawLine(g2);
                    break;
                case 3:
                    drawOval(g2);
                    break;
                case 4:
                    drawFilledOval(g2);
                    break;
                case 5:
                    drawPolyline(g2);
                    break;
                default:
                    break;
            }
        }
    
        Andie.imagePanel.repaint();
        Andie.imagePanel.revalidate();
    }
    
    /**
     * Draws a rectangle.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawRectangle(Graphics2D g2) {
        g2.drawRect(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }
    
    /**
     * Draws a filled rectangle.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawFilledRectangle(Graphics2D g2) {
        g2.fillRect(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }
    
    /**
     * Draws a line.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawLine(Graphics2D g2) {
        g2.drawLine(lineArray[0], lineArray[1],lineArray[2], lineArray[3]);    }
    
    /**
     * Draws an oval.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawOval(Graphics2D g2) {
        g2.drawOval(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }
    
    /**
     * Draws a filled oval.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawFilledOval(Graphics2D g2) {
        g2.fillOval(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }
    
    /**
     * Draws a polyline.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawPolyline(Graphics2D g2) {
        g2.setStroke(new BasicStroke(LineThickness.getThickness()));
        this.xPolyCoord = new int [DrawListener.getxPolyCoords().size()];
        this.yPolyCoord = new int [DrawListener.getyPolyCoords().size()];


        for(int i = 0; i < DrawListener.getxPolyCoords().size(); i++){
            xPolyCoord[i] = DrawListener.getxPolyCoords().get(i);
            yPolyCoord[i] = DrawListener.getyPolyCoords().get(i);
        }

        
        g2.setStroke(new BasicStroke(LineThickness.getThickness()));
        g2.drawPolyline( xPolyCoord, yPolyCoord, xPolyCoord.length);  
    }

              
     /**
     * Calculates the coordinates of the current shape based on the starting and current positions.
     * //ChatGPT was used for a bulk of this code
     * @return An array of integers representing the coordinates of the shape.
     */
    public static int[] currentCoordinates() {
        int startX = DrawListener.getStartingX();
        int startY = DrawListener.getStartingY();
        int currentX = DrawListener.getCurrentX();
        int currentY = DrawListener.getCurrentY();

        int x1 = Math.min(currentX, startX);
        int y1 = Math.min(currentY, startY);
        int x2 = Math.max(currentX, startX);
        int y2 = Math.max(currentY, startY);

        return new int[] { x1, y1, x2, y2 };
    }

     /**
     * Calculates the coordinates of the line based on the starting and current positions.
     *
     * @return An array of integers representing the coordinates of the line.
     */
    public static int[] lineCoordinates() {
        int startX = DrawListener.getStartingX();
        int startY = DrawListener.getStartingY();
        int currentX = DrawListener.getCurrentX();
        int currentY = DrawListener.getCurrentY();

        return new int[] { startX, startY, currentX, currentY };
    }
}
 