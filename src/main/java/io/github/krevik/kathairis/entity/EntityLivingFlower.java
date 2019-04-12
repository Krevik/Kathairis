package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIHealTargets;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityLivingFlower extends EntityAnimal
{
    private static final DataParameter<Boolean> canDespawn = EntityDataManager.createKey(EntityLivingFlower.class, DataSerializers.BOOLEAN);
    public EntityLivingFlower(World worldIn)
    {
        super(ModEntities.LIVING_FLOWER,worldIn);
        this.setSize(0.3F, 0.5F);
        this.experienceValue=10;
    }

    public void deallowDespawning(){
        getDataManager().set(canDespawn,false);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(1, new EntityAIHealTargets(this));
    }
    
    @Override
    public boolean canDespawn()
    {
        return getDataManager().get(canDespawn).booleanValue();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(5.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
    }

    @Override
    protected void registerData()
    {
        super.registerData();
        this.getDataManager().register(canDespawn, Boolean.valueOf(true));
    }
    
    @Override
    public void tick() {
        this.motionY=-1;
        this.motionX=0;
        this.motionZ=0;
        this.posX=this.lastTickPosX;
        this.posZ=this.lastTickPosZ;
        this.rotationPitch=0;
        this.rotationYaw=0;
        this.rotationYawHead=0;
        if(this.world.isRemote) {
            if(this.rand.nextInt(30)==1) {
                //this.world.spawnParticle(EnumParticleTypes.HEART, this.posX, this.posY+0.5, this.posZ, 0, 0.2, 0);
            }
        }
    	super.tick();
    }

    
    /*public boolean processInteract(EntityPlayer player, EnumHand hand)
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
    }*/

    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.UNDEFINED;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_LIVINGFLOWER;
    }

    @Override
    public void writeAdditional(NBTTagCompound compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("canDespawn",this.getDataManager().get(canDespawn));
    }

    @Override
    public void readAdditional(NBTTagCompound compound)
    {
        super.readAdditional(compound);
        getDataManager().set(canDespawn,compound.getBoolean("canDespawn"));
    }
}