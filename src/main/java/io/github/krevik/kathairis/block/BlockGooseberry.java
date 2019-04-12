package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.*;
import static io.github.krevik.kathairis.init.ModItems.GOOSEBERRIES;

/**
 * @author Krevik
 */
public class BlockGooseberry extends Block {

	public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", EnumType.class);

	public BlockGooseberry(Material material, float hardness, float resistance, SoundType soundType) {
		super(Block.Properties.create(material).hardnessAndResistance(hardness, resistance).sound(soundType));
		setDefaultState(getDefaultState().with(VARIANT, EnumType.WITHOUT));
	}

	@Override
	public boolean isFullCube(IBlockState p_149686_1_) {
		return false;
	}

	@Override
	public void tick(IBlockState state, World world, BlockPos pos, Random random) {
		super.tick(state, world, pos, random);
		if (!world.isRemote) {
			if (random.nextInt(50) == 0) {
				world.setBlockState(pos, GOOSEBERRY_BUSH.getDefaultState().with(VARIANT, EnumType.WITH));
			}
		}
	}

	@Override
	public void dropBlockAsItemWithChance(IBlockState p_196255_1_, World p_196255_2_, BlockPos p_196255_3_, float p_196255_4_, int p_196255_5_) {

	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		return (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.canSeeSky(pos)) && isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, net.minecraft.util.EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean result = false;
		if (state == GOOSEBERRY_BUSH.getDefaultState().with(VARIANT, EnumType.WITH)) {
			player.addItemStackToInventory(new ItemStack(GOOSEBERRIES, 1 + player.getRNG().nextInt(5)));
			result = true;
			world.setBlockState(pos, GOOSEBERRY_BUSH.getDefaultState());
		}
		return result;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		Random random = new Random();
		if (state == GOOSEBERRY_BUSH.getDefaultState().with(VARIANT, EnumType.WITH)) {
			for (int c = 0; c < (2 + random.nextInt(4)); c++) {
				EntityItem is = new EntityItem(world);
				is.setItem(new ItemStack(GOOSEBERRIES));
				is.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				if (!world.isRemote) {
					world.spawnEntity(is);
				}
			}
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(VARIANT);
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IWorldReader world, BlockPos pos, EnumFacing face) {
		return true;
	}

	protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.FARMLAND || state.getBlock() == KATHAIRIS_DIRT ||
				state.getBlock() == KATHAIRIS_GRASS || state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT;
	}

	//TODO REMOVE REMOVE REMOVE. GETTING RID OF THIS STUFF IS WHAT 1.13 WAS ALL ABOUT
	public enum EnumType implements IStringSerializable {
		WITHOUT(0, "without"),
		WITH(1, "with");

		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
		static {
			for (EnumType blockstone$enumtype : values()) {
				META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
			}
		}
		private final int meta;
		private final String name;

		EnumType(int p_i46384_3_, final String name) {
			this.meta = p_i46384_3_;
			this.name = name;
		}

		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public int getMetadata() {
			return this.meta;
		}

		@Override
		public String getName() {
			return name;
		}
	}

}
