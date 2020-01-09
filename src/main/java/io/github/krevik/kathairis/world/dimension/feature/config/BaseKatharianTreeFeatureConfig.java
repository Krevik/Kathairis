package io.github.krevik.kathairis.world.dimension.feature.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.List;

public class BaseKatharianTreeFeatureConfig extends net.minecraft.world.gen.feature.BaseTreeFeatureConfig {
    public final BlockStateProvider leavesProvider;
    public final BlockStateProvider trunkProvider;
    public final List<TreeDecorator> decorator;
    public final int baseHeight;
    public transient boolean field_227372_q_;
    protected net.minecraftforge.common.IPlantable sapling = (net.minecraftforge.common.IPlantable)net.minecraft.block.Blocks.OAK_SAPLING;

    protected BaseKatharianTreeFeatureConfig(BlockStateProvider provider1, BlockStateProvider provider2, List<TreeDecorator> decorator, int someInt) {
        super(provider1,provider2,decorator,someInt);
        this.leavesProvider = provider1;
        this.trunkProvider = provider2;
        this.decorator = decorator;
        this.baseHeight = someInt;
    }

    public void func_227373_a_() {
        this.field_227372_q_ = true;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(ops.createString("trunk_provider"), this.leavesProvider.serialize(ops)).put(ops.createString("leaves_provider"), this.trunkProvider.serialize(ops)).put(ops.createString("decorators"), ops.createList(this.decorator.stream().map((p_227375_1_) -> {
            return p_227375_1_.serialize(ops);
        }))).put(ops.createString("base_height"), ops.createInt(this.baseHeight));
        return new Dynamic<>(ops, ops.createMap(builder.build()));
    }

    public BaseKatharianTreeFeatureConfig setSapling(net.minecraftforge.common.IPlantable value) {
        this.sapling = value;
        return this;
    }

    public net.minecraftforge.common.IPlantable getSapling() {
        return this.sapling;
    }

    public static <T> BaseKatharianTreeFeatureConfig func_227376_b_(Dynamic<T> p_227376_0_) {
        BlockStateProviderType<?> blockstateprovidertype = Registry.field_229387_t_.getOrDefault(new ResourceLocation(p_227376_0_.get("trunk_provider").get("type").asString().orElseThrow(RuntimeException::new)));
        BlockStateProviderType<?> blockstateprovidertype1 = Registry.field_229387_t_.getOrDefault(new ResourceLocation(p_227376_0_.get("leaves_provider").get("type").asString().orElseThrow(RuntimeException::new)));
        return new BaseKatharianTreeFeatureConfig(blockstateprovidertype.func_227399_a_(p_227376_0_.get("trunk_provider").orElseEmptyMap()), blockstateprovidertype1.func_227399_a_(p_227376_0_.get("leaves_provider").orElseEmptyMap()), p_227376_0_.get("decorators").asList((p_227374_0_) -> {
            return Registry.field_229390_w_.getOrDefault(new ResourceLocation(p_227374_0_.get("type").asString().orElseThrow(RuntimeException::new))).func_227431_a_(p_227374_0_);
        }), p_227376_0_.get("base_height").asInt(0));
    }

    public static <T> BaseKatharianTreeFeatureConfig deserializeElderwillow(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.ELDERWILLOW_SAPLING);
    }

    public static <T> BaseKatharianTreeFeatureConfig deserializeShiny(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.SHINY_SAPLING);
    }

    public static <T> BaseKatharianTreeFeatureConfig deserializeSoul(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.SOUL_SAPLING);
    }

    public static <T> BaseKatharianTreeFeatureConfig deserializeMystic(Dynamic<T> data) {
        return func_227376_b_(data).setSapling((net.minecraftforge.common.IPlantable) ModBlocks.MYSTIC_SAPLING);
    }

    public static class Builder {
        public final BlockStateProvider leavesProvider;
        public final BlockStateProvider trunkProvider;
        private List<TreeDecorator> decorators = Lists.newArrayList();
        private int baseHeight = 0;
        protected net.minecraftforge.common.IPlantable sapling = (net.minecraftforge.common.IPlantable)net.minecraft.block.Blocks.OAK_SAPLING;

        public Builder(BlockStateProvider p_i225843_1_, BlockStateProvider p_i225843_2_) {
            this.leavesProvider = p_i225843_1_;
            this.trunkProvider = p_i225843_2_;
        }

        public BaseKatharianTreeFeatureConfig.Builder func_225569_d_(int p_225569_1_) {
            this.baseHeight = p_225569_1_;
            return this;
        }

        public BaseKatharianTreeFeatureConfig.Builder setSapling(net.minecraftforge.common.IPlantable value) {
            this.sapling = value;
            return this;
        }

        public BaseKatharianTreeFeatureConfig func_225568_b_() {
            return new BaseKatharianTreeFeatureConfig(this.leavesProvider, this.trunkProvider, this.decorators, this.baseHeight).setSapling(sapling);
        }
    }
}