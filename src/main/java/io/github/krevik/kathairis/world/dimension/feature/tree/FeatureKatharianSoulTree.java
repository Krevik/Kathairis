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

public class FeatureKatharianSoulTree extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.SOUL_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.SOUL_LEAVES.getDefaultState();

    public FeatureKatharianSoulTree(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_, true);
    }


    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox box) {
        if(canGrowInto(worldIn,position)||canGrowInto(worldIn,position.down())){
            int mainBranchHeight=5+rand.nextInt(15);
            int posX=position.getX();
            int posY=position.getY();
            int posZ=position.getZ();
            int shifterX=0;
            int shifterZ=0;
            for(int y=0;y<=mainBranchHeight;y++){
                if(rand.nextInt(2)==0){
                    shifterX=shifterX+rand.nextInt(2)-rand.nextInt(2);
                    if(shifterX>=2){
                        shifterX--;
                    }else{
                        shifterX++;
                    }
                }else{
                    shifterZ=shifterZ+rand.nextInt(2)-rand.nextInt(2);
                    if(shifterZ>=2){
                        shifterZ--;
                    }else{
                        shifterZ++;
                    }
                }
                BlockPos actualPos = new BlockPos(posX+shifterX,posY+y,posZ+shifterZ);
                setBlocks(changedBlocks,worldIn,actualPos,LOG);
            }
            generateCrown(worldIn,new BlockPos(posX+shifterX,posY+mainBranchHeight,posZ+shifterZ),(int)(1.5+mainBranchHeight/2.5),rand);
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