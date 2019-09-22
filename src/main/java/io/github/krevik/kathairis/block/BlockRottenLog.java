package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Krevik
 */
public class BlockRottenLog extends Block {
    public BlockRottenLog(Properties p_i48440_1_) {
        super(Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(5f));
    }
}
