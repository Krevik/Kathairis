package mod.krevik.world.gen.swamp;

import java.util.Random;

import mod.krevik.block.BlockBaseLog;
import mod.krevik.block.BlockMysticLog;
import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOldTrunk extends WorldGenerator{

	public WorldGenOldTrunk() {
		
	}
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int X=position.getX();
		int Y=position.getY();
		int Z=position.getZ();
		int length=3+rand.nextInt(6);
		int direction=rand.nextInt(2);
		if(direction==0) {
			for(int x=0;x<=length;x++) {
				int ground = worldIn.getHeight(new BlockPos(X+x,0,Z)).getY();
				BlockPos tmp=new BlockPos(X+x,ground,Z);
				if((worldIn.isAirBlock(tmp)||worldIn.getBlockState(tmp).getBlock().isReplaceable(worldIn, tmp))&&worldIn.getBlockState(tmp.down())== KCore.CorruptedGrass.getDefaultState()) {
					this.setBlock(worldIn, tmp, KCore.MysticLog.getDefaultState().withProperty(BlockMysticLog.LOG_AXIS, BlockBaseLog.EnumAxis.X));
				}
			}
		}else {
			for(int x=0;x<=length;x++) {
				int ground = worldIn.getHeight(new BlockPos(X,0,Z+x)).getY();
				BlockPos tmp=new BlockPos(X,ground,Z+x);
				if((worldIn.isAirBlock(tmp)||worldIn.getBlockState(tmp).getBlock().isReplaceable(worldIn, tmp))&&worldIn.getBlockState(tmp.down())==KCore.CorruptedGrass.getDefaultState()) {
					this.setBlock(worldIn, tmp, KCore.MysticLog.getDefaultState().withProperty(BlockMysticLog.LOG_AXIS, BlockBaseLog.EnumAxis.Z));
				}
			}
		}
		return true;
	}
	
	private void setBlock(World worldIn,BlockPos pos,IBlockState state) {
		this.setBlockAndNotifyAdequately(worldIn, pos, state);
	}

}
