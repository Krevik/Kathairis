package com.Krevik.GensSwamps;

import java.util.Random;

import com.Krevik.Main.KCore;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMudPaddle extends WorldGenerator
{
    private IBlockState tallGrassState;

    public WorldGenMudPaddle()
    {
        this.tallGrassState = KCore.MudBlock.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	if(rand.nextInt(8)==0) {
    		tallGrassState=Blocks.GRAVEL.getDefaultState();
    	}
    	if(rand.nextInt(8)==0) {
    		tallGrassState=Blocks.CLAY.getDefaultState();
    	}
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if(worldIn.getBlockState(blockpos.down())==KCore.CorruptedGrass.getDefaultState()) {
            	setBlockAndNotifyAdequately(worldIn,blockpos.down(), this.tallGrassState);
            }
        }

        return true;
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}