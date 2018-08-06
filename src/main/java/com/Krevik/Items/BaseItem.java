package com.Krevik.Items;

import com.Krevik.Entities.EntityLivingFlower;
import com.Krevik.Main.KCore;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItem extends Item{
 
 protected String name;
 
 public BaseItem(String name, CreativeTabs tab) {
 this.name = name;
 setUnlocalizedName(name);
 setRegistryName(name);
 this.setCreativeTab(tab);
 KCore.instance.regHelper.itemList.add(this);
 KCore.instance.cproxy.itemList.add(this);
 }
 
 @SideOnly(Side.CLIENT)
 public void initModel() {
     ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
 }
 
 public EnumRarity getRarity(ItemStack stack)
 {
	 if(this==KCore.MysticGem||this==KCore.CloudEssence||this==KCore.Ritual_Blade){
		 return EnumRarity.EPIC;
	 }
	 else
	 {
		 return EnumRarity.COMMON;
	 }
 }
 
	@Override
	public int getItemBurnTime(ItemStack fuel) {
		if (fuel.getItem() == KCore.CrystalBlend){
			return 600;
		}else {
		return 0;
		}
	}
	
 
 @SideOnly(Side.CLIENT)
 public boolean hasEffect(ItemStack stack)
 {
	 if(this==KCore.MysticGem||this==KCore.CloudEssence||this==KCore.DarknessEssence||this==KCore.Ritual_Blade) return true;
	 else return false;
 }
 
 @Override
 public BaseItem setCreativeTab(CreativeTabs tab) {
 super.setCreativeTab(tab);
 return this;
 }
 


 
 public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
 {
	 if(this==KCore.MysticGem){
		 if(!worldIn.isRemote) {
	    	 KCore.MysticPortal.trySpawnPortal(worldIn, pos.up());
	    	 ItemStack itemstack = player.getHeldItem(hand);
	    	 itemstack.shrink(1);
		 }
    	 return EnumActionResult.SUCCESS; 
	 }
	 else if(this==KCore.PotWithLivingFlower){
		 if(!worldIn.isRemote) {
			 EntityLivingFlower elv = new EntityLivingFlower(worldIn);
			 elv.setPosition(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5);
			 worldIn.spawnEntity(elv);
	    	 ItemStack itemstack = player.getHeldItem(hand);
	    	 itemstack.shrink(1);
	    	 player.addItemStackToInventory(new ItemStack(Items.FLOWER_POT,1));
		 }

		 return EnumActionResult.SUCCESS;
	 }else
	 return EnumActionResult.PASS;
 }
 

}