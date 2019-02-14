package mod.krevik.block.plants;

import mod.krevik.KCore;
import mod.krevik.block.BaseBlock;
import mod.krevik.network.KathairisPacketHandler;
import mod.krevik.network.packets.PacketJadeVinesServer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
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

public class BlockJadeVines extends BaseBlock implements net.minecraftforge.common.IShearable
{
    protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);

    public BlockJadeVines(String Name,CreativeTabs tab, float hardness, float resistance,SoundType type)
    {
        super(Name, Material.VINE, tab, hardness, resistance, type);
        this.setTickRandomly(true);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }
    
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.up());
        return !worldIn.isAirBlock(pos.up());
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return !worldIn.isAirBlock(pos.up());
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
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

    /**
     * Whether this Block can be replaced directly by other blocks (true for e.g. tall grass)
     */
    @Override
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    public boolean canAttachTo(World world, BlockPos pos, EnumFacing p_193395_3_)
    {
        return !world.isAirBlock(pos.up());
    }

    private boolean isAcceptableNeighbor(World world, BlockPos pos, EnumFacing p_193396_3_)
    {

	       // if(world.getBlockState(pos.up()).isFullBlock()||world.getBlockState(pos.up())==KCore.JadeVine_empty.getDefaultState()||
	        //		world.getBlockState(pos.up())==KCore.JadeVine_top.getDefaultState()||
	        //				world.getBlockState(pos.up())==KCore.JadeVine_mid.getDefaultState()||
	        //						world.getBlockState(pos.up())==KCore.JadeVine_bottom.getDefaultState()||world.getBlockState(pos.up())==KCore.MysticLeaves.getDefaultState()) {
	        //	return true;
	       // }else {
	        //	return false;
	        //}
    	if(this==KCore.JadeVine_empty||this==KCore.JadeVine_top||this==KCore.JadeVine_mid) {
            return world.getBlockState(pos.up()).getBlock().isFullBlock(world.getBlockState(pos.up())) || world.getBlockState(pos.up()) == KCore.JadeVine_empty.getDefaultState() ||
                    world.getBlockState(pos.up()) == KCore.JadeVine_top.getDefaultState() || world.getBlockState(pos.up()) == KCore.JadeVine_mid.getDefaultState();
    	}else if(this==KCore.JadeVine_bottom) {
            return world.getBlockState(pos.up()) == KCore.JadeVine_mid.getDefaultState();
    	}else {
    		return false;
    	}
    }

    protected static boolean isExceptBlockForAttaching(Block p_193397_0_)
    {
        return p_193397_0_ instanceof BlockShulkerBox || p_193397_0_ == Blocks.BEACON || p_193397_0_ == Blocks.CAULDRON || p_193397_0_ == Blocks.GLASS || p_193397_0_ == Blocks.STAINED_GLASS || p_193397_0_ == Blocks.PISTON || p_193397_0_ == Blocks.STICKY_PISTON || p_193397_0_ == Blocks.PISTON_HEAD || p_193397_0_ == Blocks.TRAPDOOR;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	if(!worldIn.isRemote) {
        	if(this==KCore.JadeVine_bottom) {
        		if(worldIn.getBlockState(pos.up())!=KCore.JadeVine_mid) {
        			worldIn.destroyBlock(pos, true);
        		}
        	}
        	if(worldIn.isAirBlock(pos.up())) {
        		worldIn.destroyBlock(pos, true);
        	}	
    	}
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
        	if(this==KCore.JadeVine_bottom) {
        		if(worldIn.getBlockState(pos.up())!=KCore.JadeVine_mid) {
        			worldIn.destroyBlock(pos, true);
        		}
        	}
        	if(rand.nextInt(20)==0) {
        		if(worldIn.isAirBlock(pos.down())) {
	        		if(this==KCore.JadeVine_empty) {
	        			worldIn.setBlockState(pos.down(), KCore.JadeVine_top.getDefaultState());
	        		}
	        		if(this==KCore.JadeVine_top) {
	        			worldIn.setBlockState(pos.down(), KCore.JadeVine_mid.getDefaultState());
	        		}
	        		if(this==KCore.JadeVine_mid) {
	        			worldIn.setBlockState(pos.down(), KCore.JadeVine_bottom.getDefaultState());
	        		}
        	}
        	}
        	if(worldIn.isAirBlock(pos.up())) {
        		worldIn.destroyBlock(pos, true);
        	}
        }
    }
    
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	if(worldIn.isRemote) {
    		if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
				KathairisPacketHandler.CHANNEL.sendToServer(new PacketJadeVinesServer());
    		}
    	}
    }

    public void grow(World worldIn, BlockPos pos) {
		if(worldIn.isAirBlock(pos.down())) {
    		if(this==KCore.JadeVine_empty) {
    			worldIn.setBlockState(pos.down(), KCore.JadeVine_top.getDefaultState());
    		}
    		if(this==KCore.JadeVine_top) {
    			worldIn.setBlockState(pos.down(), KCore.JadeVine_mid.getDefaultState());
    		}
    		if(this==KCore.JadeVine_mid) {
    			worldIn.setBlockState(pos.down(), KCore.JadeVine_bottom.getDefaultState());
    		}
	}
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(KCore.JadeVine_empty, 1));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    /*************************FORGE START***********************************/
    @Override public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity){ return true; }

    @Override public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){ return true; }

    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return java.util.Arrays.asList(new ItemStack(this, 1));
    }
    /*************************FORGE END***********************************/
}