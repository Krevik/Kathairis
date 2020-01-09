package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModSounds;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMysticBird extends AmbientEntity
{
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityMysticBird.class, DataSerializers.VARINT);
    private static final DataParameter<Byte> SITTING = EntityDataManager.createKey(EntityMysticBird.class, DataSerializers.BYTE);
    private BlockPos spawnPosition;
    private static final EntityPredicate field_213813_c;

    public EntityMysticBird(World worldIn)
    {
        super(ModEntities.MYSTIC_BIRD,worldIn);
        this.setIsBirdSitting(true);
        this.experienceValue=5;
    }


    public EntityMysticBird(EntityType<EntityMysticBird> type, World world) {
        super(type, world);
    }


    @Override
    public boolean canSpawn(IWorld p_205020_1_, SpawnReason p_205020_2_) {
        return super.canSpawn(p_205020_1_, p_205020_2_);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setVariant(this.rand.nextInt(4));
        return super.onInitialSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    public int getVariant()
    {
        return MathHelper.clamp(this.getDataManager().get(VARIANT).intValue(), 0, 4);
    }

    public void setVariant(int p_191997_1_)
    {
        this.getDataManager().set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(SITTING, Byte.valueOf((byte)0));
        this.getDataManager().register(VARIANT, Integer.valueOf(0));
    }


    @Nullable
    public SoundEvent getAmbientSound()
    {
    	if(this.getIsBirdSitting()) {
            return ModSounds.MOB_BIRD;
    	}else {
    		return null;
    	}
    }



    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return null;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entityIn)
    {
    }

    @Override
    protected void collideWithNearbyEntities()
    {
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    }

    public boolean getIsBirdSitting()
    {
        return (this.getDataManager().get(SITTING).byteValue() & 1) != 0;
    }

    public void setIsBirdSitting(boolean isHanging)
    {
        byte b0 = this.getDataManager().get(SITTING).byteValue();

        if (isHanging)
        {
            this.getDataManager().set(SITTING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.getDataManager().set(SITTING, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getIsBirdSitting()) {
            this.setMotion(Vec3d.ZERO);
            setPosition(getPosition().getX(),MathHelper.floor(this.getPosition().getY()+1.0D),getPosition().getZ());
        } else {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        BlockPos lvt_1_1_ = new BlockPos(this);
        BlockPos lvt_2_1_ = lvt_1_1_.down();
        if (this.getIsBirdSitting()) {
            if (this.world.getBlockState(lvt_2_1_).isNormalCube(this.world, lvt_1_1_)) {
                if (this.rand.nextInt(200) == 0) {
                    this.rotationYawHead = (float)this.rand.nextInt(360);
                }

                if (this.world.getClosestPlayer(field_213813_c, this) != null) {
                    this.setIsBirdSitting(false);
                    this.world.playEvent((PlayerEntity)null, 1025, lvt_1_1_, 0);
                }
            } else {
                this.setIsBirdSitting(false);
                this.world.playEvent((PlayerEntity)null, 1025, lvt_1_1_, 0);
            }
        } else {
            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1)) {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
                this.spawnPosition = new BlockPos(this.getPosition().getX() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.getPosition().getY() + (double)this.rand.nextInt(6) - 2.0D, this.getPosition().getZ() + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
            }

            double lvt_3_1_ = (double)this.spawnPosition.getX() + 0.5D - this.getPosition().getX();
            double lvt_5_1_ = (double)this.spawnPosition.getY() + 0.1D - this.getPosition().getY();
            double lvt_7_1_ = (double)this.spawnPosition.getZ() + 0.5D - this.getPosition().getZ();
            Vec3d lvt_9_1_ = this.getMotion();
            Vec3d lvt_10_1_ = lvt_9_1_.add((Math.signum(lvt_3_1_) * 0.5D - lvt_9_1_.x) * 0.10000000149011612D, (Math.signum(lvt_5_1_) * 0.699999988079071D - lvt_9_1_.y) * 0.10000000149011612D, (Math.signum(lvt_7_1_) * 0.5D - lvt_9_1_.z) * 0.10000000149011612D);
            this.setMotion(lvt_10_1_);
            float lvt_11_1_ = (float)(MathHelper.atan2(lvt_10_1_.z, lvt_10_1_.x) * 57.2957763671875D) - 90.0F;
            float lvt_12_1_ = MathHelper.wrapDegrees(lvt_11_1_ - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += lvt_12_1_;
            if (this.rand.nextInt(100) == 0 && this.world.getBlockState(lvt_2_1_).isNormalCube(this.world, lvt_2_1_)) {
                this.setIsBirdSitting(true);
            }
        }

    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_MYSTICBIRD;
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos)
    {
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            if (!this.world.isRemote && this.getIsBirdSitting())
            {
                this.setIsBirdSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    static {
        field_213813_c = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putByte("BatFlags", this.getDataManager().get(SITTING).byteValue());
        compound.putInt("Variant", this.getVariant());

    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.getDataManager().set(SITTING, Byte.valueOf(compound.getByte("BatFlags")));
        this.setVariant(compound.getInt("Variant"));

    }

}