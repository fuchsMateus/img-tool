package fuchs.model.abst;

import java.util.StringJoiner;

public abstract class Command {
    private final String action;

    public Command(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
