package fuchs.pipeline;

import fuchs.pipeline.modules.preprocess.BinarizationOperation;

import java.util.Map;

/**
 * A registry/factory for known modules and operations.
 */
public class OperationPlugin {

    public static Operation createOperation(String moduleName, String operationName, Map<String, String> params) {
        // Exemplo:
        if ("preprocessing".equalsIgnoreCase(moduleName)) {
            if ("binarization".equalsIgnoreCase(operationName)) {
                return new BinarizationOperation(params);
            }
            // outras operações...
        }

        // se não encontrar nada, retorna null
        return null;
    }
}
