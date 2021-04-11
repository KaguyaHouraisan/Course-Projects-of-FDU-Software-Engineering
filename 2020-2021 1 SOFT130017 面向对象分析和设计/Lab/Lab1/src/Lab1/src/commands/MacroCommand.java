package commands;

import java.util.ArrayList;

public class MacroCommand implements Command {
    private ArrayList<Command> commands;
    private String commandName;

    public MacroCommand(String commandName, ArrayList<Command> commands) {
        this.commands = commands;
        this.commandName = commandName;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.size(); i++) {
            commands.get(commands.size() - i - 1).execute();
        }
    }

    @Override
    public void unExecute() {
        for (Command command : commands) {
            command.unExecute();
        }
    }

    @Override
    public String getCommand() {
        return commandName;
    }

    @Override
    public Command getNewCommand() {
        return new MacroCommand(commandName, commands);
    }
}
