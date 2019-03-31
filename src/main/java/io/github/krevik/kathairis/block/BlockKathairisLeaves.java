package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.MYSTIC_LEAVES;
import static io.github.krevik.kathairis.init.ModBlocks.MYSTIC_LOG;
import static io.github.krevik.kathairis.init.ModBlocks.SHINY_LEAVES;
import static io.github.krevik.kathairis.init.ModBlocks.SHINY_LOG;
import static io.github.krevik.kathairis.init.ModBlocks.SOUL_LEAVES;
import static io.github.krevik.kathairis.init.ModBlocks.SOUL_LOG;

/**
 * @author Krevik
 */
public class BlockKathairisLeaves extends BlockLeaves {

	public BlockKathairisLeaves() {
		super(Properties.create(Material.PLANTS).hardnessAndResistance(0.2f).sound(SoundType.PLANT).tickRandomly());
		this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, Integer.valueOf(7)).with(PERSISTENT, Boolean.valueOf(false)));
	}

	private static IBlockState updateDistance(IBlockState p_208493_0_, IWorld p_208493_1_, BlockPos p_208493_2_) {
		int i = 7;

		try (BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain()) {
			for (EnumFacing enumfacing : EnumFacing.values()) {
				blockpos$pooledmutableblockpos.setPos(p_208493_2_).move(enumfacing);
				i = Math.min(i, getDistanceNew(p_208493_1_.getBlockState(blockpos$pooledmutableblockpos)) + 1);
				if (i == 1) {
					break;
				}
			}
		}

		return p_208493_0_.with(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistanceNew(IBlockState neighbor) {
		if (isLog(neighbor.getBlock())) {
			return 0;
		} else {
			return isLeaves(neighbor.getBlock()) ? neighbor.get(DISTANCE) : 7;
		}
	}

	static boolean isLeaves(Block block) {
		return block instanceof BlockKathairisLeaves || block instanceof BlockLeaves || block == MYSTIC_LEAVES || block == SOUL_LEAVES ||
				block == SHINY_LEAVES;
	}

	static boolean isLog(Block block) {
		return block instanceof BlockLog || block instanceof BlockKathairisLog || block == MYSTIC_LOG || block == SOUL_LOG ||
				block == SHINY_LOG;
	}

	@Override
	public boolean ticksRandomly(IBlockState p_149653_1_) {
		return p_149653_1_.get(DISTANCE) == 7 && !p_149653_1_.get(PERSISTENT);
	}

	@Override
	public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		if (!state.get(PERSISTENT) && state.get(DISTANCE) == 7) {
			worldIn.removeBlock(pos);
		}

	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		int i = getDistanceNew(facingState) + 1;
		if (i != 1 || stateIn.get(DISTANCE) != i) {
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		return stateIn;
	}

	@Override
	public IItemProvider getItemDropped(IBlockState p_199769_1_, World p_199769_2_, BlockPos p_199769_3_, int p_199769_4_) {
		return new ItemStack(this).getItem();
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		return updateDistance(this.getDefaultState().with(PERSISTENT, Boolean.valueOf(true)), context.getWorld(), context.getPos());
	}

}
