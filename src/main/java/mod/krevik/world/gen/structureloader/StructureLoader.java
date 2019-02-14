package mod.krevik.world.gen.structureloader;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class StructureLoader extends WorldGenerator {

    public StructureLoader(){

    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return false;
    }

    public boolean generate(World world, Random rand, BlockPos pos, Structure structure) {
        BlockPos correctedPos = new BlockPos(pos.getX()+structure.getCenterPosCorrection().getX(),pos.getY()+structure.getCenterPosCorrection().getY(),pos.getZ()+structure.getCenterPosCorrection().getZ());
        world.getChunkProvider().provideChunk(correctedPos.getX(), correctedPos.getZ());

        if (KCore.functionHelper.isAvailableBlockToGenOn(world, correctedPos.down())) {
            Template template = getStructureTemplate(world,structure.getStructureLocation());
            if(template!=null){
                boolean canGenerate=true;
                BlockPos size = template.getSize();
                int xSize = size.getX();
                int ySize = size.getY();
                int zSize = size.getZ();
                if(canGenerate){
                    IBlockState iblockstate = world.getBlockState(correctedPos);
                    world.notifyBlockUpdate(correctedPos, iblockstate, iblockstate, 3);
                    PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                            .setRotation(Rotation.NONE).setIgnoreEntities(true)
                            .setIgnoreStructureBlock(true);
                    template.addBlocksToWorld(world, correctedPos, placementsettings);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private Template getStructureTemplate(World world, ResourceLocation location) {
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = ((WorldServer) world).getStructureTemplateManager();
        //Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(KCore.MODID+":stonetemple"));
        Template template = templatemanager.getTemplate(minecraftserver, location);
        return template;
    }

}
