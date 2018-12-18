package mod.krevik.entity;

import javax.annotation.Nullable;

import mod.krevik.entity.ai.EntityAIHealTargets;
import mod.krevik.KCore;
import mod.krevik.util.MysticLootTables;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntityLivingFlower extends EntityAnimal
{
    private static final DataParameter<Boolean> canDespawn = EntityDataManager.<Boolean>createKey(EntityLivingFlower.class, DataSerializers.BOOLEAN);
    public EntityLivingFlower(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3F, 0.5F);
        this.experienceValue=10;
    }

    public void deallowDespawning(){
        dataManager.set(canDespawn,false);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAIHealTargets(this));
    }
    
    @Override
    protected boolean canDespawn()
    {
        return dataManager.get(canDespawn).booleanValue();
    }
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(canDespawn, Boolean.valueOf(true));
    }



    public void notifyDataManagerChange(DataParameter<?> key)
    {

        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	this.motionY=-1;
    	this.motionX=0;
    	this.motionZ=0;
    	this.posX=this.lastTickPosX;
    	this.posZ=this.lastTickPosZ;
        this.rotationPitch=0;
        this.rotationYaw=0;
        this.rotationYawHead=0;
        if(this.world.isRemote) {
        	if(this.rand.nextInt(30)==1) {
        			this.world.spawnParticle(EnumParticleTypes.HEART, this.posX, this.posY+0.5, this.posZ, 0, 0.2, 0);
        	}
        }
        super.onLivingUpdate();

    }
    
    public void onUpdate() {
    	super.onUpdate();
    }


    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }


    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.FLOWER_POT && !player.capabilities.isCreativeMode && !this.isChild())
        {
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(KCore.PotWithLivingFlower));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(KCore.PotWithLivingFlower)))
            {
                player.dropItem(new ItemStack(KCore.PotWithLivingFlower), false);
            }
            this.posY=-20;
            this.setDead();
            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_LIVINGFLOWER;
    }

    public static void registerFixesLivingFlower(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityLivingFlower.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("canDespawn",this.dataManager.get(canDespawn));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        dataManager.set(canDespawn,compound.getBoolean("canDespawn"));
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }
}