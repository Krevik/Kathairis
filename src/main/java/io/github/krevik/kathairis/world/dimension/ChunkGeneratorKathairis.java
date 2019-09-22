package io.github.krevik.kathairis.world.dimension;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

/**
 * @author Krevik
 */
public class ChunkGeneratorKathairis extends OverworldChunkGenerator{

	public ChunkGeneratorKathairis(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
		super(worldIn, provider, settingsIn);
	}
}
