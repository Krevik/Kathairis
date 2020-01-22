package io.github.krevik.kathairis.world.dimension.structures.sky_fortress;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsPieces;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageStructure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class StructureSkyFortress extends Structure<SkyFortressConfig> {
    public StructureSkyFortress(Function<Dynamic<?>, ? extends SkyFortressConfig> p_i51419_1_) {
        super(p_i51419_1_);
    }

    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> p_211744_1_, Random p_211744_2_, int p_211744_3_, int p_211744_4_, int p_211744_5_, int p_211744_6_) {
        int lvt_7_1_ = 32;
        int lvt_8_1_ = 8;
        int lvt_9_1_ = p_211744_3_ + lvt_7_1_ * p_211744_5_;
        int lvt_10_1_ = p_211744_4_ + lvt_7_1_ * p_211744_6_;
        int lvt_11_1_ = lvt_9_1_ < 0 ? lvt_9_1_ - lvt_7_1_ + 1 : lvt_9_1_;
        int lvt_12_1_ = lvt_10_1_ < 0 ? lvt_10_1_ - lvt_7_1_ + 1 : lvt_10_1_;
        int lvt_13_1_ = lvt_11_1_ / lvt_7_1_;
        int lvt_14_1_ = lvt_12_1_ / lvt_7_1_;
        ((SharedSeedRandom)p_211744_2_).setLargeFeatureSeedWithSalt(p_211744_1_.getSeed(), lvt_13_1_, lvt_14_1_, 10387312);
        lvt_13_1_ *= lvt_7_1_;
        lvt_14_1_ *= lvt_7_1_;
        lvt_13_1_ += p_211744_2_.nextInt(lvt_7_1_ - lvt_8_1_);
        lvt_14_1_ += p_211744_2_.nextInt(lvt_7_1_ - lvt_8_1_);
        return new ChunkPos(lvt_13_1_, lvt_14_1_);
    }

    @Override
    public boolean func_225558_a_(BiomeManager p_225558_1_, ChunkGenerator<?> p_225558_2_, Random p_225558_3_, int p_225558_4_, int p_225558_5_, Biome p_225558_6_) {
        ChunkPos chunkpos = this.getStartPositionForPosition(p_225558_2_, p_225558_3_, p_225558_4_, p_225558_5_, 0, 0);
        return p_225558_4_ == chunkpos.x && p_225558_5_ == chunkpos.z ? p_225558_2_.hasStructure(p_225558_6_, this) : false;
    }


    public Structure.IStartFactory getStartFactory() {
        return StructureSkyFortress.Start::new;
    }

    public String getStructureName() {
        return "kathairis:sky_fortress";
    }

    public int getSize() {
        return 8;
    }


    public static class Start extends MarginedStructureStart {
        public Start(Structure<?> p_i225821_1_, int p_i225821_2_, int p_i225821_3_, MutableBoundingBox p_i225821_4_, int p_i225821_5_, long p_i225821_6_) {
            super(p_i225821_1_, p_i225821_2_, p_i225821_3_, p_i225821_4_, p_i225821_5_, p_i225821_6_);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            SkyFortressConfig config = (SkyFortressConfig)generator.getStructureConfig(biomeIn, (Structure<? extends IFeatureConfig>) KatharianFeatureList.SKY_FORTRESS);
            BlockPos blockpos = new BlockPos(chunkX * 16, 156, chunkZ * 16);
            SkyFortressPieces.initialisePieces(generator, templateManagerIn, blockpos, this.components, this.rand, config);
            this.recalculateStructureSize();
        }
    }
}