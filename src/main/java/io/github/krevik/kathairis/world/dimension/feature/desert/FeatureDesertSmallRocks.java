package io.github.krevik.kathairis.world.dimension.feature.desert;

import io.github.krevik.kathairis.block.BlockBrinePustule;
import io.github.krevik.kathairis.block.BlockPurplePalm;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FeatureDesertSmallRocks extends Feature<NoFeatureConfig> {

    @Override
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random random, BlockPos pos, NoFeatureConfig config) {
        if(world.getBlockState(pos.down()).getBlock()== ModBlocks.SOFT_SAND || world.getBlockState(pos.down()).getBlock()==ModBlocks.KATHAIRIS_SAND){
                int mainHeight=random.nextInt(6);
                for(int y=0;y<=mainHeight;y++){
                    world.setBlockState(pos.up(y),getRandomWeatheredRockState(random),18);
                }
                if(random.nextInt(6)==0) {
                    world.setBlockState(pos.up(mainHeight + 1), ModBlocks.PURPLE_PALM.getDefaultState(), 18);
                    world.setBlockState(pos.up(mainHeight + 2), ModBlocks.PURPLE_PALM.getDefaultState().with(BlockPurplePalm.VARIANT,BlockPurplePalm.EnumType.AIR), 18);
                }

                if(mainHeight>2){
                    int surroundingHeight=random.nextInt(mainHeight-1);
                    for(int y=0;y<=surroundingHeight;y++){
                        BlockPos pos1 = pos.up(y).east();
                        BlockPos pos2 = pos.up(y).west();
                        BlockPos pos3 = pos.up(y).south();
                        BlockPos pos4 = pos.up(y).north();
                        world.setBlockState(pos1,getRandomWeatheredRockState(random),18);
                        world.setBlockState(pos2,getRandomWeatheredRockState(random),18);
                        world.setBlockState(pos3,getRandomWeatheredRockState(random),18);
                        world.setBlockState(pos4,getRandomWeatheredRockState(random),18);
                        if(y==surroundingHeight){
                            if(random.nextInt(8)==0){
                                world.setBlockState(pos.up(y+1).east(), ModBlocks.PURPLE_PALM.getDefaultState(), 18);
                            }
                            if(random.nextInt(8)==0){
                                world.setBlockState(pos.up(y+1).west(), ModBlocks.PURPLE_PALM.getDefaultState(), 18);
                            }
                            if(random.nextInt(8)==0){
                                world.setBlockState(pos.up(y+1).south(), ModBlocks.PURPLE_PALM.getDefaultState(), 18);
                            }
                            if(random.nextInt(8)==0){
                                world.setBlockState(pos.up(y+1).north(), ModBlocks.PURPLE_PALM.getDefaultState(), 18);
                            }
                        }
                    }
                }

                //TODO CHECK IF IT IS WORKING
                for(int x=-4;x<=4;x++){
                    for(int y=0;y<=mainHeight;y++){
                        for(int z=-4;z<=4;z++){
                            if(random.nextInt(6)==0){
                                BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                                if(ModBlocks.BRINE_PUSTULE.isValidPosition(ModBlocks.BRINE_PUSTULE.getDefaultState(),world,tmp)){
                                    world.setBlockState(tmp,ModBlocks.BRINE_PUSTULE.getDefaultState(),2);
                                }
                            }
                        }
                    }
                }
            return true;
        }
        return false;
    }

    private IBlockState getRandomWeatheredRockState(Random random){
        return random.nextInt(2)==0? ModBlocks.WEATHERED_ROCK.getDefaultState():ModBlocks.HARDENED_WEATHERED_ROCK.getDefaultState();
    }
}
