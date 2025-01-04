package fuchs.cli;

import fuchs.commands.*;
import fuchs.validator.CommandValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for parsing CLI arguments into a list of Commands.
 * This is a simple version that looks for arguments starting with '--'.
 */
public class InputProcessor {

    private final CommandValidator validator = new CommandValidator(); // a generic validator

    /**
     * Parse the raw arguments into a list of recognized commands.
     * @param args CLI arguments
     * @return list of AbstractCommand objects
     */
    public List<AbstractCommand> parseArguments(String[] args) {
        List<AbstractCommand> commands = new ArrayList<>();

        int i = 0;
        while (i < args.length) {
            String current = args[i];

            // If an argument starts with '--', we try to interpret
            if (current.startsWith("--")) {
                switch (current) {
                    case "--add":
                        // The next argument should have format: module:operation:param1=val1,param2=val2,...
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            commands.add(new AddCommand(current, args[i + 1]));
                            i += 2;
                        } else {
                            throw new RuntimeException("Missing target for --add");
                        }
                        break;

                    case "--rollback":
                        commands.add(new RollbackCommand(current));
                        i++;
                        break;

                    case "--clear":
                        commands.add(new ClearCommand(current));
                        i++;
                        break;

                    case "--execute":
                        commands.add(new ExecuteCommand(current));
                        i++;
                        break;

                    case "--show":
                        commands.add(new ShowCommand(current));
                        i++;
                        break;

                    case "--input":
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            commands.add(new InputCommand(current, args[i + 1]));
                            i += 2;
                        } else {
                            throw new RuntimeException("Missing target for --input");
                        }
                        break;

                    case "--output":
                        if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                            commands.add(new OutputCommand(current, args[i + 1]));
                            i += 2;
                        } else {
                            throw new RuntimeException("Missing target for --output");
                        }
                        break;

                    default:
                        // Could be an unknown command
                        throw new RuntimeException("Unknown command: " + current);
                }
            } else {
                // If we find an argument that doesn't start with '--', maybe just ignore or throw error
                throw new RuntimeException("Invalid argument: " + current);
            }
        }

        // Optional: validate all commands after parsing
        for (AbstractCommand cmd : commands) {
            validator.validate(cmd);
        }

        return commands;
    }
}
