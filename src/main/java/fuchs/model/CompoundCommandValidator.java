package fuchs.model;

import fuchs.model.abst.Command;
import fuchs.model.intf.CommandValidator;

import java.util.Arrays;

public class CompoundCommandValidator implements CommandValidator {
    @Override
    public boolean isValid(Command command) {
        boolean validAction = Arrays.stream(VALID_ACTIONS).anyMatch(a -> a.equals(command.getAction()));
        CompoundCommand compoundCommand = (CompoundCommand) command;
        //implement the logic for validate compoundCommand
        return false;
    }
}
