package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockWillowVineMain extends BlockKathairisPlant implements IGrowable {


    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if(!isValidPosition(stateIn,worldIn, currentPos)) return Blocks.AIR.getDefaultState();
        return stateIn;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return VoxelShapes.fullCube();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        boolean result=false;
        BlockState stateUp = worldIn.getBlockState(pos.up());
        if(stateUp.getBlock() instanceof LeavesBlock || stateUp.getBlock() == ModBlocks.WILLOW_VINE_MAIN){
            result=true;
        }
        return result;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block==Blocks.AIR || block == ModBlocks.WILLOW_VINE_MAIN || block == ModBlocks.WILLOW_VINE_TIP;
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random p_225534_4_) {
        super.tick(state,world,pos,p_225534_4_);
        if (!world.isRemote) {
            if (!isValidPosition(state,world,pos)) {
                world.destroyBlock(pos, false);
            }
            if(world.getBlockState(pos.down()).getBlock()!=ModBlocks.WILLOW_VINE_TIP &&
                    world.getBlockState(pos.down()).getBlock()!=ModBlocks.WILLOW_VINE_MAIN){
                world.setBlockState(pos,ModBlocks.WILLOW_VINE_TIP.getDefaultState());
            }
        }
    }


    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            if (!isValidPosition(state,worldIn,pos)) {
                worldIn.destroyBlock(pos, false);
            }
            if(worldIn.getBlockState(pos.down()).getBlock()!=ModBlocks.WILLOW_VINE_TIP &&
            worldIn.getBlockState(pos.down()).getBlock()!=ModBlocks.WILLOW_VINE_MAIN){
                worldIn.setBlockState(pos,ModBlocks.WILLOW_VINE_TIP.getDefaultState());
            }
        }
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return iBlockReader.getBlockState(blockPos.down()).getBlock()== Blocks.AIR &&
                iBlockReader.getBlockState(blockPos.down(2)).getBlock()==Blocks.AIR;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return world.getBlockState(blockPos.down()).getBlock()== Blocks.AIR &&
                world.getBlockState(blockPos.down(2)).getBlock()==Blocks.AIR;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos blockPos, BlockState blockState) {
        if(world.getBlockState(blockPos.down()).getBlock()==Blocks.AIR&&
                world.getBlockState(blockPos.down(2)).getBlock()==Blocks.AIR){
            if(world.getBlockState(blockPos.down(3)).getBlock()==Blocks.AIR){
                if(random.nextInt(6)==0){
                    world.setBlockState(blockPos.down(), ModBlocks.WILLOW_VINE_TIP.getDefaultState());
                }else{
                    world.setBlockState(blockPos.down(), ModBlocks.WILLOW_VINE_MAIN.getDefaultState());
                }
            }else{
                world.setBlockState(blockPos.down(), ModBlocks.WILLOW_VINE_TIP.getDefaultState());
            }
        }
    }

}
