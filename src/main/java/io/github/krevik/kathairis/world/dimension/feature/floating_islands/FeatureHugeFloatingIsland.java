package io.github.krevik.kathairis.world.dimension.feature.floating_islands;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FeatureHugeFloatingIsland extends Feature<NoFeatureConfig>  {
    @Override
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> c, Random random, BlockPos pos, NoFeatureConfig p_212245_5_) {
        int radius1=25+random.nextInt(10);
        int radius2=25+random.nextInt(10);
        int height=140+random.nextInt(25)+random.nextInt(25)+random.nextInt(25);

        for(int x=-radius1;x<=radius1;x++){
            for(int z=-radius2;z<=radius2;z++){
                for(int y=radius1+radius2;y>=-radius1-radius2;y--){
                    if(x*x-4*y+z*z<=2*radius1+2*radius2*MathHelper.sin((float) (x*x))*MathHelper.sin(x*x)+MathHelper.cos(z)*MathHelper.cos(z)){
                        BlockPos tmp = new BlockPos(pos.getX()+x,height+y,pos.getZ()+z);
                        IBlockState stateToPlace;
                        if(y==radius1+radius2){
                            stateToPlace=ModBlocks.KATHAIRIS_GRASS.getDefaultState();
                        }else if(MathHelper.abs(y-radius1-radius2)<2+random.nextInt(4)&&y!=radius1+radius2){
                            stateToPlace=ModBlocks.KATHAIRIS_DIRT.getDefaultState();
                        }else if(x==0&&z==0){
                            stateToPlace=ModBlocks.MAGNETHIUM.getDefaultState();
                        }else{
                            stateToPlace=ModBlocks.KATHAIRIS_STONE.getDefaultState();
                        }
                        world.setBlockState(tmp, stateToPlace,2);
                    }
                }
            }
        }

        return true;
    }
}
