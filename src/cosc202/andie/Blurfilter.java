package cosc202.andie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian blur filter.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Raaid
 */

public class Blurfilter implements ImageOperation, java.io.Serializable {
    /** Blur radius */
    private int blurRadius;
    public int offset;
    /** Blur sigma values */
    private float blurSigma;

    public Blurfilter(int blurRadius, int offset) {
        if (blurRadius < 1) {
            throw new IllegalArgumentException("Blur radius must be greater than or equal to 1.");
        }
        this.blurRadius = blurRadius;
        this.blurSigma = blurRadius / 3.0f;
        this.offset = offset;
        FileActions.changesMade = true;
    }

    /**
     * Default constructor
     */
    Blurfilter() {
        this.blurRadius = 1;
        this.offset = 0;
    }

    /**
     * Create kernel to blur image
     * 
     * @return blurKernel
     */
    private float[][] createKernel() {
        int kernelSize = 2 * blurRadius + 1;
        float[][] blurKernel = new float[kernelSize][kernelSize];

        float kernelSum = 0;
        for (int x = -blurRadius; x <= blurRadius; x++) {
            for (int y = -blurRadius; y <= blurRadius; y++) {
                float kernelValue = (float) (Math.exp(-(x * x + y * y) / (2 * blurSigma * blurSigma))
                        / (2 * Math.PI * blurSigma * blurSigma));
                blurKernel[x + blurRadius][y + blurRadius] = kernelValue;
                kernelSum += kernelValue;
            }
        }

        // Normalize the kernel
        for (int x = 0; x < kernelSize; x++) {
            for (int y = 0; y < kernelSize; y++) {
                blurKernel[x][y] /= kernelSum;
            }
        }

        return blurKernel;
    }

    /**
     * Apply the Gaussian Blur filter
     */

    public BufferedImage apply(BufferedImage inputImage) {
        if (inputImage == null) {
            throw new IllegalArgumentException("Input image cannot be null.");
        }
        // Calculate the required border size
        int borderSize = blurRadius + blurRadius;

        // Create a new BufferedImage with the required size
        int newWidth = inputImage.getWidth() + 2 * borderSize;
        int newHeight = inputImage.getHeight() + 2 * borderSize;
        BufferedImage tempImage = new BufferedImage(newWidth, newHeight, inputImage.getType());

        // Get the Graphics object for the output image
        Graphics2D graphics = tempImage.createGraphics();

        // Set the background color for the padded border
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, newWidth, newHeight);

        // Draw the input image onto the padded border
        graphics.drawImage(inputImage, borderSize, borderSize, null);

        // Dispose the Graphics object
        graphics.dispose();

        float[][] blurKernel = createKernel();
        int kernelSize = 2 * blurRadius + 1;
        Kernel convolutionKernel = new Kernel(kernelSize, kernelSize, flattenKernel(blurKernel));

        ConvolveOp convolveOperation = null;
        try {
            convolveOperation = new ConvolveOp(convolutionKernel, ConvolveOp.EDGE_ZERO_FILL, null);
        } catch (Exception e) {
            System.err.println("Error creating ConvolveOp: " + e.getMessage());
            return inputImage;
        }

        BufferedImage outputImage = null;
        try {
            outputImage = new BufferedImage(tempImage.getColorModel(), tempImage.copyData(null),
                    tempImage.isAlphaPremultiplied(), null);
            convolveOperation.filter(tempImage, outputImage);
        } catch (Exception e) {
            System.err.println("Error applying the blur filter: " + e.getMessage());
            return inputImage;
        }

        // Remove the padded border from the output image
        int outputWidth = outputImage.getWidth() - 2 * borderSize;
        int outputHeight = outputImage.getHeight() - 2 * borderSize;
        BufferedImage finalImage = outputImage.getSubimage(borderSize, borderSize, outputWidth, outputHeight);

        // Apply the offset to the output image
        if (offset != 0) {
            int width = finalImage.getWidth();
            int height = finalImage.getHeight();
            int[] pixel = new int[4];
            WritableRaster raster = finalImage.getRaster();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    raster.getPixel(x, y, pixel);
                    for (int i = 0; i < pixel.length; i++) {
                        pixel[i] += offset;
                        pixel[i] = Math.max(0, Math.min(255, pixel[i])); // Clamp values between 0 and 255
                    }
                    raster.setPixel(x, y, pixel);
                }
            }
        }

        return finalImage;
    }

    /**
     * Flatten the kerel
     * 
     * @param inputKernel
     * @return flattened kernel
     */
    private float[] flattenKernel(float[][] inputKernel) {
        int kernelWidth = inputKernel.length;
        int kernelHeight = inputKernel[0].length;
        float[] outputKernel = new float[kernelWidth * kernelHeight];

        for (int y = 0; y < kernelHeight; y++) {
            for (int x = 0; x < kernelWidth; x++) {
                outputKernel[y * kernelWidth + x] = inputKernel[x][y];
            }
        }

        return outputKernel;
    }

}