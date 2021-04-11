package commands;

public interface Command {
    void execute();
    void unExecute();
    String getCommand();
    Command getNewCommand();
}
