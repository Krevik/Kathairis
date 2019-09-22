package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum GenLayerKatharianRiverMix implements IAreaTransformer2, IDimOffset0Transformer {
    INSTANCE;

    private static final int FROZEN_RIVER = Registry.BIOME.getId(Biomes.FROZEN_RIVER);
    private static final int SNOWY_TUNDRA = Registry.BIOME.getId(Biomes.SNOWY_TUNDRA);
    private static final int MUSHROOM_FIELDS = Registry.BIOME.getId(Biomes.MUSHROOM_FIELDS);
    private static final int MUSHROOM_FIELD_SHORE = Registry.BIOME.getId(Biomes.MUSHROOM_FIELD_SHORE);
    private static final int RIVER = Registry.BIOME.getId(ModBiomes.KATHAIRIS_RIVER);

    private GenLayerKatharianRiverMix() {
    }

    @Override
    public int apply(INoiseRandom iNoiseRandom, IArea area1, IArea area2, int pos1, int pos2) {
        int lvt_7_1_ = area1.getValue(pos1, pos2);
        int lvt_8_1_ = area2.getValue(pos1, pos2);
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
