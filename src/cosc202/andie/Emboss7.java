package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.*;
/**
 * <p> A filter that applies emboss to the image.</p>
 * 
 * <p> The emboss filter applies a convolution operation to the image. </p>
 * 
 * @author Raaid Taha
 * @author Janadhi Dissanayake
 */
public class Emboss7 implements ImageOperation, java.io.Serializable { 

    public float[] arrayj;
    /**
     * Constructor for Emboss1 class.
     * @param jjjj An array of type float that is to be used in the Kernel.
     */
    public Emboss7 ( float [] jjjj){
     this.arrayj = jjjj;
    
    }


    /**<p>Applies the Emboss filter to an input BufferedImage.</p>
     * 
     * <p>A convolution is applied to the image using a unique kernel.
     * Every pixel's final colour value will be influenced by the 
     * colour values of neighbouring pixels. 
     * The degree to which the clour is affected by the neighbouring pixels is specified in the unique kernel.
     * </p>
     * 
     * <p>This emboss goes from bottom left to top right. </p>
     * 
     * @param input The image to apply the filter to.
     * @return output The new BufferedJUmage after the filter has been applied.
    */

    public BufferedImage apply (BufferedImage input) {
      

        Kernel kernel = new Kernel(3, 3, arrayj);
        Edgej convolution = new Edgej(kernel, true);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convolution.applyFilter(input, output);

        return output;
    }
}
