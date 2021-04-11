package main;

import commands.Command;
import java.util.ArrayList;
import java.util.List;

class RedoStack {
    private List<Command> modifiedCommands;

    RedoStack() {
        modifiedCommands = new ArrayList<>();
    }

    int getSize() {
        return modifiedCommands.size();
    }

    void clear() {
        modifiedCommands.clear();
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
