package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

import static io.github.krevik.kathairis.init.ModItems.GLASS_JAR;

/**
 * @author Krevik
 */
public class BlockKatharianCloud extends Block {

	private Supplier<Item> pickedItem;

	public BlockKatharianCloud(Supplier<Item> itemAfterPick) {
		super(Block.Properties.create(Material.CLOTH).doesNotBlockMovement().sound(SoundType.CLOTH).hardnessAndResistance(0.5f));
		pickedItem = itemAfterPick;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean isSideInvisible(IBlockState state, IBlockState adjacentBlockState, EnumFacing side) {
		return adjacentBlockState.getBlock() == this || super.isSideInvisible(state, adjacentBlockState, side);
	}

	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean propagatesSkylightDown(IBlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_) {
		return true;
	}

	@Override
	public int getOpacity(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return null;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Deprecated
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		Item heldItem = player.getHeldItem(hand).getItem();
		if (heldItem == GLASS_JAR) {
			if (!worldIn.isRemote) {
				player.getHeldItem(hand).shrink(1);
				player.addItemStackToInventory(new ItemStack(pickedItem.get()));
			}
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			return true;
		}
		return false;
	}

	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.motionY = -0.0000001;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

}
