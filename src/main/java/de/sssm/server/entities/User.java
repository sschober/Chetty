package de.sssm.server.entities;

import org.jboss.netty.channel.Channel;

public class User {
	private String name;
	private Channel channel;
	private Room room;
	
	public User(String name, Channel channel){
		this.name = name;
		this.setChannel(channel);
	}
	
	public String toString(){ return name; };
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	private void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setRoom(Room room) {
		this.room = room;
		channel.write("You entered room: " + room + "\r\n");
	}
	public Room getRoom() {
		return room;
	}

	public void leaveRoom() {
		room.leave(this);
		channel.write("You left room: " + room +"\r\n");
		room = null;
	}

}
