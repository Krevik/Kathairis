package com.Krevik.Gens;

import java.awt.geom.Ellipse2D;
import java.util.Random;

import com.Krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenClouds extends WorldGenerator{
	
	public WorldGenClouds() {
		
	}

    protected static WorldGenMiniTallGrass FRUPGEN = new WorldGenMiniTallGrass(KCore.BlueFruitPlant);

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
    	int mode=1;
    	mode=rand.nextInt(2);
    	IBlockState cloudState=rand.nextInt(2)==0?KCore.BlueCloud.getDefaultState():KCore.YellowCloud.getDefaultState();

    	if(mode==1) {
    		int maxWidth=3+rand.nextInt(7);
    		int maxLength=maxWidth+rand.nextInt(4)-rand.nextInt(4);
    		ellipse(worldIn,maxWidth,maxLength,cloudState,pos,true);
    		
    		ellipse(worldIn,maxWidth-1,maxLength-1,cloudState,pos.up(),true);
    		ellipse(worldIn,maxWidth-1,maxLength-1,cloudState,pos.down(),true);
    		
    		ellipse(worldIn,maxWidth-2,maxLength-2,cloudState,pos.up(2),true);
    		ellipse(worldIn,maxWidth-2,maxLength-2,cloudState,pos.down(2),true);

    	}
    	
    	if(mode==0) {
	    	int radius=3+rand.nextInt(5);
	    	for(int x=0;x<=radius;x++) {
	    		for(int z=0;z<=radius;z++) {
	    			for(int y=0;y<=radius/3;y++) {
	    				if(((x*x)+(z*z)+(y*3*y*3)<=(radius*radius))){
	    					setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()+z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()-z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()+y,pos.getZ()-z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()+z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()+z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()+x,pos.getY()-y,pos.getZ()-z), cloudState);
	    					setBlock(worldIn,new BlockPos(pos.getX()-x,pos.getY()-y,pos.getZ()-z), cloudState);
	
	    				}
	    			}
	    		}
	    	}
    	}
    	return true;
    }
    
    private void ellipse(World world, int radius_x, int radius_z, IBlockState block_state, BlockPos pos, boolean fill) {
        if (fill) {
            for (int x = -radius_x; x <= radius_x; x++) {
                int dy = (int) (Math.sqrt((radius_z * radius_z) * (1.0 - (double) (x * x) / (double) (radius_x * radius_x))));
                for (int y = -dy; y <= dy; y++) {
                	setBlock(world,pos.add(x, 0, y), block_state);
                	if(world.getBlockState(pos.add(x, 0, y))==KCore.BlueCloud.getDefaultState()&&world.isAirBlock(pos.add(x, 1, y))&&KCore.instance.functionHelper.random.nextInt(20)==0) {
                		setBlock(world,pos.add(x, 1, y), KCore.Plant_Blue_Cloud.getDefaultState());
                	}
                	if(world.getBlockState(pos.add(x, 0, y))==KCore.YellowCloud.getDefaultState()&&world.isAirBlock(pos.add(x, 1, y))&&KCore.instance.functionHelper.random.nextInt(20)==0) {
                		setBlock(world,pos.add(x, 1, y), KCore.Plant_Yellow_Cloud.getDefaultState());
                	}
                }
            }
        } else {
            double radius_x_sq = radius_x * radius_x;
            double radius_z_sq = radius_z * radius_z;
            int x = 0, y = radius_z;
            double p, px = 0, py = 2 * radius_x_sq * y;

            setBlock(world,pos.add(+x, 0, +y), block_state);
            setBlock(world,pos.add(-x, 0, +y), block_state);
            setBlock(world,pos.add(+x, 0, -y), block_state);
            setBlock(world,pos.add(-x, 0, -y), block_state);

            p = radius_z_sq - (radius_x_sq * radius_z) + (0.25 * radius_x_sq);
            while (px < py) {
                x++;
                px = px + 2 * radius_z_sq;
                if (p < 0) {
                    p = p + radius_z_sq + px;
                } else {
                    y--;
                    py = py - 2 * radius_x_sq;
                    p = p + radius_z_sq + px - py;

                }
                setBlock(world,pos.add(+x, 0, +y), block_state);
                setBlock(world,pos.add(-x, 0, +y), block_state);
                setBlock(world,pos.add(+x, 0, -y), block_state);
                setBlock(world,pos.add(-x, 0, -y), block_state);
            }

            p = radius_z_sq * (x + 0.5) * (x + 0.5) + radius_x_sq * (y - 1) * (y - 1) - radius_x_sq * radius_z_sq;
            while (y > 0) {
                y--;
                py = py - 2 * radius_x_sq;
                if (p > 0) {
                    p = p + radius_x_sq - py;
                } else {
                    x++;
                    px = px + 2 * radius_z_sq;
                    p = p + radius_x_sq - py + px;
                }
                setBlock(world,pos.add(+x, 0, +y), block_state);
                setBlock(world,pos.add(-x, 0, +y), block_state);
                setBlock(world,pos.add(+x, 0, -y), block_state);
                setBlock(world,pos.add(-x, 0, -y), block_state);
            }
        }
    }
    private void setBlock(World worldIn, BlockPos pos,IBlockState state) {
    	if(worldIn.isAirBlock(pos)||!worldIn.getBlockState(pos).isFullBlock()) {
        	setBlockAndNotifyAdequately(worldIn,pos,state);
    	}
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
            worldIn.setBlockState(pos, state, 2);
    }

  
}
