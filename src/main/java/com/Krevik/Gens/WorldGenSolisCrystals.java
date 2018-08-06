package com.Krevik.Gens;

import java.util.Random;

import com.Krevik.Main.KCore;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSolisCrystals extends WorldGenerator{

	public WorldGenSolisCrystals() {
		
	}
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
    	int X=position.getX();
    	int Z=position.getZ();
    	int Y=worldIn.getHeight(X, Z);
    	if(Y>250) {
    		return false;
    	}else {
        		setBlockAndNotifyAdequately(worldIn,new BlockPos(X,Y,Z), KCore.Solis_Crystals.getDefaultState());
    	}
    	
		return false;
	}

}
