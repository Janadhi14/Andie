package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to adjust an image's saturation. In the color menu.
 * </p>
 * 
 * <p>
 * Saturation of each pixel will be adjusted by a certain percentage,
 * inputted by the user
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Keira Malan
 * @version 1.0
 */
public class Saturation implements ImageOperation, java.io.Serializable {

    private int saturation;

    /**
     * <p>
     * Create a new Saturation operation.
     * Import saturation change settings
     * </p>
     * 
     * @param saturation The % to adjust saturation by
     */
    public Saturation(int saturation) {
        this.saturation = saturation;
        FileActions.changesMade = true;
    }

    /**
     * <p>
     * Adjust image's saturation using
     * given % changes
     * </p>
     * 
     * @param input The image to be altered
     * @return The resulting, adjusted image.
     * @throws IllegalArgumentException If the input image is null.
     *
     */

     public BufferedImage apply(BufferedImage input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("Input image cannot be null.");
        }

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {

                /* Formula for change:
                 * newValue = (1 + c/100)(v-127.5)+127.5(1+b/100)
                 */

                
                int argb = input.getRGB(x, y);

                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                //convert to hsv to adjust saturation
                float[] hsv = new float[3];
                Color.RGBtoHSB(r, g, b, hsv);
                // CHANGE ALL PIXELS
                // adjust saturation
                if (hsv[1] > 0.01) {
                    hsv[1] += (float)(saturation * 0.01);
                    // check new sat is between 0 and 1
                    if(hsv[1] > 1){
                        hsv[1] = 1;
                    }else if(hsv[1] < 0){
                        hsv[1] = 0;
                    }
                }
                
                int newPixel = Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
                // need to ensure values dont go above or below 0/255

                // Resetting the ARGB Value
                //argb = (a << 24) | (newRInt << 16) | (newGInt << 8) | newBInt;
                input.setRGB(x, y, newPixel);
                
            }
        }
        
        return input;
    }

    
}
