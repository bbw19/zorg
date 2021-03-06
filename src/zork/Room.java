package zork;
/*
 * Class Room - a room in an adventure game.
 *
 * Author:  Michael Kolling
 * Version: 1.1
 * Date:    August 2000
 * 
 * This class is part of Zork. Zork is a simple, text based adventure game.
 *
 * "Room" represents one location in the scenery of the game.  It is 
 * connected to at most four other rooms via exits.  The exits are labelled
 * north, east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 */

import java.util.HashMap;

public class Room {

	public static HashMap<Vector2, Room> Rooms = new HashMap<>();

	public boolean IsFinishRoom = false;
	public boolean HasKey = false;

	private String description;
	private Vector2 RoomPos;

	/**
	 * Create a room described "description". Initially, it has no exits.
	 * "description" is something like "a kitchen" or "an open court yard".
	 */
	public Room(String description, Vector2 roomPos) {
		this.description = description;
		RoomPos = roomPos;
		Rooms.put(roomPos, this);
	}

	/**
	 * Return the description of the room (the one that was defined in the
	 * constructor).
	 */
	public String shortDescription() {
		return description;
	}

	/**
	 * Return a long description of this room, on the form:
	 *     You are in the kitchen.
	 *     Exits: north west
	 */
	public String longDescription() {
		return "You are in " + description + ".\n" + exitString();
	}

	/**
	 * Return a string describing the room's exits, for example
	 * "Exits: north west ".
	 */
	private String exitString() {
		String returnString = "Exits:";

		if (Rooms.containsKey(new Vector2(RoomPos.x, RoomPos.y + 1))){
			returnString += " " + "north";
		}

		if (Rooms.containsKey(new Vector2(RoomPos.x + 1, RoomPos.y))){
			returnString += " " + "east";
		}

		if (Rooms.containsKey(new Vector2(RoomPos.x, RoomPos.y - 1))){
			returnString += " " + "south";
		}

		if (Rooms.containsKey(new Vector2(RoomPos.x - 1, RoomPos.y))){
			returnString += " " + "west";
		}

		return returnString;
	}

	public Room getRoomFromDirection(String stringDirection){
		switch (stringDirection) {
			case "north":
				return Rooms.getOrDefault(new Vector2(RoomPos.x, RoomPos.y + 1), null);
			case "east":
				return Rooms.getOrDefault(new Vector2(RoomPos.x + 1, RoomPos.y), null);
			case "south":
				return Rooms.getOrDefault(new Vector2(RoomPos.x, RoomPos.y - 1), null);
			case "west":
				return Rooms.getOrDefault(new Vector2(RoomPos.x - 1, RoomPos.y), null);
			default:
				return null;
		}
	}

	public static Room getFinishRoom(){
		for (Room room :
				Room.getRooms().values()) {
			if (room.IsFinishRoom){
				return room;
			}
		}

		return null;
	}

	public static Room getKeyRoom(){
		for (Room room :
				Room.getRooms().values()) {
			if (room.HasKey){
				return room;
			}
		}

		return null;
	}

	public static HashMap<Vector2, Room> getRooms() {
		return Rooms;
	}

	public Vector2 getPos() {
		return this.RoomPos;
	}
}
