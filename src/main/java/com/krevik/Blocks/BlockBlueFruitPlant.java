package com.krevik.Blocks;

import com.krevik.Main.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBlueFruitPlant extends BlockMysticCrops
{
    public BlockBlueFruitPlant(String Name) {
		super(Name);
	}

	private static final AxisAlignedBB[] CARROT_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D)};

    protected Item getSeed()
    {
        return KCore.BlueFruit;
    }

    protected Item getCrop()
    {
        return KCore.BlueFruit;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CARROT_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }
}