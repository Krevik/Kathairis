package mod.krevik.block;

import mod.krevik.KCore;
import mod.krevik.client.ClientProxy;
import mod.krevik.client.particle.ParticleMysticGemBlock;
import mod.krevik.entity.EntityPoisonousScorpion;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BaseBlock extends Block
{
	
    protected static final AxisAlignedBB HALF_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB ALMOST_FULL_BLOCK_AABB = new AxisAlignedBB(0.001D, 0.001D, 0.001D, 0.999D, 0.999D, 0.999D);

    public BaseBlock(String Name, Material material, CreativeTabs tab, float hardness1, float resistance, SoundType soundType)
    {
        super(material);
        this.setCreativeTab(tab);
        this.setHardness(hardness1);
        this.setResistance(resistance);
        this.setSoundType(soundType);
        this.setRegistryName(Name);
        this.setTranslationKey(Name);
        KCore.regHelper.blocksList.add(this);
    }
    
    @Override
	protected boolean canSilkHarvest()
    {
    	boolean can=false;
        if(this==KCore.MythicStone||this==KCore.Solis_Crystals||this==KCore.Refined_Cloud_Blue||this==KCore.Refined_Cloud_Yellow||
        		this==KCore.YellowCrystal||this==KCore.BlueCrystal||this==KCore.VioletCrystal) {
        	can=true;
        }
    	
    	return can;
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
    	if(this==KCore.Solis_Crystals) {
    		return worldIn.getBlockState(pos.down()).isFullCube();
    	}else {
    		return true;
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	if(this==KCore.Mystic_Gem_Block&&worldIn.isAirBlock(pos.up())) {
    		if(rand.nextInt(2)==0) {
                double d0 = (double)((float)pos.getX() + rand.nextFloat());
                double d1 = (double)((float)pos.getY() + 1);
                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
                double d3 = 0;
                double d4 = rand.nextFloat()*0.1;
                double d5 = 0;
                ClientProxy.drawParticle(worldIn, new ParticleMysticGemBlock(worldIn,d0,d1,d2,d3,d4,d5));
    		}

    	}
    }
    
    boolean ignoreSimilarity=false;

    @Override
	public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
    	if((this==KCore.MovingSand||this==KCore.MudBlock)&& !(entityIn instanceof EntityPoisonousScorpion)) {
    		entityIn.setInWeb();
    	}
    	if(this==KCore.Magnethium) {
    		if(entityIn instanceof EntityLivingBase) {
    			EntityLivingBase living = (EntityLivingBase)entityIn;
    			living.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50));
    		}
    	}
    }
    
    @Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
    	if(this==KCore.Magnethium) {
    		if(entityIn instanceof EntityLivingBase) {
    			EntityLivingBase living = (EntityLivingBase)entityIn;
    			living.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50));
    		}
    	}
    }

    @Override
	@Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    	if(this==KCore.MovingSand){
    		return NULL_AABB;
    	}else if(this==KCore.MudBlock) {
    		return HALF_BLOCK_AABB;
    	}
    	else if(this==KCore.Magnethium) {
    		return ALMOST_FULL_BLOCK_AABB;
    	}
    	else{
            return FULL_BLOCK_AABB;
    	}
    }
    
    @Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    @Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
        }
    }
    
    @Override
    public void breakBlock(
          World worldIn, 
          BlockPos pos, 
          IBlockState state)
    {
    	if(this==KCore.Magnethium) {
    			Random random = KCore.functionHelper.random;
    	    	for(int c=0;c<(1+random.nextInt(4));c++) {
    	    		EntityItem is = new EntityItem(worldIn);
    	    		is.setItem(new ItemStack(KCore.Magnethium_Shard,1));
    	    		is.setPosition(pos.getX()+0.5, pos.getY()+0.5,pos.getZ()+0.5);
    	    		is.setNoGravity(true);
    	    		is.motionY=+0.04;
    	    		is.velocityChanged=true;
    	    		if(!worldIn.isRemote) {
    	    			worldIn.spawnEntity(is);
    	    		}
    	            List<EntityLivingBase> e = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX() - 2, pos.getY() - 2, pos.getZ() - 2, pos.getX()  + 2, pos.getY() + 2, pos.getZ() + 2));
    	            if(e!=null) {
    	            	if(e.size()>0) {
    	            		for(int cc=0;cc<e.size();cc++) {
    	            			e.get(cc).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 50));
    	            		}
    	            	}
    	            }
    	    	}
    	}
    		super.breakBlock(worldIn, pos, state);

    }
    


    private void checkFallable(World worldIn, BlockPos pos)
    {
    	if(this==KCore.MovingSand||this==KCore.ForgottenSand) {
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
						}

    	                if (blockpos.getY() > 0)
    	                {
    	                    worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
    	                }
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
    
    @Override
	public String getLocalizedName()
    {
        return I18n.translateToLocal(this.getTranslationKey() + "." + ".name");
    }

    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	if(this==KCore.KatharianGrass) {
    		return Item.getItemFromBlock(KCore.KatharianDirt);
    	}else if(this==KCore.VioletCrystal){
    		return KCore.VioletCrystalShard;
    	}else if(this==KCore.YellowCrystal) {
    		return KCore.YellowCrystalShard;
    	}else if(this==KCore.BlueCrystal) {
    		return KCore.BlueCrystalShard;
    	}else if(this==KCore.MysticMultiGrass) {
    		return null;
    	}else if(this==KCore.MysticTallGrass) {
    		return null;
    	}else if(this==KCore.MysticMiniGrass) {
    		return null;
    	}else if(this==KCore.MysticDeadGrass) {
    		return null;
    	}else if(this==KCore.BlueFruitPlant) {
    		return KCore.BlueFruit;
    	}else if(this==KCore.MagicBeans) {
    		return KCore.MagicBeansItem;
    	}else if(this==KCore.GemsOre||this==KCore.TitaniumOre||this==KCore.RevenumOre) {
        	Item item=Items.DIAMOND;
        	if(this==KCore.GemsOre) {
        		int i=rand.nextInt(4);
        		if(i==0) {
        			item=Items.DIAMOND;
        		}else if(i==1) {
        			item=KCore.MysticGem;
        		}else {
        			item=Items.DIAMOND;
        		}
        	}else {
               item = Item.getItemFromBlock(this);	
        	}
        	return item;
    	}
    	else if(this==KCore.MythicStone) {
    		return Item.getItemFromBlock(KCore.Mythic_Cobblestone);
    	}
    	else if(this==KCore.Magnethium) {
    		return null;
    	}
    	else if(this==KCore.Solis_Crystals) {
    		return KCore.Solis_Crystal;
    	}
    	else {
    		return Item.getItemFromBlock(this);
    	}
    }
    
    @Override
	public int quantityDropped(Random random)
    {
    	if(this==KCore.Solis_Crystals) {
    		return 1+random.nextInt(5);
    	}
    	if(this==KCore.YellowCrystal||this==KCore.VioletCrystal||this==KCore.BlueCrystal) {
    		return 1+random.nextInt(5);
    	}
         return 1;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
    	if(this==KCore.BlueCloudBricks) {
    		return BlockRenderLayer.TRANSLUCENT;
    	}
    	else if(this==KCore.BlueCondensedCloud) {
    		return BlockRenderLayer.TRANSLUCENT;
    	}
    	else if(this==KCore.YellowCloudBlock) {
    		return BlockRenderLayer.TRANSLUCENT;
    	}
    	else if(this==KCore.YellowCondensedCloud) {
    		return BlockRenderLayer.TRANSLUCENT;
    	}else if(this==KCore.Solis_Crystals) {
    		return BlockRenderLayer.TRANSLUCENT;
    	}else if(this==KCore.Refined_Cloud_Yellow||this==KCore.Refined_Cloud_Blue) {
    		return BlockRenderLayer.TRANSLUCENT;
    	}
    	else {
            return BlockRenderLayer.SOLID;
    	}
    }



    @Override
	public boolean isFullCube(IBlockState state)
    {
		return this != KCore.BlueCloudBricks && this != KCore.YellowCloudBlock && this != KCore.Solis_Crystals;
    }
    
    @Override
	public boolean isOpaqueCube(IBlockState state)
    {
		return this != KCore.BlueCloudBricks && this != KCore.YellowCloudBlock && this != KCore.Solis_Crystals && this != KCore.Refined_Cloud_Yellow && this != KCore.Refined_Cloud_Blue;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if(this==KCore.BlueCloudBricks||this==KCore.YellowCloudBlock||this==KCore.Refined_Cloud_Yellow||this==KCore.Refined_Cloud_Blue) {
            IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
            Block block = iblockstate.getBlock();

            return (this.ignoreSimilarity || block != this) && super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }else if(this==KCore.Solis_Crystals) {
        	return true;
        }
        else {
        	return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }
    
}
