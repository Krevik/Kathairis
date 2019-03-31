package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.area.AreaDimension;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum GenLayerKatharianRiverMix implements IAreaTransformer2, IDimOffset0Transformer {
    INSTANCE;

    private static final int FROZEN_RIVER = IRegistry.BIOME.getId(Biomes.FROZEN_RIVER);
    private static final int SNOWY_TUNDRA = IRegistry.BIOME.getId(Biomes.SNOWY_TUNDRA);
    private static final int MUSHROOM_FIELDS = IRegistry.BIOME.getId(Biomes.MUSHROOM_FIELDS);
    private static final int MUSHROOM_FIELD_SHORE = IRegistry.BIOME.getId(Biomes.MUSHROOM_FIELD_SHORE);
    private static final int RIVER = IRegistry.BIOME.getId(ModBiomes.KATHAIRIS_RIVER);

    private GenLayerKatharianRiverMix() {
    }

    public int apply(IContext p_202709_1_, AreaDimension p_202709_2_, IArea p_202709_3_, IArea p_202709_4_, int p_202709_5_, int p_202709_6_) {
        int lvt_7_1_ = p_202709_3_.getValue(p_202709_5_, p_202709_6_);
        int lvt_8_1_ = p_202709_4_.getValue(p_202709_5_, p_202709_6_);
        if (KatharianLayerUtil.isOcean(lvt_7_1_)) {
            return lvt_7_1_;
        } else if (lvt_8_1_ == RIVER) {
            if (lvt_7_1_ == SNOWY_TUNDRA) {
                return FROZEN_RIVER;
            } else {
                return lvt_7_1_ != MUSHROOM_FIELDS && lvt_7_1_ != MUSHROOM_FIELD_SHORE ? lvt_8_1_ & 255 : MUSHROOM_FIELD_SHORE;
            }
        } else {
            return lvt_7_1_;
        }
    }
}
