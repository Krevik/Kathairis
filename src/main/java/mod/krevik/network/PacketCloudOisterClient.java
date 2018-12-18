package mod.krevik.network;

import mod.krevik.KCore;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCloudOisterClient implements IMessage {

	private float posX;
	private float posY;
	private float posZ;

	public PacketCloudOisterClient() {
	}

	public PacketCloudOisterClient(float posx,float posy,float posz) {
		this.posX=posx;
		this.posY=posy;
		this.posZ=posz;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		posX=buf.readFloat();
		posY=buf.readFloat();
		posZ=buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(posX);
		buf.writeFloat(posY);
		buf.writeFloat(posZ);
	}

	public static class Handler implements IMessageHandler<PacketCloudOisterClient, IMessage> {

		@Override
		public IMessage onMessage(PacketCloudOisterClient message, MessageContext ctx) {
		        for (int i = 0; i < 24; ++i)
		        {
		            double d0 = message.posX + KCore.instance.functionHelper.random.nextDouble() - KCore.instance.functionHelper.random.nextDouble();
		            double d1 = message.posY;
		            double d2 = message.posZ + KCore.instance.functionHelper.random.nextDouble() - KCore.instance.functionHelper.random.nextDouble();
		            double d3 = 0;
		            double d4 = -0.5;
		            double d5 = 0;
		            Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.SPELL, d0, d1, d2, d3, d4, d5);
		        }
			return null;
		}
	}
}