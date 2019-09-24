package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockMysticGemBlock extends Block implements IItemGroupProvider {
    public BlockMysticGemBlock() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(4f, 4f).sound(SoundType.METAL));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }
}
