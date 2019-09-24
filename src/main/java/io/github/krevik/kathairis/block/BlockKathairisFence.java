package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

/**
 * @author Krevik
 */
public class BlockKathairisFence extends FenceBlock implements IBucketPickupHandler, ILiquidContainer, IItemGroupProvider {

	public BlockKathairisFence(Material material, float hardnessAndResistance, SoundType soundType) {
		super(Properties.create(material).hardnessAndResistance(hardnessAndResistance).sound(soundType));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}

}
