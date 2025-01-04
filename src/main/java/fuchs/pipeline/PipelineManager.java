package fuchs.pipeline;

import fuchs.utils.JsonUtils;
import fuchs.utils.PipelineUtils;

import java.io.File;
import java.util.Map;

/**
 * Manages pipeline persistence (saving/loading).
 */
public class PipelineManager {

    private static final String PIPELINE_FILE = "imgtool-pipeline.json";

    /**
     * Loads the pipeline from a JSON file if it exists, or creates a new one otherwise.
     * After loading, it reconstructs the in-memory list of Operation objects
     * based on 'operationDescriptors'.
     */
    public Pipeline loadPipeline() {
        File file = new File(PIPELINE_FILE);
        Pipeline pipeline;
        if (file.exists()) {
            pipeline = JsonUtils.fromJson(file, Pipeline.class);
        } else {
            pipeline = new Pipeline();
        }

        // Now we reconstruct the 'operations' from 'operationDescriptors'
        for (String descriptor : pipeline.getOperationDescriptors()) {
            // e.g. descriptor = "preprocessing:binarization:threshold=128,method=otsu"
            Operation op = createOperationFromDescriptor(descriptor);
            if (op != null) {
                pipeline.getOperations().add(op);
            }
        }

        return pipeline;
    }

    /**
     * Saves the entire pipeline to a JSON file (only 'operationDescriptors', inputPath, outputPath).
     */
    public void savePipeline(Pipeline pipeline) {
        JsonUtils.toJson(new File(PIPELINE_FILE), pipeline);
    }

    /**
     * Parse the descriptor (string) and create the corresponding Operation object.
     * Exemplo: "preprocessing:binarization:threshold=128,method=otsu"
     *  => module = preprocessing
     *  => operation = binarization
     *  => param string = threshold=128,method=otsu
     */
    private Operation createOperationFromDescriptor(String descriptor) {
        String[] parts = descriptor.split(":");
        if (parts.length < 3) {
            System.err.println("[WARN] Invalid descriptor: " + descriptor);
            return null;
        }
        String moduleName = parts[0];
        String operationName = parts[1];
        String paramsStr = parts[2];

        // parse params (threshold=128,method=otsu, etc.)
        Map<String, String> params = PipelineUtils.parseParams(paramsStr);

        // Usa a factory (OperationPlugin):
        return OperationPlugin.createOperation(moduleName, operationName, params);
    }
}
