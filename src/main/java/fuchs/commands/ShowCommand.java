package fuchs.commands;

import fuchs.pipeline.Operation;
import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

/**
 * Shows the current pipeline (input, output, and the list of operations).
 */
public class ShowCommand extends AbstractCommand {

    public ShowCommand(String action) {
        super(action);
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) {
        System.out.println("=== CURRENT PIPELINE ===");
        System.out.println("Input:  " + (pipeline.getInputPath() == null ? "Not set" : pipeline.getInputPath()));
        System.out.println("Output: " + (pipeline.getOutputPath() == null ? "Not set" : pipeline.getOutputPath()));
        System.out.println("Operations:");
        if (pipeline.getOperations().isEmpty()) {
            System.out.println("  (no operations)");
        } else {
            int idx = 1;
            for (Operation op : pipeline.getOperations()) {
                System.out.println("  " + (idx++) + ") " + op);
            }
        }
    }
}
