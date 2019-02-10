package mod.krevik.block;

import java.util.Random;

import javax.annotation.Nullable;

import mod.krevik.KCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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

public class BlockEasterEgg extends BaseBlock
{

    public BlockEasterEgg(String Name)
    {
        super(Name,Material.CAKE,null,0.0F,0.0F,SoundType.GROUND);
        this.setTickRandomly(false);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {

    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return null;
    }
    Random random = new Random();
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
    
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
    	return true;
    }
    

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }
}