package zork.Commands;

import zork.Command;
import zork.Room;

import java.util.ArrayList;

import static zork.Game.currentRoom;

public class CommandGo implements ICommandRunner {
    public static ArrayList<String> actions = new ArrayList<>();

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

        actions.add(command.getSecondWord());
        currentRoom = nextRoom;
        System.out.println(currentRoom.longDescription());
    }
}
