package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Raaid
 */
public class SharpenFilter implements ImageOperation {

    public int offset;

    /**
     * Create new Sharpen Filter
     */
    public SharpenFilter(int offset) {
        this.offset = offset;
        FileActions.changesMade = true;
    }

    public SharpenFilter() {
        this.offset = 0;
        FileActions.changesMade = true;
    }

    /**
     * Sharpen filter data
     */
    private static final float[] SHARPEN_FILTER = {
            0.0f, -1.0f, 0.0f,
            -1.0f, 5.0f, -1.0f,
            0.0f, -1.0f, 0.0f
    };

    /**
     * Apply sharpen filter
     * 
     * @param input image to change
     * @return changed image
     */
    public BufferedImage apply(BufferedImage input) {
        Kernel kernel = new Kernel(3, 3, SHARPEN_FILTER);
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        convOp.filter(input, output);
        
        
   
        
        
        // Apply the offset to the output image
        if (offset != 0) {
            int width = output.getWidth();
            int height = output.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = output.getRGB(x, y);

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
                    output.setRGB(x, y, pixel);
                }
            }
        }

        return output;
    }

}