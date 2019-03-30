package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

/**
 * @author Krevik
 */
public class BlockBaurble extends Block {

	protected static final AxisAlignedBB BAURBLE_AABB = new AxisAlignedBB(0.2D, 0.4D, 0.2D, 0.8D, 1D, 0.8D);

	public BlockBaurble() {
		super(Block.Properties.create(Material.GLASS).tickRandomly().hardnessAndResistance(0f).sound(SoundType.GLASS).lightValue(10));
	}

	private void dropIfNotValidPosition(final IBlockState state, final World worldIn, final BlockPos pos) {
		if (!worldIn.getBlockState(pos.up()).isFullCube()) {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 1 | 2);
			spawnAsEntity(worldIn, pos, new ItemStack(this));
		}
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public VoxelShape getShape(IBlockState p_196244_1_, IBlockReader p_196244_2_, BlockPos p_196244_3_) {
		return VoxelShapes.create(BAURBLE_AABB);
	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		dropIfNotValidPosition(state, worldIn, pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		dropIfNotValidPosition(state, worldIn, pos);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		dropIfNotValidPosition(state, worldIn, pos);
	}

}
