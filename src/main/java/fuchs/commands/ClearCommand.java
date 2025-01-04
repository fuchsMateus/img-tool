package fuchs.commands;

import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

/**
 * Clears all operations from the pipeline.
 */
public class ClearCommand extends AbstractCommand {

    public ClearCommand(String action) {
        super(action);
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) throws Exception {
        pipeline.clearOperations();
        manager.savePipeline(pipeline);
        System.out.println("[INFO] Pipeline cleared.");
    }
}
