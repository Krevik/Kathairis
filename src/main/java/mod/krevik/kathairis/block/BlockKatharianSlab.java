package mod.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockKatharianSlab extends BlockSlab implements IBucketPickupHandler, ILiquidContainer {

	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	protected static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public BlockKatharianSlab(Material material, float hardnessAndResistance, SoundType soundType) {
		super(Properties.create(material).hardnessAndResistance(hardnessAndResistance).sound(soundType));
		this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public int getOpacity(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return worldIn.getMaxLightLevel();
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		SlabType slabtype = state.get(TYPE);
		switch (slabtype) {
			case DOUBLE:
				return VoxelShapes.fullCube();
			case TOP:
				return TOP_SHAPE;
			default:
				return BOTTOM_SHAPE;
		}
	}

	@Override
	public boolean isTopSolid(IBlockState state) {
		return state.get(TYPE) == SlabType.DOUBLE || state.get(TYPE) == SlabType.TOP;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		SlabType slabtype = state.get(TYPE);
		if (slabtype == SlabType.DOUBLE) {
			return BlockFaceShape.SOLID;
		} else if (face == EnumFacing.UP && slabtype == SlabType.TOP) {
			return BlockFaceShape.SOLID;
		} else {
			return face == EnumFacing.DOWN && slabtype == SlabType.BOTTOM ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
		}
	}

	@Nullable
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		IBlockState iblockstate = context.getWorld().getBlockState(context.getPos());
		if (iblockstate.getBlock() == this) {
			return iblockstate.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, Boolean.valueOf(false));
		} else {
			IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
			IBlockState iblockstate1 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
			EnumFacing enumfacing = context.getFace();
			return enumfacing != EnumFacing.DOWN && (enumfacing == EnumFacing.UP || !((double) context.getHitY() > 0.5D)) ? iblockstate1 : iblockstate1.with(TYPE, SlabType.TOP);
		}
	}

	@Override
	public int quantityDropped(IBlockState state, Random random) {
		return state.get(TYPE) == SlabType.DOUBLE ? 2 : 1;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return state.get(TYPE) == SlabType.DOUBLE;
	}

	@Override
	public boolean isReplaceable(IBlockState state, BlockItemUseContext useContext) {
		ItemStack itemstack = useContext.getItem();
		SlabType slabtype = state.get(TYPE);
		if (slabtype != SlabType.DOUBLE && itemstack.getItem() == this.asItem()) {
			if (useContext.replacingClickedOnBlock()) {
				boolean flag = (double) useContext.getHitY() > 0.5D;
				EnumFacing enumfacing = useContext.getFace();
				if (slabtype == SlabType.BOTTOM) {
					return enumfacing == EnumFacing.UP || flag && enumfacing.getAxis().isHorizontal();
				} else {
					return enumfacing == EnumFacing.DOWN || !flag && enumfacing.getAxis().isHorizontal();
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, IBlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}

	@Override
	public IFluidState getFluidState(IBlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, IBlockState state, Fluid fluidIn) {
		return state.get(TYPE) != SlabType.DOUBLE && !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}

	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, IBlockState state, IFluidState fluidStateIn) {
		if (state.get(TYPE) != SlabType.DOUBLE && !state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean allowsMovement(IBlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		switch (type) {
			case LAND:
				return state.get(TYPE) == SlabType.BOTTOM;
			case WATER:
				return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
			case AIR:
				return false;
			default:
				return false;
		}
	}

}
