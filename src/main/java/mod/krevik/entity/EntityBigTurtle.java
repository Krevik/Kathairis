package mod.krevik.entity;

import mod.krevik.KCore;
import mod.krevik.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import mod.krevik.util.MysticLootTables;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityBigTurtle extends EntityAnimal
{

    public EntityBigTurtle(World worldIn)
    {
        super(worldIn);
        this.setSize(0.9F, 1.2F);
        this.experienceValue=10;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
    }
    
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1000000417232513D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    protected ResourceLocation getLootTable()
    {
    	return MysticLootTables.LOOT_BIGTURTLE;
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
            super.handleStatusUpdate(id);
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	return false;
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
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
        return KCore.proxy.turtle_dead;
    }

 
    public EntityBigTurtle createChild(EntityAgeable ageable)
    {
        EntityBigTurtle entitysheep1 = new EntityBigTurtle(this.world);
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