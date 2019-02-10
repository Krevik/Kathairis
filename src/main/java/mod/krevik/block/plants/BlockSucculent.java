package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSucculent extends BlockMysticBush {

    protected static final AxisAlignedBB SUCCULENT_AABB = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1D, 0.8125D);

    public BlockSucculent(String Name) {
        super(Name, false);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt,KCore.ForgottenSand,
                KCore.MovingSand,KCore.WeatheredRock,Blocks.SAND,KCore.Succulent);
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        boolean can=false;
        if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock()==KCore.KatharianDirt
                || state.getBlock()==KCore.KatharianGrass || state.getBlock() == KCore.MovingSand || state.getBlock() == KCore.WeatheredRock ||
                state.getBlock()==Blocks.SAND||state.getBlock()==KCore.Succulent ||state.getBlock()==KCore.ForgottenSand){
            can=true;
        }
        return can;
    }

    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        boolean can=false;
        if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock()==KCore.KatharianDirt
                || state.getBlock()==KCore.KatharianGrass || state.getBlock() == KCore.MovingSand || state.getBlock() == KCore.WeatheredRock ||
                state.getBlock()==Blocks.SAND||state.getBlock()==KCore.Succulent ||state.getBlock()==KCore.ForgottenSand){
            can=true;
        }
        return can;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
        if(rand.nextInt(40)==0) {
                if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
                BlockPos blockpos = pos.up();
                if (worldIn.isAirBlock(blockpos))
                {
                    int i;

                    for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
                    {
                        ;
                    }

                    if (i < 3)
                    {
                        worldIn.setBlockState(blockpos, this.getDefaultState());
                        IBlockState iblockstate = state;
                        worldIn.setBlockState(pos, iblockstate, 4);
                        iblockstate.neighborChanged(worldIn, blockpos, this, pos);

                    }
                }
            }
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                Material material = worldIn.getBlockState(pos.offset(enumfacing)).getMaterial();

                if (material.isSolid() || material == Material.LAVA)
                {
                    return false;
                }
            }

            state = worldIn.getBlockState(pos.down());
            return this.canSustainPlantRemake(state, worldIn, pos.down(), EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) { }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
            return SUCCULENT_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
            return SUCCULENT_AABB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


}
