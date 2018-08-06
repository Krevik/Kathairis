package com.Krevik.Gens;

import java.util.Random;

import com.Krevik.Main.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenAbstractBasicMysticTree extends WorldGenAbstractTree
{
    public WorldGenAbstractBasicMysticTree(boolean notify)
    {
        super(notify);
    }

    /**
     * returns whether or not a tree can grow into a block
     * For example, a tree will not grow into stone
     */
    protected boolean canGrowInto(Block blockType)
    {
        Material material = blockType.getDefaultState().getMaterial();
        return material == Material.AIR || material == Material.LEAVES || blockType == Blocks.GRASS || blockType == Blocks.DIRT || blockType == Blocks.LOG || blockType == Blocks.LOG2 || blockType == Blocks.SAPLING || blockType == Blocks.VINE ||
        		blockType == KCore.CorruptedDirt || blockType == KCore.CorruptedGrass || blockType == KCore.MysticLog;
    }

    public void generateSaplings(World worldIn, Random random, BlockPos pos)
    {
    }

    /**
     * sets dirt at a specific location if it isn't already dirt
     */
    protected void setDirtAt(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() != KCore.CorruptedDirt)
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, KCore.CorruptedDirt.getDefaultState());
        }
    }

    public boolean isReplaceable(World world, BlockPos pos)
    {
        net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getBlock().isWood(world, pos) || canGrowInto(state.getBlock());
    }
}