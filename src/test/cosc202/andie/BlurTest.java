package test.cosc202.andie;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;






import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import org.junit.Test;

import cosc202.andie.Blurfilter;

import org.junit.Test;

//@authors:Raaid and ChatGPT
public class BlurTest {

    // @Test
    // public void testApply() throws IOException {
    //     BufferedImage inputImage = ImageIO.read(new File("testj.png"));
    //     Blurfilter blurFilter = new Blurfilter(3,10);
    //     BufferedImage outputImage = blurFilter.apply(inputImage);
    //     assertNotNull(outputImage);
    //     assertNotSame(inputImage, outputImage);
    //     assertTrue(outputImage.getWidth() == inputImage.getWidth());
    //     assertTrue(outputImage.getHeight() == inputImage.getHeight());
    // }

    @Test(expected=IllegalArgumentException.class)
    public void testApplyNull() {
        Blurfilter blurFilter = new Blurfilter(3,0);
        blurFilter.apply(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBlurRadiusInvalid() {
        new Blurfilter(-1,-256);
    }

}
