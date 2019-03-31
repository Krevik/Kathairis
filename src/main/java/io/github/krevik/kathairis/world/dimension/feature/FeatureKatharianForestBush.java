package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.tree.AbstractKatharianTreeFeature;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class FeatureKatharianForestBush extends AbstractKatharianTreeFeature {

    IBlockState LOG = ModBlocks.SHINY_LOG.getDefaultState();
    IBlockState LEAVES = ModBlocks.SHINY_LEAVES.getDefaultState();

    public FeatureKatharianForestBush() {
        super(true);
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorld worldIn, Random rand, BlockPos pos) {
        if(!canGrowInto(worldIn,pos.down())){
            return false;
        }
        if(rand.nextInt(2)==0){
            LOG=ModBlocks.MYSTIC_LOG.getDefaultState();
            LEAVES=ModBlocks.MYSTIC_LEAVES.getDefaultState();
        }
        int k=rand.nextInt(4);
        if(k==0){
            setLogState1(changedBlocks,worldIn,pos,LOG);
            setBlockState(worldIn,pos.east(),LEAVES);
            setBlockState(worldIn,pos.west(),LEAVES);
            setBlockState(worldIn,pos.south(),LEAVES);
            setBlockState(worldIn,pos.north(),LEAVES);
            setBlockState(worldIn,pos.up(),LEAVES);
        }
        else if(k==1){
            setLogState1(changedBlocks,worldIn,pos,LOG);
            setLogState1(changedBlocks,worldIn,pos.east(),LOG);
            setBlockState(worldIn,pos.west(),LEAVES);
            setBlockState(worldIn,pos.south(),LEAVES);
            setBlockState(worldIn,pos.north(),LEAVES);
            setBlockState(worldIn,pos.up(),LEAVES);
            setBlockState(worldIn,pos.east().east(),LEAVES);
            setBlockState(worldIn,pos.east().south(),LEAVES);
            setBlockState(worldIn,pos.east().north(),LEAVES);
            setBlockState(worldIn,pos.east().up(),LEAVES);
        }
        else if(k==2){
            setLogState1(changedBlocks,worldIn,pos,LOG);
            setLogState1(changedBlocks,worldIn,pos.west(),LOG);
            setBlockState(worldIn,pos.east(),LEAVES);
            setBlockState(worldIn,pos.south(),LEAVES);
            setBlockState(worldIn,pos.north(),LEAVES);
            setBlockState(worldIn,pos.up(),LEAVES);
            setBlockState(worldIn,pos.west().west(),LEAVES);
            setBlockState(worldIn,pos.west().south(),LEAVES);
            setBlockState(worldIn,pos.west().north(),LEAVES);
            setBlockState(worldIn,pos.west().up(),LEAVES);
        }
        else if(k==3){
            setLogState1(changedBlocks,worldIn,pos,LOG);
            setLogState1(changedBlocks,worldIn,pos.up(),LOG);
            setBlockState(worldIn,pos.up().up(),LEAVES);
            setBlockState(worldIn,pos.up().south(),LEAVES);
            setBlockState(worldIn,pos.up().north(),LEAVES);
            setBlockState(worldIn,pos.up().east(),LEAVES);
            setBlockState(worldIn,pos.up().west(),LEAVES);
        }

        return true;
    }

    private void func_208521_b(IWorld p_208521_1_, BlockPos p_208521_2_, IBlockState p_208521_3_) {
        if (this.doBlockNotify) {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 19);
        } else {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 18);
        }

    }

    protected final void setLogState1(Set<BlockPos> changedBlocks, IWorld worldIn, BlockPos p_208520_3_, IBlockState p_208520_4_) {
        this.func_208521_b(worldIn, p_208520_3_, p_208520_4_);
            changedBlocks.add(p_208520_3_.toImmutable());

    }
}
