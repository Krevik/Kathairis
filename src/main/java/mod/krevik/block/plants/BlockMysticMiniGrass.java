package mod.krevik.block.plants;

import mod.krevik.KCore;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockMysticMiniGrass extends BlockMysticBush
{
    protected static final AxisAlignedBB MYSTICMINIGRASS_AABB = new AxisAlignedBB(0, 0.0D, 0, 1D, 0.4D, 1D);
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 8);
    public BlockMysticMiniGrass(String Name)
    {
    	super(Name,true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));
        this.setTickRandomly(true);
        addBlocksThatPlantCanStayOn(Blocks.GRASS,Blocks.DIRT,Blocks.FARMLAND,KCore.KatharianGrass,KCore.KatharianDirt);
    }
    
    protected PropertyInteger getAgeProperty()
    {
        return AGE;
    }
    
    public int getMaxAge()
    {
        return 8;
    }
    
    protected int getAge(IBlockState state)
    {
        return state.getValue(this.getAgeProperty()).intValue();
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
    }

    public boolean isMaxAge(IBlockState state)
    {
        return state.getValue(this.getAgeProperty()).intValue() >= this.getMaxAge();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        return 1 + random.nextInt(fortune * 2 + 1);
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {

        return state.isOpaqueCube();
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
            return MYSTICMINIGRASS_AABB;
    }

    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand)
    {
    	if(this.getAge(state)<8) {
            worldIn.setBlockState(pos, this.withAge(this.getAge(state) + 1), 2);	
    	}
            if(this.getAge(state)>7) {
            	worldIn.setBlockState(pos, this.withAge(0), 2);
            }
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            if(this==KCore.MysticMiniGrass) {
                spawnAsEntity(worldIn, pos, new ItemStack(KCore.MysticMiniGrass, 1, 0));
            }
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.withAge(meta);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return this.getAge(state);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE);
    }
    

}