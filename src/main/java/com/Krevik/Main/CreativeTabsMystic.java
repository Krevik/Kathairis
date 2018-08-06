package com.Krevik.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsMystic {
	public static final CreativeTabs mainTab = new CreativeTabs("Mystic"){
		@Override
		public ItemStack getTabIconItem(){
			return new ItemStack(KCore.MysticGem);
		}
	};

}
