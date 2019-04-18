package io.github.krevik.kathairis.world.dimension.biome.gen.layers;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.init.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContextExtended;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

import java.util.function.LongFunction;

public class KatharianLayerUtil {

    protected static final int WARM_OCEAN = IRegistry.BIOME.getId(Biomes.WARM_OCEAN);
    protected static final int LUKEWARM_OCEAN = IRegistry.BIOME.getId(Biomes.LUKEWARM_OCEAN);
    protected static final int OCEAN = IRegistry.BIOME.getId(Biomes.OCEAN);
    protected static final int COLD_OCEAN = IRegistry.BIOME.getId(Biomes.COLD_OCEAN);
    protected static final int FROZEN_OCEAN = IRegistry.BIOME.getId(Biomes.FROZEN_OCEAN);
    protected static final int DEEP_WARM_OCEAN = IRegistry.BIOME.getId(Biomes.DEEP_WARM_OCEAN);
    protected static final int DEEP_LUKEWARM_OCEAN = IRegistry.BIOME.getId(Biomes.DEEP_LUKEWARM_OCEAN);
    protected static final int DEEP_OCEAN = IRegistry.BIOME.getId(Biomes.DEEP_OCEAN);
    protected static final int DEEP_COLD_OCEAN = IRegistry.BIOME.getId(Biomes.DEEP_COLD_OCEAN);
    protected static final int DEEP_FROZEN_OCEAN = IRegistry.BIOME.getId(Biomes.DEEP_FROZEN_OCEAN);

    public static <T extends IArea, C extends IContextExtended<T>> IAreaFactory<T> repeat(long seed, IAreaTransformer1 parent, IAreaFactory<T> p_202829_3_, int count, LongFunction<C> contextFactory) {
        IAreaFactory<T> iareafactory = p_202829_3_;

        for(int i = 0; i < count; ++i) {
            iareafactory = parent.apply(contextFactory.apply(seed + (long)i), iareafactory);
        }

        return iareafactory;
    }


    public static <T extends IArea, C extends IContextExtended<T>> ImmutableList<IAreaFactory<T>> buildOverworldProcedure(WorldType worldTypeIn, OverworldGenSettings settings, LongFunction<C> contextFactory) {
        IAreaFactory<T> iareafactory = GenLayerIsland.INSTANCE.apply(contextFactory.apply(1L));
        iareafactory = GenLayerZoom.FUZZY.apply(contextFactory.apply(2000L), iareafactory);
        iareafactory = GenLayerZoom.NORMAL.apply(contextFactory.apply(2001L), iareafactory);
        iareafactory = GenLayerRemoveTooMuchOcean.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
        iareafactory = GenLayerZoom.NORMAL.apply(contextFactory.apply(2002L), iareafactory);
        iareafactory = GenLayerDeepOcean.INSTANCE.apply(contextFactory.apply(4L), iareafactory);
        //iareafactory = GenLayerEdge.HeatIce.INSTANCE.apply(contextFactory.apply(2L), iareafactory);
        iareafactory = repeat(1000L, GenLayerZoom.NORMAL, iareafactory, 0, contextFactory);

        int i = 8;
        int j = i;
        if (settings != null) {
            j = settings.getRiverSize();
        }

        i = 3;

        IAreaFactory<T> lvt_7_1_ = repeat(1000L, GenLayerZoom.NORMAL, iareafactory, 0, contextFactory);
        lvt_7_1_ = GenLayerRiverInit.INSTANCE.apply((IContextExtended)contextFactory.apply(100L), lvt_7_1_);
        IAreaFactory<T> lvt_8_1_ = getBiomeLayer(iareafactory, settings, contextFactory);
        lvt_7_1_ = repeat(1000L, GenLayerZoom.NORMAL, lvt_7_1_, 2, contextFactory);
        lvt_7_1_ = repeat(1000L, GenLayerZoom.NORMAL, lvt_7_1_, j, contextFactory);
        lvt_7_1_ = GenLayerKatharianRiver.INSTANCE.apply((IContextExtended)contextFactory.apply(1L), lvt_7_1_);
        lvt_7_1_ = GenLayerSmooth.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_7_1_);
        lvt_8_1_ = GenLayerBiomeVariants.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_8_1_);

        for(int k = 0; k < i; ++k) {
            lvt_8_1_ = GenLayerZoom.NORMAL.apply((IContextExtended)contextFactory.apply((long)(1000 + k)), lvt_8_1_);

            if (k == 1 || i == 1) {
                lvt_8_1_ = GenLayerKatharianBiomeEdge.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_8_1_);
                lvt_8_1_ = GenLayerZoom.NORMAL.apply((IContextExtended)contextFactory.apply((long)(1000L)), lvt_8_1_);
            }
        }

        lvt_8_1_ = GenLayerSmooth.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_8_1_);
        lvt_8_1_ = GenLayerKatharianRiverMix.INSTANCE.apply((IContextExtended)contextFactory.apply(100L), lvt_8_1_, lvt_7_1_);
        lvt_8_1_ = GenLayerDeleteRiverNearDesert.INSTANCE.apply((IContextExtended)contextFactory.apply(100L), lvt_8_1_);
        IAreaFactory<T> iareafactory5 = GenLayerVoronoiZoom.INSTANCE.apply(contextFactory.apply(10L), lvt_8_1_);
        return ImmutableList.of(lvt_8_1_, iareafactory5, lvt_8_1_);
    }


   private static <T extends IArea, C extends IContextExtended<T>> IAreaFactory<T> getBiomeLayer(IAreaFactory<T> parentLayer,
                                                                                                 OverworldGenSettings chunkSettings, LongFunction<C> contextFactory)
    {
        parentLayer = (new GenLayerKatharianBiome(chunkSettings)).apply((IContextExtended) contextFactory.apply(200L), parentLayer);
        parentLayer = LayerUtil.repeat(1000L, GenLayerZoom.NORMAL, parentLayer, 2, contextFactory);
        parentLayer = GenLayerBiomeEdge.INSTANCE.apply((IContextExtended) contextFactory.apply(1000L), parentLayer);
        return parentLayer;
    }

    public static GenLayer[] buildOverworldProcedure(long seed, WorldType typeIn, OverworldGenSettings settings) {
        int[] aint = new int[1];
        ImmutableList<IAreaFactory<LazyArea>> immutablelist = buildOverworldProcedure(typeIn, settings, (p_202825_3_) -> {
            ++aint[0];
            return new LazyAreaLayerContext(1, aint[0], seed, p_202825_3_);
        });
        GenLayer genlayer = new GenLayer(immutablelist.get(0));
        GenLayer genlayer1 = new GenLayer(immutablelist.get(1));
        GenLayer genlayer2 = new GenLayer(immutablelist.get(2));
        return new GenLayer[]{genlayer, genlayer1, genlayer2};
    }

    public static boolean isOcean(int biomeIn) {
        return biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN || biomeIn == DEEP_WARM_OCEAN || biomeIn == DEEP_LUKEWARM_OCEAN || biomeIn == DEEP_OCEAN || biomeIn == DEEP_COLD_OCEAN || biomeIn == DEEP_FROZEN_OCEAN;
    }

    public static boolean isKatharianDesertBiome(int biomeID){
        Biome biome = IRegistry.BIOME.get(biomeID);
        return biome== ModBiomes.KATHARIAN_DESERT_EDGE||biome==ModBiomes.SOFT_SAND_LAKES||biome==ModBiomes.KATHARIAN_DESERT;
    }


}