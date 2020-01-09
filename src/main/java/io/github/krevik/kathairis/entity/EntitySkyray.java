package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EntitySkyray extends FlyingEntity
{
    private static final DataParameter<Integer> timeUntilNextFeather = EntityDataManager.createKey(EntitySkyray.class, DataSerializers.VARINT);
    public int timeUntilNextFeather() {
        return this.getDataManager().get(timeUntilNextFeather).intValue();
    }

    public void setTimeUntilNextFeather(int x) {
        this.getDataManager().set(timeUntilNextFeather, Integer.valueOf(x));
    }

    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private EntitySkyray parent=null;
    private static final DataParameter<Integer> ADULT = EntityDataManager.createKey(EntitySkyray.class, DataSerializers.VARINT);

    public EntitySkyray(World worldIn)
    {
        super(ModEntities.SKYRAY,worldIn);
        this.moveController = new FlyingMovementController(this,10,false);
        this.setNoGravity(true);
        this.setTimeUntilNextFeather(this.rand.nextInt(12000) + 12000);
    }

    public EntitySkyray(EntityType<EntitySkyray> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new AIMoveRandom(this));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(2, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
    }

    public EntitySkyray getParent() {
    	return parent;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if(this.rand.nextInt(5)==0) {
            this.setAdult(1);
        }else {
            this.setAdult(0);
        }
        if(this.getAdult()==0) {
            //this.setSize(10F, 2.5F);
        }else {
            //this.setSize(42F, 10F);
        }
        return super.onInitialSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }
    
    @Override
    public void tick() {
    	super.tick();

        this.setTimeUntilNextFeather(this.timeUntilNextFeather()-1);
        if (!this.world.isRemote && this.timeUntilNextFeather() <= 0)
        {
            this.entityDropItem(ModItems.SKYRAY_FEATHER, 1);
            this.setTimeUntilNextFeather(this.rand.nextInt(12000) + 12000);
        }
        if(this.getAdult()==0) {
        	/*width=10F;
        	height=2.5F;
            this.setSize(10F, 2.5F);*/
        }else {
        	/*width=42F;
        	height=10F;
            this.setSize(42F, 10F);    */
        }
        if(this.getAdult()==0) {
        	if(this.rand.nextInt(99999999)==0) {
        	    EntitySkyray skyray = new EntitySkyray(world);
        	    skyray.setAdult(1);
        	    skyray.setPosition(getPosition().getX(),getPosition().getY(),getPosition().getZ());
        	    world.addEntity(skyray);
        	    setPosition(0,-10,0);
        	    onKillCommand();
        	}
        }
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_SKYRAY;
    }
    
    public int getAdult()
    {
        return MathHelper.clamp(this.getDataManager().get(ADULT).intValue(), 0, 4);
    }

    public void setAdult(int p_191997_1_)
    {
        this.getDataManager().set(ADULT, Integer.valueOf(p_191997_1_));
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(ADULT, Integer.valueOf(0));
        this.getDataManager().register(timeUntilNextFeather,Integer.valueOf(12000));
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putInt("timeUntilNextFeather", this.timeUntilNextFeather());
        compound.putInt("Adult", this.getAdult());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setTimeUntilNextFeather(compound.getInt("timeUntilNextFeather"));
        this.setAdult(compound.getInt("Adult"));
        if(this.getAdult()==0) {
            //this.setSize(10F, 2.5F);
        }else {
            //this.setSize(42F, 10F);
        }
    }


    @Override
    protected PathNavigator createNavigator(World worldIn)
    {
        FlyingPathNavigator pathnavigateflying = new FlyingPathNavigator(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanEnterDoors(false);
        return pathnavigateflying;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000059604645D);

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if(!(source.getTrueSource() instanceof PlayerEntity)){
            this.setMovementVector(rand.nextFloat()-rand.nextFloat(), rand.nextFloat()-rand.nextFloat(), rand.nextFloat()-rand.nextFloat());
        }
        return super.attackEntityFrom(source,amount);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 5;
    }

    @Override
    public boolean canDespawn(double p_213397_1_) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSounds.MOB_SKYRAY_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.MOB_SKYRAY_HURT;
    }

    @Override
    protected float getSoundVolume()
    {
        return 15F;
    }

    @Override
    public void livingTick() {
        super.livingTick();
        travel();
        if(MathHelper.sin(this.ticksExisted)<0) {
            if(rand.nextInt(25)==0) {
                this.setMovementVector(rand.nextFloat()-rand.nextFloat(), rand.nextFloat()-rand.nextFloat(), rand.nextFloat()-rand.nextFloat());
            }
        }
    }


    @Override
    public boolean canSpawn(IWorld p_205020_1_, SpawnReason p_205020_2_) {
        return true;
    }

    public void travel()
    {
    	this.moveController.setMoveTo(this.getPosition().getX()+randomMotionVecX, this.getPosition().getY()+randomMotionVecY, this.getPosition().getZ()+randomMotionVecZ, 1000f);
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

    static class AIMoveRandom extends Goal
        {
            private final EntitySkyray squid;

            public AIMoveRandom(EntitySkyray p_i45859_1_)
            {
                this.squid = p_i45859_1_;
                this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));

            }

            @Override
            public boolean shouldExecute()
            {
                return true;
            }

            float destX;
            float destZ;

            @Override
            public void tick()
            {
            	
            	if(this.squid.getAdult()==0&&this.squid.parent==null) {
            	     //watch for parent
            		if(this.squid.getRNG().nextInt(25)==0) {
                    List<EntitySkyray> skyrays = new ArrayList<EntitySkyray>();
            	        	skyrays=this.squid.world.getEntitiesWithinAABB(EntitySkyray.class, new AxisAlignedBB(this.squid.getPosition().getX() - 15, this.squid.getPosition().getY() - 15, this.squid.getPosition().getZ() - 15, this.squid.getPosition().getX()  + 15, this.squid.getPosition().getY() + 15, this.squid.getPosition().getZ() + 15));
            	        	if(skyrays.size()>0) {
            	        	    for(int x=0;x<skyrays.size();x++) {
            	        	        if(skyrays.get(x).getAdult()==1){
            	        	            squid.parent=skyrays.get(x);
                                    }
                                }
                            }
            	        if(this.squid.parent!=null) {
            	        	if(!this.squid.parent.isAlive()||squid.getParent().getAdult()==0) {
            	        		this.squid.parent=null;
            	        	}
            	        }
            		}
            	}
	            	if(this.squid.getParent()==null&&this.squid.getAdult()==1) {
	                	Vec3d look = this.squid.getLookVec().normalize();
	                	double lookX=look.x;
	                	double lookZ=look.z;
	
	                	float f2=this.squid.randomMotionVecY;
	                	float f1=this.squid.randomMotionVecX;
	                	float f3=this.squid.randomMotionVecZ;
	                    //float f = this.squid.getRNG().nextFloat() * ((float)Math.PI/64);
	                    //float f1 = MathHelper.cos(f) * 0.2F;
	                	if(this.squid.getRNG().nextInt(100)==0)
	                	{

	                	}
	                    //float f3 = MathHelper.sin(f) * 0.2F;
	
	                    
	                    if(this.squid.getRNG().nextInt(30)==0||!this.squid.hasMovementVector()) {
	                    	float ff = (float) (this.squid.getRNG().nextFloat()*(Math.PI*2F));
	                    	this.destX= MathHelper.sin(ff);
	                    	this.destZ= MathHelper.cos(ff);
	                        f2 = (this.squid.getRNG().nextFloat()-this.squid.getRNG().nextFloat()) * 0.2F;
	                    }
	                	float diffX=(float) (destX-lookX);
	                	float diffZ=(float) (destZ-lookZ);
	                	if(diffX>0.1) {
	                        f1=(float) (lookX+diffX/100);
	                	}
	                	if(diffZ>0.1) {
	                        f3=(float) (lookZ+diffZ/100);
	                	}
	                    if(this.squid.getPosition().getY()>240&&squid.randomMotionVecY>0) {
	                    	f2=-squid.getRNG().nextFloat();
	                    }
	                    if(this.squid.getPosition().getY()<120&&squid.randomMotionVecY<0) {
	                    	f2=squid.getRNG().nextFloat();
	                    }
	                    this.squid.setMovementVector(f1, f2, f3);
	                }

	                if(this.squid.getAdult()==0&&this.squid.getParent()!=null){
	                    if(this.squid.getDistance(this.squid.getParent())>5) {
                            float f1 = (float) (this.squid.getParent().getPosition().getX() - this.squid.getPosition().getX()) / 3;
                            float f2 = (float) (this.squid.getParent().getPosition().getY() - this.squid.getPosition().getY()) / 3;
                            float f3 = (float) (this.squid.getParent().getPosition().getZ() - this.squid.getPosition().getZ()) / 3;

                            this.squid.setMovementVector(f1, f2, f3);
                        }
	                }
            	}
        }
    
        
}