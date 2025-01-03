package fuchs.model;

import fuchs.model.abst.Command;

import java.util.Map;
import java.util.StringJoiner;

public class CompoundCommand extends Command {
    private final String module;
    private final String operation;
    private final Map<String, String> params;
    public CompoundCommand(String action, String module, String operation, Map<String, String> params) {
        super(action);
        this.module = module;
        this.operation = operation;
        this.params = params;
    }

    public String getModule() {
        return module;
    }

    public String getOperation() {
        return operation;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
