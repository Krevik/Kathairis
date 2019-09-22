package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraftforge.common.BiomeManager;

import java.util.ArrayList;

public class GenLayerKatharianBiome implements IC0Transformer {


    public static ImmutableList<BiomeManager.BiomeEntry> getBiomesToGeneration(){
        ArrayList<BiomeManager.BiomeEntry> list = new ArrayList<>();
        list.add(new BiomeManager.BiomeEntry(ModBiomes.KATHARIAN_FOREST,10));
        list.add(new BiomeManager.BiomeEntry(ModBiomes.KATHARIAN_DESERT,10));
        list.add(new BiomeManager.BiomeEntry(ModBiomes.PLAIN_FIELDS,10));
        list.add(new BiomeManager.BiomeEntry(ModBiomes.KATHARIAN_SWAMP,10));
        return ImmutableList.copyOf(list);
    }


    @SuppressWarnings("unchecked")
    private java.util.List<BiomeManager.BiomeEntry>[] biomes = new ArrayList[BiomeManager.BiomeType.values().length];
    private final OverworldGenSettings settings;

    public GenLayerKatharianBiome(OverworldGenSettings p_i48641_2_) {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values()) {
            ImmutableList<BiomeManager.BiomeEntry> biomesToAdd = getBiomesToGeneration();
            int idx = type.ordinal();

            if (biomes[idx] == null) biomes[idx] = new ArrayList<BiomeManager.BiomeEntry>();
            if (biomesToAdd != null) biomes[idx].addAll(biomesToAdd);
        }

            this.settings = p_i48641_2_;

    }


    protected BiomeManager.BiomeEntry getWeightedBiomeEntry(BiomeManager.BiomeType type, INoiseRandom iNoiseRandom) {
        java.util.List<BiomeManager.BiomeEntry> biomeList = getBiomesToGeneration();
        int totalWeight = net.minecraft.util.WeightedRandom.getTotalWeight(biomeList);
        int weight = BiomeManager.isTypeListModded(type)?iNoiseRandom.random(totalWeight):iNoiseRandom.random(totalWeight / 10) * 10;
        return (BiomeManager.BiomeEntry)net.minecraft.util.WeightedRandom.getRandomItem(biomeList, weight);
    }

    @Override
    public int apply(INoiseRandom iNoiseRandom, int i) {
        if (this.settings != null && this.settings.getBiomeId() >= 0) {
            return this.settings.getBiomeId();
        } else {
            return Registry.BIOME.getId(getWeightedBiomeEntry(BiomeManager.BiomeType.COOL, iNoiseRandom).biome);
        }
    }
}