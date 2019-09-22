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

public class FeatureKatharianTallTree2 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();

    public FeatureKatharianTallTree2(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49920_1_) {
        super(p_i49920_1_, true);
    }


    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox box) {
        if(canGrowInto(worldIn, pos.down())) {
            int treeHeight = 5+rand.nextInt(7);
            for (int c = 0; c <= treeHeight; c++) {
                setBlocks(changedBlocks,worldIn,new BlockPos(pos.getX(),pos.getY()+c,pos.getZ()),LOG);
            }

            generateCrown(worldIn, new BlockPos(pos.getX(), pos.getY() + treeHeight, pos.getZ()), (int) (1 + (treeHeight / 1.5)),rand);
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