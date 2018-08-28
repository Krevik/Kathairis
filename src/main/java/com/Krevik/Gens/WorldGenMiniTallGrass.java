package com.Krevik.Gens;

import java.util.Random;

import com.Krevik.Blocks.BlockBlueFruitPlant;
import com.Krevik.Blocks.BlockMysticBush;
import com.Krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMiniTallGrass extends WorldGenerator
{
    private BlockMysticBush grassToGen;

    public WorldGenMiniTallGrass(BlockMysticBush block)
    {
        grassToGen=block;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = new BlockPos(position.getX()+rand.nextInt(6) - rand.nextInt(6), position.getY()+rand.nextInt(4) - rand.nextInt(4), position.getZ()+rand.nextInt(6) - rand.nextInt(6));

            if (worldIn.isAirBlock(blockpos) && grassToGen.canBlockStay(worldIn, blockpos, grassToGen.getDefaultState()))
            {
            	if(grassToGen==KCore.BlueFruitPlant) {
                	setBlockAndNotifyAdequately(worldIn,blockpos, KCore.BlueFruitPlant.getDefaultState().withProperty(BlockBlueFruitPlant.AGE, rand.nextInt(7)));
            	}else {
            	setBlockAndNotifyAdequately(worldIn,blockpos, this.grassToGen.getDefaultState());
            	}
            }
        }

        return true;
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}