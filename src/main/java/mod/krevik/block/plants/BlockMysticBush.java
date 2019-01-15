package mod.krevik.block.plants;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Nullable;

import mod.krevik.block.BaseBlock;
import mod.krevik.block.BlockMysticCloud;
import mod.krevik.util.CreativeTabsMystic;
import mod.krevik.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockMysticBush extends BaseBlock implements net.minecraftforge.common.IPlantable
{
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);

    boolean replacable;

    private ArrayList<Block> blocksThatThePlantCanStayOn = new ArrayList<Block>();
    public BlockMysticBush(String Name, boolean replacable1)
    {
        super(Name,Material.PLANTS,CreativeTabsMystic.plants,0.0F,0.0F,SoundType.PLANT);
        this.setTickRandomly(true);
        replacable=replacable1;
    }

    public void addBlocksThatPlantCanStayOn(Block... block1){
        for(Block block:block1){
            blocksThatThePlantCanStayOn.add(block);
        }
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
    	/*boolean can=false;
        if(state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND || state.getBlock()==KCore.KatharianDirt
        		|| state.getBlock()==KCore.KatharianGrass || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand){
        	can=true;
        }
        if(this==KCore.Plant_Blue_Cloud||this==KCore.Plant_Yellow_Cloud) {
        	if(state.getBlock() instanceof BlockMysticCloud) {
        		return true;
        	}
        }
        	return can;*/
    	boolean can=false;
    	for(Block block: blocksThatThePlantCanStayOn){
    	    if(state.getBlock()==block){
    	        can=true;
            }
        }
        return can;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
    }

    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
        {
            IBlockState soil = worldIn.getBlockState(pos.down());
            return this.canSustainPlantRemake(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        }
        return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }

    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
    	/*boolean can=false;
    	if(state==KCore.KatharianDirt.getDefaultState() || state==KCore.KatharianGrass.getDefaultState() || state==Blocks.GRASS.getDefaultState() || state==Blocks.DIRT.getDefaultState()||
    			state==Blocks.FARMLAND.getDefaultState()  || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand){
    		can=true;
    	}
        if(this==KCore.Plant_Blue_Cloud||this==KCore.Plant_Yellow_Cloud) {
        	if(state.getBlock() instanceof BlockMysticCloud) {
        		can=true;
        	}
        }
    	return can;*/
        boolean can=false;
        for(Block block: blocksThatThePlantCanStayOn){
            if(state.getBlock()==block){
                can=true;
            }
        }
        return can;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) { }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    		return BUSH_AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
            return NULL_AABB;
    }

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
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }
    
}