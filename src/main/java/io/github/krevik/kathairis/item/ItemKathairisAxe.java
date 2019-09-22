package io.github.krevik.kathairis.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class ItemKathairisAxe extends ItemKathairisTool {

	protected static final Map<Block, Block> BLOCK_STRIPPING_MAP = (new ImmutableMap.Builder<Block, Block>())
			.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
			.put(MYSTIC_LOG, MYSTIC_LOG_STRIPPED)
			.put(SHINY_LOG, SHINY_LOG_STRIPPED)
			.put(SOUL_LOG, SOUL_LOG_STRIPPED)
			.build();

	//TODO COMPLETE
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.BIRCH_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.ACACIA_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.BOOKSHELF, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.BIRCH_WOOD, Blocks.JUNGLE_WOOD, Blocks.ACACIA_WOOD, Blocks.DARK_OAK_WOOD, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.BIRCH_LOG, Blocks.JUNGLE_LOG, Blocks.ACACIA_LOG, Blocks.DARK_OAK_LOG, Blocks.CHEST, Blocks.PUMPKIN, Blocks.CARVED_PUMPKIN, Blocks.JACK_O_LANTERN, Blocks.MELON, Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.OAK_PRESSURE_PLATE, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.BIRCH_PRESSURE_PLATE, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.ACACIA_PRESSURE_PLATE,
			MYSTIC_LOG, MYSTIC_PLANKS, SHINY_LOG, SHINY_PLANKS, MYSTIC_WOOD_STAIRS, SHINY_WOOD_STAIRS, MYSTIC_WOOD_STAIRS, SHINY_WOOD_FENCE, MYSTIC_WOOD_FENCE_GATE, SHINY_WOOD_FENCE_GATE, SOUL_LOG, SOUL_PLANKS, SOUL_WOOD_STAIRS, MYSTIC_WOOD_SLAB, SHINY_WOOD_SLAB, SOUL_WOOD_SLAB, MYSTIC_LOG_STRIPPED, SHINY_LOG_STRIPPED, SOUL_LOG_STRIPPED, MYSTIC_WOOD_TRAPDOOR, SHINY_WOOD_TRAPDOOR, SOUL_WOOD_TRAPDOOR, MYSTIC_WOOD_DOORS, SHINY_WOOD_DOORS, SOUL_WOOD_DOORS
	);

	public ItemKathairisAxe(IItemTier tier) {
		super(tier, EFFECTIVE_ON, new Item.Properties().group(ModItemGroups.TOOLS).addToolType(net.minecraftforge.common.ToolType.AXE, tier.getHarvestLevel()).maxStackSize(1));
	}

	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.LEAVES ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	public ActionResultType onItemUse(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.getWorld();
		BlockPos blockpos = p_195939_1_.getPos();
		BlockState iblockstate = world.getBlockState(blockpos);
		Block block = BLOCK_STRIPPING_MAP.get(iblockstate.getBlock());
		if (block != null) {
			PlayerEntity entityplayer = p_195939_1_.getPlayer();
			world.playSound(entityplayer, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			if (!world.isRemote) {
				world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS, iblockstate.get(RotatedPillarBlock.AXIS)), 11);
				if (entityplayer != null) {
					p_195939_1_.getItem().damageItem(1, p_195939_1_.getPlayer(), (p_220045_0_) -> {
						p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
					});
				}
			}

			return ActionResultType.SUCCESS;
		} else {
			return ActionResultType.PASS;
		}
	}

}
