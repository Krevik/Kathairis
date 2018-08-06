package com.Krevik.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Krevik.Main.KCore;
import com.Krevik.Particles.ParticleBlueCloud;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCloudParticleEmitter extends BlockMysticCloud{

    public static final PropertyEnum<BlockCloudParticleEmitter.EnumType> VARIANT = PropertyEnum.<BlockCloudParticleEmitter.EnumType>create("variant", BlockCloudParticleEmitter.EnumType.class);

	public BlockCloudParticleEmitter(String Name) {
		super(Name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockCloudParticleEmitter.EnumType.BLUE));
        this.setTickRandomly(true);
        this.setHardness(-1F);
        this.setCreativeTab(null);
	}
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    { 
    	
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    		return NULL_AABB;
    }
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockCloudParticleEmitter.EnumType.byMetadata(meta));
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
        if(rand.nextInt(2)==0) {
        	worldIn.destroyBlock(pos, false);
        }
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	super.breakBlock(worldIn, pos, state);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        for (int i = 0; i < 4; ++i)
        {
        	double d2=pos.getZ()+rand.nextDouble();
        	double d1=pos.getY()+rand.nextDouble();
        	double d0=pos.getX()+rand.nextDouble();
        	double d5=0;
        	double d4=0;
        	double d3=0;
        	EnumParticleTypes type = EnumParticleTypes.SPELL;
            //KCore.cproxy.drawParticle(worldIn, new ParticleBlueCloud(worldIn,d0,d1,d2,d3,d4,d5));
        	worldIn.spawnParticle(type, d0, d1, d2, d3, d4, d5,0);
        }
    }
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockCloudParticleEmitter.EnumType)state.getValue(VARIANT)).getMetadata();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }
    
    public static enum EnumType implements IStringSerializable
    {
        YELLOW(0,"yellow"),
        BLUE(1,"blue");

        private static final BlockCloudParticleEmitter.EnumType[] META_LOOKUP = new BlockCloudParticleEmitter.EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int p_i46384_3_,String Name)
        {
            this.meta = p_i46384_3_;
            name=Name;
        }

        /**
         * Returns the EnumType's metadata value.
         */
        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * Returns an EnumType for the BlockState from a metadata value.
         */
        public static BlockCloudParticleEmitter.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (BlockCloudParticleEmitter.EnumType blockstone$enumtype : values())
            {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }

		@Override
		public String getName() {
			return name;
		}
    }
    
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        if(this==KCore.CloudParticleEmitter.getDefaultState().withProperty(BlockCloudParticleEmitter.VARIANT, BlockCloudParticleEmitter.EnumType.BLUE)) {
        	return new ItemStack(KCore.BlueCloudDust,1);
        }
        else if(this==KCore.CloudParticleEmitter.getDefaultState().withProperty(BlockCloudParticleEmitter.VARIANT, BlockCloudParticleEmitter.EnumType.YELLOW)){
        	return new ItemStack(KCore.YellowCloudDust,1);
        }else {
        	return new ItemStack(KCore.BlueCloudDust,1);
        }
    }
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
}
