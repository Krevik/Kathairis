package com.Krevik.Blocks;

import java.util.Random;

import com.Krevik.Main.CreativeTabsMystic;
import com.Krevik.Main.KCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGooseberry extends BaseBlock{

	public BlockGooseberry(String Name) {
		super(Name, Material.PLANTS, CreativeTabsMystic.mainTab, 0.3F, 0.3F, SoundType.PLANT);
	}
	
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.CUTOUT;
    }
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	return true;
    }
    public int quantityDropped(Random random)
    {
        return 1+random.nextInt(5);
    }
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return KCore.Gooseberry;
    }
}
