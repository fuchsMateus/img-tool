package fuchs;

import fuchs.exceptions.InvalidCommandException;
import fuchs.model.CompoundCommandValidator;
import fuchs.model.SimpleCommandValidator;
import fuchs.model.intf.CommandValidator;
import fuchs.model.abst.Command;
import fuchs.model.CompoundCommand;
import fuchs.model.SimpleCommand;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class InputProcessor {

    private final CommandValidator compoundCommandValidator = new CompoundCommandValidator();
    private final CommandValidator simpleCommandValidator = new SimpleCommandValidator();

    public Queue<Command> getCommands(String[] args) {
        Queue<Command> commands = new ArrayDeque<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                String action = args[i];
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    String[] splitArg = args[i+1].split(":");
                    String module = splitArg[0];
                    String operation = splitArg[1];
                    Map<String, String> params = new HashMap<>();

                    String[] splitParams = splitArg[2].split(",");
                    for (String param: splitParams) {
                        String[] splitParam = param.split("=");
                        params.put(splitParam[0], splitParam[1]);
                    }

                    CompoundCommand command = new CompoundCommand(action, module, operation, params);
                    if (!compoundCommandValidator.isValid(command)) {
                        throw new InvalidCommandException("Invalid CompoundCommand");
                    }
                    commands.add(command);
                    i++;

                } else {
                    SimpleCommand command = new SimpleCommand(action);
                    if (!simpleCommandValidator.isValid(command)) {
                        throw new InvalidCommandException("Invalid SimpleCommand");
                    }
                    commands.add(command);
                }
            }
        }

        return commands;
    }

}
