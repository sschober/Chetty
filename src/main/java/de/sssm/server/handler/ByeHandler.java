package de.sssm.server.handler;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import de.sssm.server.ApplicationContext;

public class ByeHandler extends AbstractChettyHandler {
	public ByeHandler(ApplicationContext ctx) {
		super(ctx);
	}

	@Override
	public void handle(ChannelHandlerContext ctx, MessageEvent e)
	throws Exception {
		ChannelFuture cf = e.getChannel().write("Bye.\r\n");
		cf.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	String getCmdName() {
		return "/bye";
	}
}
