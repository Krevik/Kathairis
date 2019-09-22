package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPurplePalm extends BlockKathairisPlant {
    public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", EnumType.class);
    public BlockPurplePalm() {
        this.setDefaultState(this.stateContainer.getBaseState().with(VARIANT, EnumType.NORMAL));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        if(state.get(VARIANT)== EnumType.NORMAL){
            return VoxelShapes.create(0,0,0,1,2,1);
        }else{
            return VoxelShapes.create(0,-1,0,1,1,1);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if(state== ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, EnumType.AIR)){
            return worldIn.getBlockState(pos.down())==ModBlocks.PURPLE_PALM.getDefaultState();
        }else{
            boolean b3 = this.isValidGround(worldIn.getBlockState(pos.down()),worldIn,pos.down());
            return b3&&(worldIn.isAirBlock(pos.up())||worldIn.getBlockState(pos.up())== ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, EnumType.AIR));
        }
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos blockPos, boolean isMoving) {
        if (!isValidPosition(state, world, pos)) {
            if(world.getBlockState(pos.down()).getBlock()==this){
                world.destroyBlock(pos.down(),false);
                world.destroyBlock(pos,false);
            }
            if(world.getBlockState(pos.up()).getBlock()==this){
                world.removeBlock(pos.up(),isMoving);
                world.destroyBlock(pos,false);
            }
        }else{
            if(state==ModBlocks.PURPLE_PALM.getDefaultState()){
                if(world.getBlockState(pos.up())!=ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, EnumType.AIR)&&world.getBlockState(pos.up()).getBlock()!=Blocks.AIR){
                    world.removeBlock(pos,isMoving);
                }
            }else{
                if(world.getBlockState(pos.down())!=ModBlocks.PURPLE_PALM.getDefaultState()){
                    world.removeBlock(pos,isMoving);
                }
            }

        }
    }



    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {

    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld wo, BlockPos pos, BlockPos facingPos) {
        if(stateIn==ModBlocks.PURPLE_PALM.getDefaultState()) {
            if (wo.isAirBlock(pos.up())) {
                wo.setBlockState(pos.up(), ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, EnumType.AIR), 2);
            }
        }
        return super.updatePostPlacement(stateIn, facing, facingState, wo, pos, facingPos);
    }

    @Override
    public void onBlockPlacedBy(World wo, BlockPos pos, BlockState state, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if(state==ModBlocks.PURPLE_PALM.getDefaultState()) {
            if (wo.isAirBlock(pos.up())) {
                wo.setBlockState(pos.up(), ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, EnumType.AIR));
            }
        }
        super.onBlockPlacedBy(wo, pos, state, p_180633_4_, p_180633_5_);
    }

    @Override
    public void onBlockAdded(BlockState p_220082_1_, World wo, BlockPos pos, BlockState p_220082_4_, boolean p_220082_5_) {
        if(p_220082_1_==ModBlocks.PURPLE_PALM.getDefaultState()) {
            if (wo.isAirBlock(pos.up())) {
                wo.setBlockState(pos.up(), ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, EnumType.AIR));
            }
        }
        super.onBlockAdded(p_220082_1_, wo, pos, p_220082_4_,p_220082_5_);
    }


    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(world.getBlockState(pos.down()).getBlock()==this){
            world.destroyBlock(pos.down(),true);
        }
        if(world.getBlockState(pos.up()).getBlock()==this){
            world.destroyBlock(pos.up(),false);
        }
        super.onBlockHarvested(world,pos,state,player);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == ModBlocks.WEATHERED_ROCK||block == ModBlocks.KATHAIRIS_STONE||block == ModBlocks.KATHAIRIS_COBBLESTONE||
                block== Blocks.STONE||block == Blocks.COBBLESTONE||block == ModBlocks.HARDENED_WEATHERED_ROCK;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        super.fillStateContainer(p_206840_1_);
        p_206840_1_.add(VARIANT);
    }
    public enum EnumType implements IStringSerializable
    {
        NORMAL(0,"normal"),
        AIR(1,"air");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name;

        EnumType(int p_i46384_3_, String Name)
        {
            this.meta = p_i46384_3_;
            name=Name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public static EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }

        @Override
        public String getName() {
            return name;
        }
    }
}