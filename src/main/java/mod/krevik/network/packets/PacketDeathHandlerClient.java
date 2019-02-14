package mod.krevik.network.packets;

import io.netty.buffer.ByteBuf;
import mod.krevik.world.dimension.KathairisDataStorage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketDeathHandlerClient implements IMessage {

	private boolean isDeathSpawned;
	private boolean isDeathDefeated;
	private boolean isDeathFighting;

	public PacketDeathHandlerClient() {
	}

	public PacketDeathHandlerClient(boolean spawned,boolean fighting,boolean defeated) {
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

	public static class Handler implements IMessageHandler<PacketDeathHandlerClient, IMessage> {

		@Override
		public IMessage onMessage(PacketDeathHandlerClient message, MessageContext ctx) {

					KathairisDataStorage.getDataInstance(Minecraft.getMinecraft().player.world).setIsDeathSpawned(message.isDeathSpawned);
					KathairisDataStorage.getDataInstance(Minecraft.getMinecraft().player.world).setIsDeathFighting(message.isDeathFighting);
					KathairisDataStorage.getDataInstance(Minecraft.getMinecraft().player.world).setIsDeathDefeated(message.isDeathDefeated);

			return null;
		}
	}
}