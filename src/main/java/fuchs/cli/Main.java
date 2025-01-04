package fuchs.cli;

import fuchs.commands.AbstractCommand;
import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

import java.util.List;

/**
 * The entry point of the CLI application.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize pipeline (could load from persisted JSON if exists)
        PipelineManager pipelineManager = new PipelineManager();
        Pipeline pipeline = pipelineManager.loadPipeline();

        // Process the input and parse commands
        InputProcessor processor = new InputProcessor();
        List<AbstractCommand> commands = processor.parseArguments(args);

        // Execute each command in sequence
        for (AbstractCommand cmd : commands) {
            try {
                cmd.execute(pipeline, pipelineManager);
            } catch (Exception e) {
                System.err.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
