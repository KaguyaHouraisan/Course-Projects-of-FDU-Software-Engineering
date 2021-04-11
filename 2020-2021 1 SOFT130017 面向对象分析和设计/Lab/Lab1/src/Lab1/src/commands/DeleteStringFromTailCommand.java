package commands;

import main.StringText;

public class DeleteStringFromTailCommand implements Command {
    private StringText operatingStr;
    private String deleted;
    private int size;

    public DeleteStringFromTailCommand(StringText operatingStr, int size) {
        this.operatingStr = operatingStr;
        this.size = size;
        this.deleted = null;
    }

    @Override
    public void execute() {
        deleted = operatingStr.deleteStringFromTail(size);
    }

    @Override
    public void unExecute() {
        operatingStr.addStringToTail(deleted);
    }

    @Override
    public String getCommand() {
        return "D " + size;
    }

    @Override
    public Command getNewCommand() {
        return new DeleteStringFromTailCommand(operatingStr, size);
    }
}
