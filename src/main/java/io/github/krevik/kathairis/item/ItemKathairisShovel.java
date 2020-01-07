package io.github.krevik.kathairis.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class ItemKathairisShovel extends ItemKathairisTool {

	protected static final Map<Block, BlockState> field_195955_e = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH.getDefaultState()));

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER,
			KATHAIRIS_SAND, SOFT_SAND, KATHAIRIS_DIRT, KATHAIRIS_GRASS, MUD_BLOCK, BLUE_CLOUD, BLUE_CLOUD_CONDENSED, YELLOW_CLOUD, YELLOW_CLOUD_CONDENSED, LAYERED_SAND
	);

	public ItemKathairisShovel(IItemTier tier) {
		super(tier, EFFECTIVE_ON, new Properties().group(ModItemGroups.TOOLS).addToolType(net.minecraftforge.common.ToolType.SHOVEL, tier.getHarvestLevel()).maxStackSize(1));
	}


	@Override
	public boolean canHarvestBlock(BlockState blockIn) {
		Block block = blockIn.getBlock();
		return block == Blocks.SNOW || block == Blocks.SNOW_BLOCK;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		if (context.getFace() != Direction.DOWN && world.getBlockState(blockpos.up()).isAir(world, blockpos.up())) {
			BlockState blockstate = field_195955_e.get(world.getBlockState(blockpos).getBlock());
			if (blockstate != null) {
				PlayerEntity playerentity = context.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(blockpos, blockstate, 11);
					if (playerentity != null) {
						context.getItem().damageItem(1, playerentity, (p_220041_1_) -> {
							p_220041_1_.sendBreakAnimation(context.getHand());
						});
					}
				}

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack p_179218_1_, World p_179218_2_, BlockState p_179218_3_, BlockPos p_179218_4_, LivingEntity p_179218_5_) {
		if(p_179218_1_.getItem()== ModItems.MAGNETHIUM_SHOVEL){
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
