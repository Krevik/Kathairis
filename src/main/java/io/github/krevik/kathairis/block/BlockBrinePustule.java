package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemBoneMeal;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReaderBase;
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

    public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
    public static final IntegerProperty AGE = BlockStateProperties.STAGE_0_1;


    public BlockBrinePustule() {
        super(Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT));
        setDefaultState(getDefaultState().with(FACING, EnumFacing.NORTH).with(AGE,0));
    }

    @Override
    public void onPlantGrow(IBlockState state, IWorld world, BlockPos pos, BlockPos source) {

    }

    @Override
    public void tick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
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
    public IBlockState getStateForPlacement(BlockItemUseContext context) {
        EnumFacing facing = context.getFace();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (isStone(world.getBlockState(pos.east()).getBlock())) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, EnumFacing.EAST);
        } else if (isStone(world.getBlockState(pos.west()).getBlock())) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, EnumFacing.WEST);
        } else if (isStone(world.getBlockState(pos.south()).getBlock())) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, EnumFacing.SOUTH);
        } else if (isStone(world.getBlockState(pos.north()).getBlock())) {
            return BRINE_PUSTULE.getDefaultState().with(FACING, EnumFacing.NORTH);
        } else {
            return BRINE_PUSTULE.getDefaultState().with(FACING, EnumFacing.WEST);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING);
        builder.add(AGE);
    }

    public boolean isStoneAround(IWorldReaderBase worldIn, BlockPos pos) {
        boolean is = false;
        ArrayList<BlockPos> blockPoses = new ArrayList<>();
        blockPoses.add(pos.west());
        blockPoses.add(pos.east());
        blockPoses.add(pos.north());
        blockPoses.add(pos.south());

        for(BlockPos poses:blockPoses){
            IBlockState state = worldIn.getBlockState(poses);
            if(state.getBlock() instanceof BlockKathairisStone || state.getBlock() instanceof BlockStone){
                is=true;
            }
        }

        return is;
    }

    private void dropBlock(World worldIn, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        spawnAsEntity(worldIn, pos, new ItemStack(this));
    }

    @Override
    public IItemProvider getItemDropped(IBlockState state, World world, BlockPos pos, int fortune) {
            return ModItems.MINERAL_FRUIT;
    }

    @Override
    public int getItemsToDropCount(IBlockState state, int fortune, World world, BlockPos pos, Random random) {
        if(state.get(AGE)==0){
            return 1;
        }else{
            return 2+random.nextInt(3)+fortune;
        }
    }

    @Override
    public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        handleFacing(stateIn, worldIn, currentPos);
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
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
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
        return isStoneAround(worldIn, pos);
    }

    private void handleFacing(IBlockState state, IWorld world, BlockPos pos) {
        if (isStone(world.getBlockState(pos.east()).getBlock())) {
            world.setBlockState(pos, state.with(FACING, EnumFacing.EAST), 2);
        }
        if (isStone(world.getBlockState(pos.west()).getBlock())) {
            world.setBlockState(pos, state.with(FACING, EnumFacing.WEST), 2);
        }
        if (isStone(world.getBlockState(pos.south()).getBlock())) {
            world.setBlockState(pos, state.with(FACING, EnumFacing.SOUTH), 2);
        }
        if (isStone(world.getBlockState(pos.north()).getBlock())) {
            world.setBlockState(pos, state.with(FACING, EnumFacing.NORTH), 2);
        }
    }

    private boolean isStone(Block block) {
        return block instanceof BlockStone || block instanceof BlockKathairisStone;
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, IBlockState iBlockState, boolean b) {
        return iBlockState.get(AGE)<1;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, IBlockState iBlockState) {
        return iBlockState.get(AGE)<1;
    }

    @Override
    public void grow(World world, Random random, BlockPos blockPos, IBlockState iBlockState) {
        world.setBlockState(blockPos,iBlockState.with(AGE,1));
    }
}
