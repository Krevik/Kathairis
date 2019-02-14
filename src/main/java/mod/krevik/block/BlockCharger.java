package mod.krevik.block;

import mod.krevik.KCore;
import mod.krevik.tileentity.TileEntityCharger;
import mod.krevik.util.CreativeTabsMystic;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCharger extends BlockContainer
{
	
    protected static final AxisAlignedBB CHARGER_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 1D, 0.7D);

    public BlockCharger(String Name)
    {
        super(Material.ROCK);
        lightOpacity = 0;
        setTickRandomly(false);
        useNeighborBrightness = false;
        this.setCreativeTab(CreativeTabsMystic.miscellaneous);
        this.setHardness(3F);
        this.setResistance(3F);
        this.setSoundType(SoundType.STONE);
        this.setRegistryName(Name);
        setTranslationKey(Name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	return CHARGER_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	return CHARGER_AABB;
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public Item getItemDropped(
          IBlockState state, 
          Random rand, 
          int fortune)
    {
        return Item.getItemFromBlock(KCore.Charger);
    }

    @Override
    public void onBlockAdded(
          World parWorld, 
          BlockPos parBlockPos, 
          IBlockState parIBlockState)
    {
    	super.onBlockAdded(parWorld, parBlockPos, parIBlockState);
    }
    
    @Override
    public boolean onBlockActivated(World parWorld, BlockPos parBlockPos, IBlockState parIBlockState, EntityPlayer parPlayer, EnumHand hand,EnumFacing parSide, float hitX, float hitY, float hitZ)
    {
        if (!parWorld.isRemote)
        {
            parPlayer.openGui(KCore.instance, KCore.GUI_ENUM.CHARGER.ordinal(), 
                  parWorld, 
                  parBlockPos.getX(), 
                  parBlockPos.getY(), 
                  parBlockPos.getZ()); 
        }
        
        return true;
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityCharger();
    }

    
    @Override
    public void breakBlock(
          World worldIn, 
          BlockPos pos, 
          IBlockState state)
    {
    	
    	
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityCharger)
        {
        	((TileEntityCharger) tileentity).willBeDeleted = true;
            InventoryHelper.dropInventoryItems(worldIn, pos, 
                    (TileEntityCharger)tileentity);
              if(((TileEntityCharger) tileentity).display!=null) {
	                worldIn.updateComparatorOutputLevel(pos, this);
	                ((TileEntityCharger) tileentity).display.setPosition(0, -3, 0);
	                ((TileEntityCharger) tileentity).display.setDead();
              }
        }
        if (!hasTileEntity)
        {
            tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityCharger)
            {

            }
        }

        super.breakBlock(worldIn, pos, state);

    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

}    