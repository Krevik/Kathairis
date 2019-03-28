package io.github.krevik.kathairis.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemButterflyCatcher extends Item {

	public ItemButterflyCatcher(ItemGroup group) {
		super(new Item.Properties().group(group).maxStackSize(1));
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        /*
        if(entity instanceof EntityBasicButterfly){
            EntityBasicButterfly butterfly = (EntityBasicButterfly) entity;
            EntityBasicButterfly.ButterflyType type = EntityBasicButterfly.ButterflyType.byMetadata(butterfly.getVariant());
            butterfly.setDead();
            player.addItemStackToInventory(new ItemStack(getProperButterflyItem(type),1));
            //TODO drop wings?
            butterfly.posX=999;
            butterfly.posY=-10;
            butterfly.posZ=999;
            return true;
        }
        return false;*/
		return false;
	}

   /* private Item getProperButterflyItem(EntityBasicButterfly.ButterflyType type){
        if(type==EntityBasicButterfly.ButterflyType.BASIC1){
            return KCore.butterfly_common_1;
        }
        else if(type==EntityBasicButterfly.ButterflyType.BASIC2){
            return KCore.butterfly_common_2;
        }
        else if(type==EntityBasicButterfly.ButterflyType.ILLUKINI){
            return KCore.butterfly_illukini;
        }
        else if(type==EntityBasicButterfly.ButterflyType.COMMONMOTH){
            return KCore.butterfly_common_moth;
        }
        else if(type==EntityBasicButterfly.ButterflyType.CLOUDSHIMMER){
            return KCore.butterfly_cloud_shimmer;
        }else{
            return null;
        }
    }*/

}
