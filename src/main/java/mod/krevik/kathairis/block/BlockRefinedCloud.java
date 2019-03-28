package mod.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockRefinedCloud extends Block {

	public BlockRefinedCloud() {
		super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(0.75f).slipperiness(0.98f));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(IBlockState state, IBlockState adjacentBlockState, EnumFacing side) {
		return adjacentBlockState.getBlock() == this || super.isSideInvisible(state, adjacentBlockState, side);
	}

	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public int getOpacity(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return null;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

}
