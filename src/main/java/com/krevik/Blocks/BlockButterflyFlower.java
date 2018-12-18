package com.krevik.Blocks;

import java.util.List;
import java.util.Random;

import com.krevik.Entities.Butterflies.EntityButterfly;
import com.krevik.Entities.Butterflies.EntityButterfly1;
import com.krevik.Main.KCore;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockButterflyFlower extends BlockMysticBush{

    public static final PropertyEnum<BlockButterflyFlower.EnumType> VARIANT = PropertyEnum.<BlockButterflyFlower.EnumType>create("variant", BlockButterflyFlower.EnumType.class);

	public BlockButterflyFlower(String Name, boolean b, boolean replacable1) {
		super(Name, b, replacable1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockButterflyFlower.EnumType.WITHOUT));
	}
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(KCore.ButterflyFlower.getDefaultState().getBlock());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockButterflyFlower.EnumType.byMetadata(meta));
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        if(rand.nextInt(10)==0) {
        	if(!worldIn.isDaytime()) {
        		worldIn.setBlockState(pos, KCore.ButterflyFlower.getDefaultState().withProperty(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH));
        	}
        }
        if(state==KCore.ButterflyFlower.getDefaultState().withProperty(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH)) {
            List<EntityButterfly> e = worldIn.getEntitiesWithinAABB(EntityButterfly.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX()  + 15, pos.getY() + 15, pos.getZ() + 15));
            List<EntityButterfly1> e1 = worldIn.getEntitiesWithinAABB(EntityButterfly1.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX()  + 15, pos.getY() + 15, pos.getZ() + 15));
            
            if(e!=null) {
            	if(e.size()>0) {
            		for(int c=0;c<e.size();c++) {
            			e.get(c).butterflyFlowerPos=pos;
            		}
            	}
            }
            if(e1!=null) {
            	if(e1.size()>0) {
            		for(int c=0;c<e1.size();c++) {
            			e1.get(c).butterflyFlowerPos=pos;
            		}
            	}
            }
        }
    }
    
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	super.breakBlock(worldIn, pos, state);
    	if(state==KCore.ButterflyFlower.getDefaultState().withProperty(BlockButterflyFlower.VARIANT, BlockButterflyFlower.EnumType.WITH)) {
            spawnAsEntity(worldIn, pos, new ItemStack(KCore.ButterflyFlowerNectar, 1, 0));
    	}
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockButterflyFlower.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
    
    public static enum EnumType implements IStringSerializable
    {
        WITHOUT(0,"without"),
        WITH(1,"with");

        private static final BlockButterflyFlower.EnumType[] META_LOOKUP = new BlockButterflyFlower.EnumType[values().length];
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
        public static BlockButterflyFlower.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockButterflyFlower.EnumType blockstone$enumtype : values())
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
