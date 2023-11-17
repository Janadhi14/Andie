package test.cosc202.andie;

import cosc202.andie.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.image.BufferedImage;

/**
 * 
 * JUnit tests to ensure Sharpen Filter alters the image and does not damage it
 * @author Dyrel Lumiwes
 * Aided by ChatGPT
 *
 */

public class SharpenFilterTest {
    
    
      /** 
      * <p>
      * Tests that the apply method returns an output image with the same dimensions as the input image.
      * </p>
      */
     @Test
      public void testApply() {
         BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
         BufferedImage output = new SharpenFilter().apply(testImage);
         assertEquals(testImage.getWidth(), output.getWidth());
         assertEquals(testImage.getHeight(), output.getHeight());
     }
     

       /** 
      * <p>
      * Test to ensure the Image actually has changed.
      * </p>
      */
     @Test
      public void testImageHasChanged() {
         BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
         BufferedImage output = new SharpenFilter().apply(testImage);
         assertNotSame(testImage, output);
        
     }
 
     
     
 }