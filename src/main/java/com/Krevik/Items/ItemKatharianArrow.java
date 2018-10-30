package com.Krevik.Items;

import com.Krevik.Entities.EntityKatharianArrow;
import com.Krevik.Main.CreativeTabsMystic;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemKatharianArrow extends ItemArrow {

    protected String name;

    public ItemKatharianArrow(String name1)
    {
        this.setCreativeTab(CreativeTabsMystic.weapons);
        name=name1;
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntityKatharianArrow entitytippedarrow = new EntityKatharianArrow(worldIn, shooter);
        entitytippedarrow.setPotionEffect(stack);
        return entitytippedarrow;
    }

    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
    {
        int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
        return enchant <= 0 ? false : this.getClass() == ItemKatharianArrow.class;
    }
}
