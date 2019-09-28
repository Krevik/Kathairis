package io.github.krevik.kathairis.world.dimension.structures.crystal_ruins;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.util.ModReference;
import io.github.krevik.kathairis.util.ModUtil;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.*;
import net.minecraft.world.gen.feature.structure.PlainsVillagePools;
import net.minecraft.world.gen.feature.template.*;

public class CrystalRuinsPools {
    public static void init() {
    }

    static {
        JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/corridors"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/corridor_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/crossings"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/crossing_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/crossings"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/decoration_01"), 1), new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/decoration_02"), 1), new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/decoration_03"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/entrances"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/entrance_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/rooms"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/room_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
        JigsawManager.field_214891_a.register(new JigsawPattern(new ResourceLocation(ModReference.MOD_ID,"crystal_ruins/stairs"), new ResourceLocation("empty"), ImmutableList.of(new Pair<>(new SingleJigsawPiece(ModReference.MOD_ID+":"+"crystal_ruins/corridors/stairs_01"), 1)), JigsawPattern.PlacementBehaviour.RIGID));
    }
}