package io.github.krevik.kathairis.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.EnumSet;

public class EntityAIAttackTarget extends MeleeAttackGoal
{
    protected LivingEntity targetEntity;


    public EntityAIAttackTarget(CreatureEntity creature, LivingEntity classTarget)
    {
        super(creature,creature.getAIMoveSpeed(), false);
        this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
        targetEntity=classTarget;
    }

    @Override
    public boolean shouldExecute()
    {
    	this.targetEntity=this.attacker.getAttackTarget();
    	return true;
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance)
    {
        return this.attacker.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
    }

    @Override
    public void startExecuting()
    {
        this.attacker.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}