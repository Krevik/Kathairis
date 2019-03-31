package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.BUTTERFLY_FLOWER;
import static io.github.krevik.kathairis.init.ModItems.BUTTERFLY_FLOWER_NECTAR;

/**
 * @author Krevik
 */
public class BlockButterflyFlower extends BlockKathairisPlant {

	public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", EnumType.class);
	protected static final AxisAlignedBB BUTTERFLYFLOWER_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.9D, 1D);

	public BlockButterflyFlower() {
		super();
		this.setDefaultState(this.stateContainer.getBaseState().with(VARIANT, EnumType.WITHOUT));
	}

	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.create(BUTTERFLYFLOWER_AABB);
	}

	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		if (rand.nextInt(10) == 0) {
			if (!worldIn.isDaytime()) {
				worldIn.setBlockState(pos, BUTTERFLY_FLOWER.getDefaultState().with(BlockButterflyFlower.VARIANT, EnumType.WITH));
			}
		}
		//TODO
//		if (state == BUTTERFLY_FLOWER.getDefaultState().with(BlockButterflyFlower.VARIANT, EnumType.WITH)) {
//			List<EntityButterfly> e = worldIn.getEntitiesWithinAABB(EntityButterfly.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX() + 15, pos.getY() + 15, pos.getZ() + 15));
//			List<EntityButterfly1> e1 = worldIn.getEntitiesWithinAABB(EntityButterfly1.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX() + 15, pos.getY() + 15, pos.getZ() + 15));
//
//			if (e != null) {
//				if (e.size() > 0) {
//					for (int c = 0; c < e.size(); c++) {
//						e.get(c).butterflyFlowerPos = pos;
//					}
//				}
//			}
//			if (e1 != null) {
//				if (e1.size() > 0) {
//					for (int c = 0; c < e1.size(); c++) {
//						e1.get(c).butterflyFlowerPos = pos;
//					}
//				}
//			}
//		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity p_180657_5_, ItemStack p_180657_6_) {
		super.harvestBlock(world, player, pos, state, p_180657_5_, p_180657_6_);
		if (state == BUTTERFLY_FLOWER.getDefaultState().with(BlockButterflyFlower.VARIANT, EnumType.WITH)) {
			spawnAsEntity(world, pos, new ItemStack(BUTTERFLY_FLOWER_NECTAR, 1));
		}
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> p_206840_1_) {
		super.fillStateContainer(p_206840_1_);
		p_206840_1_.add(VARIANT);
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

		EnumType(final int meta, final String name) {
			this.meta = meta;
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
