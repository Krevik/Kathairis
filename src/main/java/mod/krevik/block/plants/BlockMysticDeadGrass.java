package mod.krevik.block.plants;

import java.util.Random;

import javax.annotation.Nullable;

import mod.krevik.KCore;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMysticDeadGrass extends BlockMysticBush
{
    protected static final AxisAlignedBB MYSTICDEADGRASS_AABB = new AxisAlignedBB(0.20000001192092896D, 0.0D, 0.20000001192092896D, 0.699999988079071D, 0.7D, 0.799999988079071D);

    public BlockMysticDeadGrass(String Name,boolean c)
    {
    	super(Name,c);
    }


    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return MYSTICDEADGRASS_AABB;
    }
 
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }
    
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return 1 + random.nextInt(fortune * 2 + 1);
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            if(this==KCore.MysticDeadGrass) {
                spawnAsEntity(worldIn, pos, new ItemStack(KCore.MysticDeadGrass, 1, 0));
            }
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        boolean can=false;
        if(state==KCore.KatharianDirt.getDefaultState() || state==KCore.KatharianGrass.getDefaultState() || state== Blocks.GRASS.getDefaultState() || state==Blocks.DIRT.getDefaultState()||
                state==Blocks.FARMLAND.getDefaultState()  || state.getBlock()==KCore.MovingSand || state.getBlock()==KCore.ForgottenSand){
            can=true;
        }
        return can;
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
            if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            {
                IBlockState soil = worldIn.getBlockState(pos.down());
                return this.canSustainPlantRemake(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
            }
            return this.canSustainBush(worldIn.getBlockState(pos.down()));
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {}

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        this.checkAndDropBlock(worldIn, pos, state);
    }

}