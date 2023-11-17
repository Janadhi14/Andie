package test.cosc202.andie;

import cosc202.andie.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.image.BufferedImage;


/**
 * 
 * JUnit tests to ensure Brightness/Contrast adjustments work as required
 * @author Keira Malan
 *
 */

public class BrightnessContrastTest {
    
    /** Example % change in brightness */
    private int brightnessChange = 15;
    /** Example % change in contrast */
    private int constrastChange = 20;
    
      /** 
      * <p>
      * Tests that the apply method returns an output image with the same dimensions as the input image.
      * </p>
      */
     @Test
      public void testApply() {
         BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
         BufferedImage output = new BrightnessContrast(constrastChange, brightnessChange).apply(testImage);
         assertEquals(testImage.getWidth(), output.getWidth());
         assertEquals(testImage.getHeight(), output.getHeight());
     }
     

       /** 
      * <p>
      * Test to ensure the Image actually has changed
      * </p>
      */
     @Test
      public void testImageHasChanged() {
         BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
         BufferedImage testImage2 = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
         testImage2 = new BrightnessContrast(10, 10).apply(testImage2);
         assertNotSame(testImage2, testImage);
        
     }
 
     
     
 }