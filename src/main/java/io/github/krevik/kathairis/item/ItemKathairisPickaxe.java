package io.github.krevik.kathairis.item;

import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import java.util.List;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class ItemKathairisPickaxe extends ItemKathairisTool {


	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX,
			KATHAIRIS_STONE, TITANIUM_ORE, TITANIUM_BLOCK, KATHAIRIS_STONE_TILES, WEATHERED_ROCK, HARDENED_WEATHERED_ROCK, HARDENED_WEATHERED_ROCK_TILES, HARDENED_WEATHERED_ROCK_BRICKS, SHINY_ROCK, REVENUM_ORE, YELLOW_CRYSTAL, BLUE_CRYSTAL, VIOLET_CRYSTAL, HARDENED_WEATHERED_ROCK_STAIRS, KATHAIRIS_STONE_STAIRS, KATHAIRIS_STONE_WALL, HARDENED_WEATHERED_ROCK_WALL, BLUE_CLOUD_BRICKS, YELLOW_CLOUD_BLOCK, MAGNETHIUM, HARDENED_WEATHERED_ROCK_BRICKS_STAIRS, MYSTIC_GEM_BLOCK, KATHAIRIS_COBBLESTONE, IRON_GOLD_BLOCK, YELLOW_CLOUD_REFINED, BLUE_CLOUD_REFINED, KATHAIRIS_SANDSTONE, MUD_BRICKS, MUD_BRICKS_SLAB, MUD_BRICKS_STAIRS, MUD_BRICKS_WALL
	);

	public ItemKathairisPickaxe(IItemTier tier) {
		super(tier, EFFECTIVE_ON, new Properties().group(ModItemGroups.TOOLS).addToolType(ToolType.PICKAXE, tier.getHarvestLevel()).maxStackSize(1));
	}

	@Override
	public boolean canHarvestBlock(BlockState blockIn) {
		Block block = blockIn.getBlock();
		int i = this.getTier().getHarvestLevel();
		if (blockIn.getHarvestTool() == net.minecraftforge.common.ToolType.PICKAXE) {
			return i >= blockIn.getHarvestLevel();
		}
		Material material = blockIn.getMaterial();
		return material == Material.ROCK || material == Material.IRON || material == Material.ANVIL;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return material != Material.IRON && material != Material.ANVIL && material != Material.ROCK ? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack p_179218_1_, World p_179218_2_, BlockState p_179218_3_, BlockPos p_179218_4_, LivingEntity p_179218_5_) {
		if(p_179218_1_.getItem()== ModItems.MAGNETHIUM_PICKAXE){
			List<ItemEntity> e = p_179218_2_.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(p_179218_4_.getX() - 5, p_179218_4_.getY() - 5, p_179218_4_.getZ() - 5, p_179218_4_.getX() + 5, p_179218_4_.getY() + 5, p_179218_4_.getZ() + 5));
			if(!e.isEmpty()){
				for(ItemEntity entity:e){
					double motionX = p_179218_5_.getPosition().getX()-entity.getPosition().getX();
					double motionY = p_179218_5_.getPosition().getY()-entity.getPosition().getY();
					double motionZ = p_179218_5_.getPosition().getZ()-entity.getPosition().getZ();
					entity.setMotion(motionX,motionY,motionZ);
				}
			}
		}
		return super.onBlockDestroyed(p_179218_1_, p_179218_2_, p_179218_3_, p_179218_4_, p_179218_5_);
	}
}
