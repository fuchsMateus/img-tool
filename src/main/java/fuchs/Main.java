package fuchs;

import fuchs.model.abst.Command;

import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        InputProcessor inputProcessor = new InputProcessor();

        Queue<Command> commands = inputProcessor.getCommands(args);

        commands.forEach(command -> {


        });
    }
}
