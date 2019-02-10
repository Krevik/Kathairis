package mod.krevik.block.plants;

import mod.krevik.KCore;
import mod.krevik.block.BlockMysticCloud;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockSteppedSucculent extends BlockMysticBush {

    protected static final AxisAlignedBB SUCCULENT_AABB = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1D, 0.8125D);

    public BlockSteppedSucculent(String Name) {
        super(Name, false);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt,KCore.SteppedSucculent);
    }

    /**
     * Return true if the block can sustain a Bush
     */
    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        boolean can=false;
        if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock()==KCore.KatharianDirt
                || state.getBlock()==KCore.KatharianGrass || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand
        ||state.getBlock() == KCore.SteppedSucculent){
            can=true;
        }
        return can;
    }




    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
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
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
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

    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        boolean can=false;
        if(state==KCore.KatharianDirt.getDefaultState() || state==KCore.KatharianGrass.getDefaultState() || state==Blocks.GRASS.getDefaultState() || state==Blocks.DIRT.getDefaultState()||
                state==Blocks.FARMLAND.getDefaultState()  || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand||
        state.getBlock() == KCore.SteppedSucculent){
            can=true;
        }
        return can;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) { }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
         return FULL_BLOCK_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
            return FULL_BLOCK_AABB;
    }

}