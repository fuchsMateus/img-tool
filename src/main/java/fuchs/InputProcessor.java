package fuchs;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Pattern;

public class InputProcessor {

    public static Queue<String> getCommands(String[] args) {
        Queue<String> commands = new ArrayDeque<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    commands.add(args[i] + ' ' + args[i + 1]);
                    i++;
                } else {
                    commands.add(args[i]);
                }
            }
        }
        return commands;
    }

}
