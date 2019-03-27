package mod.krevik.kathairis.block;

import mod.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockKatharianStone extends Block {

	public BlockKatharianStone() {
		super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.5f, 2.5f));
	}

	@Nonnull
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return ModBlocks.KATHARIAN_COBBLESTONE;
	}

}
