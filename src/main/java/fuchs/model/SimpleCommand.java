package fuchs.model;

import fuchs.model.abst.Command;

import java.util.StringJoiner;

public class SimpleCommand extends Command {
    public SimpleCommand(String action) {
        super(action);
    }
}
