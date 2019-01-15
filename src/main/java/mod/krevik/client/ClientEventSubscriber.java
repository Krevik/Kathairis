package mod.krevik.client;

/**you can also import constants directly*/
import static mod.krevik.KCore.MODID;
import static net.minecraftforge.fml.relauncher.Side.CLIENT;

import mod.krevik.KCore;
import mod.krevik.client.renderer.tileentity.TileEntityMythicStoneSignRenderer;
import mod.krevik.tileentity.TileEntityMythicStoneSign;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.world.dimension.KetherDataStorage;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IRegistryDelegate;
import org.lwjgl.opengl.GL11;
import scala.collection.parallel.ParIterableLike;

import java.util.Map;

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

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain(KCore.MODID);
		KCore.initModels();
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		EntityAndRenderRegistry.registerRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMythicStoneSign.class, new TileEntityMythicStoneSignRenderer());
	}

	private static final Map<IRegistryDelegate<Block>, IStateMapper> stateMappers = ReflectionHelper.getPrivateValue(ModelLoader.class, null, "customStateMappers");
	private static final IStateMapper defaultStateMapper = new DefaultStateMapper();

	public static void registerToState(Block b, int itemMeta, IBlockState state) {
		ModelResourceLocation mrl = stateMappers.getOrDefault(state.getBlock().delegate, defaultStateMapper)
				.putStateModelLocations(state.getBlock())
				.get(state);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), itemMeta, mrl);
	}


	@SubscribeEvent
	public static void updateFogColors(EntityViewRenderEvent.FogColors event){
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.dimension==KCore.DIMENSION_ID){
			KetherDataStorage data = KCore.data.getDataInstance(player.world);
			if(data.getFogTime()>0){
				float f = 1 + MathHelper.abs(data.getLastFogTime() + 1 - (data.getFogTime()) / 2) / 20000;
				float r = event.getRed() / f;
				float g = event.getGreen() / f;
				float b = event.getBlue() / f;
				event.setRed(r);
				event.setGreen(g);
				event.setBlue(b);
			}


		}
	}

	@SubscribeEvent
	public static void handleFogGl(EntityViewRenderEvent.RenderFogEvent event){
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.dimension==KCore.DIMENSION_ID){
			KetherDataStorage data = KCore.data.getDataInstance(player.world);
			if(data.getFogTime()>0){
				float f = MathHelper.sin((float) ((data.getFogTime() * Math.PI) / (data.getLastFogTime())));
				GL11.glFogf(GL11.GL_FOG_START, 140f - 139.5f * f);
				GL11.glFogf(GL11.GL_FOG_END, 180f - 10f * f);
			}
		}
	}

	//CAUTION: CLIENT ONLY
	@SubscribeEvent
	public static void onWorldTickEvent(TickEvent.WorldTickEvent event){
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player!=null) {
			if (player.dimension == KCore.DIMENSION_ID) {
				KetherDataStorage data = KCore.data.getDataInstance(player.world);
				if (data.getFogTime() > -1) {
					data.setFogTime(data.getFogTime() - 1);
				}
			}
		}
	}

}