package io.github.krevik.kathairis.world.dimension.feature.config;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

public class KatharianTreeFeatureConfig extends TreeFeatureConfig {
    public final FoliagePlacer field_227327_a_;
    public final int field_227328_b_;
    public final int field_227329_c_;
    public final int field_227330_d_;
    public final int field_227331_f_;
    public final int field_227332_g_;
    public final int field_227333_h_;
    public final int field_227334_i_;
    public final int field_227335_j_;
    public final int field_227336_k_;
    public final boolean field_227337_l_;

    protected KatharianTreeFeatureConfig(BlockStateProvider p_i225839_1_, BlockStateProvider p_i225839_2_, FoliagePlacer p_i225839_3_, List<TreeDecorator> p_i225839_4_, int p_i225839_5_, int p_i225839_6_, int p_i225839_7_, int p_i225839_8_, int p_i225839_9_, int p_i225839_10_, int p_i225839_11_, int p_i225839_12_, int p_i225839_13_, int p_i225839_14_, boolean p_i225839_15_) {
        super(p_i225839_1_,p_i225839_2_,p_i225839_3_,p_i225839_4_,p_i225839_5_,p_i225839_6_,p_i225839_7_,
                p_i225839_8_,p_i225839_9_,p_i225839_10_,p_i225839_11_,p_i225839_12_,p_i225839_13_,p_i225839_14_,
                p_i225839_15_);
        this.field_227327_a_ = p_i225839_3_;
        this.field_227328_b_ = p_i225839_6_;
        this.field_227329_c_ = p_i225839_7_;
        this.field_227330_d_ = p_i225839_8_;
        this.field_227331_f_ = p_i225839_9_;
        this.field_227332_g_ = p_i225839_10_;
        this.field_227333_h_ = p_i225839_11_;
        this.field_227334_i_ = p_i225839_12_;
        this.field_227335_j_ = p_i225839_13_;
        this.field_227336_k_ = p_i225839_14_;
        this.field_227337_l_ = p_i225839_15_;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(ops.createString("foliage_placer"), this.field_227327_a_.serialize(ops)).put(ops.createString("height_rand_a"), ops.createInt(this.field_227328_b_)).put(ops.createString("height_rand_b"), ops.createInt(this.field_227329_c_)).put(ops.createString("trunk_height"), ops.createInt(this.field_227330_d_)).put(ops.createString("trunk_height_random"), ops.createInt(this.field_227331_f_)).put(ops.createString("trunk_top_offset"), ops.createInt(this.field_227332_g_)).put(ops.createString("trunk_top_offset_random"), ops.createInt(this.field_227333_h_)).put(ops.createString("foliage_height"), ops.createInt(this.field_227334_i_)).put(ops.createString("foliage_height_random"), ops.createInt(this.field_227335_j_)).put(ops.createString("max_water_depth"), ops.createInt(this.field_227336_k_)).put(ops.createString("ignore_vines"), ops.createBoolean(this.field_227337_l_));
        Dynamic<T> dynamic = new Dynamic<>(ops, ops.createMap(builder.build()));
        return dynamic.merge(super.serialize(ops));
    }

    @Override
    public KatharianTreeFeatureConfig setSapling(net.minecraftforge.common.IPlantable value) {
        super.setSapling(value);
        return this;
    }

    public static <T> KatharianTreeFeatureConfig deserializeElderwillow(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.ELDERWILLOW_SAPLING);
    }

    public static <T> KatharianTreeFeatureConfig deserializeShiny(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.SHINY_SAPLING);
    }

    public static <T> KatharianTreeFeatureConfig deserializeSoul(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.SOUL_SAPLING);
    }

    public static <T> KatharianTreeFeatureConfig deserializeMystic(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING);
    }


    public static <T> KatharianTreeFeatureConfig func_227338_a_(Dynamic<T> p_227338_0_) {
        BaseTreeFeatureConfig basetreefeatureconfig = BaseTreeFeatureConfig.func_227376_b_(p_227338_0_);
        FoliagePlacerType<?> foliageplacertype = Registry.field_229389_v_.getOrDefault(new ResourceLocation(p_227338_0_.get("foliage_placer").get("type").asString().orElseThrow(RuntimeException::new)));
        return new KatharianTreeFeatureConfig(basetreefeatureconfig.field_227368_m_, basetreefeatureconfig.field_227369_n_, foliageplacertype.func_227391_a_(p_227338_0_.get("foliage_placer").orElseEmptyMap()), basetreefeatureconfig.field_227370_o_, basetreefeatureconfig.field_227371_p_, p_227338_0_.get("height_rand_a").asInt(0), p_227338_0_.get("height_rand_b").asInt(0), p_227338_0_.get("trunk_height").asInt(-1), p_227338_0_.get("trunk_height_random").asInt(0), p_227338_0_.get("trunk_top_offset").asInt(0), p_227338_0_.get("trunk_top_offset_random").asInt(0), p_227338_0_.get("foliage_height").asInt(-1), p_227338_0_.get("foliage_height_random").asInt(0), p_227338_0_.get("max_water_depth").asInt(0), p_227338_0_.get("ignore_vines").asBoolean(false));
    }

}