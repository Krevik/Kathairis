package com.krevik.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.krevik.Main.CreativeTabsMystic;
import com.krevik.Main.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockMysticBush extends BaseBlock implements net.minecraftforge.common.IPlantable
{
	boolean multi;
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
    protected static final AxisAlignedBB MYSTICFLOWER_AABB = new AxisAlignedBB(0.20000001192092896D, 0.0D, 0.20000001192092896D, 0.699999988079071D, 0.6D, 0.799999988079071D);
    protected static final AxisAlignedBB MYSTICNIGHTFLOWER_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 1D, 0.699999988079071D);
    protected static final AxisAlignedBB MYSTICDEADGRASS_AABB = new AxisAlignedBB(0.20000001192092896D, 0.0D, 0.20000001192092896D, 0.699999988079071D, 0.7D, 0.799999988079071D);
    protected static final AxisAlignedBB EYEPLANT_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 1D, 1D);

    protected static final AxisAlignedBB MYSTICMINIGRASS_AABB = new AxisAlignedBB(0, 0.0D, 0, 1D, 0.4D, 1D);
    protected static final AxisAlignedBB BUTTERFLYFLOWER_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.9D, 1D);
    protected static final AxisAlignedBB SUCCULENT_AABB = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1D, 0.8125D);

    boolean replacable;
    public BlockMysticBush(String Name, boolean b, boolean replacable1)
    {
        super(Name,Material.PLANTS,CreativeTabsMystic.plants,0.0F,0.0F,SoundType.PLANT);
        this.setTickRandomly(true);
        multi=b;
        replacable=replacable1;
    }

    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return replacable;
    }
    
    public BlockMysticBush setLightLevels(float value)
    {
        this.lightValue = (int)(15.0F * value);
        return this;
    }
    
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        return this.canSustainPlantRemake(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, KCore.MysticSapling);
    }
    

    /**
     * Return true if the block can sustain a Bush
     */
    protected boolean canSustainBush(IBlockState state)
    {
    	boolean can=false;
        if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock()==KCore.CorruptedDirt
        		|| state.getBlock()==KCore.CorruptedGrass || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand){
        	can=true;
        }
        if(multi){
        	if(state.getBlock()==KCore.MysticMultiGrass){
        		can=true;
        	}
        	if(state.getBlock()==KCore.SteppedSucculent){
        		can=true;
        	}
        	/*if(state.getBlock()==KCore.Hell_Plant) {
        		can=true;
        	}*/
        }
        if(this==KCore.Plant_Blue_Cloud||this==KCore.Plant_Yellow_Cloud) {
        	if(state.getBlock() instanceof BlockMysticCloud) {
        		return true;
        	}
        }
        if(this==KCore.MysticFungus) {
        	if(state.getBlock()==KCore.MythicStone) {
        		can=true;
        	}
        }
        if(this==KCore.Succulent) {
        	if(state.getBlock()==KCore.Succulent) {
        		can=true;
        	}
        }
        if(this==KCore.CursedFlower||this==KCore.DeadLichen) {
        	if(state.getBlock().isFullBlock(state)) {
        		can=true;
        	}
        }
        	
        	return can;
    }
    



    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
        if(rand.nextInt(40)==0) {
	        if(this==KCore.Succulent||this==KCore.SteppedSucculent) {
	            if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
	            BlockPos blockpos = pos.up();
	            if (worldIn.isAirBlock(blockpos))
	            {
	                int i;

	                for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i)
	                {
	                    ;
	                }

	                if (i < 3)
	                {
	                        worldIn.setBlockState(blockpos, this.getDefaultState());
	                        IBlockState iblockstate = state;
	                        worldIn.setBlockState(pos, iblockstate, 4);
	                        iblockstate.neighborChanged(worldIn, blockpos, this, pos);

	                }
	            }
	        }
        }
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    int timer=0;
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	if(this==KCore.Succulent) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
    	}
    	/*if(this==KCore.Hell_Plant) {
    		entityIn.setInWeb();
	    		timer++;
	    		if(timer>100) {
	    			timer=0;
	    			entityIn.setFire(2);
	    		}
    	}*/
    }
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
    	if(this==KCore.Succulent) {
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
            {
                Material material = worldIn.getBlockState(pos.offset(enumfacing)).getMaterial();

                if (material.isSolid() || material == Material.LAVA)
                {
                    return false;
                }
            }

            state = worldIn.getBlockState(pos.down());
            return this.canSustainPlantRemake(state, worldIn, pos.down(), EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
    	}else if(this==KCore.Plant_Blue_Cloud||this==KCore.Plant_Yellow_Cloud) {
            state = worldIn.getBlockState(pos.down());
    		if(state.getBlock() instanceof BlockMysticCloud) {
    			return this.canSustainPlantRemake(state, worldIn, pos.down(), EnumFacing.UP, this) && !worldIn.getBlockState(pos.up()).getMaterial().isLiquid();
    		}else {
    			return false;
    		}
    	}
    	else {
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            IBlockState soil = worldIn.getBlockState(pos.down());
            return this.canSustainPlantRemake(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        }
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
    	}
    }
    
    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
    	boolean can=false;
    	if(state==KCore.CorruptedDirt.getDefaultState() || state==KCore.CorruptedGrass.getDefaultState() || state==Blocks.GRASS.getDefaultState() || state==Blocks.DIRT.getDefaultState()||
    			state==Blocks.FARMLAND.getDefaultState()  || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand){
    		can=true;
    	}
        if(multi){
        	if(state.getBlock()==KCore.MysticMultiGrass){
        		can=true;
        	}
        	if(state.getBlock()==KCore.SteppedSucculent){
        		can=true;
        	}
        	/*if(state.getBlock()==KCore.Hell_Plant) {
        		can=true;
        	}*/
        }
        if(this==KCore.MysticFungus) {
        	if(state.getBlock()==KCore.MythicStone) {
        		can=true;
        	}
        }
        if(this==KCore.Plant_Blue_Cloud||this==KCore.Plant_Yellow_Cloud) {
        	if(state.getBlock() instanceof BlockMysticCloud) {
        		can=true;
        	}
        }
        if(this==KCore.Succulent) {
        	if(state.getBlock()==KCore.Succulent) {
        		can=true;
        	}
        }
        if(this==KCore.CursedFlower||this==KCore.DeadLichen) {
        	if(state.getBlock().isFullBlock(state)) {
        		can=true;
        	}
        }
    	return can;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(1000) == 0)
        {
        	if(this==KCore.CursedFlower||this==KCore.DeadLichen) {
                worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, KCore.instance.cproxy.scary_flower, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        	}
        }
        /*if(this==KCore.Hell_Plant) {
        	if(rand.nextInt(10)==0) {
        		for(int x=0;x<1+rand.nextInt(4);x++) {
        			float d0=pos.getX()+rand.nextFloat();
        			float d1=pos.getY()+rand.nextFloat();
        			float d2=pos.getZ()+rand.nextFloat();
        			float d3=rand.nextFloat();
        			float d4=rand.nextFloat();
        			float d5=rand.nextFloat();
                    worldIn.spawnParticle(EnumParticleTypes.LAVA, d0, d1, d2, d3, d4, d5);
        		}
        	}
        }*/
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	if(this==KCore.MysticFlower){
    		return MYSTICFLOWER_AABB;
    	}
    	else if(this==KCore.MysticNightFlower) {
    		return MYSTICNIGHTFLOWER_AABB;
    	}
    	else if(this==KCore.MysticDeadGrass) {
    		return MYSTICDEADGRASS_AABB;
    	}
    	else if(this==KCore.MysticMiniGrass) {
    		return MYSTICMINIGRASS_AABB;
    	}else if(this==KCore.EyePlant) {
    		return EYEPLANT_AABB;
		}else if(this==KCore.ButterflyFlower) {
			return BUTTERFLYFLOWER_AABB;
		}
		else if(this==KCore.Succulent) {
			return SUCCULENT_AABB;
		}else if(this==KCore.SteppedSucculent) {
			return FULL_BLOCK_AABB;
		}/*else if(this==KCore.Hell_Plant) {
			return FULL_BLOCK_AABB;
		}*/
    	else{
    		return BUSH_AABB;	
    	}
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	if(this==KCore.Succulent) {
    		return SUCCULENT_AABB;
    	}else if(this==KCore.SteppedSucculent) {
			return FULL_BLOCK_AABB;
		}
    	else {
            return NULL_AABB;	
    	}
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
}