package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class FeatureKatharianTallTree1 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();

    public FeatureKatharianTallTree1(Function<Dynamic<?>, ? extends TreeFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_);
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set leavesSet, Set trunksSet, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig p_225557_7_) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int mainTrunkHeight = 6+rand.nextInt(6);
            int branches = 2+rand.nextInt(4);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            for(int xx=0;xx<=mainTrunkHeight;xx++){
                worldIn.setBlockState(new BlockPos(posX,posY+xx,posZ),LOG,2);
                trunksSet.add(new BlockPos(posX,posY+xx,posZ));
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
                    worldIn.setBlockState(actualPos,LOG,2);
                    trunksSet.add(actualPos);
                    int actualLeavesRadius = 2;
                    for(int x=-actualLeavesRadius/2;x<=actualLeavesRadius/2;x++){
                        for(int y=-actualLeavesRadius/2;y<=actualLeavesRadius;y++){
                            for(int z=-actualLeavesRadius/2;z<=actualLeavesRadius/2;z++){
                                BlockPos tmp = new BlockPos(posX+shiftX+x,posY+mainTrunkHeight+height+y+1,posZ+shiftZ+z);
                                if(x*x+y*y+z*z<=actualLeavesRadius/2*actualLeavesRadius/2+1) {
                                    if (isAir(worldIn,tmp)) {
                                        worldIn.setBlockState(actualPos,LEAF,2);
                                        leavesSet.add(actualPos);
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

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config) {
        return true;
    }
}