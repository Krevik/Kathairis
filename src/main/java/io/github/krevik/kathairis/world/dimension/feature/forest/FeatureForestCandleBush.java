package io.github.krevik.kathairis.world.dimension.feature.forest;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.block.BlockForestCandle;
import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class FeatureForestCandleBush extends Feature<NoFeatureConfig> {
    public FeatureForestCandleBush(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49878_1_) {
        super(p_i49878_1_);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        BlockState iblockstate = ModBlocks.FOREST_CANDLE.getDefaultState();
        BlockState state2 = ModBlocks.FOREST_CANDLE.getDefaultState().with(BlockForestCandle.VARIANT,BlockForestCandle.EnumType.UPPER);

        for(int j = 0; j < 64; ++j) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && (!worldIn.getDimension().isNether() || blockpos.getY() < worldIn.getWorld().getHeight() - 1) && iblockstate.getBlock().isValidPosition(iblockstate,worldIn,blockpos)) {
                worldIn.setBlockState(blockpos, iblockstate, 2);
                worldIn.setBlockState(blockpos.up(),state2,2);
                ++i;
            }
        }

        return i > 0;
    }
}