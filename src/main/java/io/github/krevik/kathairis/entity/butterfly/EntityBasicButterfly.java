package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AmbientEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBasicButterfly extends AmbientEntity
{
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityBasicButterfly.class, DataSerializers.VARINT);

    private static final DataParameter<Byte> SITTING = EntityDataManager.createKey(EntityBasicButterfly.class, DataSerializers.BYTE);
    private BlockPos spawnPosition;
    private static final EntityPredicate field_213813_c;


    public EntityBasicButterfly(World worldIn)
    {
        super(ModEntities.COMMON_BUTTERFLY1, worldIn);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    public EntityBasicButterfly(World worldIn, EntityType<? extends EntityBasicButterfly> commonButterfly1) {
        super(commonButterfly1, worldIn);
    }

    public EntityBasicButterfly(EntityType<? extends EntityBasicButterfly> type, World world) {
        super(type,world);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
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
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
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
            setPosition(getPosition().getX(),(double)MathHelper.floor(this.getPosition().getY()) + 1.0D,getPosition().getZ());
        } else {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_BUTTERFLY;
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


    public int getVariant()
    {
        return MathHelper.clamp(this.getDataManager().get(VARIANT).intValue(), 0, ButterflyType.META_LOOKUP.length);
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(VARIANT, Integer.valueOf(0));
        this.getDataManager().register(SITTING, Byte.valueOf((byte)1));
    }


    public void setVariant(int p_191997_1_)
    {
        this.getDataManager().set(VARIANT, Integer.valueOf(p_191997_1_));
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        this.getDataManager().set(SITTING, Byte.valueOf(compound.getByte("BatFlags")));
        this.setVariant(compound.getInt("Variant"));

    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putByte("BatFlags", this.getDataManager().get(SITTING).byteValue());
        compound.putInt("Variant", this.getVariant());

    }

    static {
        field_213813_c = (new EntityPredicate()).setDistance(4.0D).allowFriendlyFire();
    }

    public enum ButterflyType implements IStringSerializable
    {
        BASIC1(0,"basic1"),
        BASIC2(1,"basic2"),
        CLOUDSHIMMER(2,"cloud_shimmer"),
        ILLUKINI(3,"illukini"),
        RUBYSILE(4,"ruby_sile"),
        COMMONMOTH(5,"common_moth");

        private static final ButterflyType[] META_LOOKUP = new ButterflyType[values().length];
        private final int meta;
        private final String name;

        ButterflyType(int p_i46384_3_, String Name)
        {
            this.meta = p_i46384_3_;
            name=Name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public static ButterflyType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (ButterflyType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }

        @Override
        public String getName() {
            return name;
        }
    }
}