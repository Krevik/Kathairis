package mod.krevik.world.gen.desert;

import java.util.Random;

import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRockMushroom extends WorldGenerator
{

    public WorldGenRockMushroom()
    {
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	if(KCore.instance.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())) {

    	IBlockState Sand = KCore.ForgottenSand.getDefaultState();
    	IBlockState Rock = KCore.WeatheredRock.getDefaultState();
    	double posX = position.getX();
    	double posZ = position.getZ();
    	double posY = position.getY();
    	BlockPos tmp = new BlockPos(posX,posY,posZ);
    	
    	if(posY>250||worldIn.getBlockState(position)==KCore.MysticLeaves.getDefaultState()) {
    		return false;
    	}else {
    	setBlockAndNotifyAdequately(worldIn,tmp, Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north().west().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north().west().south().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north().west().south().west().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north().west().south().west().west().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp.east().south().west().up().north().east().south().up().north().west().south().west().west().north().east(), Rock);
    	BlockPos tmp2 = new BlockPos(tmp.east().south().west().up().north().east().south().up().north().west().south().west().west().north().east());
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south().west().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south().west().south().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south().west().south().west().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south().west().south().west().west().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp2.east().east().east().east().south().west().south().west().west().west().north().north().north(), Rock);
    	BlockPos tmp3 = new BlockPos(tmp2.east().east().east().east().south().west().south().west().west().west().north().north().north().east());
    	setBlockAndNotifyAdequately(worldIn,tmp3, Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east().north().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east().north().west().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east().north().west().up().west(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east().north().west().up().west().south(), Rock);
    	setBlockAndNotifyAdequately(worldIn,tmp3.east().east().up().west().west().south().west().south().east().south().east().north().east().north().west().up().west().south().east(), Rock);

        return true;
    	}
    	}
		return false;
    }
    
}