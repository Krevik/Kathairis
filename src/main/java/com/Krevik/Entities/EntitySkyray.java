package com.Krevik.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySkyray extends EntityFlying
{
    private static final DataParameter<Integer> timeUntilNextFeather = EntityDataManager.<Integer>createKey(EntitySkyray.class, DataSerializers.VARINT);
    public int timeUntilNextFeather() {
        return ((Integer)this.dataManager.get(timeUntilNextFeather)).intValue();
    }
    public void setTimeUntilNextFeather(int x) {
        this.dataManager.set(timeUntilNextFeather, Integer.valueOf(x));
    }

    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
    private EntitySkyray parent=null;
    private static final DataParameter<Integer> ADULT = EntityDataManager.<Integer>createKey(EntitySkyray.class, DataSerializers.VARINT);

    public EntitySkyray(World worldIn)
    {
        super(worldIn);
        this.setSize(10F, 2.5F);    
        this.moveHelper = new EntityFlyHelper(this);
        this.setNoGravity(true);
        this.setTimeUntilNextFeather(this.rand.nextInt(12000) + 12000);
    }
    
    public EntitySkyray getParent() {
    	return parent;
    }
    
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        if(this.rand.nextInt(5)==0) {
        	this.setAdult(1);
        }else {
        	this.setAdult(0);
        }
        if(this.getAdult()==0) {
            this.setSize(10F, 2.5F);    
        }else {
            this.setSize(42F, 10F);    
        }
        return super.onInitialSpawn(difficulty, livingdata);
    }
    
    public void onUpdate() {
    	super.onUpdate();

        this.setTimeUntilNextFeather(this.timeUntilNextFeather()-1);
        if (!this.world.isRemote && this.timeUntilNextFeather() <= 0)
        {
            this.dropItem(KCore.skyray_feather, 1);
            this.setTimeUntilNextFeather(this.rand.nextInt(12000) + 12000);
        }
        if(this.getAdult()==0) {
        	width=10F;
        	height=2.5F;
            this.setSize(10F, 2.5F);    
        }else {
        	width=42F;
        	height=10F;
            this.setSize(42F, 10F);    
        }
        if(this.getAdult()==0) {
        	if(this.rand.nextInt(99999999)==0) {
        		this.dataManager.set(ADULT, Integer.valueOf(1));
        	}
        }
    }
    
    public int getAdult()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(ADULT)).intValue(), 0, 4);
    }

    public void setAdult(int p_191997_1_)
    {
        this.dataManager.set(ADULT, Integer.valueOf(p_191997_1_));
    }

    public static void registerFixesSquid(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntitySkyray.class);
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ADULT, Integer.valueOf(0));
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("timeUntilNextFeather", this.timeUntilNextFeather());
        compound.setInteger("Adult", this.getAdult());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setTimeUntilNextFeather(compound.getInteger("timeUntilNextFeather"));
        this.setAdult(compound.getInteger("Adult"));
        if(this.getAdult()==0) {
            this.setSize(10F, 2.5F);    
        }else {
            this.setSize(42F, 10F);    
        }
    }


    protected void initEntityAI()
    {
    	super.initEntityAI();
        this.tasks.addTask(2, new EntitySkyray.AIMoveRandom(this));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));

    }
    
    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4000000059604645D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4000000059604645D);

    }
    
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.instance.proxy.skyray_ambient;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KCore.instance.proxy.skyray_hurt;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 15F;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_SKYRAY;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */

    		

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        travel();
        if(MathHelper.sin(this.ticksExisted)<0) {
        	if(rand.nextInt(25)==0) {
        	this.setMovementVector(rand.nextFloat()-rand.nextFloat(), rand.nextFloat()-rand.nextFloat(), rand.nextFloat()-rand.nextFloat());
        	}
        }
    }

    public void travel()
    {
    	this.moveHelper.setMoveTo(this.posX+randomMotionVecX, this.posY+randomMotionVecY, this.posZ+randomMotionVecZ, 1000f);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return super.getCanSpawnHere();
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
            super.handleStatusUpdate(id);
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

    static class AIMoveRandom extends EntityAIBase
        {
            private final EntitySkyray squid;

            public AIMoveRandom(EntitySkyray p_i45859_1_)
            {
                this.squid = p_i45859_1_;
                this.setMutexBits(3);
            }

            /**
             * Returns whether the EntityAIBase should begin execution.
             */
            public boolean shouldExecute()
            {
                return true;
            }

            /**
             * Keep ticking a continuous task that has already been started
             */
            float destX;
            float destZ;
            public void updateTask()
            {
            	
            	if(this.squid.getAdult()==0&&this.squid.parent==null) {
            	     //watch for parent
            		if(this.squid.getRNG().nextInt(25)==0) {
                    List<EntitySkyray> skyrays = new ArrayList<EntitySkyray>();
            	        	skyrays=this.squid.world.getEntitiesWithinAABB(EntitySkyray.class, new AxisAlignedBB(this.squid.posX - 15, this.squid.posY - 15, this.squid.posZ - 15, this.squid.posX  + 15, this.squid.posY + 15, this.squid.posZ + 15));
            	        	if(skyrays.size()>0) {
            	        	    for(int x=0;x<skyrays.size();x++) {
            	        	        if(skyrays.get(x).getAdult()==1){
            	        	            squid.parent=skyrays.get(x);
                                    }
                                }
                            }
            	        if(this.squid.parent!=null) {
            	        	if(this.squid.parent.isDead||squid.getParent().getAdult()==0) {
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
	                    	this.destX=MathHelper.sin(ff);
	                    	this.destZ=MathHelper.cos(ff);
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
	                    if(this.squid.posY>240&&squid.randomMotionVecY>0) {
	                    	f2=-squid.getRNG().nextFloat();
	                    }
	                    if(this.squid.posY<120&&squid.randomMotionVecY<0) {
	                    	f2=squid.getRNG().nextFloat();
	                    }
	                    this.squid.setMovementVector(f1, f2, f3);
	                }

	                if(this.squid.getAdult()==0&&this.squid.getParent()!=null){
	                    if(this.squid.getDistance(this.squid.getParent())>5) {
                            float f1 = (float) (this.squid.getParent().posX - this.squid.posX) / 3;
                            float f2 = (float) (this.squid.getParent().posY - this.squid.posY) / 3;
                            float f3 = (float) (this.squid.getParent().posZ - this.squid.posZ) / 3;

                            this.squid.setMovementVector(f1, f2, f3);
                        }
	                }
            	}
        }
    
        
}