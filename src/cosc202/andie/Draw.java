package cosc202.andie;

import javax.swing.JOptionPane;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

// ChatGPT was used for JavaDocs and programming logic


/**
 * ImageOperation to draw on an image.
 

 * <p>
 * Scribble Feature inspiration and reference accessed from 
 * https://stackoverflow.com/questions/23393967/smooth-out-drawn-lines-pencil-like-tool-java
 * DakkVader
 * </p>
 * 
 */
public class Draw implements ImageOperation, Serializable {
    private int currentSetting;
    private Color colour = Color.BLACK;
    private int[] coordArray = new int[4];
    private int[] lineArray;

    private List<Integer> xPolyCoord;
    private List<Integer> yPolyCoord;
    private int thickness;

    /**
     * Constructor for a Draw action with the given settings, color, and thickness.
     *
     * @param currentSetting The current setting for the drawing operation.
     * @param colour         The color to be used for drawing.
     * @param thickness      The thickness of the brush.
     */
    public Draw(int currentSetting, Color colour, int thickness) {
        this.currentSetting = currentSetting;
        this.colour = colour;
        this.thickness = thickness;
        this.coordArray = currentCoordinates();
        this.lineArray = lineCoordinates();
        this.xPolyCoord = DrawListener.getxPolyCoords();
        this.yPolyCoord = DrawListener.getyPolyCoords();
        FileActions.changesMade = true;
    }

    /**
     * Calculates the coordinates of the current shape based on the starting and current positions.
     *
     * @return An array of integers representing the coordinates of the shape.
     */
    public int[] currentCoordinates() {
        int startX = DrawListener.getStartingX();
        int startY = DrawListener.getStartingY();
        int currentX = DrawListener.getCurrentX();
        int currentY = DrawListener.getCurrentY();

        int x1 = Math.min(currentX, startX);
        int y1 = Math.min(currentY, startY);
        int x2 = Math.max(currentX, startX);
        int y2 = Math.max(currentY, startY);

        return new int[]{x1, y1, x2, y2};
    }
    public static int[] lineCoordinates() {
        int startX = DrawListener.getStartingX();
        int startY = DrawListener.getStartingY();
        int currentX = DrawListener.getCurrentX();
        int currentY = DrawListener.getCurrentY();

        return new int[] { startX, startY, currentX, currentY };
    }

    /**
     * Apply Draw action to an image.
     * Switch case to determine which shape to draw
     * depending on the current setting.
     *
     * @param input The image to apply the Draw operation to.
     * @return The image with the Draw action applied.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = null;
       try {
        
      
            output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
            Graphics2D g = output.createGraphics();
            g.setColor(colour);
            g.setStroke(new BasicStroke(thickness));
            FileActions.changesMade = true;

            switch (currentSetting) {
                case 0:
                    drawRectangle(g);
                    break;
                case 1:
                    drawFilledRectangle(g);
                    break;
                case 2:
                    drawLine(g);
                    break;
                case 3:
                    drawOval(g);
                    break;
                case 4:
                    drawFilledOval(g);
                    break;
                case 5:
                    drawPolyline(g);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No image available", "Error", JOptionPane.ERROR_MESSAGE);
           }

        return output;
    }

    /**
     * Draws a rectangle.
     *
     * @param g The Graphics2D object used for drawing.
     */
    private void drawRectangle(Graphics2D g) {
        g.drawRect(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }

    /**
     * Draws a filled rectangle.
     *
     * @param g The Graphics2D object used for drawing.
     */
    private void drawFilledRectangle(Graphics2D g) {
        g.fillRect(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }

    /**
     * Draws a line.
     *
     * @param g The Graphics2D object used for drawing.
     */
    private void drawLine(Graphics2D g) {
        g.drawLine(lineArray[0], lineArray[1],lineArray[2], lineArray[3]);    
    }

    /**
     * Draws an oval.
     *
     * @param g The Graphics2D object used for drawing.
     */
    private void drawOval(Graphics2D g) {
        g.drawOval(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }

    /**
     * Draws a filled oval.
     *
     * @param g The Graphics2D object used for drawing.
     */
    private void drawFilledOval(Graphics2D g) {
        g.fillOval(coordArray[0], coordArray[1], coordArray[2] - coordArray[0], coordArray[3] - coordArray[1]);
    }

    /**
     * Draws a polyline.
     *
     * @param g The Graphics2D object used for drawing.
     */
    private void drawPolyline(Graphics2D g) {
        int[] xCoords = xPolyCoord.stream().mapToInt(x -> (int) (x / (Andie.imagePanel.getZoom() / 100))).toArray();
        int[] yCoords = yPolyCoord.stream().mapToInt(y -> (int) (y / (Andie.imagePanel.getZoom() / 100))).toArray();
        g.drawPolyline(xCoords, yCoords, xCoords.length);
    }
}
