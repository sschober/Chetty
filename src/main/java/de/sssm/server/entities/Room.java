package de.sssm.server.entities;

import java.util.HashSet;
import java.util.Set;

public class Room {
	private Set<User> participants;

	public Room(User a, User b){
		participants = new HashSet<User>();
		participants.add(a);
		a.setRoom(this);
		participants.add(b);
		b.setRoom(this);
	}
	
	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public Set<User> getParticipants() {
		return participants;
	}
	
	public boolean leave(User u){
		if(participants.remove(u)){
			u.leaveRoom();
		}
		
		if(participants.size() == 0) return false;

		for(User p : participants){
			p.getChannel().write("User: " + p + " left the room.\r\n");
		}
		return true;
	}

	public String toString(){
		String res = "(";
		for(User p : participants){
			res += " " + p.getName();
		}
		res += " )";
		return res;
	}

}
