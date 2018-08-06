package com.Krevik.Networking;

import java.util.Random;

import com.Krevik.Blocks.BlockMysticBush;
import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Main.KCore;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpawnForgottenSandEntity implements IMessage {

	private double posX;
	private double posY;
	private double posZ;
	
	public PacketSpawnForgottenSandEntity() {
		
	}

	public PacketSpawnForgottenSandEntity(double x, double y, double z) {
		posX=x;
		posY=y;
		posZ=z;
	}



	@Override
	public void fromBytes(ByteBuf buf) {
		posX=buf.readDouble();
		posY=buf.readDouble();
		posZ=buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(posX);
		buf.writeDouble(posY);
		buf.writeDouble(posZ);
	}

	public static class Handler implements IMessageHandler<PacketSpawnForgottenSandEntity, IMessage> {

		@Override
		public IMessage onMessage(PacketSpawnForgottenSandEntity message, MessageContext ctx) {
			
				((WorldServer)ctx.getServerHandler().player.world).addScheduledTask(new Runnable() {
				Random random = new Random();
				@Override
				public void run() {
					KetherDataStorage data = KetherDataStorage.getDataInstance(DimensionManager.getWorld(KCore.instance.DIMENSION_ID));
					BlockPos blockpos = new BlockPos(message.posX,((WorldServer)ctx.getServerHandler().player.world).getHeight(new BlockPos(message.posX,0,message.posZ)).getY(),message.posZ);
					if(((WorldServer)ctx.getServerHandler().player.world).getBlockState(blockpos.down()) instanceof BlockMysticBush) {
						((WorldServer)ctx.getServerHandler().player.world).setBlockState(blockpos.down(), KCore.ForgottenSand.getDefaultState(),3);
					}else {
						((WorldServer)ctx.getServerHandler().player.world).setBlockState(blockpos, KCore.ForgottenSand.getDefaultState(),3);
					}

				}

			});

			return null;
		}
	}
}