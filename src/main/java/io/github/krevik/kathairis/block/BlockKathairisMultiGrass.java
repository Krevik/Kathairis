package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class BlockKathairisMultiGrass extends BlockKathairisPlant implements IItemGroupProvider {

	public BlockKathairisMultiGrass() {
		super(Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0f).doesNotBlockMovement().tickRandomly());
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.PLANTS;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL ||
				block == Blocks.FARMLAND || block == KATHAIRIS_DIRT || block == KATHAIRIS_GRASS ||
				block == KATHAIRIS_MULTI_GRASS;
	}

	@Override
	public void func_225534_a_(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.func_225534_a_(state, world, pos, random);
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
					world.setBlockState(pos.up(), KATHAIRIS_MULTI_GRASS.getDefaultState());
				}
			}
		}
	}

}
