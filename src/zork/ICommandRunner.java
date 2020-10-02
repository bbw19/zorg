package zork;

public interface ICommandRunner {
    String GetCommandName();

    void RunCommand(Command command);
}
