package mod.krevik.entity;

import javax.annotation.Nullable;

import mod.krevik.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import mod.krevik.KCore;
import mod.krevik.util.MysticLootTables;

import net.minecraft.block.SoundType;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityCamel extends AbstractHorse
{

    public EntityCamel(World worldIn)
    {
        super(worldIn);
        this.setSize(1.6F, 1.5F);
    }
    
    protected void initEntityAI()
    {
    	super.initEntityAI();
        this.tasks.addTask(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
    }
    
    protected void entityInit()
    {
        super.entityInit();

    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

    }
    
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);

    }

 
    /**
     * Updates the items in the saddle and armor slots of the horse's inventory.
     */
    protected void updateHorseSlots()
    {
        super.updateHorseSlots();
    }

    /**
     * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
     */
    public void onInventoryChanged(IInventory invBasic)
    {
        super.onInventoryChanged(invBasic);
    }

    protected void playGallopSound(SoundType p_190680_1_)
    {
        super.playGallopSound(p_190680_1_);

        if (this.rand.nextInt(10) == 0)
        {
            this.playSound(KCore.instance.proxy.camel_breath, p_190680_1_.getVolume() * 0.6F, p_190680_1_.getPitch());
        }
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.getModifiedMaxHealth());
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getModifiedMovementSpeed());
        this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.world.isRemote && this.dataManager.isDirty())
        {
            this.dataManager.setClean();
        }
    }

    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return KCore.instance.proxy.camel_ambient;
    }

    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return KCore.instance.proxy.camel_dead;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        super.getHurtSound(damageSourceIn);
        return KCore.instance.proxy.camel_hurt;
    }

    protected SoundEvent getAngrySound()
    {
        super.getAngrySound();
        return SoundEvents.ENTITY_HORSE_ANGRY;
    }

    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_CAMEL;
    }

    	   public boolean processInteract(EntityPlayer player, EnumHand hand)
    	    {
    	        ItemStack itemstack = player.getHeldItem(hand);
    	        boolean flag = !itemstack.isEmpty();

    	        if (flag && itemstack.getItem() == Items.SPAWN_EGG)
    	        {
    	            return super.processInteract(player, hand);
    	        }
    	        else
    	        {
    	            if (!this.isChild())
    	            {
    	                if (this.isTame() && player.isSneaking())
    	                {
    	                    this.openGUI(player);
    	                    return true;
    	                }

    	                if (this.isBeingRidden())
    	                {
    	                    return super.processInteract(player, hand);
    	                }
    	            }

    	            if (flag)
    	            {
    	                if (this.handleEating(player, itemstack))
    	                {
    	                    if (!player.capabilities.isCreativeMode)
    	                    {
    	                        itemstack.shrink(1);
    	                    }

    	                    return true;
    	                }

    	                if (itemstack.interactWithEntity(player, this, hand))
    	                {
    	                    return true;
    	                }

    	                if (!this.isTame())
    	                {
    	                    this.makeMad();
    	                    return true;
    	                }

    	                boolean flag1 = HorseArmorType.getByItemStack(itemstack) != HorseArmorType.NONE;
    	                boolean flag2 = !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE;

    	                if (flag1 || flag2)
    	                {
    	                    this.openGUI(player);
    	                    return true;
    	                }
    	            }

    	            if (this.isChild())
    	            {
    	                return super.processInteract(player, hand);
    	            }
    	            else
    	            {
    	                this.mountTo(player);
    	                return true;
    	            }
    	        }
    }
    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
    	return false;
    }

    public EntityCamel createChild(EntityAgeable ageable)
    {
        return new EntityCamel(this.world);
    }



    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        livingdata = new EntityCamel.GroupData();

        return livingdata;
    }

    public static class GroupData implements IEntityLivingData
        {
            public GroupData()
            {
            	
            }
        }
}