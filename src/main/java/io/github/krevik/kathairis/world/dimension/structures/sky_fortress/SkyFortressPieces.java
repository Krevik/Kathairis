package io.github.krevik.kathairis.world.dimension.structures.sky_fortress;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import io.github.krevik.kathairis.world.dimension.feature.structures.utils.SingleJigsawPieceNoAirIgnorance;
import io.github.krevik.kathairis.world.dimension.structures.crystal_ruins.CrystalRuinsConfig;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.VillagePieces;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;

import java.util.List;

public class SkyFortressPieces {

    public static void initialisePieces(ChunkGenerator<?> p_214838_0_, TemplateManager p_214838_1_, BlockPos p_214838_2_, List<StructurePiece> p_214838_3_, SharedSeedRandom p_214838_4_, SkyFortressConfig p_214838_5_) {
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("sky_fortress_decorations"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece("sky_fortress/sky_fortress_decoration_01"), 6)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("sky_fortress_connections"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece("sky_fortress/sky_fortress_bridge_straight_1"), 6), Pair.of(new SingleJigsawPiece("sky_fortress/sky_fortress_bridge_crossing_1"), 1), Pair.of(new SingleJigsawPiece("sky_fortress/sky_fortress_bridge_wooden_01"), 1),Pair.of(new SingleJigsawPiece("sky_fortress/sky_fortress_fountain_01"), 6)), JigsawPattern.PlacementBehaviour.RIGID));

        JigsawManager.func_214889_a(p_214838_5_.startPool, p_214838_5_.size, SkyFortressPieces.SkyFortress::new, p_214838_0_, p_214838_1_, p_214838_2_, p_214838_3_, p_214838_4_);
    }

    public static class SkyFortress extends AbstractVillagePiece {
        public SkyFortress(TemplateManager p_i50890_1_, JigsawPiece p_i50890_2_, BlockPos p_i50890_3_, int p_i50890_4_, Rotation p_i50890_5_, MutableBoundingBox p_i50890_6_) {
            super(KatharianFeatureList.SKY_FORTRESS_PIECE_TYPE, p_i50890_1_, p_i50890_2_, p_i50890_3_, p_i50890_4_, p_i50890_5_, p_i50890_6_);
        }

        public SkyFortress(TemplateManager p_i50891_1_, CompoundNBT p_i50891_2_) {
            super(p_i50891_1_, p_i50891_2_, KatharianFeatureList.SKY_FORTRESS_PIECE_TYPE);
        }
    }
}