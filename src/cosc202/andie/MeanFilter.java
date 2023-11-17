package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.File;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convoloution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    public int radius;
    public int offset;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * @param offset The offset of the newly constructed MeanFilter
     * @param radius The radius of the newly constructed MeanFilter
     */
    public MeanFilter(int radius, int offset) {
        this.radius = radius;
        this.offset = offset;

        FileActions.changesMade = true;
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter
     */
    public MeanFilter() {
        this.radius = 1;
        this.offset = 0;
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        int size = (2 * radius + 1) * (2 * radius + 1);
        float[] array = new float[size];
        Arrays.fill(array, 1.0f / size);

           // Calculate the required border size
    int borderSize = radius + radius;

    // Create a new BufferedImage with the required size
    int newWidth = input.getWidth() + 2 * borderSize;
    int newHeight = input.getHeight() + 2 * borderSize;

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage paddedInput = new BufferedImage(newWidth , newHeight , input.getType());
        Graphics2D g = paddedInput.createGraphics();

        // Get the Graphics object for the output image
    
    // Set the background color for the padded border
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, newWidth, newHeight);

    // Draw the input image onto the padded border
    g.drawImage(input, borderSize,borderSize, null);

    // Dispose the Graphics object
   
        //g.drawImage(input, radius, radius, null);
        g.dispose();
        BufferedImage output = new BufferedImage(paddedInput.getColorModel(), paddedInput.copyData(null),
                paddedInput.isAlphaPremultiplied(), null);
    

        convOp.filter(paddedInput, output);

          // Remove the padded border from the output image
    int outputWidth = output.getWidth() - 2 * borderSize;
    int outputHeight = output.getHeight() - 2 * borderSize;
    BufferedImage finalImage = output.getSubimage(borderSize, borderSize, outputWidth, outputHeight);

        // Apply the offset to the output image
        if (offset != 0) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = finalImage.getRGB(x, y);

                    int alpha = (pixel >> 24) & 0xFF;
                    int red = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8) & 0xFF;
                    int blue = pixel & 0xFF;

                    red += offset;
                    green += offset;
                    blue += offset;

                    red = Math.max(0, Math.min(255, red));
                    green = Math.max(0, Math.min(255, green));
                    blue = Math.max(0, Math.min(255, blue));

                    pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    finalImage.setRGB(x, y, pixel);
                }
            }
        }
        return finalImage;
    }

}
