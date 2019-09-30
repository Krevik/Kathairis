package io.github.krevik.kathairis.world.dimension.structures.crystal_ruins;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class StructureCrystalRuins extends ScatteredStructure<CrystalRuinsConfig> {
    public StructureCrystalRuins(Function<Dynamic<?>, ? extends CrystalRuinsConfig> p_i51419_1_) {
        super(p_i51419_1_);
    }

    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
        int i = chunkGenerator.getSettings().getVillageDistance();
        int j = chunkGenerator.getSettings().getVillageSeparation();
        int k = x + i * spacingOffsetsX;
        int l = z + i * spacingOffsetsZ;
        int i1 = k < 0 ? k - i + 1 : k;
        int j1 = l < 0 ? l - i + 1 : l;
        int k1 = i1 / i;
        int l1 = j1 / i;
        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), k1, l1, 10387312);
        k1 = k1 * i;
        l1 = l1 * i;
        k1 = k1 + random.nextInt(i - j);
        l1 = l1 + random.nextInt(i - j);
        return new ChunkPos(k1, l1);
    }



    public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
        ChunkPos chunkpos = this.getStartPositionForPosition(chunkGen, rand, chunkPosX, chunkPosZ, 0, 0);
        if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z) {
            Biome biome = chunkGen.getBiomeProvider().getBiome(new BlockPos((chunkPosX << 4) + 9, 0, (chunkPosZ << 4) + 9));
            return chunkGen.hasStructure(biome, KatharianFeatureList.CRYSTAL_RUINS);
        } else {
            return false;
        }
    }

    @Override
    protected int getSeedModifier() {
        return 0;
    }

    public Structure.IStartFactory getStartFactory() {
        return Start::new;
    }

    public String getStructureName() {
        return "kathairis:crystal_ruins";
    }

    public int getSize() {
        return 8;
    }

    public static class Start extends MarginedStructureStart {
        public Start(Structure<?> p_i51110_1_, int p_i51110_2_, int p_i51110_3_, Biome p_i51110_4_, MutableBoundingBox p_i51110_5_, int p_i51110_6_, long p_i51110_7_) {
            super(p_i51110_1_, p_i51110_2_, p_i51110_3_, p_i51110_4_, p_i51110_5_, p_i51110_6_, p_i51110_7_);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            CrystalRuinsConfig config = (CrystalRuinsConfig)generator.getStructureConfig(biomeIn, KatharianFeatureList.CRYSTAL_RUINS);
            BlockPos blockpos = new BlockPos(chunkX * 16, 64, chunkZ * 16);
            CrystalRuinsPieces.initialisePieces(generator, templateManagerIn, blockpos, this.components, this.rand, config);
            this.recalculateStructureSize();
        }
    }
}