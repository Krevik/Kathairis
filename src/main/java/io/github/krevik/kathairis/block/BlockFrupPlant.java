package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_DIRT;
import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_GRASS;
import static io.github.krevik.kathairis.init.ModItems.FRUP;

/**
 * @author Krevik
 */
public class BlockFrupPlant extends BlockCrops {

	private static final AxisAlignedBB[] CARROT_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D)};

	public BlockFrupPlant() {
		super(Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader p_196244_2_, BlockPos p_196244_3_) {
		return VoxelShapes.create(CARROT_AABB[state.get(getAgeProperty())]);
	}

	@Override
	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.FARMLAND || state.getBlock() == KATHAIRIS_DIRT ||
				state.getBlock() == KATHAIRIS_GRASS || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT;
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		return (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
	}

	@Override
	protected IItemProvider getSeedsItem() {
		return FRUP;
	}

	@Override
	protected IItemProvider getCropsItem() {
		return FRUP;
	}

}
