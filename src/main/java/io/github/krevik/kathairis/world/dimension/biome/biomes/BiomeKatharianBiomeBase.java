package io.github.krevik.kathairis.world.dimension.biome.biomes;

import com.google.common.collect.Lists;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.FunctionHelper;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.KatharianMinableConfig;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.PlainsBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.structure.VillageStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.GameData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BiomeKatharianBiomeBase extends Biome {
    public Color baseGrassColor = new Color(66,244,238);
    protected BiomeKatharianBiomeBase(Builder p_i48975_1_) {
        super(p_i48975_1_);
        //this.addStructure(KatharianFeatureList.CRYSTAL_RUINS,new CrystalRuinsConfig("kathairis:crystal_ruins/entrances",6));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(KatharianFeatureList.ORE, new KatharianMinableConfig(KatharianMinableConfig.FillerBlockType.NATURAL_STONE, ModBlocks.TITANIUM_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(KatharianFeatureList.ORE, new KatharianMinableConfig(KatharianMinableConfig.FillerBlockType.NATURAL_STONE, ModBlocks.REVENUM_ORE.getDefaultState(), 18), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, createDecoratedFeature(KatharianFeatureList.KATHARIAN_CRYSTAL_CHAMBER, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, new ChanceConfig(128)));
    }


    private static final int MONSTER_SPAWN_WEIGHT = 5;


    @Override
    public List<SpawnListEntry> getSpawns(EntityClassification p_76747_1_) {
        if (p_76747_1_ == EntityClassification.MONSTER) {
            FunctionHelper helper = new FunctionHelper();
            return helper.getRandom().nextInt(MONSTER_SPAWN_WEIGHT) == 0 ? this.getSpawns(p_76747_1_) : Lists.newArrayList();
        }
        return super.getSpawns(p_76747_1_);
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

    public void addSpawn(EntityClassification type, SpawnListEntry spawnListEntry) {
        getSpawns(type).add(spawnListEntry);
    }
}
