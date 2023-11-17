package cosc202.andie;

import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class Crop implements ImageOperation, java.io.Serializable {
    private int startX, startY, trimWidth, trimHeight;
    private int rotationDegrees;

    public Crop(int startX, int startY, int trimWidth, int trimHeight, int rotationDegrees) {
        this.startX = startX;
        this.startY = startY;
        this.trimWidth = trimWidth;
        this.trimHeight = trimHeight;
        this.rotationDegrees = rotationDegrees;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage finalImagej = null;
        try {
            // Rotate the image
            // Crop the rotated image
            int width = input.getWidth();
            int height = input.getHeight();
            int startX = Math.max(0, this.startX);
            int startY = Math.max(0, this.startY);
            int endX = Math.min(width, this.startX + this.trimWidth);
            int endY = Math.min(height, this.startY + this.trimHeight);
            int subimageWidth = endX - startX;
            int subimageHeight = endY - startY;
            BufferedImage croppedImage = input.getSubimage(startX, startY, subimageWidth, subimageHeight);

            // Rotate the cropped image back to the original orientation
            Rotation reverseRotation = new Rotation(rotationDegrees);
            BufferedImage finalImage = reverseRotation.apply(croppedImage);
            Rotation reverseRotationj = new Rotation(-rotationDegrees);
            finalImagej = reverseRotationj.apply(finalImage);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Crop dimensions are out of bounds. Please check your input values: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Invalid arguments provided for cropping: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while processing the image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return finalImagej;
    }
}
