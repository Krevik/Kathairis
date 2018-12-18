package mod.krevik.entity;

import java.util.Map;

import javax.annotation.Nullable;

import mod.krevik.enchantment.KathairisEnchantments;
import mod.krevik.KCore;
import mod.krevik.util.MysticLootTables;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.World;

public class EntitySteveGhost extends EntityMob
{
    public EntitySteveGhost(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);

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
    	if(this.getAttackTarget()!=null) {
    		if(this.getAttackTarget() instanceof EntityPlayerMP) {
    			EntityPlayerMP ep = (EntityPlayerMP) this.getAttackTarget();
    			ep.addPotionEffect(new PotionEffect(Potion.getPotionById(17),150,2));
    			ep.addPotionEffect(new PotionEffect(Potion.getPotionById(15),150,2));
    			ep.addPotionEffect(new PotionEffect(Potion.getPotionById(27),150,2));

    		}
    	}
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
    	if(source.getTrueSource() instanceof EntityPlayerMP) {
    		EntityPlayerMP ep = (EntityPlayerMP) source.getTrueSource();
        	Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(ep.getHeldItemMainhand());
    		if(map.containsKey(KathairisEnchantments.Ethereal)) {
    			this.setDead();
    			return true;
    		}else {
    			return false;
    		}
    	}else {
    		this.heal(10f);
    		return false;
    	}
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        if(entityIn instanceof EntityPlayerMP) {
        	EntityPlayerMP ep = (EntityPlayerMP) entityIn;
			ep.addPotionEffect(new PotionEffect(Potion.getPotionById(20),150,2));
			ep.addPotionEffect(new PotionEffect(Potion.getPotionById(9),300,2));
			ep.addPotionEffect(new PotionEffect(Potion.getPotionById(2),200,2));
        }
        this.onDeath(DamageSource.GENERIC);
        this.setDead();
        return flag;
    }

    protected SoundEvent getAmbientSound()
    {
        return KCore.instance.proxy.ghost_living;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KCore.instance.proxy.ghost_attack;
    }

    protected SoundEvent getDeathSound()
    {
        return KCore.instance.proxy.ghost_dead;
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
        return MysticLootTables.LOOT_STEVEGHOST;
    }

    public static void registerFixesZombie(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntitySteveGhost.class);
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

 
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }
}