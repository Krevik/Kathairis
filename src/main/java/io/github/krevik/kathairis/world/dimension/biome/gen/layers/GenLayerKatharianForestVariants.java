package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

public enum GenLayerKatharianForestVariants implements IC1Transformer {
    INSTANCE;

    private static final int KATHARIAN_FOREST_ID = IRegistry.BIOME.getId(ModBiomes.KATHARIAN_FOREST);
    private static final int KATHARIAN_DENSE_FOREST_ID = IRegistry.BIOME.getId(ModBiomes.KATHARIAN_DENSE_FOREST);

    private GenLayerKatharianForestVariants() {
    }

    public int apply(IContext context, int centerBiomeID) {
        return context.random(4) == 0 && centerBiomeID == KATHARIAN_FOREST_ID ? getRandomVariantID() : centerBiomeID;
    }

    private int getRandomVariantID(){
        return KATHARIAN_DENSE_FOREST_ID;
    }
}