package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityPoisonousScorpion extends EntityMob
{
    public EntityPoisonousScorpion(World worldIn)
    {
        super(ModEntities.POISONOUS_SCORPION,worldIn);
        this.setSize(0.4F, 0.7F);
        this.experienceValue=25;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.2D, false));
        this.tasks.addTask(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3000000417232513D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    @Override
    protected void registerData()
    {
        super.registerData();
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if (super.attackEntityAsMob(entityIn))
        {
            if (entityIn instanceof EntityLivingBase)
            {
                int i = 0;

                if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
                {
                    i = 7;
                }
                else if (this.world.getDifficulty() == EnumDifficulty.HARD)
                {
                    i = 15;
                }

                if (i > 0)
                {
                    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, 0));
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public void tick() {
    	super.tick();
    	if(!this.world.isRemote) {
    		
    	}
    }

    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.ARTHROPOD;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_POISONOUSSCORPION;
    }

}