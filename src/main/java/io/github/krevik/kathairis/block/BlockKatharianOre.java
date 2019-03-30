package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author Krevik
 */
public class BlockKatharianOre extends Block {

	private final int maxXp;
	private final int minXp;
	private int amountOfDroppedItems;

	public BlockKatharianOre(int quantityDrop, final int minXp, final int maxXp, Properties builder) {
		super(builder);
		amountOfDroppedItems = quantityDrop;
		this.maxXp = maxXp;
		this.minXp = minXp;
	}

	@Override
	public int quantityDropped(IBlockState state, Random random) {
		return amountOfDroppedItems;
	}

	@Override
	public void dropBlockAsItemWithChance(IBlockState state, World worldIn, BlockPos pos, float chancePerItem, int fortune) {
		super.dropBlockAsItemWithChance(state, worldIn, pos, chancePerItem, fortune);
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune) {
		World world = reader instanceof World ? (World) reader : null;
		if (world == null || this.getItemDropped(state, world, pos, fortune) != this) {
			return MathHelper.nextInt(this.RANDOM, this.minXp, this.maxXp);
		}
		return 0;
	}

}
