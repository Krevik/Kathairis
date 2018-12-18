package com.krevik.Gens;

import java.util.Random;

import com.krevik.Main.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldGenFloatingIslands extends WorldGenerator{
	int weight=1;
	public WorldGenFloatingIslands(int w) {
		weight=w;
	}
	
	
    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
    	this.generateIsland(worldIn, rand, pos);
    	
    	
    	return true;
    }
    boolean isStructuresIsland=false;
    boolean isTempleGenerated=false;
    
    private void generateIsland(World worldIn,Random rand,BlockPos pos) {
    	int mode=0;
    			if(mode==0) {
    		    	if(rand.nextInt(15)==0) {
    		    		isStructuresIsland=true;
    		    	}
    		    	int forz=0;
    		    	int maxForZ=0;
    		    	int k=20+rand.nextInt(30);
    		    	for(int width=0;width<=k;width++) {
    		    		if(width>(k/2)) {
    		    			forz-=rand.nextInt(3);
    		    		}else {
    		    			forz+=rand.nextInt(3);
    		    		}
    		    		if(forz>maxForZ) {
    		    			maxForZ=forz;
    		    		}
    		    		generateLayer(worldIn,rand,new BlockPos(pos.getX()+width,pos.getY(),pos.getZ()),forz,width,k);
    		    	}
    			}
    		
    	
    	
    }
    private void generateLayer(World world,Random rand,BlockPos pos,int forz,int width,int maxWidth) {
    	IBlockState stoneState = KCore.MythicStone.getDefaultState();
    	IBlockState grassState = KCore.CorruptedGrass.getDefaultState();
    	IBlockState dirtState = KCore.CorruptedDirt.getDefaultState();
    	BlockPos tmp;
    	int chance=5;
    	for(int c=0;c<=forz;c++) {
    		int height = (int) ((int) ((int) 1+rand.nextInt(3)+(MathHelper.sqrt(maxWidth*forz)))-MathHelper.sqrt(width*c));
    		for(int y=0;y<=height;y++) {
    			int dirtHeight=1+rand.nextInt(4);
    			if(y==0) {
            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-c);
            		setBlock(world,tmp,grassState);
            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+c);
            		setBlock(world,tmp,grassState);
            		if(isStructuresIsland) {
            			if(c<forz&&c>-forz) {
	            			if(rand.nextInt(50)==0&&!isTempleGenerated) {
	            				isTempleGenerated=true;
	            				if(rand.nextInt(2)==0) {
	                        		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-c);
	            				}else {
	                        		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+c);
	            				}
	            				generateTemple(world,tmp.up());
	            			}
            			}
            		}
    			}
    			else if(y!=0&&y<=dirtHeight) {
            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-c);
            		setBlock(world,tmp,dirtState);
            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+c);
            		setBlock(world,tmp,dirtState);
    			}
    			else if(y==height) {
    				if(c>4&&rand.nextInt(13+chance)==0) {
    					if(rand.nextInt(2)==0) {
    	            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-c);
    					}else {
    	            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+c);
    					}
	    				generateMagnethiumCrystals(world,rand,tmp,maxWidth,forz);

	    				chance+=50;
    				}
    			}
    			else {
            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-c);
            		setBlock(world,tmp,stoneState);
            		tmp=new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+c);
            		setBlock(world,tmp,stoneState);
    			}

    		}

    	}
    	
    }
    
    private void generateTemple(World world,BlockPos position) {
		world.getChunkProvider().provideChunk(position.getX(), position.getZ());
		WorldServer worldserver = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(KCore.MODID+":stonetemple"));
		
		if(template == null)
		{
			System.out.println("NO STRUCTURE");
		}
			int xSize = template.getSize().getX();
			int ySize = template.getSize().getY();
			int zSize = template.getSize().getZ();

			if(world.isAirBlock(position)&&world.isAirBlock(position.add(xSize, 0, 0))&&world.isAirBlock(position.add(0,ySize,0))&&
					world.isAirBlock(position.add(0, 0, zSize))&&world.isAirBlock(position.add(xSize, 0, zSize))&&world.isAirBlock(position.add(xSize, ySize, zSize))) {
				IBlockState iblockstate = world.getBlockState(position);
				world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);
				PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
						.setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos) null)
						.setReplacedBlock((Block) null).setIgnoreStructureBlock(true);
				template.addBlocksToWorld(world, position, placementsettings);
			}
    }
    
    private void generateMagnethiumCrystals(World world,Random random,BlockPos pos,int maxWidth,int maxLength) {
    	int branches=1+random.nextInt(4);
    	IBlockState magnethiumState = KCore.Magnethium.getDefaultState();
    	BlockPos tmp;
    	
    	for(int c=0;c<=branches;c++) {
    		int shiftX=0;
    		int shiftZ=0;
    		int shiftY=0;
    		int branchLength=4+random.nextInt((maxWidth+maxLength)/6);
    		for(int length=0;length<=branchLength;length++) {
    			int decreaser = 2+random.nextInt(7);
	    		if(c==0) {
	    			shiftX=shiftX+random.nextInt(3)-random.nextInt(2);
	    			shiftZ=shiftZ+random.nextInt(3)-random.nextInt(2);
	    		}
	    		if(c==1) {
	    			shiftX=shiftX+random.nextInt(2)-random.nextInt(3);
	    			shiftZ=shiftZ+random.nextInt(3)-random.nextInt(2);
	    		}
	    		if(c==2) {
	    			shiftX=shiftX+random.nextInt(3)-random.nextInt(2);
	    			shiftZ=shiftZ+random.nextInt(2)-random.nextInt(3);
	    		}
	    		if(c==3) {
	    			shiftX=shiftX+random.nextInt(2)-random.nextInt(3);
	    			shiftZ=shiftZ+random.nextInt(2)-random.nextInt(3);
	    		}
	    		shiftY--;
	    		tmp=new BlockPos(pos.getX()+shiftX,pos.getY()+shiftY,pos.getZ()+shiftZ);
	    		setSphere(world,tmp,magnethiumState,1+random.nextInt(branchLength-length+1)/4);
    		}
    	}
    }
    
    private void setSphere(World world,BlockPos pos,IBlockState state,int radius) {
    	for(int x=-radius;x<=radius;x++) {
    		for(int z=-radius;z<=radius;z++) {
    			for(int y=-radius;y<=radius;y++) {
    				if((x*x)+(y*y)+(z*z)<=(radius*radius)) {
    					setBlock(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z),state);
    				}
    			}
    		}
    	}
    }
    
    private void generateDetails(World worldIn,Random rand,BlockPos pos,int radius) {
    	BlockPos startPos=getDetailStartPos(pos,radius,rand);
    	BlockPos tmp;
    	IBlockState fillerState = KCore.CorruptedGrass.getDefaultState();
    	int newRadius=1+rand.nextInt(radius);
    	for(int x=0;x<=newRadius;x++) {
    		for(int z=0;z<=newRadius;z++) {
    			for(int y=0;y<=(newRadius-MathHelper.sqrt((x*x)+(z*z)+1))*1.5;y++) {
        			tmp=new BlockPos(startPos.getX()+x,startPos.getY()+y,startPos.getZ()+z);
        			setBlock(worldIn,tmp,fillerState);
        			tmp=new BlockPos(startPos.getX()-x,startPos.getY()+y,startPos.getZ()+z);
        			setBlock(worldIn,tmp,fillerState);
        			tmp=new BlockPos(startPos.getX()+x,startPos.getY()+y,startPos.getZ()-z);
        			setBlock(worldIn,tmp,fillerState);
        			tmp=new BlockPos(startPos.getX()-x,startPos.getY()+y,startPos.getZ()-z);
        			setBlock(worldIn,tmp,fillerState);
    			}
    		}
    	}
    	
    }
    
    private BlockPos getDetailStartPos(BlockPos pos,int radius,Random random) {
    	BlockPos tmp;
    	int X;
    	int Y=pos.getY();
    	int Z;
    	X=KCore.functionHelper.getRandomInteger(0, radius-3);
    	if(random.nextInt(2)==0) {
    		X=-X;
    	}
    	Z=KCore.functionHelper.getRandomInteger(0, radius-3);
    	if(random.nextInt(2)==0) {
    		Z=-X;
    	}
    	tmp=new BlockPos(pos.getX()+X,Y,pos.getZ()+Z);
    	return tmp;
    }
    
    private void setBlock(World worldIn, BlockPos pos,IBlockState state) {
        	worldIn.setBlockState(pos, state,2);
    }
    
    
    /*private void generateIsland(World worldIn,Random rand,BlockPos pos) {
    	int radius=6+rand.nextInt(12);
    	BlockPos tmp = new BlockPos(0,0,0);
		IBlockState stoneState = KCore.MythicStone.getDefaultState();
		IBlockState grassState = KCore.CorruptedGrass.getDefaultState();
		IBlockState dirtState = KCore.CorruptedDirt.getDefaultState();
		IBlockState magnethiumState = KCore.Magnethium.getDefaultState();
		int randomik = 0;				
		int[] addx = new int [4];		
		int[] addz = new int [4];
		for(int i=0; i<4; i++){
		    addx[i]=0;
		    addz[i]=0;
		}
    	for(int x=0;x<=radius;x++) {
    		randomik = rand.nextInt(16); 	
    		for(int i=0; i<4; i++){ 	 
    		    addx[i]+=1-((randomik>>i)%3)%2;	
    			addz[i]=0;               	
    		}
        	for(int z=0;z<=radius;z++) {
        		randomik = rand.nextInt(16);
        		for(int i=0; i<4; i++){ 	 
        		    addz[i]+=1-((randomik>>i)%3)%2;
        		}
            	for(int y=0;y<=1+rand.nextInt(4)+(radius-MathHelper.sqrt((x*x)+(z*z)))*0.5;y++) {
            		if((x*x)*+z*z<=(radius*radius)) {
	            			if(y==0) {
	                			tmp=new BlockPos(pos.getX()+addx[0],pos.getY()-y,pos.getZ()+addz[0]);
	                			setBlock(worldIn,tmp,grassState);
	                			tmp=new BlockPos(pos.getX()+addx[1],pos.getY()-y,pos.getZ()-addz[1]);
	                			setBlock(worldIn,tmp,grassState);
	                			tmp=new BlockPos(pos.getX()-addx[2],pos.getY()-y,pos.getZ()+addz[2]);
	                			setBlock(worldIn,tmp,grassState);
	                			tmp=new BlockPos(pos.getX()-addx[3],pos.getY()-y,pos.getZ()-addz[3]);
	                			setBlock(worldIn,tmp,grassState);
	            			}else {
	                			tmp=new BlockPos(pos.getX()+addx[0],pos.getY()-y,pos.getZ()+addz[0]);
	                			setBlock(worldIn,tmp,stoneState);
	                			tmp=new BlockPos(pos.getX()-addx[1],pos.getY()-y,pos.getZ()+addz[1]);
	                			setBlock(worldIn,tmp,stoneState);
	                			tmp=new BlockPos(pos.getX()+addx[2],pos.getY()-y,pos.getZ()-addz[2]);
	                			setBlock(worldIn,tmp,stoneState);
	                			tmp=new BlockPos(pos.getX()-addx[3],pos.getY()-y,pos.getZ()-addz[3]);
	                			setBlock(worldIn,tmp,stoneState);
	            			}

            		}else {
            			
                		
            		}
            		
            	}
        	}
    	}
    	
    	
    	if(rand.nextInt(5)==0) {
    		weight++;
    	}
    	
    	if(rand.nextInt(weight)==0) {
    		int k=rand.nextInt(8);
    		if(k==0)this.generateIsland(worldIn, rand, new BlockPos(pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()+radius));
    		if(k==1)this.generateIsland(worldIn, rand, new BlockPos(pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()-radius));
    		if(k==2)this.generateIsland(worldIn, rand, new BlockPos(radius+pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()));
    		if(k==3)this.generateIsland(worldIn, rand, new BlockPos(radius-pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()));
    		if(k==4)this.generateIsland(worldIn, rand, new BlockPos(radius+pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()+radius));
    		if(k==5)this.generateIsland(worldIn, rand, new BlockPos(radius-pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()-radius));
    		if(k==6)this.generateIsland(worldIn, rand, new BlockPos(radius+pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()-radius));
    		if(k==7)this.generateIsland(worldIn, rand, new BlockPos(radius-pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()+radius));
    	}	
    }
    */
    
    
    
   /* private void generateIsland(World worldIn,Random rand,BlockPos pos) {
    	int radius1=6+rand.nextInt(12);
    	int radius2=6+rand.nextInt(12);
		BlockPos tmp = new BlockPos(0,0,0);
		IBlockState stoneState = KCore.MythicStone.getDefaultState();
		IBlockState grassState = KCore.CorruptedGrass.getDefaultState();
		IBlockState dirtState = KCore.CorruptedDirt.getDefaultState();
		IBlockState magnethiumState = KCore.Magnethium.getDefaultState();
		
    	for(int x=0;x<=radius1;x++) {
        	for(int z=0;z<=radius2;z++) {
            	for(int y=0;y<=1+rand.nextInt(4)+(radius1+radius2-MathHelper.sqrt((x*x)+(z*z)))*0.5;y++) {
            		if((x*x)*(radius2*radius2)+z*z*(radius1*radius1)<=radius2*radius2*(radius1*radius1)) {
	            			if(y==0) {
	                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z);
	                			setBlock(worldIn,tmp,grassState);
	                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z);
	                			setBlock(worldIn,tmp,grassState);
	                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z);
	                			setBlock(worldIn,tmp,grassState);
	                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z);
	                			setBlock(worldIn,tmp,grassState);
	            			}else {
	                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z);
	                			setBlock(worldIn,tmp,stoneState);
	                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z);
	                			setBlock(worldIn,tmp,stoneState);
	                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z);
	                			setBlock(worldIn,tmp,stoneState);
	                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z);
	                			setBlock(worldIn,tmp,stoneState);
	            			}

            		}else {

            		}
            	}
        	}
    	}
    	
    	if(rand.nextInt(5)==0) {
    		weight++;
    	}
    	
    	if(rand.nextInt(weight)==0) {
    		int k=rand.nextInt(8);
    		if(k==0)this.generateIsland(worldIn, rand, new BlockPos(pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()+radius2));
    		if(k==1)this.generateIsland(worldIn, rand, new BlockPos(pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()-radius2));
    		if(k==2)this.generateIsland(worldIn, rand, new BlockPos(radius1+pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()));
    		if(k==3)this.generateIsland(worldIn, rand, new BlockPos(radius1-pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()));
    		if(k==4)this.generateIsland(worldIn, rand, new BlockPos(radius1+pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()+radius2));
    		if(k==5)this.generateIsland(worldIn, rand, new BlockPos(radius1-pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()-radius2));
    		if(k==6)this.generateIsland(worldIn, rand, new BlockPos(radius1+pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()-radius2));
    		if(k==7)this.generateIsland(worldIn, rand, new BlockPos(radius1-pos.getX(),pos.getY()+rand.nextInt(2)-rand.nextInt(2),pos.getZ()+radius2));
    	}	
    }
    
    
    */
    
    
   /* public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
    	int radius=6+rand.nextInt(12);
		BlockPos tmp = new BlockPos(0,0,0);
		IBlockState stoneState = KCore.MythicStone.getDefaultState();
		IBlockState grassState = KCore.CorruptedGrass.getDefaultState();
		IBlockState dirtState = KCore.CorruptedDirt.getDefaultState();
		int width=6+rand.nextInt(60);
		int legth=6+rand.nextInt(60);
    	for(int x=0;x<=radius;x++) {
        	for(int z=0;z<=radius;z++) {
            	for(int y=0;y<=1+rand.nextInt(4)+(radius-MathHelper.sqrt((x*x)+(z*z)))*1.5;y++) {
            		if((x*x)+(z*z)<=(radius*radius)) {
            			if(y==0) {
                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z);
                			setBlock(worldIn,tmp,grassState);
                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z);
                			setBlock(worldIn,tmp,grassState);
                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z);
                			setBlock(worldIn,tmp,grassState);
                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z);
                			setBlock(worldIn,tmp,grassState);
            			}else {
                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z);
                			setBlock(worldIn,tmp,stoneState);
                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z);
                			setBlock(worldIn,tmp,stoneState);
                			tmp=new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z);
                			setBlock(worldIn,tmp,stoneState);
                			tmp=new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z);
                			setBlock(worldIn,tmp,stoneState);
            			}
            		}
            	}
        	}
    	}
    	
    	return true;
    }
    */
}
