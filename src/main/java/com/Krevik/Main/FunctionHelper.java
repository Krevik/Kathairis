package com.Krevik.Main;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.Int;
import scala.collection.generic.BitOperations;

public class FunctionHelper {

	public Random random = new Random();
	public FunctionHelper() {
		
	}
	
	public int getRandomInteger(int min, int max) {
		return min+random.nextInt(((max+1)-min));
	}
	
	public int getRandomInteger(long Seed, int min, int max) {
		int result=0;
		Random rand = new Random(Seed);
		result=min+rand.nextInt(((max+1)-min));
		return result;
	}
	
    private BlockPos getRandomDestination(Entity entity,int maxX, int maxZ) {
    	BlockPos tmp;
    	double X=entity.posX-random.nextInt(maxX)+random.nextInt(10);
    	double Z=entity.posZ-random.nextInt(maxZ)+random.nextInt(10);
    	double Y=KCore.functionHelper.getRandomInteger(80,120);
    	tmp = new BlockPos(X,Y,Z);
    	return tmp;
    }
    
    public void JellyFishFlee(Entity entity) {
    	BlockPos Destination = this.getRandomDestination(entity,15,15);
    	if(!entity.world.isAirBlock(Destination)) {
    		Destination = this.getRandomDestination(entity,15,15);
    	}
    	if(entity.world.isAirBlock(Destination)) {
        	entity.motionX=(Destination.getX()-entity.posX)/20;
        	entity.motionY=(Destination.getY()-entity.posY)/1000;
        	entity.motionZ=(Destination.getZ()-entity.posZ)/20;
    	}
    }
    
    public boolean canSustainPlantRemake(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
    	boolean can=false;
    	if(state==KCore.CorruptedDirt.getDefaultState() || state==KCore.CorruptedGrass.getDefaultState() || state==Blocks.GRASS.getDefaultState() 
    			|| state==Blocks.DIRT.getDefaultState()||state==Blocks.FARMLAND.getDefaultState()){
    		can=true;
    	}
    	return can;
    }
    
    public ItemStack getRandomReward1() {
    	ItemStack is=null;
    	Item[] rewards = {KCore.RevenumIngot,KCore.TitaniumIngot,KCore.MysticGem,KCore.CondensedBlueCloudDust,KCore.CottonCandy,
    			KCore.MagicBeansItem,KCore.CrystalsCluster,KCore.Heart,KCore.PotWithLivingFlower,Item.getItemFromBlock(KCore.ShinyTreeSapling),
    			KCore.BlueFruit,KCore.ShinyStick,KCore.Gooseberry,Item.getItemFromBlock(KCore.EnergyShard)};
    	is=new ItemStack(rewards[random.nextInt(rewards.length)],random.nextInt(6)+1);
    	if(is.getItem().equals(KCore.CloudBoots)) {is = new ItemStack(KCore.CloudBoots,1);}
    	
    	return is;
    }
    
    public ItemStack getCloudTempleReward() {
    	ItemStack is=null;
    	Item[] rewards = {KCore.RevenumIngot,KCore.TitaniumIngot,KCore.MysticGem,KCore.CondensedBlueCloudDust,KCore.CottonCandy,
    			KCore.MagicBeansItem,KCore.CrystalsCluster,KCore.Heart,KCore.PotWithLivingFlower,Item.getItemFromBlock(KCore.ShinyTreeSapling),
    			KCore.BlueFruit,KCore.ShinyStick,KCore.Gooseberry,KCore.CloudBoots,KCore.CloudEssence,Item.getItemFromBlock(KCore.EnergyShard)};
    	is=new ItemStack(rewards[random.nextInt(rewards.length)],random.nextInt(6)+4);
    	if(is.getItem().equals(KCore.CloudBoots)) {is = new ItemStack(KCore.CloudBoots,1);}
    	
    	return is;
    }
    
    public ItemStack getEasterReward() {
    	ItemStack is=null;
    	Item[] rewards = {KCore.TitaniumIngot,KCore.MysticGem,KCore.CottonCandy,KCore.SweetMuffin,KCore.IceCreams,KCore.BittenCookie,
    			KCore.MagicBeansItem,KCore.CrystalsCluster,Item.getItemFromBlock(KCore.ShinyTreeSapling),
    			KCore.BlueFruit,KCore.Gooseberry,KCore.CloudEssence,Item.getItemFromBlock(KCore.EnergyShard),KCore.JellyFishTentacle};
    	is=new ItemStack(rewards[random.nextInt(rewards.length)],random.nextInt(20)+20);
    	if(is.getItem().equals(KCore.CloudBoots)) {is = new ItemStack(KCore.CloudBoots,1);}
    	
    	return is;
    }

