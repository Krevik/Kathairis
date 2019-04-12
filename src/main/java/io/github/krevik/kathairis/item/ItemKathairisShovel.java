package io.github.krevik.kathairis.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.*;
import static net.minecraft.init.Blocks.*;

/**
 * @author Krevik
 */
public class ItemKathairisShovel extends ItemKathairisTool {

	protected static final Map<Block, IBlockState> field_195955_e = Maps.newHashMap(ImmutableMap.of(GRASS_BLOCK, GRASS_PATH.getDefaultState()));

	//TODO COMPLETE
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			CLAY, DIRT, COARSE_DIRT, PODZOL, FARMLAND, GRASS_BLOCK, GRAVEL, MYCELIUM, SAND, RED_SAND, SNOW_BLOCK, SNOW, SOUL_SAND, GRASS_PATH, WHITE_CONCRETE_POWDER, ORANGE_CONCRETE_POWDER, MAGENTA_CONCRETE_POWDER, LIGHT_BLUE_CONCRETE_POWDER, YELLOW_CONCRETE_POWDER, LIME_CONCRETE_POWDER, PINK_CONCRETE_POWDER, GRAY_CONCRETE_POWDER, LIGHT_GRAY_CONCRETE_POWDER, CYAN_CONCRETE_POWDER, PURPLE_CONCRETE_POWDER, BLUE_CONCRETE_POWDER, BROWN_CONCRETE_POWDER, GREEN_CONCRETE_POWDER, RED_CONCRETE_POWDER, BLACK_CONCRETE_POWDER,
			KATHAIRIS_SAND, SOFT_SAND, KATHAIRIS_DIRT, KATHAIRIS_GRASS, MUD_BLOCK, BLUE_CLOUD, BLUE_CLOUD_CONDENSED, YELLOW_CLOUD, YELLOW_CLOUD_CONDENSED, LAYERED_SAND
	);

	public ItemKathairisShovel(IItemTier tier) {
		super(tier, EFFECTIVE_ON, new Item.Properties().group(ModItemGroups.TOOLS).addToolType(net.minecraftforge.common.ToolType.SHOVEL, tier.getHarvestLevel()).maxStackSize(1));
	}

	public EnumActionResult onItemUse(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.getWorld();
		BlockPos blockpos = p_195939_1_.getPos();
		if (p_195939_1_.getFace() != EnumFacing.DOWN && world.getBlockState(blockpos.up()).isAir()) {
			IBlockState iblockstate = field_195955_e.get(world.getBlockState(blockpos).getBlock());
			if (iblockstate != null) {
				EntityPlayer entityplayer = p_195939_1_.getPlayer();
				world.playSound(entityplayer, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(blockpos, iblockstate, 11);
					if (entityplayer != null) {
						p_195939_1_.getItem().damageItem(1, entityplayer);
					}
				}

				return EnumActionResult.SUCCESS;
			}
		}

		return EnumActionResult.PASS;
	}

	public boolean canHarvestBlock(IBlockState blockIn) {
		Block block = blockIn.getBlock();
		return block == SNOW || block == SNOW_BLOCK;
	}

}
