package com.Krevik.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsMystic {
	public static final CreativeTabs buildingBlocks = new CreativeTabs("Kathairis Building Blocks"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.MythicStone);
		}
	};
	public static final CreativeTabs food = new CreativeTabs("Kathairis Food"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.BisonMeat);
		}
	};
	public static final CreativeTabs plants = new CreativeTabs("Kathairis Plants"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.MysticFungus);
		}
	};
	public static final CreativeTabs weapons = new CreativeTabs("Kathairis Weapons"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.DeathWand);
		}
	};
	public static final CreativeTabs armors = new CreativeTabs("Kathairis Armors"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.RevenumHelmet);
		}
	};
	public static final CreativeTabs tools = new CreativeTabs("Kathairis Tools"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.CrystalPickaxe);
		}
	};
	public static final CreativeTabs miscellaneous = new CreativeTabs("Kathairis Miscellaneous"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.CrystalBlend);
		}
	};

}
