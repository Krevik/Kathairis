package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.GLOWVINES;

/**
 * @author Krevik
 */
public class BlockGlowVines extends BlockKathairisPlant implements IItemGroupProvider {

	public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", EnumType.class);
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public BlockGlowVines() {
		super(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0).sound(SoundType.PLANT).tickRandomly().doesNotBlockMovement().lightValue(5));
		setDefaultState(getDefaultState().with(VARIANT, EnumType.TOP).with(FACING, Direction.EAST));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos,isMoving);
		handleFacing(worldIn, pos, state);
		handleVariants(worldIn, pos, state);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return canPlaceBlockAt(worldIn, pos);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		if (!worldIn.isRemote) {
			if (random.nextInt(40) == 0) {
				grow(worldIn, pos, random);
			}
		}
		handleFacing(worldIn, pos, state);
		handleVariants(worldIn, pos, state);
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
			spawnAsEntity(worldIn, pos, new ItemStack(GLOWVINES, 1));
		} else {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		handleFacing(worldIn, pos, state);
		handleVariants(worldIn, pos, state);
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines) {
			if (worldIn.getBlockState(pos.up()).get(VARIANT) == EnumType.MID) {
				if (RANDOM.nextInt(5) == 0) {
					worldIn.setBlockState(pos, state.with(VARIANT, EnumType.BOTTOM));
				}
			}
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
		super.fillStateContainer(p_206840_1_);
		p_206840_1_.add(VARIANT, FACING);
	}

	public void grow(World worldIn, BlockPos pos, Random random) {
		if (worldIn.getBlockState(pos).getBlock() instanceof BlockGlowVines) {
			if (worldIn.isAirBlock(pos.down())) {
				EnumType variant = worldIn.getBlockState(pos).get(VARIANT);
				if (variant == EnumType.TOP) {
					worldIn.setBlockState(pos.down(), GLOWVINES.getDefaultState().with(VARIANT, EnumType.MID));
				}
				if (variant == EnumType.MID) {
					if (random.nextInt(5) == 0) {
						worldIn.setBlockState(pos.down(), GLOWVINES.getDefaultState().with(VARIANT, EnumType.BOTTOM));
					} else {
						worldIn.setBlockState(pos.down(), GLOWVINES.getDefaultState().with(VARIANT, EnumType.MID));
					}
					if (!worldIn.isAirBlock(pos.down(2))) {
						worldIn.setBlockState(pos.down(), GLOWVINES.getDefaultState().with(VARIANT, EnumType.BOTTOM));
					}
				}
			}
		}
	}

	public void handleFacing(World world, BlockPos pos, BlockState state) {
		if (!world.isAirBlock(pos.east()) && !(world.getBlockState(pos.east()).getBlock() instanceof BlockGlowVines)) {
			if (world.getBlockState(pos).has(FACING)) {
				world.setBlockState(pos, state.with(FACING, Direction.NORTH));
			}
		} else if (!world.isAirBlock(pos.west()) && !(world.getBlockState(pos.west()).getBlock() instanceof BlockGlowVines)) {
			if (world.getBlockState(pos).has(FACING)) {
				world.setBlockState(pos, state.with(FACING, Direction.SOUTH));
			}
		} else if (!world.isAirBlock(pos.south()) && !(world.getBlockState(pos.south()).getBlock() instanceof BlockGlowVines)) {
			if (world.getBlockState(pos).has(FACING)) {
				world.setBlockState(pos, state.with(FACING, Direction.EAST));
			}
		} else if (!world.isAirBlock(pos.north()) && !(world.getBlockState(pos.north()).getBlock() instanceof BlockGlowVines)) {
			if (world.getBlockState(pos).has(FACING)) {
				world.setBlockState(pos, state.with(FACING, Direction.WEST));
			}
		}
	}

	public void handleVariants(World world, BlockPos pos, BlockState state) {
		if (world.getBlockState(pos).getBlock() instanceof BlockGlowVines) {
			EnumType variant = world.getBlockState(pos).get(VARIANT);
			if (world.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines) {
				EnumType upperVariant = world.getBlockState(pos.up()).get(VARIANT);
				if (variant == EnumType.TOP) {
					if (upperVariant == EnumType.TOP || upperVariant == EnumType.MID || upperVariant == EnumType.BOTTOM) {
						world.setBlockState(pos, state.with(VARIANT, EnumType.MID));
					}
				}
				if (variant == EnumType.MID) {
					if (upperVariant == EnumType.BOTTOM) {
						world.destroyBlock(pos, true);
					}
					if (world.getBlockState(pos.down(2)).isSolid() || world.getBlockState(pos.down()).isSolid()) {
						world.setBlockState(pos, state.with(VARIANT, EnumType.BOTTOM));
					}
				}
				if (variant == EnumType.BOTTOM) {
					if (upperVariant == EnumType.BOTTOM || upperVariant == EnumType.TOP) {
						world.destroyBlock(pos, true);
					}
				}
			}
		}
	}

	public boolean canPlaceBlockAt(IWorldReader worldIn, BlockPos pos) {
		boolean can = false;
		if (!worldIn.isAirBlock(pos.east()) && worldIn.getBlockState(pos.east()).isSolid()) {
			can = true;
		}
		if (!worldIn.isAirBlock(pos.west()) && worldIn.getBlockState(pos.west()).isSolid()) {
			can = true;
		}
		if (!worldIn.isAirBlock(pos.south()) && worldIn.getBlockState(pos.south()).isSolid()) {
			can = true;
		}
		if (!worldIn.isAirBlock(pos.north()) && worldIn.getBlockState(pos.north()).isSolid()) {
			can = true;
		}
		if (!worldIn.isAirBlock(pos.up())) {
			if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockGlowVines) {
				EnumType upperVariant = worldIn.getBlockState(pos.up()).get(VARIANT);
				can = upperVariant != EnumType.BOTTOM;
			}
		}
		return can;
	}

	//TODO REMOVE REMOVE REMOVE. GETTING RID OF THIS STUFF IS WHAT 1.13 WAS ALL ABOUT
	public enum EnumType implements IStringSerializable {
		TOP(0, "top"),
		MID(1, "mid"),
		BOTTOM(2, "bottom");

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

		/**
		 * Returns an EnumType for the BlockState from a metadata value.
		 */
		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		/**
		 * Returns the EnumType's metadata value.
		 */
		public int getMetadata() {
			return this.meta;
		}

		@Override
		public String getName() {
			return name;
		}
	}

}
