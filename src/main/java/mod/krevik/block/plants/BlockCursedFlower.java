package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCursedFlower extends BlockMysticBush{
    public BlockCursedFlower(String Name) {
        super(Name, false);
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        boolean can=false;
        if(state.getBlock().isFullBlock(state)){
            can=true;
        }
        return can;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
            if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            {
                IBlockState soil = worldIn.getBlockState(pos.down());
                return this.canSustainPlantRemake(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
            }
            return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }

    @Override
    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        boolean can=false;
        if(state.getBlock().isFullCube(state)){
            can=true;
        }
        return can;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(1000) == 0)
        {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, KCore.proxy.scary_flower, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
            return BUSH_AABB;
    }

}
