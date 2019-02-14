package mod.krevik.block;

import mod.krevik.entity.EntityGecko;
import mod.krevik.util.CreativeTabsMystic;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockGeckoEggs extends BaseBlock{
	
    protected static final AxisAlignedBB EGGS_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.3D, 0.7D);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return EGGS_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	return EGGS_AABB;
    }

    boolean ignoreSimilarity=true;
	public BlockGeckoEggs(String Name) {
		super(Name, Material.CORAL, CreativeTabsMystic.miscellaneous, 0.5F, 0.5F, SoundType.STONE);
		this.setTickRandomly(true);
	}
	int timer=0;

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	super.updateTick(worldIn, pos, state, rand);
    	timer++;
    	if(timer>25+rand.nextInt(50)) {
    		timer=0;
    		
    		if(!worldIn.isRemote) {
	    		for(int x=0;x<1+rand.nextInt(4);x++) {
	        		EntityGecko eg = new EntityGecko(worldIn);
	        		eg.setLocationAndAngles(pos.getX()+0.5+(rand.nextFloat()-rand.nextFloat())/2, pos.getY()+0.5+(rand.nextFloat()-rand.nextFloat())/2, pos.getZ()+0.5+(rand.nextFloat()-rand.nextFloat())/2, 0, 0);
	        		worldIn.spawnEntity(eg);
	        		eg.addGrowth(-40);
	    		}
    		}
    		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        return (this.ignoreSimilarity || block != this) && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
	
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return null;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
    	return new ItemStack(this);
    }

}
