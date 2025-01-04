package fuchs.validator;

import fuchs.commands.AbstractCommand;

/**
 * Simple validator that checks if command action is known.
 */
public class CommandValidator {
    private static final String[] VALID_ACTIONS = {
            "--add", "--execute", "--rollback", "--clear",
            "--show", "--input", "--output"
    };

    /**
     * Validates the command.
     * @param cmd the command to validate
     */
    public void validate(AbstractCommand cmd) {
        String action = cmd.getAction();
        for (String valid : VALID_ACTIONS) {
            if (valid.equals(action)) {
                return;
            }
        }
        throw new RuntimeException("Invalid command action: " + action);
    }
}
