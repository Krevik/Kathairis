package io.github.krevik.kathairis.world.dimension.feature.tree;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.world.dimension.feature.config.BaseKatharianTreeFeatureConfig;
import io.github.krevik.kathairis.world.dimension.feature.config.KatharianTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class FeatureKatharianTallTree3 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.SHINY_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.SHINY_LEAVES.getDefaultState();

    public FeatureKatharianTallTree3(Function<Dynamic<?>, ? extends KatharianTreeFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_);
    }

    private void generateCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random, Set<BlockPos> leavesSet) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*y)<=(radius/2*radius/2))){
                        if(isAir(world,tmp)){
                            world.setBlockState(tmp,leaves,2);
                            leavesSet.add(tmp);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*y)==(radius/2*radius/2)) && isAir(world,tmp.down())){
                        world.setBlockState(tmp.down(),LEAF,2);
                        leavesSet.add(tmp.down());
                        if(random.nextInt(3)==0 && isAir(world,tmp.down(2))){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            leavesSet.add(tmp.down(2));
                            if(random.nextInt(3)==0 && isAir(world,tmp.down(3))){
                                world.setBlockState(tmp.down(3),LEAF,2);
                                leavesSet.add(tmp.down(3));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set leavesSet, Set trunksSet, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig p_225557_7_) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int mainTrunkHeight=10+rand.nextInt(8);
            int branches = (int) (mainTrunkHeight/3);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            for(int c=0;c<=mainTrunkHeight;c++){
                worldIn.setBlockState(new BlockPos(posX,posY+c,posZ),LOG,2);
                trunksSet.add(new BlockPos(posX,posY+c,posZ));
            }
            int branchesHeights[] = new int[branches];
            for(int count=0;count<branches;count++){
                branchesHeights[count]=2+((mainTrunkHeight-2)/branches)+count+count;
            }
            for(int count=0;count<branchesHeights.length;count++){
                int branchLength=4+rand.nextInt(3);
                int shiftX=0; int shiftZ=0;
                for(int y=0;y<branchLength;y++){
                    shiftX=shiftX+rand.nextInt(2)-rand.nextInt(2);
                    shiftZ=shiftZ+rand.nextInt(2)-rand.nextInt(2);
                    BlockPos pos = new BlockPos(posX+shiftX,posY+y+branchesHeights[count],posZ+shiftZ);
                    worldIn.setBlockState(pos,LOG,2);
                    trunksSet.add(pos);
                    if(y==branchLength-1){
                        int radius=3+rand.nextInt(3);
                        generateCrown(worldIn,pos,radius,rand,leavesSet);
                    }
                }
            }
            generateCrown(worldIn,new BlockPos(posX,posY+mainTrunkHeight,posZ),2+rand.nextInt(5),rand,leavesSet);

            return true;
        }
        return false;
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config) {
        return true;
    }
}