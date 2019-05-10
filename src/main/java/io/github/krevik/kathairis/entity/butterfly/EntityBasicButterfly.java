package io.github.krevik.kathairis.entity.butterfly;

import io.github.krevik.kathairis.block.BlockKathairisPlant;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;

public class EntityBasicButterfly extends EntityAmbientCreature
{
    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityBasicButterfly.class, DataSerializers.VARINT);

    private static final DataParameter<Byte> SITTING = EntityDataManager.createKey(EntityBasicButterfly.class, DataSerializers.BYTE);
    private BlockPos spawnPosition;

    public EntityBasicButterfly(World worldIn, EntityType type)
    {
        super(type, worldIn);
        this.setSize(0.6F, 0.5F);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
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
    public void tick()
    {
        super.tick();

        if (this.getIsBirdSitting())
        {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.posY=this.world.getHeight(Heightmap.Type.MOTION_BLOCKING,this.getPosition()).getY();
            if(this.rand.nextInt(1000)==0) {
                this.setIsBirdSitting(false);
            }
        }
        else
        {
            this.motionY *= 0.6000000238418579D;
        }
    }

    @Override
    protected void updateAITasks()
    {
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.down();

        if (this.getIsBirdSitting())
        {
            if (this.world.getBlockState(blockpos1).isNormalCube())
            {

                if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null)
                {
                    if(!this.world.getNearestPlayerNotCreative(this, 4.0D).isSneaking()) {
                        this.setIsBirdSitting(false);
                        this.world.playEvent(null, 1025, blockpos, 0);
                    }
                }
            }
            else
            {
                this.setIsBirdSitting(false);
                this.world.playEvent(null, 1025, blockpos, 0);
            }
        }
        else
        {
            if (this.spawnPosition != null && (!this.world.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
            {
                this.spawnPosition = null;
            }

            if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((double)((int)this.posX), (double)((int)this.posY), (double)((int)this.posZ)) < 4.0D)
            {
                this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
            }

            double d0 = (double)this.spawnPosition.getX() + 0.5D - this.posX;
            double d1 = (double)this.spawnPosition.getY() + 0.1D - this.posY;
            double d2 = (double)this.spawnPosition.getZ() + 0.5D - this.posZ;
            this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
            this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
            this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
            float f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
            float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
            this.moveForward = 0.5F;
            this.rotationYaw += f1;

            if (this.rand.nextInt(100) == 0 && (this.world.getBlockState(blockpos1).isNormalCube()||this.world.getBlockState(blockpos1).getBlock()instanceof BlockKathairisPlant))
            {
                this.setIsBirdSitting(true);
            }
        }
    }


    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
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
    public void readAdditional(NBTTagCompound compound)
    {
        super.readAdditional(compound);
        this.getDataManager().set(SITTING, Byte.valueOf(compound.getByte("BatFlags")));
        this.setVariant(compound.getInt("Variant"));

    }

    @Override
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);
        compound.putByte("BatFlags", this.getDataManager().get(SITTING).byteValue());
        compound.putInt("Variant", this.getVariant());

    }
    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_BUTTERFLY;
    }

    public enum ButterflyType implements IStringSerializable
    {
        BASIC1(0,"basic1"),
        BASIC2(1,"basic2"),
        CLOUDSHIMMER(2,"cloud_shimmer"),
        ILLUKINI(3,"illukini"),
        RUBYSILE(4,"ruby_sile"),
        COMMONMOTH(5,"common_moth");

        private static final EntityBasicButterfly.ButterflyType[] META_LOOKUP = new EntityBasicButterfly.ButterflyType[values().length];
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

        public static EntityBasicButterfly.ButterflyType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (EntityBasicButterfly.ButterflyType blockstone$enumtype : values())
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