package mod.krevik.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockMysticFenceGate extends BlockMysticHorizontal
{
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");
    protected static final AxisAlignedBB AABB_HITBOX_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB AABB_HITBOX_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_HITBOX_ZAXIS_INWALL = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
    protected static final AxisAlignedBB AABB_HITBOX_XAXIS_INWALL = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
    protected static final AxisAlignedBB AABB_COLLISION_BOX_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
    protected static final AxisAlignedBB AABB_COLLISION_BOX_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);

    public BlockMysticFenceGate(String Name,Material mat,CreativeTabs tab,float hardness,float resistance,SoundType type)
    {
        super(Name, mat, tab, hardness, resistance, type);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, Boolean.valueOf(false)).withProperty(POWERED, Boolean.valueOf(false)).withProperty(IN_WALL, Boolean.valueOf(false)));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        state = this.getActualState(state, source, pos);

        if (state.getValue(IN_WALL).booleanValue())
        {
            return state.getValue(FACING).getAxis() == EnumFacing.Axis.X ? AABB_HITBOX_XAXIS_INWALL : AABB_HITBOX_ZAXIS_INWALL;
        }
        else
        {
            return state.getValue(FACING).getAxis() == EnumFacing.Axis.X ? AABB_HITBOX_XAXIS : AABB_HITBOX_ZAXIS;
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        EnumFacing.Axis enumfacing$axis = state.getValue(FACING).getAxis();

        if (enumfacing$axis == EnumFacing.Axis.Z && (canFenceGateConnectTo(worldIn, pos, EnumFacing.WEST) || canFenceGateConnectTo(worldIn, pos, EnumFacing.EAST)) || enumfacing$axis == EnumFacing.Axis.X && (canFenceGateConnectTo(worldIn, pos, EnumFacing.NORTH) || canFenceGateConnectTo(worldIn, pos, EnumFacing.SOUTH)))
        {
            state = state.withProperty(IN_WALL, Boolean.valueOf(true));
        }

        return state;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).getMaterial().isSolid() && super.canPlaceBlockAt(worldIn, pos);
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if (blockState.getValue(OPEN).booleanValue())
        {
            return NULL_AABB;
        }
        else
        {
            return blockState.getValue(FACING).getAxis() == EnumFacing.Axis.Z ? AABB_COLLISION_BOX_ZAXIS : AABB_COLLISION_BOX_XAXIS;
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getValue(OPEN).booleanValue();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        boolean flag = worldIn.isBlockPowered(pos);
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, Boolean.valueOf(flag)).withProperty(POWERED, Boolean.valueOf(flag)).withProperty(IN_WALL, Boolean.valueOf(false));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (state.getValue(OPEN).booleanValue())
        {
            state = state.withProperty(OPEN, Boolean.valueOf(false));
            worldIn.setBlockState(pos, state, 10);
        }
        else
        {
            EnumFacing enumfacing = EnumFacing.fromAngle((double)playerIn.rotationYaw);

            if (state.getValue(FACING) == enumfacing.getOpposite())
            {
                state = state.withProperty(FACING, enumfacing);
            }

            state = state.withProperty(OPEN, Boolean.valueOf(true));
            worldIn.setBlockState(pos, state, 10);
        }

        worldIn.playEvent(playerIn, state.getValue(OPEN).booleanValue() ? 1008 : 1014, pos, 0);
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            boolean flag = worldIn.isBlockPowered(pos);

            if (state.getValue(POWERED).booleanValue() != flag)
            {
                worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.valueOf(flag)).withProperty(OPEN, Boolean.valueOf(flag)), 2);

                if (state.getValue(OPEN).booleanValue() != flag)
                {
                    worldIn.playEvent(null, flag ? 1008 : 1014, pos, 0);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }


    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta)).withProperty(OPEN, Boolean.valueOf((meta & 4) != 0)).withProperty(POWERED, Boolean.valueOf((meta & 8) != 0));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();

        if (state.getValue(POWERED).booleanValue())
        {
            i |= 8;
        }

        if (state.getValue(OPEN).booleanValue())
        {
            i |= 4;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, OPEN, POWERED, IN_WALL);
    }

    /* ======================================== FORGE START ======================================== */

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block connector = world.getBlockState(pos.offset(facing)).getBlock();
        return connector instanceof BlockFence || connector instanceof BlockWall|| connector instanceof BlockMysticFence || connector instanceof BlockMysticWall;
    }

    private boolean canFenceGateConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        Block block = world.getBlockState(pos.offset(facing)).getBlock();
        return block.canBeConnectedTo(world, pos.offset(facing), facing.getOpposite());
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        if (face != EnumFacing.UP && face != EnumFacing.DOWN)
        {
            return state.getValue(FACING).getAxis() == face.rotateY().getAxis() ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.UNDEFINED;
        }
        else
        {
            return BlockFaceShape.UNDEFINED;
        }
    }
}