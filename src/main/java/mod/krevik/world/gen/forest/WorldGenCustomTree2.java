package mod.krevik.world.gen.forest;

import java.util.Random;

import mod.krevik.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenCustomTree2 extends WorldGenAbstractTree{

	Random random = new Random();
	protected int treeHeight=5+random.nextInt(15);
	
	public WorldGenCustomTree2() {
		super(false);
	}
	
    public boolean generate(World worldIn, Random random, BlockPos pos)
    {
    	int posX=pos.getX();
    	int posZ=pos.getZ();
    	int posY=pos.getY();
    	if(posY>200||!worldIn.isAirBlock(pos)||worldIn.getBlockState(pos.down())!= KCore.CorruptedGrass.getDefaultState()||
    			worldIn.getBlockState(pos.down())==KCore.MysticLeaves.getDefaultState()) {
    		return false;
    	}else {
    		generateTree(worldIn,random,new BlockPos(posX,posY,posZ));
    		return true;

    	}

    }
    
    private void generateTree(World world, Random random, BlockPos pos) {
    	
    	for(int c=0;c<=treeHeight;c++) {
    		this.setBlockAndNotifyAdequately(world, new BlockPos(pos.getX(),pos.getY()+c,pos.getZ()), KCore.MysticLog.getDefaultState());
    	}
    	
    	
    	generateCrown(world,new BlockPos(pos.getX(),pos.getY()+treeHeight,pos.getZ()),1+(treeHeight/4));
		this.setBlockAndNotifyAdequately(world, new BlockPos(pos.getX(),pos.getY()+treeHeight,pos.getZ()), KCore.MysticLog.getDefaultState());
    	
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
    	return false;
    }

    public void generateSaplings(World worldIn, Random random, BlockPos pos)
    {
    }

    /**
     * sets dirt at a specific location if it isn't already dirt
     */
    protected void setDirtAt(World worldIn, BlockPos pos)
    {

    }

    public boolean isReplaceable(World world, BlockPos pos)
    {
    	return false;
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

}
