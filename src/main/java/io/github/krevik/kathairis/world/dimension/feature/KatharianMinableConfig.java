package io.github.krevik.kathairis.world.dimension.feature;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.function.Predicate;

import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE;

public class KatharianMinableConfig implements IFeatureConfig {

	public static final Predicate<IBlockState> IS_ROCK = (p_210462_0_) -> {
		if (p_210462_0_ == null) {
			return false;
		} else {
			Block lvt_1_1_ = p_210462_0_.getBlock();
			return lvt_1_1_ == Blocks.STONE || lvt_1_1_ == Blocks.GRANITE || lvt_1_1_ == Blocks.DIORITE || lvt_1_1_ == Blocks.ANDESITE ||
					lvt_1_1_ == KATHARIAN_STONE;
		}
	};
	public final Predicate<IBlockState> canReplace;
	public final int size;
	public final IBlockState state;

	public KatharianMinableConfig(Predicate<IBlockState> p_i48673_1_, IBlockState p_i48673_2_, int p_i48673_3_) {
		this.size = p_i48673_3_;
		this.state = p_i48673_2_;
		this.canReplace = p_i48673_1_;
	}

}
