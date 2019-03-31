package io.github.krevik.kathairis.world.dimension.feature.swamp;

import io.github.krevik.kathairis.init.ModBiomes;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FeatureBasicSwamp extends Feature<NoFeatureConfig> {
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> c123, Random random, BlockPos pos, NoFeatureConfig p_212245_5_) {

        IBlockState iblockstate1 = getRandomBlockState(random);

        for(int i = 0; i < 32; ++i) {
            BlockPos blockpos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (!world.isAirBlock(blockpos.down()) && world.isAirBlock(blockpos)&&world.getBiome(blockpos)== ModBiomes.KATHARIAN_SWAMP&&
            world.getBlockState(blockpos.down())!= Blocks.WATER.getDefaultState()&&world.getBlockState(blockpos.down()).getBlock()==ModBlocks.KATHAIRIS_GRASS) {
                world.setBlockState(blockpos.down(), iblockstate1, 2);
            }
        }

        return true;
    }

    private IBlockState getRandomBlockState(Random random){
        int k=random.nextInt(4);
        IBlockState result= Blocks.WATER.getDefaultState();
        if(k==0){result= Blocks.WATER.getDefaultState();}
        else if(k==1){result= Blocks.CLAY.getDefaultState();}
        else if(k==2){result= Blocks.GRAVEL.getDefaultState();}
        else if(k==3){result= ModBlocks.MUD_BLOCK.getDefaultState();}
        return result;
    }
}
