package zork;

public class CommandHelp implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "help";
    }

    @Override
    public void RunCommand(Command command) {
        printHelp();
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at Monash Uni, Peninsula Campus.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(Command.showAll());
    }
}
