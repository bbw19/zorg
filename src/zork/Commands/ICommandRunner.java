package zork.Commands;

import zork.Command;

public interface ICommandRunner {
    String GetCommandName();

    void RunCommand(Command command);
}
