package com.Krevik.Gens.StructureLoader;

import com.Krevik.Main.KCore;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.ArrayList;
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

        if (KCore.instance.functionHelper.isAvailableBlockToGenOn(world, correctedPos.down())) {
            Template template = getStructureTemplate(world,structure.getStructureLocation());
            if(template!=null){
                boolean canGenerate=true;
                BlockPos size = template.getSize();
                int xSize = size.getX();
                int ySize = size.getY();
                int zSize = size.getZ();
                ArrayList<BlockPos> corners = new ArrayList<BlockPos>();
                corners.add(new BlockPos(correctedPos.getX()+xSize,correctedPos.getY(),correctedPos.getZ()));
                corners.add(new BlockPos(correctedPos.getX(),correctedPos.getY(),correctedPos.getZ()+zSize));
                corners.add(new BlockPos(correctedPos.getX()+xSize,correctedPos.getY(),correctedPos.getZ()+zSize));
                corners.add(new BlockPos(correctedPos.getX()+xSize,correctedPos.getY()+ySize,correctedPos.getZ()+zSize));
                corners.add(new BlockPos(correctedPos.getX(),correctedPos.getY()+ySize,correctedPos.getZ()+zSize));
                corners.add(new BlockPos(correctedPos.getX()+xSize,correctedPos.getY()+ySize,correctedPos.getZ()));
                corners.add(new BlockPos(correctedPos.getX(),correctedPos.getY()+ySize,correctedPos.getZ()));
                for(BlockPos corner:corners){
                    if(!world.isAirBlock(corner)){
                        canGenerate=false;
                        break;
                    }
                }
                if(canGenerate){
                    PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                            .setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos) null)
                            .setReplacedBlock((Block) null).setIgnoreStructureBlock(true);
                    template.addBlocksToWorld(world, new BlockPos(correctedPos.getX()+structure.getCenterPosCorrection().getX(),correctedPos.getY()+structure.getCenterPosCorrection().getY(),correctedPos.getZ()+structure.getCenterPosCorrection().getZ()), placementsettings);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private Template getStructureTemplate(World world, ResourceLocation location) {
        WorldServer worldserver = (WorldServer) world;
        MinecraftServer minecraftserver = world.getMinecraftServer();
        TemplateManager templatemanager = worldserver.getStructureTemplateManager();
        //Template template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(KCore.MODID+":stonetemple"));
        Template template = templatemanager.getTemplate(minecraftserver, location);
        return template;
    }

}
