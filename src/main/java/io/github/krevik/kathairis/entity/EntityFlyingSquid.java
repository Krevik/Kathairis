package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.EnumSet;

public class EntityFlyingSquid extends FlyingEntity
{
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private static final DataParameter<Boolean> isDiving = EntityDataManager.createKey(EntityFlyingSquid.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> isHoldingPlayer = EntityDataManager.createKey(EntityFlyingSquid.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> canHoldPlayer = EntityDataManager.createKey(EntityFlyingSquid.class, DataSerializers.BOOLEAN);

    public EntityFlyingSquid(World worldIn)
    {
        super(ModEntities.FLYING_SQUID,worldIn);
        this.getDataManager().set(isHoldingPlayer, Boolean.valueOf(false));
        this.getDataManager().set(canHoldPlayer, Boolean.valueOf(true));

    }

    public EntityFlyingSquid(EntityType<EntityFlyingSquid> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(isDiving, Boolean.FALSE);
        this.getDataManager().register(isHoldingPlayer, Boolean.valueOf(false));
        this.getDataManager().register(canHoldPlayer, Boolean.valueOf(true));

    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_FLYINGSQUID;
    }
    
    public boolean isDiving() {
        return this.getDataManager().get(isDiving).booleanValue();
    }
    public void setDiving(boolean truorfalse) {
        this.getDataManager().set(isDiving, Boolean.valueOf(truorfalse));
    }
    
    public boolean isHoldingPlayer() {
        return this.getDataManager().get(isHoldingPlayer).booleanValue();
    }
    public void setIsHoldingPlayer(boolean truorfalse) {
        this.getDataManager().set(isHoldingPlayer, Boolean.valueOf(truorfalse));
    }
    
    public boolean canHoldPlayer() {
        return this.getDataManager().get(canHoldPlayer).booleanValue();
    }
    public void setCanHoldPlayer(boolean truorfalse) {
        this.getDataManager().set(canHoldPlayer, Boolean.valueOf(truorfalse));
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(this.isHoldingPlayer()||this.isDiving()) {
    		setIsHoldingPlayer(false);
    		setDiving(false);
    	}
    	return super.attackEntityFrom(source,amount);
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("isHoldingPlayer", this.isHoldingPlayer());
        compound.putBoolean("isDiving", this.isDiving());
        compound.putBoolean("canHoldPlayer", this.canHoldPlayer());

    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        setIsHoldingPlayer(compound.getBoolean("isHoldingPlayer"));
        setDiving(compound.getBoolean("isDiving"));
        setCanHoldPlayer(compound.getBoolean("canHoldPlayer"));

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AIMoveRandom(this));
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_SQUID_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }


    public int animTravelTime=0;
    public int animTravelTime2=0;
    private int mode=0;
    int swingTries=0;
    @Override
    public void tick() {
    	super.tick();
        if(isDiving()) {
            this.travel(getMotion().getX(), getMotion().getY(), getMotion().getZ());
        }
        if(mode==0) {
            animTravelTime++;
            animTravelTime++;
            animTravelTime++;
            animTravelTime++;

        }
        if(mode==0&&animTravelTime>40) {
            mode=1;
            setMotion(new Vec3d(randomMotionVecX,randomMotionVecY,randomMotionVecZ));
        }
        if(mode==1) {
            animTravelTime--;
            animTravelTime--;
            animTravelTime--;
            animTravelTime--;
            animTravelTime--;
            animTravelTime--;
            animTravelTime--;
            animTravelTime--;
            if(animTravelTime<-20) {
                this.travel(getMotion().getX(), getMotion().getY(), getMotion().getZ());
            }
        }
        if(mode==1&&animTravelTime<-60) {
            mode=0;
        }
        animTravelTime2=animTravelTime/2;
        if(animTravelTime2<-15) {
            animTravelTime2=-15;
        }
        if(animTravelTime2>15) {
            animTravelTime2=15;
        }

        if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL)
        {
            this.onKillCommand();
        }
	    	PlayerEntity ep=this.world.getClosestPlayer(this.posX, this.posY, this.posZ, 5, true);
	    	if(ep!=null) {
	    		
	        	if(!ep.isAlive()) {
	        		setIsHoldingPlayer(false);
	        	}
	        	if(isHoldingPlayer()) {
	        		this.setDiving(false);
		    		if(ep.isSwingInProgress) {
		    			swingTries++;
		    		}
		    		if(swingTries>100) {
		    			this.setIsHoldingPlayer(false);
		    		}
		    		ep.setPositionAndUpdate(this.posX,this.posY-2,this.posZ);
			    		//PacketSquidHoldingPlayerServer packet = new PacketSquidHoldingPlayerServer(this.posX,this.posY-2,this.posZ);
			    		//KathairisPacketHandler.CHANNEL.sendToServer(packet);
			    		//maybe add client side to make it working?
	        	}
	    	}else {
        		setIsHoldingPlayer(false);
        		swingTries=0;
	    	}

    }

