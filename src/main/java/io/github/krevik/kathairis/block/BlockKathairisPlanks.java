package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockKathairisPlanks extends Block implements IItemGroupProvider {
    public BlockKathairisPlanks() {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(3f, 3f).sound(SoundType.WOOD));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }
}
