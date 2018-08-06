package com.Krevik.Blocks;
/*
import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Main.CreativeTabsMystic;
import com.Krevik.Main.KCore;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockButterflyAnalysingTable extends BlockContainer
{
	
    private static boolean hasTileEntity;

    public BlockButterflyAnalysingTable(String Name)
    {
        super(Material.ROCK);
        lightOpacity = 255; // cast a light shadow
        setTickRandomly(false);
        useNeighborBrightness = false;
    
        this.setCreativeTab(CreativeTabsMystic.mainTab);
        this.setHardness(3F);
        this.setResistance(3F);
        this.setSoundType(SoundType.STONE);
        this.setRegistryName(Name);
        this.setUnlocalizedName(Name);
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    @Override
    public Item getItemDropped(
          IBlockState state, 
          Random rand, 
          int fortune)
    {
        return Item.getItemFromBlock(KCore.Butterfly_Analysing_Table);
    }

    @Override
    public void onBlockAdded(
          World parWorld, 
          BlockPos parBlockPos, 
          IBlockState parIBlockState)
    {
    	super.onBlockAdded(parWorld, parBlockPos, parIBlockState);
    }
    
    @Override
    public boolean onBlockActivated(World parWorld, BlockPos parBlockPos, IBlockState parIBlockState, EntityPlayer parPlayer, EnumHand hand,EnumFacing parSide, float hitX, float hitY, float hitZ)
    {
        if (!parWorld.isRemote)
        {
            parPlayer.openGui(KCore.instance, KCore.GUI_ENUM.BUTTERFLYANALYSINGTABLE.ordinal(), 
                  parWorld, 
                  parBlockPos.getX(), 
                  parBlockPos.getY(), 
                  parBlockPos.getZ()); 
        }
        
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityButterflyAnalysingTable();
    }

    
    @Override
    public void breakBlock(
          World worldIn, 
          BlockPos pos, 
          IBlockState state)
    {
    	
    	
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityButterflyAnalysingTable)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, 
                    (TileEntityButterflyAnalysingTable)tileentity);
	                worldIn.updateComparatorOutputLevel(pos, this);

        }
        if (!hasTileEntity)
        {
            tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityButterflyAnalysingTable)
            {

            }
        }

        super.breakBlock(worldIn, pos, state);

    }
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

}    
*/