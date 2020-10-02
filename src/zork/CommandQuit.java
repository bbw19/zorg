package zork;

public class CommandQuit implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "quit";
    }

    @Override
    public void RunCommand(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
        } else {
            Game.hasFinished = true; // signal that we want to quit
        }
    }
}
