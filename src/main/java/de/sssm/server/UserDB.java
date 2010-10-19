package de.sssm.server;

import java.util.Collection;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import de.sssm.server.entities.User;

public class UserDB {
	
	private Map<Channel, User> channelToUser = new ConcurrentHashMap<Channel, User>();;
	private Map<String,User> nameToUser = new ConcurrentHashMap<String, User>();;
	
	public boolean addUserWithNameAndChannel(String name, Channel c){
		if(nameToUser.containsKey(name)) return false;
		User usr = new User(name, c);
		nameToUser.put(name, usr);
		channelToUser.put(c, usr);
		return true;
	}
	
	public User getUserForName(String name){
		return nameToUser.get(name);
	}
	
	public User getUserForChannel(Channel c){
		return channelToUser.get(c);
	}
	
	public Collection<User> getAllUsers(){
		return channelToUser.values();
	}
}
