package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockWeatheredRock extends Block implements IItemGroupProvider {
    public BlockWeatheredRock() {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 1.5f).sound(SoundType.STONE));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }
}
