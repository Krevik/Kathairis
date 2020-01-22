package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.*;
import static io.github.krevik.kathairis.init.ModItems.GOOSEBERRIES;

/**
 * @author Krevik
 */
public class BlockGooseberry extends Block implements IItemGroupProvider {

	public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", EnumType.class);

	public BlockGooseberry(Material material, float hardness, float resistance, SoundType soundType) {
		super(Properties.create(material).hardnessAndResistance(hardness, resistance).sound(soundType));
		setDefaultState(getDefaultState().with(VARIANT, EnumType.WITHOUT));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.PLANTS;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.tick(state, world, pos, random);
		if (!world.isRemote) {
			if (random.nextInt(50) == 0) {
				world.setBlockState(pos, GOOSEBERRY_BUSH.getDefaultState().with(VARIANT, EnumType.WITH));
			}
		}
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return (worldIn.getNeighborAwareLightSubtracted(pos, 0) >= 8 || worldIn.canBlockSeeSky(pos)) && isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
	}



	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
		if (state == GOOSEBERRY_BUSH.getDefaultState().with(VARIANT, EnumType.WITH)) {
			player.addItemStackToInventory(new ItemStack(GOOSEBERRIES, 1 + player.getRNG().nextInt(5)));
			world.setBlockState(pos, GOOSEBERRY_BUSH.getDefaultState());
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}


	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		Random random = new Random();
		if (state == GOOSEBERRY_BUSH.getDefaultState().with(VARIANT, EnumType.WITH)) {
			for (int c = 0; c < (2 + random.nextInt(4)); c++) {
				ItemEntity is = new ItemEntity(world,pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				is.setItem(new ItemStack(GOOSEBERRIES));
				is.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				if (!world.isRemote) {
					world.addEntity(is);
				}
			}
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(VARIANT);
	}

	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
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
