package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIHealTargets;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.init.ModItems;
import io.github.krevik.kathairis.util.KatharianLootTables;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.github.krevik.kathairis.util.networking.packets.PacketClientSpawnHeartParticle;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

public class EntityLivingFlower extends EntityKatharianAnimal
{
    private static final DataParameter<Boolean> canDespawn = EntityDataManager.createKey(EntityLivingFlower.class, DataSerializers.BOOLEAN);
    public EntityLivingFlower(World worldIn)
    {
        super(ModEntities.LIVING_FLOWER,worldIn);
        this.experienceValue=10;
    }

    public EntityLivingFlower(EntityType<EntityLivingFlower> type, World world) {
        super(type, world);
    }


    public void deallowDespawning(){
        getDataManager().set(canDespawn,false);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new EntityAIHealTargets(this));
    }

    @Override
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_LIVINGFLOWER;
    }

    @Override
    public boolean canDespawn(double p_213397_1_) {
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
        setMotion(new Vec3d(0,-1,0));
        setPosition(this.lastTickPosX,getPosition().getY(),this.lastTickPosZ);
        this.rotationPitch=0;
        this.rotationYaw=0;
        this.rotationYawHead=0;
        if(!this.world.isRemote) {
            if(this.rand.nextInt(30)==1) {
                PacketHandler.sendToAll(new PacketClientSpawnHeartParticle(this.getPosition().getX(), this.getPosition().getY()+0.5, this.getPosition().getZ(), 0, 0.2, 0));
            }
        }
    	super.tick();
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.FLOWER_POT && !player.isCreative() && !this.isChild())
        {
            itemstack.shrink(1);

            if (itemstack.isEmpty())
            {
                player.setHeldItem(hand, new ItemStack(ModItems.POT_WITH_LIVING_FLOWER));
            }
            else if (!player.inventory.addItemStackToInventory(new ItemStack(ModItems.POT_WITH_LIVING_FLOWER)))
            {
                player.dropItem(new ItemStack(ModItems.POT_WITH_LIVING_FLOWER), false);
            }
            this.setPosition(0,-50,0);
            this.remove();
            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.UNDEFINED;
    }


    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putBoolean("canDespawn",this.getDataManager().get(canDespawn));
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
        getDataManager().set(canDespawn,compound.getBoolean("canDespawn"));
    }
}