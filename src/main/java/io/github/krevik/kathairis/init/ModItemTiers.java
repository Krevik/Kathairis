package io.github.krevik.kathairis.init;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static io.github.krevik.kathairis.init.ModItems.CRYSTAL_CLUSTER;
import static io.github.krevik.kathairis.init.ModItems.MAGNETHIUM_SHARD;
import static io.github.krevik.kathairis.init.ModItems.MYSTIC_GEM;
import static io.github.krevik.kathairis.init.ModItems.REVENUM_INGOT;
import static io.github.krevik.kathairis.init.ModItems.TITANIUM_INGOT;

/**
 * @author Cadiboo
 */
public enum ModItemTiers implements IItemTier {

	TITANIUM(3, 1000, 7.0F, 2.5F, 12, () -> Ingredient.fromItems(TITANIUM_INGOT)),
	MYSTIC(3, 2000, 12.0F, 3.5F, 12, () -> Ingredient.fromItems(MYSTIC_GEM)),
	REVENUM(3, 800, 7.0F, 3.0F, 18, () -> Ingredient.fromItems(REVENUM_INGOT)),
	CRYSTAL(3, 1000, 2.0F, 4.0F, 22, () -> Ingredient.fromItems(CRYSTAL_CLUSTER)),
	MAGNETHIUM(3, 800, 6.0F, 3.0F, 20, () -> Ingredient.fromItems(MAGNETHIUM_SHARD)),

	;

	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyLoadBase<Ingredient> repairMaterial;

	ModItemTiers(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	@Nonnull
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

}
