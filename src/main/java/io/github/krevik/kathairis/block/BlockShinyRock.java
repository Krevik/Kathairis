package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockShinyRock extends Block implements IItemGroupProvider {
    public BlockShinyRock() {
        super(Block.Properties.create(Material.GLASS).hardnessAndResistance(2.5f, 2.5f).lightValue(12).sound(SoundType.GLASS));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }
}
