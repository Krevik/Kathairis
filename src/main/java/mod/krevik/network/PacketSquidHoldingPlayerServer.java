package mod.krevik.network;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSquidHoldingPlayerServer implements IMessage {

	double destX;
	double destY;
	double destZ;
	
	public PacketSquidHoldingPlayerServer() {
	}

	public PacketSquidHoldingPlayerServer(double x, double y, double z) {
		destX=x;
		destY=y;
		destZ=z;
	}



	@Override
	public void fromBytes(ByteBuf buf) {
		destX=buf.readDouble();
		destY=buf.readDouble();
		destZ=buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(destX);
		buf.writeDouble(destY);
		buf.writeDouble(destZ);
	}

	public static class Handler implements IMessageHandler<PacketSquidHoldingPlayerServer, IMessage> {

		@Override
		public IMessage onMessage(PacketSquidHoldingPlayerServer message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				Random random = new Random();
				@Override
				public void run() {
					ctx.getServerHandler().player.setPositionAndUpdate(message.destX, message.destY, message.destZ);
					ctx.getServerHandler().player.rotationPitch=ctx.getServerHandler().player.prevRotationPitch;
					ctx.getServerHandler().player.rotationYaw=ctx.getServerHandler().player.prevRotationYaw;
				}

			});

			return null;
		}
	}
}