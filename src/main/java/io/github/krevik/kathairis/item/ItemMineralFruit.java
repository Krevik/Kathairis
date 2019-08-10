package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ItemMineralFruit extends ItemKathairisFood {

    public ItemMineralFruit(int healAmountIn, float saturation, boolean meat, ItemGroup group) {
        super(healAmountIn, saturation, meat, group);
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        //TODO NOT WORKING
        IWorld iworld = context.getWorld();
        BlockPos blockpos = context.getPos().offset(context.getFace());
        boolean canPlaceHere = ModBlocks.BRINE_PUSTULE.isValidPosition(iworld.getBlockState(blockpos), iworld, blockpos);
        if (iworld.isAirBlock(blockpos) && canPlaceHere) {
            BlockItemUseContext context1 = new BlockItemUseContext(context);
            iworld.setBlockState(blockpos, ModBlocks.BRINE_PUSTULE.getStateForPlacement(context1), 11);

            EntityPlayer entityplayer = context.getPlayer();
            ItemStack itemstack = context.getItem();
            if (entityplayer instanceof EntityPlayerMP) {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) entityplayer, blockpos, itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.PASS;
        }
    }
}
