package zork.Commands;

import zork.Command;
import zork.Room;

import static zork.Game.currentRoom;

public class CommandBack implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "back";
    }

    @Override
    public void RunCommand(Command command) {
        if (CommandGo.actions.toArray().length > 0){
            String direction = invertDirection(CommandGo.actions.get(CommandGo.actions.toArray().length - 1));
            CommandGo.actions.remove(CommandGo.actions.toArray().length - 1);

            Command goCommand = new Command("go", direction);
            goCommand.processCommand();

            CommandGo.actions.remove(CommandGo.actions.toArray().length - 1);
        }
    }

    private String invertDirection(String direction){
        if (direction.equals("north")){
            return "south";
        }
        if (direction.equals("west")){
            return "east";
        }
        if (direction.equals("south")){
            return "north";
        }
        if (direction.equals("east")){
            return "west";
        }
        return "";
    }
}
