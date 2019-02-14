package mod.krevik.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Random;

public class PacketOnCloudBootsUseClient implements IMessage {



	public PacketOnCloudBootsUseClient() {
	}



	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketOnCloudBootsUseClient, IMessage> {

		@Override
		public IMessage onMessage(PacketOnCloudBootsUseClient message, MessageContext ctx) {

    		Random rand = new Random();
    		EntityPlayerSP player = Minecraft.getMinecraft().player;
            for (int i = 0; i < 24; ++i)
            {
                double d0 = player.posX + rand.nextDouble() - rand.nextDouble();
                double d1 = player.posY;
                double d2 = player.posZ + rand.nextDouble() - rand.nextDouble();
                double d3 = 0;
                double d4 = -0.5;
                double d5 = 0;
                player.world.spawnParticle(EnumParticleTypes.SPELL, d0, d1, d2, d3, d4, d5);
            }
			return null;
		}
	}
}