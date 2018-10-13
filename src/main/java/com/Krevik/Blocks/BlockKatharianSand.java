package com.Krevik.Blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Entities.EntityPoisonousScorpion;
import com.Krevik.Entities.Butterflies.EntityButterfly;
import com.Krevik.Main.KCore;
import com.Krevik.Particles.ParticleMysticGemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockKatharianSand extends BaseBlock
{

    public BlockKatharianSand(String Name, Material material, CreativeTabs tab, float hardness1, float resistance, SoundType soundType)
    {
        super(Name,material,tab,hardness1,resistance,soundType);
        this.setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {

    }


    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
            return FULL_BLOCK_AABB;
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
        }
        if(worldIn.getBiome(pos)==KCore.instance.MysticDesert){
            if(!worldIn.isRemote){
                KetherDataStorage data = KCore.data.getDataInstance(worldIn);
                if(data!=null){
                    if(data.getIsSandstorm()){
                        if(rand.nextInt(40)==0){
                            if(worldIn.isAirBlock(pos.up())){
                                worldIn.setBlockState(pos.up(),KCore.ForgottenSand.getDefaultState(),2);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void breakBlock(
            World worldIn,
            BlockPos pos,
            IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);

    }



    private void checkFallable(World worldIn, BlockPos pos)
    {
            if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
            {
                int i = 32;

                if (worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
                {
                    if (!worldIn.isRemote)
                    {
                        EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                        worldIn.spawnEntity(entityfallingblock);
                    }
                }
                else
                {
                    IBlockState state = worldIn.getBlockState(pos);
                    worldIn.setBlockToAir(pos);
                    BlockPos blockpos;

                    for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
                    {
                        ;
                    }

                    if (blockpos.getY() > 0)
                    {
                        worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
                    }
                }
            }
    }

    public static boolean canFallThrough(IBlockState state)
    {
        Block block = state.getBlock();
        Material material = state.getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
    }

    public String getLocalizedName()
    {
        return I18n.translateToLocal(this.getUnlocalizedName() + "." + ".name");
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
            return Item.getItemFromBlock(this);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.SOLID;
    }



    public boolean isFullCube(IBlockState state)
    {
        return true;
    }


}