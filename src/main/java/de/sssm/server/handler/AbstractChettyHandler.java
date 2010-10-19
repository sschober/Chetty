package de.sssm.server.handler;

import java.net.InetAddress;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import de.sssm.server.ApplicationContext;

public abstract class AbstractChettyHandler extends
		SimpleChannelUpstreamHandler {
	
	protected ApplicationContext appCtx;
	protected String[] splitLine;

	abstract String getCmdName();
	abstract void handle(ChannelHandlerContext ctx, MessageEvent e) throws Exception;
	
	private static final Logger logger = Logger.getLogger(
            AbstractChettyHandler.class.getName());
	
	public AbstractChettyHandler(ApplicationContext ctx) {
		super();
		this.appCtx = ctx;
	}
	
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
    		throws Exception {
    	e.getChannel().write("Welcome to Chetty@"+InetAddress.getLocalHost().getHostName() + "!\r\n");
    }
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		if(isMessage(getCmdName(), e)){
			logger.info(e.toString());
			handle(ctx, e);
		}
		else ctx.sendUpstream(e);
	}
	
	protected boolean isMessage(String msg, MessageEvent e){
		String req = (String) e.getMessage();
		splitLine = req.split(" ");
		// for AnyHandler TODO: ugly
		if(msg.equals("")) return true;
		if(null!=splitLine && splitLine.length > 0)
			return splitLine[0].toLowerCase().equals(msg);
		return false;
	}
}
