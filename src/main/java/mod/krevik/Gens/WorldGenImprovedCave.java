package mod.krevik.Gens;

import java.util.Random;

import mod.krevik.Blocks.BlockCrystal;
import mod.krevik.Main.KCore;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenImprovedCave {
    public WorldGenImprovedCave(){
    	
    }
    
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	initGenerating(worldIn,rand,position);
    	return true;
    }

    private void initGenerating(World world, Random rand, BlockPos pos) {
    	pos=new BlockPos(pos.getX()-8,pos.getY(),pos.getZ()-8);
    	int caveLength = 60+rand.nextInt(100);
    	int actualY=getTopGrassBlock(world,pos).getY();
    	if(actualY<200) {
	    	int actualX=0;
	    	int radius=3+rand.nextInt(6);
	    	int actualZ=0;
	    	int unchangedRadius=0;
	    	int unchangedY=0;
	    	if(actualY<200) {
	    		for(int c=0;c<=caveLength;c++) {	
	    			if(rand.nextInt(12-unchangedRadius)==0) {
	    				unchangedRadius=0;
		    			if(radius<=4) {
		    				radius=radius+rand.nextInt(3)-rand.nextInt(2);
		    			}
		    			if(radius>4) {
		    				radius=radius+rand.nextInt(2)-rand.nextInt(3);
		    			}
	    			}else {unchangedRadius++;}
	    			
	    			if(actualY>60) {
		    			actualY--;
	    			}else {
	    				if(rand.nextInt(6-unchangedY)==0) {
	    					unchangedY=0;
	    					actualY--;
	    					if(rand.nextInt(4)==0) {
	    						actualY++;
	    					}
	    				}
	    				unchangedY++;
	    			}
	    			
	    			
	    			actualX++;

	    			if(rand.nextInt(4)==1) {
	    				actualZ=actualZ+rand.nextInt(2)-rand.nextInt(2);
	    			}
	    			
	    			if(actualY<6) {
	    				actualY=5;
	    				radius=0;
	    				break;
	    			}
	    			
	    			if(caveLength-c<6) {
	    				radius=caveLength-c;
	    			}
	    			
	    			digCircleHorizontal(world,rand,pos,radius,actualX,actualY,actualZ);
	    		}
	    		
	    	}
    	}
    	
    	
    }
    
    private void digCircleHorizontal(World world,Random random,BlockPos pos,int radius, int shiftX, int shiftY, int shiftZ) {
    	
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			if(x*x+z*z<=(radius+1)*radius) {
    				int xx=x+1, zz=z+1;
        		if(xx*xx+zz*zz<=(radius+1)*radius) {
        			digBlock(world,new BlockPos(pos.getX()+shiftX,x+shiftY,pos.getZ()+z+shiftZ));

    				digBlock(world,new BlockPos(pos.getX()+shiftX,-x+shiftY,pos.getZ()-z+shiftZ));
    				digBlock(world,new BlockPos(pos.getX()+shiftX,-x+shiftY,pos.getZ()+z+shiftZ));
    				digBlock(world,new BlockPos(pos.getX()+shiftX,x+shiftY,pos.getZ()-z+shiftZ));
    				if(shiftY<30&&random.nextInt(1800)==1&&(world.getBlockState(new BlockPos(pos.getX()+shiftX+1,x+shiftY,pos.getZ()+z+shiftZ))==KCore.MythicStone.getDefaultState()||
    														world.getBlockState(new BlockPos(pos.getX()+shiftX-1,x+shiftY,pos.getZ()+z+shiftZ))==KCore.MythicStone.getDefaultState()||
    														world.getBlockState(new BlockPos(pos.getX()+shiftX,x+shiftY,pos.getZ()+z+shiftZ))==KCore.MythicStone.getDefaultState()||
    														world.getBlockState(new BlockPos(pos.getX()+shiftX,x+shiftY+1,pos.getZ()+z+shiftZ))==KCore.MythicStone.getDefaultState()||
    														world.getBlockState(new BlockPos(pos.getX()+shiftX,x+shiftY-1,pos.getZ()+z+shiftZ))==KCore.MythicStone.getDefaultState()||
    														world.getBlockState(new BlockPos(pos.getX()+shiftX,x+shiftY,pos.getZ()+z+shiftZ+1))==KCore.MythicStone.getDefaultState()||
    														world.getBlockState(new BlockPos(pos.getX()+shiftX,x+shiftY,pos.getZ()+z+shiftZ-1))==KCore.MythicStone.getDefaultState())) {
    					world.setBlockState(new BlockPos(pos.getX()+shiftX,x+shiftY,pos.getZ()+z+shiftZ), KCore.RevenumOre.getDefaultState());
    				}
    				if(random.nextInt(100)==1&&shiftY<60) {
    					BlockCrystal crystal = pickRandomCrystalBlock(random);
    					if(crystal!=null) {
    						tryToGenerateCrystals(crystal,world,radius,new BlockPos(pos.getX()+shiftX-1,shiftY,pos.getZ()+shiftZ),random);
    					}
    				}
    				if(random.nextInt(1500)==1) {
						tryToGenerateLake(world,radius,new BlockPos(pos.getX()+shiftX-1,shiftY,pos.getZ()+shiftZ),random);
    				}
    				if(random.nextInt(250)==1) {
    					tryToGenerateMysticFungus(world,radius,new BlockPos(pos.getX()+shiftX-1,shiftY,pos.getZ()+shiftZ),random);
    				}
        			}
    			}
    		}
    	}
    	}
    
    private void tryToGenerateMysticFungus(World world, int radius, BlockPos pos, Random random) {
    	for(int y=0;y<=radius;y++) {
    		for(int z=0;z<=radius;z++) {
    			if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z))==KCore.MythicStone.getDefaultState()) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()-y+1,pos.getZ()+z))) {
    					if(random.nextInt(5)==1) {
        					world.setBlockState(new BlockPos(pos.getX(),pos.getY()-y+1,pos.getZ()+z), KCore.MysticFungus.getDefaultState());
    					}
    				}
    			}
    			if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-z))==KCore.MythicStone.getDefaultState()) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()-y+1,pos.getZ()-z))) {
    					if(random.nextInt(5)==1) {
    					world.setBlockState(new BlockPos(pos.getX(),pos.getY()-y+1,pos.getZ()-z), KCore.MysticFungus.getDefaultState());
    					}
    				}
    			}
    			
    		}
    	}
    }
    
    private void tryToGenerateLake(World world, int radius, BlockPos pos, Random random) {
    	for(int y=0;y<=radius;y++) {
    		for(int z=0;z<=radius;z++) {
    			if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z))==KCore.MythicStone.getDefaultState()) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()-y+1,pos.getZ()+z))) {
    					new WorldGenMysticLakes(Blocks.LAVA).generate(world, random, new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z));
    				}
    			}
    			if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-z))==KCore.MythicStone.getDefaultState()) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()-y+1,pos.getZ()-z))) {
    					new WorldGenMysticLakes(Blocks.LAVA).generate(world, random, new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()-z));
    				}
    			}
    			
    		}
    	}
    }
    
    private void tryToGenerateCrystals(BlockCrystal crystal, World world, int radius, BlockPos pos, Random random) {
    	for(int y=0;y<=radius;y++) {
    		for(int z=0;z<=radius;z++) {
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()+y+1,pos.getZ()+z))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.DOWN));
        					}
    				}
    			}
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()+y-1,pos.getZ()+z))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.UP));
        					}
    				}
    			}
    			
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()-z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()+y+1,pos.getZ()-z))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()-z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.DOWN));
        					}
    				}
    			}
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()-z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()+y-1,pos.getZ()-z))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()-z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.UP));
        					}
    				}
    			}
    			
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z+1))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.NORTH));
        					}
    				}
    			}
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z-1))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.SOUTH));
        					}
    				}
    			}
    			
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z+1))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.NORTH));
        					}
    				}
    			}
    			if(random.nextInt(3)==1) {
    				if(world.isAirBlock(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z))) {
        					if(world.getBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z-1))==KCore.MythicStone.getDefaultState()) {
        						world.setBlockState(new BlockPos(pos.getX(),pos.getY()-y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.SOUTH));
        					}
    				}
    			}
    		}
    	}
    }

    private BlockCrystal pickRandomCrystalBlock(Random random) {
    	BlockCrystal crystal=null;
    	int c=random.nextInt(3);
    	if(c==0) crystal=KCore.BlueCrystal;
    	if(c==1) crystal=KCore.VioletCrystal;
    	if(c==2) crystal=KCore.YellowCrystal;
    	return crystal;
    }
    
	private void digBlock(World worldIn, BlockPos pos) {
			worldIn.setBlockToAir(pos);
	}
	
	
    private BlockPos getTopGrassBlock(World worldIn,BlockPos position){
    	int top=0;
        	for(top=50;top<256;top++){
        		if(worldIn.getBlockState(new BlockPos(position.getX(),top,position.getZ()))==KCore.CorruptedGrass.getDefaultState()){
        			break;
        		}
        	}
    	return new BlockPos(position.getX(),top,position.getZ());
    }
}
