package io.github.krevik.kathairis.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
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
public class ItemKathairisAxe extends ItemKathairisTool {

	protected static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new ImmutableMap.Builder<Block, Block>())
			.put(OAK_WOOD, STRIPPED_OAK_WOOD).put(OAK_LOG, STRIPPED_OAK_LOG)
			.put(DARK_OAK_WOOD, STRIPPED_DARK_OAK_WOOD).put(DARK_OAK_LOG, STRIPPED_DARK_OAK_LOG)
			.put(ACACIA_WOOD, STRIPPED_ACACIA_WOOD).put(ACACIA_LOG, STRIPPED_ACACIA_LOG)
			.put(BIRCH_WOOD, STRIPPED_BIRCH_WOOD).put(BIRCH_LOG, STRIPPED_BIRCH_LOG)
			.put(JUNGLE_WOOD, STRIPPED_JUNGLE_WOOD).put(JUNGLE_LOG, STRIPPED_JUNGLE_LOG)
			.put(SPRUCE_WOOD, STRIPPED_SPRUCE_WOOD).put(SPRUCE_LOG, STRIPPED_SPRUCE_LOG)
			.put(MYSTIC_LOG, MYSTIC_LOG_STRIPPED)
			.put(SHINY_LOG, SHINY_LOG_STRIPPED)
			.put(SOUL_LOG, SOUL_LOG_STRIPPED)
			.build();

	//TODO COMPLETE
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			OAK_PLANKS, SPRUCE_PLANKS, BIRCH_PLANKS, JUNGLE_PLANKS, ACACIA_PLANKS, DARK_OAK_PLANKS, BOOKSHELF, OAK_WOOD, SPRUCE_WOOD, BIRCH_WOOD, JUNGLE_WOOD, ACACIA_WOOD, DARK_OAK_WOOD, OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, CHEST, PUMPKIN, CARVED_PUMPKIN, JACK_O_LANTERN, MELON, LADDER, OAK_BUTTON, SPRUCE_BUTTON, BIRCH_BUTTON, JUNGLE_BUTTON, DARK_OAK_BUTTON, ACACIA_BUTTON, OAK_PRESSURE_PLATE, SPRUCE_PRESSURE_PLATE, BIRCH_PRESSURE_PLATE, JUNGLE_PRESSURE_PLATE, DARK_OAK_PRESSURE_PLATE, ACACIA_PRESSURE_PLATE,
			MYSTIC_LOG, MYSTIC_PLANKS, SHINY_LOG, SHINY_PLANKS, MYSTIC_WOOD_STAIRS, SHINY_WOOD_STAIRS, MYSTIC_WOOD_STAIRS, SHINY_WOOD_FENCE, MYSTIC_WOOD_FENCE_GATE, SHINY_WOOD_FENCE_GATE, SOUL_LOG, SOUL_PLANKS, SOUL_WOOD_STAIRS, MYSTIC_WOOD_SLAB, SHINY_WOOD_SLAB, SOUL_WOOD_SLAB, MYSTIC_LOG_STRIPPED, SHINY_LOG_STRIPPED, SOUL_LOG_STRIPPED, MYSTIC_WOOD_TRAPDOOR, SHINY_WOOD_TRAPDOOR, SOUL_WOOD_TRAPDOOR, MYSTIC_WOOD_DOORS, SHINY_WOOD_DOORS, SOUL_WOOD_DOORS
	);

	public ItemKathairisAxe(IItemTier tier) {
		super(tier, EFFECTIVE_ON, new Item.Properties().group(ModItemGroups.TOOLS).addToolType(net.minecraftforge.common.ToolType.AXE, tier.getHarvestLevel()).maxStackSize(1));
	}

	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	public EnumActionResult onItemUse(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.getWorld();
		BlockPos blockpos = p_195939_1_.getPos();
		IBlockState iblockstate = world.getBlockState(blockpos);
		Block block = BLOCK_STRIPPING_MAP.get(iblockstate.getBlock());
		if (block != null) {
			EntityPlayer entityplayer = p_195939_1_.getPlayer();
			world.playSound(entityplayer, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isRemote) {
				world.setBlockState(blockpos, block.getDefaultState().with(BlockRotatedPillar.AXIS, iblockstate.get(BlockRotatedPillar.AXIS)), 11);
				if (entityplayer != null) {
					p_195939_1_.getItem().damageItem(1, entityplayer);
				}
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.PASS;
		}
	}

}
