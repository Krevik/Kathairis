package com.krevik.Entities.Butterflies;

import javax.annotation.Nullable;

import com.krevik.Blocks.BlockButterflyFlower;
import com.krevik.Blocks.BlockMysticBush;
import com.krevik.Main.KCore;
import com.krevik.Main.MysticLootTables;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityButterfly1 extends EntityAmbientCreature
{
    private static final DataParameter<Byte> SITTING = EntityDataManager.<Byte>createKey(EntityButterfly1.class, DataSerializers.BYTE);
    /** Coordinates of where the bat spawned. */
    private BlockPos spawnPosition;
    public BlockPos butterflyFlowerPos=null;

    public EntityButterfly1(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.5F);
        this.setIsBirdSitting(true);
        this.experienceValue=1;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(SITTING, Byte.valueOf((byte)0));
    }
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }
    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 1F;
    }

    /**
     * Gets the pitch of living sounds in living entities.
     */
    protected float getSoundPitch()
    {
        return super.getSoundPitch() * 0.95F;
    }

    @Nullable
    public SoundEvent getAmbientSound()
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
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    protected void collideWithEntity(Entity entityIn)
    {
    }

    protected void collideWithNearbyEntities()
    {
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    public boolean getIsBirdSitting()
    {
        return (((Byte)this.dataManager.get(SITTING)).byteValue() & 1) != 0;
    }

    public void setIsBirdSitting(boolean isHanging)
    {
        byte b0 = ((Byte)this.dataManager.get(SITTING)).byteValue();

        if (isHanging)
        {
            this.dataManager.set(SITTING, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataManager.set(SITTING, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.getIsBirdSitting())
        {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
            this.posY=this.world.getHeight(this.getPosition()).getY();
            if(this.rand.nextInt(1000)==0) {
            	this.setIsBirdSitting(false);
            }
        }
        else
        {
            this.motionY *= 0.6000000238418579D;
        }
    }

    int ticker=0;
    protected void updateAITasks()
    {
        ticker++;
        super.updateAITasks();
        BlockPos blockpos = new BlockPos(this);
        BlockPos blockpos1 = blockpos.down();
        if(butterflyFlowerPos!=null) {
        if(this.getIsBirdSitting()) {
    		if(this.getPosition()!=butterflyFlowerPos.up()) {
    			this.setIsBirdSitting(false);
    		}
        }
        }
        if(butterflyFlowerPos!=null) {
        	if(this.world.getBlockState(butterflyFlowerPos)==KCore.ButterflyFlower.getDefaultState().withProperty(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH)) {
        		this.getMoveHelper().setMoveTo(butterflyFlowerPos.getX()+rand.nextFloat(), butterflyFlowerPos.getY()+5, butterflyFlowerPos.getZ()+rand.nextFloat(), 0.5);
        		this.motionY*=1.6;
        		if(this.getPosition()==butterflyFlowerPos.up()) {
        			this.setIsBirdSitting(true);
        		}
        	}else {
        		butterflyFlowerPos=null;
        		this.setIsBirdSitting(false);
        	}
        	
        }else {
        if (this.getIsBirdSitting())
        {
            if (this.world.getBlockState(blockpos1).isNormalCube()||this.world.getBlockState(blockpos1)==KCore.ButterflyFlower.getDefaultState().withProperty(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH))
            {

                if (this.world.getNearestPlayerNotCreative(this, 4.0D) != null)
                {
                    this.setIsBirdSitting(false);
                    this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
                }
            }
            else
            {
                this.setIsBirdSitting(false);
                this.world.playEvent((EntityPlayer)null, 1025, blockpos, 0);
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

            if (this.rand.nextInt(100) == 0 && (this.world.getBlockState(blockpos1).isNormalCube()||this.world.getBlockState(blockpos1).getBlock()instanceof BlockMysticBush)||this.world.getBlockState(blockpos1)==KCore.ButterflyFlower.getDefaultState().withProperty(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH))
            {
                this.setIsBirdSitting(true);
            }
            if(ticker>=30){
                EntityPlayer ep = world.getNearestPlayerNotCreative(this,15D);
                if(ep!=null){
                    d0 = ep.posX + getRNG().nextFloat()-getRNG().nextFloat() + 0.5D - this.posX;
                    d1 = ep.posY + getRNG().nextFloat() + 0.1D - this.posY;
                    d2 = ep.posZ + getRNG().nextFloat()-getRNG().nextFloat() + 0.5D - this.posZ;
                    this.motionX += (Math.signum(d0) * 0.5D - this.motionX) * 0.10000000149011612D;
                    this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
                    this.motionZ += (Math.signum(d2) * 0.5D - this.motionZ) * 0.10000000149011612D;
                    f = (float)(MathHelper.atan2(this.motionZ, this.motionX) * (180D / Math.PI)) - 90.0F;
                    f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
                    this.moveForward = 0.5F;
                    this.rotationYaw += f1;
                }
            }
        }
        }
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    public void fall(float distance, float damageMultiplier)
    {
    }

    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    /**
     * Return whether this entity should NOT trigger a pressure plate or a tripwire.
     */
    public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
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
            if (!this.world.isRemote && this.getIsBirdSitting())
            {
                this.setIsBirdSitting(false);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public static void registerFixesBat(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityButterfly1.class);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.dataManager.set(SITTING, Byte.valueOf(compound.getByte("BatFlags")));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("BatFlags", ((Byte)this.dataManager.get(SITTING)).byteValue());
    }

    public float getEyeHeight()
    {
        return this.height / 2.0F;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_BUTTERFLY;
    }
}