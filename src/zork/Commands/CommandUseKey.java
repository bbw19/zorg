package zork.Commands;

import zork.Color;
import zork.Command;
import zork.Game;
import zork.Room;

public class CommandUseKey implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "usekey";
    }

    @Override
    public void RunCommand(Command command) {
        if (Game.currentRoom.equals(Room.getFinishRoom()) && Game.playerHasKey){
            System.out.println(Color.ANSI_RED.getColor() + "You WON!!!");
            Game.hasFinished = true;
        }
    }
}
