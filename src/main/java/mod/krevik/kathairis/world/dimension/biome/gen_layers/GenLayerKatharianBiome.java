package mod.krevik.kathairis.world.dimension.biome.gen_layers;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraftforge.common.BiomeManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class GenLayerKatharianBiome implements IC0Transformer {

	private final OverworldGenSettings settings;
	@SuppressWarnings("unchecked")
	private java.util.List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];
	public GenLayerKatharianBiome(OverworldGenSettings p_i48641_2_) {
		for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
			ImmutableList<BiomeManager.BiomeEntry> biomesToAdd = getBiomesToGeneration();
			int idx = type.ordinal();

			if (biomes[idx] == null) biomes[idx] = new ArrayList<BiomeManager.BiomeEntry>();
			if (biomesToAdd != null) biomes[idx].addAll(biomesToAdd);
		}

		//int desertIdx = net.minecraftforge.common.BiomeManager.BiomeType.DESERT.ordinal();

		//biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.DESERT, 30));
		//biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.SAVANNA, 20));
		//biomes[desertIdx].add(new net.minecraftforge.common.BiomeManager.BiomeEntry(Biomes.PLAINS, 10));

		this.settings = p_i48641_2_;

	}

	public static ImmutableList<BiomeManager.BiomeEntry> getBiomesToGeneration() {
		ArrayList<BiomeManager.BiomeEntry> list = new ArrayList<>();
//        list.add(new BiomeManager.BiomeEntry(Kathairis.BIOME_KATHARIAN_FOREST,10));
//        list.add(new BiomeManager.BiomeEntry(Kathairis.BIOME_KATHARIAN_DESERT,10));
//        list.add(new BiomeManager.BiomeEntry(Kathairis.BIOME_KATHARIAN_PLAIN_FIELDS,10));
//        list.add(new BiomeManager.BiomeEntry(Kathairis.BIOME_KATHARIAN_SWAMPS,10));

		return ImmutableList.copyOf(list);
	}

	public int apply(@Nonnull IContext context, int value) {
		if (this.settings != null && this.settings.func_202199_l() >= 0) {
			return this.settings.func_202199_l();
		} else {
			return IRegistry.BIOME.getId(getWeightedBiomeEntry(BiomeManager.BiomeType.COOL, context).biome);
		}
	}

	protected BiomeManager.BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type, IContext context) {
		java.util.List<BiomeManager.BiomeEntry> biomeList = getBiomesToGeneration();
		int totalWeight = net.minecraft.util.WeightedRandom.getTotalWeight(biomeList);
		int weight = BiomeManager.isTypeListModded(type) ? context.random(totalWeight) : context.random(totalWeight / 10) * 10;
		return (BiomeManager.BiomeEntry) net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
	}

}
