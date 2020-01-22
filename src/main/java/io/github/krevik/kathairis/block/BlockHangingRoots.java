package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockHangingRoots extends BlockKathairisPlant {

    public BlockHangingRoots(){
        super();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.up()).isSolid()||worldIn.getBlockState(pos.up()).getBlock()== ModBlocks.HANGING_ROOTS;
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, world, pos, neighbor);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
        return true;
    }

    @Override
    public void onEntityCollision(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {

        super.onEntityCollision(p_196262_1_, p_196262_2_, p_196262_3_, p_196262_4_);
    }

    @Override
    public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.func_225534_a_(state, worldIn, pos, random);
        if (!worldIn.isRemote) {
            if (random.nextInt(40) == 0) {
                if(worldIn.isAirBlock(pos.down())){
                    worldIn.setBlockState(pos.down(),ModBlocks.HANGING_ROOTS.getDefaultState());
                }
            }
        }
    }
}
