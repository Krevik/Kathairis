package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.STEPPED_SUCCULENT;

public class BlockSteppedSucculent extends BlockKatharianPlant {

	public BlockSteppedSucculent() {
		super(Block.Properties.create(Material.PLANTS).tickRandomly().hardnessAndResistance(0.5f).sound(SoundType.PLANT));
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
	}

	@Override
	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		boolean can = false;
		can = block instanceof BlockKatharianGrass || block == KATHARIAN_DIRT || block instanceof BlockGrass ||
				block == STEPPED_SUCCULENT;
		return can;
	}

	@Override
	public void tick(IBlockState state, World world, BlockPos pos, Random random) {
		super.tick(state, world, pos, random);
		if (random.nextInt(20) == 0) {
			int height = 0;
			if (world.isAirBlock(pos.up())) {
				for (int c = 1; c <= 10; c++) {
					if (world.getBlockState(pos.down(c)).getBlock() instanceof BlockKatharianMultiGrass) {
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

}
