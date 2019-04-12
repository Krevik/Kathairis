package io.github.krevik.kathairis.item;

import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;

import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.*;
import static net.minecraft.init.Blocks.*;

/**
 * @author Krevik
 */
public class ItemKathairisPickaxe extends ItemKathairisTool {

	//TODO COPY SET EFFECTIVE_ON + ADD CRYSTAL PICKAXE OPTION
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			ACTIVATOR_RAIL, COAL_ORE, COBBLESTONE, DETECTOR_RAIL, DIAMOND_BLOCK, DIAMOND_ORE, POWERED_RAIL, GOLD_BLOCK, GOLD_ORE, ICE, IRON_BLOCK, IRON_ORE, LAPIS_BLOCK, LAPIS_ORE, MOSSY_COBBLESTONE, NETHERRACK, PACKED_ICE, BLUE_ICE, RAIL, REDSTONE_ORE, SANDSTONE, CHISELED_SANDSTONE, CUT_SANDSTONE, CHISELED_RED_SANDSTONE, CUT_RED_SANDSTONE, RED_SANDSTONE, STONE, GRANITE, POLISHED_GRANITE, DIORITE, POLISHED_DIORITE, ANDESITE, POLISHED_ANDESITE, STONE_SLAB, SANDSTONE_SLAB, PETRIFIED_OAK_SLAB, COBBLESTONE_SLAB, BRICK_SLAB, STONE_BRICK_SLAB, NETHER_BRICK_SLAB, QUARTZ_SLAB, RED_SANDSTONE_SLAB, PURPUR_SLAB, SMOOTH_QUARTZ, SMOOTH_RED_SANDSTONE, SMOOTH_SANDSTONE, SMOOTH_STONE, STONE_BUTTON, STONE_PRESSURE_PLATE,
			KATHAIRIS_STONE, TITANIUM_ORE, TITANIUM_BLOCK, KATHAIRIS_STONE_TILES, WEATHERED_ROCK, HARDENED_WEATHERED_ROCK, HARDENED_WEATHERED_ROCK_TILES, HARDENED_WEATHERED_ROCK_BRICKS, SHINY_ROCK, REVENUM_ORE, YELLOW_CRYSTAL, BLUE_CRYSTAL, VIOLET_CRYSTAL, HARDENED_WEATHERED_ROCK_STAIRS, KATHAIRIS_STONE_STAIRS, KATHAIRIS_STONE_WALL, HARDENED_WEATHERED_ROCK_WALL, BLUE_CLOUD_BRICKS, YELLOW_CLOUD_BLOCK, MAGNETHIUM, HARDENED_WEATHERED_ROCK_BRICKS_STAIRS, MYSTIC_GEM_BLOCK, KATHAIRIS_COBBLESTONE, IRON_GOLD_BLOCK, YELLOW_CLOUD_REFINED, BLUE_CLOUD_REFINED, KATHAIRIS_SANDSTONE, MUD_BRICKS, MUD_BRICKS_SLAB, MUD_BRICKS_STAIRS, MUD_BRICKS_WALL
	);

	public ItemKathairisPickaxe(IItemTier tier) {
		super(tier, EFFECTIVE_ON, new Item.Properties().group(ModItemGroups.TOOLS).addToolType(ToolType.PICKAXE, tier.getHarvestLevel()).maxStackSize(1));
	}

	/**
	 * Check whether this Item can harvest the given Block
	 */
	public boolean canHarvestBlock(IBlockState blockIn) {
		int i = this.getTier().getHarvestLevel();
		if (blockIn.getHarvestTool() == ToolType.PICKAXE) {
			return i >= blockIn.getHarvestLevel();
		}
		Material material = blockIn.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
	}

	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

}
