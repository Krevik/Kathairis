package io.github.krevik.kathairis.world.dimension;


import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.init.ModBiomes;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModDimensions;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProvider;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProviderSettings;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;
import java.util.ArrayList;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

public class DimensionKathairis extends OverworldDimension {

    public DimensionKathairis() {
        super(DimensionType.getById(Kathairis.KATH_DIM_TYPE.getId()));
    }

    public DimensionKathairis(DimensionType dimensionType) {
        super(dimensionType);
    }

    @Override
    public IChunkGenerator<? extends IChunkGenSettings> createChunkGenerator() {
        WorldType worldtype = this.world.getWorldInfo().getGenerator();
        BiomeProviderType<KatharianBiomeProviderSettings, KatharianBiomeProvider> biomeprovidertype1 = new BiomeProviderType<>(KatharianBiomeProvider::new,KatharianBiomeProviderSettings::new,new ResourceLocation("kathairis","katharian_biome_provider_type"));
        KatharianBiomeProviderSettings overworldbiomeprovidersettings1 = biomeprovidertype1.createSettings().setGeneratorSettings(new KathairisGenSettings()).setWorldInfo(this.world.getWorldInfo());
        BiomeProvider biomeprovider = biomeprovidertype1.create(overworldbiomeprovidersettings1);

        ChunkGeneratorType<OverworldGenSettings, ChunkGeneratorOverworld> chunkgeneratortype4 = ChunkGeneratorType.SURFACE;
        OverworldGenSettings overworldgensettings1 = chunkgeneratortype4.createSettings();
        overworldgensettings1.setDefautBlock(ModBlocks.KATHAIRIS_STONE.getDefaultState());
        overworldgensettings1.setDefaultFluid(Blocks.WATER.getDefaultState());
        return chunkgeneratortype4.create(this.world, biomeprovider, overworldgensettings1);
    }


    @Override
    public float getCloudHeight() {
        return -1000;
    }

    @Nullable
    @Override
    public IRenderHandler getCloudRenderer() {
        return null;
    }

    Vec3d swampColor = new Vec3d(14/10,51/10,12/10);
    @Override
    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
        EntityPlayer player=Minecraft.getInstance().player;
        int radius=6;
        ArrayList<BlockPos> posesToCalculate = new ArrayList<>();
        for(int x=-radius;x<=radius;x++){
            for(int z=-radius;z<=radius;z++){
                BlockPos pos = new BlockPos(player.getPosition().getX()+x,player.getPosition().getY(),player.getPosition().getZ()+z);
                posesToCalculate.add(pos);
            }
        }
        boolean isSwampNear=false;
        for(BlockPos pos:posesToCalculate){
            if(player.world.getBiome(pos)==ModBiomes.KATHARIAN_SWAMP){
                isSwampNear=true;
            }
        }
        if(isSwampNear){
            Vec3d normalColor = super.getSkyColor(cameraEntity,partialTicks);
            return getAverage(getWorld(),posesToCalculate,normalColor,swampColor);

        }
        int R=200;
        int G=190;
        int B=40;
        //return new Vec3d(R/100,G/100,B/100);
        return super.getSkyColor(cameraEntity,partialTicks);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Vec3d getFogColor(float float1, float float2) {
        EntityPlayer player=Minecraft.getInstance().player;
        int radius=6;
        ArrayList<BlockPos> posesToCalculate = new ArrayList<>();
        for(int x=-radius;x<=radius;x++){
            for(int z=-radius;z<=radius;z++){
                BlockPos pos = new BlockPos(player.getPosition().getX()+x,player.getPosition().getY(),player.getPosition().getZ()+z);
                posesToCalculate.add(pos);
            }
        }
        boolean isSwampNear=false;
        for(BlockPos pos:posesToCalculate){
            if(player.world.getBiome(pos)==ModBiomes.KATHARIAN_SWAMP){
                isSwampNear=true;
            }
        }
        if(isSwampNear){
            float swampR=12;
            float swampG=45;
            float swampB=7;
            return getAverage(player.world,posesToCalculate,super.getFogColor(float1,float2),new Vec3d(swampR/45,swampG/45,swampB/45));
        }else{
            return super.getFogColor(float1, float2);
        }
    }

    private static Vec3d getAverage(IWorld world, ArrayList<BlockPos> posesToCalculate, Vec3d normalValue, Vec3d swampValue){
        Vec3d result;
        float sumR=0;
        float sumG=0;
        float sumB=0;
        for(BlockPos pos:posesToCalculate){
            if(world.getBiome(pos)==ModBiomes.KATHARIAN_SWAMP){
                sumR+=swampValue.x;
                sumG+=swampValue.y;
                sumB+=swampValue.z;
            }else{
                sumR+=normalValue.x;
                sumG+=normalValue.y;
                sumB+=normalValue.z;
            }
        }
        result=new Vec3d(sumR/posesToCalculate.size(),sumG/posesToCalculate.size(),sumB/posesToCalculate.size());
        return result;
    }


    @Nullable
    @Override
    public MusicTicker.MusicType getMusicType() {
        return null;
    }

    @Nullable
    @Override
    public IRenderHandler getSkyRenderer() {
        return super.getSkyRenderer();
    }
}
