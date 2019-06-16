package io.github.krevik.kathairis.world.dimension.biome.biomes;

import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KatharianMinableConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.ArrayList;

public class BiomeKatharianBiomeBase extends Biome {
    public Color baseGrassColor =new Color(66,244,238);

    protected BiomeKatharianBiomeBase(BiomeBuilder p_i48975_1_) {
        super(p_i48975_1_);
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, createCompositeFeature(Feature.MINABLE, new MinableConfig(KatharianMinableConfig.IS_ROCK, Blocks.STONE.getDefaultState(), 17), COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, createCompositeFeature(KatharianFeatureList.KATHARIAN_CRYSTAL_CHAMBER, IFeatureConfig.NO_FEATURE_CONFIG, WITH_CHANCE, new ChanceConfig(128)));
    }




    @OnlyIn(Dist.CLIENT)
    @Override
    public int getGrassColor(BlockPos pos)
    {
        World world = Minecraft.getInstance().world;
        ArrayList<BlockPos> toCalculate = new ArrayList<BlockPos>();
        int radiusCalculation = 6;
        for (int x = -radiusCalculation; x <= radiusCalculation; x++) {
            for (int z = -radiusCalculation; z <= radiusCalculation; z++) {
                toCalculate.add(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z));
            }
        }

        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        int count = 0;
        for (BlockPos positionForAverage : toCalculate) {
            if(world.getBiome(positionForAverage) instanceof BiomeKatharianBiomeBase) {
                BiomeKatharianBiomeBase biome = (BiomeKatharianBiomeBase) world.getBiome(positionForAverage);
                count++;
                sumR += biome.baseGrassColor.getRed();
                sumG += biome.baseGrassColor.getGreen();
                sumB += biome.baseGrassColor.getBlue();
            }else{
                return super.getGrassColor(positionForAverage);
            }
        }
        Color averageColor = new Color(sumR / count, sumG / count, sumB / count);


        return Integer.parseInt(convertColorToHexadeimal(averageColor), 16);
    }

    public static String convertColorToHexadeimal(Color color)
    {
        String hex = Integer.toHexString(color.getRGB() & 0xffffff);
        if(hex.length() < 6)
        {
            if(hex.length()==5)
                hex = "0" + hex;
            if(hex.length()==4)
                hex = "00" + hex;
            if(hex.length()==3)
                hex = "000" + hex;
        }
        hex = hex;
        return hex;
    }

    public void addSpawn(EnumCreatureType type, Biome.SpawnListEntry spawnListEntry) {
        getSpawns(type).add(spawnListEntry);
    }
}
