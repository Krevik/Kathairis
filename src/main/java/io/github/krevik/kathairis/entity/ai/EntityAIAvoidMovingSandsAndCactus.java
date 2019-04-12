package io.github.krevik.kathairis.entity.ai;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class EntityAIAvoidMovingSandsAndCactus extends EntityAIBase
{
    private final EntityCreature creature;
    private double shelterX;
    private double shelterY;
    private double shelterZ;
    private final double movementSpeed;
    private final World world;

    public EntityAIAvoidMovingSandsAndCactus(EntityCreature theCreatureIn, double movementSpeedIn)
    {
        this.creature = theCreatureIn;
        this.movementSpeed = movementSpeedIn;
        this.world = theCreatureIn.world;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	boolean areMovingSandsNear=false;
    	ArrayList<BlockPos> poses = new ArrayList<>();
    	for(int x=-2;x<=2;x++) {
    		for(int y=-2;y<=2;y++) {
    			for(int z=-2;z<=2;z++) {
    				BlockPos tmp = new BlockPos(this.creature.posX+x,this.creature.posY+y,this.creature.posZ+z);
    				poses.add(tmp);
    			}
    		}
    	}
    	for(int c=0;c<poses.size();c++) {
    		if(this.creature.world.getBlockState(poses.get(c))== ModBlocks.SOFT_SAND.getDefaultState()||this.creature.world.getBlockState(poses.get(c))== ModBlocks.KATHAIRIS_SUCCULENT.getDefaultState()) {
    			areMovingSandsNear=true;
    		}
    	}
    	
    	if(areMovingSandsNear) {
            Vec3d vec3d = this.findPossibleShelter();

            if (vec3d == null)
            {
                return false;
            }
            else
            {
                this.shelterX = vec3d.x;
                this.shelterY = vec3d.y;
                this.shelterZ = vec3d.z;
                return true;
            }
    	}else {
    		return false;
    	}
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return !this.creature.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.creature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
    }

    @Nullable
    private Vec3d findPossibleShelter()
    {
        Random random = this.creature.getRNG();
        BlockPos blockpos = new BlockPos(this.creature.posX, this.creature.getBoundingBox().minY, this.creature.posZ);

        for (int i = 0; i < 3; ++i)
        {
            BlockPos blockpos1 = blockpos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);

            if (this.creature.world.getBlockState(new BlockPos(blockpos1.getX(),this.creature.world.getHeight(Heightmap.Type.MOTION_BLOCKING,blockpos1).getY()+1,blockpos1.getZ()))!=ModBlocks.KATHAIRIS_SUCCULENT.getDefaultState()&&this.creature.world.getBlockState(new BlockPos(blockpos1.getX(),this.creature.world.getHeight(Heightmap.Type.MOTION_BLOCKING,blockpos1).getY()-1,blockpos1.getZ()))!=ModBlocks.SOFT_SAND.getDefaultState() && this.creature.getBlockPathWeight(blockpos1) < 0.0F)
            {
                return new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
            }
        }

        return null;
    }
}