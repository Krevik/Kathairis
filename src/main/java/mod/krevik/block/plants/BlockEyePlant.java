package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockEyePlant extends BlockMysticBush {
    protected static final AxisAlignedBB EYEPLANT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 1D, 1D);

    public BlockEyePlant(String Name) {
        super(Name, false);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
            return EYEPLANT_AABB;
    }

}
