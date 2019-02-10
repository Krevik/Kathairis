package mod.krevik.world.gen;

import java.util.Random;

import mod.krevik.block.plants.BlockMysticBush;
import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMysticUniversal extends WorldGenerator
{
	int mode=0;
    public WorldGenMysticUniversal(int i)
    {
    	mode=i;
    }
    
    public WorldGenMysticUniversal()
    {
    	mode=0;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	if(mode==2) {
        	int X=position.getX();
        	int Z=position.getZ();
        	int Y=position.getY();
        	if(Y>250) {
        		return false;
        	}else {
        		if(KCore.ButterflyFlower.canBlockStay(worldIn, position, KCore.ButterflyFlower.getDefaultState())) {
            		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y,Z), KCore.ButterflyFlower.getDefaultState());
        		}
        	}
    	}
    	if(mode==0) {
    	int X=position.getX();
    	int Z=position.getZ();
    	int Y=position.getY();
    	if(Y>250) {
    		return false;
    	}
    	BlockMysticBush blockToGen=this.getRandomBlock(rand);
			if(blockToGen.canBlockStay(worldIn, position, blockToGen.getDefaultState())) {
	    		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y,Z), blockToGen.getDefaultState());
			}
    	}
    	
    	if(mode==1) {
        	int X=position.getX();
        	int Z=position.getZ();
        	int Y=position.getY();
        	if(Y>250) {
        		return false;
        	}else {
    			if(KCore.MysticDeadGrass.canBlockStay(worldIn, position, KCore.MysticDeadGrass.getDefaultState())) {
    	    		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y,Z), KCore.MysticDeadGrass.getDefaultState());
    			}
        	}
    	}
    	if(mode==3) {
        	int X=position.getX();
        	int Z=position.getZ();
        	int Y=position.getY();
        	if(Y>250) {
        		return false;
        	}else {
        	   	if(worldIn.getBlockState(new BlockPos(X,Y,Z))==KCore.KatharianGrass.getDefaultState()&&worldIn.isAirBlock(new BlockPos(X,Y+1,Z))){
            		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y+1,Z), KCore.GooseberryBlock.getDefaultState());
            	}
            	if(worldIn.getBlockState(new BlockPos(X,Y-1,Z))==KCore.KatharianGrass.getDefaultState()&&worldIn.isAirBlock(new BlockPos(X,Y,Z))){
            		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y,Z), KCore.GooseberryBlock.getDefaultState());
            	}
            	if(worldIn.getBlockState(new BlockPos(X,Y-2,Z))==KCore.KatharianGrass.getDefaultState()&&worldIn.isAirBlock(new BlockPos(X,Y-1,Z))){
            		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y-1,Z), KCore.GooseberryBlock.getDefaultState());
            	}
            	if(worldIn.getBlockState(new BlockPos(X,Y+1,Z))==KCore.KatharianGrass.getDefaultState()&&worldIn.isAirBlock(new BlockPos(X,Y+2,Z))){
            		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y+2,Z), KCore.GooseberryBlock.getDefaultState());
            	}
        	}
    	}
    	if(mode==4) {
        	int X=position.getX();
        	int Z=position.getZ();
        	int Y=position.getY();
        	if(Y>250) {
        		return false;
        	}else {
        		if(worldIn.isAirBlock(new BlockPos(X,Y,Z))) {
            		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y,Z), KCore.EnergyShard.getDefaultState());
        		}
        	}
    	}
    	if(mode==5) {
        	int X=position.getX();
        	int Z=position.getZ();
        	int Y=position.getY()+1;
        	int radiusX=1+rand.nextInt(12);
        	int radiusZ=1+rand.nextInt(12);

        	if(Y>250) {
        		return false;
        	}else {
        		for(int x=0;x<radiusX;x++) {
        			for(int y=0;y<5;y++) {
        				for(int z=0;z<radiusZ;z++) {
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X+x,Y+y,Z+z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X-x,Y+y,Z+z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X+x,Y+y,Z-z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X-x,Y+y,Z-z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X+x,Y-y,Z+z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X-x,Y-y,Z+z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X+x,Y-y,Z-z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        					if(rand.nextInt(4)==0) {
        						BlockPos tmp = new BlockPos(X-x,Y-y,Z-z);
        		        		if(worldIn.isAirBlock(tmp)) {
        		        			setBlockAndNotifyAdequately(worldIn,tmp, KCore.SwampGas.getDefaultState());
        		        		}
        					}
        				}
        			}
        		}
        	}
    	}
    	
        return true;
    }

    private BlockMysticBush getRandomBlock(Random rand){
    	BlockMysticBush block=KCore.MysticTallGrass;

    	if(mode==0) {
	    	int random=rand.nextInt(7);
	    	if(random==0||random==1||random==2||random==3||random==4||random==5||random==6){
		    	if(random==0){
		    		block=KCore.MysticFlower;
		    	}
		    	if(random==1){
		    		block=KCore.MysticFlower;
		    	}
		    	if(random==2){
		    		block=KCore.MysticFlower;
		    	}
		    	if(random==3){
		    		block=KCore.MysticMiniGrass;
		    	}
		    	if(random==4){
		    		block=KCore.MysticNightFlower;
		    	}
		    	if(random==5) {
		    		block=KCore.EyePlant;
		    	}
		    	if(random==6) {
		    		block=KCore.MysticFungus;
		    	}
	    	}else{
	    		block=KCore.MysticTallGrass;
	    	}
    	}
    	if(mode==1) {
    		block=KCore.MysticDeadGrass;
    	}
    	return block;
    }
    
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}