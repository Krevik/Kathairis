//package io.github.krevik.kathairis.world.dimension;
//
//import io.github.krevik.kathairis.Kathairis;
//import io.github.krevik.kathairis.world.dimension.biome.KathairisBiomeProvider;
//import io.github.krevik.kathairis.world.dimension.biome.KathairisBiomeProviderSettings;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.audio.MusicTicker;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.world.WorldType;
//import net.minecraft.world.biome.provider.BiomeProvider;
//import net.minecraft.world.biome.provider.BiomeProviderType;
//import net.minecraft.world.dimension.DimensionType;
//import net.minecraft.world.dimension.OverworldDimension;
//import net.minecraft.world.gen.ChunkGeneratorOverworld;
//import net.minecraft.world.gen.ChunkGeneratorType;
//import net.minecraft.world.gen.IChunkGenSettings;
//import net.minecraft.world.gen.IChunkGenerator;
//import net.minecraft.world.gen.OverworldGenSettings;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.client.IRenderHandler;
//import net.minecraftforge.common.DimensionManager;
//
//import javax.annotation.Nullable;
//
//import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE;
//
///**
// * @author Krevik
// */
//public class DimensionKathairis extends OverworldDimension {
//
//	public DimensionKathairis() {
//		super(DimensionManager.getRegistry().get(Kathairis.kath_DIM_ID));
//	}
//
//	@Override
//	public DimensionType getType() {
//		return DimensionManager.getRegistry().get(Kathairis.kath_DIM_ID);
//	}
//
//	public IChunkGenerator<? extends IChunkGenSettings> createChunkGenerator() {
//		WorldType worldtype = this.world.getWorldInfo().getGenerator();
//		BiomeProviderType<KathairisBiomeProviderSettings, KathairisBiomeProvider> biomeprovidertype1 = Kathairis.KATHARIAN_BIOME_PROVIDER_TYPE;
//
//		KathairisBiomeProviderSettings overworldbiomeprovidersettings1 = biomeprovidertype1.createSettings().setGeneratorSettings(new KathairisGenSettings()).setWorldInfo(this.world.getWorldInfo());
//		BiomeProvider biomeprovider = biomeprovidertype1.create(overworldbiomeprovidersettings1);
//
//		ChunkGeneratorType<OverworldGenSettings, ChunkGeneratorOverworld> chunkgeneratortype4 = ChunkGeneratorType.SURFACE;
//		OverworldGenSettings overworldgensettings1 = chunkgeneratortype4.createSettings();
//		overworldgensettings1.setDefautBlock(KATHARIAN_STONE.getDefaultState());
//		overworldgensettings1.setDefaultFluid(Blocks.WATER.getDefaultState());
//		return chunkgeneratortype4.create(this.world, biomeprovider, overworldgensettings1);
//	}
//
//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public Vec3d getFogColor(float float1, float float2) {
//		EntityPlayer player = Minecraft.getInstance().player;
//		if (player.world.getBiome(player.getPosition()) == Kathairis.BIOME_KATHARIAN_SWAMPS) {
//			int R = 12;
//			int G = 45;
//			int B = 7;
//			return new Vec3d(R / 45, G / 45, B / 45);
//		} else {
//
//			return super.getFogColor(float1, float2);
//		}
//	}
//
//	@Override
//	public float getCloudHeight() {
//		return -1000;
//	}
//
//	@Nullable
//	@Override
//	public IRenderHandler getCloudRenderer() {
//		return null;
//	}
//
//	@Nullable
//	@Override
//	public MusicTicker.MusicType getMusicType() {
//		return null;
//	}
//
//	@Override
//	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
////		int r = 200;
////		int g = 190;
////		int b = 40;
////		return new Vec3d(r/100,g/100,b/100);
//		return super.getSkyColor(cameraEntity, partialTicks);
//	}
//
//}
