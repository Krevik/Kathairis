package mod.krevik.client;

/**you can also import constants directly*/
import static mod.krevik.KCore.MODID;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;

import mod.krevik.KCore;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = MODID, value = CLIENT)
public final class ClientEventSubscriber {

//	private static final String DEFAULT_VARIANT = "normal";
//
//	@SubscribeEvent
//	public static void onRegisterModelsEvent(final ModelRegistryEvent event) {
//
//		registerTileEntitySpecialRenderers();
//		KCore.LOGGER.info("Registered tile entity special renderers");
//
//		registerEntityRenderers();
//		KCore.LOGGER.info("Registered entity renderers");
//
//		/* item blocks */
//		registerItemBlockModel(ModBlocks.);
//
//		/* items */
//		registerItemModel(ModItems.);
//
//		KCore.LOGGER.info("Registered models");
//
//	}
//
//	private static void registerTileEntitySpecialRenderers() {
////		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity___.class, new TileEntity___Renderer());
//	}
//
//	private static void registerEntityRenderers() {
////		RenderingRegistry.registerEntityRenderingHandler(Entity___.class, renderManager -> new Entity___Renderer(renderManager));
//	}
//
//	private static void registerItemModel(final Item item) {
//		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), DEFAULT_VARIANT));
//	}
//
//	private static void registerItemBlockModel(final Block block) {
//		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), DEFAULT_VARIANT));
//	}

	@SubscribeEvent
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
				builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
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

}