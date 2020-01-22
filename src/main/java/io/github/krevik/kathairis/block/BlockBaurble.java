package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

/**
 * @author Krevik
 */
public class BlockBaurble extends Block implements IItemGroupProvider {

	protected static final AxisAlignedBB BAURBLE_AABB = new AxisAlignedBB(0.2D, 0.4D, 0.2D, 0.8D, 1D, 0.8D);

	public BlockBaurble() {
		super(Properties.create(Material.GLASS).tickRandomly().hardnessAndResistance(0f).sound(SoundType.GLASS).lightValue(10));
	}

	private void dropIfNotValidPosition(final BlockState state, final World worldIn, final BlockPos pos) {
		if (!worldIn.getBlockState(pos.up()).isSolid()) {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 1 | 2);
			spawnAsEntity(worldIn, pos, new ItemStack(this));
		}
	}

	@Override
	public VoxelShape getShape(BlockState p_196244_1_, IBlockReader p_196244_2_, BlockPos p_196244_3_, ISelectionContext context) {
		return VoxelShapes.create(BAURBLE_AABB);
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.tick(state, world, pos, random);
		dropIfNotValidPosition(state, world, pos);
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		dropIfNotValidPosition(state, worldIn, pos);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		dropIfNotValidPosition(state, worldIn, pos);
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}
}
