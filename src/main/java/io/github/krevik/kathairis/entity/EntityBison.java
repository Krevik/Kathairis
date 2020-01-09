package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAttackMeleeBison;
import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityBison extends EntityKatharianAnimal
{
    private static final DataParameter<Float> animTimer = EntityDataManager.createKey(EntityBison.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> shouldAnimTail = EntityDataManager.createKey(EntityBison.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Float> hapiness = EntityDataManager.createKey(EntityBison.class, DataSerializers.FLOAT);

    public EntityBison(World worldIn)
    {
        super(ModEntities.BISON,worldIn);
        this.experienceValue=30;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_BISON;
    }


    public EntityBison(EntityType<EntityBison> entityBisonEntityType, World world) {
        super(entityBisonEntityType, world);
    }


    @Override
    public boolean canSpawn(IWorld world, SpawnReason sth) {
        int lvt_3_1_ = MathHelper.floor(this.getPosition().getX());
        int lvt_4_1_ = MathHelper.floor(this.getBoundingBox().minY);
        int lvt_5_1_ = MathHelper.floor(this.getPosition().getZ());
        BlockPos lvt_6_1_ = new BlockPos(lvt_3_1_, lvt_4_1_, lvt_5_1_);
        return spawnableBlocks.contains(world.getBlockState(lvt_6_1_.down()).getBlock()) && world.getNeighborAwareLightSubtracted(lvt_6_1_, 0) > 8 &&
                this.getBlockPathWeight(new BlockPos(this.getPosition().getX(), this.getBoundingBox().minY, this.getPosition().getZ()), world) >= 0.0F && world.getBlockState((new BlockPos(this)).down()).canEntitySpawn(world,getPosition(),ModEntities.BISON);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(0, new EntityAIAvoidMovingSandsAndCactus(this,1.0D));
        this.goalSelector.addGoal(1, new PanicGoal(this,1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this,1.0D));
        this.goalSelector.addGoal(3, new FollowParentGoal(this,1.1D));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this,1.0D));
        this.goalSelector.addGoal(5, new TemptGoal(this,1.25D, Ingredient.fromItems(ModBlocks.BISON_STARS),false));
        this.goalSelector.addGoal(6, new EntityAIAttackMeleeBison(this,1.2D,true));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class,1.0F));
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(animTimer, Float.valueOf(0));
        this.getDataManager().register(shouldAnimTail, Boolean.valueOf(false));
        this.getDataManager().register(hapiness, Float.valueOf(0));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Item.getItemFromBlock(ModBlocks.BISON_STARS);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),2F);
        return true;
    }

    public boolean getShouldAnimTail()
    {
        return (this.getDataManager().get(shouldAnimTail));
    }

    public void setShouldAnimTail(boolean value)
    {
        this.getDataManager().set(shouldAnimTail,value);
    }

    public void setAnimTimer(float time){
        this.getDataManager().set(animTimer,time);
    }

    public float getAnimTimer(){
        return this.getDataManager().get(animTimer);
    }

    public void setHapiness(float time){
        this.getDataManager().set(hapiness,time);
    }

    public float getHapiness(){
        return this.getDataManager().get(hapiness);
    }


    boolean shouldDeleteRevenegeTarget=false;

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        int k = getRNG().nextInt(100);
        if(k>=0&&k<=10){
            //do nothing - ingore
            shouldDeleteRevenegeTarget=true;
        }
        else if(k>10&&k<50){
            //attack in hordes
            if(source.getTrueSource() instanceof LivingEntity) {
                List<EntityBison> bisons = new ArrayList<EntityBison>();
                bisons=world.getEntitiesWithinAABB(EntityBison.class, new AxisAlignedBB(getPosition().getX() - 15, getPosition().getY() - 15, getPosition().getZ() - 15, getPosition().getX()  + 15, getPosition().getY() + 15, getPosition().getZ() + 15));
                for(int x=0;x<bisons.size();x++) {
                    bisons.get(x).setAttackTarget((LivingEntity) source.getTrueSource());
                }
                setAttackTarget((LivingEntity) source.getTrueSource());
            }
            shouldDeleteRevenegeTarget=true;
        }
        else if(k>50){
            //flee
            shouldDeleteRevenegeTarget=false;
        }
        return super.attackEntityFrom(source, amount);
    }


    @Override
    public boolean processInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean c=super.processInteract(player, hand);
        if (!itemstack.isEmpty())
        {
            if (itemstack.getItem().equals(ModItems.FRUP)) {
                if(!world.isRemote) {
                    this.consumeItemFromStack(player, itemstack);
                    setHapiness(getHapiness() + rand.nextFloat());
                    if(getHapiness()>3+rand.nextInt(4)+rand.nextFloat()){
                        setHapiness(0);
                        //dropItem(KCore.functionHelper.getRandomMusicDisc(),0);
                        //EntityItem item = new EntityItem(world,getPosition().getX()+0.5f,getPosition().getY()+0.5f,getPosition().getZ()+0.5f);
                        //item.setItem(new ItemStack(KCore.functionHelper.getRandomMusicDisc(),1));
                       // world.spawnEntity(item);
                    }
                }
                //KCore.functionHelper.playTameEffect(world,getRNG(),this,true);

                return true;
            }
        }
    	return c;
    }

    @Override
    public void tick(){
        super.tick();
        if(!getShouldAnimTail()) {
            if(getRNG().nextInt(750)==0) {
                setShouldAnimTail(true);
            }
        }
        if(getShouldAnimTail()) {
            setAnimTimer(getAnimTimer()+2);
            if(getAnimTimer()>400) {
                setAnimTimer(0);
                setShouldAnimTail(false);
            }
        }
        if(shouldDeleteRevenegeTarget==true){
            setRevengeTarget(null);
        }

    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("shouldAnimTail",getShouldAnimTail());
        compound.putFloat("animTimer",getAnimTimer());
        compound.putFloat("hapiness",getHapiness());

    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        setShouldAnimTail(compound.getBoolean("shouldAnimTail"));
        setAnimTimer(compound.getFloat("animTimer"));
        setHapiness(compound.getFloat("hapiness"));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MOB_BISON_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.MOB_BISON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.MOB_BISON_DEAD;
    }

    @Override
    public EntityBison createChild(AgeableEntity ageable)
    {
        EntityBison entitysheep = (EntityBison)ageable;
        EntityBison entitysheep1 = new EntityBison(this.world);
        return entitysheep1;
    }

    

}