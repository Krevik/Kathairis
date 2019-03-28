package mod.krevik.kathairis.item;

import mod.krevik.kathairis.init.ModBlocks;
import mod.krevik.kathairis.init.ModItemGroups;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nonnull;

public class ItemGooseberries extends ItemSeedFood {

	private final IBlockState crop;

	public ItemGooseberries(int healAmountIn, float saturation, Block crop) {
		super(healAmountIn, saturation, crop, new Properties().group(ModItemGroups.FOOD));
		this.crop = crop.getDefaultState();
	}

	@Nonnull
	@Override
	public EnumActionResult onItemUse(@Nonnull final ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos().up();
		boolean canPlaceHere = ModBlocks.GOOSEBERRY_BUSH.isValidPosition(iworld.getBlockState(blockpos), iworld, blockpos);
		if (context.getFace() == EnumFacing.UP && iworld.isAirBlock(blockpos) && canPlaceHere) {
			iworld.setBlockState(blockpos, this.crop, 11);
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
