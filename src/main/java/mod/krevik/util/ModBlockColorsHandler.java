package mod.krevik.util;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ModBlockColorsHandler implements IBlockColor
{
    public static final IBlockColor INSTANCE = new ModBlockColorsHandler();
    public static Random rand = new Random();

    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex)
    {
        return worldIn.getBiome(pos).getGrassColorAtPos(pos);
    }

    public static void registerBlockColors()
    {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(INSTANCE, KCore.KatharianGrass);
    }
}