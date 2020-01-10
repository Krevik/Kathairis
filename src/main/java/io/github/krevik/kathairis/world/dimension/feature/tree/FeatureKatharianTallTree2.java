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

public class FeatureKatharianTallTree2 extends AbstractKatharianTreeFeature {
    private static final BlockState LOG = ModBlocks.MYSTIC_LOG.getDefaultState();
    private static final BlockState LEAF = ModBlocks.MYSTIC_LEAVES.getDefaultState();

    public FeatureKatharianTallTree2(Function<Dynamic<?>, ? extends KatharianTreeFeatureConfig> p_i49920_1_) {
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
    protected boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos pos, Set leavesSet, Set trunksSet, MutableBoundingBox p_225557_6_, BaseTreeFeatureConfig p_225557_7_) {
        if(canGrowInto(worldIn, pos.down())) {
            int treeHeight = 5+rand.nextInt(7);
            for (int c = 0; c <= treeHeight; c++) {
                worldIn.setBlockState(new BlockPos(pos.getX(),pos.getY()+c,pos.getZ()),LOG,2);
                trunksSet.add(new BlockPos(pos.getX(),pos.getY()+c,pos.getZ()));
            }

            generateCrown(worldIn, new BlockPos(pos.getX(), pos.getY() + treeHeight, pos.getZ()), (int) (1 + (treeHeight / 1.5)),rand,leavesSet);
            return true;
        }

        return false;
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator generator, Random rand, BlockPos pos, IFeatureConfig config) {
        return true;
    }
}