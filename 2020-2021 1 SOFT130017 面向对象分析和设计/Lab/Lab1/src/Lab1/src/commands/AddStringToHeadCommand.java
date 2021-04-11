package commands;

import main.StringText;

public class AddStringToHeadCommand implements Command {
    private StringText operatingStr;
    private String add;

    public AddStringToHeadCommand(StringText operatingStr, String add) {
        this.operatingStr = operatingStr;
        this.add = add;
    }

    @Override
    public void execute() {
        operatingStr.addStringToHead(add);
    }

    @Override
    public void unExecute() {
        operatingStr.deleteStringFromHead(add.length());
    }

    @Override
    public String getCommand() {
        return "a \"" + add + "\"";
    }

    @Override
    public Command getNewCommand() {
        return new AddStringToHeadCommand(operatingStr, add);
    }
}
