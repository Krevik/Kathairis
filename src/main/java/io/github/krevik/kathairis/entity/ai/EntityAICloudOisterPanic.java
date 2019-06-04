package io.github.krevik.kathairis.entity.ai;

import io.github.krevik.kathairis.entity.EntityCloudOister;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class EntityAICloudOisterPanic extends EntityAIBase
{
    protected final EntityCloudOister creature;
    protected double speed;

    public EntityAICloudOisterPanic(EntityCloudOister creature, double speedIn)
    {
        this.creature = creature;
        this.speed = speedIn;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        return this.creature.getRevengeTarget() != null || this.creature.isBurning();
    }

    @Override
    public void startExecuting()
    {
    	
    }

    @Override
    public boolean shouldContinueExecuting()
    {
    	return creature.panic();
    }
    
    int jumpTimer=0;
    @Override
    public void tick()
    {
    	jumpTimer++;
    	if(jumpTimer>12) {
    		jumpTimer=0;
			creature.motionY+=0.5;
            creature.spawnJumpParticles(creature.getEntityWorld());
			double destPosX=creature.posX-creature.getRNG().nextInt(6)+creature.getRNG().nextInt(6);
			double destPosZ=creature.posZ-creature.getRNG().nextInt(6)+creature.getRNG().nextInt(6);
			creature.getNavigator().setPath(creature.getNavigator().getPathToPos(new BlockPos(destPosX,creature.posY,destPosZ)), 1);
			creature.motionX=(destPosX-creature.posX)*0.15;
			creature.motionZ=(destPosZ-creature.posZ)*0.15;
    	}
    }
}