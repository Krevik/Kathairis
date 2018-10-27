package com.Krevik.Networking;

import java.util.ArrayList;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Main.KCore;

import com.Krevik.Particles.DynamicParticle;
import com.Krevik.Particles.ParticlesFactory;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketDustStormClient implements IMessage {



	public PacketDustStormClient() {
	}



	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler implements IMessageHandler<PacketDustStormClient, IMessage> {

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PacketDustStormClient message, MessageContext ctx) {
			KetherDataStorage data = KCore.data.getDataInstance(Minecraft.getMinecraft().player.world);
			if(ctx.side.isClient()) {
				EntityPlayer player = Minecraft.getMinecraft().player;
				if(player!=null) {	
						if(player.dimension==KCore.instance.DIMENSION_ID) {
							if(data!=null) {
							ArrayList<BlockPos> positions=new ArrayList<BlockPos>();
							positions.add(player.getPosition());

							for(int x=0;x<=8;x++) {
								for(int z=0;z<=8;z++) {
									for(int y=0;y<=6;y++) {
										BlockPos tmp1 = new BlockPos(player.posX+x,player.posY+y,player.posZ+z);
										BlockPos tmp2 = new BlockPos(player.posX+x,player.posY+y,player.posZ-z);
										BlockPos tmp3 = new BlockPos(player.posX-x,player.posY+y,player.posZ+z);
										BlockPos tmp4 = new BlockPos(player.posX-x,player.posY+y,player.posZ-z);
										BlockPos tmp5 = new BlockPos(player.posX+x,player.posY-y,player.posZ+z);
										BlockPos tmp6 = new BlockPos(player.posX+x,player.posY-y,player.posZ-z);
										BlockPos tmp7 = new BlockPos(player.posX-x,player.posY-y,player.posZ+z);
										BlockPos tmp8 = new BlockPos(player.posX-x,player.posY-y,player.posZ-z);
										positions.add(tmp1);
										positions.add(tmp2);
										positions.add(tmp3);
										positions.add(tmp4);
										positions.add(tmp5);
										positions.add(tmp6);
										positions.add(tmp7);
										positions.add(tmp8);
									}
								}
							}
							for(int x=0;x<positions.size()-1;x++) {
								if(player.world.isAirBlock(positions.get(x))) {
									if(player.getRNG().nextInt(50)==0) {
										if(player.world.getBiome(positions.get(x))==KCore.instance.MysticDesert&&player.posY>63&&player.posY<84) {
											double d0=positions.get(x).getX()+0.5+(player.getRNG().nextFloat()-player.getRNG().nextFloat())*0.5;
											double d1=positions.get(x).getY()+0.5+(player.getRNG().nextFloat()-player.getRNG().nextFloat())*0.5;
											double d2=positions.get(x).getZ()+0.5+(player.getRNG().nextFloat()-player.getRNG().nextFloat())*0.5;
											double d3=data.getSandstormX();
											double d4=(player.getRNG().nextFloat()-player.getRNG().nextFloat())*0.3;
											double d5=data.getSandstormZ();
											//KCore.instance.cproxy.drawParticle(player.world, new DustParticle(player.world,d0,d1,d2,d3,d4,d5));
											Particle theParticle = new DynamicParticle(
													ParticlesFactory.DUST,
													player.world,
													d0, d1, d2,
													d3, d4, d5)
													.setRotSpeed((player.getRNG().nextFloat()-0.5f)*0.15f)
													.setLifeSpan(40 + player.getRNG().nextInt(20))
													.setGravity(0F)
													.setScale(2.0F)
													.setInitialAlpha(1.0F)
													.setFinalAlpha(1.0F);
											Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
										}
									}
								}
							}
								if(player.world.getBiome(player.getPosition())==KCore.instance.MysticDesert&&player.posY>63&&player.posY<84) {
									MoveMeDuringSandstormServer message1 = new MoveMeDuringSandstormServer(data.getSandstormX(),data.getSandstormZ());
									//KetherPacketHandler.CHANNEL.sendToServer(message1);
								}
							}
						}
				}
			}

			return null;
		}
	}
}