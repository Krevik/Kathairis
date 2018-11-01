package com.Krevik.Gens.Forest;

import java.util.Random;

import com.Krevik.Blocks.BlockMysticBush;
import com.Krevik.Blocks.BlockMysticLeaf;
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
    private final int crownRadius;

    public WorldGenFlippedHeartTree(int minTreeHeightIn,int radiusOfCrown)
    {
        super(false);
        this.minTreeHeight = minTreeHeightIn;
        crownRadius=radiusOfCrown; //it should be more than 3
    }

    public boolean generate(World worldIn, Random rand, BlockPos pos)
    {
        if(KCore.instance.functionHelper.isAvailableBlockToGenOn(worldIn, pos.down())) {
            int TreeHeight = minTreeHeight + rand.nextInt(1 + crownRadius / 2);
            int Y = worldIn.getHeight(pos).getY();
            for (int c = 0; c <= TreeHeight; c++) {
                setBlockAndNotifyAdequately(worldIn, new BlockPos(pos.getX(), Y + c, pos.getZ()), KCore.ShinyLog.getDefaultState());
            }
            if (rand.nextInt(3) == 0) {
                BlockPos tmp = pos.east();
                int height = worldIn.getHeight(tmp).getY();
                if (MathHelper.abs(height - Y) < 2) {
                    tmp = worldIn.getHeight(tmp);
                    setBlockAndNotifyAdequately(worldIn, tmp, KCore.ShinyLog.getDefaultState());
                }
            }
            if (rand.nextInt(3) == 0) {
                BlockPos tmp = pos.west();
                int height = worldIn.getHeight(tmp).getY();
                if (MathHelper.abs(height - Y) < 2) {
                    tmp = worldIn.getHeight(tmp);
                    setBlockAndNotifyAdequately(worldIn, tmp, KCore.ShinyLog.getDefaultState());
                }
            }
            if (rand.nextInt(3) == 0) {
                BlockPos tmp = pos.south();
                int height = worldIn.getHeight(tmp).getY();
                if (MathHelper.abs(height - Y) < 2) {
                    tmp = worldIn.getHeight(tmp);
                    setBlockAndNotifyAdequately(worldIn, tmp, KCore.ShinyLog.getDefaultState());
                }
            }
            if (rand.nextInt(3) == 0) {
                BlockPos tmp = pos.north();
                int height = worldIn.getHeight(tmp).getY();
                if (MathHelper.abs(height - Y) < 2) {
                    tmp = worldIn.getHeight(tmp);
                    setBlockAndNotifyAdequately(worldIn, tmp, KCore.ShinyLog.getDefaultState());
                }
            }
            int someRadius = crownRadius;
            for (int x = -someRadius; x <= someRadius; x++) {
                for (int y = -someRadius; y <= someRadius; y++) {
                    for (int z = -someRadius; z <= someRadius; z++) {
                        //float part1 = (x*x+((9*(y*y))/4)+(z*z)-1);
                        //float part2 = -(x*x)*(z*z*z)-((9*y*y*z*z*z)/80);
                        //float part1=2*(x*x)+(9/4)*(y*y)+(z*z)-1;
                        //float part2=(x*x)*(z*z*z);
                        //float part3=(9/80)*y*y*z*z*z;
                        //float equation=((part1*part1*part1)-part2-part3)*10;
                        if ((x * x + y * y + z * z - someRadius * 2) * (x * x + y * y + z * z - someRadius * 2) * (x * x + y * y + z * z - someRadius * 2) - someRadius * 2 * x * x * y * y * y - (y * y * z * z * z) / someRadius < 0) {
                            setBlockAndNotifyAdequately(worldIn, new BlockPos(pos.getX() + x, pos.getY() - y + TreeHeight + someRadius / 3, pos.getZ() + z), rand.nextInt(5) == 0 ? KCore.ShinyLog.getDefaultState() : KCore.ShinyLeaves.getDefaultState());
                        }
                    }
                }
            }
            return true;
        }else{
            return false;
        }

    }


    private void addVine(World worldIn, BlockPos pos, PropertyBool prop)
    {
        this.setBlockAndNotifyAdequately(worldIn, pos, Blocks.VINE.getDefaultState().withProperty(prop, Boolean.valueOf(true)));
    }

    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state)
    {
        if(worldIn.isAirBlock(pos)||worldIn.getBlockState(pos).getBlock() instanceof BlockMysticLeaf||worldIn.getBlockState(pos) instanceof BlockMysticBush) {
            worldIn.setBlockState(pos, state, 2);
        }
    }

}