package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

public enum GenLayerDesertBiomeVariants implements IC1Transformer {
    INSTANCE;

    private static final int KATHARIAN_DESERT_ID = IRegistry.BIOME.getId(ModBiomes.KATHARIAN_DESERT);
    private static final int SOFT_SAND_LAKES_ID = IRegistry.BIOME.getId(ModBiomes.SOFT_SAND_LAKES);

    private GenLayerDesertBiomeVariants() {
    }

    public int apply(IContext context, int centerBiomeID) {
        return context.random(4) == 0 && centerBiomeID == KATHARIAN_DESERT_ID ? getRandomVariantID() : centerBiomeID;
    }

    private int getRandomVariantID(){
        return SOFT_SAND_LAKES_ID;
    }
}
