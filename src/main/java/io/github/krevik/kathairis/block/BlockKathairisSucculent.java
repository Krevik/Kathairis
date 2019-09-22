package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_SUCCULENT;

/**
 * @author Krevik
 */
public class BlockKathairisSucculent extends BlockKathairisPlant {

	public BlockKathairisSucculent() {
		super(Properties.create(Material.PLANTS).tickRandomly().hardnessAndResistance(0.5f).sound(SoundType.PLANT));
	}

	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random) {
		super.tick(state, world, pos, random);
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

	@Nonnull
	@Override
	@OnlyIn(Dist.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
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
