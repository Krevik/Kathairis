package io.github.krevik.kathairis.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_SUCCULENT;

/**
 * @author Krevik
 */
public class BlockKathairisSucculent extends BlockKathairisPlant {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public BlockKathairisSucculent() {
		super(Properties.create(Material.PLANTS).tickRandomly().hardnessAndResistance(0.5f).sound(SoundType.PLANT));
	}

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}


	@Override
	public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.func_225534_a_(state,world,pos,random);
		if (random.nextInt(20) == 0) {
			int height = 0;
			if (world.isAirBlock(pos.up())) {
				for (int c = 1; c <= 10; c++) {
					if (world.getBlockState(pos.down(c)).getBlock() instanceof BlockKathairisMultiGrass) {
						height++;
					} else {
						break;
					}
				}
				if (height < 10) {
					world.setBlockState(pos.up(), world.getBlockState(pos));
				}
			}
		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		boolean can = false;
		can = block instanceof SandBlock || block instanceof BlockSoftSand || block instanceof BlockKathairisSand ||
				block == KATHAIRIS_SUCCULENT;
		return can;
	}

}
