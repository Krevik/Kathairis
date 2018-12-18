package mod.krevik.Networking;

import java.util.Random;

import mod.krevik.Gens.WorldGenDeathsTower;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnDeathServer implements IMessage {



	public PacketSpawnDeathServer() {
	}



	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketSpawnDeathServer, IMessage> {

		@Override
		public IMessage onMessage(PacketSpawnDeathServer message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				IBlockState iblockstate = ctx.getServerHandler().player.getEntityWorld().getBlockState(new BlockPos(666,ctx.getServerHandler().player.getEntityWorld().getHeight(666, 666),666));
				Random random = new Random();
				@Override
				public void run() {
					ctx.getServerHandler().player.getEntityWorld().notifyBlockUpdate(new BlockPos(666,ctx.getServerHandler().player.getEntityWorld().getHeight(666, 666),666), iblockstate, iblockstate, 3);
					new WorldGenDeathsTower().generate(ctx.getServerHandler().player.getEntityWorld(), random, new BlockPos(666,ctx.getServerHandler().player.getEntityWorld().getHeight(666, 666),666));
					ctx.getServerHandler().player.getEntityWorld().notifyBlockUpdate(new BlockPos(666,ctx.getServerHandler().player.getEntityWorld().getHeight(666, 666),666), iblockstate, iblockstate, 3);
					
				}

			});

			return null;
		}
	}
}