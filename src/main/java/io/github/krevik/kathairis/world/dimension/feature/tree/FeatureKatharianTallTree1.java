package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class FeatureKatharianTallTree1 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();

    public FeatureKatharianTallTree1(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_, true);
    }


    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox box) {
      if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
          int mainTrunkHeight = 6+rand.nextInt(6);
          int branches = 2+rand.nextInt(4);
          int posX=position.getX();
          int posY=position.getY();
          int posZ=position.getZ();
          for(int xx=0;xx<=mainTrunkHeight;xx++){
              setBlocks(changedBlocks,worldIn,new BlockPos(posX,posY+xx,posZ),LOG);
          }
          for(int c=1;c<=branches;c++){
              int branchHeight = 5+rand.nextInt(6)-rand.nextInt(4);
              int shiftX=0;
              int shiftZ=0;
              int desiredShiftX=rand.nextInt(10)-5;
              int desiredShiftZ=rand.nextInt(10)-5;
              for(int height=1;height<=branchHeight;height++){
                if(shiftX<=desiredShiftX){shiftX=shiftX+rand.nextInt(2);}
                if(shiftX>=desiredShiftX){shiftX=shiftX-rand.nextInt(2);}
                if(shiftZ<=desiredShiftZ){shiftZ=shiftZ+rand.nextInt(2);}
                if(shiftZ>=desiredShiftZ){shiftZ=shiftZ-rand.nextInt(2);}
                BlockPos actualPos = new BlockPos(posX+shiftX,posY+height+mainTrunkHeight,posZ+shiftZ);
                setBlocks(changedBlocks,worldIn,actualPos,LOG);
                int actualLeavesRadius = 2;
                for(int x=-actualLeavesRadius/2;x<=actualLeavesRadius/2;x++){
                    for(int y=-actualLeavesRadius/2;y<=actualLeavesRadius;y++){
                        for(int z=-actualLeavesRadius/2;z<=actualLeavesRadius/2;z++){
                            BlockPos tmp = new BlockPos(posX+shiftX+x,posY+mainTrunkHeight+height+y+1,posZ+shiftZ+z);
                            if(x*x+y*y+z*z<=actualLeavesRadius/2*actualLeavesRadius/2+1) {
                                if (isAir(worldIn,tmp)) {
                                    worldIn.setBlockState(tmp, LEAF, 2);
                                }
                            }
                        }
                    }
                }
              }

          }

          return true;
      }
      return false;
    }



    protected final void setBlocks(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos p_208520_3_, BlockState p_208520_4_) {
        this.func_208521_b(worldIn, p_208520_3_, p_208520_4_);
            changedBlocks.add(p_208520_3_.toImmutable());

    }

    private void func_208521_b(IWorldGenerationReader p_208521_1_, BlockPos p_208521_2_, BlockState p_208521_3_) {
        if (this.doBlockNotify) {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 19);
        } else {
            p_208521_1_.setBlockState(p_208521_2_, p_208521_3_, 18);
        }
    }
}