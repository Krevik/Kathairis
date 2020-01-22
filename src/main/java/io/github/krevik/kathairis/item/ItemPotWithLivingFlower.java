package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.entity.EntityLivingFlower;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

/**
 * @author Krevik
 */
public class ItemPotWithLivingFlower extends Item {

	public ItemPotWithLivingFlower(ItemGroup group) {
		super(new Properties().group(group));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if (!context.getWorld().isRemote) {
			EntityLivingFlower elv = new EntityLivingFlower(context.getWorld());
			elv.setPosition(context.getPos().getX()+0.5, context.getPos().getY()+1, context.getPos().getZ()+0.5);
			elv.deallowDespawning();
			context.getWorld().addEntity(elv);
			ItemStack itemstack = context.getItem();
			itemstack.shrink(1);
			context.getPlayer().addItemStackToInventory(new ItemStack(Blocks.FLOWER_POT, 1));
		}
		return ActionResultType.SUCCESS;
	}

}
