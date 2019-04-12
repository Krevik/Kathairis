package io.github.krevik.kathairis.init;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static io.github.krevik.kathairis.init.ModItems.*;
import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

/**
 * @author Cadiboo
 */
public enum ModArmorMaterials implements IArmorMaterial {

	TITANIUM("titanium", 22, new int[]{3, 5, 7, 3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () ->
			Ingredient.fromItems(TITANIUM_INGOT)
	),
	MYSTIC("mystic", 30, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> {
		return Ingredient.fromItems(MYSTIC_GEM);
	}),
	CLOUD("cloud", 5, new int[]{1, 2, 3, 1}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> {
		return Ingredient.fromItems(CLOUD_ESSENCE);
	}),
	REVENUM("revenum", 15, new int[]{3, 5, 7, 3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> {
		return Ingredient.fromItems(REVENUM_INGOT);
	}),

	;

	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final LazyLoadBase<Ingredient> repairMaterial;

	ModArmorMaterials(
			@Nonnull final String name,
			final int maxDamageFactor,
			@Nonnull final int[] damageReductionAmountArray,
			final int enchantability,
			@Nonnull final SoundEvent soundEvent,
			final float toughness,
			@Nonnull final Supplier<Ingredient> repairMaterial
	) {
		this(new ResourceLocation(MOD_ID, name), maxDamageFactor, damageReductionAmountArray, enchantability, soundEvent, toughness, repairMaterial);
	}

	ModArmorMaterials(
			@Nonnull final ResourceLocation name,
			final int maxDamageFactor,
			@Nonnull final int[] damageReductionAmountArray,
			final int enchantability,
			@Nonnull final SoundEvent soundEvent,
			final float toughness,
			@Nonnull final Supplier<Ingredient> repairMaterial
	) {
		this.name = name.toString();
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.repairMaterial = new LazyLoadBase<>(repairMaterial);
	}

	@Override
	public int getDurability(@Nonnull final EntityEquipmentSlot slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDamageReductionAmount(@Nonnull final EntityEquipmentSlot slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	@Nonnull
	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	@Override
	@Nonnull
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@Override
	@Nonnull
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

}
