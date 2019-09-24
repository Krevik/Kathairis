package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockIronGoldBlock extends Block implements IItemGroupProvider {
    public BlockIronGoldBlock() {
        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(4f, 4f).sound(SoundType.METAL));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }
}