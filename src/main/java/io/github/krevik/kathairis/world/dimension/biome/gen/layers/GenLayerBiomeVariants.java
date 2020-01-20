package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

/**
 * @author Krevik
 */
public enum GenLayerBiomeVariants implements IC1Transformer {
    INSTANCE;

    private GenLayerBiomeVariants() {
    }

    BiomeVariantsUtil util = new BiomeVariantsUtil();
    @Override
    public int apply(INoiseRandom iNoiseRandom, int centerBiomeID) {
        if(util.getBiomesWithVariantsList().contains(Registry.BIOME.getByValue(centerBiomeID))){
            if(iNoiseRandom.random(4)==0){
                return Registry.BIOME.getId(util.getBiomeVariant(Registry.BIOME.getByValue(centerBiomeID)));
            }
        }
        return centerBiomeID;
    }
}
