package main;

import commands.Command;
import java.util.ArrayList;
import java.util.List;

class UndoStack {
    private List<Command> modifiedCommands;

    UndoStack() {
        modifiedCommands = new ArrayList<>();
    }

    ArrayList<Command> getModifiedCommands(int num) {
        ArrayList<Command> commands = new ArrayList<>();
        if (num > modifiedCommands.size()) {
            num = modifiedCommands.size();
        }
        for (int i = 1; i <= num; i++) {
            commands.add(modifiedCommands.get(modifiedCommands.size() - i).getNewCommand());
        }
        return commands;
    }

    void showList(int n) {
        if (n > modifiedCommands.size()) {
            n = modifiedCommands.size();
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + modifiedCommands.get(modifiedCommands.size() - i).getCommand());
        }
    }

    void push(Command command) {
        modifiedCommands.add(command);
    }

    Command pop() {
        if (modifiedCommands.size() > 0) {
            return modifiedCommands.remove(modifiedCommands.size() - 1);
        }
        return null;
    }
}
