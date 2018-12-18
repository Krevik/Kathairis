package mod.krevik.Entities;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import mod.krevik.Entities.AI.EntityAIAttackTarget;
import mod.krevik.Main.KCore;
import mod.krevik.Main.MysticLootTables;
import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIFollowOwnerFlying;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
public class EntityCloudySlime extends EntityTameable implements EntityFlying
{
    private static final DataParameter<Integer> VARIANT = EntityDataManager.<Integer>createKey(EntityCloudySlime.class, DataSerializers.VARINT);
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(KCore.CloudEssence);

    public EntityCloudySlime(World worldIn)
    {
        super(worldIn);
        this.setSize(1.4F, 1.4F);
        this.moveHelper = new EntityFlyHelper(this);
        this.setTamed(false);
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setVariant(this.rand.nextInt(5));
        return super.onInitialSpawn(difficulty, livingdata);
    }

    protected void initEntityAI()
    {
    	super.initEntityAI();
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIFollowOwnerFlying(this, 1.0D, 5.0F, 1.0F));
        this.tasks.addTask(4, new EntityAIWanderAvoidWaterFlying(this, 1.0D));
        this.tasks.addTask(3, new EntityAIFollow(this, 1.0D, 3.0F, 7.0F));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(1, new EntityAIAttackTarget(this, this.getAttackTarget()));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));

    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.6000000059604645D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
    }

    /**
     * Returns new PathNavigateGround instance
     */
    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }

    public float getEyeHeight()
    {
        return this.height * 0.6F;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        EntityLivingBase target = this.getAttackTarget();
        if(target!=null&&!this.isSitting()) {
            Vec3d vec3d = target.getPositionEyes(1.0F);
            EntityCloudySlime.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
        }
        if(!this.isTamed()) {
        	this.setAttackTarget(this.getRevengeTarget());
        }
        if(this.getOwner()!=null) {
        	if(this.getAttackTarget() instanceof EntityTameable) {
        		EntityTameable et = (EntityTameable) this.getAttackTarget();
        		if(this.getOwner()==et.getOwner()) {
        			this.setAttackTarget(null);
        		}
        	}
        }
        
    }

    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem()))
        {
        	if(this.world.getWorldTime()>0&&this.world.getWorldTime()<1000) {
	            if (!player.capabilities.isCreativeMode)
	            {
	                itemstack.shrink(1);
	            }

	            if (!this.world.isRemote)
	            {
	                if (this.rand.nextInt(8) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
	                {
	                    this.setTamedBy(player);
	                    this.playTameEffect(true);
	                    this.world.setEntityState(this, (byte)7);
	                }
	                else
	                {
	                    this.playTameEffect(false);
	                    this.world.setEntityState(this, (byte)6);
	                }
	            }
        	}
            return true;
        	
        }
        else
        {
            if (!this.world.isRemote && this.isTamed() && this.isOwner(player))
            {
                if(itemstack.getItem().equals(KCore.CloudEssence)){
                    if(this.getHealth()<this.getMaxHealth()){
                        this.heal(3.0f);
                        this.playTameEffect(true);
                        if (!player.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }
                    }
                }else {
                    this.aiSit.setSitting(!this.isSitting());
                }
            }

            return super.processInteract(player, hand);
        }
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
    	return super.getCanSpawnHere();
    }

    public void fall(float distance, float damageMultiplier)
    {
    	
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    	
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        return false;
    }

    @Nullable
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        return null;
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
    }

    @Nullable
    public SoundEvent getAmbientSound()
    {
        return getAmbientSound(this.rand);
    }

    private static SoundEvent getAmbientSound(Random random)
    {
    	return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    protected SoundEvent getDeathSound()
    {
        return null;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return getPitch(this.rand);
    }

    private static float getPitch(Random random)
    {
        return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
    }

    public SoundCategory getSoundCategory()
    {
        return SoundCategory.NEUTRAL;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return true;
    }

    protected void collideWithEntity(Entity entityIn)
    {
        if (!(entityIn instanceof EntityPlayer))
        {
            super.collideWithEntity(entityIn);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (this.aiSit != null)
            {
                this.aiSit.setSitting(false);
            }
            if(source==DamageSource.IN_WALL){
                if(this.getOwner()!=null&&getOwner().isEntityAlive()){
                    setPosition(getOwner().posX,getOwner().posY,getOwner().posZ);
                }else{
                    BlockPos tmp = new BlockPos(posX,world.getHeight(getPosition()).getY()+1,posZ);
                    setPosition(tmp.getX(),tmp.getY(),tmp.getZ());
                }
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    public int getVariant()
    {
        return MathHelper.clamp(((Integer)this.dataManager.get(VARIANT)).intValue(), 0, 4);
    }

    public void setVariant(int p_191997_1_)
    {
        this.dataManager.set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VARIANT, Integer.valueOf(0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setVariant(compound.getInteger("Variant"));
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_CLOUDYSLIME;
    }

    public boolean isFlying()
    {
        return !this.onGround;
    }
}