package io.github.krevik.kathairis.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.EnumSet;

public class EntityAIAttackMeleeBison extends Goal
{

    World world;
    protected CreatureEntity attacker;
    protected int attackTick;
    double speedTowardsTarget;
    boolean longMemory;
    Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityAIAttackMeleeBison(CreatureEntity creature, double speedIn, boolean useLongMemory)
    {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
    }

    @Override
    public boolean shouldExecute()
    {
        LivingEntity entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isAlive())
        {
            return false;
        }
        else
        {
            if (canPenalize)
            {
                if (--this.delayCounter <= 0)
                {
                    this.path = this.attacker.getNavigator().getPathToEntity(entitylivingbase,0);
                    this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                    return this.path != null;
                }
                else
                {
                    return true;
                }
            }
            this.path = this.attacker.getNavigator().getPathToEntity(entitylivingbase,0);

            if (this.path != null)
            {
                return true;
            }
            else
            {
                return this.getAttackReachSqr(entitylivingbase) >= this.attacker.getDistanceSq(entitylivingbase.getPosition().getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getPosition().getZ());
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        LivingEntity entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isAlive())
        {
            return false;
        }
        else if (!this.longMemory)
        {
            return !this.attacker.getNavigator().noPath();
        }
        else
        {
            return !(entitylivingbase instanceof PlayerEntity) || !((PlayerEntity)entitylivingbase).isSpectator() && !((PlayerEntity)entitylivingbase).isCreative();
        }
    }

    @Override
    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.delayCounter = 0;
    }

    @Override
    public void resetTask()
    {
        LivingEntity entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase instanceof PlayerEntity && (((PlayerEntity)entitylivingbase).isSpectator() || ((PlayerEntity)entitylivingbase).isCreative()))
        {
            this.attacker.setAttackTarget(null);
        }

        this.attacker.getNavigator().clearPath();
    }

    @Override
    public void tick()
    {
        LivingEntity entitylivingbase = this.attacker.getAttackTarget();
        this.attacker.getLookController().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
        double d0 = this.attacker.getDistanceSq(entitylivingbase.getPosition().getX(), entitylivingbase.getBoundingBox().minY, entitylivingbase.getPosition().getZ());
        --this.delayCounter;

        if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || entitylivingbase.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F))
        {
            this.targetX = entitylivingbase.getPosition().getX();
            this.targetY = entitylivingbase.getBoundingBox().minY;
            this.targetZ = entitylivingbase.getPosition().getZ();
            this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);

            if (this.canPenalize)
            {
                this.delayCounter += failedPathFindingPenalty;
                if (this.attacker.getNavigator().getPath() != null)
                {
                    net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
                    if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                        failedPathFindingPenalty = 0;
                    else
                        failedPathFindingPenalty += 10;
                }
                else
                {
                    failedPathFindingPenalty += 10;
                }
            }

            if (d0 > 1024.0D)
            {
                this.delayCounter += 10;
            }
            else if (d0 > 256.0D)
            {
                this.delayCounter += 5;
            }

            if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget))
            {
                this.delayCounter += 15;
            }
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(entitylivingbase, d0);
    }

    protected void checkAndPerformAttack(LivingEntity p_190102_1_, double p_190102_2_)
    {
        double d0 = this.getAttackReachSqr(p_190102_1_);

        if (p_190102_2_ <= d0 && this.attackTick <= 0)
        {
            this.attackTick = 20;
            this.attacker.swingArm(Hand.MAIN_HAND);
            this.attacker.attackEntityAsMob(p_190102_1_);
        }
    }

    protected double getAttackReachSqr(LivingEntity attackTarget)
    {
        return (double)(this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth());
    }
}