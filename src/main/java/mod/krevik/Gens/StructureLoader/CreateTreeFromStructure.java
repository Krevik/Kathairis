package mod.krevik.Gens.StructureLoader;

import com.krevik.Gens.WorldGenAbstractBasicMysticTree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CreateTreeFromStructure extends WorldGenAbstractBasicMysticTree {

    private Structure structure;
    private StructureLoader structureLoader;
    public CreateTreeFromStructure(Structure structure1,StructureLoader loader){
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
