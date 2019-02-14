package mod.krevik.world.gen;

import mod.krevik.KCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class KathairisTreeBase extends WorldGenAbstractBasicMysticTree {

    protected KathairisTreeBase(){
        super(false);
    }

    @Override
    protected boolean canGrowInto(Block blockType)
    {
        Material material = blockType.getDefaultState().getMaterial();
        return material == Material.AIR || material == Material.LEAVES || blockType == Blocks.GRASS || blockType == Blocks.DIRT || blockType == Blocks.LOG || blockType == Blocks.LOG2 || blockType == Blocks.SAPLING || blockType == Blocks.VINE ||
                blockType == KCore.KatharianDirt || blockType == KCore.KatharianGrass || blockType == KCore.MysticLog || blockType==KCore.ForgottenSand;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return false;
    }

    @Override
    protected void setDirtAt(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() != KCore.KatharianDirt)
        {
            this.setBlockAndNotifyAdequately(worldIn, pos, KCore.KatharianDirt.getDefaultState());
        }
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos) || state.getBlock().isLeaves(state, world, pos) || state.getBlock().isWood(world, pos) || canGrowInto(state.getBlock());
    }

    public void generateCrown(World world, BlockPos pos, int width,IBlockState leaves) {
        for (int i = -2; i <= 0; i++) {
            growLeavesLayerStrict(world, pos.up(i), width + 1 - i, leaves);
        }
    }

    /**
     * grow leaves in a circle with the outsides being within the circle
     */
    protected void growLeavesLayerStrict(World worldIn, BlockPos layerCenter, int width,IBlockState leaves)
    {
        int i = width * width;

        for (int j = -width; j <= width + 1; ++j)
        {
            for (int k = -width; k <= width + 1; ++k)
            {
                int l = j - 1;
                int i1 = k - 1;

                if (j * j + k * k <= i || l * l + i1 * i1 <= i || j * j + i1 * i1 <= i || l * l + k * k <= i)
                {
                    BlockPos blockpos = layerCenter.add(j, 0, k);
                    IBlockState state = worldIn.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(worldIn, blockpos, leaves);
                    }
                }
            }
        }
    }

    public boolean canGenerate(World worldIn, BlockPos pos)
    {
        int posY=pos.getY();
        return posY <= 200 && worldIn.isAirBlock(pos) && worldIn.getBlockState(pos.down()) == KCore.KatharianGrass.getDefaultState() &&
                worldIn.getBlockState(pos.down()) != KCore.MysticLeaves.getDefaultState();

    }
}
