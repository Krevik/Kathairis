package mod.krevik.biome;

import net.minecraft.world.gen.layer.*;

public abstract class GenLayerMysticMain extends GenLayer
{
	public GenLayerMysticMain(long seed) {
		super(seed);
	}
	
	public static GenLayer[] makeTheWorld(long seed) {

		GenLayer biomes = new GenLayerMystic(1L);

		biomes = new GenLayerZoom(1000L, biomes);
		biomes = new GenLayerZoom(1001L, biomes);
		biomes = new GenLayerZoom(1002L, biomes);
		biomes = new GenLayerZoom(1003L, biomes);
		biomes = new GenLayerZoom(1004L, biomes);


		biomes = new GenLayerSmooth(1007L, biomes);
		

		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);

		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[] {biomes, genlayervoronoizoom};
	}
}