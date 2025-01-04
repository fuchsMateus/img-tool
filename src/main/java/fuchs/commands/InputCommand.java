package fuchs.commands;

import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

/**
 * Sets the input image path in the pipeline.
 */
public class InputCommand extends AbstractCommand {

    private final String path;

    public InputCommand(String action, String path) {
        super(action);
        this.path = path;
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) throws Exception {
        pipeline.setInputPath(path);
        manager.savePipeline(pipeline);
        System.out.println("[INFO] Input path set to: " + path);
    }
}
