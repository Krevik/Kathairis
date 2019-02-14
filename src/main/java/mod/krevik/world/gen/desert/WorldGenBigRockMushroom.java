package mod.krevik.world.gen.desert;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenBigRockMushroom extends WorldGenerator
{

    public WorldGenBigRockMushroom()
    {
    }
    
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	if(KCore.functionHelper.isAvailableBlockToGenOn(worldIn, position.down())) {
    	int radius = 2+rand.nextInt(5);
    	int mushroomHeight = 4+rand.nextInt(radius)+rand.nextInt(4);

    	//base
    	for(int x = -radius/2;x<=radius/2;x++){
    		for(int z= -radius/2;z<=radius/2;z++){
    			for(int y=0;y<=mushroomHeight;y++){
    				if((x*x)+(z*z)<=(radius/2*radius/2)){
    					worldIn.setBlockState(new BlockPos(position.getX()+x,position.getY()+y,position.getZ()+z),KCore.WeatheredRock.getDefaultState());
					}
				}
			}
		}
		//head
			int headRadius= MathHelper.clamp(rand.nextInt(9),radius+2,8);
			for(int x=-headRadius/2;x<=headRadius/2;x++){
				for(int y=0;y<=headRadius/2;y++){
					for(int z=-headRadius/2;z<=headRadius/2;z++){
						if((x*x)+(y*y)+(z*z) <= (headRadius/2*headRadius/2+1)){
							worldIn.setBlockState(new BlockPos(position.getX()+x,position.getY()+y+mushroomHeight,position.getZ()+z),KCore.WeatheredRock.getDefaultState());
						}
					}
				}
			}

        return true;
    	}
		return false;
	}
    
}