package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Direction;
import net.minecraft.util.datafix.fixes.LeavesFix;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.*;

/**
 * @author Krevik
 */
public class BlockKathairisLeaves extends LeavesBlock implements IItemGroupProvider {

	public BlockKathairisLeaves() {
		super(Properties.create(Material.PLANTS).hardnessAndResistance(0.2f).sound(SoundType.PLANT).tickRandomly().func_226896_b_());
		this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, Integer.valueOf(7)).with(PERSISTENT, Boolean.valueOf(false)));
	}

	@Override
	public ItemGroup getItemGroup() {
		return ModItemGroups.BUILDING_BLOCKS;
	}


	@Override
	public boolean isSideInvisible(BlockState p_200122_1_, BlockState p_200122_2_, Direction p_200122_3_) {
		return false;
	}

}
