package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.BLUE_CRYSTAL;
import static io.github.krevik.kathairis.init.ModBlocks.VIOLET_CRYSTAL;
import static io.github.krevik.kathairis.init.ModBlocks.YELLOW_CRYSTAL;
import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_SHARD_BLUE;
import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_SHARD_VIOLET;
import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_SHARD_YELLOW;

public class BlockCrystal extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public BlockCrystal() {
		super(Block.Properties.create(Material.GLASS).hardnessAndResistance(2f, 2f).sound(SoundType.GLASS));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumFacing.UP));
	}

	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public IBlockState rotate(IBlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public IBlockState mirror(IBlockState state, Mirror mirrorIn) {
		return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public int getOpacity(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public int quantityDropped(IBlockState state, Random random) {
		return MathHelper.nextInt(random, 1, 3);
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		if (state.getBlock() == BLUE_CRYSTAL) {
			return CRYSTAL_SHARD_BLUE;
		} else if (state.getBlock() == VIOLET_CRYSTAL) {
			return CRYSTAL_SHARD_VIOLET;
		} else if (state.getBlock() == YELLOW_CRYSTAL) {
			return CRYSTAL_SHARD_YELLOW;
		} else {
			return CRYSTAL_SHARD_BLUE;
		}
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		EnumFacing facing = state.get(FACING);
		if (facing == EnumFacing.UP) return isValidGround(worldIn.getBlockState(pos.down()));
		else if (facing == EnumFacing.DOWN) return isValidGround(worldIn.getBlockState(pos.up()));
		else if (facing == EnumFacing.EAST) return isValidGround(worldIn.getBlockState(pos.west()));
		else if (facing == EnumFacing.WEST) return isValidGround(worldIn.getBlockState(pos.east()));
		else if (facing == EnumFacing.SOUTH) return isValidGround(worldIn.getBlockState(pos.north()));
		else if (facing == EnumFacing.NORTH) return isValidGround(worldIn.getBlockState(pos.south()));
		else {
			return false;
		}
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isRemote) {
			entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
		}
	}

	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		EnumFacing facing = context.getFace();
		IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

		if (iblockstate.getBlock() instanceof BlockCrystal) {
			EnumFacing enumfacing = iblockstate.get(FACING);

			if (enumfacing == facing) {
				return Blocks.AIR.getDefaultState();
			}
		}
		if (!isValidGround(context.getWorld().getBlockState(context.getPos().offset(context.getFace().getOpposite())))) {
			return Blocks.AIR.getDefaultState();
		}
		return this.getDefaultState().with(FACING, facing);
	}

	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isRemote) {
			entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(FACING);
	}

	public boolean isValidGround(IBlockState state) {
		boolean is = false;
		Block groundBlock = state.getBlock();
		if (groundBlock.isFullCube(state) && groundBlock.isTopSolid(state)) {
			is = true;
		}
		return is;
	}

}
