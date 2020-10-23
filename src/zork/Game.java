package zork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Game - the main class of the "Zork" game.
 * <p>
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    March 2000
 * <p>
 * This class is the main class of the "Zork" application. Zork is a very
 * simple, text based adventure game.  Users can walk around some scenery.
 * That's all. It should really be extended to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * routine.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates the
 * commands that the parser returns.
 */

public class Game {

    public static Room currentRoom;

    public static boolean hasFinished = false;

    public static boolean playerHasKey = false;

    public static int MapSize = 25;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {

        // Create all the rooms and link their exits together.
        //Room outside = new Room("outside G block on Peninsula campus", new Vector2(1, 1));
        //new Room("a lecture theatre in A block", new Vector2(2, 1));
        //new Room("the Seahorse Tavern (the campus pub)", new Vector2(0, 1));
        //new Room("the G building", new Vector2(1, 0));
        //new Room("the computing admin office", new Vector2(0, 0));

        //currentRoom = outside; // start game outside

        generateRooms();
    }


    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        while (!hasFinished) {
            Command command = Parser.getCommand();
            command.processCommand();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private static void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Zork!");
        System.out.println("Zork is a simple adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.longDescription());
    }

    private void generateRooms(){
        int middle = Math.floorDiv((int) Math.sqrt(MapSize), 2);

        int maxRooms = 10;
        int rooms = 1;

        currentRoom = new Room("Starter Room", new Vector2(middle, middle));

        ArrayList<Vector2> roomPoses = new ArrayList<>();

        roomPoses.add(new Vector2(middle, middle));

        while (rooms < maxRooms){
            Vector2 pos = getRandomRoomPos(roomPoses);

            rooms += tryGenerateAround(pos, maxRooms - rooms, roomPoses);
        }

        boolean repeat = true;

        while (repeat){
            Random random = new Random();
            int roomInt = random.nextInt(maxRooms);

            if (!currentRoom.getPos().equals(roomPoses.get(roomInt))) {

                Room room = Room.Rooms.get(roomPoses.get(roomInt));
                room.IsFinishRoom = true;

                repeat = false;
            }
        }

        repeat = true;

        while (repeat){
            Random random = new Random();
            int roomInt = random.nextInt(maxRooms);

            Vector2 vector2 = roomPoses.get(roomInt);

            if (!currentRoom.getPos().equals(vector2) && !Room.getFinishRoom().getPos().equals(vector2)) {

                Room room = Room.Rooms.get(roomPoses.get(roomInt));
                room.HasKey = true;

                repeat = false;
            }
        }
    }

    private Vector2 getRandomRoomPos(ArrayList<Vector2> roomPoses){
        Random random = new Random();
        int rand = random.nextInt(roomPoses.toArray().length);

        return roomPoses.get(rand);
    }

    private int tryGenerateAround(Vector2 pos, int maxRooms, ArrayList<Vector2> roomPoses){
        Random rand = new Random();

        int added = 0;

        if (added >= maxRooms){
            return added;
        }

        Vector2 newPos;

        if (rand.nextBoolean()) {
            newPos = new Vector2(pos.x + 1, pos.y);
            if (tryGenerateRoom(newPos)) {
                added++;
                roomPoses.add(newPos);
            }
        }

        if (added >= maxRooms){
            return added;
        }

        if (rand.nextBoolean()) {
            newPos = new Vector2(pos.x, pos.y + 1);
            if (tryGenerateRoom(newPos)) {
                added++;
                roomPoses.add(newPos);
            }
        }

        if (added >= maxRooms){
            return added;
        }

        if (rand.nextBoolean()) {
            newPos = new Vector2(pos.x - 1, pos.y);
            if (tryGenerateRoom(newPos)) {
                added++;
                roomPoses.add(newPos);
            }
        }

        if (added >= maxRooms){
            return added;
        }

        if (rand.nextBoolean()) {
            newPos = new Vector2(pos.x, pos.y - 1);
            if (tryGenerateRoom(newPos)) {
                added++;
                roomPoses.add(newPos);
            }
        }

        return added;
    }

    private boolean tryGenerateRoom(Vector2 pos){
        int mapLength = (int) Math.sqrt(MapSize);

        if (pos.x < 0 || pos.x >= mapLength || pos.y < 0 || pos.y >= mapLength){
            return false;
        }

        if (Room.Rooms.containsKey(pos)){
            return false;
        }

        new Room("Normal Room", pos);
        return true;
    }
}
