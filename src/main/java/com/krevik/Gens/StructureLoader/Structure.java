package com.krevik.Gens.StructureLoader;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class Structure {

    private String structureName;
    private ResourceLocation structureLocation;
    private BlockPos centerPosCorrection;
    public Structure(String Name, ResourceLocation location,BlockPos correction){
        structureName=Name;
        structureLocation=location;
        centerPosCorrection=correction;
    }

    public String getStructureName() {
        return structureName;
    }

    public ResourceLocation getStructureLocation(){
        return structureLocation;
    }

    public BlockPos getCenterPosCorrection(){
        return centerPosCorrection;
    }
}
