package fuchs.commands;

import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

/**
 * Sets the output image path in the pipeline.
 */
public class OutputCommand extends AbstractCommand {

    private final String path;

    public OutputCommand(String action, String path) {
        super(action);
        this.path = path;
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) throws Exception {
        pipeline.setOutputPath(path);
        manager.savePipeline(pipeline);
        System.out.println("[INFO] Output path set to: " + path);
    }
}
