package mod.krevik.block;

import mod.krevik.KCore;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockChristmasGift extends BaseBlock {

    public static final PropertyEnum<BlockChristmasGift.EnumType> VARIANT = PropertyEnum.<BlockChristmasGift.EnumType>create("color", BlockChristmasGift.EnumType.class);

    public BlockChristmasGift(String Name){
        super(Name, Material.CLOTH, null,0,0, SoundType.CLOTH);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.RED));
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        int k=KCore.instance.functionHelper.random.nextInt(3);
        if(k==0){worldIn.setBlockState(pos,state.withProperty(VARIANT,EnumType.RED));}
        if(k==1){worldIn.setBlockState(pos,state.withProperty(VARIANT,EnumType.YELLOW));}
        if(k==2){worldIn.setBlockState(pos,state.withProperty(VARIANT,EnumType.VIOLET));}

    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(KCore.christmas_gift);
    }
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockChristmasGift.EnumType.byMetadata(meta));
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if(!worldIn.isRemote){
            for(int x=1;x<=2+KCore.instance.functionHelper.random.nextInt(4);x++){
                ItemStack reward = KCore.instance.functionHelper.getChristmasGiftDrop();
                EntityItem item = new EntityItem(worldIn,pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5);
                item.setItem(reward);
                worldIn.spawnEntity(item);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((BlockChristmasGift.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

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
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public static enum EnumType implements IStringSerializable
    {
        YELLOW(0,"yellow"),
        RED(1,"red"),
        VIOLET(2,"violet");

        private static final BlockChristmasGift.EnumType[] META_LOOKUP = new BlockChristmasGift.EnumType[values().length];
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
        public static BlockChristmasGift.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockChristmasGift.EnumType blockstone$enumtype : values())
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
