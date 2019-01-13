package mod.krevik.block.plants;

import java.util.Random;

import javax.annotation.Nullable;

import mod.krevik.KCore;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGooseberry extends BlockMysticBush implements net.minecraftforge.common.IShearable{

    public static final PropertyEnum<BlockGooseberry.EnumType> VARIANT = PropertyEnum.<BlockGooseberry.EnumType>create("variant", BlockGooseberry.EnumType.class);

	public BlockGooseberry(String Name) {
		super(Name,false);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockGooseberry.EnumType.WITHOUT));
        this.setTickRandomly(true);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt);
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
	
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        if(!worldIn.isRemote) {
            if (rand.nextInt(150) == 0) {
                worldIn.setBlockState(pos, KCore.GooseberryBlock.getDefaultState().withProperty(VARIANT, BlockGooseberry.EnumType.WITH));
            }
        }
    }
	
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
       boolean result=false;
       if(state==KCore.GooseberryBlock.getDefaultState().withProperty(VARIANT, BlockGooseberry.EnumType.WITH)) {
    	   playerIn.addItemStackToInventory(new ItemStack(KCore.Gooseberry,1+KCore.instance.functionHelper.random.nextInt(5)));
    	   result=true;
    	   worldIn.setBlockState(pos, KCore.GooseberryBlock.getDefaultState());
       }
       
       return result;
    }



    public void breakBlock(World world,BlockPos pos,IBlockState state) {
	    Random random = new Random();
	    if(state==KCore.GooseberryBlock.getDefaultState().withProperty(VARIANT, BlockGooseberry.EnumType.WITH)){
            for(int c=0;c<(2+random.nextInt(4));c++) {
                EntityItem is = new EntityItem(world);
                is.setItem(new ItemStack(KCore.Gooseberry));
                is.setPosition(pos.getX()+0.5, pos.getY(),pos.getZ()+0.5);
                if(!world.isRemote) {
                    world.spawnEntity(is);
                }
            }
        }
        super.breakBlock(world, pos, state);
    }
	
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
    	return BlockRenderLayer.CUTOUT;
    }
    public boolean isOpaqueCube(IBlockState state)
    {
    	return false;
    }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	return true;
    }
    public int quantityDropped(Random random)
    {
        return 0;
    }
    @Override
    public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        return NonNullList.withSize(1, new ItemStack(this, 1, 0));
    }
    protected boolean canSilkHarvest()
    {
    	return true;
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return KCore.Gooseberry;
    }
    
    public static enum EnumType implements IStringSerializable
    {
        WITHOUT(0,"without"),
        WITH(1,"with");

        private static final BlockGooseberry.EnumType[] META_LOOKUP = new BlockGooseberry.EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int p_i46384_3_,String Name)
        {
            this.meta = p_i46384_3_;
            name=Name;
        }

        /**
         * Returns the EnumType's metadata value.
         */
        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * Returns an EnumType for the BlockState from a metadata value.
         */
        public static BlockGooseberry.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockGooseberry.EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }

		@Override
		public String getName() {
			return name;
		}
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockGooseberry.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockGooseberry.EnumType.byMetadata(meta));
    }
}
