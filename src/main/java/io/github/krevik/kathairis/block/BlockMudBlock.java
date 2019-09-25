package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockMudBlock extends Block implements IItemGroupProvider {
    public BlockMudBlock() {
        super(Block.Properties.create(Material.GOURD).hardnessAndResistance(1f, 1f).sound(SoundType.GROUND));
    }
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.BUILDING_BLOCKS;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }


    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.setMotionMultiplier(state, new Vec3d(0.25D, (double)0.05F, 0.25D));
    }
}