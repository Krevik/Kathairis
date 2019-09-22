package io.github.krevik.kathairis.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static io.github.krevik.kathairis.init.ModBlocks.KATHAIRIS_MINI_GRASS;

/**
 * @author Krevik
 */
public class BlockKathairisMiniGrass extends BlockKathairisPlant {

	protected static final AxisAlignedBB MYSTICMINIGRASS_AABB = new AxisAlignedBB(0, 0.0D, 0, 1D, 0.4D, 1D);

	public BlockKathairisMiniGrass() {
		super();
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
			spawnAsEntity(worldIn, pos, new ItemStack(KATHAIRIS_MINI_GRASS, 1));
		} else {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.create(MYSTICMINIGRASS_AABB);
	}

}
