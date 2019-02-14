package mod.krevik.block.plants;

import mod.krevik.KCore;
import mod.krevik.block.BlockMysticCloud;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCloudPlant extends BlockMysticBush {
    public BlockCloudPlant(String Name) {
        super(Name, false);
        addBlocksThatPlantCanStayOn(KCore.YellowCloud,KCore.BlueCloud);
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
            state = worldIn.getBlockState(pos.down());
            if(state.getBlock() instanceof BlockMysticCloud) {
                return this.canSustainPlantRemake(state, worldIn, pos.down(), EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
            }else {
                return false;
            }
    }
}
