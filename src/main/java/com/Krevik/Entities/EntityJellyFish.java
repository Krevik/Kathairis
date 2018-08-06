package com.Krevik.Entities;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
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

public class EntityJellyFish extends EntityFlying implements IMob
{   
	protected Random random = new Random();
	
	private AxisAlignedBB bb = new AxisAlignedBB(0.3,1,0.3,0.7,1.3,0.7);
	
    public EntityJellyFish(World worldIn)
    {
        super(worldIn);
        this.experienceValue = 30;
        this.setEntityBoundingBox(bb);
    }

    protected void initEntityAI()
    {
    }
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
    EntityJellyFish jellyfish=null;
    EntityPlayer target=null;
    public void onUpdate()
    {
        super.onUpdate();
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }   
        List<EntityJellyFish> e = this.world.getEntitiesWithinAABB(EntityJellyFish.class, new AxisAlignedBB(posX - 5, posY - 5, posZ - 5, posX + 5, posY + 5, posZ + 5));

        if (!this.world.isRemote) {
        if(e.size()>1) {
            target=getNearestPlayer();
        	if(target!=null) {
        		if(!target.isCreative()) {
	        		this.setAttackTarget(target);
	        		if(random.nextInt(20)==1) {
	        			this.motionX=(target.posX-this.posX)/10;
	        			this.motionY=(target.posY-this.posY)/10;
	        			this.motionZ=(target.posZ-this.posZ)/10;
	        		}
	        		if(this.getDistance(target)<1.5) {
	        					target.attackEntityFrom(DamageSource.causeMobDamage(this), 1F);
	        		}
        		}
        	}
            
        }else {
            if(random.nextInt(100)==1) {
            	KCore.functionHelper.updateJellyFishMotion(this);
            }
        }
        }
        
    }
    
    @Nullable
    private EntityPlayer getNearestPlayer() {
    	if(this.world.getClosestPlayer(this.posX, this.posY, this.posZ, 30, false)!=null) {
    		return this.world.getClosestPlayer(this.posX, this.posY, this.posZ, 30, false);
    	}else {
    		return null;
    	}
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
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
}