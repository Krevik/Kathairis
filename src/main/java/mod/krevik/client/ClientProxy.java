package mod.krevik.client;

import mod.krevik.KCore;
import mod.krevik.client.particle.TextureStitcherParicleManager;
import mod.krevik.util.IProxy;
import mod.krevik.util.ModBlockColorsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy implements IProxy {

	@Override
	public String localizeAndFormat(final String unlocalized, final Object... args) {
		return I18n.format(unlocalized, args);
	}

	@Override
	public String localize(final String unlocalized) {
		return this.localizeAndFormat(unlocalized);
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


	@Override
	public void preInit(FMLPreInitializationEvent e) {
		IProxy.super.preInit(e);
		ketherMusicDay = EnumHelperClient.addMusicType("kether.day", KCore.proxy.ketherMusicDaySound, 1000, 10000);
		ketherMusicNight = EnumHelperClient.addMusicType("kether.night", KCore.proxy.ketherMusicNightSound, 1000, 10000);
		ketherMusicXmas = EnumHelperClient.addMusicType("kether.xmas", KCore.proxy.ketherMusicChristmasSound, 1000, 10000);
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

}