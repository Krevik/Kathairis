package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAttackTarget;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.List;
import java.util.Random;

//TODO
public class EntityJellyFish extends CreatureEntity
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
        this.moveController = new JellyFishMoveHelper(this);
        this.setNoGravity(true);
    }

    public EntityJellyFish(EntityType<EntityJellyFish> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new EntityAIAttackTarget(this, this.getAttackTarget()));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
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
        if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL)
        {
            this.onKillCommand();
        }   
        if(!this.world.isRemote) {
	        this.fallDistance=0;
	        if(this.hasMovementVector()&&this.getAttackTarget()==null) {
	            setMotion(this.randomMotionVecX/5,this.randomMotionVecY/5,this.randomMotionVecZ/5);
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
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_JELLYFISH;
    }


    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(!this.world.isRemote) {
    		if(jellyfish!=null) {
    		    //TODO
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
        this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
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


    @Override
    public boolean canSpawn(IWorld p_205020_1_, SpawnReason p_205020_2_) {
        return super.canSpawn(p_205020_1_, p_205020_2_);
    }

    static class JellyFishMoveHelper extends FlyingMovementController
    {
        private final EntityJellyFish parentEntity;
        private int courseChangeCooldown;

        public JellyFishMoveHelper(EntityJellyFish ghast)
        {
            super(ghast,10,false);
            this.parentEntity = ghast;
        }

        @Override
        public void tick()
        {
        	if(!this.parentEntity.hasMovementVector()||this.parentEntity.getRNG().nextInt(40)==0) {
        		this.parentEntity.randomMotionVecX=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		this.parentEntity.randomMotionVecX=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		this.parentEntity.randomMotionVecY=this.parentEntity.getRNG().nextFloat()-this.parentEntity.getRNG().nextFloat();
        		if(this.parentEntity.getPosition().getY() < this.parentEntity.world.getHeight(Heightmap.Type.MOTION_BLOCKING,this.parentEntity.getPosition()).getY()+1&&this.parentEntity.randomMotionVecY<0) {
        			this.parentEntity.randomMotionVecY=-this.parentEntity.randomMotionVecY;
        		}
        		if(this.parentEntity.randomMotionVecY>0&&this.parentEntity.getPosition().getY()>220) {
        			this.parentEntity.randomMotionVecY=-this.parentEntity.randomMotionVecY;
        		}
        		
        	}
        	PlayerEntity ep = this.parentEntity.world.getClosestPlayer(this.parentEntity, 10);
        	if(ep!=null) {
        		if(!ep.isAlive()) {
        			this.parentEntity.setAttackTarget(null);
        		}
        		if(ep!=null) {
        			List<EntityJellyFish> list = this.parentEntity.world.getEntitiesWithinAABB(EntityJellyFish.class, this.parentEntity.getBoundingBox().grow(10));
        			if(list.size()>1) {
        				this.parentEntity.setAttackTarget(ep);
        				this.parentEntity.setMotion(new Vec3d((ep.getPosition().getX()-this.parentEntity.getPosition().getX())/3,(ep.getPosition().getY()-this.parentEntity.getPosition().getY())/3,(ep.getPosition().getZ()-this.parentEntity.getPosition().getZ())/3));
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