	public ItemStack getChristmasGiftDrop() {
		ItemStack is=null;
		ItemStack[] posibilities = {new ItemStack(KCore.bison_Stars,1+random.nextInt(10)),
		new ItemStack(KCore.christmas_chocolate,1+random.nextInt(10)),
		new ItemStack(KCore.candy_cane,1+random.nextInt(20)),
		new ItemStack(KCore.MysticGem,1+random.nextInt(5)),
		new ItemStack(KCore.CottonCandy,1+random.nextInt(10)),
		new ItemStack(KCore.TitaniumIngot,1+random.nextInt(20)),
		new ItemStack(KCore.CloudEssence,1+random.nextInt(20)),
		new ItemStack(KCore.Magnethium_Shard,1+random.nextInt(50)),
		new ItemStack(KCore.CrystalsCluster,1),
				new ItemStack(KCore.snowdrop_Cyprepedium,1+random.nextInt(20)),
				new ItemStack(KCore.christmas_chocolate,1+random.nextInt(10)),
				new ItemStack(KCore.candy_cane,1+random.nextInt(20)),
				new ItemStack(KCore.christmas_chocolate,1+random.nextInt(10)),
				new ItemStack(KCore.candy_cane,1+random.nextInt(20)),
				new ItemStack(KCore.christmas_chocolate,1+random.nextInt(10)),
				new ItemStack(KCore.candy_cane,1+random.nextInt(20)),
		new ItemStack(KCore.baurble,1),
		new ItemStack(KCore.snowdrop_Cyprepedium,1+random.nextInt(20))};
		is=posibilities[random.nextInt(posibilities.length)];
		return is;
	}
    
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(KCore.EnergyShard))
            {
                return 200;
            }else if(item==KCore.Solis_Crystal){
            	return 400;
			}
            else {
            	return 0;
            }
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
    
    public boolean isAvailableBlockToGenOn(World world,BlockPos pos) {
    	boolean is=false;
    	IBlockState state = world.getBlockState(pos);
    	Block block = state.getBlock();
    	if((block==KCore.CorruptedDirt||block==KCore.CorruptedGrass||block==KCore.ForgottenSand)) {
    		if(world.isAirBlock(pos.up())) {
    			is=true;
    		}
    	}    	
    	return is;
    }
    
    public float degToRad(float degrees)
    {
        return degrees * (float)Math.PI / 180 ;
    }
    

    public Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
    	Matrix4f matrix = new Matrix4f();
    	matrix.setIdentity();
    	Matrix4f.translate(translation, matrix, matrix);
    	Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
    	Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
    	Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
    	Matrix4f.scale(new Vector3f(scale,scale,scale), matrix, matrix);
    	return matrix;
    }

	public Vec3d getForward(EntityPlayer ep)
	{
		return fromPitchYawVector(getPitchYaw(ep));
	}

	public Vec2fNonClientOnly getPitchYaw(Entity ep)
	{
		return new Vec2fNonClientOnly(ep.rotationPitch, ep.rotationYaw);
	}

	public static Vec3d fromPitchYawVector(Vec2fNonClientOnly p_189984_0_)
	{
		return fromPitchYaw(p_189984_0_.x, p_189984_0_.y);
	}

	/**
	 * returns a Vec3d from given pitch and yaw degrees
	 */
	public static Vec3d fromPitchYaw(float p_189986_0_, float p_189986_1_)
	{
		float f = MathHelper.cos(-p_189986_1_ * 0.017453292F - (float)Math.PI);
		float f1 = MathHelper.sin(-p_189986_1_ * 0.017453292F - (float)Math.PI);
		float f2 = -MathHelper.cos(-p_189986_0_ * 0.017453292F);
		float f3 = MathHelper.sin(-p_189986_0_ * 0.017453292F);
		return new Vec3d((double)(f1 * f2), (double)f3, (double)(f * f2));
	}

	public Item getRandomMusicDisc(){
		int k=random.nextInt(2);
		if(k==0)return KCore.Music_Disc_8bit;
		else if(k==1) return KCore.Music_Disc_jazzy;
		else return KCore.Music_Disc_jazzy;
	}

	public void playTameEffect(World world, Random rand, EntityLivingBase animal, boolean play)
	{
		EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;

		if (!play)
		{
			enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
		}

		for (int i = 0; i < 7; ++i)
		{
			double d0 = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			world.spawnParticle(enumparticletypes, animal.posX + (double)(rand.nextFloat() * animal.width * 2.0F) - (double)animal.width, animal.posY + 0.5D + (double)(rand.nextFloat() * animal.height), animal.posZ + (double)(rand.nextFloat() * animal.width * 2.0F) - (double)animal.width, d0, d1, d2);
		}
	}

	public static boolean isGift(ItemStack itemStack){
		boolean is=false;
		if(itemStack.getItem().equals(KCore.howler_fur)||itemStack.getItem().equals(KCore.Fungal_Drug)||
				itemStack.getItem().equals(KCore.skyray_feather)){
			is=true;
		}

		return is;
	}

}
