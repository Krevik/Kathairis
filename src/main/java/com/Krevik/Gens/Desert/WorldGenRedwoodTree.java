package com.Krevik.Gens.Desert;

import java.util.Random;

import com.Krevik.Blocks.BlockBaseLog.EnumAxis;
import com.Krevik.Blocks.BlockRedwoodLog;
import com.Krevik.Gens.WorldGenAbstractBasicMysticTree;
import com.Krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldGenRedwoodTree extends WorldGenAbstractBasicMysticTree{

	public WorldGenRedwoodTree() {
		super(false);
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		//do Trunk
		Random random = new Random();
		int basicTreeSize = 6+random.nextInt(12);
		if(KCore.instance.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())) {
			int actualBranchesNumber=0;
			int branchChanceModifier=1+actualBranchesNumber+basicTreeSize/3;
			BlockPos tmp;
			for(int y=0;y<=basicTreeSize;y++) {
				tmp=new BlockPos(position.getX(),position.getY()+y,position.getZ());
				this.setBlock(worldIn, tmp, this.getProperStateForHeight(y, basicTreeSize));
				/*if(y>4) {
					if(random.nextInt(branchChanceModifier)==0) {
						actualBranchesNumber++;
						int branchLength=1+random.nextInt(4);
						int branchHeight=1+random.nextInt(this.basicTreeSize-y+1);
						int direction=random.nextInt(4);
						BlockPos tmpBranchWidth = null;
						BlockPos tmpBranchHeight;
						for(int xz=1;xz<=branchLength;xz++) {
							if(direction==0) {tmpBranchWidth = new BlockPos(position.getX()-xz,position.getY()+y,position.getZ());
								this.setBlock(worldIn, tmpBranchWidth, this.getProperStateForHeight(y, basicTreeSize).withProperty(BlockRedwoodLog.LOG_AXIS, EnumAxis.X));
							}
							if(direction==1) {tmpBranchWidth = new BlockPos(position.getX()+xz,position.getY()+y,position.getZ());
								this.setBlock(worldIn, tmpBranchWidth, this.getProperStateForHeight(y, basicTreeSize).withProperty(BlockRedwoodLog.LOG_AXIS, EnumAxis.X));
							}
							if(direction==2) {tmpBranchWidth = new BlockPos(position.getX(),position.getY()+y,position.getZ()-xz);
								this.setBlock(worldIn, tmpBranchWidth, this.getProperStateForHeight(y, basicTreeSize).withProperty(BlockRedwoodLog.LOG_AXIS, EnumAxis.Z));
							}
							if(direction==3) {tmpBranchWidth = new BlockPos(position.getX(),position.getY()+y,position.getZ()+xz);
								this.setBlock(worldIn, tmpBranchWidth, this.getProperStateForHeight(y, basicTreeSize).withProperty(BlockRedwoodLog.LOG_AXIS, EnumAxis.Z));
							}
							if(xz==branchLength) {
								for(int yy=1;yy<=branchHeight;yy++) {
									tmpBranchHeight = new BlockPos(tmpBranchWidth.getX(),tmpBranchWidth.getY()+yy,tmpBranchWidth.getZ());
									this.setBlock(worldIn, tmpBranchHeight, this.getProperStateForHeight(y+yy, basicTreeSize));
								}
							}
						}
					}
				}*/
			}
			return true;
		}
		
		return false;
	}
	
	private IBlockState getProperStateForHeight(int actualHeight,int maxHeight) {
		IBlockState result = KCore.Redwood_log_full.getDefaultState();
		int stages=7;
		if(actualHeight==0) {
			actualHeight=1;
		}
		float steps = maxHeight/stages;
		float actualStage=(actualHeight/steps);
		if(actualStage<1) result=KCore.Redwood_log_full.getDefaultState();
		if(actualStage>=1&&actualStage<3) result=KCore.Redwood_log_size_6.getDefaultState();
		if(actualStage>=3&&actualStage<4) result=KCore.Redwood_log_size_5.getDefaultState();
		if(actualStage>=4&&actualStage<5) result=KCore.Redwood_log_size_4.getDefaultState();
		if(actualStage>=5&&actualStage<6) result=KCore.Redwood_log_size_3.getDefaultState();
		if(actualStage>=6&&actualStage<7) result=KCore.Redwood_log_size_2.getDefaultState();
		if(actualStage>=7) result=KCore.Redwood_log_size_1.getDefaultState();

		return result;
	}
	
	private void setBlock(World world,BlockPos pos,IBlockState state) {
		if(world.isAirBlock(pos)) {
			world.setBlockState(pos, state, 2);
		}
	}
}
