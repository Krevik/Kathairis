package com.krevik.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.krevik.Main.KCore;
import com.krevik.Particles.DynamicParticle;

import com.krevik.Particles.ParticlesFactory;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMysticLeaf extends BlockLeaves
{

    public BlockMysticLeaf(String Name, Material material, CreativeTabs tab)
    {
    	super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
        this.setCreativeTab(tab);
        this.setHardness(0.5F);
        this.setResistance(0.1F);
        this.setSoundType(SoundType.PLANT);
        this.setRegistryName(Name);
        this.setUnlocalizedName(Name);
        KCore.instance.regHelper.leavesBlocksList.add(this);
        this.leavesFancy=true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(CHECK_DECAY,Boolean.valueOf(false)).withProperty(DECAYABLE,Boolean.valueOf(false)),2);
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos){ return true; }
    
    public String getLocalizedName()
    {
        return I18n.translateToLocal(this.getUnlocalizedName() + "." + ".name");
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    protected int getSaplingDropChance(IBlockState state)
    {
        return super.getSaplingDropChance(state);
    }


    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

    public BlockPlanks.EnumType getWoodType(int meta)
    {
        return BlockPlanks.EnumType.byMetadata((meta & 3) % 4);
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }
    
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	if(this==KCore.ShinyLeaves && worldIn.isAirBlock(pos.up())) {
            for (int i = 0; i < 4; ++i)
            {
                double d0 = (double)((float)pos.getX() + rand.nextFloat());
                double d1 = (double)((float)pos.getY() + 1);
                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
                double d3 = 0;
                double d4 = 0.35;
                double d5 = 0;
                worldIn.spawnParticle(EnumParticleTypes.SPELL, d0, d1, d2, d3, d4, d5);
            }
    	}
    	if(this==KCore.SoulLeaves&&worldIn.isAirBlock(pos.down())) {
    		if(rand.nextInt(10)==0) {
                double d0 = (double)((float)pos.getX() + rand.nextFloat());
                double d1 = (double)((float)pos.getY() + 1);
                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
                double d3 = 0;
                double d4 = 0;
                double d5 = 0;
                //KCore.cproxy.drawParticle(worldIn, new ParticleSoulTree(worldIn,d0,d1,d2,d3,d4,d5));
                Particle theParticle = new DynamicParticle(
                        ParticlesFactory.SOULTREE,
                        worldIn,
                        d0, d1, d2,
                        d3, d4, d5)
                        .setRotSpeed(((float) Math.random() - 0.5F) * 0.1F)
                        .setLifeSpan((20 + rand.nextInt(20))*10)
                        .setGravity(0.01F)
                        .setScale(2.0F)
                        .setInitialAlpha(1.0F)
                        .setFinalAlpha(0.1F)
                        .setEnableDepth(true).setCanCollide(false);
                Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
    		}

    	}
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }
    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
    
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	if(this==KCore.MysticLeaves) {
            return Item.getItemFromBlock(KCore.MysticSapling);
    	}else if(this==KCore.SoulLeaves) {
    		return Item.getItemFromBlock(KCore.SoulSapling);
    	}else if(this==KCore.ShinyLeaves) {
    		return Item.getItemFromBlock(KCore.ShinyTreeSapling);
    	}else{
    	    return null;
        }

    }

    @Override
    public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        return NonNullList.withSize(1, new ItemStack(this, 1, 0));
    }
}