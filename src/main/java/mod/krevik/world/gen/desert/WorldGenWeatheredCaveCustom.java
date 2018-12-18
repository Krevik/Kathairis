package mod.krevik.world.gen.desert;

import java.util.Random;

import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenWeatheredCaveCustom {
    public WorldGenWeatheredCaveCustom(){
    }
    
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	initGenerating(worldIn,rand,position);
    	return true;
    }
    
    private BlockPos getTopSandBlock(World worldIn,BlockPos position){
    	int top=0;
        	for(top=50;top<256;top++){
        		if(worldIn.getBlockState(new BlockPos(position.getX(),top,position.getZ()))== KCore.ForgottenSand.getDefaultState()||
        				worldIn.getBlockState(new BlockPos(position.getX(),top,position.getZ()))==KCore.MovingSand){
        			break;
        		}
        	}
    	return new BlockPos(position.getX(),top,position.getZ());
    }
    
    private void digBlock(World world, BlockPos pos) {
    	world.setBlockToAir(pos);
    }
    
    private void replaceBlockWith(World world, BlockPos pos, IBlockState state) {
    	if(!world.isAirBlock(pos)) {
        	world.setBlockState(pos, state);	
    	}
    }
    private void digCircleVertical(World world,Random random,BlockPos pos,int radius) {
    	
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			
    			if(x*x+z*z<=(radius+1)*radius) {
    			int xx=x+1, zz=z+1;
    			if(xx*xx+zz*zz<=(radius+1)*radius) {
    			digBlock(world,new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()+z));
    			digBlock(world,new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()-z));
    			digBlock(world,new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()+z));
    			digBlock(world,new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()-z));
    			}
    			else {
    				if(random.nextInt(50)==0) {
    					replaceBlockWith(world,new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()+z), KCore.GemsOre.getDefaultState());
    	    			replaceBlockWith(world,new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()-z), KCore.GemsOre.getDefaultState());
    	    			replaceBlockWith(world,new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()+z), KCore.GemsOre.getDefaultState());
    	    			replaceBlockWith(world,new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()-z), KCore.GemsOre.getDefaultState());
    				}else {
    	    			replaceBlockWith(world,new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()+z), KCore.WeatheredRock.getDefaultState());
    	    			replaceBlockWith(world,new BlockPos(pos.getX()+x,pos.getY(),pos.getZ()-z), KCore.WeatheredRock.getDefaultState());
    	    			replaceBlockWith(world,new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()+z), KCore.WeatheredRock.getDefaultState());
    	    			replaceBlockWith(world,new BlockPos(pos.getX()-x,pos.getY(),pos.getZ()-z), KCore.WeatheredRock.getDefaultState());}	
    				}

    			}
    		}
    	}
    }
    
    private void digCircleHorizontal(World world,Random random,BlockPos pos,int radius) {
    	
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			if(x*x+z*z<=(radius+1)*radius) {
    				int xx=x+1, zz=z+1;
        		if(xx*xx+zz*zz<=(radius+1)*radius) {
        			digBlock(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()+z));
    				digBlock(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()-z));
    				digBlock(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()+z));
    				digBlock(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()-z));
        			}
    			else {
    				if(random.nextInt(10)==0){
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()+z), KCore.ShinyRock.getDefaultState());
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()-z), KCore.ShinyRock.getDefaultState());
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()+z), KCore.ShinyRock.getDefaultState());
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()-z), KCore.ShinyRock.getDefaultState());
        				if(random.nextInt(50)==0) {
                			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()+z), KCore.GemsOre.getDefaultState());
                			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()-z), KCore.GemsOre.getDefaultState());
                			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()+z), KCore.GemsOre.getDefaultState());
                			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()-z), KCore.GemsOre.getDefaultState());
        				}
    				}else
    				{
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()+z), KCore.WeatheredRock.getDefaultState());
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()+x,pos.getZ()-z), KCore.WeatheredRock.getDefaultState());
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()+z), KCore.WeatheredRock.getDefaultState());
            			replaceBlockWith(world,new BlockPos(pos.getX(),pos.getY()-x,pos.getZ()-z), KCore.WeatheredRock.getDefaultState());}	
    				}

        			}
    			}
    		}
    	}
    private void initGenerating(World world, Random random, BlockPos pos) {
    	int StartHeight = getTopSandBlock(world,pos).getY();
    	int CaveHeight=30;
    	int EndHeight = StartHeight-CaveHeight;
    	int x=0,y=EndHeight;
    	int revertY=EndHeight-y;
    	int CaveLenght=random.nextInt(15)+15;
    	int radius=3;
    	while(!world.isAirBlock(new BlockPos(pos.getX()+x,y,pos.getZ()))){
    		
    		digCircleVertical(world,random,new BlockPos(pos.getX()+x,y,pos.getZ()),radius);
    		
    		if(random.nextInt(x+1)==0) {
    		x++;
    		}
    		y++;
    		if(radius<7) {
    		if(random.nextInt(10)==0) {
    			radius++;
    		}
    		}
    	}
    	int r2=3;
    	if(EndHeight<170) {
	    	for(int xx=0;xx<CaveLenght;xx++) {
	    		if(radius<2) {
	    			radius=2;
	    		}
	    		digCircleHorizontal(world,random,new BlockPos(pos.getX()-xx,EndHeight,pos.getZ()),r2);
	    		r2=r2+random.nextInt(3)-1;
	    	}
    	}
    }

    
}
