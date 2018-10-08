package com.Krevik.Gens.Forest;

import java.util.Random;

import com.Krevik.Blocks.BlockLuminescentGnarl;
import com.Krevik.Blocks.BlockMysticLog;
import com.Krevik.Main.KCore;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenHugeKathairisTree extends WorldGenAbstractTree{
	
	public WorldGenHugeKathairisTree() {
		super(true);
	}
	Random random = new Random();
	int baseTreeHeight=10+random.nextInt(7);
	
	@Override
    public boolean generate(World worldIn, Random random, BlockPos pos)
    {
    	int posX=pos.getX();
    	int posZ=pos.getZ();
    	int posY=pos.getY();
    	if(posY>200||!worldIn.isAirBlock(pos)||worldIn.getBlockState(pos.down())!=KCore.CorruptedGrass.getDefaultState()||
    			worldIn.getBlockState(pos.down())==KCore.MysticLeaves.getDefaultState()) {
    		return false;
    	}else {
    		generateTree(worldIn,random,new BlockPos(posX,posY,posZ));
    		return true;
    	}

    }
	
	private void doTrunkPieceFromHeight(World world,BlockPos pos,IBlockState state) {
		int groundHeight=world.getHeight(pos).getY();
		if(pos.getY()-groundHeight<6) {
			if (pos.getY() - groundHeight > 2) {
				for (int c = pos.getY(); c >= groundHeight; c--) {
					this.setBlock(world, new BlockPos(pos.getX(), c - 1, pos.getZ()), state);
				}
			} else {
				for (int c = pos.getY(); c >= groundHeight; c--) {
					this.setBlock(world, new BlockPos(pos.getX(), c, pos.getZ()), state);
				}
			}
		}

	}
	
	private void placeGnalr(World world,BlockPos position,int x,int z) {
			if(x<0&&z>0) {
				this.setBlockAndNotifyAdequately(world, new BlockPos(position.getX(),position.getY(),position.getZ()), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.WEST));	
				world.updateBlockTick(position, KCore.LuminescentGnarl, 1, 2);
			}else if(x==0&&z>0) {
				this.setBlockAndNotifyAdequately(world, new BlockPos(position.getX(),position.getY(),position.getZ()), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.SOUTH));	
				world.updateBlockTick(position, KCore.LuminescentGnarl, 1, 2);
			}else if(x>0&&z>0) {
				this.setBlockAndNotifyAdequately(world, new BlockPos(position.getX(),position.getY(),position.getZ()), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.EAST));	
				world.updateBlockTick(position, KCore.LuminescentGnarl, 1, 2);
			}else if(x<0&&z<0) {
				this.setBlockAndNotifyAdequately(world, new BlockPos(position.getX(),position.getY(),position.getZ()), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.NORTH));	
				world.updateBlockTick(position, KCore.LuminescentGnarl, 1, 2);
			}
	}
	
	private void generateTree(World worldIn, Random rand, BlockPos position) {
		IBlockState logState = KCore.MysticLog.getDefaultState();
		IBlockState leavesState = KCore.MysticLeaves.getDefaultState();
		int Y=position.getY();
		int X=position.getX();
		int Z=position.getZ();
		//rots start
		for(int rootsNumber=0;rootsNumber<4;rootsNumber++) {
			int length=2+rand.nextInt(4);
			BlockPos root=new BlockPos(position.getX(),position.getY(),position.getZ());
			if(rootsNumber==0) {
				outerloop: for(int shift=1;shift<=length;shift++) {
					int groundHeight=worldIn.getHeight(root).getY();
					if((root.getY()+length-shift)-groundHeight>5) {
						break outerloop;
					}else {
						doTrunkPieceFromHeight(worldIn, new BlockPos(root.getX() - shift, root.getY() + length - shift, root.getZ() - shift), logState);
					}
				}
			}
			if(rootsNumber==1) {
				outerloop: for(int shift=1;shift<=length;shift++) {
					int groundHeight=worldIn.getHeight(root).getY();

					if((root.getY()+length-shift)-groundHeight>5) {
						break outerloop;
					}else {
						doTrunkPieceFromHeight(worldIn, new BlockPos(root.getX() - shift, root.getY() + length - shift, root.getZ() + 1 + shift), logState);
					}
				}
			}
			if(rootsNumber==2) {
				outerloop: for(int shift=1;shift<=length;shift++) {
					int groundHeight=worldIn.getHeight(root).getY();

					if((root.getY()+length-shift)-groundHeight>5) {
						break outerloop;
					}
					else {
						doTrunkPieceFromHeight(worldIn, new BlockPos(root.getX() + 1 + shift, root.getY() + length - shift, root.getZ() + 1 + shift), logState);
					}
				}
			}
			if(rootsNumber==3) {
				outerloop: for(int shift=1;shift<=length;shift++) {
					int groundHeight=worldIn.getHeight(root).getY();

					if((root.getY()+length-shift)-groundHeight>5) {
						break outerloop;
					}else {
						doTrunkPieceFromHeight(worldIn, new BlockPos(root.getX() + 1 + shift, root.getY() + length - shift, root.getZ() - shift), logState);
					}
				}
			}
		}
		//roots end
		
		//base trunk start
		for(int k=0;k<4;k++) {
			for(int y=0;y<=baseTreeHeight;y++) {
				if(k==0) {
					this.setBlock(worldIn, logState, new BlockPos(X,Y+y,Z));
					if(random.nextInt(45)==0) {placeGnalr(worldIn,new BlockPos(X-1,Y+y,Z),-1,1);}
				}
				if(k==1) {
					this.setBlock(worldIn, logState, new BlockPos(X,Y+y,Z+1));
					if(random.nextInt(45)==0) {placeGnalr(worldIn,new BlockPos(X,Y+y,Z+2),0,+2);}
				}
				if(k==2) {
					this.setBlock(worldIn, logState, new BlockPos(X+1,Y+y,Z+1));
					if(random.nextInt(45)==0) {placeGnalr(worldIn,new BlockPos(X+2,Y+y,Z+1),+2,+2);}
				}
				if(k==3) {
					this.setBlock(worldIn, logState, new BlockPos(X+1,Y+y,Z));
					if(random.nextInt(45)==0) {placeGnalr(worldIn,new BlockPos(X-1,Y+y,Z-1),-1,-1);}
				}
			}
		}
		//base trunk end
		
		//branch start
		for(int k=0;k<4;k++) {
			int branchHeight=5+random.nextInt(5);
			for(int y=1;y<=branchHeight;y++) {
				if(k==0) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X-y,Y+baseTreeHeight+y,Z-y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z-2));
						doCrown(worldIn,leavesState,3+random.nextInt(4), new BlockPos(X-2,Y+baseTreeHeight+y,Z-2));
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z-2));
					}
				}
				if(k==1) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X-y,Y+baseTreeHeight+y,Z+1+y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z+3));
						doCrown(worldIn,leavesState,3+random.nextInt(4),new BlockPos(X-2,Y+baseTreeHeight+y,Z+3));
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z+3));
					}
				}
				if(k==2) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X+1+y,Y+baseTreeHeight+y,Z+1+y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z+3));
						doCrown(worldIn,leavesState,3+random.nextInt(4),new BlockPos(X+3,Y+baseTreeHeight+y,Z+3));
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z+3));
					}
				}
				if(k==3) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X+1+y,Y+baseTreeHeight+y,Z-y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z-2));
						doCrown(worldIn,leavesState,3+random.nextInt(4),new BlockPos(X+3,Y+baseTreeHeight+y,Z-2));
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z-2));
					}
				}
			}
		}
		//brach end
		
	}
	
	private void doCrown(World worldIn,IBlockState leavesState,int radius,BlockPos pos) {
		IBlockState logState = KCore.MysticLog.getDefaultState();
    	for(int x=0;x<=(radius+1);x++) {
    		for(int z=0;z<=(radius+1);z++) {
    			for(int y=0;y<=(radius+1)/2;y++) {
    				if(((x*x)+(z*z)+(y*2*y*2)<(radius*radius-radius))){
    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), logState);}
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), logState);}
    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), logState);}
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), logState);}

    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), logState);}
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), logState);}
    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), logState);}
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), leavesState);
    					if(random.nextInt(5)==0) {this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), logState);}
    				}
    				else if(((x*x)+(z*z)+(y*2*y*2)<=(radius*radius))){
    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), leavesState);
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), leavesState);
    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), leavesState);
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), leavesState);

    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), leavesState);
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), leavesState);
    					this.setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), leavesState);
    					this.setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), leavesState);
    				}
    			}
    		}
    	}
    	//placeVines(worldIn,radius,pos);
	}
	
	void placeVines(World worldIn, int radius, BlockPos pos) {
    	for(int x=0;x<=(radius+2);x++) {
    		for(int z=0;z<=(radius+2);z++) {
    			for(int y=0;y<=(radius-2)/2;y++) {
    				
    			}
    		}
    	}
	}
	
	void setBlock(World worldIn,BlockPos pos,IBlockState state) {
		if(worldIn.isAirBlock(pos)||(worldIn.getBlockState(pos).getBlock() instanceof BlockLeaves&&state.getBlock() instanceof BlockMysticLog)) {
			worldIn.setBlockState(pos, state, 2);
		}

	}
	
	void setBlock(World worldIn,IBlockState state,BlockPos pos) {
		this.setBlock(worldIn, pos, state);
	}
}
