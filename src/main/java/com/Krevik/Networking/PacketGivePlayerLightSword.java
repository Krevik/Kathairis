package com.Krevik.Networking;

import java.util.Random;

import com.Krevik.Gens.WorldGenDeathsTower;
import com.Krevik.Main.KCore;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGivePlayerLightSword implements IMessage {



	public PacketGivePlayerLightSword() {
	}



	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketGivePlayerLightSword, IMessage> {

		@Override
		public IMessage onMessage(PacketGivePlayerLightSword message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				IBlockState iblockstate = DimensionManager.getWorld(KCore.DIMENSION_ID).getBlockState(new BlockPos(666,ctx.getServerHandler().player.getEntityWorld().getHeight(666, 666),666));
				Random random = new Random();
				@Override
				public void run() {
					ctx.getServerHandler().player.addItemStackToInventory(new ItemStack(KCore.LightSword));
				}

			});

			return null;
		}
	}
}