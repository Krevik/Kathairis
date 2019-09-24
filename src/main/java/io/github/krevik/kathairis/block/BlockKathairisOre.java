package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

/**
 * @author Krevik
 */
public class BlockKathairisOre extends Block implements IItemGroupProvider {

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

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

}
