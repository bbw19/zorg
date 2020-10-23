package zork;

import java.lang.Math;
import java.util.HashMap;

public class Map {
    private Room initialRoom;
    private int fieldSize;
    private Vector2[] wholeField;
    private int roomWidth = 7;
    private int roomHeight = 3;
    private HashMap<Vector2, Room> allRooms;

    public Map(Room initialRoom, int fieldSize) {
        this.initialRoom = initialRoom;
        this.fieldSize = fieldSize;
        this.allRooms = Room.getRooms();
    }

    public void drawMap() {
        int sqrt = (int) Math.sqrt(fieldSize);

        String map = "";

        for (int i = sqrt - 1; i >= 0; i--){
            for (int k = 0; k < roomHeight; k++){
                for (int j = 0; j < sqrt; j++){
                    if (allRooms.containsKey(new Vector2(j, i))) {
                        map += DrawRoom(roomWidth, roomHeight, k, initialRoom.getPos().equals(new Vector2(j, i)), allRooms.get(new Vector2(j, i)).IsFinishRoom);
                    } else {
                        map += NoRoom(roomWidth);
                    }
                    map += " ";
                }
                map += System.lineSeparator();
            }
        }

        System.out.println(map);
    }

    private String NoRoom(int roomWidth){
        String output = Color.ANSI_CYAN.getColor();

        for (int i = 0; i < roomWidth; i++){
            output += "o";
        }

        output += Color.ANSI_WHITE.getColor();

        return output;
    }

    private String DrawRoom(int roomWidth, int roomHeight, int height, boolean IsActualRoom, boolean isFinishRoom){
        StringBuilder output = new StringBuilder();

        String roomColor;

        if (isFinishRoom){
            roomColor = Color.ANSI_BLUE.getColor();
        }
        else {
            roomColor = Color.ANSI_YELLOW.getColor();
        }

        output.append(roomColor);

        if (height == 0 || height == roomHeight - 1){
            for (int i = 0; i < roomWidth; i++){
                output.append("-");
            }

            output.append(Color.ANSI_WHITE.getColor());

            return output.toString();
        }

        output.append("|");

        output.append(Color.ANSI_RED.getColor());

        for (int i = 0; i < roomWidth - 2; i++){
            if (IsActualRoom){
                output.append("X");
            }
            else {
                output.append(" ");
            }
        }

        output.append(roomColor);

        output.append("|");

        output.append(Color.ANSI_WHITE.getColor());

        return output.toString();
    }
}
