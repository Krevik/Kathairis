package io.github.krevik.kathairis.entity;

import com.google.common.base.Predicate;
import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.entity.ai.EntityAIHowlerAttackStun;
import io.github.krevik.kathairis.entity.ai.EntityAITargetSpecified;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityHowler extends MonsterEntity
{
    private static final DataParameter<Float> animTimer = EntityDataManager.createKey(EntityHowler.class, DataSerializers.FLOAT);
    private static final DataParameter<Float> animTimerTail = EntityDataManager.createKey(EntityHowler.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> shouldAnimJaw = EntityDataManager.createKey(EntityHowler.class, DataSerializers.BOOLEAN);


    public EntityHowler(World worldIn)
    {
        super(ModEntities.HOWLER,worldIn);
    }

    public EntityHowler(EntityType<EntityHowler> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
        this.goalSelector.addGoal(4, new EntityAIHowlerAttackStun(this, 1.0D, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(2, new EntityAITargetSpecified<AnimalEntity>(this, AnimalEntity.class, false, new Predicate<LivingEntity>()
        {

            public boolean apply(@Nullable LivingEntity e)
            {
                return e instanceof SheepEntity || e instanceof RabbitEntity ||e instanceof PigEntity ||
                        e instanceof EntityBison||e instanceof EntityBigTurtle||e instanceof EntityGecko||
                        e instanceof EntityStrangeWanderer|| e instanceof ChickenEntity ||
                        e instanceof EntityCamel||e instanceof EntityCloudySlime|| e instanceof LlamaEntity ||
                        e instanceof VillagerEntity || e instanceof CowEntity;
            }
        }));
        this.experienceValue=15;

    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);

    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_HOWLER;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(animTimer, Float.valueOf(0));
        this.getDataManager().register(animTimerTail, Float.valueOf(0));
        this.getDataManager().register(shouldAnimJaw, Boolean.valueOf(false));

    }

    public void setAnimTimer(float time){
        this.getDataManager().set(animTimer,time);
    }

    public float getAnimTimer(){
        return this.getDataManager().get(animTimer);
    }

    public void setAnimTimerTail(float time){
        this.getDataManager().set(animTimerTail,time);
    }

    public float getAnimTimerTail(){
        return this.getDataManager().get(animTimerTail);
    }

    public void setShouldAnimJaw(boolean should){
        this.getDataManager().set(shouldAnimJaw,should);
    }

    public boolean getShouldAnimJaw(){
        return this.getDataManager().get(shouldAnimJaw);
    }

    @Override
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

    int someTimer=0;
    @Override
    public void tick() {
    	super.tick();
    	if(getShouldAnimJaw()==false){
    	    if(rand.nextInt(500)==0){
    	        setShouldAnimJaw(true);
            }
        }else{
    	    someTimer++;
    	    if(someTimer>300){
    	        someTimer=0;
    	        setShouldAnimJaw(false);
            }
        }

            setAnimTimer(getAnimTimer() + MathHelper.clamp(limbSwingAmount*limbSwing*10*movedDistance,0,25));
            if (getAnimTimer() > 300) {
                setAnimTimer(0);
            }

            if(getAnimTimerTail()==0){
                if(rand.nextInt(500)==0){
                    setAnimTimerTail(getAnimTimerTail()+30);
                }
            }else{
                setAnimTimerTail(getAnimTimerTail()+20);
                if(getAnimTimerTail()>300){
                    setAnimTimerTail(0);
                }
            }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MOB_HOWLER_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.MOB_HOWLER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.MOB_HOWLER_DEAD;
    }

    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.UNDEAD;
    }


    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putFloat("animTimer",getAnimTimer());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        setAnimTimer(compound.getFloat("animTimer"));
    }

}