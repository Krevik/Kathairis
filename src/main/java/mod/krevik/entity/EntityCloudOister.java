package mod.krevik.entity;

import mod.krevik.KCore;
import mod.krevik.block.BlockMysticCloud;
import mod.krevik.entity.ai.EntityAIPanicNew;
import mod.krevik.network.KathairisPacketHandler;
import mod.krevik.network.packets.PacketCloudOisterClient;
import mod.krevik.util.MysticLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import javax.annotation.Nullable;

public class EntityCloudOister extends EntityAmbientCreature
{
    private static final DataParameter<Integer> timeUntilNextPearl = EntityDataManager.createKey(EntityCloudOister.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> panic = EntityDataManager.createKey(EntityCloudOister.class, DataSerializers.BOOLEAN);
    
    public int timeUntilNextPearl() {
    	return this.dataManager.get(timeUntilNextPearl).intValue();
    }
    public void setTimeUntilNextPearl(int x) {
    	this.dataManager.set(timeUntilNextPearl, Integer.valueOf(x));
    }
    
    public boolean panic() {
    	return this.dataManager.get(panic).booleanValue();
    }
    
    public void setPanic(boolean trueorfalse) {
    	this.dataManager.set(panic, Boolean.valueOf(trueorfalse));
    }

    public EntityCloudOister(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.6F);
        this.experienceValue=5;
        this.setTimeUntilNextPearl(this.rand.nextInt(6000) + 6000);
        this.setPanic(false);
    }
    

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(1, new EntityAIPanicNew(this,1D));

    }
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(timeUntilNextPearl, 24000);
        this.dataManager.register(panic, Boolean.valueOf(false));
    }


    public void notifyDataManagerChange(DataParameter<?> key)
    {
        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    int jumpTimer=0;
    int k = 50+rand.nextInt(300);
    int panicTimer=0;
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.setTimeUntilNextPearl(this.timeUntilNextPearl()-1);
        if (!this.world.isRemote && this.timeUntilNextPearl() <= 0)
        {
            this.dropItem(KCore.CloudPearl, 1);
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
	        			motionY+=0.5;
	        			IMessage message = new PacketCloudOisterClient((float)posX,(float)posY,(float)posZ);
	        			KathairisPacketHandler.CHANNEL.sendToAll(message);
	        		}
	        		if(!this.onGround) {
	        			if(this.getRNG().nextInt(100)==0) {
	        				double destPosX=this.posX-this.getRNG().nextInt(6)+this.getRNG().nextInt(6);
	        				double destPosZ=this.posZ-this.getRNG().nextInt(6)+this.getRNG().nextInt(6);
	        				this.getNavigator().setPath(this.getNavigator().getPathToPos(new BlockPos(destPosX,posY,destPosZ)), 1);
	        				motionX=(destPosX-this.posX)*0.15;
	        				motionZ=(destPosZ-this.posZ)*0.15;
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
    
    public void onUpdate() {
    	super.onUpdate();
    
    }


    protected SoundEvent getAmbientSound()
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

    protected SoundEvent getStepSound()
    {
        return null;
    }

    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
            return super.processInteract(player, hand);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_CLOUDOISTER;
    }

    public static void registerFixesLivingFlower(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityCloudOister.class);
    }
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
    	this.motionY=0.5;
		IMessage message = new PacketCloudOisterClient((int)posX,(int)posY,(int)posZ);
		KathairisPacketHandler.CHANNEL.sendToAll(message);
        return flag;
    }
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
		IMessage message = new PacketCloudOisterClient((int)posX,(int)posY,(int)posZ);
		KathairisPacketHandler.CHANNEL.sendToAll(message);
    	return super.attackEntityFrom(source, amount);
    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("timeUntilNextPearl", this.timeUntilNextPearl());
        compound.setBoolean("panic", panic());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.setTimeUntilNextPearl(compound.getInteger("timeUntilNextPearl"));
        this.setPanic(compound.getBoolean("panic"));
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }
    
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        Block block = this.world.getBlockState(blockpos.down()).getBlock();
        return block instanceof BlockMysticCloud;
    }
    
}