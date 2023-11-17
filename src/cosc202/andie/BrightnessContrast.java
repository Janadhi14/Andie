package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;





/**
 * <p>
 * ImageOperation to change an image's brightness and contrast. In the color menu.
 * </p>
 * 
 * <p>
 * Brightness and contrast of each pixel will be adjusted by a certain percentage,
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
public class BrightnessContrast implements ImageOperation, java.io.Serializable {
    
    private double contrastChange;
    private double brightnessChange;

      /**
     * <p>
     * Create a new BrightnessContrast operation.
     * Import contrast/brightness change settings
     * </p>
     * 
     * @param contrastChange The % to adjust contrast by
     * @param brightnessChange The % to adjust brightness by.
     */
    public BrightnessContrast(double contrastChange, double brightnessChange) {
        this.contrastChange = contrastChange;
        this.brightnessChange = brightnessChange;
        FileActions.changesMade = true;
    }
    
    
    
    
    
    
    
    
        /**
     * <p>
     * Adjust image's brightness/contrast using
     * given % changes
     * </p>
     * 
     * @param input The image to be altered, % to change brightness, % to change contrast
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
                try {
                    int argb = input.getRGB(x, y);

                    int a = (argb & 0xFF000000) >> 24;
                    int r = (argb & 0x00FF0000) >> 16;
                    int g = (argb & 0x0000FF00) >> 8;
                    int b = (argb & 0x000000FF);

                    double newR = Math.round(1 + contrastChange/100)*(r-127.5) + 127.5*(1+brightnessChange/100);
                    int newRInt = (int) newR;
                    if (newRInt > 255) {
                        newRInt = 255;
                    } else if (newRInt < 0) {
                        newRInt = 0;
                    }

                    double newG = Math.round(1 + contrastChange/100)*(g-127.5) + 127.5*(1+brightnessChange/100);
                    int newGInt = (int) newG;
                    if (newGInt > 255) {
                        newGInt = 255;
                    } else if (newGInt < 0) {
                        newGInt = 0;
                    }

                    double newB = Math.round(1 + contrastChange/100)*(b-127.5) + 127.5*(1+brightnessChange/100);
                    int newBInt = (int) newB;
                    if (newBInt > 255) {
                        newBInt = 255;
                    } else if (newBInt < 0) {
                        newBInt = 0;
                    }

                    argb = (a << 24) | (newRInt << 16) | (newGInt << 8) | newBInt;
                    input.setRGB(x, y, argb);

                } catch(Exception j) {
                    // Print out the exception to the console. 
                    // Replace this with appropriate error handling for your application.
                    JOptionPane.showMessageDialog(null, "Error: " + "Brightness and Contrast cannot be applied to this image", "Brightness Adjustment Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        return input;
    }
}
