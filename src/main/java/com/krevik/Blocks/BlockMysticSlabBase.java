//WITH TWILIGHT FOREST HELP
package com.krevik.Blocks;

import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import com.krevik.Main.KCore;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockMysticSlabBase extends BlockSlab {

	private static final PropertyEnum<MysticSlabVariant> VARIANT = PropertyEnum.create("variant", MysticSlabVariant.class);

	
	private final boolean isDouble;

	public BlockMysticSlabBase(boolean isDouble1,String Name, Material material, CreativeTabs tab, SoundType soundType,float hardness,float resistance) {
		super(material);
        this.setCreativeTab(tab);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setSoundType(soundType);
        this.setRegistryName(Name);
        this.setUnlocalizedName(Name);
		this.isDouble = isDouble1;
		this.setLightOpacity(isDouble ? 255 : 0);

		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, MysticSlabVariant.MYSTIC);

		if (!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);

		this.setDefaultState(state);
        KCore.instance.regHelper.slabList.add(this);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, VARIANT, HALF);
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	public boolean isDouble() {
		return isDouble;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return MysticSlabVariant.MYSTIC;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(state.getBlock()==KCore.MysticWoodHalfSlab||state.getBlock()==KCore.MysticWoodDoubleSlab) {
			return Item.getItemFromBlock(KCore.MysticWoodHalfSlab);
		}
		else if(state.getBlock()==KCore.ShinyWoodHalfSlab||state.getBlock()==KCore.ShinyWoodDoubleSlab){
			return Item.getItemFromBlock(KCore.ShinyWoodHalfSlab);
		}
		else if(state.getBlock()==KCore.SoulWoodHalfSlab||state.getBlock()==KCore.SoulWoodDoubleSlab){
			return Item.getItemFromBlock(KCore.SoulWoodHalfSlab);
		}else{
			return Item.getItemFromBlock(KCore.MysticWoodHalfSlab);
		}
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		if(state.getBlock()==KCore.MysticWoodHalfSlab||state.getBlock()==KCore.MysticWoodDoubleSlab) {
			return new ItemStack(Item.getItemFromBlock(KCore.MysticWoodHalfSlab), 2, 0);
		}
		else if(state.getBlock()==KCore.ShinyWoodHalfSlab||state.getBlock()==KCore.ShinyWoodDoubleSlab) {
			return new ItemStack(Item.getItemFromBlock(KCore.ShinyWoodHalfSlab), 2, 0);
		}
		else if(state.getBlock()==KCore.SoulWoodHalfSlab||state.getBlock()==KCore.SoulWoodDoubleSlab) {
			return new ItemStack(Item.getItemFromBlock(KCore.SoulWoodHalfSlab), 2, 0);
		}else{
			return new ItemStack(Item.getItemFromBlock(KCore.MysticWoodHalfSlab), 2, 0);
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		if(state.getBlock()==KCore.MysticWoodDoubleSlab||state.getBlock()==KCore.MysticWoodHalfSlab){
			return new ItemStack(KCore.MysticWoodHalfSlab);
		}
		else if(state.getBlock()==KCore.ShinyWoodDoubleSlab||state.getBlock()==KCore.ShinyWoodHalfSlab){
			return new ItemStack(KCore.ShinyWoodHalfSlab);
		}
		else if(state.getBlock()==KCore.SoulWoodDoubleSlab||state.getBlock()==KCore.SoulWoodHalfSlab){
			return new ItemStack(KCore.SoulWoodHalfSlab);
		}
		else{
			return new ItemStack(KCore.MysticWoodHalfSlab);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.isDouble() ? this.getDefaultState() : this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(HALF).ordinal();
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		if (this.isDouble())
			ModelLoader.setCustomStateMapper(this, new StateMap.Builder().ignore(HALF).build());
		else {
			KCore.instance.cproxy.registerToState(this, 0, getDefaultState());
		}
	}

    public static enum MysticSlabVariant implements IStringSerializable
    {
        MYSTIC(0,"mystic");

        private static final BlockMysticSlabBase.MysticSlabVariant[] META_LOOKUP = new BlockMysticSlabBase.MysticSlabVariant[values().length];
        private final int meta;
        private final String name;

        private MysticSlabVariant(int p_i46384_3_,String Name)
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
        public static BlockMysticSlabBase.MysticSlabVariant byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockMysticSlabBase.MysticSlabVariant blockstone$enumtype : values())
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