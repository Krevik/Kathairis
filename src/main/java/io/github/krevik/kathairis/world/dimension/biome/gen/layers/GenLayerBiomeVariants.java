package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

/**
 * @author Krevik
 */
public enum GenLayerBiomeVariants implements IC1Transformer {
    INSTANCE;

    private GenLayerBiomeVariants() {
    }

    BiomeVariantsUtil util = new BiomeVariantsUtil();
    public int apply(IContext context, int centerBiomeID) {
        if(util.getBiomesWithVariantsList().contains(IRegistry.BIOME.get(centerBiomeID))){
            if(context.random(4)==0){
                return IRegistry.BIOME.getId(util.getBiomeVariant(IRegistry.BIOME.get(centerBiomeID)));
            }
        }
        return centerBiomeID;
    }

}
