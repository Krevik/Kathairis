package mod.krevik.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMysticWall extends BaseBlock
{
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[] {new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D), new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 0.6875D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D), new AxisAlignedBB(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 0.875D, 0.6875D), new AxisAlignedBB(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D), new AxisAlignedBB(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};
    protected static final AxisAlignedBB[] CLIP_AABB_BY_INDEX = new AxisAlignedBB[] {AABB_BY_INDEX[0].setMaxY(1.5D), AABB_BY_INDEX[1].setMaxY(1.5D), AABB_BY_INDEX[2].setMaxY(1.5D), AABB_BY_INDEX[3].setMaxY(1.5D), AABB_BY_INDEX[4].setMaxY(1.5D), AABB_BY_INDEX[5].setMaxY(1.5D), AABB_BY_INDEX[6].setMaxY(1.5D), AABB_BY_INDEX[7].setMaxY(1.5D), AABB_BY_INDEX[8].setMaxY(1.5D), AABB_BY_INDEX[9].setMaxY(1.5D), AABB_BY_INDEX[10].setMaxY(1.5D), AABB_BY_INDEX[11].setMaxY(1.5D), AABB_BY_INDEX[12].setMaxY(1.5D), AABB_BY_INDEX[13].setMaxY(1.5D), AABB_BY_INDEX[14].setMaxY(1.5D), AABB_BY_INDEX[15].setMaxY(1.5D)};

    public BlockMysticWall(Block modelBlock,String Name,Material mat,CreativeTabs tab,float hardness,float resistance,SoundType type)
    {
        super(Name,mat,tab,hardness,resistance,type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);
        return AABB_BY_INDEX[getAABBIndex(state)];
    }

    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        {
            state = this.getActualState(state, worldIn, pos);
        }

        addCollisionBoxToList(pos, entityBox, collidingBoxes, CLIP_AABB_BY_INDEX[getAABBIndex(state)]);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        blockState = this.getActualState(blockState, worldIn, pos);
        return CLIP_AABB_BY_INDEX[getAABBIndex(blockState)];
    }

    private static int getAABBIndex(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(NORTH)).booleanValue())
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(EAST)).booleanValue())
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(SOUTH)).booleanValue())
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (((Boolean)state.getValue(WEST)).booleanValue())
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Determines if an entity can path through this block
     */
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing p_176253_3_)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, p_176253_3_);
        boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE_THICK || blockfaceshape == BlockFaceShape.MIDDLE_POLE && (block instanceof BlockFenceGate || block instanceof BlockMysticFenceGate);
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block p_194143_0_)
    {
        return isExceptBlockForAttachWithPiston(p_194143_0_) || p_194143_0_ == Blocks.BARRIER || p_194143_0_ == Blocks.MELON_BLOCK || p_194143_0_ == Blocks.PUMPKIN || p_194143_0_ == Blocks.LIT_PUMPKIN;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.DOWN ? super.shouldSideBeRendered(blockState, blockAccess, pos, side) : true;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        boolean flag =  canWallConnectTo(worldIn, pos, EnumFacing.NORTH);
        boolean flag1 = canWallConnectTo(worldIn, pos, EnumFacing.EAST);
        boolean flag2 = canWallConnectTo(worldIn, pos, EnumFacing.SOUTH);
        boolean flag3 = canWallConnectTo(worldIn, pos, EnumFacing.WEST);
        boolean flag4 = flag && !flag1 && flag2 && !flag3 || !flag && flag1 && !flag2 && flag3;
        return state.withProperty(UP, Boolean.valueOf(!flag4 || !worldIn.isAirBlock(pos.up()))).withProperty(NORTH, Boolean.valueOf(flag)).withProperty(EAST, Boolean.valueOf(flag1)).withProperty(SOUTH, Boolean.valueOf(flag2)).withProperty(WEST, Boolean.valueOf(flag3));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {UP, NORTH, EAST, WEST, SOUTH});
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE_THICK : BlockFaceShape.CENTER_BIG;
    }

    /* ======================================== FORGE START ======================================== */

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block connector = world.getBlockState(pos.offset(facing)).getBlock();
        return connector instanceof BlockMysticWall || connector instanceof BlockFenceGate;
    }

    private boolean canWallConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
    }

    /* ======================================== FORGE END ======================================== */

}