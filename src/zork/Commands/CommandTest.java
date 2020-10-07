package zork.Commands;

import zork.Command;

public class CommandTest implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "test";
    }

    @Override
    public void RunCommand(Command command) {
        if (command.hasSecondWord()){
            System.out.println(command.getSecondWord());
        }
        else {
            System.out.println(command.getCommandWord());
        }
    }
}
