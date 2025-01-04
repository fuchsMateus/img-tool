package fuchs.commands;

import fuchs.pipeline.Operation;
import fuchs.pipeline.Pipeline;
import fuchs.pipeline.PipelineManager;

import java.awt.image.BufferedImage;

/**
 * Executes all operations in the pipeline in sequence, applying them to the loaded image.
 */
public class ExecuteCommand extends AbstractCommand {

    public ExecuteCommand(String action) {
        super(action);
    }

    @Override
    public void execute(Pipeline pipeline, PipelineManager manager) throws Exception {
        if (pipeline.getInputPath() == null) {
            System.out.println("[WARN] No input image path set. Aborting execution.");
            return;
        }

        if (pipeline.getOperations().isEmpty()) {
            System.out.println("[INFO] No operations to execute.");
            return;
        }

        // 1) Load the image from pipeline's input
        BufferedImage image = pipeline.loadInputImage();

        // 2) Apply each operation
        for (Operation op : pipeline.getOperations()) {
            image = op.apply(image);
        }

        // 3) Save the resulting image to pipeline's output
        pipeline.saveOutputImage(image);
        pipeline.clearOperations();
        manager.savePipeline(pipeline);
        System.out.println("[INFO] Pipeline executed and cleared.");

    }
}
