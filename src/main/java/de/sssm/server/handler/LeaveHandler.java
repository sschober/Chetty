package de.sssm.server.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import de.sssm.server.ApplicationContext;
import de.sssm.server.entities.Room;
import de.sssm.server.entities.User;

public class LeaveHandler extends AbstractChettyHandler {

	public LeaveHandler(ApplicationContext ctx) {
		super(ctx);
	}

	@Override
	String getCmdName() {
		return "/l";
	}

	@Override
	void handle(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		User usr = appCtx.getUserDB().getUserForChannel(e.getChannel());
		if(null != usr){
			Room r = usr.getRoom();
			if(null!=r){
				if(!r.leave(usr)){
					appCtx.getRooms().remove(r);
				}
			}
		}
	}

}
