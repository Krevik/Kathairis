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

public class ItemFrup extends ItemSeedFood {

	public final IBlockState state;

	public ItemFrup(int healAmount, float saturation, Block block) {
		super(healAmount, saturation, block, new Properties().group(ModItemGroups.FOOD));
		state = block.getDefaultState();
	}

	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos().up();
		boolean canPlaceHere = ModBlocks.FRUP_PLANT.isValidPosition(iworld.getBlockState(blockpos), iworld, blockpos);
		if (context.getFace() == EnumFacing.UP && iworld.isAirBlock(blockpos) && canPlaceHere) {
			iworld.setBlockState(blockpos, this.state, 11);
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
