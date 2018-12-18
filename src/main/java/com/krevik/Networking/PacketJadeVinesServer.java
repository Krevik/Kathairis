package com.krevik.Networking;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketJadeVinesServer implements IMessage {



	public PacketJadeVinesServer() {
	}



	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketJadeVinesServer, IMessage> {

		@Override
		public IMessage onMessage(PacketJadeVinesServer message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					ctx.getServerHandler().player.motionY=0.1;
					ctx.getServerHandler().player.fallDistance=0;
					ctx.getServerHandler().player.velocityChanged=true;
				}

			});

			return null;
		}
	}
}