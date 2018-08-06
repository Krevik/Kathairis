package com.Krevik.Events;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Items.ItemMysticArmor;
import com.Krevik.Main.KCore;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketDustStormClient;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KetherEventsHandler {
	

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onEvent(PlaySoundEvent event)
	{
		ISound sound = event.getSound();

		if(sound!=null&&sound.getCategory() == SoundCategory.MUSIC) {
			EntityPlayer ep = FMLClientHandler.instance().getClient().player;
			if(ep!=null) {
				if(ep.dimension==KCore.DIMENSION_ID) {
					PositionedSoundRecord  positionedDaySound;
					PositionedSoundRecord  positionedNightSound;
					positionedDaySound=PositionedSoundRecord.getMusicRecord(KCore.instance.cproxy.dayMusic);
					positionedNightSound=PositionedSoundRecord.getMusicRecord(KCore.instance.cproxy.nightMusic);
					if(sound.getSoundLocation().getResourcePath().toString()!=positionedDaySound.getSoundLocation().getResourcePath().toString()&&sound.getSoundLocation().getResourcePath().toString()!=positionedNightSound.getSoundLocation().getResourcePath().toString()) {
						if(ep.world.getWorldTime()>12000) {
							event.setResultSound(positionedNightSound);
						}else {
							event.setResultSound(positionedDaySound);
						}
					}
				}
			}
		}
	}
	

	static KetherDataStorage data = null;
	@SubscribeEvent
	public static void onEvent1(WorldTickEvent event)
	{
		KCore.instance.updateRendererCount++;
				if(data==null) {
					data = KetherDataStorage.getDataInstance(DimensionManager.getWorld(KCore.instance.DIMENSION_ID));
				}
				if(data!=null&&event.world.getTotalWorldTime()>100) {
					if(!event.world.isRaining()) {
						if(!data.getIsSandstorm()) {
							if(event.world.rand.nextInt(500000)==0) {
								data.setSandstormTime(9000+event.world.rand.nextInt(20000));
								data.setSandstormX((event.world.rand.nextDouble()-event.world.rand.nextDouble()));
								data.setSandstormZ((event.world.rand.nextDouble()-event.world.rand.nextDouble()));
								data.setIsSandstorm(true);
							}
						}
					}
					if(data.getSandstormTime()>0) {
						data.setIsSandstorm(true);
						data.setSandstormTime(data.getSandstormTime()-1);
						if(!data.getIsSandstorm()) {
							data.setSandstormTime(0);
						}
					}else {
						data.setSandstormTime(0);
						data.setIsSandstorm(false);
						data.setSandstormX(0);
						data.setSandstormZ(0);
					}
					
					if(data.getIsSandstorm()) {
						IMessage message2 = new PacketDustStormClient();
						KetherPacketHandler.CHANNEL.sendToAll(message2);
					}
				}
	}
	
	@SubscribeEvent
	public static void onEvent2(PlayerTickEvent event)
	{
		if(event.player!=null) {
			if(!event.player.inventory.getStackInSlot(36).isEmpty()) {
				if(event.player.inventory.getStackInSlot(36).getItem().equals(KCore.CloudBoots)) {
					event.player.fallDistance=0;
					if(event.player.onGround) {
						((ItemMysticArmor)(event.player.inventory.getStackInSlot(36).getItem())).wereCloudBootsUsed=false;
					}
				}
			}
				if(data!=null) {
					if(data.getIsSandstorm()) {
						if(event.player.world.getBiome(event.player.getPosition())==KCore.MysticDesert) {
							if(event.player.motionY==0D) {
								event.player.motionX+=data.getSandstormX()*0.09;
								event.player.motionZ+=data.getSandstormZ()*0.09;
							}else {
								event.player.motionX+=data.getSandstormX()*0.022;
								event.player.motionZ+=data.getSandstormZ()*0.022;
							}
							event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(15),10,100));
						}
					}
				}
				//TODO SOUND
				if(data!=null) {
					if(data.getIsSandstorm()) {
						if(event.player.world.getBiome(event.player.getPosition())==KCore.MysticDesert) {
							if(event.player.getRNG().nextInt(350)==0) {
								event.player.playSound(KCore.instance.cproxy.sandstorm, 1, 1);
							}
						}
					}
				}


		}
	}
	
}
