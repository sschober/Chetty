package de.sssm.server.handler;

import java.util.logging.Logger;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import de.sssm.server.ApplicationContext;
import de.sssm.server.entities.User;

public class AuthenticationHandler extends SimpleChannelUpstreamHandler {

	ApplicationContext appCtx;

	
	private static final Logger logger = Logger.getLogger(
            AuthenticationHandler.class.getName());

	public AuthenticationHandler(ApplicationContext appCtx) {
		super();
		this.appCtx = appCtx;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Channel c = e.getChannel();
		User usr = appCtx.getUserDB().getUserForChannel(c);
		String res = null;
		if(null == usr){
			// no user for channel
			String[] splitLine = ((String) e.getMessage()).split(" ");
			if(null == splitLine 
					|| splitLine.length < 2 
					|| !splitLine[0].toLowerCase().equals("/r")) {
				res = "You need to register first (/r <name>).\r\n";
				e.getChannel().write(res);
			}
			else {
				String name = splitLine[1];
				if(name != null && name.length() > 0){
					appCtx.getUserDB().addUserWithNameAndChannel(name, c);
					logger.info("Registered new user: " + name);
				}
				else{
					res = "No user name given. Usage: /r <name>\r\n";
					e.getChannel().write(res);
				}
			}
		}
		else {
			ctx.sendUpstream(e);
		}
	}
}
