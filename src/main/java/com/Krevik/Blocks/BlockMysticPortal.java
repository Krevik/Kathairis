package com.Krevik.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Dimension.TileEntityKether;
import com.Krevik.Entities.EntityStrangeWanderer;
import com.Krevik.Main.KCore;
import com.Krevik.Main.KathairisMaterials;
import com.Krevik.Particles.DynamicMovementParticle;
import com.Krevik.Particles.KetherPortalParticle;
import com.google.common.cache.LoadingCache;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMysticPortal extends BaseBlock
{
    public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class, EnumFacing.Axis.X, EnumFacing.Axis.Z);
    protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

    public BlockMysticPortal(String Name)
    {
        super(Name, KathairisMaterials.KATHAIRISPORTAL, null, -1, -1, SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
        this.setTickRandomly(true);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing.Axis)state.getValue(AXIS))
        {
            case X:
                return X_AABB;
            case Y:
            default:
                return Y_AABB;
            case Z:
                return Z_AABB;
        }
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getBoolean("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getDifficultyId())
        {
            int i = pos.getY();
            BlockPos blockpos;

            for (blockpos = pos; !worldIn.getBlockState(blockpos).isTopSolid() && blockpos.getY() > 0; blockpos = blockpos.down())
            {
                ;
            }

            if (i > 0 && !worldIn.getBlockState(blockpos.up()).isNormalCube())
            {
                Entity entity = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPigZombie.class), (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 1.1D, (double)blockpos.getZ() + 0.5D);

                if (entity != null)
                {
                    entity.timeUntilPortal = entity.getPortalCooldown();
                }
            }
        }
        
        	
        
        	if(worldIn.provider.getDimension()==KCore.DIMENSION_ID) {
            List<EntityStrangeWanderer> e = worldIn.getEntitiesWithinAABB(EntityStrangeWanderer.class, new AxisAlignedBB(pos.getX() - 15, pos.getY() - 15, pos.getZ() - 15, pos.getX()  + 15, pos.getY() + 15, pos.getZ() + 15));
            if(e.size()==0) {
            	if(!worldIn.isRemote) {
            	int X=pos.getX()+rand.nextInt(4)-rand.nextInt(4);
            	int Y=pos.getY();
            	int Z = pos.getZ()+rand.nextInt(4)-rand.nextInt(4);
            	if(worldIn.isAirBlock(new BlockPos(X,Y,Z))&&worldIn.isAirBlock(new BlockPos(X,Y,Z).up())) {
                	EntityStrangeWanderer esw = new EntityStrangeWanderer(worldIn);
                	esw.setPosition(X,Y,Z);
                	worldIn.spawnEntity(esw);
            	}

            	}
            }
        }
        	for(int x=0;x<1+rand.nextInt(4);x++) {
        		updateBlocksAroundPortal(worldIn, pos, state, rand);
        	}

    }
    
    private IBlockState pickUpRandomFlowerStateForKether(Random random) {
    	IBlockState[] flowers = {KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),KCore.MysticNightFlower.getDefaultState(),
    			KCore.ButterflyFlower.getDefaultState(),KCore.MysticFlower.getDefaultState(),KCore.MysticFungus.getDefaultState(),
    			KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),
    			KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),
    			KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),KCore.MysticMiniGrass.getDefaultState(),KCore.MysticTallGrass.getDefaultState(),
    			KCore.MysticSapling.getDefaultState(),KCore.EyePlant.getDefaultState(),KCore.MagicBeans.getDefaultState(),KCore.GooseberryBlock.getDefaultState(),
    			KCore.SteppedSucculent.getDefaultState()
    			};
    	return flowers[random.nextInt(flowers.length)];
    }
    
    private IBlockState pickUpRandomFlowerState(Random random) {
    	ArrayList<IBlockState> flowers = new ArrayList<IBlockState>();
        for (BlockFlower.EnumFlowerType type : BlockFlower.EnumFlowerType.values())
        {
            if (type.getBlockType() == BlockFlower.EnumFlowerColor.YELLOW) continue;
            if (type == BlockFlower.EnumFlowerType.BLUE_ORCHID) type = BlockFlower.EnumFlowerType.POPPY;
            flowers.add(net.minecraft.init.Blocks.RED_FLOWER.getDefaultState().withProperty(net.minecraft.init.Blocks.RED_FLOWER.getTypeProperty(), type));
        }
        for(int x=0;x<=10;x++) {
        	flowers.add(Blocks.TALLGRASS.getDefaultState());
        }
    	return flowers.get(this.RANDOM.nextInt(flowers.size()));
    }
    
    
    private void updateBlocksAroundPortal(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	if(worldIn.provider.getDimension()==KCore.DIMENSION_ID) {
    		int radius=5;
    		int shiftX=+rand.nextInt(radius+1)-rand.nextInt(radius+1);
    		int shiftY=-rand.nextInt(radius+1)+rand.nextInt(radius+1);
    		int shiftZ=+rand.nextInt(radius+1)-rand.nextInt(radius+1);
    			int randomX=pos.getX()+shiftX;
    			int randomY=pos.getY()+shiftY;
    			int randomZ=pos.getZ()+shiftZ;
    			if((MathHelper.abs(randomX-pos.getX())*MathHelper.abs(randomX-pos.getX()))+
    					(MathHelper.abs(randomZ-pos.getZ())*MathHelper.abs(randomZ-pos.getZ()))<=radius*radius) {
    			BlockPos tmp = new BlockPos(randomX,randomY,randomZ);
    			if(worldIn.getBlockState(tmp)==KCore.CorruptedGrass.getDefaultState()) {
    				worldIn.setBlockState(tmp, Blocks.GRASS.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==KCore.CorruptedDirt.getDefaultState()) {
    				worldIn.setBlockState(tmp, Blocks.DIRT.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==KCore.MythicStone.getDefaultState()) {
    				worldIn.setBlockState(tmp, Blocks.STONE.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==KCore.ForgottenSand.getDefaultState()) {
    				worldIn.setBlockState(tmp, Blocks.SAND.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==Blocks.GRASS.getDefaultState()&&worldIn.getBlockState(tmp.up())==Blocks.AIR.getDefaultState()) {
    				worldIn.setBlockState(tmp.up(), pickUpRandomFlowerState(rand),2);
    			}
    			}
    			if(rand.nextInt(2)==0) {
    				this.updateBlocksAroundPortal(worldIn, pos, state, rand);
    			}
    	}else {
    		int radius=5;
    			int randomX=pos.getX()+rand.nextInt(radius+1)-rand.nextInt(radius+1);
    			int randomY=pos.getY()-rand.nextInt(radius+1)+rand.nextInt(radius+1);
    			int randomZ=pos.getZ()+rand.nextInt(radius+1)-rand.nextInt(radius+1);
    			BlockPos tmp = new BlockPos(randomX,randomY,randomZ);
    			if((MathHelper.abs(randomX-pos.getX())*MathHelper.abs(randomX-pos.getX()))+
    					(MathHelper.abs(randomZ-pos.getZ())*MathHelper.abs(randomZ-pos.getZ()))<=radius*radius) {
    				

    			if(worldIn.getBlockState(tmp)==Blocks.GRASS.getDefaultState()) {
    				worldIn.setBlockState(tmp, KCore.CorruptedGrass.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==Blocks.DIRT.getDefaultState()) {
    				worldIn.setBlockState(tmp, KCore.CorruptedDirt.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==Blocks.STONE.getDefaultState()) {
    				worldIn.setBlockState(tmp, KCore.MythicStone.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==Blocks.SAND.getDefaultState()) {
    				worldIn.setBlockState(tmp, KCore.ForgottenSand.getDefaultState(),2);
    			}
    			if(worldIn.getBlockState(tmp)==KCore.CorruptedGrass.getDefaultState()&&worldIn.getBlockState(tmp.up())==Blocks.AIR.getDefaultState()) {
    				worldIn.setBlockState(tmp.up(), pickUpRandomFlowerStateForKether(rand),2);
    			}
    			}
    	}
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public static int getMetaForAxis(EnumFacing.Axis axis)
    {
        if (axis == EnumFacing.Axis.X)
        {
            return 1;
        }
        else
        {
            return axis == EnumFacing.Axis.Z ? 2 : 0;
        }
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    public boolean trySpawnPortal(World worldIn, BlockPos pos)
    {
        BlockMysticPortal.Size blockportal$size = new BlockMysticPortal.Size(worldIn, pos, EnumFacing.Axis.X);

        if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0)
        {
            blockportal$size.placePortalBlocks();
            return true;
        }
        else
        {
            BlockMysticPortal.Size blockportal$size1 = new BlockMysticPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0)
            {
                blockportal$size1.placePortalBlocks();
                return true;
            }
            else
            {
                return false;
                
            }
        }
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);

        if (enumfacing$axis == EnumFacing.Axis.X)
        {
            BlockMysticPortal.Size blockportal$size = new BlockMysticPortal.Size(worldIn, pos, EnumFacing.Axis.X);

            if (!blockportal$size.isValid() || blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height)
            {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        else if (enumfacing$axis == EnumFacing.Axis.Z)
        {
            BlockMysticPortal.Size blockportal$size1 = new BlockMysticPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

            if (!blockportal$size1.isValid() || blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height)
            {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        pos = pos.offset(side);
        EnumFacing.Axis enumfacing$axis = null;

        if (blockState.getBlock() == this)
        {
            enumfacing$axis = (EnumFacing.Axis)blockState.getValue(AXIS);

            if (enumfacing$axis == null)
            {
                return false;
            }

            if (enumfacing$axis == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
            {
                return false;
            }

            if (enumfacing$axis == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
            {
                return false;
            }
        }

        boolean flag = blockAccess.getBlockState(pos.west()).getBlock() == this && blockAccess.getBlockState(pos.west(2)).getBlock() != this;
        boolean flag1 = blockAccess.getBlockState(pos.east()).getBlock() == this && blockAccess.getBlockState(pos.east(2)).getBlock() != this;
        boolean flag2 = blockAccess.getBlockState(pos.north()).getBlock() == this && blockAccess.getBlockState(pos.north(2)).getBlock() != this;
        boolean flag3 = blockAccess.getBlockState(pos.south()).getBlock() == this && blockAccess.getBlockState(pos.south(2)).getBlock() != this;
        boolean flag4 = flag || flag1 || enumfacing$axis == EnumFacing.Axis.X;
        boolean flag5 = flag2 || flag3 || enumfacing$axis == EnumFacing.Axis.Z;

        if (flag4 && side == EnumFacing.WEST)
        {
            return true;
        }
        else if (flag4 && side == EnumFacing.EAST)
        {
            return true;
        }
        else if (flag5 && side == EnumFacing.NORTH)
        {
            return true;
        }
        else
        {
            return flag5 && side == EnumFacing.SOUTH;
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {   
		if ((entityIn.getRidingEntity() == null) && ((entityIn instanceof EntityPlayerMP)))
		{
			
			EntityPlayerMP player1 = (EntityPlayerMP)entityIn;
		
			TileEntityKether.tele(player1);
		
		}


    }
    
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AXIS, (meta & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(100) == 0)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i)
        {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)((float)pos.getY() + rand.nextFloat());
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;

            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this)
            {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }
            else
            {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }

            KCore.cproxy.drawParticle(worldIn, new KetherPortalParticle(worldIn,d0,d1,d2,d3*0.1,d4*0.1,d5*0.1));
        }
        if(rand.nextInt(50)==0) {
            for (int i = 0; i < 4; ++i)
            {
	            double d0 = (double)((float)pos.getX() + rand.nextFloat());
	            double d1 = (double)((float)pos.getY() + rand.nextFloat());
	            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
	            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
	            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
	            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
	            int j = rand.nextInt(2) * 2 - 1;
	
	            if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this)
	            {
	                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
	                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
	            }
	            else
	            {
	                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
	                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
	            }
	
	            KCore.cproxy.drawParticle(worldIn, new DynamicMovementParticle(worldIn,d0,d1,d2,d3*0.01,d4*0.01,d5*0.01));
            }
        }
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return getMetaForAxis((EnumFacing.Axis)state.getValue(AXIS));
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        switch (rot)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:

                switch ((EnumFacing.Axis)state.getValue(AXIS))
                {
                    case X:
                        return state.withProperty(AXIS, EnumFacing.Axis.Z);
                    case Z:
                        return state.withProperty(AXIS, EnumFacing.Axis.X);
                    default:
                        return state;
                }

            default:
                return state;
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {AXIS});
    }

    public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos p_181089_2_)
    {
        EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
        BlockMysticPortal.Size blockportal$size = new BlockMysticPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.X);
        LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);

        if (!blockportal$size.isValid())
        {
            enumfacing$axis = EnumFacing.Axis.X;
            blockportal$size = new BlockMysticPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.Z);
        }

        if (!blockportal$size.isValid())
        {
            return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
        }
        else
        {
            int[] aint = new int[EnumFacing.AxisDirection.values().length];
            EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
            BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

            for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values())
            {
                BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

                for (int i = 0; i < blockportal$size.getWidth(); ++i)
                {
                    for (int j = 0; j < blockportal$size.getHeight(); ++j)
                    {
                        BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);

                        if (blockworldstate.getBlockState() != null && blockworldstate.getBlockState().getMaterial() != Material.AIR)
                        {
                            ++aint[enumfacing$axisdirection.ordinal()];
                        }
                    }
                }
            }

            EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;

            for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values())
            {
                if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()])
                {
                    enumfacing$axisdirection1 = enumfacing$axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos : blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1), EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP, loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
        }
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public static class Size
        {
            private final World world;
            private final EnumFacing.Axis axis;
            private final EnumFacing rightDir;
            private final EnumFacing leftDir;
            private int portalBlockCount;
            private BlockPos bottomLeft;
            private int height;
            private int width;

            public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_)
            {
                this.world = worldIn;
                this.axis = p_i45694_3_;

                if (p_i45694_3_ == EnumFacing.Axis.X)
                {
                    this.leftDir = EnumFacing.EAST;
                    this.rightDir = EnumFacing.WEST;
                }
                else
                {
                    this.leftDir = EnumFacing.NORTH;
                    this.rightDir = EnumFacing.SOUTH;
                }

                for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && this.isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down())
                {
                    ;
                }

                int i = this.getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;

                if (i >= 0)
                {
                    this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
                    this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);

                    if (this.width < 2 || this.width > 21)
                    {
                        this.bottomLeft = null;
                        this.width = 0;
                    }
                }

                if (this.bottomLeft != null)
                {
                    this.height = this.calculatePortalHeight();
                }
            }

            protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_)
            {
                int i;

                for (i = 0; i < 22; ++i)
                {
                    BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

                    if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()) != Blocks.STONE.getDefaultState())
                    {
                        break;
                    }
                }

                IBlockState block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i));
                return block == Blocks.STONE.getDefaultState() ? i : 0;
            }

            public int getHeight()
            {
                return this.height;
            }

            public int getWidth()
            {
                return this.width;
            }

            protected int calculatePortalHeight()
            {
                label56:

                for (this.height = 0; this.height < 21; ++this.height)
                {
                    for (int i = 0; i < this.width; ++i)
                    {
                        BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
                        Block block = this.world.getBlockState(blockpos).getBlock();

                        if (!this.isEmptyBlock(block))
                        {
                            break label56;
                        }

                        if (block == KCore.MysticPortal)
                        {
                            ++this.portalBlockCount;
                        }

                        if (i == 0)
                        {
                        	IBlockState state = this.world.getBlockState(blockpos.offset(this.leftDir));

                            if (state != Blocks.STONE.getDefaultState())
                            {
                                break label56;
                            }
                        }
                        else if (i == this.width - 1)
                        {
                        	IBlockState state = this.world.getBlockState(blockpos.offset(this.rightDir));

                            if (state != Blocks.STONE.getDefaultState())
                            {
                                break label56;
                            }
                        }
                    }
                }

                for (int j = 0; j < this.width; ++j)
                {
                    if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)) != Blocks.STONE.getDefaultState())
                    {
                        this.height = 0;
                        break;
                    }
                }

                if (this.height <= 21 && this.height >= 3)
                {
                    return this.height;
                }
                else
                {
                    this.bottomLeft = null;
                    this.width = 0;
                    this.height = 0;
                    return 0;
                }
            }

            protected boolean isEmptyBlock(Block blockIn)
            {
                return blockIn.getMaterial(blockIn.getDefaultState()) == Material.AIR || blockIn == Blocks.FIRE || blockIn == KCore.MysticPortal;
            }

            public boolean isValid()
            {
                return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
            }

            public void placePortalBlocks()
            {
                for (int i = 0; i < this.width; ++i)
                {
                    BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

                    for (int j = 0; j < this.height; ++j)
                    {
                        this.world.setBlockState(blockpos.up(j), KCore.MysticPortal.getDefaultState().withProperty(BlockMysticPortal.AXIS, this.axis), 2);
                    }
                }
            }
        }
}