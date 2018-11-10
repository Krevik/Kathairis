package com.Krevik.Entities;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Dimension.TileEntityKether;
import com.Krevik.Enchantments.KathairisEnchantments;
import com.Krevik.Main.KCore;
import com.Krevik.Main.MysticLootTables;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketDeathHandlerServer;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityDeath extends EntityLiving
{
	private boolean defeated;
	public boolean isFighting;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

    public EntityDeath(World worldIn)
    {
        super(worldIn);
        this.setSize(1.25F, 4F);
        defeated=false;
        isFighting=false;
    }
    
    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    protected void initEntityAI()
    {
        this.experienceValue=1000;
        this.applyEntityAI();
    }
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }
    protected void applyEntityAI()
    {
    	
    }
    

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(500);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15);


    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * Get the experience points the entity currently has.
     */
    protected int getExperiencePoints(EntityPlayer player)
    {
        return super.getExperiencePoints(player);
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
        super.onLivingUpdate();
    }
    
    /**
     * Add the given player to the list of players tracking this entity. For instance, a player may track a boss in
     * order to view its associated boss bar.
     */
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    /**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(KCore.Scythe));
    }
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(KCore.Scythe));
        return livingdata;
    }
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        return flag;
    }
    
    KetherDataStorage data;
    public void onUpdate() {
    	super.onUpdate();
	    	if(data==null) {
	    		data=KetherDataStorage.getDataInstance(world);
	    	}
    	if(data!=null) {

    	}
    }
    
    
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(isFighting) {
    		if(source.getTrueSource() instanceof EntityPlayer) {
    			EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
            	Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
    			if(map.containsKey(KathairisEnchantments.Ethereal)) {
    				this.damageEntity(source, 6);
    				return true;
    			}
    		}
    	}
    	return false;
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.instance.cproxy.death_living;
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

    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    public static void registerFixesHowler(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityDeath.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("defeated", defeated);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        defeated=compound.getBoolean("defeated");
        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
    	if(!isFighting) {
        player.openGui(KCore.instance, KCore.GUI_ENUM.DEATH.ordinal(), 
                player.world, 
                (int)player.posX, 
                (int)player.posY, 
                (int)player.posZ); 
    	}
        return super.processInteract(player, hand);

    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLivingBase entityLivingIn)
    {
        super.onKillEntity(entityLivingIn);
        isFighting=false;
    }

    protected boolean canEquipItem(ItemStack stack)
    {
        return false;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {

        super.onDeath(cause);

    }

}