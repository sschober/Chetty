package de.sssm.server.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import de.sssm.server.ApplicationContext;
import de.sssm.server.entities.Room;
import de.sssm.server.entities.User;

public class AnyHandler extends AbstractChettyHandler {

	public AnyHandler(ApplicationContext ctx) {
		super(ctx);
	}

	@Override
	String getCmdName() {
		return "";
	}

	@Override
	void handle(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		User usr = appCtx.getUserDB().getUserForChannel(e.getChannel());
		if(null != usr){
			Room r = usr.getRoom();
			if(null!=r){
				for(User p : r.getParticipants()){
					if(!p.equals(usr)){
						p.getChannel().write(usr.getName() +": "+ e.getMessage() + "\r\n");
					}
				}
			}
		}
	}

}
