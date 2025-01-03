package fuchs.model;

import fuchs.model.abst.Command;
import fuchs.model.intf.CommandValidator;

import java.util.Arrays;

public class SimpleCommandValidator implements CommandValidator {
    @Override
    public boolean isValid(Command command) {
        return Arrays.stream(VALID_ACTIONS).anyMatch(a -> a.equals(command.getAction()));
    }
}
