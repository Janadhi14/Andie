package test.cosc202.andie;

import cosc202.andie.*;
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;
/**
 * 
 * JUnit tests to ensure Mean Filter creates and does not damage output images
 * @author Dyrel Lumiwes
 * Aided by ChatGPT
 *
 */

import java.awt.image.BufferedImage;

public class MeanFilterTest {
    @Test
    public void initialDummyTest(){
    }
   /** 
     * <p>
     * This test checks the constructor creates a MeanFilter with the correct radius
     * </p>
     */
    @Test
    public void testConstructor() {
        MeanFilter filter = new MeanFilter(3,0);
        assertEquals(3, filter.radius);
    }
    
     /** 
     * <p>
     * This test checks the default constructor creates a MeanFilter with the correct radius
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        MeanFilter filter = new MeanFilter();
        assertEquals(1, filter.radius);
        assertEquals(0,filter.offset);
    }

     /** 
     * <p>
     * Tests that the apply method returns an image with default radius with the same dimensions as the input image.
     * </p>
     */
    @Test
     public void testApply() {
        BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        BufferedImage output = new MeanFilter(1,0).apply(testImage);
        assertEquals(testImage.getWidth(), output.getWidth());
        assertEquals(testImage.getHeight(), output.getHeight());
    }
    
     /** 
     * <p>
     * Test that the apply method returns an image with non default radius with the same dimensions as the input image,
     * </p>
     */
    @Test
    public void testApplyWithRadius() {
        BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        BufferedImage output = new MeanFilter(2,50).apply(testImage);
        assertEquals(testImage.getWidth(), output.getWidth());
        assertEquals(testImage.getHeight(),output.getHeight());
    }

      /** 
     * <p>
     * Test to ensure the Image actually has changed.
     * </p>
     */
    @Test
    public void testImageHasChanged() {
        BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        BufferedImage output = new MeanFilter(2,200).apply(testImage);
        assertNotSame(testImage, output);
       
    }

    
    
}

