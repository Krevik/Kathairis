package com.Krevik.Gens;

import java.util.Random;

import com.Krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenHugeSoulTree extends WorldGenAbstractBasicMysticTree{
	
	public WorldGenHugeSoulTree() {
		super(false);
	}

	IBlockState logState = KCore.SoulLog.getDefaultState();
	private BlockPos lastBranchPos;
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if(KCore.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())) {
			this.generateTree(worldIn,rand,position);
			return true;
		}
		else {
			return false;
		}
	}
	
	private void generateTree(World world,Random random,BlockPos pos) {
		int treeHeight=10+random.nextInt(8);
		int branchChanceWeight=1;
		int baseLength=treeHeight/3;
		for(int y=0;y<=treeHeight;y++) {
			BlockPos tmp = new BlockPos(pos.getX(),pos.getY()+y,pos.getZ());
			world.setBlockState(tmp, logState, 2);
			if(y>=4) {
				if(random.nextInt(branchChanceWeight)==0) {
					branchChanceWeight+=2;
					this.generateBranchPiece(world, baseLength, tmp);
					baseLength=baseLength-random.nextInt(3);
				}
			}
			if(y==treeHeight) {
				doCrown(world,tmp,treeHeight/3);
			}
		}
	}
	
	
	private void generateBranchPiece(World world, int length,BlockPos pos) {
		int shiftX=0;
		int shiftZ=0;
		int direction=KCore.instance.functionHelper.random.nextInt(3);
		for(int y=0;y<=length;y++) {
			if(direction==0)shiftX++;
			if(direction==1)shiftX--;
			if(direction==2)shiftZ++;
			if(direction==3)shiftZ--;
			BlockPos tmp=new BlockPos(pos.getX()+shiftX,pos.getY()+y,pos.getZ()+shiftZ);
			world.setBlockState(tmp, logState, 2);
				plantLeaves(world,tmp,KCore.instance.functionHelper.random.nextInt(2));
		}
	}
	
	private void plantLeaves(World world,BlockPos pos,int radius) {
	   	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			for(int y=0;y<=radius;y++) {
    				if(((x*x)+(z*z)+(y*y)<=(radius*radius))){
    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);

    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);
    				}
    			}
    		}
    	}
	}
	
    private void doCrown(World world,BlockPos pos,int radius) {
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			for(int y=0;y<=radius/2;y++) {
    				if(((x*x)+(z*z)+(y*2*y*2)<=(radius*radius))){

    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);

    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);
    					setBlockState(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState(),2);
    					
    					
    				}
    			}
    		}
    	}
    }
    
    private void setBlockState(World world, BlockPos pos,IBlockState state, int flag) {
    	if(world.isAirBlock(pos)) {
        	world.setBlockState(pos, state, flag);
    	}
    }

}
