package com.Krevik.Entities;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLivingFlower extends EntityMob
{
	private int timer=0;
	public EntityPlayerMP target=null;
    private static final DataParameter<Integer> animationTime = EntityDataManager.<Integer>createKey(EntityLivingFlower.class, DataSerializers.VARINT);

    public EntityLivingFlower(World worldIn)
    {
        super(worldIn);
        this.setSize(0.3F, 0.5F);
        this.experienceValue=10;
    }

    protected void initEntityAI()
    {

    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(animationTime, 0);
    }

    public int getAnimationTime()
    {
        return MathHelper.clamp(dataManager.get(animationTime), 0, 4);
    }

    public void setAnimationTime(int meta) {
        dataManager.set(animationTime, meta);
    }

    public void notifyDataManagerChange(DataParameter<?> key)
    {

        super.notifyDataManagerChange(key);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
    	this.motionY=-1;
    	this.motionX=0;
    	this.motionZ=0;
    	this.posX=this.lastTickPosX;
    	this.posZ=this.lastTickPosZ;
        timer++;
        this.rotationPitch=0;
        this.rotationYaw=0;
        this.rotationYawHead=0;
        if(getAnimationTime()>0){
            setAnimationTime(getAnimationTime()+1);
        }
        if(getAnimationTime()>499){
            setAnimationTime(0);
        }
            EntityPlayer ep = this.world.getClosestPlayerToEntity(this, 5);
        if(ep!=null) {
        	if(ep instanceof EntityPlayerMP) {
        		EntityPlayerMP target = (EntityPlayerMP) ep;
        		if(target!=null&&!target.isCreative()) {
        			this.target=target;
        			timer++;
        			if(timer>=100) {
                    	if(target!=null) {
            				target.heal(1F);
            				timer=0;
                    	}
        			}
        			if(getRNG().nextInt(100)==0){
        			    if(getAnimationTime()==0){
                            setAnimationTime(1);
                        }
                    }


        		}

        	}
        }else {
        	target=null;
        }
        if(this.world.isRemote) {
        	if(this.rand.nextInt(30)==1) {
        		if(ep!=null) {
        			this.world.spawnParticle(EnumParticleTypes.HEART, this.posX, this.posY+0.5, this.posZ, 0, (ep.posY-this.posY)/1000+1, 0);
        		}
        	}
        }
        super.onLivingUpdate();

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
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.FLOWER_POT && !player.capabilities.isCreativeMode && !this.isChild())
        {
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(KCore.PotWithLivingFlower));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(KCore.PotWithLivingFlower)))
            {
                player.dropItem(new ItemStack(KCore.PotWithLivingFlower), false);
            }
            this.posY=-20;
            this.setDead();
            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
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
        return MysticLootTables.LOOT_LIVINGFLOWER;
    }

    public static void registerFixesLivingFlower(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityLivingFlower.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("animation_time", getAnimationTime());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        setAnimationTime(compound.getInteger("animation_time"));

    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }
}