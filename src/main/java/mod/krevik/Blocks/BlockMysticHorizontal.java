package mod.krevik.Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;

public abstract class BlockMysticHorizontal extends BaseBlock
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    protected BlockMysticHorizontal(String Name,Material mat,CreativeTabs tab,float hardness,float resistance,SoundType type)
    {
        super(Name, mat, tab, hardness, resistance, type);
    }
}