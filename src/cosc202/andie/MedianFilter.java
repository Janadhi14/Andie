package cosc202.andie;

import java.awt.image.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

/**
 * <p>
 * ImageOperation to apply a Median (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Median filter blurs an image by replacing each pixel by the average of the
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
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    public int radius;
    public int offset;

    /**
     * <p>
     * Construct a Median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution pixelCounternel
     * used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param offset The offset of the newly constructed Medianfilter
     * @param radius The radius of the newly constructed MedianFilter
     */
    public MedianFilter(int radius, int offset) {
        this.radius = radius;
        this.offset = offset;
    }

    /**
     * <p>
     * Construct a Median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Median filter has radius 1.
     * </p>
     * 
     * @see MedianFilter
     */
    public MedianFilter() {
        this.radius = 1;
        this.offset = 0;
        FileActions.changesMade = true;
    }

    /**
     * <p>
     * Apply a Median filter to an image.
     * </p>
     * 
     * <p>
     * 
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Median filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int paddedWidth = input.getWidth() + 2 * radius ;
        int paddedHeight = input.getHeight() + 2 * radius;
        BufferedImage paddedInput = new BufferedImage(paddedWidth, paddedHeight, input.getType());

        // Copy the input image onto the padded image
        Graphics2D g = paddedInput.createGraphics();
        g.drawImage(input, radius, radius, null);

        g.dispose();

        int outputWidth = input.getWidth();
        int outputHeight = input.getHeight();
        BufferedImage output = new BufferedImage(outputWidth, outputHeight, input.getType());

        int pixelCounternelWidthHeight = 2 * radius + 1;
        int numPixels = pixelCounternelWidthHeight * pixelCounternelWidthHeight;

        int[] red = new int[numPixels];
        int[] blue = new int[numPixels];
        int[] green = new int[numPixels];
        int[] alpha = new int[numPixels];

        for (int i = 0; i < outputWidth; i++) {
            for (int j = 0; j < outputHeight; j++) {
                int pixelCount = 0;
                for (int x = -radius; x <= radius; x++) {
                    for (int y = -radius; y <= radius; y++) {
                        int xCoord = i + x + radius;
                        int yCoord = j + y + radius;

                        Color col = new Color(paddedInput.getRGB(xCoord, yCoord));
                        red[pixelCount] = col.getRed();
                        blue[pixelCount] = col.getBlue();
                        green[pixelCount] = col.getGreen();
                        alpha[pixelCount] = col.getAlpha();
                        pixelCount++;
                    }
                }

                Arrays.sort(red, 0, pixelCount);
                Arrays.sort(blue, 0, pixelCount);
                Arrays.sort(green, 0, pixelCount);
                Arrays.sort(alpha, 0, pixelCount);

                int medianIndex = pixelCount / 2 + offset;
                medianIndex = Math.max(0, Math.min(pixelCount - 1, medianIndex)); // Ensure offset is within bounds
                output.setRGB(i , j ,
                        new Color(red[medianIndex], green[medianIndex],
                                blue[medianIndex], alpha[medianIndex]).getRGB());
            }
        }

        return output;
    }

}
