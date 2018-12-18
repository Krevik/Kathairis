package mod.krevik.client;

import mod.krevik.KCore;
import mod.krevik.client.particle.TextureStitcherParicleManager;
import mod.krevik.tileentity.TileEntityMythicStoneSign;
import mod.krevik.client.renderer.tileentity.TileEntityMythicStoneSignRenderer;
import mod.krevik.util.EntityAndRenderRegistry;
import mod.krevik.util.IProxy;
import mod.krevik.util.ModBlockColorsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IRegistryDelegate;

import java.util.Map;

public class ClientProxy implements IProxy {

	@Override
	public String localizeAndFormat(final String unlocalized, final Object... args) {
		return I18n.format(unlocalized, args);
	}

	@Override
	public String localize(final String unlocalized) {
		return this.localizeAndFormat(unlocalized, new Object[0]);
	}

	@Override
	public Side getPhysicalSide() {
		return Side.CLIENT;
	}

	public static final ResourceLocation particleBlueCloud = new ResourceLocation(KCore.MODID + ":" + "effect/bluecloud");
	public static final ResourceLocation shockingBall = new ResourceLocation(KCore.MODID + ":" + "effect/shockingball");
	public static final ResourceLocation shockingParticle = new ResourceLocation(KCore.MODID + ":" + "effect/shockingparticle");
	public static final ResourceLocation Mystic_Gem_Block_Particle = new ResourceLocation(KCore.MODID + ":" + "effect/mystic_gem_block_particle");
	public static final ResourceLocation living_particle = new ResourceLocation(KCore.MODID + ":" + "effect/living_particle");

	public static final ResourceLocation stun_potion = new ResourceLocation(KCore.MODID + ":" + "textures/effect/player/stun_potion.png");
	public static final ResourceLocation dissolution_potion = new ResourceLocation(KCore.MODID + ":" + "textures/effect/player/dissolution_potion.png");

	public static MusicTicker.MusicType ketherMusicDay;
	public static MusicTicker.MusicType ketherMusicNight;
	public static MusicTicker.MusicType ketherMusicXmas;

	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(KCore.MODID + ":" + id, "inventory"));
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		OBJLoader.INSTANCE.addDomain(KCore.instance.MODID);
		KCore.initModels();
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		EntityAndRenderRegistry.registerRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMythicStoneSign.class, new TileEntityMythicStoneSignRenderer());
	}

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		IProxy.super.preInit(e);
		ketherMusicDay = EnumHelperClient.addMusicType("kether.day", KCore.instance.proxy.ketherMusicDaySound, 1000, 10000);
		ketherMusicNight = EnumHelperClient.addMusicType("kether.night", KCore.instance.proxy.ketherMusicNightSound, 1000, 10000);
		ketherMusicXmas = EnumHelperClient.addMusicType("kether.xmas", KCore.instance.proxy.ketherMusicChristmasSound, 1000, 10000);
		;
		MinecraftForge.EVENT_BUS.register(new TextureStitcherParicleManager());
	}

	@Override
	public void init(FMLInitializationEvent e) {
		IProxy.super.init(e);
		ModBlockColorsHandler.registerBlockColors();
	}

	public static void drawParticle(World worldObj, Particle particle) {
		if (particle != null)
			Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}

	private static final Map<IRegistryDelegate<Block>, IStateMapper> stateMappers = ReflectionHelper.getPrivateValue(ModelLoader.class, null, "customStateMappers");
	private static final IStateMapper defaultStateMapper = new DefaultStateMapper();

	public static void registerToState(Block b, int itemMeta, IBlockState state) {
		ModelResourceLocation mrl = stateMappers.getOrDefault(state.getBlock().delegate, defaultStateMapper)
				.putStateModelLocations(state.getBlock())
				.get(state);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), itemMeta, mrl);
	}

}