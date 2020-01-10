package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_GRASS;
import static io.github.krevik.kathairis.init.ModItems.FRUP;

/**
 * @author Krevik
 */
public class BlockFrupPlant extends CropsBlock {

	private static final AxisAlignedBB[] CARROT_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D)};

	public BlockFrupPlant() {
		super(Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader p_196244_2_, BlockPos p_196244_3_, ISelectionContext p_220053_4_) {
		return VoxelShapes.create(CARROT_AABB[state.get(getAgeProperty())]);
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.FARMLAND || state.getBlock() == KATHAIRIS_DIRT ||
				state.getBlock() == KATHAIRIS_GRASS || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return (worldIn.getNeighborAwareLightSubtracted(pos, 0) >= 8 || worldIn.canBlockSeeSky(pos)) && isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return FRUP;
	}

}
