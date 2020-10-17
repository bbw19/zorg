package zork;

import java.lang.Math;
import java.util.HashMap;

public class Map {
    private Room initialRoom;
    private int fieldSize;
    private Vector2[] wholeField;
    private int roomWidth = 6;
    private int roomHeight = 3;
    private HashMap<Vector2, Room> allRooms;

    public Map(Room initialRoom, int fieldSize) {
        this.initialRoom = initialRoom;
        this.fieldSize = fieldSize;
        this.allRooms = initialRoom.getRooms();
    }

    public void drawMap() {
        int sqrt = (int) Math.sqrt(fieldSize);

        for(int i = 0; i < sqrt; i++) {
            int xVal = ( (sqrt-1) /2 ) - i;

            for(int q = 0; q < roomHeight; q++) {

                for(int j = 0; j < sqrt; j++) {
                    int yVal = ( (sqrt-1) /2 ) - j;
                    yVal *= -1;

                    for(int p = 0; p < roomWidth; p++) {

                        if(allRooms.containsKey(new Vector2(yVal, xVal))) {
                            if(q == 0 || q == roomHeight-1) {
                                System.out.print("-");
                            } else {
                                if (p == 0 || p == roomWidth -1) {
                                    System.out.print("|");
                                } else {
                                    if ( initialRoom.getPos().equals(new Vector2(yVal, xVal))) {
                                        System.out.print(Color.ANSI_RED.getColor() + "X" + Color.ANSI_WHITE.getColor());
                                    } else {
                                        System.out.print(" ");
                                    }
                                }
                            }
                        } else{
                            System.out.print("o");
                        }
                    }
                    System.out.print(" ");
                } 
                System.out.println();
            }
        }
    }


}
