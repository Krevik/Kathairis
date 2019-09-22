package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum GenLayerKatharianRiver implements ICastleTransformer {
    INSTANCE;

    public static final int RIVER = Registry.BIOME.getId(ModBiomes.KATHAIRIS_RIVER);
    private GenLayerKatharianRiver() {

    }

    private static int riverFilter(int p_151630_0_) {
        return p_151630_0_ >= 2 ? 2 + (p_151630_0_ & 1) : p_151630_0_;
    }

    @Override
    public int apply(INoiseRandom iNoiseRandom, int p_202748_2_, int p_202748_3_, int p_202748_4_, int p_202748_5_, int p_202748_6_) {
        int lvt_7_1_ = riverFilter(p_202748_6_);
        return lvt_7_1_ == riverFilter(p_202748_5_) && lvt_7_1_ == riverFilter(p_202748_2_) && lvt_7_1_ == riverFilter(p_202748_3_) && lvt_7_1_ == riverFilter(p_202748_4_) ? -1 : RIVER;

    }
}
