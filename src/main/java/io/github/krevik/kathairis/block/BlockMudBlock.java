package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockMudBlock extends Block implements IItemGroupProvider {
    public BlockMudBlock() {
        super(Block.Properties.create(Material.GOURD).hardnessAndResistance(1f, 1f).sound(SoundType.GROUND));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }
}