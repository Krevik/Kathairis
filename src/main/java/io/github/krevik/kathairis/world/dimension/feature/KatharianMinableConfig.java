package io.github.krevik.kathairis.world.dimension.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KatharianMinableConfig implements IFeatureConfig {
    public final FillerBlockType target;
    public final int size;
    public final BlockState state;

    public KatharianMinableConfig(FillerBlockType p_i51473_1_, BlockState p_i51473_2_, int p_i51473_3_) {
        this.size = p_i51473_3_;
        this.state = p_i51473_2_;
        this.target = p_i51473_1_;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> p_214634_1_) {
        return new Dynamic(p_214634_1_, p_214634_1_.createMap(ImmutableMap.of(p_214634_1_.createString("size"), p_214634_1_.createInt(this.size), p_214634_1_.createString("target"), p_214634_1_.createString(this.target.func_214737_a()), p_214634_1_.createString("state"), BlockState.serialize(p_214634_1_, this.state).getValue())));
    }

    public static KatharianMinableConfig deserialize(Dynamic<?> p_214641_0_) {
        int lvt_1_1_ = p_214641_0_.get("size").asInt(0);
        FillerBlockType lvt_2_1_ = FillerBlockType.func_214736_a(p_214641_0_.get("target").asString(""));
        BlockState lvt_3_1_ = (BlockState)p_214641_0_.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new KatharianMinableConfig(lvt_2_1_, lvt_3_1_, lvt_1_1_);
    }

    public static enum FillerBlockType {
        NATURAL_STONE("natural_stone", (p_214739_0_) -> {
            if (p_214739_0_ == null) {
                return false;
            } else {
                Block lvt_1_1_ = p_214739_0_.getBlock();
                return lvt_1_1_ == Blocks.STONE || lvt_1_1_ == Blocks.GRANITE || lvt_1_1_ == Blocks.DIORITE
                        || lvt_1_1_ == Blocks.ANDESITE || lvt_1_1_ == ModBlocks.KATHAIRIS_STONE;
            }
        });

        private static final Map<String, FillerBlockType> field_214741_c = (Map)Arrays.stream(values()).collect(Collectors.toMap(FillerBlockType::func_214737_a, (p_214740_0_) -> {
            return p_214740_0_;
        }));
        private final String field_214742_d;
        private final Predicate<BlockState> field_214743_e;

        private FillerBlockType(String p_i50618_3_, Predicate<BlockState> p_i50618_4_) {
            this.field_214742_d = p_i50618_3_;
            this.field_214743_e = p_i50618_4_;
        }

        public String func_214737_a() {
            return this.field_214742_d;
        }

        public static FillerBlockType func_214736_a(String p_214736_0_) {
            return (FillerBlockType)field_214741_c.get(p_214736_0_);
        }

        public Predicate<BlockState> func_214738_b() {
            return this.field_214743_e;
        }
    }
}
