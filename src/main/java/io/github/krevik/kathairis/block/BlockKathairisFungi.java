package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.item.ItemGroup;

/**
 * @author Cadiboo
 */
public class BlockKathairisFungi extends BlockKathairisPlant implements IItemGroupProvider {

	public BlockKathairisFungi() {
		super();
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.PLANTS;
	}

}
