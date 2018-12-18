package mod.krevik.Gens.Desert;

import java.util.Random;

import mod.krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBigRockMushroom extends WorldGenerator
{

    public WorldGenBigRockMushroom()
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
    	int radius=rand.nextInt(4)+3;
    	if(posY>250||worldIn.getBlockState(position)==KCore.MysticLeaves.getDefaultState()) {
    		return false;
    	}else {
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().east(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north(), Rock);
    	
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().east().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().east().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north().up(), Rock);
    	
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().east().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().east().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north().up().up(), Rock);
    	
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().east().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).north().north().east().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north().up().up().up(), Rock);
    	setBlockAndNotifyAdequately(worldIn,new BlockPos(posX,posY,posZ).east().east().north().up().up().up(), Rock);
    	tmp=tmp.east().north().up().up().up();
    	posX=tmp.getX();
    	posY=tmp.getY();
    	posZ=tmp.getZ();
    	int tmpradius=radius;
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
				for(int y=0;y<radius;y++) {
        			if((x*x)+(z*z)<=((radius-y)*(radius-y))) {
        				setBlockAndNotifyAdequately(worldIn,new BlockPos(posX+x,posY+y,posZ+z), Rock);
        				setBlockAndNotifyAdequately(worldIn,new BlockPos(posX-x,posY+y,posZ-z), Rock);
        				setBlockAndNotifyAdequately(worldIn,new BlockPos(posX+x,posY+y,posZ-z), Rock);
        				setBlockAndNotifyAdequately(worldIn,new BlockPos(posX-x,posY+y,posZ+z), Rock);
        			}
				}
    		}
    	}
    	
    	
        return true;
    	}
    }
		return false;
	}
    
}