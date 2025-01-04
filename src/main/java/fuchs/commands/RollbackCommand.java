package fuchs.commands;

import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

/**
 * Removes the last operation from the pipeline (if any).
 */
public class RollbackCommand extends AbstractCommand {

    public RollbackCommand(String action) {
        super(action);
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) throws Exception {
        if (pipeline.getOperations().isEmpty()) {
            System.out.println("[INFO] Pipeline is empty. Nothing to rollback.");
            return;
        }
        pipeline.removeLastOperation();
        manager.savePipeline(pipeline);
        System.out.println("[INFO] Last operation removed from pipeline.");
    }
}
