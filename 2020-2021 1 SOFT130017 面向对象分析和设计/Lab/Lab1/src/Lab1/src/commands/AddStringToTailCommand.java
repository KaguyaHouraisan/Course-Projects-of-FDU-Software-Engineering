package commands;

import main.StringText;

public class AddStringToTailCommand implements Command{
    private StringText operatingStr;
    private String add;

    public AddStringToTailCommand(StringText operatingStr, String add) {
        this.operatingStr = operatingStr;
        this.add = add;
    }

    @Override
    public void execute() {
        operatingStr.addStringToTail(add);
    }

    @Override
    public void unExecute() {
        operatingStr.deleteStringFromTail(add.length());
    }

    @Override
    public String getCommand() {
        return "A \"" + add + "\"";
    }

    @Override
    public Command getNewCommand() {
        return new AddStringToTailCommand(operatingStr, add);
    }
}
