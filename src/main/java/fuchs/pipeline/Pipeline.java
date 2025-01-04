package fuchs.pipeline;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the pipeline data.
 * We only serialize 'operationDescriptors' instead of Operation objects directly.
 */
public class Pipeline {

    private String inputPath;
    private String outputPath;

    /**
     * List of textual descriptions, e.g.:
     * ["preprocessing:binarization:threshold=128,method=otsu", "preprocessing:gaussianBlur:kernel=3", ...]
     *
     * This is what goes into the JSON.
     */
    private List<String> operationDescriptors = new ArrayList<>();

    /**
     * The actual Operation objects. We mark this as @JsonIgnore
     * so Jackson does NOT serialize/deserialize it directly.
     */
    @JsonIgnore
    private transient List<Operation> operations = new ArrayList<>();

    // Getters/Setters

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public List<String> getOperationDescriptors() {
        return operationDescriptors;
    }

    public void setOperationDescriptors(List<String> operationDescriptors) {
        this.operationDescriptors = operationDescriptors;
    }

    /**
     * Returns the in-memory list of Operation objects (not serialized).
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * Adds a new Operation object to the pipeline and also updates the textual descriptor.
     */
    public void addOperation(Operation op, String descriptor) {
        operations.add(op);
        operationDescriptors.add(descriptor);
    }

    /**
     * Removes last operation (if any) from both lists.
     */
    public void removeLastOperation() {
        if (!operations.isEmpty()) {
            operations.remove(operations.size() - 1);
        }
        if (!operationDescriptors.isEmpty()) {
            operationDescriptors.remove(operationDescriptors.size() - 1);
        }
    }

    /**
     * Clears the pipeline.
     */
    public void clearOperations() {
        operations.clear();
        operationDescriptors.clear();
    }

    /**
     * Load the image from inputPath.
     */
    public BufferedImage loadInputImage() throws Exception {
        if (inputPath == null) {
            throw new RuntimeException("Input path is not set.");
        }
        File f = new File(inputPath);
        if (!f.exists()) {
            throw new RuntimeException("Input file does not exist: " + inputPath);
        }
        return javax.imageio.ImageIO.read(f);
    }

    /**
     * Save the resulting image to outputPath.
     */
    public void saveOutputImage(BufferedImage image) throws Exception {
        if (outputPath == null) {
            throw new RuntimeException("Output path is not set.");
        }
        File f = new File(outputPath);
        // Supondo PNG. Ajuste conforme necessário.
        javax.imageio.ImageIO.write(image, "png", f);
    }
}
