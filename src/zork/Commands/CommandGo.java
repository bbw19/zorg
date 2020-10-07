package zork.Commands;

import zork.Command;
import zork.Room;

import static zork.Game.currentRoom;

public class CommandGo implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "go";
    }

    @Override
    public void RunCommand(Command command) {
        // if there is no second word, we don't know where to go...
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getRoomFromDirection(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return;
        }

        currentRoom = nextRoom;
        System.out.println(currentRoom.longDescription());
    }
}
