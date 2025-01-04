package fuchs.commands;

import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

/**
 * The abstract class for all CLI commands.
 */
public abstract class AbstractCommand {
    private final String action;

    public AbstractCommand(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    /**
     * Executes the command, modifying the pipeline or performing other operations.
     * @param pipeline the pipeline holding the operations
     * @param manager the pipeline manager for saving/loading pipeline state
     */
    public abstract void execute(Pipeline pipeline, PipelineManager manager) throws Exception;
}
