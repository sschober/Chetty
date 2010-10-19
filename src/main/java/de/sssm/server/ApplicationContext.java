package de.sssm.server;

import java.util.HashSet;
import java.util.Set;

import de.sssm.server.entities.Room;

public class ApplicationContext {
	private UserDB userDB = new UserDB();
	private Set<Room> rooms = new HashSet<Room>();
	
	public Set<Room> getRooms() {
		return rooms;
	}
	public UserDB getUserDB() {
		return userDB;
	}
}
