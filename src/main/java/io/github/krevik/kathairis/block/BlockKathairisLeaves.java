package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class BlockKathairisLeaves extends LeavesBlock implements IItemGroupProvider {

	boolean renderTranslucent;
	public BlockKathairisLeaves() {
		super(Properties.create(Material.PLANTS).hardnessAndResistance(0.2f).sound(SoundType.PLANT).tickRandomly());
		this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, Integer.valueOf(7)).with(PERSISTENT, Boolean.valueOf(false)));
		renderTranslucent=true;
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}


	private static BlockState updateDistance(BlockState p_208493_0_, IWorld p_208493_1_, BlockPos p_208493_2_) {
		int i = 7;

		try (BlockPos.PooledMutable blockpos$pooledmutableblockpos = BlockPos.PooledMutable.retain()) {
			for (Direction enumfacing : Direction.values()) {
				blockpos$pooledmutableblockpos.setPos(p_208493_2_).move(enumfacing);
				i = Math.min(i, getDistanceNew(p_208493_1_.getBlockState(blockpos$pooledmutableblockpos)) + 1);
				if (i == 1) {
					break;
				}
			}
		}

		return p_208493_0_.with(DISTANCE, Integer.valueOf(i));
	}

	private static int getDistanceNew(BlockState neighbor) {
		if (isLog(neighbor.getBlock())) {
			return 0;
		} else {
			return isLeaves(neighbor.getBlock()) ? neighbor.get(DISTANCE) : 7;
		}
	}

	static boolean isLeaves(Block block) {
		return block instanceof BlockKathairisLeaves || block instanceof LeavesBlock || block == MYSTIC_LEAVES || block == SOUL_LEAVES ||
				block == SHINY_LEAVES;
	}

	static boolean isLog(Block block) {
		return block instanceof LogBlock || block instanceof BlockKathairisLog || block == MYSTIC_LOG || block == SOUL_LOG ||
				block == SHINY_LOG;
	}

	@Override
	public boolean ticksRandomly(BlockState p_149653_1_) {
		return p_149653_1_.get(DISTANCE) == 7 && !p_149653_1_.get(PERSISTENT);
	}

	@Override
	public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.func_225534_a_(state, worldIn, pos, random);
		if (!state.get(PERSISTENT) && state.get(DISTANCE) == 7) {
			worldIn.removeBlock(pos,false);
		}
		worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);

	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		int i = getDistanceNew(facingState) + 1;
		if (i != 1 || stateIn.get(DISTANCE) != i) {
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}

		return stateIn;
	}


	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return updateDistance(this.getDefaultState().with(PERSISTENT, Boolean.valueOf(true)), context.getWorld(), context.getPos());
	}

}
