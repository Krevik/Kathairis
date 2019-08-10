package io.github.krevik.kathairis.world.dimension.structures.crystal_labirynth;

import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.init.Biomes;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.structure.*;

import java.util.Random;

public class StructureCrystalLabirynth extends Structure<CrystalLabirynthConfig> {
    public StructureCrystalLabirynth(){

    }

    protected boolean hasStartAt(IChunkGenerator<?> iChunkGenerator, Random random, int posX, int posZ) {
        ((SharedSeedRandom)random).setLargeFeatureSeed(iChunkGenerator.getSeed(), posX, posZ);
        Biome biome = iChunkGenerator.getBiomeProvider().getBiome(new BlockPos((posX << 4) + 9, 0, (posZ << 4) + 9), Biomes.DEFAULT);
        if (iChunkGenerator.hasStructure(biome, KatharianFeatureList.CRYSTAL_LABIRYNTH)) {
            CrystalLabirynthConfig config = (CrystalLabirynthConfig) iChunkGenerator.getStructureConfig(biome, KatharianFeatureList.CRYSTAL_LABIRYNTH);
            double chances = config.probability;
            return random.nextDouble() < chances;
        } else {
            return false;
        }
    }

    protected boolean isEnabledIn(IWorld world) {
        return true;
    }

    protected StructureStart makeStart(IWorld world, IChunkGenerator<?> iChunkGenerator, SharedSeedRandom seedRandom, int posX, int posZ) {
        Biome biome = iChunkGenerator.getBiomeProvider().getBiome(new BlockPos((posX << 4) + 9, 0, (posZ << 4) + 9), Biomes.DEFAULT);
        return new StructureCrystalLabirynth.Start(world, iChunkGenerator, seedRandom, posX, posZ, biome);
    }

    protected String getStructureName() {
        return "Crystal_Labirynth";
    }

    public int getSize() {
        return 8;
    }

    public static class Start extends StructureStart {

        public Start() {
        }

        public Start(IWorld world, IChunkGenerator<?> iChunkGenerator, SharedSeedRandom seedRandom, int posX, int posZ, Biome biome) {
            super(posX, posZ, biome, seedRandom, world.getSeed());
            CrystalLabirynthConfig config = (CrystalLabirynthConfig)iChunkGenerator.getStructureConfig(biome, KatharianFeatureList.CRYSTAL_LABIRYNTH);
            StructureCrystalLabirynthPieces.Room structurePieces = new StructureCrystalLabirynthPieces.Room(0, seedRandom, (posX << 4) + 2, (posZ << 4) + 2);
            this.components.add(structurePieces);
            structurePieces.buildComponent(structurePieces, this.components, seedRandom);
            this.recalculateStructureSize(world);
            this.markAvailableHeight(world, seedRandom, 10);
        }
    }
}
