package com.Krevik.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Entities.EntityPoisonousScorpion;
import com.Krevik.Entities.Butterflies.EntityButterfly;
import com.Krevik.Main.KCore;
import com.Krevik.Particles.ParticleMysticGemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMythicStoneBricks extends BaseBlock
{

    public BlockMythicStoneBricks(String Name, Material material, CreativeTabs tab, float hardness1, float resistance, SoundType soundType)
    {
        super(Name,material,tab,hardness1,resistance,soundType);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return true;
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
            return FULL_BLOCK_AABB;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);

    }

    public String getLocalizedName()
    {
        return I18n.translateToLocal(this.getUnlocalizedName() + "." + ".name");
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
            return Item.getItemFromBlock(this);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
            return BlockRenderLayer.SOLID;
    }

    public boolean isFullCube(IBlockState state)
    {
        return true;
    }


}