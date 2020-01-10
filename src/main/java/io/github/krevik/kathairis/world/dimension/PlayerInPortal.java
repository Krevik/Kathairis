package io.github.krevik.kathairis.world.dimension;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerInPortal {
    private PlayerEntity player;
    private int timeInPortal;

    public PlayerInPortal(PlayerEntity ply, int time){
        player=ply;
        timeInPortal=time;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public int getTimeInPortal() {
        return timeInPortal;
    }

    public void setTimeInPortal(int time){
        timeInPortal=time;
    }
}
