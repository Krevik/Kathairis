package mod.krevik.entity;

import mod.krevik.KCore;
import mod.krevik.entity.ai.EntityAIAttackMeleeBison;
import mod.krevik.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import mod.krevik.util.MysticLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityBison extends EntityAnimal
{
    private static final DataParameter<Float> animTimer = EntityDataManager.createKey(EntityBison.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> shouldAnimTail = EntityDataManager.createKey(EntityBison.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Float> hapiness = EntityDataManager.createKey(EntityBison.class, DataSerializers.FLOAT);

    public EntityBison(World worldIn)
    {
        super(worldIn);
        this.setSize(1.5F, 1.7F);
        this.experienceValue=30;
        spawnableBlock=KCore.KatharianGrass;
    }

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
        this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(KCore.bison_Stars), false));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(1,new EntityAIAttackMeleeBison(this,1.2D,true));

    }

    protected void updateAITasks()
    {
        super.updateAITasks();
    }
    
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(animTimer, Float.valueOf(0));
        this.dataManager.register(shouldAnimTail, Boolean.valueOf(false));
        this.dataManager.register(hapiness, Float.valueOf(0));
    }

    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Item.getItemFromBlock(KCore.bison_Stars);
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),2F);
        return true;
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

    public void setHapiness(float time){
        this.dataManager.set(hapiness,time);
    }

    public float getHapiness(){
        return this.dataManager.get(hapiness);
    }


    boolean shouldDeleteRevenegeTarget=false;
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


    protected ResourceLocation getLootTable()
    {
    	return MysticLootTables.LOOT_BISON;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
            super.handleStatusUpdate(id);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean c=super.processInteract(player, hand);
        if (!itemstack.isEmpty())
        {
            if (itemstack.getItem().equals(KCore.BlueFruit)) {
                if(!world.isRemote) {
                    this.consumeItemFromStack(player, itemstack);
                    setHapiness(getHapiness() + rand.nextFloat());
                    if(getHapiness()>3+rand.nextInt(4)+rand.nextFloat()){
                        setHapiness(0);
                        dropItem(KCore.functionHelper.getRandomMusicDisc(),0);
                        EntityItem item = new EntityItem(world,posX+0.5f,posY+0.5f,posZ+0.5f);
                        item.setItem(new ItemStack(KCore.functionHelper.getRandomMusicDisc(),1));
                        world.spawnEntity(item);
                    }
                }
                KCore.functionHelper.playTameEffect(world,getRNG(),this,true);

                return true;
            }
        }
    	return c;
    }

    public void onUpdate(){
        super.onUpdate();
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
    public void onLivingUpdate()
    {
    	super.onLivingUpdate();
    }


    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("shouldAnimTail",getShouldAnimTail());
        compound.setFloat("animTimer",getAnimTimer());
        compound.setFloat("hapiness",getHapiness());

    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setShouldAnimTail(compound.getBoolean("shouldAnimTail"));
        setAnimTimer(compound.getFloat("animTimer"));
        setHapiness(compound.getFloat("hapiness"));
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.proxy.bison_living;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KCore.proxy.bison_hurt;
    }

    protected SoundEvent getDeathSound()
    {
        return KCore.proxy.bison_dead;
    }

 
    public EntityBison createChild(EntityAgeable ageable)
    {
        EntityBison entitysheep = (EntityBison)ageable;
        EntityBison entitysheep1 = new EntityBison(this.world);
        return entitysheep1;
    }

 
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        return livingdata;
    }

    public float getEyeHeight()
    {
        return 0.95F * this.height;
    }
    
    

}