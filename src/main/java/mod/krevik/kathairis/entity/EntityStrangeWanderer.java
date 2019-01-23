package mod.krevik.kathairis.entity;

import mod.krevik.KCore;
import mod.krevik.enchantment.KathairisEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Calendar;
import java.util.Map;

public class EntityStrangeWanderer extends EntityMob {

	public EntityStrangeWanderer(World worldIn) {
		super(worldIn);
		this.setSize(1F, 2F);
		this.experienceValue = 0;
	}

	@Override
	protected void initEntityAI() {

	}

	@Override
	public boolean attackEntityFrom(@Nonnull final DamageSource source, float amount) {
		if (source.getTrueSource() instanceof EntityPlayer) {
			EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
			Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
			if (map.containsKey(ModEnchantments.Ethereal)) {
				this.damageEntity(source, 6);
				return true;
			}
		}
		return false;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public void notifyDataManagerChange(@Nonnull final DataParameter<?> key) {
		super.notifyDataManagerChange(key);
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityPlayer ep = this.world.getClosestPlayer(posX, posY, posZ, 15, true);
		if (ep != null) {
			this.getLookHelper().setLookPosition(ep.posX, ep.posY + (double) ep.getEyeHeight(), ep.posZ, (float) 100, (float) 100);
		}

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return KCore.instance.proxy.oldman_ambient;
	}

	@Override
	protected SoundEvent getHurtSound(@Nonnull final DamageSource damageSourceIn) {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}

	int month = Calendar.getInstance().get(Calendar.MONTH);
	EntityPlayer lastTalker;

	@Override
	public boolean processInteract(@Nonnull final EntityPlayer player, @Nonnull final EnumHand hand) {
		lastTalker = player;
		System.out.println(month);
		if (month == 11) {
			if (!isGift(player.getHeldItemMainhand())) {
				player.openGui(KCore.instance, KCore.GUI_ENUM.OLDMAN.ordinal(),
						player.world,
						(int) player.posX,
						(int) player.posY,
						(int) player.posZ);
			} else {
				if (!world.isRemote) {
					ItemStack stack = player.getHeldItem(hand);
					if (isGift(stack)) {
						stack.shrink(1);
						player.inventory.addItemStackToInventory(new ItemStack(KCore.christmas_gift, 1));
					}
				}
			}
		} else {
			player.openGui(KCore.instance, KCore.GUI_ENUM.OLDMAN.ordinal(),
					player.world,
					(int) player.posX,
					(int) player.posY,
					(int) player.posZ);
		}
		return super.processInteract(player, hand);
	}

	public boolean isGift(ItemStack itemStack) {
		boolean is = false;
		if (itemStack.getItem().equals(KCore.howler_fur) || itemStack.getItem().equals(KCore.Fungal_Drug) ||
				itemStack.getItem().equals(KCore.skyray_feather)) {
			is = true;
		}
		return is;
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	@Nonnull
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public static void registerFixesLivingFlower(@Nonnull final DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityStrangeWanderer.class);
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(@Nonnull final NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(@Nonnull final NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

	}

	@Override
	public IEntityLivingData onInitialSpawn(@Nonnull final DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		return super.onInitialSpawn(difficulty, livingdata);
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(@Nonnull final DamageSource cause) {
		super.onDeath(cause);
	}

}
