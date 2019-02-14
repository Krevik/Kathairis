package mod.krevik.world.gen;

import mod.krevik.KCore;
import mod.krevik.block.plants.BlockMysticBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenMysticDesertCactus extends WorldGenerator
{
    private BlockMysticBush grassToGen;
    Random random = new Random();
    public WorldGenMysticDesertCactus()
    {
        grassToGen= KCore.Succulent;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	BlockPos tmp=position;
    	int Y=position.getY();
    	int X=position.getX();
    	int Z=position.getZ();
    	int height=random.nextInt(6)+1;
    	if(KCore.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())&&grassToGen.canBlockStay(worldIn, position, grassToGen.getDefaultState())) {
	    	for(int c=0;c<height;c++) {
	    		BlockPos target=new BlockPos(X,Y+c,Z);
	    		if(worldIn.isAirBlock(target)) {
	    			setBlockAndNotifyAdequately(worldIn,target, grassToGen.getDefaultState());
	    		}
	    	}
    	}
    	return true;
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}