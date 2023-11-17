package test.cosc202.andie;
import cosc202.andie.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 
 * JUnit test to check for pixel loss and if pixels end up in the right place after actions are made
 @author Dyrel Lumiwes
 Chat GPT was used to generate JUnit test ideas
 */

public class RotationTest{
    @Test
    void initialDummyTest(){
    }
    private int height = 3;
    private int width = 2;

    /** 
     * <p>
     * This test creates an image that undergoes the same transformations as the Rotation test. 
     * It checks if the image has been rotated right by comparing start and end destinations
     * of the pixels
     * </p>
     */

    @Test
     void testRightTurn() {
        BufferedImage rotatedImage = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if(y==1){
                    //changing the colour set when y == 1 to check that top
                    //pixels are a different colour to pixels at y ==0
                rotatedImage.setRGB(height-1-x, y, 0xff000000); // alpha mask
                }else 
                rotatedImage.setRGB(height-1-x, y, 0xFFFFFFFF); // non alpha mask

            }
        }
        Assertions.assertEquals(rotatedImage.getWidth(),3);
        Assertions.assertEquals(rotatedImage.getHeight(),2);
        Assertions.assertEquals(rotatedImage.getType(), BufferedImage.TYPE_INT_RGB);
        Assertions.assertEquals(rotatedImage.getRGB(0, 0), 0xFFFFFFFF);
        Assertions.assertEquals(rotatedImage.getRGB(0, 1), 0xff000000); //should be alpha mask
        Assertions.assertEquals(rotatedImage.getRGB(1, 0), 0xFFFFFFFF);
        Assertions.assertEquals(rotatedImage.getRGB(1, 1), 0xff000000); //should be alpha mask
        Assertions.assertEquals(rotatedImage.getRGB(2, 0), 0xFFFFFFFF);
        Assertions.assertEquals(rotatedImage.getRGB(2, 1), 0xff000000); //should be alpha mask
    }

       /** 
     * <p>
     * This test creates an image that undergoes the same transformations as the Rotation test. 
     * It checks if the image has been rotated left by comparing start and end destinations
     * of the pixels
     * </p>
     */
    @Test
    void testLeftTurn(){
        height = 3;
        width = 2;
        BufferedImage rotatedImage = new BufferedImage(height,width,BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if(y==1){
                    //changing the colour set when y == 1 to check that top
                    //pixels are a different colour to pixels at y ==0
                rotatedImage.setRGB(x,width - 1-y, 0xff000000); // alpha mask
                }else 
                rotatedImage.setRGB(x,width - 1-y, 0xFFFFFFFF);
            }
        }
        Assertions.assertEquals(rotatedImage.getWidth(),3);
        Assertions.assertEquals(rotatedImage.getHeight(),2);
        Assertions.assertEquals(rotatedImage.getType(), BufferedImage.TYPE_INT_RGB);
        Assertions.assertEquals(rotatedImage.getRGB(0, 0), 0xff000000); //should be alpha mask
        Assertions.assertEquals(rotatedImage.getRGB(0, 1), 0xFFFFFFFF);
        Assertions.assertEquals(rotatedImage.getRGB(1, 0), 0xff000000); //should be alpha mask
        Assertions.assertEquals(rotatedImage.getRGB(1, 1), 0xFFFFFFFF);
        Assertions.assertEquals(rotatedImage.getRGB(2, 0), 0xff000000);//should be alpha mask
        Assertions.assertEquals(rotatedImage.getRGB(2, 1), 0xFFFFFFFF); 
    }
     
   /** 
     * <p>
     * This test checks to see if the Rotation menu has been created.
     * </p>
     */
    @Test
     void testCreateMenu() {
        RotationActions rotationActions = new RotationActions();
        Assertions.assertNotNull(rotationActions.createMenu());
    }



}