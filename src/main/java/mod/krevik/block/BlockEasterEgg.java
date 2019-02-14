package mod.krevik.block;

import mod.krevik.KCore;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockEasterEgg extends BaseBlock
{

    public BlockEasterEgg(String Name)
    {
        super(Name,Material.CAKE,null,0.0F,0.0F,SoundType.GROUND);
        this.setTickRandomly(false);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return null;
    }

    Random random = new Random();

    @Override
    public void breakBlock(World world,BlockPos pos,IBlockState state) {
    	super.breakBlock(world, pos, state);
    	for(int c=0;c<(3+random.nextInt(4));c++) {
    		EntityItem is = new EntityItem(world);
    		is.setItem(KCore.functionHelper.getEasterReward());
    		is.setPosition(pos.getX()+0.5, pos.getY(),pos.getZ()+0.5);
    		if(!world.isRemote) {
    			world.spawnEntity(is);
    		}
    	}
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }
}