package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.block.BlockKathairisCloud;
import io.github.krevik.kathairis.entity.ai.EntityAICloudOisterPanic;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityCloudOister extends AmbientEntity
{
    private static final DataParameter<Integer> timeUntilNextPearl = EntityDataManager.createKey(EntityCloudOister.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> panic = EntityDataManager.createKey(EntityCloudOister.class, DataSerializers.BOOLEAN);
    
    public int timeUntilNextPearl() {
    	return this.getDataManager().get(timeUntilNextPearl).intValue();
    }

    public void setTimeUntilNextPearl(int x) {
    	this.getDataManager().set(timeUntilNextPearl, Integer.valueOf(x));
    }
    
    public boolean panic() {
    	return this.getDataManager().get(panic).booleanValue();
    }
    
    public void setPanic(boolean trueorfalse) {
    	this.getDataManager().set(panic, Boolean.valueOf(trueorfalse));
    }

    public EntityCloudOister(World worldIn)
    {
        super(ModEntities.CLOUD_OISTER,worldIn);
        this.experienceValue=5;
        this.setTimeUntilNextPearl(this.rand.nextInt(6000) + 6000);
        this.setPanic(false);
    }
    public EntityCloudOister(EntityType<EntityCloudOister> type, World world) {
        super(type, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new EntityAICloudOisterPanic(this,1.0D));
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_CLOUDOISTER;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(timeUntilNextPearl, 24000);
        this.getDataManager().register(panic, Boolean.valueOf(false));
    }

    int jumpTimer=0;
    int k = 50+rand.nextInt(300);
    int panicTimer=0;
    
    @Override
    public void tick() {
    	super.tick();
        this.setTimeUntilNextPearl(this.timeUntilNextPearl()-1);
        if (!this.world.isRemote && this.timeUntilNextPearl() <= 0)
        {
            this.entityDropItem(ModItems.CLOUD_PEARL, 1);
            this.setTimeUntilNextPearl(this.rand.nextInt(6000) + 6000);
        }
        this.fallDistance=0;
        if(this.getRevengeTarget()!=null) {
            this.setPanic(true);
        }

        if(!panic()) {
            jumpTimer++;
            if(jumpTimer>k) {
                k=50+rand.nextInt(300);
                jumpTimer=0;
                setMotion(new Vec3d(getMotion().getX(),0.5,getMotion().getZ()));
                spawnJumpParticles(getEntityWorld());
            }
            if(!this.onGround) {
                if(this.getRNG().nextInt(100)==0) {
                    double destPosX=this.posX-this.getRNG().nextInt(6)+this.getRNG().nextInt(6);
                    double destPosZ=this.posZ-this.getRNG().nextInt(6)+this.getRNG().nextInt(6);
                    this.getNavigator().setPath(this.getNavigator().getPathToPos(new BlockPos(destPosX,posY,destPosZ),1), 1);
                    setMotion(new Vec3d((destPosX-this.posX)*0.15,getMotion().getY(),(destPosZ-this.posZ)*0.15));
                }
            }
        }else {
            panicTimer++;
            if(panicTimer>200+rand.nextInt(100)) {
                this.setPanic(false);
                panicTimer=0;
                this.setRevengeTarget(null);
            }

        }
    }

    public void spawnJumpParticles(World world){
        for (int i = 0; i < 24; ++i)
        {
            double d0 = posX + Kathairis.getHelper().getRandom().nextDouble() - Kathairis.getHelper().getRandom().nextDouble();
            double d1 = posY;
            double d2 = posZ + Kathairis.getHelper().getRandom().nextDouble() - Kathairis.getHelper().getRandom().nextDouble();
            double d3 = 0;
            double d4 = 0;
            double d5 = 0;
            world.addParticle(ParticleTypes.CLOUD, d0, d1, d2, d3, d4, d5);
        }
    }

    /*public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }*/

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        setMotion(new Vec3d(getMotion().getX(),0.5,getMotion().getZ()));
        spawnJumpParticles(getEntityWorld());
        return flag;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        spawnJumpParticles(getEntityWorld());
    	return super.attackEntityFrom(source, amount);
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putInt("timeUntilNextPearl", this.timeUntilNextPearl());
        compound.putBoolean("panic", panic());
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.setTimeUntilNextPearl(compound.getInt("timeUntilNextPearl"));
        this.setPanic(compound.getBoolean("panic"));
    }

    @Override
    public boolean canSpawn(IWorld p_205020_1_, SpawnReason reason) {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        Block block = this.world.getBlockState(blockpos.down()).getBlock();
        return block instanceof BlockKathairisCloud;
    }
    
}