package mod.krevik.world.gen.swamp;

import mod.krevik.KCore;
import mod.krevik.block.plants.BlockJadeVines;
import mod.krevik.world.gen.WorldGenAbstractBasicMysticTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenMysticSwampsTree extends WorldGenAbstractBasicMysticTree {

	
	public WorldGenMysticSwampsTree() {
		super(true);
	}
	Random random;
	
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {	
    	random = rand;
    	IBlockState logState= KCore.MysticLog.getDefaultState();
    	int posX=position.getX();
    	int posZ=position.getZ();
    	int posY=position.getY();
    		int treeHeight=4+rand.nextInt(5);
    		int shiftX=0;
    		int shiftZ=0;
    		if(KCore.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())) {
    				for(int c=0;c<=treeHeight;c++) {
    					shiftX=shiftX+rand.nextInt(2)-rand.nextInt(2);
    					shiftZ=shiftZ+rand.nextInt(2)-rand.nextInt(2);
    					BlockPos tmp = new BlockPos(posX+shiftX,posY+c,posZ+shiftZ);
    					setBlockAndNotifyAdequately(worldIn,tmp, logState);
    				}
    			generateCrown(worldIn, new BlockPos(posX+shiftX,posY+2+treeHeight,posZ+shiftZ), treeHeight/2);
				setBlockAndNotifyAdequately(worldIn,new BlockPos(posX+shiftX,posY+2+treeHeight,posZ+shiftZ), logState);

    			for(int x=0;x<=treeHeight+2;x++) {
    				for(int z=0;z<=treeHeight+2;z++) {
    					for(int y=0;y<=4;y++) {
    						BlockPos tmp = new BlockPos(posX+shiftX-treeHeight-1+x,posY+treeHeight+y-1,posZ+shiftZ-treeHeight-1+z);
    						if(worldIn.getBlockState(tmp.up())==KCore.MysticLeaves.getDefaultState()) {
    							if(worldIn.isAirBlock(tmp)) {
    								setBlockAndNotifyAdequately(worldIn,tmp, KCore.JadeVine_empty.getDefaultState());
    								
    								for(int i=0;i<rand.nextInt(5);i++) {
    		    						BlockPos tmp2 = new BlockPos(posX+shiftX-treeHeight-1+x,posY+treeHeight+y-1-i,posZ+shiftZ-treeHeight-1+z);
    		    						if(worldIn.getBlockState(tmp2.up()).getBlock() instanceof BlockJadeVines) {
    		    							BlockJadeVines vines = (BlockJadeVines) worldIn.getBlockState(tmp2.up()).getBlock();
    		    							vines.grow(worldIn, tmp2.up());
    		    						}
    								}
    							}
    						}
    					}
    				}
    			}
    		}
    			
        	return true;
    	}
    
    public void generateCrown(World world,BlockPos pos,int radius) {
    	IBlockState leaves = KCore.MysticLeaves.getDefaultState();
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			for(int y=0;y<=radius;y++) {
    				if(((x*x)+(z*z)+(y*y)<=(radius*radius))){
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());

    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());
    					if(((x*x)+(z*z)+(y*y)<(radius*radius-radius))) {
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), KCore.MysticLog.getDefaultState());}
        					if(random.nextInt(8)==0) {setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), KCore.MysticLog.getDefaultState());}
    					}
    				}
    			}
    		}
    	}
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}
