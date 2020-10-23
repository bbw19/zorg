package zork.Commands;

import zork.Color;
import zork.Command;
import zork.Game;
import zork.Room;

public class CommandPickup implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "pickup";
    }

    @Override
    public void RunCommand(Command command) {
        if (Game.currentRoom.equals(Room.getKeyRoom())){
            Game.playerHasKey = true;
            Game.currentRoom.HasKey = false;
            System.out.println("You found the Key!");
            System.out.println("Now go to the Exit.");
        }
    }
}
