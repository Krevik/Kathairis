package io.github.krevik.kathairis.entity;

import io.github.krevik.kathairis.entity.ai.EntityAIAvoidMovingSandsAndCactus;
import io.github.krevik.kathairis.init.ModEntities;
import io.github.krevik.kathairis.util.KatharianLootTables;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class EntityPoisonousScorpion extends MonsterEntity
{
    public EntityPoisonousScorpion(World worldIn)
    {
        super(ModEntities.POISONOUS_SCORPION,worldIn);
        this.experienceValue=25;
    }

    public EntityPoisonousScorpion(EntityType<EntityPoisonousScorpion> type, World world) {
        super(type, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(0, new EntityAIAvoidMovingSandsAndCactus(this,1.2D));
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
    protected ResourceLocation getLootTable() {
        return KatharianLootTables.LOOT_POISONOUSSCORPION;
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
            if (entityIn instanceof LivingEntity)
            {
                int i = 0;

                if (this.world.getDifficulty() == Difficulty.NORMAL)
                {
                    i = 7;
                }
                else if (this.world.getDifficulty() == Difficulty.HARD)
                {
                    i = 15;
                }

                if (i > 0)
                {
                    ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.POISON, i * 20, 0));
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

}