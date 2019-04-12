package io.github.krevik.kathairis.block;

import io.github.krevik.kathairis.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import javax.annotation.Nullable;

//TODO cannot place it O.o
public class BlockPurplePalm extends BlockKathairisPlant {
    public static final EnumProperty<EnumType> VARIANT = EnumProperty.create("variant", BlockPurplePalm.EnumType.class);
    public BlockPurplePalm() {
        this.setDefaultState(this.stateContainer.getBaseState().with(VARIANT, BlockPurplePalm.EnumType.NORMAL));
    }
    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        if(state.get(VARIANT)==BlockPurplePalm.EnumType.NORMAL){
            return VoxelShapes.create(0,0,0,1,2,1);
        }else{
            return VoxelShapes.create(0,-1,0,1,1,1);
        }
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        if(state==ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, BlockPurplePalm.EnumType.AIR)){
            return worldIn.getBlockState(blockpos)==ModBlocks.PURPLE_PALM.getDefaultState();
        }else{
            boolean b3 = this.isValidGround(worldIn.getBlockState(blockpos),worldIn,blockpos);
            return b3&&(worldIn.isAirBlock(pos.up()));
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block p_189540_4_, BlockPos p_189540_5_) {
        if (!isValidPosition(state, world, pos)) {
            if(world.getBlockState(pos.down()).getBlock()==this){
                world.destroyBlock(pos.down(),true);
            }
            if(world.getBlockState(pos.up()).getBlock()==this){
                world.removeBlock(pos.up());
            }
            world.removeBlock(pos);
        }
    }



    @Override
    public void onNeighborChange(IBlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {

    }

    @Override
    public void onBlockPlacedBy(World wo, BlockPos pos, IBlockState state, @Nullable EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
        if(wo.isAirBlock(pos.up())) {
            wo.setBlockState(pos.up(), ModBlocks.PURPLE_PALM.getDefaultState().with(VARIANT, BlockPurplePalm.EnumType.AIR));
        }
        super.onBlockPlacedBy(wo, pos, state, p_180633_4_, p_180633_5_);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(world, pos, state, player);
        if(world.getBlockState(pos.down()).getBlock()==this){
            world.destroyBlock(pos.down(),true);
        }
        if(world.getBlockState(pos.up()).getBlock()==this){
            world.removeBlock(pos.up());
        }
    }

    @Override
    protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == ModBlocks.WEATHERED_ROCK||block == ModBlocks.KATHAIRIS_STONE||block == ModBlocks.KATHAIRIS_COBBLESTONE||
                block== Blocks.STONE||block == Blocks.COBBLESTONE||block == ModBlocks.HARDENED_WEATHERED_ROCK;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, IBlockState> p_206840_1_) {
        super.fillStateContainer(p_206840_1_);
        p_206840_1_.add(VARIANT);
    }
    public enum EnumType implements IStringSerializable
    {
        NORMAL(0,"normal"),
        AIR(1,"air");

        private static final BlockPurplePalm.EnumType[] META_LOOKUP = new BlockPurplePalm.EnumType[values().length];
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

        public static BlockPurplePalm.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockPurplePalm.EnumType blockstone$enumtype : values())
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