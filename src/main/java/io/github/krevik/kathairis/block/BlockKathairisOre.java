package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author Krevik
 */
public class BlockKathairisOre extends Block {

	private final int maxXp;
	private final int minXp;
	private int amountOfDroppedItems;

	public BlockKathairisOre(int quantityDrop, final int minXp, final int maxXp, Properties builder) {
		super(builder);
		amountOfDroppedItems = quantityDrop;
		this.maxXp = maxXp;
		this.minXp = minXp;
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this);
	}

}
