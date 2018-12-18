package com.krevik.Entities;

import javax.annotation.Nullable;

import com.krevik.Main.MysticLootTables;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityCactiSpore extends EntityMob
{
    private static final DataParameter<Boolean> canDespawn = EntityDataManager.<Boolean>createKey(EntityCactiSpore.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> spikeTimer = EntityDataManager.<Integer>createKey(EntityCactiSpore.class, DataSerializers.VARINT);

    public EntityCactiSpore(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 1F);
        this.experienceValue=30;
    }

    public void deallowDespawning(){
        dataManager.set(canDespawn,false);
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    public int getSpikeTimer()
    {
        return dataManager.get(spikeTimer);
    }

    public void setSpikeTimer(int meta) {
        dataManager.set(spikeTimer, meta);
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(canDespawn, Boolean.valueOf(true));
        this.dataManager.register(spikeTimer, 0);
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
        super.onLivingUpdate();
    }

    public void onUpdate() {
        super.onUpdate();
        if (getAttackTarget() != null) {
            if (getDistance(getAttackTarget()) < 2.5f) {
                setSpikeTimer(MathHelper.clamp(getSpikeTimer() + 10, 0, 100));
            }
        }
            setSpikeTimer(MathHelper.clamp(getSpikeTimer() - 1, 0, 100));
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        this.setLastAttackedEntity(entityIn);
        if(entityIn instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase) entityIn;
            if(getRNG().nextInt(4)==0) {
                target.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 100, 3));
            }
        }
        return super.attackEntityAsMob(entityIn);
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
    return super.processInteract(player, hand);
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
        return MysticLootTables.LOOT_CACTISPORE;
    }


    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("canDespawn",this.dataManager.get(canDespawn));
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        setPositionAndUpdate(posX+getRNG().nextFloat()-0.5f,posY,posZ+getRNG().nextFloat()-0.5f);
        return livingdata;
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