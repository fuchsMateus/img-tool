package fuchs.commands;

import fuchs.pipeline.Operation;
import fuchs.pipeline.OperationPlugin;
import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;
import fuchs.utils.PipelineUtils;

import java.util.Map;

/**
 * Adds a new operation to the pipeline using a textual descriptor like:
 * "preprocessing:binarization:threshold=128,method=otsu"
 */
public class AddCommand extends AbstractCommand {

    private final String descriptor; // ex.: "preprocessing:binarization:threshold=128,method=otsu"

    public AddCommand(String action, String target) {
        super(action);
        this.descriptor = target; // "preprocessing:binarization:threshold=128,method=otsu"
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) throws Exception {
        // parse
        String[] parts = descriptor.split(":");
        if (parts.length < 3) {
            throw new RuntimeException("Invalid format for add command. " +
                    "Expected: module:operation:params");
        }
        String moduleName = parts[0];
        String operationName = parts[1];
        String paramsStr = parts[2];

        Map<String, String> params = PipelineUtils.parseParams(paramsStr);

        // create operation
        Operation operation = OperationPlugin.createOperation(moduleName, operationName, params);
        if (operation == null) {
            throw new RuntimeException("Operation not recognized: " + moduleName + ":" + operationName);
        }

        // add operation + descriptor
        pipeline.addOperation(operation, descriptor);

        // persist pipeline
        manager.savePipeline(pipeline);

        System.out.println("[INFO] Operation added: " + descriptor);
    }
}
