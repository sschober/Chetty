package de.sssm.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import de.sssm.server.handler.AnyHandler;
import de.sssm.server.handler.AuthenticationHandler;
import de.sssm.server.handler.ByeHandler;
import de.sssm.server.handler.LeaveHandler;
import de.sssm.server.handler.NewChatHandler;
import de.sssm.server.handler.UserListHandler;

public class ChettyServerPipelineFactory implements ChannelPipelineFactory {

	private ApplicationContext appCtx;

	public ChettyServerPipelineFactory(ApplicationContext appCtx) {
		super();
		this.appCtx = appCtx;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline cp = Channels.pipeline();
		cp.addLast("framer", new DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));
		cp.addLast("decoder", new StringDecoder());
		cp.addLast("encoder", new StringEncoder());
		
		// business logic
		cp.addLast("auth", new AuthenticationHandler(appCtx));
		cp.addLast("ul", new UserListHandler(appCtx));
		cp.addLast("bye", new ByeHandler(appCtx));
		cp.addLast("join", new NewChatHandler(appCtx));
		cp.addLast("leave", new LeaveHandler(appCtx));
		cp.addLast("any", new AnyHandler(appCtx));
		
		return cp;
	}

}
