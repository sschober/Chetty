package de.sssm.server.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import de.sssm.server.ApplicationContext;
import de.sssm.server.entities.User;

public class UserListHandler extends AbstractChettyHandler {
	public UserListHandler(ApplicationContext ctx) {
		super(ctx);
	}

	@Override
	String getCmdName() {
		return "/ul";
	}

	@Override
	void handle(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		String res = null;
		res = "Users:\r\n";
		for(User u : appCtx.getUserDB().getAllUsers()){
			res += u.getName() +"\r\n";
		}
    	e.getChannel().write(res);
	}
}
