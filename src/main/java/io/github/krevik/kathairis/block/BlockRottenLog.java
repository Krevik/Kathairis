package io.github.krevik.kathairis.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * @author Krevik
 */
public class BlockRottenLog extends Block {
    public BlockRottenLog(Properties p_i48440_1_) {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(5f));
    }

    @Nonnull
    @Override
    public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
        return null;
    }

    @Override
    public boolean canSilkHarvest(IBlockState p_canSilkHarvest_1_, IWorldReader p_canSilkHarvest_2_, BlockPos p_canSilkHarvest_3_, EntityPlayer p_canSilkHarvest_4_) {
        return true;
    }
}
