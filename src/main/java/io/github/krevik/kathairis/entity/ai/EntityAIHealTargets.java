package io.github.krevik.kathairis.entity.ai;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.EnumSet;

public class EntityAIHealTargets extends Goal
{
    private final CreatureEntity creature;
    private final World world;
    private PlayerEntity target;

    public EntityAIHealTargets(CreatureEntity theCreatureIn)
    {
        this.creature = theCreatureIn;
        this.world = theCreatureIn.world;
        this.setMutexFlags(EnumSet.of(Flag.TARGET));
    }

    @Override
    public boolean shouldExecute()
    {
        return world.getClosestPlayer(creature,5)!=null;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return false;
    }

    @Override
    public void startExecuting()
    {
        LivingEntity entity = world.getClosestPlayer(creature,5);
        if(entity!=null){
            if(entity instanceof PlayerEntity){
                target=(PlayerEntity)entity;
            }
        }
        if(target!=null) {
            if (creature.getRNG().nextInt(30)==0) {
                target.heal(1);
            }
        }
    }

}