package io.github.krevik.kathairis.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;

import java.util.function.Predicate;

public class EntityAITargetSpecified<T extends LivingEntity> extends NearestAttackableTargetGoal<T>
{
    private final CreatureEntity tameable;

    public EntityAITargetSpecified(CreatureEntity entityIn, Class<T> classTarget, boolean checkSight, Predicate<LivingEntity> p_i50315_6_)
    {
        super(entityIn, classTarget, 10, checkSight, false, p_i50315_6_);
        this.tameable = entityIn;
    }

    @Override
    public boolean shouldExecute()
    {
        return super.shouldExecute();
    }
}