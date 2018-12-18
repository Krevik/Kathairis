package mod.krevik.Gens.StructureLoader;

import mod.krevik.Main.KCore;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class StructureList {
    public static Structure fauyn_Bush_1 = new Structure("fauyn_Bush_1",new ResourceLocation(KCore.MODID,"kath_forest/fauynbush_01"),new BlockPos(-1,0,-2));
    public static Structure fauyn_Tree_1 = new Structure("fauyn_Tree_1",new ResourceLocation(KCore.MODID,"kath_forest/fauyntree_01"),new BlockPos(-2,0,-2));
    public static Structure fauyn_Tree_2 = new Structure("fauyn_Tree_2",new ResourceLocation(KCore.MODID,"kath_forest/fauyntree_02"),new BlockPos(-2,0,-2));
    public static Structure nath_bush_1 = new Structure("nath_bush_1",new ResourceLocation(KCore.MODID,"kath_forest/nathbush_01"),new BlockPos(-1,0,-1));
    public static Structure nath_tree_1 = new Structure("nath_tree_1",new ResourceLocation(KCore.MODID,"kath_forest/nathtree_01"),new BlockPos(-2,0,-2));

    public static Structure nathbush_01_plains = new Structure("nathbush_01_plains",new ResourceLocation(KCore.MODID,"kath_plains/nathbush_01_plains"),new BlockPos(-1,0,-1));

    public static Structure lahnbush_01 = new Structure("lahnbush_01",new ResourceLocation(KCore.MODID,"floating_islands/lahnbush_01"),new BlockPos(-2,0,-2));
    public static Structure lahnbush_02 = new Structure("lahnbush_02",new ResourceLocation(KCore.MODID,"floating_islands/lahnbush_02"),new BlockPos(-1,0,-1));
    public static Structure lahntree_01 = new Structure("lahntree_01",new ResourceLocation(KCore.MODID,"floating_islands/lahntree_01"),new BlockPos(-1,0,-1));


}
