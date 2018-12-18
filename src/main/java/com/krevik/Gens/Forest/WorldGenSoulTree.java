package com.krevik.Gens.Forest;

import java.util.Random;

import com.krevik.Blocks.BlockLuminescentGnarl;
import com.krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenSoulTree extends WorldGenAbstractTree{

	public WorldGenSoulTree(boolean notify) {
		super(notify);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if(KCore.instance.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())) {
		int height=0;
		height=6+rand.nextInt(8);
		int shiftX=0;
		int shiftZ=0;
		
		for(int c=0;c<=height;c++) {
			shiftX=shiftX+rand.nextInt(2)-rand.nextInt(2);
			if(shiftX>2) {
				shiftX--;
			}
			if(shiftX<-2) {
				shiftX++;
			}
			shiftZ=shiftZ+rand.nextInt(2)-rand.nextInt(2);
			if(shiftZ>2) {
				shiftZ--;
			}
			if(shiftZ<-2) {
				shiftZ++;
			}
			this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX()+shiftX,position.getY()+c,position.getZ()+shiftZ), KCore.SoulLog.getDefaultState());
			if(rand.nextInt((int)(height/2)+1)==0) {
				int k=rand.nextInt(4);
				if(k==0) {
					this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX()+shiftX+1,position.getY()+c,position.getZ()+shiftZ), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.EAST));	
				}
				if(k==1) {
					this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX()+shiftX-1,position.getY()+c,position.getZ()+shiftZ), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.WEST));	
				}
				if(k==2) {
					this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX()+shiftX,position.getY()+c,position.getZ()+shiftZ+1), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.SOUTH));	
				}
				if(k==3) {
					this.setBlockAndNotifyAdequately(worldIn, new BlockPos(position.getX()+shiftX,position.getY()+c,position.getZ()+shiftZ-1), KCore.LuminescentGnarl.getDefaultState().withProperty(BlockLuminescentGnarl.FACING, EnumFacing.NORTH));	
				}
			}
		}
		
		BlockPos tmp = new BlockPos(position.getX()+shiftX,position.getY()+height,position.getZ()+shiftZ);
		this.generateCrown(worldIn, tmp, height/2,rand);
		this.setBlockAndNotifyAdequately(worldIn, tmp, KCore.SoulLog.getDefaultState());

		}
		
		return true;
	}
	
	
    private void generateCrown(World world,BlockPos pos,int radius,Random random) {
    	for(int x=0;x<=radius;x++) {
    		for(int z=0;z<=radius;z++) {
    			for(int y=0;y<=radius/2;y++) {
    				if(((x*x)+(z*z)+(y*2*y*2)<=(radius*radius))){

    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState());

    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), KCore.SoulLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState());
    					setBlockAndNotifyAdequately(world,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), KCore.SoulLeaves.getDefaultState());
    					
    					
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
