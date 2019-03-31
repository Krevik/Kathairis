package io.github.krevik.kathairis.world.dimension.feature;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FeatureKatharianCloud extends Feature<NoFeatureConfig> {

    @Override
    public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random random, BlockPos pos, NoFeatureConfig config) {
            if(random.nextInt(5)==0) {
                IBlockState block = random.nextInt(2)==0 ? ModBlocks.BLUE_CLOUD.getDefaultState() : ModBlocks.YELLOW_CLOUD.getDefaultState();
                int height = 100+random.nextInt(24)+random.nextInt(24)+random.nextInt(24)+random.nextInt(24)+random.nextInt(24);
               generateCloud(world,new BlockPos(pos.getX(),height,pos.getZ()),2+random.nextInt(7),random,block,0);
            }
        return false;
    }

    private void generateCloud(IWorld world, BlockPos pos, int radius, Random random, IBlockState state, int count) {
        if(count<6) {
            for (int x = -radius / 2; x <= radius / 2; x++) {
                for (int z = -radius / 2; z <= radius / 2; z++) {
                    for (int y = -radius / 4; y <= radius; y++) {
                        //do main crown
                        BlockPos tmp = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                        if (((x * x) + (z * z) + (y * 2 * y * 2) <= (radius / 2 * radius / 2))) {
                            if (world.isBlockLoaded(tmp)) {
                                if (world.getBlockState(tmp) == Blocks.AIR.getDefaultState()) {
                                    world.setBlockState(tmp, state, 2);
                                }
                                if (((x * x) + (z * z) + (y * 2 * y * 2) >= (radius / 2 * radius / 2) - 4)) {
                                    if (random.nextInt(7) == 0) {
                                        generateCloud(world, tmp, random.nextInt(6), random, state, count + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
