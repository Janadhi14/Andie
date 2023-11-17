package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * The <code>Edgej</code> class represents an edge detection filter used to update each pixel in an image
 * based on its local neighborhood. It performs edge detection, which is a technique used to identify
 * boundaries between different regions in an image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Raaid Taha
 * StackOverflow and ChatGPT used to generate method ideas, fix bugs and in-code solutionsj
 * @version 1.0
 */
/**
 * This class represents an edge detection filter that applies a convolution kernel to an image.
 */
public class Edgej {

    private static final int OFFSET = 127;
    private Kernel convKernel;
    private boolean offsetEnabled;

    /**
     * Constructs an Edgej object with the specified convolution kernel.
     *
     * @param kernel The convolution kernel to apply for edge detection.
     */
    public Edgej(Kernel kernel) {
        convKernel = kernel;
        offsetEnabled = false;
    }

    /**
     * Constructs an Edgej object with the specified convolution kernel and offset enabled/disabled.
     *
     * @param kernel The convolution kernel to apply for edge detection.
     * @param offsetEnabled Specifies whether the offset should be enabled or disabled.
     */
    
    public Edgej (Kernel kernel, boolean offsetEnabled) {
        convKernel = kernel;
        this.offsetEnabled = offsetEnabled;
    }

    private int[] separateChannels(int pixelColor) {
        int[] channels = {(pixelColor & 0xFF000000) >>> 24,
                (pixelColor & 0x00FF0000) >>> 16, (pixelColor & 0x0000FF00) >>> 8, (pixelColor & 0x000000FF)};
        return channels;
    }

    private int mergeChannels(int[] channels) {
        int combinedColor = 0;
        for (int i = 0; i < channels.length; i++) {
            combinedColor = combinedColor << 8;
            if (offsetEnabled) {
                channels[i] += OFFSET;
            }
            channels[i] = Math.max(0, Math.min(255, channels[i]));
            combinedColor = combinedColor | channels[i];
        }
        return combinedColor;
    }

    private float[] convertIntToFloat(int[] input) {
        float[] output = new float[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }

    private int[] convertFloatToInt(float[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = Math.round(input[i]);
        }
        return output;
    }

    /**
     * Applies the edge detection filter to the input image and stores the result in the output image.
     *
     * @param inputImage  The input image to apply the filter on.
     * @param outputImage The output image to store the filtered result.
     */
    public void applyFilter(BufferedImage inputImage, BufferedImage outputImage) {
        try {
            for (int xPos = 0; xPos < inputImage.getWidth(); xPos++) {
                for (int yPos = 0; yPos < inputImage.getHeight(); yPos++) {
                    float[] outputChannels = {0.0f, 0.0f, 0.0f, 0.0f};
                    for (int kWidth = 0; kWidth < convKernel.getWidth(); kWidth++) {
                        for (int kHeight = 0; kHeight < convKernel.getHeight(); kHeight++) {
                            int sampleX = Math.max(0, xPos - convKernel.getXOrigin() + kWidth);
                            int sampleY = Math.max(0, yPos - convKernel.getYOrigin() + kHeight);

                            sampleX = Math.max(0, Math.min(inputImage.getWidth() - 1, sampleX));
                            sampleY = Math.max(0, Math.min(inputImage.getHeight() - 1, sampleY));
                            float[] colorChannels = convertIntToFloat(separateChannels(inputImage.getRGB(sampleX, sampleY)));
                            float[] kernelData = convKernel.getKernelData(null);
                            for (int i = 0; i < 4; i++) {
                                outputChannels[i] += colorChannels[i] * kernelData[kHeight * convKernel.getWidth() + kWidth];
                            }
                        }
                    }
                    outputImage.setRGB(xPos, yPos, mergeChannels(convertFloatToInt(outputChannels)));
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the edge detection process
            e.printStackTrace();
        }
    }
}
