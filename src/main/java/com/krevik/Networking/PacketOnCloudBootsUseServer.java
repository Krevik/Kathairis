package com.krevik.Networking;

import java.util.Random;

import com.krevik.Items.ItemMysticArmor;

import io.netty.buffer.ByteBuf;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOnCloudBootsUseServer implements IMessage {



	public PacketOnCloudBootsUseServer() {
	}



	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketOnCloudBootsUseServer, IMessage> {

		@Override
		public IMessage onMessage(PacketOnCloudBootsUseServer message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				Random random = new Random();
				@Override
				public void run() {
					ctx.getServerHandler().player.world.playSound(ctx.getServerHandler().player.posX, ctx.getServerHandler().player.posY, ctx.getServerHandler().player.posZ, SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 1F, 1F, false);
					ctx.getServerHandler().player.motionY+=0.75;
					ctx.getServerHandler().player.fallDistance=0;
					ctx.getServerHandler().player.velocityChanged=true;
					ctx.getServerHandler().player.inventory.getStackInSlot(36).damageItem(1, ctx.getServerHandler().player);
					((ItemMysticArmor)ctx.getServerHandler().player.inventory.getStackInSlot(36).getItem()).wereCloudBootsUsed=true;
				}

			});

			return null;
		}
	}
}