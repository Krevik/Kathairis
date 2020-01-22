package io.github.krevik.kathairis.world.dimension;


import io.github.krevik.kathairis.init.ModBiomes;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModDimensions;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProvider;
import io.github.krevik.kathairis.world.dimension.biome.KatharianBiomeProviderSettings;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IRenderHandler;
import org.omg.DynamicAny.DynEnumHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class DimensionKathairis extends OverworldDimension {


    public DimensionKathairis(World world) {
        super(world,DimensionType.byName(ModReference.KATHAIRIS));
    }

    public DimensionKathairis(World world, DimensionType dimensionType) {
        super(world, dimensionType);
    }

    @Override
    public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
        WorldType worldtype = this.world.getWorldInfo().getGenerator();
        //BiomeProviderType<KatharianBiomeProviderSettings, KatharianBiomeProvider> biomeprovidertype1 = ModDimensions.KATHAIRIS_BIOME_PROVIDER_TYPE;
        //BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> biomeprovidertype1 = BiomeProviderType.VANILLA_LAYERED;
        //BiomeProvider biomeprovider = biomeprovidertype1.create(new KatharianBiomeProviderSettings(world.getWorldInfo()));

        KatharianBiomeProvider biomeProviderKath = new KatharianBiomeProvider(new KatharianBiomeProviderSettings(world.getWorldInfo()));

        ChunkGeneratorType<OverworldGenSettings, ChunkGeneratorKathairis> chunkgeneratortype4 = ModDimensions.KATHAIRIS_CHUNK_GENERATOR_TYPE;
        OverworldGenSettings overworldgensettings1 = chunkgeneratortype4.createSettings();
        overworldgensettings1.setDefaultBlock(ModBlocks.KATHAIRIS_STONE.getDefaultState());
        overworldgensettings1.setDefaultFluid(Blocks.WATER.getDefaultState());
        return chunkgeneratortype4.create(this.world, biomeProviderKath, overworldgensettings1);
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

    /*@Override
    //TODO
    public Vec3d getSkyColor(BlockPos cameraPosition, float partialTicks) {
        int radius=6;
        ArrayList<BlockPos> posesToCalculate = new ArrayList<>();
        for(int x=-radius;x<=radius;x++){
            for(int z=-radius;z<=radius;z++){
                BlockPos pos = new BlockPos(cameraPosition.getX()+x,cameraPosition.getY(),cameraPosition.getZ()+z);
                posesToCalculate.add(pos);
            }
        }
        boolean isSwampNear=false;
        for(BlockPos pos:posesToCalculate){
            if(Minecraft.getInstance().player.world.func_226691_t_(pos)==ModBiomes.KATHARIAN_SWAMP){
                isSwampNear=true;
            }
        }
        if(isSwampNear){
            Vec3d normalColor = super.getSkyColor(cameraPosition,partialTicks);
            return getAverageColor(getWorld(),posesToCalculate,normalColor,swampColor);

        }
        return super.getSkyColor(cameraPosition,partialTicks);
    }*/

    @Override
    @OnlyIn(Dist.CLIENT)
    public Vec3d getFogColor(float float1, float float2) {
        PlayerEntity player = Minecraft.getInstance().player;
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
            return getAverageColor(player.world,posesToCalculate,super.getFogColor(float1,float2),new Vec3d(swampR/45,swampG/45,swampB/45));
        }else{
            return super.getFogColor(float1, float2);
        }
    }

    private static Vec3d getAverageColor(IWorld world, ArrayList<BlockPos> posesToCalculate, Vec3d normalValue, Vec3d swampValue){
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
        return super.getMusicType();
    }

    //TODO
    @Nullable
    @Override
    public IRenderHandler getSkyRenderer() {
        //if (super.getSkyRenderer() == null) {
            //this.setSkyRenderer(new RenderKathairisSky());
        //}
        return super.getSkyRenderer();
    }
}
