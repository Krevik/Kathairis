package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum GenLayerKatharianBiomeEdge implements ICastleTransformer {
    INSTANCE;

    @Override
    public int apply(INoiseRandom iNoiseRandom, int biome1ID, int biome2ID, int biome3ID, int biome4ID, int biomeCenter) {
        if(KatharianLayerUtil.isKatharianDesertBiome(biomeCenter)){
            if(!KatharianLayerUtil.isKatharianDesertBiome(biome1ID)||!KatharianLayerUtil.isKatharianDesertBiome(biome2ID)||!KatharianLayerUtil.isKatharianDesertBiome(biome3ID)||
                    !KatharianLayerUtil.isKatharianDesertBiome(biome4ID)){
                return Registry.BIOME.getId(ModBiomes.KATHARIAN_DESERT_EDGE);
            }
        }
        return biomeCenter;
    }
}