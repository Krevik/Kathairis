package io.github.krevik.kathairis.world.dimension.feature.plainfields;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockGravel;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FeaturePlainFields extends Feature<NoFeatureConfig> {
    @Override
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig p_212245_5_) {
            int shiftX=0;
            int shiftZ=0;
            int direction = rand.nextInt(2)==0?rand.nextInt(2):1+rand.nextInt(3);
            BlockPos startPos=checkForStartPost(world,pos,6);
            for(int length=0;length<=5+rand.nextInt(16);length++){
                if(direction==0){shiftX=length+rand.nextInt(2)-rand.nextInt(2); shiftZ=shiftZ+rand.nextInt(2)-rand.nextInt(2);}
                if(direction==1){shiftX=-length+rand.nextInt(2)-rand.nextInt(2); shiftZ=shiftZ+rand.nextInt(2)-rand.nextInt(2);}
                if(direction==2){shiftX=shiftX+rand.nextInt(2)-rand.nextInt(2); shiftZ=length+rand.nextInt(2)-rand.nextInt(2);}
                if(direction==3){shiftX=shiftX+rand.nextInt(2)-rand.nextInt(2); shiftZ=-length+rand.nextInt(2)-rand.nextInt(2);}
                int posX=startPos.getX()+shiftX;
                int posY=startPos.getY();
                int posZ=startPos.getZ()+shiftZ;
                if(world.isBlockLoaded(new BlockPos(posX,posY,posZ).down())) {
                    BlockPos tmp = world.getHeight(Heightmap.Type.MOTION_BLOCKING, new BlockPos(posX, posY, posZ)).down();
                    if (world.isBlockLoaded(tmp)) {
                        if (world.getBlockState(tmp).getBlock() == ModBlocks.KATHAIRIS_GRASS)
                            world.setBlockState(tmp, Blocks.GRAVEL.getDefaultState(), 2);
                    }
                }else{
                    break;
                }
            }
            return true;
    }

    private BlockPos checkForStartPost(IWorld world, BlockPos pos, int radius){
        BlockPos result = pos;
        int posX=pos.getX();
        int posY=pos.getY();
        int posZ=pos.getZ();
        for(int x=-radius/2;x<=radius/2;x++){
            for(int z=-radius/2;z<=radius/2;z++){
                BlockPos toCheck = world.getHeight(Heightmap.Type.MOTION_BLOCKING,new BlockPos(posX+x,posY,posZ+z)).down();
                if(world.isBlockLoaded(toCheck)){
                    if(world.getBlockState(toCheck).getBlock() instanceof BlockGravel){
                        result=toCheck;
                    }
                }
            }
        }

        return result;
    }
}
