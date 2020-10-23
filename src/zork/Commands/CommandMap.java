package zork.Commands;

import zork.Command;
import zork.Game;
import zork.Map;

import static zork.Game.currentRoom;

public class CommandMap implements ICommandRunner {
    @Override
    public String GetCommandName() {
        return "map";
    }

    @Override
    public void RunCommand(Command command) {
        printMap();
    }

    private void printMap() {
        Map map = new Map(currentRoom, Game.MapSize);
        map.drawMap();
    }
}
