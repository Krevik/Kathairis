package mod.krevik.world.gen.swamp;

import java.util.Random;

import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMysticSwampsWater extends WorldGenerator
{
    private final IBlockState tallGrassState;

    public WorldGenMysticSwampsWater()
    {
        this.tallGrassState = Blocks.WATER.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if(worldIn.getBlockState(blockpos.down())== KCore.KatharianGrass.getDefaultState()) {
            	worldIn.setBlockState(blockpos.down(), this.tallGrassState,3);
				worldIn.neighborChanged(blockpos.down(), worldIn.getBlockState(blockpos.down()).getBlock(), blockpos);
            }
        }

        return true;
    }
}