    public void travel(double x, double y, double z)
    {
        this.move(MoverType.SELF, new Vec3d(x, y, z));
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
            private final EntityFlyingSquid squid;

            public AIMoveRandom(EntityFlyingSquid p_i45859_1_)
            {
                this.squid = p_i45859_1_;
                this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
            }

            @Override
            public boolean shouldExecute()
            {
                return true;
            }

            @Override
            public void tick()
            {
                int i = this.squid.getIdleTime();
                boolean isHoldingPlayer=this.squid.isHoldingPlayer();
                if(!isHoldingPlayer) {
			                PlayerEntity ep = this.squid.world.getClosestPlayer(squid,50);
			                if(ep!=null) {
			                		if(!ep.onGround) {
			                			this.squid.setCanHoldPlayer(false);
			                		}else {
			                			this.squid.setCanHoldPlayer(true);
			                		}
				                	if(ep.posX-this.squid.posX<0.15F&&ep.posZ-this.squid.posZ<0.15F&&(int)this.squid.posY<=(int)ep.posY+2) {
				                		if(this.squid.canHoldPlayer()) {
				                			this.squid.setIsHoldingPlayer(true);	
				                		}
				                	}else {
				                		this.squid.setIsHoldingPlayer(false);
				                	}
				                	if(this.squid.getPosition().getX() !=(int)ep.posX|| this.squid.getPosition().getZ() !=(int)ep.posZ) {
					                	float f1=(float) (ep.posX-this.squid.posX);
					                	float f3=(float) (ep.posZ-this.squid.posZ);
					                	float f2=0;
					                	if((this.squid.posY-ep.posY)<=4) {
					                		f2=3;
					                	}
					                	Vec3d direction=new Vec3d(f1,f2,f3);
					                	Vec3d normalized=direction.normalize();
					                	this.squid.setMovementVector((float)normalized.x/10, (float)normalized.y, (float)normalized.z/10);
				                	}
				                	if(ep.posX-this.squid.posX<0.15F&&ep.posZ-this.squid.posZ<0.15F) {
				                		if(squid.posY>ep.posY+2) {
				                			float f = (float) (ep.posY-this.squid.posY);
				                			Vec3d normalized = new Vec3d(0,f,0).normalize();
				                			this.squid.setMovementVector(0, (float)normalized.y, 0);
				                			this.squid.getDataManager().set(isDiving, Boolean.valueOf(true));
				                		}
				                	}else {
				                		this.squid.getDataManager().set(isDiving, Boolean.valueOf(false));
				                	}
				                	if(this.squid.isDiving()) {
				                		if(this.squid.posY<ep.posY+2) {
				                			if(!this.squid.isHoldingPlayer()) {
				                				this.squid.setDiving(false);
				                			}
				                		}
				                	}
		                }
			            else {
	                	this.squid.setDiving(false);
		                if (i > 100)
		                {
		                    this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
		                }
		                else if (this.squid.getRNG().nextInt(100) == 0 || !this.squid.inWater || !this.squid.hasMovementVector())
		                {
		                    float f = this.squid.getRNG().nextFloat() * ((float)Math.PI * 2F);
		                    float f1 = MathHelper.cos(f) * 0.2F;
		                    float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
		
		                    if(squid.posY>250&&f2>0) {
		                    	f2=-f2;
		                    }
		                    float f3 = MathHelper.sin(f) * 0.2F;
		                    this.squid.setMovementVector(f1, f2, f3);
		                }
	                }
	            }else {
	            	float f=-0.1F + this.squid.getRNG().nextFloat() * 0.2F;
	            	if(this.squid.posY<=this.squid.world.getHeight(Heightmap.Type.MOTION_BLOCKING,this.squid.getPosition()).getY()+12) {
	            		f=1F;
	            	}
	            	this.squid.setMovementVector(0, f, 0);
                    if(squid.posY>250&&f>0) {
                    	f=-f;
                    }	            	
	            	
	            }
            }
        }
}