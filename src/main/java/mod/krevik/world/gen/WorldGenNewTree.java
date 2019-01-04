package mod.krevik.world.gen;

import java.util.Random;

import mod.krevik.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenNewTree extends WorldGenAbstractBasicMysticTree{

	
    public WorldGenNewTree()
    {
    	super(false);
    }
    
    
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	
    	boolean shouldGenMainCrown=false;
    	int treeHeight=8+rand.nextInt(4);
    	if(position.getY()>200||!worldIn.isAirBlock(position)||worldIn.getBlockState(position.down())!=KCore.CorruptedGrass.getDefaultState()||
    			worldIn.getBlockState(position.down())==KCore.MysticLeaves.getDefaultState()) {
    		return false;
    	}else {
    	int branchesCount=rand.nextInt(4)+1;
    	int[] branchesHeights=new int[branchesCount];
    	int[] branchesLenghts=new int[branchesCount];
    	int[] branchesDirections=new int[branchesCount];
    	for(int c=0;c<branchesCount;c++) {
    		branchesHeights[c]=treeHeight-rand.nextInt(treeHeight)+1;
    		branchesLenghts[c]=(treeHeight/2)-rand.nextInt(4);
    		branchesDirections[c]=rand.nextInt(4);
    	}
    	for(int xd=0;xd<branchesCount;xd++) {
    		if(branchesHeights[xd]+branchesLenghts[xd]<treeHeight+1||(branchesHeights[xd]+branchesLenghts[xd])>treeHeight+2) {
    			shouldGenMainCrown=true;
    		}
    	}

    	//generate trunk
    	for(int i=0;i<=treeHeight;i++) {
    		setBlockAndNotifyAdequately(worldIn,new BlockPos(position.getX(),position.getY()+i,position.getZ()),KCore.MysticLog.getDefaultState());
    	}
		setBlockAndNotifyAdequately(worldIn,new BlockPos(position.getX(),position.getY()+treeHeight+1,position.getZ()),KCore.MysticLog.getDefaultState());

    	/////
    	//////////////////////////////////
    	
    	//generate branches
    	//generate First Branch
    	IBlockState log = KCore.MysticLog.getDefaultState();
    	for(int k=0;k<branchesCount;k++) {
    		if(branchesDirections[k]==0) {
    			for(int c=1;c<=branchesLenghts[k];c++) {
    				BlockPos tmp = new BlockPos(position.getX()+c,position.getY()+branchesHeights[k]+c,position.getZ());
    				setBlockAndNotifyAdequately(worldIn,tmp,log);
    				if(c==branchesLenghts[k]) {
    					generateCrown(worldIn,tmp,branchesLenghts[k]/2+rand.nextInt(3));
    					setBlockAndNotifyAdequately(worldIn,tmp,KCore.MysticLog.getDefaultState());

    				}
    			}
    		}
    		if(branchesDirections[k]==1) {
    			for(int c=1;c<=branchesLenghts[k];c++) {
    				BlockPos tmp = new BlockPos(position.getX()-c,position.getY()+branchesHeights[k]+c,position.getZ());
    				setBlockAndNotifyAdequately(worldIn,tmp,log);
    				if(c==branchesLenghts[k]) {
    					generateCrown(worldIn,tmp,branchesLenghts[k]/2+rand.nextInt(3));
    					setBlockAndNotifyAdequately(worldIn,tmp,KCore.MysticLog.getDefaultState());

    				}
    			}
    		}
    		if(branchesDirections[k]==2) {
    			for(int c=1;c<=branchesLenghts[k];c++) {
    				BlockPos tmp = new BlockPos(position.getX(),position.getY()+branchesHeights[k]+c,position.getZ()+c);
    				setBlockAndNotifyAdequately(worldIn,tmp,log);
    				if(c==branchesLenghts[k]) {
    					generateCrown(worldIn,tmp,branchesLenghts[k]/2+rand.nextInt(3));
    					setBlockAndNotifyAdequately(worldIn,tmp,KCore.MysticLog.getDefaultState());

    				}
    			}
    		}
    		if(branchesDirections[k]==3) {
    			for(int c=1;c<=branchesLenghts[k];c++) {
    				BlockPos tmp = new BlockPos(position.getX(),position.getY()+branchesHeights[k]+c,position.getZ()-c);
    				setBlockAndNotifyAdequately(worldIn,tmp,log);
    				if(c==branchesLenghts[k]) {
    					generateCrown(worldIn,tmp,branchesLenghts[k]/2+rand.nextInt(3));
    					setBlockAndNotifyAdequately(worldIn,tmp,KCore.MysticLog.getDefaultState());
    				}
    			}
    		}
    	}
    	if(shouldGenMainCrown) {
			generateCrown(worldIn,new BlockPos(position.getX(),position.getY()+treeHeight+1,position.getZ()),treeHeight/2-1);
			setBlockAndNotifyAdequately(worldIn,new BlockPos(position.getX(),position.getY()+treeHeight+1,position.getZ()),KCore.MysticLog.getDefaultState());

    	}
    	
    	//////////////////////////////////////////////////
    	
    	
    	return true;
    	}
    }
    
    private void generateCrown(World world,BlockPos pos,int radius) {
    	IBlockState leaves = KCore.MysticLeaves.getDefaultState();
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			for(int y=0;y<=radius/2;y++) {
    				if(((x*x)+(z*z)+(y*2*y*2)<=(radius*radius))){
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());

    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), KCore.MysticLeaves.getDefaultState());

    				}
    			}
    		}
    	}
    }

    
    
    /**
     * returns whether or not a tree can grow into a block
     * For example, a tree will not grow into stone
     */
    protected boolean canGrowInto(Block blockType)
    {
        Material material = blockType.getDefaultState().getMaterial();
        return material == Material.AIR || material == Material.LEAVES || blockType == Blocks.GRASS || blockType == Blocks.DIRT || blockType == Blocks.LOG || blockType == Blocks.LOG2 || blockType == Blocks.SAPLING || blockType == Blocks.VINE ||
        		blockType == KCore.CorruptedDirt || blockType == KCore.CorruptedGrass || blockType == KCore.MysticLog;
    }

    public void generateSaplings(World worldIn, Random random, BlockPos pos)
    {
    }

    /**
     * sets dirt at a specific location if it isn't already dirt
     */
    protected void setDirtAt(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() != KCore.CorruptedDirt)
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, KCore.CorruptedDirt.getDefaultState());
        }
    }
    

    public boolean isReplaceable(World world, BlockPos pos)
    {
        net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getBlock().isWood(world, pos) || canGrowInto(state.getBlock());
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}
