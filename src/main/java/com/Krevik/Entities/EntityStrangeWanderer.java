package com.Krevik.Entities;

import javax.annotation.Nullable;

import com.Krevik.Enchantments.KathairisEnchantments;
import com.Krevik.Main.KCore;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.Calendar;
import java.util.Map;

public class EntityStrangeWanderer extends EntityMob
{
    public EntityStrangeWanderer(World worldIn)
    {
        super(worldIn);
        this.setSize(1F, 2F);
        this.experienceValue=0;
    }

    protected void initEntityAI()
    {

    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
            if(source.getTrueSource() instanceof EntityPlayer) {
                EntityPlayer attacker = (EntityPlayer) source.getTrueSource();
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(attacker.getHeldItemMainhand());
                if(map.containsKey(KathairisEnchantments.Ethereal)) {
                    this.damageEntity(source, 6);
                    return true;
                }
            }
        return false;
    }
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
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
    
    public void onUpdate() {
    	super.onUpdate();
    	EntityPlayer ep = this.world.getClosestPlayer(posX, posY, posZ, 15, true);
    	if(ep!=null) {
            this.getLookHelper().setLookPosition(ep.posX, ep.posY + (double)ep.getEyeHeight(), ep.posZ, (float)100, (float)100);
    	}

    }


    protected SoundEvent getAmbientSound()
    {
        return KCore.instance.cproxy.oldman_ambient;
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

    EntityPlayer lastTalker;
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        int month= Calendar.MONTH;
    	lastTalker=player;
    	if(month==11) {
            if (!KCore.instance.functionHelper.isGift(player.getHeldItemMainhand())) {
                player.openGui(KCore.instance, KCore.GUI_ENUM.OLDMAN.ordinal(),
                        player.world,
                        (int) player.posX,
                        (int) player.posY,
                        (int) player.posZ);
            }
        }else{
            player.openGui(KCore.instance, KCore.GUI_ENUM.OLDMAN.ordinal(),
                    player.world,
                    (int) player.posX,
                    (int) player.posY,
                    (int) player.posZ);
        }
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
        return null;
    }

    public static void registerFixesLivingFlower(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityStrangeWanderer.class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

    }
    
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {

    	return super.onInitialSpawn(difficulty, livingdata);
    	
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }
}