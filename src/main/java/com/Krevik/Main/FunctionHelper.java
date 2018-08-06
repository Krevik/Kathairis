package com.Krevik.Main;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FunctionHelper {

	public Random random = new Random();
	public FunctionHelper() {
		
	}
	
	public int getRandomInteger(int min, int max) {
		return min+random.nextInt(((max+1)-min));
	}
	
    private BlockPos getRandomDestination(Entity entity,int maxX, int maxZ) {
    	BlockPos tmp;
    	double X=entity.posX-random.nextInt(maxX)+random.nextInt(10);
    	double Z=entity.posZ-random.nextInt(maxZ)+random.nextInt(10);
    	double Y=KCore.functionHelper.getRandomInteger(80,120);
    	tmp = new BlockPos(X,Y,Z);
    	return tmp;
    }
    
    public BlockPos getRandomCircleDestination(Entity entity,BlockPos from,int radius) {
    	BlockPos tmp=entity.getPosition();
    	BlockPos result=entity.getPosition();
    	double posX=tmp.getX();
    	double posY=tmp.getY();
    	double posZ=tmp.getZ();
    	
    	int k = -5-random.nextInt(5);
    	double distanceToX1=entity.getDistance(from.getX()+radius, from.getY()+k, posZ);
    	double distanceToX2=entity.getDistance(from.getX()-radius, from.getY()+k, posZ);
    	double distanceToZ1=entity.getDistance(posX, from.getY()+k, from.getZ()+radius);
    	double distanceToZ2=entity.getDistance(posX, from.getY()+k, from.getZ()-radius);
    	
    	double shortestTo=distanceToX1;
    	if(distanceToX1<=4) shortestTo=distanceToZ1;
    	if(distanceToZ1<shortestTo&&distanceToZ1>4) shortestTo=distanceToZ1;
    	if(distanceToX2<shortestTo&&distanceToX2>4) shortestTo=distanceToX2;
    	if(distanceToZ2<shortestTo&&distanceToZ2>4) shortestTo=distanceToZ2;
    	
    	float kk = random.nextFloat()-random.nextFloat()-5;
    	if(shortestTo==distanceToX1) result = new BlockPos(from.getX()+radius+kk,from.getY()+k,from.getZ()+kk);
    	if(shortestTo==distanceToX2) result = new BlockPos(from.getX()-radius+kk,from.getY()+k,from.getZ()+kk);
    	
    	if(shortestTo==distanceToZ1) result = new BlockPos(from.getX()+kk,from.getY()+k,from.getZ()+radius+kk);
    	if(shortestTo==distanceToZ2) result = new BlockPos(from.getX()+kk,from.getY()+k,from.getZ()-radius+kk);
    	
    	if(entity.world.isAirBlock(result))return result;
    	else return getRandomCircleDestination(entity,from,radius);
    }
    
    public void updateJellyFishMotion(Entity entity) {
    	if(!entity.world.isRemote) {
        	BlockPos Destination = this.getRandomDestination(entity,10,10);
        	if(!entity.world.isAirBlock(Destination)) {
        		Destination = this.getRandomDestination(entity,10,10);
        	}
        	if(entity.world.isAirBlock(Destination)) {
            	entity.motionX=(Destination.getX()-entity.posX)/20;
            	entity.motionY=(Destination.getY()-entity.posY)/1000;
            	entity.motionZ=(Destination.getZ()-entity.posZ)/20;
        	}
    	}

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
    	Item[] rewards = {KCore.AdamantiumIngot,KCore.TitaniumIngot,KCore.MysticGem,KCore.CondensedBlueCloudDust,KCore.CottonCandy,
    			KCore.MagicBeansItem,KCore.CrystalsCluster,KCore.Heart,KCore.PotWithLivingFlower,Item.getItemFromBlock(KCore.ShinyTreeSapling),
    			KCore.BlueFruit,KCore.ShinyStick,KCore.Gooseberry,Item.getItemFromBlock(KCore.EnergyShard)};
    	is=new ItemStack(rewards[random.nextInt(rewards.length)],random.nextInt(6)+1);
    	if(is.getItem().equals(KCore.CloudBoots)) {is = new ItemStack(KCore.CloudBoots,1);}
    	
    	return is;
    }
    
    public ItemStack getCloudTempleReward() {
    	ItemStack is=null;
    	Item[] rewards = {KCore.AdamantiumIngot,KCore.TitaniumIngot,KCore.MysticGem,KCore.CondensedBlueCloudDust,KCore.CottonCandy,
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
            }else {
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
    	if((block==KCore.CorruptedDirt||block==KCore.CorruptedGrass||block==KCore.ForgottenSand||block==KCore.WeatheredRock)) {
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
    
    public float normalDistibution(int srednia, int odchylenie, int actual) {
    	float result=0;
    	float drugiczlon=0;
    	drugiczlon=(-((actual-srednia)*(actual-srednia)))/(2*odchylenie*odchylenie);
    	result=(float) (1/(odchylenie*Math.sqrt(2*Math.PI))*Math.exp(drugiczlon));
    	return result;
    }

}
