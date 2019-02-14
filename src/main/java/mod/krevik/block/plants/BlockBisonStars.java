package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockBisonStars extends BlockMysticBush {
    protected static final AxisAlignedBB BISON_STARS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 0.125D, 1D);

    public BlockBisonStars(String Name)
    {
        super(Name,false);
        this.setTickRandomly(true);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND, KCore.KatharianGrass,KCore.KatharianDirt);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BISON_STARS_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return null;
    }

}
