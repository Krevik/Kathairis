package io.github.krevik.kathairis.world.dimension.structures.crystal_labirynth;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class CrystalLabirynthConfig implements IFeatureConfig {
    public final ResourceLocation startPool;
    public final int size;

    public CrystalLabirynthConfig(String startPool, int size) {
        this.startPool = new ResourceLocation(startPool);
        this.size = size;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("start_pool"), ops.createString(this.startPool.toString()), ops.createString("size"), ops.createInt(this.size))));
    }

    public static <T> CrystalLabirynthConfig deserialize(Dynamic<T> p_214679_0_) {
        String s = p_214679_0_.get("start_pool").asString("");
        int i = p_214679_0_.get("size").asInt(6);
        return new CrystalLabirynthConfig(s, i);
    }
}