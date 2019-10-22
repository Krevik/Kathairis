package io.github.krevik.kathairis.world.dimension.structures.crystal_ruins;

import com.mojang.datafixers.Dynamic;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class StructureCrystalRuins extends Structure<CrystalRuinsConfig> {
    public StructureCrystalRuins(Function<Dynamic<?>, ? extends CrystalRuinsConfig> p_i51419_1_) {
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



    public boolean hasStartAt(ChunkGenerator<?> p_202372_1_, Random p_202372_2_, int p_202372_3_, int p_202372_4_) {
        ChunkPos lvt_5_1_ = this.getStartPositionForPosition(p_202372_1_, p_202372_2_, p_202372_3_, p_202372_4_, 0, 0);
        if (p_202372_3_ == lvt_5_1_.x && p_202372_4_ == lvt_5_1_.z) {
            Biome lvt_6_1_ = p_202372_1_.getBiomeProvider().getBiome(new BlockPos((p_202372_3_ << 4) + 9, 0, (p_202372_4_ << 4) + 9));
            return p_202372_1_.hasStructure(lvt_6_1_, (Structure<? extends IFeatureConfig>) KatharianFeatureList.CRYSTAL_RUINS);
        } else {
            return false;
        }
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
            CrystalRuinsConfig config = (CrystalRuinsConfig)generator.getStructureConfig(biomeIn, (Structure<? extends IFeatureConfig>)KatharianFeatureList.CRYSTAL_RUINS);
            BlockPos blockpos = new BlockPos(chunkX * 16, 64, chunkZ * 16);
            CrystalRuinsPieces.initialisePieces(generator, templateManagerIn, blockpos, this.components, this.rand, config);
            this.recalculateStructureSize();
        }
    }
}