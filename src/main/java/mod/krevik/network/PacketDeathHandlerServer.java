package mod.krevik.network;

import mod.krevik.world.dimension.KetherDataStorage;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDeathHandlerServer implements IMessage {

	private boolean isDeathSpawned;
	private boolean isDeathDefeated;
	private boolean isDeathFighting;

	public PacketDeathHandlerServer() {
		
	}

	public PacketDeathHandlerServer(boolean spawned,boolean fighting,boolean defeated) {
		this.isDeathSpawned=spawned;
		this.isDeathFighting=fighting;
		this.isDeathDefeated=defeated;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		isDeathSpawned=buf.readBoolean();
		isDeathFighting=buf.readBoolean();
		isDeathDefeated=buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isDeathSpawned);
		buf.writeBoolean(isDeathFighting);
		buf.writeBoolean(isDeathDefeated);
	}

	public static class Handler implements IMessageHandler<PacketDeathHandlerServer, IMessage> {

		@Override
		public IMessage onMessage(PacketDeathHandlerServer message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {

				@Override
				public void run() {
					KetherDataStorage.getDataInstance(ctx.getServerHandler().player.getServerWorld()).setIsDeathSpawned(message.isDeathSpawned);
					KetherDataStorage.getDataInstance(ctx.getServerHandler().player.getServerWorld()).setIsDeathFighting(message.isDeathFighting);
					KetherDataStorage.getDataInstance(ctx.getServerHandler().player.getServerWorld()).setIsDeathDefeated(message.isDeathDefeated);
					IMessage message11 = new PacketDeathHandlerClient(message.isDeathSpawned,message.isDeathFighting,message.isDeathDefeated);
					KetherPacketHandler.CHANNEL.sendToAll(message11);
				}
				
			});



			return null;
		}
	}
}