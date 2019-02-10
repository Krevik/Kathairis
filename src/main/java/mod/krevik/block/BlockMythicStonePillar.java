package mod.krevik.block;

import mod.krevik.util.CreativeTabsMystic;
import mod.krevik.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMythicStonePillar extends BaseBlock
{

    public static final PropertyEnum<BlockMythicStonePillar.EnumType> VARIANT = PropertyEnum.<BlockMythicStonePillar.EnumType>create("variant", BlockMythicStonePillar.EnumType.class);    
    
    public BlockMythicStonePillar(String Name)
    {
    	super(Name,Material.ROCK,CreativeTabsMystic.buildingBlocks,3F,3F,SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.NOCONNECTION));
    }
    
    
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }


    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;

    }
    
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return FULL_BLOCK_AABB;
    }


    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    /**
     * Checks if this block can be placed exactly at the given position.
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
    	return true;
    }
    
	   @Override
       public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
		   operateStatements(state,worldIn,pos,blockIn,fromPos);
	    }
	   
	   private void operateStatements(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
	    	IBlockState result = this.getDefaultState();
	    	
	        Block pillar = KCore.MythicStonePillar;
	        
	        Block iblockstate1,iblockstate2,iblockstate3,iblockstate4;

	            iblockstate1 = worldIn.getBlockState(pos.east()).getBlock();
	            iblockstate2 = worldIn.getBlockState(pos.west()).getBlock();
	            iblockstate3 = worldIn.getBlockState(pos.north()).getBlock();
	            iblockstate4 = worldIn.getBlockState(pos.south()).getBlock();

	        if(iblockstate1!=pillar&&iblockstate2!=pillar&&iblockstate3!=pillar&&iblockstate4!=pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.NOCONNECTION);
	        }
	        if(iblockstate1==pillar||iblockstate2==pillar||iblockstate3==pillar||iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.ONECONNECTIONS);
	        }
	        if(iblockstate2==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNERWS);
	        }
	        if(iblockstate1==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNERSE);
	        }
	        if(iblockstate1==pillar&&iblockstate3==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNEREN);
	        }
	        if(iblockstate2==pillar&&iblockstate3==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNERNW);
	        }
	        if(iblockstate2==pillar&&iblockstate1==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.TWOCONNECTIONS);
	        }
	        if(iblockstate3==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.TWOCONNECTIONS);
	        }
	        if(iblockstate1==pillar&&iblockstate2==pillar&&iblockstate3==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTS);
	        }
	        if(iblockstate2==pillar&&iblockstate3==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTE);
	        }
	        if(iblockstate1==pillar&&iblockstate3==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTW);
	        }
	        if(iblockstate1==pillar&&iblockstate2==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTN);
	        }
	        if(iblockstate1==pillar&&iblockstate2==pillar&&iblockstate3==pillar&&iblockstate4==pillar) {
	        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.FOURCONNECTIONS);
	        }
	        
	        worldIn.setBlockState(pos, result);
	        
	   }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	IBlockState result = this.getDefaultState();
    	
        IBlockState iblockstate0 = worldIn.getBlockState(pos.offset(facing.getOpposite()));

        Block pillar = KCore.MythicStonePillar;
        
        Block iblockstate1,iblockstate2,iblockstate3,iblockstate4;

    	if(facing.getOpposite()==EnumFacing.DOWN) {
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east().up()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west().up()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north().up()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south().up()).getBlock();
    	}else if(facing.getOpposite()==EnumFacing.UP){
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east().down()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west().down()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north().down()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south().down()).getBlock();
    	}else if(facing.getOpposite()==EnumFacing.NORTH) {
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east().south()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west().south()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north().south()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south().south()).getBlock();
    	}else if(facing.getOpposite()==EnumFacing.SOUTH) {
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east().north()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west().north()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north().north()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south().north()).getBlock();
    	}else if(facing.getOpposite()==EnumFacing.WEST) {
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east().east()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west().east()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north().east()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south().east()).getBlock();
    	}else if(facing.getOpposite()==EnumFacing.EAST) {
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east().west()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west().west()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north().west()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south().west()).getBlock();
    	}else {
            iblockstate1 = worldIn.getBlockState(pos.offset(facing.getOpposite()).east()).getBlock();
            iblockstate2 = worldIn.getBlockState(pos.offset(facing.getOpposite()).west()).getBlock();
            iblockstate3 = worldIn.getBlockState(pos.offset(facing.getOpposite()).north()).getBlock();
            iblockstate4 = worldIn.getBlockState(pos.offset(facing.getOpposite()).south()).getBlock();
    	}

        if(iblockstate1!=pillar&&iblockstate2!=pillar&&iblockstate3!=pillar&&iblockstate4!=pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.NOCONNECTION);
        }
        if(iblockstate1==pillar||iblockstate2==pillar||iblockstate3==pillar||iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.ONECONNECTIONS);
        }
        if(iblockstate2==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNERWS);
        }
        if(iblockstate1==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNERSE);
        }
        if(iblockstate1==pillar&&iblockstate3==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNEREN);
        }
        if(iblockstate2==pillar&&iblockstate3==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.CORNERNW);
        }
        if(iblockstate2==pillar&&iblockstate1==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.TWOCONNECTIONS);
        }
        if(iblockstate3==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.TWOCONNECTIONS);
        }
        if(iblockstate1==pillar&&iblockstate2==pillar&&iblockstate3==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTS);
        }
        if(iblockstate2==pillar&&iblockstate3==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTE);
        }
        if(iblockstate1==pillar&&iblockstate3==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTW);
        }
        if(iblockstate1==pillar&&iblockstate2==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.THREECONNECTIONSWITHOUTN);
        }
        if(iblockstate1==pillar&&iblockstate2==pillar&&iblockstate3==pillar&&iblockstate4==pillar) {
        	result=this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.FOURCONNECTIONS);
        }
        
        return result;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockMythicStonePillar.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockMythicStonePillar.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    public static enum EnumType implements IStringSerializable
    {
        NOCONNECTION(0,"noconnection"),
        CORNERWS(1,"cornerws"),
    	ONECONNECTIONS(2,"oneconnections"),
    	TWOCONNECTIONS(3,"twoconnections"),
    	THREECONNECTIONSWITHOUTW(4,"threeconnectionswithoutw"),
    	THREECONNECTIONSWITHOUTE(5,"threeconnectionswithoute"),
    	THREECONNECTIONSWITHOUTS(6,"threeconnectionswithouts"),
    	THREECONNECTIONSWITHOUTN(7,"threeconnectionswithoutn"),
    	FOURCONNECTIONS(8,"fourconnections"),
        CORNERSE(9,"cornerse"),
        CORNEREN(10,"corneren"),
        CORNERNW(11,"cornernw");

        private static final BlockMythicStonePillar.EnumType[] META_LOOKUP = new BlockMythicStonePillar.EnumType[values().length];
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
        public static BlockMythicStonePillar.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockMythicStonePillar.EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }

		@Override
		public String getName() {
			return name;
		}
    }
}