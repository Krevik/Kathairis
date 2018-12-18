package com.krevik.Entities;

import javax.annotation.Nullable;

import com.krevik.Main.KCore;
import com.krevik.Main.MysticLootTables;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySkeletonWarrior extends AbstractSkeleton
{
	
    public EntitySkeletonWarrior(World worldIn)
    {
        super(worldIn);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,new ItemStack(KCore.DarknessSword));
        this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(KCore.DarknessSword));
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);

    }
    public void onLivingUpdate()
    {
        
        if(this.world.getNearestAttackablePlayer(getPosition(), 30, 5)!=null) {
        	this.setAttackTarget(this.world.getNearestAttackablePlayer(getPosition(), 30, 5));
        }

        super.onLivingUpdate();
    }
    EntityPlayer target = null;
    public void onUpdate() {
    	super.onUpdate();
    	if(!this.world.isRemote) {
    		target=this.world.getClosestPlayer(this.posX, this.posY, this.posZ, 10, false);
    		if(target!=null) {
    			if(!target.isCreative()) {
    			Path path = this.navigator.getPathToXYZ(target.posX, target.posY, target.posZ);
    			if(path!=null) {
    				this.navigator.setPath(path, 1);
    			}
    			if(this.getDistance(target)<1.2) {
					target.attackEntityFrom(DamageSource.causeMobDamage(this), 6F);
    			}
    			}
    		}
    		
    	}
    }
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(KCore.DarknessSword));
    }
    
    public boolean attackEntityAsMob(Entity entityIn)
    {
        this.setLastAttackedEntity(entityIn);
        if(entityIn instanceof EntityLivingBase) {
        	EntityLivingBase target = (EntityLivingBase) entityIn;
        	target.addPotionEffect(new PotionEffect(Potion.getPotionById(9),100,1));
        	target.addPotionEffect(new PotionEffect(Potion.getPotionById(15),100,1));
        	target.addPotionEffect(new PotionEffect(Potion.getPotionById(2),100,1));
        }
        return true;
    }
    
    public static void registerFixesSkeleton(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntitySkeletonWarrior.class);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return MysticLootTables.LOOT_SKELETONWARRIOR;
    }

    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }

}