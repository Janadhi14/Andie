package cosc202.andie;


import java.awt.image.*;

/**
 * <p> A filter that apply emboss to image.</p>
 * 
 * <p> The sobel vertical filter applys a convolution operation to the image. </p>
 * 
 * @author Bernice
 */


public class SobelVertical implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Apply a vertical sobel filter to an image.
     * </p>
     * 
     * <p>
     * 
     * 
     * </p>
     * 
     * @param image The image to apply the sobel filter to.
     * @return The resulting dark image.
    */
    
    public BufferedImage apply(BufferedImage image) {
        if (image == null) {
            throw new IllegalArgumentException("Input image cannot be null.");
        }
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Apply the Sobel filter
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int pixelY = ((image.getRGB(x - 1, y + 1) & 0xFF) - (image.getRGB(x - 1, y - 1) & 0xFF))
                        + 2 * ((image.getRGB(x, y + 1) & 0xFF) - (image.getRGB(x, y - 1) & 0xFF))
                        + ((image.getRGB(x + 1, y + 1) & 0xFF) - (image.getRGB(x + 1, y - 1) & 0xFF));

                // Normalize the pixel value
                int normalizedPixel = Math.abs(pixelY) > 255 ? 255 : Math.abs(pixelY);

                // Set the filtered pixel value
                int filteredPixel = (normalizedPixel << 16) | (normalizedPixel << 8) | normalizedPixel;
                filteredImage.setRGB(x, y, filteredPixel);
            }
        }

        return filteredImage;
    }
    
}
