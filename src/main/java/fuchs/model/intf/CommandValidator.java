package fuchs.model.intf;

import fuchs.model.abst.Command;

public interface CommandValidator extends Validator<Command> {

    public final String[] VALID_ACTIONS = {"--add", "--execute", "--rollback", "--clear"};
    @Override
    public boolean isValid(Command command);
}
