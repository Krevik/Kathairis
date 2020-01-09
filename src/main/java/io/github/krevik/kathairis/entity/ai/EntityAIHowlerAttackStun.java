package io.github.krevik.kathairis.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;

public class EntityAIHowlerAttackStun extends Goal
{
    World world;
    protected CreatureEntity attacker;
    /** An amount of decrementing ticks that allows the entity to attack once the tick reaches 0. */
    protected int attackTick;
    /** The speed with which the mob will approach the target */
    double speedTowardsTarget;
    /** When true, the mob will continue chasing its target, even if it can't find a path to them right now. */
    boolean longMemory;
    /** The PathEntity of our entity. */
    Path path;
    private int delayCounter;
    private double targetX;
    private double targetY;
    private double targetZ;
    protected final int attackInterval = 20;
    private int failedPathFindingPenalty = 0;
    private boolean canPenalize = false;

    public EntityAIHowlerAttackStun(CreatureEntity creature, double speedIn, boolean useLongMemory)
    {
        this.attacker = creature;
        this.world = creature.world;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexFlags(EnumSet.of(Flag.JUMP, Flag.MOVE, Flag.LOOK, Flag.TARGET));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
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
                    this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase,0);
                    this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
                    return this.path != null;
                }
                else
                {
                    return true;
                }
            }
            this.path = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase,0);

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

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
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
        else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(entitylivingbase)))
        {
            return false;
        }
        else
        {
            return !(entitylivingbase instanceof PlayerEntity) || !((PlayerEntity)entitylivingbase).isSpectator() && !((PlayerEntity)entitylivingbase).isCreative();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.delayCounter = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        LivingEntity entitylivingbase = this.attacker.getAttackTarget();

        if (entitylivingbase instanceof PlayerEntity && (((PlayerEntity)entitylivingbase).isSpectator() || ((PlayerEntity)entitylivingbase).isCreative()))
        {
            this.attacker.setAttackTarget(null);
        }

        this.attacker.getNavigator().clearPath();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
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
            if(attacker.getRNG().nextInt(4)==0) {
                //TODO
                //p_190102_1_.addPotionEffect(new PotionEffect(KCore.stun_potion, 100, 1));
            }
        }
    }

    protected double getAttackReachSqr(LivingEntity attackTarget)
    {
        return (double)(this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth());
    }
}