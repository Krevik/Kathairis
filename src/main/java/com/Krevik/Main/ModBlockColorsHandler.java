package com.Krevik.Main;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModBlockColorsHandler implements IBlockColor
{
    public static final IBlockColor INSTANCE = new ModBlockColorsHandler();
    public static Random rand = new Random();

    /* (non-Javadoc)
     * @see net.minecraft.client.renderer.color.IBlockColor#colorMultiplier(net.minecraft.block.state.IBlockState, net.minecraft.world.IBlockAccess, net.minecraft.util.math.BlockPos, int)
     */
    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex)
    {
        return worldIn.getBiome(pos).getGrassColorAtPos(pos);
    }

    /**
     * Register block colors.
     */
    public static void registerBlockColors()
    {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(INSTANCE, KCore.CorruptedGrass);
    }
}