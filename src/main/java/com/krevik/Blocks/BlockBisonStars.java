package com.krevik.Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockBisonStars extends BlockMysticBush {
    protected static final AxisAlignedBB BISON_STARS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 0.125D, 1D);

    public BlockBisonStars(String Name)
    {
        super(Name,false,false);
        this.setTickRandomly(true);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BISON_STARS_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return null;
    }

}
