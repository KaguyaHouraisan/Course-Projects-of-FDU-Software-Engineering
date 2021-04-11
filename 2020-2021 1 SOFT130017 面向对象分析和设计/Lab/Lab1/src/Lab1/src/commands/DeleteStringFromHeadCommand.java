package commands;

import main.StringText;

public class DeleteStringFromHeadCommand implements Command {
    private StringText operatingStr;
    private String deleted;
    private int size;

    public DeleteStringFromHeadCommand(StringText operatingStr, int size) {
        this.operatingStr = operatingStr;
        this.size = size;
        this.deleted = null;
    }

    @Override
    public void execute() {
        deleted = operatingStr.deleteStringFromHead(size);
    }

    @Override
    public void unExecute() {
        operatingStr.addStringToHead(deleted);
    }

    @Override
    public String getCommand() {
        return "d " + size;
    }

    @Override
    public Command getNewCommand() {
        return new DeleteStringFromHeadCommand(operatingStr, size);
    }
}
