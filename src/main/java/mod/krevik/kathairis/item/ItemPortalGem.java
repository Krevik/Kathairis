package mod.krevik.kathairis.item;

import mod.krevik.kathairis.creativetab.ModCreativeTabs;
import mod.krevik.kathairis.init.ModBlocks;
import mod.krevik.kathairis.util.ModUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * The same as an Iron Ingot or a Gold Ingot but for our stuff
 *
 * @author Cadiboo
 */
public class ItemPortalGem extends Item {

	public ItemPortalGem() {
		ModUtil.setCreativeTab(this, ModCreativeTabs.MISC);
	}

	@Override
	public boolean isBeaconPayment(final ItemStack stack) {
		return true;
	}

	@Override
	@Nonnull
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.EPIC;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	@Nonnull
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (ModUtil.getLogicalSide(worldIn) == Side.SERVER) {
			// try and spawn the portal on the opposite side of the block clicked i.e. the one closest to the player
			if (ModBlocks.KATHAIRIS_PORTAL.trySpawnPortal(worldIn, pos.offset(facing.getOpposite()))) {
				//if we were able to, decrease the stack size
				if (!player.capabilities.isCreativeMode) {
					player.getHeldItem(hand).shrink(1);
				}
			} else {
				return EnumActionResult.FAIL;
			}
		}
		return EnumActionResult.SUCCESS;
	}

}
