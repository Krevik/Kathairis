package mod.krevik.item;

import mod.krevik.block.BlockCloudParticleEmitter;
import mod.krevik.KCore;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGlassJar extends BaseItem{
 
 
 public ItemGlassJar(String name, CreativeTabs tab) {
	 super(name,tab);
 this.name = name;
 }
 
 @SideOnly(Side.CLIENT)
 public void initModel() {
     ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
 }
 
 public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
 {
	 if(this==KCore.GlassJar) {
		 IBlockState state = worldIn.getBlockState(pos);
		 if(state==KCore.CloudParticleEmitter.getDefaultState().withProperty(BlockCloudParticleEmitter.VARIANT, BlockCloudParticleEmitter.EnumType.BLUE)) {
	    	 ItemStack itemstack = player.getHeldItem(hand);
	    	 itemstack.shrink(1);
	    	 player.inventory.addItemStackToInventory(new ItemStack(KCore.BlueCloudDust,1));
	    	 worldIn.setBlockToAir(pos);
		 }
		 if(state==KCore.CloudParticleEmitter.getDefaultState().withProperty(BlockCloudParticleEmitter.VARIANT, BlockCloudParticleEmitter.EnumType.YELLOW)) {
	    	 ItemStack itemstack = player.getHeldItem(hand);
	    	 itemstack.shrink(1);
	    	 player.inventory.addItemStackToInventory(new ItemStack(KCore.YellowCloudDust,1));
	    	 worldIn.setBlockToAir(pos);
		 }
		 if(state==KCore.SwampGas.getDefaultState()) {
	    	 ItemStack itemstack = player.getHeldItem(hand);
	    	 itemstack.shrink(1);
	    	 player.inventory.addItemStackToInventory(new ItemStack(KCore.JarWithSwampGas,1));
	    	 worldIn.setBlockToAir(pos);
		 }
		 return EnumActionResult.SUCCESS; 
	 }else if(this==KCore.JarWithSwampGas){
		 IBlockState state = worldIn.getBlockState(pos);
		 if(state.getBlock().isFullBlock(state)&&worldIn.isAirBlock(pos.up())) {
			 worldIn.setBlockState(pos.up(), KCore.SwampGas.getDefaultState());
	    	 ItemStack itemstack = player.getHeldItem(hand);
	    	 itemstack.shrink(1);
	    	 player.inventory.addItemStackToInventory(new ItemStack(KCore.GlassJar,1));
		 }
		 return EnumActionResult.SUCCESS; 
	 }else 
	 return EnumActionResult.PASS;
 }
 

}