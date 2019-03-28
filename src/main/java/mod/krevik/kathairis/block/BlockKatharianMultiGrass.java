package mod.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import java.util.Random;

import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_DIRT;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_GRASS;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_MULTI_GRASS;

public class BlockKatharianMultiGrass extends BlockKatharianPlant {

	public BlockKatharianMultiGrass() {
		super(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0f).doesNotBlockMovement().tickRandomly());
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos);
	}

	@Override
	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL ||
				block == Blocks.FARMLAND || block == KATHARIAN_DIRT || block == KATHARIAN_GRASS ||
				block == KATHARIAN_MULTI_GRASS;
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
					world.setBlockState(pos.up(), KATHARIAN_MULTI_GRASS.getDefaultState());
				}
			}
		}
	}

}
