package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAttackMeleeBison;
import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
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
        this.setSize(1.5F, 1.7F);
        this.experienceValue=30;
        spawnableBlocks.add(ModBlocks.KATHAIRIS_GRASS);
        spawnableBlocks.add(ModBlocks.KATHAIRIS_DIRT);
    }

    @Override
    public boolean canSpawn(IWorld p_205020_1_, boolean p_205020_2_) {
        int lvt_3_1_ = MathHelper.floor(this.posX);
        int lvt_4_1_ = MathHelper.floor(this.getBoundingBox().minY);
        int lvt_5_1_ = MathHelper.floor(this.posZ);
        BlockPos lvt_6_1_ = new BlockPos(lvt_3_1_, lvt_4_1_, lvt_5_1_);
        return spawnableBlocks.contains(p_205020_1_.getBlockState(lvt_6_1_.down()).getBlock()) && p_205020_1_.getLightSubtracted(lvt_6_1_, 0) > 8 &&
                this.getBlockPathWeight(new BlockPos(this.posX, this.getBoundingBox().minY, this.posZ), p_205020_1_) >= 0.0F && p_205020_1_.getBlockState((new BlockPos(this)).down()).canEntitySpawn(this);
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Ingredient.fromItems(ModBlocks.BISON_STARS), false));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(1,new EntityAIAttackMeleeBison(this,1.2D,true));

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
            if(source.getTrueSource() instanceof EntityLivingBase) {
                List<EntityBison> bisons = new ArrayList<EntityBison>();
                bisons=world.getEntitiesWithinAABB(EntityBison.class, new AxisAlignedBB(posX - 15, posY - 15, posZ - 15, posX  + 15, posY + 15, posZ + 15));
                for(int x=0;x<bisons.size();x++) {
                    bisons.get(x).setAttackTarget((EntityLivingBase) source.getTrueSource());
                }
                setAttackTarget((EntityLivingBase) source.getTrueSource());
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
    protected ResourceLocation getLootTable()
    {
    	return KatharianLootTables.LOOT_BISON;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
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
                        //EntityItem item = new EntityItem(world,posX+0.5f,posY+0.5f,posZ+0.5f);
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
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("shouldAnimTail",getShouldAnimTail());
        compound.putFloat("animTimer",getAnimTimer());
        compound.putFloat("hapiness",getHapiness());

    }

    @Override
    public void readAdditional(NBTTagCompound compound)
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
    public EntityBison createChild(EntityAgeable ageable)
    {
        EntityBison entitysheep = (EntityBison)ageable;
        EntityBison entitysheep1 = new EntityBison(this.world);
        return entitysheep1;
    }

    @Override
    public float getEyeHeight()
    {
        return 0.95F * this.height;
    }
    
    

}