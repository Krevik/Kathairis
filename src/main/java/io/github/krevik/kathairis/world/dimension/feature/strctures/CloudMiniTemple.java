package io.github.krevik.kathairis.world.dimension.feature.strctures;
/*
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.RewardHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

import java.util.ArrayList;
import java.util.Random;

public class CloudMiniTemple extends Feature<NoFeatureConfig>{

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int posX=pos.getX();
        int posY=150+rand.nextInt(25)+rand.nextInt(25)+rand.nextInt(25);
        int posZ=pos.getZ();

        Template structureTemplate = worldIn.getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation(ModReference.MOD_ID,"cloud_mini_temple"));
        structureTemplate.addBlocksToWorld(worldIn,new BlockPos(posX,posY,posZ),new PlacementSettings().setReplacedBlock(Blocks.AIR).setRotation(Rotation.NONE).setMirror(Mirror.NONE));
        structureTemplate.getSize();
        ArrayList<ChestTileEntity> chests = new ArrayList<>();
        BlockPos size = structureTemplate.getSize();
        for(int x=0;x<=size.getX();x++){
            for(int y=0;y<=size.getY();y++){
                for(int z=0;z<=size.getZ();z++){
                    BlockPos tmp = new BlockPos(posX+x,posY+y,posZ+z);
                    if(worldIn.getTileEntity(tmp)!=null){
                        if(worldIn.getTileEntity(tmp) instanceof ChestTileEntity){
                            chests.add((ChestTileEntity) worldIn.getTileEntity(tmp));
                        }
                    }
                }
            }
        }
        //fill chests
        for(TileEntityChest chest:chests){
            ArrayList<ItemStack> rewards = new ArrayList<>();
            rewards = RewardHelper.getCloudMiniTempleRewards(rand);
            for(int i = 0;i<=rewards.size();i++) {
                chest.setInventorySlotContents(rand.nextInt(15), rewards.get(rand.nextInt(rewards.size())));
            }
        }
        return true;
    }
}
*/