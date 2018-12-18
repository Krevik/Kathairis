package mod.krevik.entity.ai;

import mod.krevik.entity.EntityCloudOister;
import mod.krevik.network.KetherPacketHandler;
import mod.krevik.network.PacketCloudOisterClient;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityAIPanicNew extends EntityAIBase
{
    protected final EntityCloudOister creature;
    protected double speed;
    protected double randPosX;
    protected double randPosY;
    protected double randPosZ;

    public EntityAIPanicNew(EntityCloudOister creature, double speedIn)
    {
        this.creature = creature;
        this.speed = speedIn;
        this.setMutexBits(3);
    }
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.creature.getRevengeTarget() == null && !this.creature.isBurning())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
    	return creature.panic();
    }
    
    int jumpTimer=0;
    public void updateTask()
    {
    	jumpTimer++;
    	if(jumpTimer>12) {
    		jumpTimer=0;
			creature.motionY+=0.5;
			IMessage message = new PacketCloudOisterClient((float)creature.posX,(float)creature.posY,(float)creature.posZ);
			KetherPacketHandler.CHANNEL.sendToAll(message);
			double destPosX=creature.posX-creature.getRNG().nextInt(6)+creature.getRNG().nextInt(6);
			double destPosZ=creature.posZ-creature.getRNG().nextInt(6)+creature.getRNG().nextInt(6);
			creature.getNavigator().setPath(creature.getNavigator().getPathToPos(new BlockPos(destPosX,creature.posY,destPosZ)), 1);
			creature.motionX=(destPosX-creature.posX)*0.15;
			creature.motionZ=(destPosZ-creature.posZ)*0.15;
    	}
    }
}