package mod.krevik.kathairis.item;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;

import java.util.Set;

import static mod.krevik.kathairis.init.ModBlocks.BLUE_CLOUD_BRICKS;
import static mod.krevik.kathairis.init.ModBlocks.BLUE_CLOUD_REFINED;
import static mod.krevik.kathairis.init.ModBlocks.BLUE_CRYSTAL;
import static mod.krevik.kathairis.init.ModBlocks.HARDENED_WEATHERED_ROCK;
import static mod.krevik.kathairis.init.ModBlocks.HARDENED_WEATHERED_ROCK_BRICKS;
import static mod.krevik.kathairis.init.ModBlocks.HARDENED_WEATHERED_ROCK_BRICKS_STAIRS;
import static mod.krevik.kathairis.init.ModBlocks.HARDENED_WEATHERED_ROCK_STAIRS;
import static mod.krevik.kathairis.init.ModBlocks.HARDENED_WEATHERED_ROCK_TILES;
import static mod.krevik.kathairis.init.ModBlocks.HARDENED_WEATHERED_ROCK_WALL;
import static mod.krevik.kathairis.init.ModBlocks.IRON_GOLD_BLOCK;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_COBBLESTONE;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_SANDSTONE;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE_STAIRS;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE_TILES;
import static mod.krevik.kathairis.init.ModBlocks.KATHARIAN_STONE_WALL;
import static mod.krevik.kathairis.init.ModBlocks.MAGNETHIUM;
import static mod.krevik.kathairis.init.ModBlocks.MUD_BRICKS;
import static mod.krevik.kathairis.init.ModBlocks.MUD_BRICKS_SLAB;
import static mod.krevik.kathairis.init.ModBlocks.MUD_BRICKS_STAIRS;
import static mod.krevik.kathairis.init.ModBlocks.MUD_BRICKS_WALL;
import static mod.krevik.kathairis.init.ModBlocks.MYSTIC_GEM_BLOCK;
import static mod.krevik.kathairis.init.ModBlocks.REVENUM_ORE;
import static mod.krevik.kathairis.init.ModBlocks.SHINY_ROCK;
import static mod.krevik.kathairis.init.ModBlocks.TITANIUM_BLOCK;
import static mod.krevik.kathairis.init.ModBlocks.TITANIUM_ORE;
import static mod.krevik.kathairis.init.ModBlocks.VIOLET_CRYSTAL;
import static mod.krevik.kathairis.init.ModBlocks.WEATHERED_ROCK;
import static mod.krevik.kathairis.init.ModBlocks.YELLOW_CLOUD_BLOCK;
import static mod.krevik.kathairis.init.ModBlocks.YELLOW_CLOUD_REFINED;
import static mod.krevik.kathairis.init.ModBlocks.YELLOW_CRYSTAL;
import static net.minecraft.init.Blocks.ACTIVATOR_RAIL;
import static net.minecraft.init.Blocks.ANDESITE;
import static net.minecraft.init.Blocks.BLUE_ICE;
import static net.minecraft.init.Blocks.BRICK_SLAB;
import static net.minecraft.init.Blocks.CHISELED_RED_SANDSTONE;
import static net.minecraft.init.Blocks.CHISELED_SANDSTONE;
import static net.minecraft.init.Blocks.COAL_ORE;
import static net.minecraft.init.Blocks.COBBLESTONE;
import static net.minecraft.init.Blocks.COBBLESTONE_SLAB;
import static net.minecraft.init.Blocks.CUT_RED_SANDSTONE;
import static net.minecraft.init.Blocks.CUT_SANDSTONE;
import static net.minecraft.init.Blocks.DETECTOR_RAIL;
import static net.minecraft.init.Blocks.DIAMOND_BLOCK;
import static net.minecraft.init.Blocks.DIAMOND_ORE;
import static net.minecraft.init.Blocks.DIORITE;
import static net.minecraft.init.Blocks.GOLD_BLOCK;
import static net.minecraft.init.Blocks.GOLD_ORE;
import static net.minecraft.init.Blocks.GRANITE;
import static net.minecraft.init.Blocks.ICE;
import static net.minecraft.init.Blocks.IRON_BLOCK;
import static net.minecraft.init.Blocks.IRON_ORE;
import static net.minecraft.init.Blocks.LAPIS_BLOCK;
import static net.minecraft.init.Blocks.LAPIS_ORE;
import static net.minecraft.init.Blocks.MOSSY_COBBLESTONE;
import static net.minecraft.init.Blocks.NETHERRACK;
import static net.minecraft.init.Blocks.NETHER_BRICK_SLAB;
import static net.minecraft.init.Blocks.PACKED_ICE;
import static net.minecraft.init.Blocks.PETRIFIED_OAK_SLAB;
import static net.minecraft.init.Blocks.POLISHED_ANDESITE;
import static net.minecraft.init.Blocks.POLISHED_DIORITE;
import static net.minecraft.init.Blocks.POLISHED_GRANITE;
import static net.minecraft.init.Blocks.POWERED_RAIL;
import static net.minecraft.init.Blocks.PURPUR_SLAB;
import static net.minecraft.init.Blocks.QUARTZ_SLAB;
import static net.minecraft.init.Blocks.RAIL;
import static net.minecraft.init.Blocks.REDSTONE_ORE;
import static net.minecraft.init.Blocks.RED_SANDSTONE;
import static net.minecraft.init.Blocks.RED_SANDSTONE_SLAB;
import static net.minecraft.init.Blocks.SANDSTONE;
import static net.minecraft.init.Blocks.SANDSTONE_SLAB;
import static net.minecraft.init.Blocks.SMOOTH_QUARTZ;
import static net.minecraft.init.Blocks.SMOOTH_RED_SANDSTONE;
import static net.minecraft.init.Blocks.SMOOTH_SANDSTONE;
import static net.minecraft.init.Blocks.SMOOTH_STONE;
import static net.minecraft.init.Blocks.STONE;
import static net.minecraft.init.Blocks.STONE_BRICK_SLAB;
import static net.minecraft.init.Blocks.STONE_BUTTON;
import static net.minecraft.init.Blocks.STONE_PRESSURE_PLATE;
import static net.minecraft.init.Blocks.STONE_SLAB;

