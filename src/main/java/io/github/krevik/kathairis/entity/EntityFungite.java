package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityFungite extends EntityMob
{
    public EntityFungite(World worldIn)
    {
        super(ModEntities.FUNGITE,worldIn);
        this.setSize(1.5F, 2.4F);
        this.experienceValue=30;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
        this.applyEntityAI();
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2500000417232513D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    }

    @Override
    public CreatureAttribute getCreatureAttribute()
    {
        return CreatureAttribute.UNDEFINED;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return KatharianLootTables.LOOT_FUNGITE;
    }

}