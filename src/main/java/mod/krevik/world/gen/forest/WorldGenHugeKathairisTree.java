package mod.krevik.world.gen.forest;

import mod.krevik.KCore;
import mod.krevik.block.BlockMysticLog;
import mod.krevik.block.plants.BlockGlowVines;
import mod.krevik.block.plants.BlockLuminescentGnarl;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

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
    	if(posY>200||!worldIn.isAirBlock(pos)||worldIn.getBlockState(pos.down())!= KCore.KatharianGrass.getDefaultState()||
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
	
	private void generateTree(World worldIn, Random random, BlockPos position) {
		IBlockState logState = KCore.MysticLog.getDefaultState();
		IBlockState leavesState = KCore.MysticLeaves.getDefaultState();
		int Y=position.getY();
		int X=position.getX();
		int Z=position.getZ();
		//rots start
		for(int rootsNumber=0;rootsNumber<4;rootsNumber++) {
			int length=2+random.nextInt(4);
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
			for(int y=0;y<=baseTreeHeight+1;y++) {
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
		if(random.nextInt(5)==0)tryToGenerateVinesAtBlockPos(worldIn, new BlockPos(X, Y + baseTreeHeight, Z), random, true);
		if(random.nextInt(5)==0)tryToGenerateVinesAtBlockPos(worldIn, new BlockPos(X, Y + baseTreeHeight, Z + 1), random, true);
		if(random.nextInt(5)==0)tryToGenerateVinesAtBlockPos(worldIn, new BlockPos(X + 1, Y + baseTreeHeight, Z + 1), random, true);
		if(random.nextInt(5)==0)tryToGenerateVinesAtBlockPos(worldIn, new BlockPos(X + 1, Y + baseTreeHeight, Z), random, true);

		//base trunk end
		
		//branch start
		int branchHeight=5+random.nextInt(5);
		for(int k=0;k<4;k++) {
			branchHeight=5+random.nextInt(5);
			for(int y=1;y<=branchHeight;y++) {
				if(k==0) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X-y,Y+baseTreeHeight+y,Z-y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z-2));
						doCrown(worldIn,leavesState,3+random.nextInt(4), new BlockPos(X-2,Y+baseTreeHeight+y,Z-2),random);
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z-2));
					}
				}
				if(k==1) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X-y,Y+baseTreeHeight+y,Z+1+y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z+3));
						doCrown(worldIn,leavesState,3+random.nextInt(4),new BlockPos(X-2,Y+baseTreeHeight+y,Z+3),random);
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X-2,Y+baseTreeHeight+y,Z+3));
					}
				}
				if(k==2) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X+1+y,Y+baseTreeHeight+y,Z+1+y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z+3));
						doCrown(worldIn,leavesState,3+random.nextInt(4),new BlockPos(X+3,Y+baseTreeHeight+y,Z+3),random);
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z+3));
					}
				}
				if(k==3) {
					if(y==1||y==2) {
						this.setBlock(worldIn, logState, new BlockPos(X+1+y,Y+baseTreeHeight+y,Z-y));
					}else if(y==branchHeight){
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z-2));
						doCrown(worldIn,leavesState,3+random.nextInt(4),new BlockPos(X+3,Y+baseTreeHeight+y,Z-2),random);
					}else {
						this.setBlock(worldIn, logState, new BlockPos(X+3,Y+baseTreeHeight+y,Z-2));
					}
				}
			}
		}
		//brach end
		
	}
	
	private void doCrown(World worldIn,IBlockState leavesState,int radius,BlockPos pos,Random random) {
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

	private void tryToGenerateVinesAtBlockPos(World world,BlockPos pos,Random random,boolean grow){
		for(int x=-1;x<=1;x++) {
			for (int z = -1; z <= 1; z++) {
					BlockPos blockPos1 = new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()+z);
					BlockPos blockPos2 = new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()+z);
					BlockPos blockPos3 = new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()-z);
					BlockPos blockPos4 = new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()-z);
					tryToPutVines(world,blockPos1,random,grow);
					tryToPutVines(world,blockPos2,random,grow);
					tryToPutVines(world,blockPos3,random,grow);
					tryToPutVines(world,blockPos4,random,grow);
			}
		}
	}

	boolean canVinesStayAtPos(World worldIn, BlockPos pos){
		boolean can=false;
		if(!worldIn.isAirBlock(pos.east())&&worldIn.getBlockState(pos.east()).isFullBlock()){
			can=true;
		}
		if(!worldIn.isAirBlock(pos.west())&&worldIn.getBlockState(pos.west()).isFullBlock()){
			can=true;
		}
		if(!worldIn.isAirBlock(pos.south())&&worldIn.getBlockState(pos.south()).isFullBlock()){
			can=true;
		}
		if(!worldIn.isAirBlock(pos.north())&&worldIn.getBlockState(pos.north()).isFullBlock()){
			can=true;
		}
		if(!worldIn.isAirBlock(pos.up())) {
			if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines) {
				BlockGlowVines.EnumType upperVariant = worldIn.getBlockState(pos.up()).getValue(BlockGlowVines.VARIANT);
                can = upperVariant != BlockGlowVines.EnumType.BOTTOM;
			}
		}
		return can;
	}
	void tryToPutVines(World world,BlockPos pos,Random random,boolean grow){
		if(world.isAirBlock(pos)&&canVinesStayAtPos(world,pos)){
			world.setBlockState(pos, KCore.glowvines.getDefaultState());
			if(grow){
				for(int y=0;y>=-random.nextInt(10);y--){
					BlockPos pos1 = pos.down(-y);
					if(world.isAirBlock(pos1)&&canVinesStayAtPos(world,pos1)){
						world.setBlockState(pos1,KCore.glowvines.getDefaultState());
						world.getBlockState(pos1).getBlock().updateTick(world,pos1,world.getBlockState(pos1),random);
					}
				}
			}
		}
		if(world.isAirBlock(pos)&&canVinesStayAtPos(world,pos)){
			world.setBlockState(pos, KCore.glowvines.getDefaultState());
			if(grow){
				for(int y=0;y>=-random.nextInt(10);y--){
					BlockPos pos1 = pos.down(-y);
					if(world.isAirBlock(pos1)&&canVinesStayAtPos(world,pos1)){
						world.setBlockState(pos1,KCore.glowvines.getDefaultState());
						world.getBlockState(pos1).getBlock().updateTick(world,pos1,world.getBlockState(pos1),random);
					}
				}
			}
		}
		if(world.isAirBlock(pos)&&canVinesStayAtPos(world,pos)){
			world.setBlockState(pos, KCore.glowvines.getDefaultState());
			if(grow){
				for(int y=0;y>=-random.nextInt(10);y--){
					BlockPos pos1 = pos.down(-y);
					if(world.isAirBlock(pos1)&&canVinesStayAtPos(world,pos1)){
						world.setBlockState(pos1,KCore.glowvines.getDefaultState());
						world.getBlockState(pos1).getBlock().updateTick(world,pos1,world.getBlockState(pos1),random);
					}
				}
			}
		}
		if(world.isAirBlock(pos)&&canVinesStayAtPos(world,pos)){
			world.setBlockState(pos, KCore.glowvines.getDefaultState());
			if(grow){
				for(int y=0;y>=-random.nextInt(10);y--){
					BlockPos pos1 = pos.down(-y);
					if(world.isAirBlock(pos1)&&canVinesStayAtPos(world,pos1)){
						world.setBlockState(pos1,KCore.glowvines.getDefaultState());
						world.getBlockState(pos1).getBlock().updateTick(world,pos1,world.getBlockState(pos1),random);
					}
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
