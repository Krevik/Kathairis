package com.krevik.Gens.StructureLoader;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class CreateWorldGenFromStructure extends WorldGenerator {

    private Structure structure;
    private StructureLoader structureLoader;
    public CreateWorldGenFromStructure(Structure structure1,StructureLoader loader){
        super(false);
        structure=structure1;
        structureLoader=loader;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if(structureLoader!=null){
            if(structure!=null){
                structureLoader.generate(worldIn,rand,position,structure);
                return true;
            }
        }
        return false;
    }

}
