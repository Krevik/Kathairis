package com.krevik.Capabilities.EnteringKathairis;

import net.minecraft.entity.EntityLivingBase;

public class WasInKathairisHandler implements IWasInKathairisCapability {
    private EntityLivingBase host;
    private boolean wasInKathairisBefore;

    @Override
    public void setEntity(EntityLivingBase entity) {
        host = entity;
    }

    public boolean wasInKathairisBefore(){
        return wasInKathairisBefore;
    }

    @Override
    public void setWasInKathairis(boolean was) {
        wasInKathairisBefore=was;
    }

}
