package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMagicBeans extends BlockMysticCrops
{
    public BlockMagicBeans(String Name) {
		super(Name);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt);
    }

	private static final AxisAlignedBB[] CARROT_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.2D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.2D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.8D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1D, 1.0D)};

    @Override
    protected Item getSeed()
    {
        return KCore.MagicBeansItem;
    }

    @Override
    protected Item getCrop()
    {
        return KCore.MagicBeansItem;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CARROT_AABB[state.getValue(this.getAgeProperty()).intValue()];
    }
}