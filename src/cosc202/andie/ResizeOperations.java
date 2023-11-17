package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 * <p>
 * ResizeOperation to change an image's size in the resize menu.
 * </p>
 * 
 * <p>
 * Brightness and contrast of each pixel will be adjusted by a certain percentage,
 * inputted by the user
 * </p>
 * 
 * 
 * @author Bernice
 * @version 1.0
 */

public class ResizeOperations implements ImageOperation {
    // The scale used to change the image
    private int scale;


    /**
     * <p>
     * Create a new ResizeOperations constructor.
     * Define the scale to be used
     * </p>
     * @param scale scale to be used in resize
     */
    public ResizeOperations(int scale){
        this.scale = scale;
    }
     /**
     * <p>
     * Adjust image's size.
     * </p>
     * 
     * 
     * @param image The image to be altered, by a certain scale.
     * @return The resulting resized image.
     */

    public BufferedImage apply(BufferedImage image) {
        
        if( scale <= 0 || scale > 500){
            return image;
        }
         
        int width =(int) (image.getWidth() * (scale/100.0));
        int height = (int)(image.getHeight() * (scale/100.0));
        Image scaleImage = image.getScaledInstance(width,height,Image.SCALE_SMOOTH);   
        BufferedImage resizedImage = new BufferedImage(width, height, image.getType());

        resizedImage.getGraphics().drawImage(scaleImage,0, 0, null);
           
            
            
            return resizedImage;
           

    }
}
