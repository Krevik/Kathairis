package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_GRASS;

/**
 * @author Krevik
 */
public class BlockSnowdropCyprepedium extends BlockKathairisPlant {

	public BlockSnowdropCyprepedium() {
		super();
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isRemote) {
			for (int c = 0; c <= 1 + rand.nextInt(5); c++) {
				BlockPos tmp = new BlockPos(pos.getX() - 6 + rand.nextInt(12), pos.getY() - 3 + rand.nextInt(6), pos.getZ() - 6 + rand.nextInt(12));
				if (worldIn.getBlockState(tmp).getBlock() == KATHAIRIS_GRASS) {
					worldIn.setBlockState(tmp, KATHAIRIS_GRASS.getDefaultState().with(BlockKathairisGrass.SNOWY, Boolean.valueOf(true)), 2);
				}
				if (worldIn.getBlockState(tmp) == Blocks.GRASS.getDefaultState()) {
					worldIn.setBlockState(tmp, Blocks.GRASS.getDefaultState().with(GrassBlock.SNOWY, Boolean.valueOf(true)));
				}
			}
		}
	}

}
