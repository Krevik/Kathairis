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

public class FeatureKatharianTallTree3 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.SHINY_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.SHINY_LEAVES.getDefaultState();

    public FeatureKatharianTallTree3(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_, true);
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox box) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int mainTrunkHeight=10+rand.nextInt(8);
            int branches = (int) (mainTrunkHeight/3);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            for(int c=0;c<=mainTrunkHeight;c++){
                setBlocks(changedBlocks,worldIn,new BlockPos(posX,posY+c,posZ),LOG);
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
                    setBlocks(changedBlocks,worldIn,pos,LOG);
                    if(y==branchLength-1){
                        int radius=3+rand.nextInt(3);
                        generateCrown(worldIn,pos,radius,rand);
                    }
                }
            }
            generateCrown(worldIn,new BlockPos(posX,posY+mainTrunkHeight,posZ),2+rand.nextInt(5),rand);

            return true;
        }
        return false;
    }

    private void generateCrown(IWorldGenerationReader world, BlockPos pos, int radius, Random random) {
        BlockState leaves = LEAF;
        for(int x=-radius/2;x<=radius/2;x++) {
            for(int z=-radius/2;z<=radius/2;z++) {
                for(int y=-radius/4;y<=radius;y++) {
                    //do main crown
                    BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                    if(((x*x)+(z*z)+(y*y)<=(radius/2*radius/2))){
                        if(isAir(world,tmp)){
                            world.setBlockState(tmp,leaves,2);
                        }
                    }
                    //add some hanging blocks
                    if(((x*x)+(z*z)+(y*y)==(radius/2*radius/2)) && isAir(world,tmp.down())){
                        world.setBlockState(tmp.down(),LEAF,2);
                        if(random.nextInt(3)==0 && isAir(world,tmp.down(2))){
                            world.setBlockState(tmp.down(2),LEAF,2);
                            if(random.nextInt(3)==0 && isAir(world,tmp.down(3))){
                                world.setBlockState(tmp.down(3),LEAF,2);
                            }
                        }
                    }
                }
            }
        }
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