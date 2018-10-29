package com.Krevik.Entities;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.Krevik.Entities.AI.EntityAIAvoidMovingSands;
import com.Krevik.Entities.AI.EntityAIHowlerAttackStun;
import com.Krevik.Entities.AI.EntityAITargetSpecified;
import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityHowler extends EntityMob
{
    private static final DataParameter<Float> animTimer = EntityDataManager.<Float>createKey(EntityHowler.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> shouldAnimTail = EntityDataManager.<Boolean>createKey(EntityHowler.class, DataSerializers.BOOLEAN);


    public EntityHowler(World worldIn)
    {
        super(worldIn);
        this.setSize(0.85F, 1F);
    }


    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(0, new EntityAIAvoidMovingSands(this,1.2D));
        this.tasks.addTask(2, new EntityAIHowlerAttackStun(this, 1.0D, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(4, new EntityAITargetSpecified<>(this, EntityAnimal.class, false, new Predicate<Entity>()
        {
            public boolean apply(@Nullable Entity e)
            {
                return e instanceof EntitySheep || e instanceof EntityRabbit||e instanceof EntityPig||
                        e instanceof EntityBison||e instanceof EntityBigTurtle||e instanceof EntityGecko||
                        e instanceof EntityStrangeWanderer|| e instanceof EntityChicken||
                        e instanceof EntityCamel||e instanceof EntityCloudySlime|| e instanceof EntityLlama||
                        e instanceof EntityVillager;
            }
        }));
        this.experienceValue=15;

        this.applyEntityAI();
    }
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    protected void applyEntityAI()
    {

    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);

    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(animTimer, Float.valueOf(0));
        this.dataManager.register(shouldAnimTail, Boolean.valueOf(false));
    }

    public boolean getShouldAnimTail()
    {
        return (this.dataManager.get(shouldAnimTail));
    }

    public void setShouldAnimTail(boolean value)
    {
        this.dataManager.set(shouldAnimTail,value);
    }

    public void setAnimTimer(float time){
        this.dataManager.set(animTimer,time);
    }

    public float getAnimTimer(){
        return this.dataManager.get(animTimer);
    }
    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        return super.getExperiencePoints(player);
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
        super.onLivingUpdate();
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);

        if (flag)
        {
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();

            if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F)
            {
                entityIn.setFire(2 * (int)f);
            }
        }

        return flag;
    }

    public int shouldAnimEars=0;
    public void onUpdate() {
    	super.onUpdate();
        if(!getShouldAnimTail()) {
            if(getRNG().nextInt(1000)==0) {
                setShouldAnimTail(true);
            }
        }
        if(getShouldAnimTail()) {
            setAnimTimer(getAnimTimer()+2);
            if(getAnimTimer()>200) {
                setAnimTimer(0);
                setShouldAnimTail(false);
            }
        }
        if(shouldAnimEars==0){
            if(getRNG().nextInt(1000)==0){
                shouldAnimEars=1;
            }
        }
        if(shouldAnimEars==1){
            if(getRNG().nextInt(100)==0){
                shouldAnimEars=0;
            }
        }
 
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.cproxy.Howler_living;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return KCore.cproxy.Howler_dead;
    }

    protected SoundEvent getStepSound()
    {
        return null;
    }

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_HOWLER;
    }

    public static void registerFixesHowler(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityHowler.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("shouldAnimTail",getShouldAnimTail());
        compound.setFloat("animTimer",getAnimTimer());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setShouldAnimTail(compound.getBoolean("shouldAnimTail"));
        setAnimTimer(compound.getFloat("animTimer"));
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);

    }

    protected boolean canEquipItem(ItemStack stack)
    {
        return false;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }


    class GroupData implements IEntityLivingData
    {
        public boolean isChild;

        private GroupData(boolean p_i47328_2_)
        {
            this.isChild = p_i47328_2_;
        }
    }

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        return livingdata;
    }

}