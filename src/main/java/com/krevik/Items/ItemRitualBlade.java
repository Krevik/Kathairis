package com.krevik.Items;

import java.util.List;

import javax.annotation.Nullable;

import com.krevik.Main.KCore;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRitualBlade extends BaseItem
{
    private float attackDamage;

    public ItemRitualBlade(String Name, CreativeTabs tab)
    {
    	super(Name,tab);
        this.maxStackSize = 1;
        this.setMaxDamage(1000);
        this.attackDamage = 3.0F;
    }

    /**
     * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
     */
    public float getDamageVsEntity()
    {
        return this.attackDamage;
    }
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {

    }
    public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
        Block block = state.getBlock();

        if (block == Blocks.WEB)
        {
            return 15.0F;
        }
        else
        {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Durability: " + (this.getMaxDamage()-this.getDamage(stack)));
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);

        return true;
    }

    /**
     * Called when a Block is destroyed using this Item. Return true to trigger the "Use Item" statistic.
     */
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }


    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == Blocks.WEB;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 15;
    }



    /**
     * Return whether this item is repairable in an anvil.
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
    	return false;
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }
    
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
   	 if(this==KCore.Ritual_Blade){
   		 if(!worldIn.isRemote) {
   	    	 if(KCore.MysticPortal.trySpawnPortal(worldIn, pos.up())) {
   	   	    	 ItemStack itemstack = player.getHeldItem(hand);
   	   	    	 itemstack.damageItem(1, player); 
   	    	 }
   		 }
       	 return EnumActionResult.SUCCESS; 
   	 }
   	 else
   	 return EnumActionResult.PASS;
    }
}