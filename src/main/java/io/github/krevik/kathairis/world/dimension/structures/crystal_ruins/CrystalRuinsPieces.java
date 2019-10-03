package io.github.krevik.kathairis.world.dimension.structures.crystal_ruins;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.world.dimension.feature.KatharianFeatureList;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class CrystalRuinsPieces {

    static{
        //JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/corridors"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/corridor_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        //JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/rooms"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/room_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        //JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/decorations"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/decoration_01"), 1), Pair.of(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/decoration_02"), 1), Pair.of(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/decoration_03"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("crystal_ruins/stairs"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece("crystal_ruins/stairs/stairs_01"), 6)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("crystal_ruins/entrances"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece("crystal_ruins/entrances/entrance_01"), 6)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.REGISTRY.register(new JigsawPattern(new ResourceLocation("crystal_ruins/crossings"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(new SingleJigsawPiece("crystal_ruins/crossings/crossing_01"), 6)), JigsawPattern.PlacementBehaviour.RIGID));

    }

    public static void initialisePieces(ChunkGenerator<?> p_214838_0_, TemplateManager p_214838_1_, BlockPos p_214838_2_, List<StructurePiece> p_214838_3_, SharedSeedRandom p_214838_4_, CrystalRuinsConfig p_214838_5_) {

        JigsawManager.func_214889_a(new ResourceLocation("crystal_ruins/entrances"), 32, CrystalRuinsPieces.CrystalRuins::new, p_214838_0_, p_214838_1_, p_214838_2_, p_214838_3_, p_214838_4_);
    }

    public static class CrystalRuins extends AbstractVillagePiece {
        public CrystalRuins(TemplateManager p_i50890_1_, JigsawPiece p_i50890_2_, BlockPos p_i50890_3_, int p_i50890_4_, Rotation p_i50890_5_, MutableBoundingBox p_i50890_6_) {
            super(KatharianFeatureList.CRYSTAL_RUINS_PIECES_TYPE, p_i50890_1_, p_i50890_2_, p_i50890_3_, p_i50890_4_, p_i50890_5_, p_i50890_6_);
        }

        public CrystalRuins(TemplateManager p_i50891_1_, CompoundNBT p_i50891_2_) {
            super(p_i50891_1_, p_i50891_2_, KatharianFeatureList.CRYSTAL_RUINS_PIECES_TYPE);
        }
    }
}