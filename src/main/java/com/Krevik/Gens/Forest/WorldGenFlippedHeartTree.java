package com.Krevik.Gens.Forest;

import java.util.Random;

import com.Krevik.Gens.WorldGenAbstractBasicMysticTree;
import com.Krevik.Main.KCore;

import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WorldGenFlippedHeartTree extends WorldGenAbstractBasicMysticTree
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;


    public WorldGenFlippedHeartTree(int minTreeHeightIn)
    {
        super(false);
        this.minTreeHeight = minTreeHeightIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        BlockPos position = new BlockPos(pos.getX(),pos.getY()+10,pos.getZ());
        int someRadius=3;
        for(int x=-6;x<=6;x++){
            for(int y=-6;y<=6;y++){
                for(int z=-6;z<=6;z++){
                    //float part1 = (x*x+((9*(y*y))/4)+(z*z)-1);
                    //float part2 = -(x*x)*(z*z*z)-((9*y*y*z*z*z)/80);
                    float part1=216*2*(x*x)+(y*y)+(z*z)-1;
                    float part2=216*(x*x)*((z*z*z)/10);
                    float part3=216*y*y*z*z*z;
                    float equation=(part1*part1*part1)-part2-part3;
                    System.out.println(equation);
                    if((part1*part1*part1)-part2-part3<0){
                        setBlockAndNotifyAdequately(worldIn,new BlockPos(position.getX()+x,position.getY()+y,position.getZ()+z),KCore.ShinyLeaves.getDefaultState());
                    }
                }
            }
        }
        return true;
    }


    private void addVine(World worldIn, BlockPos pos, PropertyBool prop)
    {
        this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true)));
    }

    private void addHangingVine(World worldIn, BlockPos pos, PropertyBool prop)
    {
        this.addVine(worldIn, pos, prop);
        int i = 4;

        for (BlockPos blockpos = pos.down(); worldIn.isAirBlock(blockpos) && i > 0; --i)
        {
            this.addVine(worldIn, blockpos, prop);
            blockpos = blockpos.down();
        }
    }
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos, state, 2);
    }

}