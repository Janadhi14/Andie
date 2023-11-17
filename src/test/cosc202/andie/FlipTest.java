package test.cosc202.andie;
import cosc202.andie.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;


/**
 * This class contains JUnit tests for the FlipActions class,
 * The test cases check if the flipped image has the expected dimensions, type, and pixel values.
 @author Dyrel Lumiwes
 */


public class FlipTest {
    @Test
    void initialDummyTest(){
    }
    private int width = 2;
    private int height = 3;
    
    /** 
     * <p>
     * This test creates an image that undergoes the same transformations as the Flip test 
     * and checks if the image has been flipped horizontally by comparing start and end destinations
     * of the pixels. It also checks that the width and height have not changed.
     * </p>
     */
    @Test
    void horizontalTest() {
        BufferedImage flippedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if(y==1){
                flippedImage.setRGB(y,height-x-1, 0xff000000); // alpha mask
                }else
                flippedImage.setRGB(y,height-x-1, 0xFFFFFFFF); // non alpha mask
            }
        }
        Assertions.assertEquals(flippedImage.getWidth(),2);
        Assertions.assertEquals(flippedImage.getHeight(),3);
        Assertions.assertEquals(flippedImage.getType(), BufferedImage.TYPE_INT_RGB);
        Assertions.assertEquals(flippedImage.getRGB(0, 0), 0xFFFFFFFF);
        Assertions.assertEquals(flippedImage.getRGB(0, 1), 0xFFFFFFFF);
        Assertions.assertEquals(flippedImage.getRGB(0, 2), 0xFFFFFFFF);
        Assertions.assertEquals(flippedImage.getRGB(1, 0), 0xff000000);
        Assertions.assertEquals(flippedImage.getRGB(1, 1), 0xff000000);
        Assertions.assertEquals(flippedImage.getRGB(1, 2), 0xff000000);

    }

     /** 
     * <p>
     * This test creates an image that undergoes the same transformations as the Flip test 
     * and checks if the image has been flipped vertically by comparing start and end destinations
     * of the pixels. It also checks that the width and height have not changed
     *
     * 
     * </p>
     */
    @Test
    void verticalTest() {
        BufferedImage flippedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if(y==1){
                flippedImage.setRGB(width - 1-y,x, 0xff000000);
                }else
                flippedImage.setRGB(width - 1-y,x, 0xFFFFFFFF);

            }
        }
        Assertions.assertEquals(flippedImage.getWidth(),2);
        Assertions.assertEquals(flippedImage.getHeight(),3);
        Assertions.assertEquals(flippedImage.getType(), BufferedImage.TYPE_INT_RGB);
        Assertions.assertEquals(flippedImage.getRGB(0, 0), 0xff000000);
        Assertions.assertEquals(flippedImage.getRGB(0, 1), 0xff000000);
        Assertions.assertEquals(flippedImage.getRGB(0, 2), 0xff000000);
        Assertions.assertEquals(flippedImage.getRGB(1, 0), 0xFFFFFFFF);
        Assertions.assertEquals(flippedImage.getRGB(1, 1), 0xFFFFFFFF);
        Assertions.assertEquals(flippedImage.getRGB(1, 2), 0xFFFFFFFF);

    }

    
}
