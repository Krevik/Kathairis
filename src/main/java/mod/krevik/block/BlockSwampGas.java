package mod.krevik.block;

import java.util.Random;

import javax.annotation.Nullable;

import mod.krevik.util.CreativeTabsMystic;
import mod.krevik.KCore;
import mod.krevik.client.particle.DynamicParticle;

import mod.krevik.client.particle.ParticlesFactory;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSwampGas extends BlockMysticCloud{


	public BlockSwampGas(String Name) {
		super(Name);
        this.setTickRandomly(true);
        this.setHardness(-1F);
        this.setCreativeTab(CreativeTabsMystic.miscellaneous);
	}
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    { 
    	if(entityIn instanceof EntityPlayerMP) {
    		EntityPlayerMP ep = (EntityPlayerMP) entityIn;
    		ep.addPotionEffect(new PotionEffect(Potion.getPotionById(9),100));
    	}
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        if(isFireNearby(worldIn,pos)) {
			explodeBlock(worldIn,pos,state);
        }
        
    }
    
    private boolean isFireNearby(World world, BlockPos pos) {
    	if(world.getBlockState(pos.up()).getBlock()==Blocks.FIRE) {
    		return true;
    	}else if(world.getBlockState(pos.down()).getBlock()==Blocks.FIRE) {
    		return true;
    	}else if(world.getBlockState(pos.south()).getBlock()==Blocks.FIRE){
    		return true;
    	}else if(world.getBlockState(pos.north()).getBlock()==Blocks.FIRE){
    		return true;
    	}else if(world.getBlockState(pos.east()).getBlock()==Blocks.FIRE){
    		return true;
    	}else if(world.getBlockState(pos.west()).getBlock()==Blocks.FIRE){
    		return true;
    	}else {
    		return false;
    	}
    }
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
    		return NULL_AABB;
    }
    
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);
    }

    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	super.breakBlock(worldIn, pos, state);
    }
    

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	for(int x=0;x<=1+rand.nextInt(8);x++) {
			double d0 = (double) ((float) pos.getX() + rand.nextFloat());
			double d1 = (double) ((float) pos.getY() + rand.nextFloat());
			double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
			Particle theParticle = new DynamicParticle(
					ParticlesFactory.SWAMPGAS,
					worldIn,
					d0, d1, d2,
					0, 0, 0)
					.setRotSpeed(0F)
					.setLifeSpan(20 + rand.nextInt(20))
					.setGravity(0F)
					.setScale(2.5F)
					.setInitialAlpha(1.0F)
					.setFinalAlpha(0.1F);
			Minecraft.getMinecraft().effectRenderer.addEffect(theParticle);
        	//worldIn.spawnParticle(type, d0, d1, d2, d3, d4, d5,0);
    	}
    }
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
   	 ItemStack itemstack = playerIn.getHeldItem(hand);
   	 if(itemstack.getItem()==Items.FLINT_AND_STEEL) {
   	 	explodeBlock(worldIn,pos,state);
   		 return true;
   	 }else {
        return false;
   	 }
    }
    
    public void explodeBlock(World world, BlockPos pos, IBlockState state) {
		/*if(world.isRemote) {
			PacketSwampGasExplosionServer message = new PacketSwampGasExplosionServer(pos.getX(), pos.getY(), pos.getZ(), 4);
			KathairisPacketHandler.CHANNEL.sendToServer(message);
		}*/

		if(!world.isRemote){
			world.getMinecraftServer().addScheduledTask(new Runnable() {
					@Override
					public void run() {
						world.createExplosion(null,pos.getX(), pos.getY(), pos.getZ(), 4,true);
						//PacketSwampGasExplosionClient messageNew = new PacketSwampGasExplosionClient(message.posX,message.posY
						// ,message.posZ,message.size,ctx.getServerHandler().player.dimension);
						//KathairisPacketHandler.CHANNEL.sendToAll(messageNew);
					}

				});
		}
    }

    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
	    	int radius=2;
	    	for(int x=-radius;x<=radius;x++) {
	        	for(int y=-radius;y<=radius;y++) {
	            	for(int z=-radius;z<=radius;z++) {
	            		BlockPos tmp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
	            		if(worldIn.getBlockState(tmp).getBlock()==KCore.SwampGas) {
							explodeBlock(worldIn,pos,worldIn.getBlockState(pos));
	            		}
	            	}
	        	}
	    	}
    }
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }
    
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
    	return new ItemStack(KCore.JarWithSwampGas,1);
    }
}
