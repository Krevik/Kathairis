package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAttackTarget;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class EntityJellyFish extends EntityMob
{   
	protected Random random = new Random();
	
	private AxisAlignedBB bb = new AxisAlignedBB(0.3,1,0.3,0.7,1.3,0.7);
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public EntityJellyFish(World worldIn)
    {
        super(ModEntities.JELLY_FISH,worldIn);
        this.experienceValue = 30;
        this.setBoundingBox(bb);
        this.moveHelper = new EntityJellyFish.JellyFishMoveHelper(this);
        this.setNoGravity(true);
    }

    @Override
    protected void initEntityAI()
    {
    	super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAIAttackTarget<>(this, this.getAttackTarget()));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        //this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));

    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    public EntityJellyFish jellyfish=null;

    @Override
    public void tick()
    {
        super.tick();
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            this.onKillCommand();
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


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(!this.world.isRemote) {
    		if(jellyfish!=null) {
            	//KCore.functionHelper.JellyFishFlee(this);
    		}
    	}
    	
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    protected void registerData()
    {
        super.registerData();
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6000000059604645D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023224D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MOB_JELLYFISH_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.MOB_JELLYFISH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return ModSounds.MOB_JELLYFISH_DEAD;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_JELLYFISH;
    }

    @Override
    public boolean canSpawn(IWorld p_205020_1_, boolean p_205020_2_) {
        return super.canSpawn(p_205020_1_, p_205020_2_);
    }

    @Override
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

        @Override
        public void tick()
        {
        	if(!this.parentEntity.hasMovementVector()||this.parentEntity.getRNG().nextInt(40)==0) {
        		this.parentEntity.randomMotionVecX=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		this.parentEntity.randomMotionVecX=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		this.parentEntity.randomMotionVecY=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		if(this.parentEntity.posY < this.parentEntity.world.getHeight(Heightmap.Type.MOTION_BLOCKING,this.parentEntity.getPosition()).getY()+1&&this.parentEntity.randomMotionVecY<0) {
        			this.parentEntity.randomMotionVecY=-this.parentEntity.randomMotionVecY;
        		}
        		if(this.parentEntity.randomMotionVecY>0&&this.parentEntity.posY>220) {
        			this.parentEntity.randomMotionVecY=-this.parentEntity.randomMotionVecY;
        		}
        		
        	}
        	EntityPlayer ep = this.parentEntity.world.getNearestAttackablePlayer(this.parentEntity.getPosition(), 10, 10);
        	if(ep!=null) {
        		if(!ep.isAlive()) {
        			this.parentEntity.setAttackTarget(null);
        		}
        		if(ep!=null) {
        			List<EntityJellyFish> list = this.parentEntity.world.getEntitiesWithinAABB(EntityJellyFish.class, this.parentEntity.getBoundingBox().grow(10));
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