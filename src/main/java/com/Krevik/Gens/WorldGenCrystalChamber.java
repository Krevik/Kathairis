package com.Krevik.Gens;

import java.util.Random;

import com.Krevik.Blocks.BlockCrystal;
import com.Krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCrystalChamber extends WorldGenerator{

	public WorldGenCrystalChamber() {
		
	}
	
    private BlockCrystal pickRandomCrystalBlock(Random random) {
    	BlockCrystal crystal=null;
    	int c=random.nextInt(3);
    	if(c==0) crystal=KCore.BlueCrystal;
    	if(c==1) crystal=KCore.VioletCrystal;
    	if(c==2) crystal=KCore.YellowCrystal;
    	return crystal;
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
    	int X=pos.getX();
    	int Y=pos.getY();
    	int Z=pos.getZ();
    	if(Y<60) {
    		int radius=2+rand.nextInt(6);
    		for(int x=0;x<=radius;x++) {
    			for(int y=0;y<=radius;y++) {
    				for(int z=0;z<=radius;z++) {
    					if((x*x)+(y*y)+(z*z)<=(radius*radius)) {
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X+x,Y+y,Z+z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X-x,Y+y,Z+z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X+x,Y+y,Z-z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X-x,Y+y,Z-z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X+x,Y-y,Z+z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X-x,Y-y,Z+z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X+x,Y-y,Z-z),Blocks.AIR.getDefaultState());
    						setBlockAndNotifyAdequately(worldIn,new BlockPos(X-x,Y-y,Z-z),Blocks.AIR.getDefaultState());
    						BlockCrystal crystal = pickRandomCrystalBlock(rand);
    					}
    				}
    			}
    		}
    		for(int x=0;x<=radius+1;x++) {
    			for(int y=0;y<=radius+1;y++) {
    				for(int z=0;z<=radius+1;z++) {
    					if((x*x)+(y*y)+(z*z)<=((radius+1)*(radius+1))) {
    						BlockCrystal crystal = pickRandomCrystalBlock(rand);
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()+x,pos.getY()+y+1,pos.getZ()+z))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.DOWN));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()+x,pos.getY()+y-1,pos.getZ()+z))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.UP));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()-x,pos.getY()+y+1,pos.getZ()-z))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.DOWN));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()-x,pos.getY()+y-1,pos.getZ()-z))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.UP));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z+1))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.NORTH));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z-1))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.SOUTH));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z+1))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.NORTH));
    								}
    							}
    							if(worldIn.isAirBlock(new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z))) {
    								if(worldIn.getBlockState(new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z-1))==KCore.MythicStone.getDefaultState()) {
    									setBlockAndNotifyAdequately(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), crystal.getDefaultState().withProperty(crystal.FACING, EnumFacing.SOUTH));
    							}
    						}
    					}
    				}
    			}
    		}
    		
    	}
    	return true;
    }
    

}
