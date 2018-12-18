package com.krevik.Main;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class KathairisMaterials extends Material
{	
    public static final Material KATHAIRISPORTAL = (new KathairisMaterials(MapColor.AIR)).setImmovableMobility();
    
    private EnumPushReaction mobilityFlag = EnumPushReaction.NORMAL;

    public KathairisMaterials(MapColor color)
    {
    	super(color);
    }
    

    protected Material setImmovableMobility()
    {
        this.mobilityFlag = EnumPushReaction.BLOCK;
        return this;
    }
    
    public EnumPushReaction getMobilityFlag()
    {
        return this.mobilityFlag;
    }

}