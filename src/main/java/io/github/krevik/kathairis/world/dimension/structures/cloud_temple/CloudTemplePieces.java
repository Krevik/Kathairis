package io.github.krevik.kathairis.world.dimension.structures.cloud_temple;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.structures.utils.SingleJigsawPieceCloudTemple;
import io.github.krevik.kathairis.world.dimension.feature.structures.utils.SingleJigsawPieceNoAirIgnorance;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.VillagePieces;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

public class CloudTemplePieces {

    static{
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("cloud_temple/cloud_temple"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPieceCloudTemple("cloud_temple/cloud_temple"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }

    public static void initialisePieces(ChunkGenerator<?> p_214838_0_, TemplateManager p_214838_1_, BlockPos p_214838_2_, List<StructurePiece> p_214838_3_, SharedSeedRandom p_214838_4_) {
        JigsawManager.func_214889_a(new ResourceLocation("cloud_temple/cloud_temple"), 1, io.github.krevik.kathairis.world.dimension.structures.cloud_temple.CloudTemplePieces.CloudTemple::new, p_214838_0_, p_214838_1_, p_214838_2_, p_214838_3_, p_214838_4_);
    }

    public static class CloudTemple extends AbstractVillagePiece {
        public CloudTemple(TemplateManager p_i50890_1_, JigsawPiece p_i50890_2_, BlockPos p_i50890_3_, int p_i50890_4_, Rotation p_i50890_5_, MutableBoundingBox p_i50890_6_) {
            super(KatharianFeatureList.CLOUD_TEMPLE_PIECES_TYPE, p_i50890_1_, p_i50890_2_, p_i50890_3_, p_i50890_4_, p_i50890_5_, p_i50890_6_);
        }

        public CloudTemple(TemplateManager p_i50891_1_, CompoundNBT p_i50891_2_) {
            super(p_i50891_1_, p_i50891_2_, KatharianFeatureList.CLOUD_TEMPLE_PIECES_TYPE);
        }
    }
}