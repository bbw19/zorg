package zork;

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

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {

        // Create all the rooms and link their exits together.
        Room outside = new Room("outside G block on Peninsula campus", new Vector2(1, 1));
        new Room("a lecture theatre in A block", new Vector2(2, 1));
        new Room("the Seahorse Tavern (the campus pub)", new Vector2(0, 1));
        new Room("the G building", new Vector2(1, 0));
        new Room("the computing admin office", new Vector2(0, 0));

        currentRoom = outside; // start game outside

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
}