public class ItemKathairisPickaxe extends ItemKathairisTool {

	//TODO COPY SET EFFECTIVE_ON + ADD CRYSTAL PICKAXE OPTION
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			ACTIVATOR_RAIL, COAL_ORE, COBBLESTONE, DETECTOR_RAIL, DIAMOND_BLOCK, DIAMOND_ORE, POWERED_RAIL, GOLD_BLOCK, GOLD_ORE, ICE, IRON_BLOCK, IRON_ORE, LAPIS_BLOCK, LAPIS_ORE, MOSSY_COBBLESTONE, NETHERRACK, PACKED_ICE, BLUE_ICE, RAIL, REDSTONE_ORE, SANDSTONE, CHISELED_SANDSTONE, CUT_SANDSTONE, CHISELED_RED_SANDSTONE, CUT_RED_SANDSTONE, RED_SANDSTONE, STONE, GRANITE, POLISHED_GRANITE, DIORITE, POLISHED_DIORITE, ANDESITE, POLISHED_ANDESITE, STONE_SLAB, SANDSTONE_SLAB, PETRIFIED_OAK_SLAB, COBBLESTONE_SLAB, BRICK_SLAB, STONE_BRICK_SLAB, NETHER_BRICK_SLAB, QUARTZ_SLAB, RED_SANDSTONE_SLAB, PURPUR_SLAB, SMOOTH_QUARTZ, SMOOTH_RED_SANDSTONE, SMOOTH_SANDSTONE, SMOOTH_STONE, STONE_BUTTON, STONE_PRESSURE_PLATE,
			KATHARIAN_STONE, TITANIUM_ORE, TITANIUM_BLOCK, KATHARIAN_STONE_TILES, WEATHERED_ROCK, HARDENED_WEATHERED_ROCK, HARDENED_WEATHERED_ROCK_TILES, HARDENED_WEATHERED_ROCK_BRICKS, SHINY_ROCK, REVENUM_ORE, YELLOW_CRYSTAL, BLUE_CRYSTAL, VIOLET_CRYSTAL, HARDENED_WEATHERED_ROCK_STAIRS, KATHARIAN_STONE_STAIRS, KATHARIAN_STONE_WALL, HARDENED_WEATHERED_ROCK_WALL, BLUE_CLOUD_BRICKS, YELLOW_CLOUD_BLOCK, MAGNETHIUM, HARDENED_WEATHERED_ROCK_BRICKS_STAIRS, MYSTIC_GEM_BLOCK, KATHARIAN_COBBLESTONE, IRON_GOLD_BLOCK, YELLOW_CLOUD_REFINED, BLUE_CLOUD_REFINED, KATHARIAN_SANDSTONE, MUD_BRICKS, MUD_BRICKS_SLAB, MUD_BRICKS_STAIRS, MUD_BRICKS_WALL
	);

	public ItemKathairisPickaxe(IItemTier tier, ItemGroup group) {
		super(tier, EFFECTIVE_ON, new Item.Properties().group(group).addToolType(ToolType.PICKAXE, tier.getHarvestLevel()).maxStackSize(1));
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
