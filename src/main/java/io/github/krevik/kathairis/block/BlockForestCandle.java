package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import io.github.krevik.kathairis.init.ModItemGroups;
import io.github.krevik.kathairis.util.IItemGroupProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
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

public class BlockForestCandle extends BlockKathairisPlant implements IItemGroupProvider {
    public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", EnumType.class);
    public BlockForestCandle() {
        super(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0).tickRandomly().doesNotBlockMovement().lightValue(7));
        this.setDefaultState(this.stateContainer.getBaseState().with(VARIANT, EnumType.BOTTOM));
    }

    @Override
    public ItemGroup getItemGroup() {
        return ModItemGroups.PLANTS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) {
        if(state.get(VARIANT)== EnumType.BOTTOM){
            return VoxelShapes.create(0,0,0,1,2,1);
        }else{
            return VoxelShapes.create(0,-1,0,1,1,1);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if(state== ModBlocks.FOREST_CANDLE.getDefaultState().with(VARIANT, EnumType.UPPER)){
            return worldIn.getBlockState(pos.down())==ModBlocks.FOREST_CANDLE.getDefaultState();
        }else{
            boolean b3 = this.isValidGround(worldIn.getBlockState(pos.down()),worldIn,pos.down());
            return b3&&(worldIn.isAirBlock(pos.up())||worldIn.getBlockState(pos.up())== ModBlocks.FOREST_CANDLE.getDefaultState().with(VARIANT, EnumType.UPPER));
        }
    }


    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block p_189540_4_, BlockPos p_189540_5_, boolean isMoving) {
        if (!isValidPosition(state, world, pos)) {
            if(world.getBlockState(pos.down()).getBlock()==this){
                world.destroyBlock(pos.down(),false);
                world.destroyBlock(pos,false);
            }
            if(world.getBlockState(pos.up()).getBlock()==this){
                world.removeBlock(pos.up(),false);
                world.destroyBlock(pos,false);
            }
        }else{
            if(state==ModBlocks.FOREST_CANDLE.getDefaultState()){
                if(world.getBlockState(pos.up())!=ModBlocks.FOREST_CANDLE.getDefaultState().with(VARIANT, EnumType.UPPER)&&world.getBlockState(pos.up()).getBlock()!= Blocks.AIR){
                    world.removeBlock(pos,false);
                }
            }else{
                if(world.getBlockState(pos.down())!=ModBlocks.FOREST_CANDLE.getDefaultState()){
                    world.removeBlock(pos,false);
                }
            }

        }
    }



    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {

    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld wo, BlockPos pos, BlockPos facingPos) {
        if(stateIn==ModBlocks.FOREST_CANDLE.getDefaultState()) {
            if (wo.isAirBlock(pos.up())) {
                wo.setBlockState(pos.up(), ModBlocks.FOREST_CANDLE.getDefaultState().with(VARIANT, EnumType.UPPER), 2);
            }
        }
        return super.updatePostPlacement(stateIn, facing, facingState, wo, pos, facingPos);
    }

    @Override
    public void onBlockPlacedBy(World wo, BlockPos pos, BlockState state, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if(state==ModBlocks.FOREST_CANDLE.getDefaultState()) {
            if (wo.isAirBlock(pos.up())) {
                wo.setBlockState(pos.up(), ModBlocks.FOREST_CANDLE.getDefaultState().with(VARIANT, EnumType.UPPER));
            }
        }
        super.onBlockPlacedBy(wo, pos, state, p_180633_4_, p_180633_5_);
    }

    @Override
    public void onBlockAdded(BlockState p_196259_1_, World wo, BlockPos pos, BlockState p_196259_4_, boolean isMoving) {
        if(p_196259_1_==ModBlocks.FOREST_CANDLE.getDefaultState()) {
            if (wo.isAirBlock(pos.up())) {
                wo.setBlockState(pos.up(), ModBlocks.FOREST_CANDLE.getDefaultState().with(VARIANT, EnumType.UPPER));
            }
        }
        super.onBlockAdded(p_196259_1_, wo, pos, p_196259_4_, isMoving);
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
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        super.fillStateContainer(p_206840_1_);
        p_206840_1_.add(VARIANT);
    }
    public enum EnumType implements IStringSerializable
    {
        BOTTOM(0,"bottom"),
        UPPER(1,"upper");

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
