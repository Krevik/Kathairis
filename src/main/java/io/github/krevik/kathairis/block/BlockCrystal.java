package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

/**
 * @author Krevik
 */
public class BlockCrystal extends Block implements IItemGroupProvider {

	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public BlockCrystal() {
		super(Properties.create(Material.GLASS).hardnessAndResistance(2f, 2f).sound(SoundType.GLASS));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public static boolean isValidPosition1(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction facing = state.get(FACING);
		if(facing == Direction.UP) return isValidGround1(worldIn.getBlockState(pos.down()));
		else if(facing == Direction.DOWN) return isValidGround1(worldIn.getBlockState(pos.up()));
		else if(facing == Direction.EAST) return isValidGround1(worldIn.getBlockState(pos.west()));
		else if(facing == Direction.WEST) return isValidGround1(worldIn.getBlockState(pos.east()));
		else if(facing == Direction.SOUTH) return isValidGround1(worldIn.getBlockState(pos.north()));
		else if(facing == Direction.NORTH) return isValidGround1(worldIn.getBlockState(pos.south()));
		else{
			return false;
		}
	}

	public static boolean isValidGround1(BlockState state){
		boolean is=false;
		if(state.isSolid()){
			is=true;
		}
		return is;
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
	}

	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction facing = state.get(FACING);
		if (facing == Direction.UP) return isValidGround(worldIn.getBlockState(pos.down()));
		else if (facing == Direction.DOWN) return isValidGround(worldIn.getBlockState(pos.up()));
		else if (facing == Direction.EAST) return isValidGround(worldIn.getBlockState(pos.west()));
		else if (facing == Direction.WEST) return isValidGround(worldIn.getBlockState(pos.east()));
		else if (facing == Direction.SOUTH) return isValidGround(worldIn.getBlockState(pos.north()));
		else if (facing == Direction.NORTH) return isValidGround(worldIn.getBlockState(pos.south()));
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
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		Direction facing = context.getFace();
		BlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

		if (iblockstate.getBlock() instanceof BlockCrystal) {
			Direction enumfacing = iblockstate.get(FACING);

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
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isRemote) {
			entityIn.attackEntityFrom(DamageSource.MAGIC, 1);
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public boolean isValidGround(BlockState state) {
		boolean is = false;
		if (state.isSolid()) {
			is = true;
		}
		return is;
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

}
