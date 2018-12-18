package mod.krevik.Events;

import mod.krevik.Blocks.BlockChristmasGift;
import mod.krevik.Dimension.KetherDataStorage;
import mod.krevik.Items.ItemMysticArmor;
import mod.krevik.Main.KCore;
import mod.krevik.Networking.KetherPacketHandler;
import mod.krevik.Networking.PacketDustStormClient;
import mod.krevik.Networking.PacketSandstormUpdatedOnClient;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Calendar;
import java.util.Random;

public class KathairisEventsHandler {


	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onEvent(PlaySoundEvent event)
	{
		/*
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
		*/
	}

	@SubscribeEvent
	public static void handleServerTick(TickEvent.ServerTickEvent event){

	}

	@SubscribeEvent
	public static void onChangeDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event){
		if(event.player!=null){

		}
	}



	@SubscribeEvent
	public static void onEvent1(WorldTickEvent event)
	{
		KCore.instance.updateRendererCount++;
					KetherDataStorage data = KCore.data.getDataInstance(event.world);

					if(!event.world.isRemote){
						data = KCore.data.getDataInstance(event.world);
						if(data.getIsSandstorm()) {
							PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(data.getIsSandstorm(), data.getSandstormX(), data.getSandstormTime(), data.getSandstormZ());
							KetherPacketHandler.CHANNEL.sendToAll(message);
						}
					}

				if(data!=null&&event.world.getTotalWorldTime()>100) {
                        if (!data.getIsSandstorm()) {
                            if (event.world.rand.nextInt(500000) == 0) {
                                if (!event.world.isRemote) {
                                    data = KCore.data.getDataInstance(event.world);
                                    float time = 9000 + event.world.rand.nextInt(20000);
                                    double X = (event.world.rand.nextDouble() - event.world.rand.nextDouble());
                                    double Z = (event.world.rand.nextDouble() - event.world.rand.nextDouble());
                                    data.setSandstormTime((int) time);
                                    data.setSandstormX(X);
                                    data.setSandstormZ(Z);
                                    data.setIsSandstorm(true);
                                    PacketSandstormUpdatedOnClient message = new PacketSandstormUpdatedOnClient(true, X, time, Z);
                                    KetherPacketHandler.CHANNEL.sendToAll(message);
                                }
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
					if(data.getIsSandstorm()){
						if(!event.world.isRemote){
							for(EntityPlayer player:event.world.getMinecraftServer().getPlayerList().getPlayers()) {
							    if(event.world.getBiome(player.getPosition())==KCore.MysticDesert) {
                                    if (player.motionY == 0D) {
                                        player.motionX += data.getSandstormX() * 0.0080;
                                        player.motionZ += data.getSandstormZ() * 0.0080;
                                        player.velocityChanged = true;
                                    } else {
                                        player.motionX += data.getSandstormX() * 0.0017;
                                        player.motionZ += data.getSandstormZ() * 0.0017;
                                        player.velocityChanged = true;
                                    }
                                    player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 100, 100));
                                }
							}
						}
					}
	}

	static int month = Calendar.getInstance().get(Calendar.MONTH);
	private static IBlockState pickRandomPresentState(Random random){
		int k=random.nextInt(3);
		if(k==0){
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.RED);
		}
		else if(k==1){
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.YELLOW);
		}
		else if(k==2){
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
		}else{
			return KCore.christmas_gift.getDefaultState().withProperty(BlockChristmasGift.VARIANT,BlockChristmasGift.EnumType.VIOLET);
		}
	}
	@SubscribeEvent
	public static void onEvent2(PlayerTickEvent event)
	{
		if(event.player!=null){
				if(KCore.instance.functionHelper.random.nextInt(400000)==0){
					if(month==11){
						BlockPos tmp = new BlockPos(event.player.posX-16+KCore.instance.functionHelper.random.nextInt(32),event.player.posY,event.player.posZ-16+KCore.instance.functionHelper.random.nextInt(32));
						BlockPos ground = event.player.world.getHeight(tmp);
						if(event.player.dimension==KCore.instance.DIMENSION_ID) {
							if (event.player.world.isAirBlock(ground) && event.player.world.isBlockFullCube(ground.down())) {
								event.player.world.setBlockState(ground, pickRandomPresentState(KCore.instance.functionHelper.random), 2);
							}
						}
					}

					}

		}
		if(event.player!=null) {
			if(!event.player.inventory.getStackInSlot(36).isEmpty()) {
				if(event.player.inventory.getStackInSlot(36).getItem().equals(KCore.CloudBoots)) {
					event.player.fallDistance=0;
					if(event.player.onGround) {
						((ItemMysticArmor)(event.player.inventory.getStackInSlot(36).getItem())).wereCloudBootsUsed=false;
					}
				}
			}
			if(event.player.world.isRemote) {
				KetherDataStorage data = KCore.data.getDataInstance(event.player.getEntityWorld());
				if (data != null) {
					if (data.getIsSandstorm()) {
						if (event.player.world.getBiome(event.player.getPosition()) == KCore.MysticDesert) {
							if (event.player.getRNG().nextInt(350) == 0) {
								event.player.playSound(KCore.instance.proxy.sandstorm, 1, 1);
							}
						}
					}
				}
			}
		}
	}


	
	
	/*@SubscribeEvent
	public static void onRenderPre(RenderGameOverlayEvent.Pre event) {

	}
	@SubscribeEvent
	public static void onRenderPost(RenderGameOverlayEvent.Post event){

	}*/


	@SubscribeEvent
	public static void blockBreakEvent(BlockEvent.HarvestDropsEvent event) {
		if (!event.getWorld().isRemote) {
			EntityPlayer breaker = event.getHarvester();
			if (breaker != null) {
				ItemStack heldStack = breaker.getHeldItemMainhand();
				Item heldItem = heldStack.getItem();
				if (heldItem.equals(KCore.Magnethium_Axe) || heldItem.equals(KCore.Magnethium_Pickaxe) || heldItem.equals(KCore.Magnethium_Shovel)) {
					event.setDropChance(0);

					double x = event.getPos().getX() + 0.5;
					double y = event.getPos().getY() + 0.5;
					double z = event.getPos().getZ() + 0.5;

					for (ItemStack itemStack : event.getDrops()) {
						EntityItem currentItem = new EntityItem(breaker.world, x, y, z, itemStack);

						if (heldItem.equals(KCore.Magnethium_Axe)) {
							currentItem.motionX = breaker.posX - currentItem.posX;
							currentItem.motionY = breaker.posY - currentItem.posY;
							currentItem.motionZ = breaker.posZ - currentItem.posZ;
						} else if (heldItem.equals(KCore.Magnethium_Pickaxe)) {
							currentItem.setNoGravity(true);
							currentItem.motionX = 0;
							currentItem.motionY = -0.01;
							currentItem.motionZ = 0;
						} else if (heldItem.equals(KCore.Magnethium_Shovel)) {
							currentItem.setNoGravity(true);
							currentItem.motionX = 0;
							currentItem.motionY = 0;
							currentItem.motionZ = 0;
						}
						breaker.world.spawnEntity(currentItem);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void healAnimalsWithHeartEvent(PlayerInteractEvent.EntityInteract event){
		ItemStack itemstack = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
			if(itemstack.getItem().equals(KCore.Heart)	){
				Entity entity = event.getTarget();
				if(entity instanceof EntityLivingBase){
					EntityLivingBase animal = (EntityLivingBase) entity;
					if(animal.getHealth()<animal.getMaxHealth()){
						if(!event.getEntityPlayer().world.isRemote) {
							animal.heal(1);
							if (!event.getEntityPlayer().capabilities.isCreativeMode) {
								itemstack.shrink(1);
							}
						}
						KCore.instance.functionHelper.playTameEffect(event.getEntityPlayer().world,KCore.instance.functionHelper.random,animal,true);
					}
				}
			}
	}


	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void handleDissolution(RenderGameOverlayEvent.Post event){
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.player!=null){
			if(mc.player.isPotionActive(KCore.dissolution_potion)){
				Tessellator tess = Tessellator.getInstance();
				BufferBuilder builder = tess.getBuffer();
                GlStateManager.pushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                mc.renderEngine.bindTexture(new ResourceLocation(KCore.MODID,"textures/effect/player/dissolution.png"));
				builder.begin(GL11.GL_QUADS,DefaultVertexFormats.POSITION_TEX);
				builder.pos(0,0,0).tex(0,0).endVertex();
				builder.pos(0,event.getResolution().getScaledHeight(),0).tex(0,1).endVertex();
				builder.pos(event.getResolution().getScaledWidth(),event.getResolution().getScaledHeight(),0).tex(1,1).endVertex();
				builder.pos(event.getResolution().getScaledWidth(),0,0).tex(1,0).endVertex();

				tess.draw();
                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_BLEND);
				GlStateManager.popMatrix();

			}
		}
		/*Minecraft event = Minecraft.getMinecraft();
		if(event.player!=null){
			if(event.player instanceof EntityLivingBase){
				EntityLivingBase elb = (EntityLivingBase) event.player;
				if(elb.isPotionActive(KCore.dissolution_potion)){
					if(elb.world.isRemote){
						//TODO render dissolution effect
						GlStateManager.pushMatrix();
						Tessellator tess = Tessellator.getInstance();
						BufferBuilder builder = tess.getBuffer();
						builder.begin(6,DefaultVertexFormats.POSITION_COLOR);
						builder.pos(0,0,0).color(255,255,255,255).endVertex();
						builder.pos(Minecraft.getMinecraft().displayWidth/4,20,0).color(255,255,255,255).endVertex();
						builder.pos(Minecraft.getMinecraft().displayWidth/2+Minecraft.getMinecraft().displayWidth/4,20,0).color(200,200,200,255).endVertex();
						builder.pos(Minecraft.getMinecraft().displayWidth,0,0).color(200,200,200,255).endVertex();
						tess.draw();
						GlStateManager.popMatrix();
					}
				}
			}
		}
					*/
	}


	static int ticker=0;
	@SubscribeEvent
	public static void handlePotionEffectsOnEntityLiving(LivingEvent.LivingUpdateEvent  event){
		if(event.getEntityLiving()!=null) {
            EntityLivingBase entityLivingBaseIn = event.getEntityLiving();
            if (entityLivingBaseIn.isPotionActive(KCore.stun_potion)) {
                entityLivingBaseIn.setJumping(false);
                entityLivingBaseIn.setPositionAndUpdate(entityLivingBaseIn.prevPosX, entityLivingBaseIn.prevPosY, entityLivingBaseIn.prevPosZ);
            }
            if (entityLivingBaseIn.isPotionActive(KCore.dissolution_potion)) {
                ticker++;
                if (ticker >= 40) {
                    ticker = 0;
                    if (entityLivingBaseIn instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
                        player.addExhaustion(10);
                        player.addPotionEffect(new PotionEffect(KCore.dissolution_potion, 1000, 1));
                    }
                }
            }
        }
	}

	@SubscribeEvent
            public static void handlePotionsAgain(LivingEntityUseItemEvent.Finish event){
            if(event.getItem().getItem() instanceof ItemFood){
                ItemFood food = (ItemFood) event.getItem().getItem();
                if(event.getEntityLiving().isPotionActive(KCore.dissolution_potion) && event.getEntityLiving() instanceof EntityPlayer){
                    EntityPlayer player = (EntityPlayer) event.getEntityLiving();
                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()-food.getHealAmount(event.getItem()));
                }
            }
        }

	@SubscribeEvent
	public static void denyMilkBucketWhileDissolution(LivingEntityUseItemEvent.Tick event){
		if(event.getItem().getItem().equals(Items.MILK_BUCKET)){
			if(event.getEntityLiving().isPotionActive(KCore.dissolution_potion)){
				event.setDuration(999999);
			}
		}
	}

	@SubscribeEvent
	public static void handleInteracts(PlayerInteractEvent.EntityInteractSpecific event){

	}




	/*@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void doButterflyCatcherHandling(){

	}*/



}
