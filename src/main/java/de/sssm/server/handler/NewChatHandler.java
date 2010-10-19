package de.sssm.server.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import de.sssm.server.ApplicationContext;
import de.sssm.server.entities.Room;
import de.sssm.server.entities.User;

public class NewChatHandler extends AbstractChettyHandler {

	public NewChatHandler(ApplicationContext ctx) {
		super(ctx);
	}

	@Override
	String getCmdName() {
		return "/q";
	}

	@Override
	void handle(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		String chatPartner = null;
		if(splitLine.length > 1){
			chatPartner = splitLine[1];
			User partner = appCtx.getUserDB().getUserForName(chatPartner);
			if(null != partner){
				if(null != partner.getRoom()){
					String res = "User " + partner + " already in a chat.\r\n";
			    	e.getChannel().write(res);
				}
				else{
					User usr = appCtx.getUserDB().getUserForChannel(e.getChannel());
					if(null != usr){
						Room oldRoom = usr.getRoom();
						if(null != oldRoom){
							oldRoom.leave(usr);
						}
						Room room = new Room(usr,partner);
						appCtx.getRooms().add(room);
					}
				}
			}
		}
	}

}
