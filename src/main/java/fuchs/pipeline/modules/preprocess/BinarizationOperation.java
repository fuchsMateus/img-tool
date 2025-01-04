package fuchs.pipeline.modules.preprocess;

import fuchs.pipeline.Operation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Example operation that binarizes an image based on a threshold.
 */
public class BinarizationOperation implements Operation {

    private final int threshold;
    private final String method; // e.g., "otsu"

    public BinarizationOperation(Map<String, String> params) {
        // parse threshold
        this.threshold = Integer.parseInt(params.getOrDefault("threshold", "128"));
        this.method = params.getOrDefault("method", "manual");
    }

    @Override
    public BufferedImage apply(BufferedImage input) throws Exception {
        // Simple example: go through pixels, convert to black or white
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                Color color = new Color(input.getRGB(x, y));
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                // If "otsu", you'd implement Otsu's thresholding, for now we'll just do manual threshold
                int binVal = gray < threshold ? 0 : 255;
                Color newColor = new Color(binVal, binVal, binVal);
                output.setRGB(x, y, newColor.getRGB());
            }
        }
        return output;
    }

    @Override
    public String getDescription() {
        return "Binarization(threshold=" + threshold + ", method=" + method + ")";
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
