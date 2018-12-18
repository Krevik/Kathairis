package mod.krevik.Entities.AI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

import net.minecraft.util.math.AxisAlignedBB;

public class EntityAIAttackTarget<T extends EntityLivingBase> extends EntityAITarget
{
    protected EntityLivingBase targetEntity;


    public EntityAIAttackTarget(EntityCreature creature, EntityLivingBase classTarget)
    {
        super(creature, false);
        this.setMutexBits(1);
        targetEntity=classTarget;
    
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	this.targetEntity=this.taskOwner.getAttackTarget();
    	return true;
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance)
    {
        return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}