package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

import static io.github.krevik.kathairis.init.ModBlocks.BRINE_PUSTULE;

/**
 * @author Krevik
 */
public class BlockBrinePustule extends BlockKathairisPlant implements IGrowable {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty AGE = BlockStateProperties.STAGE_0_1;


    public BlockBrinePustule() {
        super(Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT));
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(AGE,0));
    }

    @Override
    public void onPlantGrow(BlockState state, IWorld world, BlockPos pos, BlockPos source) {

    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random rand) {
        handleFacing(state, worldIn, pos);
        if (!this.isStoneAround(worldIn, pos)) {
            this.dropBlock(worldIn, pos, state);
        }
        if(rand.nextInt(100)==0) {
            if (state.get(AGE) < 1) {
                worldIn.setBlockState(pos,state.with(AGE,state.get(AGE)+1));
            }
        }
    }


    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction facing = context.getFace();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (isStone(world.getBlockState(pos.east()))) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, Direction.EAST);
        } else if (isStone(world.getBlockState(pos.west()))) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, Direction.WEST);
        } else if (isStone(world.getBlockState(pos.south()))) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, Direction.SOUTH);
        } else if (isStone(world.getBlockState(pos.north()))) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, Direction.NORTH);
        } else {
            return BRINE_PUSTULE.getDefaultState().with(FACING, Direction.WEST);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
        builder.add(AGE);
    }

    public boolean isStoneAround(IWorldReader worldIn, BlockPos pos) {
        boolean is = false;
        ArrayList<BlockPos> blockPoses = new ArrayList<>();
        blockPoses.add(pos.west());
        blockPoses.add(pos.east());
        blockPoses.add(pos.north());
        blockPoses.add(pos.south());


        for(BlockPos poses:blockPoses){
            BlockState state = worldIn.getBlockState(poses);
            if(state.getBlock() instanceof BlockKathairisStone || state.getMaterial() == Material.ROCK){
                is=true;
            }
        }

        return is;
    }

    private void dropBlock(World worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        spawnAsEntity(worldIn, pos, new ItemStack(this));
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return new ItemStack(ModItems.MINERAL_FRUIT,1);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        handleFacing(state, worldIn, pos);
        if (!this.isStoneAround(worldIn, pos)) {
            this.dropBlock(worldIn, pos, state);
        }
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return isStoneAround(worldIn, pos);
    }

    private void handleFacing(BlockState state, IWorld world, BlockPos pos) {
        if (isStone(world.getBlockState(pos.east()))) {
            world.setBlockState(pos, state.with(FACING, Direction.EAST), 2);
        }
        if (isStone(world.getBlockState(pos.west()))) {
            world.setBlockState(pos, state.with(FACING, Direction.WEST), 2);
        }
        if (isStone(world.getBlockState(pos.south()))) {
            world.setBlockState(pos, state.with(FACING, Direction.SOUTH), 2);
        }
        if (isStone(world.getBlockState(pos.north()))) {
            world.setBlockState(pos, state.with(FACING, Direction.NORTH), 2);
        }
    }

    private boolean isStone(BlockState state) {
        return state.getMaterial()==Material.ROCK;
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState iBlockState, boolean b) {
        return iBlockState.get(AGE)<1;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState iBlockState) {
        return iBlockState.get(AGE)<1;
    }

    @Override
    public void grow(World world, Random random, BlockPos blockPos, BlockState iBlockState) {
        world.setBlockState(blockPos,iBlockState.with(AGE,1));
    }
}
