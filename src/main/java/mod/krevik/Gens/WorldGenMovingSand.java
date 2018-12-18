package mod.krevik.Gens;

import java.util.Random;

import mod.krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMovingSand extends WorldGenerator
{

    public WorldGenMovingSand()
    {
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	position=new BlockPos(position.getX(),position.getY(),position.getZ());
    	IBlockState Sand = KCore.ForgottenSand.getDefaultState();
    	IBlockState MSand = KCore.MovingSand.getDefaultState();
    	double posX = position.getX();
    	double posZ = position.getZ();
    	double posY = getTopSandBlock(worldIn,position).getY();
    	BlockPos tmp = new BlockPos(posX,posY,posZ);
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.north();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.south().south();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.north().north().east();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.west().west();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.south();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.south();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.east().east();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	tmp=tmp.north();
    	if(worldIn.getBlockState(tmp)==Sand) {
    		setBlockAndNotifyAdequately(worldIn,tmp, MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    		setBlockAndNotifyAdequately(worldIn,tmp.down(), MSand);
    	}
    	
    	
        return true;
    }
    
    
    private BlockPos getTopSandBlock(World worldIn,BlockPos position){
    	int top=0;
        	for(top=50;top<256;top++){
        		if(worldIn.getBlockState(new BlockPos(position.getX(),top,position.getZ()))==KCore.ForgottenSand.getDefaultState()){
        			break;
        		}
        	}
    	return new BlockPos(position.getX(),top,position.getZ());

    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}