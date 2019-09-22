package io.github.krevik.kathairis.client;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ModBlocksColorHandler implements IBlockColor
{
    public static final IBlockColor INSTANCE = new ModBlocksColorHandler();
    public static Random rand = new Random();

    public static void registerBlockColors()
    {
        Minecraft.getInstance().getBlockColors().register(INSTANCE, ModBlocks.KATHAIRIS_GRASS);
    }

    @Override
    public int getColor(BlockState blockState, @Nullable IEnviromentBlockReader iEnviromentBlockReader, @Nullable BlockPos blockPos, int i) {
        return iEnviromentBlockReader.getBiome(blockPos).getGrassColor(blockPos);
    }
}
