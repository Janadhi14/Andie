package cosc202.andie;
import java.awt.image.BufferedImage;


/**
 * <p>
 * ImageOperation to flip an image horizontally or verticall.
 * </p>
 * 
 * <p>
 * It flips by storing the pixels from the input BufferedImage input
 * and mapping the pixels of the image 
 * to a new location on a new BufferedImage output
 * </p>
 * 
 * <p> 
 * References
 * </p>
 * 
 *
 * @author Dyrel Lumiwes
 * @version 1.0
 */
    public class FlipActions implements ImageOperation, java.io.Serializable {
        /**
     * The direction indicates what axis it is to flip on
     */
        private int direction;

        /**
     * <p>
     * Construct a Flip action with the given direction.
     * </p>
     */
        public FlipActions(){
        }

        /**
     * <p>
     * Construct a Flip action with the given direction.
     * </p>
     * 
     * <p>
     * The direction indicates on which axis it will flip on,
     * With 0 indicating a horizontally flip and 1 being vertical
     * </p>
     * 
     * @param direction The radius of the newly constructed MeanFilter
     */
    
        public FlipActions(int direction) {
            this.direction = direction;
            }

  /**
     * <p>
     * Apply Flip action to an image.
     * </p>
     * 
     * <p>
     * A BufferedImage comprised of a raster stores the pixels of the image.
     * If flipped horizontally, new location of (y,x) --> (y, height -x -1)
     * If flipper vertically, new location of (y,x) --> (width-1-y,x)
     * Axis controlled by {@link direction}.  
     * 
     * </p>
     * 
     * @param image The image to apply Rotation to.
     * @return The rotated image.
     */
        
            public BufferedImage apply(BufferedImage image) {
                int width = image.getWidth();
                int height = image.getHeight();
               
                //reverses the pixels
                BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
                    //Iterates through each pixel, gets the int rgb and sets its new position as the rgb vale stord
                for (int x = 0; x < height; x++) {
                    for (int y = 0; y < width; y++) {
                        int rgb = image.getRGB(y, x);
                        //flip horizontally
                        if(direction==0){
                            flippedImage.setRGB(y,height-x-1, rgb);
                        //flip verically
                        }else 
                        flippedImage.setRGB(width - 1-y,x, rgb);

                    }
            }
            return flippedImage;

        }
    }
    
    



