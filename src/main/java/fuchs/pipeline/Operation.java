package fuchs.pipeline;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Represents an operation that can be applied to an image.
 */
public interface Operation {

    /**
     * Applies this operation to the given image.
     * @param input the source image
     * @return the transformed image
     * @throws Exception if the operation fails
     */
    BufferedImage apply(BufferedImage input) throws Exception;

    /**
     * Returns a short description or name of the operation.
     */
    String getDescription();
}
