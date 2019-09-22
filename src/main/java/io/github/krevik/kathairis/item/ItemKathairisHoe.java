package io.github.krevik.kathairis.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.github.krevik.kathairis.init.ModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

/**
 * @author Krevik
 */
public class ItemKathairisHoe extends TieredItem {


	//TODO Krevik wtf
	protected static final Map<Block, BlockState> HOE_LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND.getDefaultState(), Blocks.GRASS_PATH, Blocks.FARMLAND.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState()));
	private final float speed;

	public ItemKathairisHoe(IItemTier tier) {
		super(tier, new Properties().group(ModItemGroups.TOOLS).maxStackSize(1));
		this.speed = 1;
	}

	public ActionResultType onItemUse(ItemUseContext p_195939_1_) {
		World world = p_195939_1_.getWorld();
		BlockPos blockpos = p_195939_1_.getPos();
		int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(p_195939_1_);
		if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		if (p_195939_1_.getFace() != Direction.DOWN && world.isAirBlock(blockpos.up())) {
			BlockState iblockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
			if (iblockstate != null) {
				PlayerEntity entityplayer = p_195939_1_.getPlayer();
				world.playSound(entityplayer, blockpos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(blockpos, iblockstate, 11);
					if (entityplayer != null) {
						p_195939_1_.getItem().damageItem(1, entityplayer, (p_220043_1_) -> {
							p_220043_1_.sendBreakAnimation(p_195939_1_.getHand());
						});					}
				}

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damageItem(1, attacker, (p_220042_0_) -> {
			p_220042_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});		return true;
	}

	public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		if (equipmentSlot == EquipmentSlotType.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, AttributeModifier.Operation.ADDITION));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) this.speed, AttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

}
