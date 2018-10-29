package com.Krevik.Entities;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Entities.AI.EntityAIAttackTarget;
import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityJellyFish extends EntityMob
{   
	protected Random random = new Random();
	
	private AxisAlignedBB bb = new AxisAlignedBB(0.3,1,0.3,0.7,1.3,0.7);
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    public EntityJellyFish(World worldIn)
    {
        super(worldIn);
        this.experienceValue = 30;
        this.setEntityBoundingBox(bb);
        this.moveHelper = new EntityJellyFish.JellyFishMoveHelper(this);
        this.setNoGravity(true);
    }
    protected void initEntityAI()
    {
    	super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIAttackTarget(this, this.getAttackTarget()));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        //this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));

    }
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
    public EntityJellyFish jellyfish=null;
    public void onUpdate()
    {
        super.onUpdate();
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }   
        if(!this.world.isRemote) {
	        this.fallDistance=0;
	        if(this.hasMovementVector()&&this.getAttackTarget()==null) {
	        	this.motionX=this.randomMotionVecX/5;
	        	this.motionY=this.randomMotionVecY/5;
	        	this.motionZ=this.randomMotionVecZ/5;
	        }
        }
    }
    
    
    
    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn)
    {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector()
    {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(!this.world.isRemote) {
    		if(jellyfish!=null) {
            	KCore.functionHelper.JellyFishFlee(this);
    		}
    	}
    	
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6000000059604645D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.cproxy.jellyfish_living;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KCore.cproxy.jellyfish_hurt;
    }

    protected SoundEvent getDeathSound()
    {
        return KCore.cproxy.jellyfish_dead;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_JELLYFISH;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 1.0F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.rand.nextInt(5) == 0 && super.getCanSpawnHere() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }



    public static void registerFixesJellyFish(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityJellyFish.class);
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

    public float getEyeHeight()
    {
        return 2.6F;
    }
    static class JellyFishMoveHelper extends EntityMoveHelper
    {
        private final EntityJellyFish parentEntity;
        private int courseChangeCooldown;

        public JellyFishMoveHelper(EntityJellyFish ghast)
        {
            super(ghast);
            this.parentEntity = ghast;
        }

        public void onUpdateMoveHelper()
        {
        	if(!this.parentEntity.hasMovementVector()||this.parentEntity.getRNG().nextInt(40)==0) {
        		this.parentEntity.randomMotionVecX=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		this.parentEntity.randomMotionVecX=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		this.parentEntity.randomMotionVecY=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		if(this.parentEntity.posY < this.parentEntity.world.getHeight(this.parentEntity.getPosition()).getY()+1&&this.parentEntity.randomMotionVecY<0) {
        			this.parentEntity.randomMotionVecY=-this.parentEntity.randomMotionVecY;
        		}
        		if(this.parentEntity.randomMotionVecY>0&&this.parentEntity.posY>220) {
        			this.parentEntity.randomMotionVecY=-this.parentEntity.randomMotionVecY;
        		}
        		
        	}
        	EntityPlayer ep = this.parentEntity.world.getNearestAttackablePlayer(this.parentEntity.getPosition(), 10, 10);
        	if(ep!=null) {
        		if(ep.isDead) {
        			this.parentEntity.setAttackTarget(null);
        		}
        		if(ep!=null) {
        			List<EntityJellyFish> list = this.parentEntity.world.getEntitiesWithinAABB(EntityJellyFish.class, this.parentEntity.getEntityBoundingBox().grow(10));
        			if(list.size()>1) {
        				this.parentEntity.setAttackTarget(ep);
        				this.parentEntity.motionX=(ep.posX-this.parentEntity.posX)/3;
        				this.parentEntity.motionY=(ep.posY-this.parentEntity.posY)/3;
        				this.parentEntity.motionZ=(ep.posZ-this.parentEntity.posZ)/3;
        				this.parentEntity.jellyfish=list.get(0);
        			}else {
        				this.parentEntity.setAttackTarget(null);
        				this.parentEntity.jellyfish=null;
        			}
        		}
        	}
        }
    }
}