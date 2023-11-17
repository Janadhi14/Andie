package cosc202.andie;
import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to make an image rotate.
 * </p>
 * 
 * <p>
 * It rotates by mapping the pixels of an image to a new location
 * based on the angle it is supposed to rotate
 * </p>
 * 
 * <p> 
 * References a query found on https://stackoverflow.com/questions/20959796/rotate-90-degree-to-right-image-in-java
 * By Ertuğrul Çetin
 * </p>
 * 
 *
 * @author Dyrel Lumiwes
 * @version 1.5
 */

public class Rotation implements ImageOperation, java.io.Serializable {
    
/**
     * The degrees indicates what direction it is to turn, 90º is right, -90º is left.
     */    private int degrees;

 /**
     * <p>
     * Construct a Rotation action with the given degrees.
     * </p>
     * 
     * <p>
     * Degrees indicate direction
     * 90º is a right turn, -90º is a left turn
     * </p>
     * 
     * @param degrees The radius of the newly constructed MeanFilter
     */    

    public Rotation(int degrees){
        this.degrees = degrees;
        
        
        
        
        
        
         FileActions.changesMade = true;
    }
/**
     * <p>
     * Apply Rotation action to an image.
     * </p>
     * 
     * <p>
     * A BufferedImage comprised of a raster stores the int rgb of each pixel of the image.
     * The new position of the pixel is dependent on if turning left or right. 
     * Direcction controlled by {@link degrees}.  
     * 
     * </p>
     * 
     * @param image The image to apply Rotation to.
     * @return The rotated image.
     */

public BufferedImage apply(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    //switch orientations go from x--> x1, y-->y1
    BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());
    //Iterates through each pixel, gets the int rgb and sets its new position as the rgb vale stord
    for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) {
            int rgb = image.getRGB(y, x);
            if(degrees ==90){
                //setRGB(int x, int y, rgb)
                rotatedImage.setRGB(height - 1 - x,y, rgb);
            }else if(degrees == -90){
                rotatedImage.setRGB(x,width - 1-y, rgb);

            }
        }
    }
    return rotatedImage;
}




}
