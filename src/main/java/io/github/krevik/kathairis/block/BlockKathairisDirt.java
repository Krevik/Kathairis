package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Cadiboo
 */
public class BlockKathairisDirt extends Block implements IItemGroupProvider {

	public BlockKathairisDirt() {
		super(Properties.create(Material.GOURD).sound(SoundType.GROUND).hardnessAndResistance(0.5F, 0.5F));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

}
