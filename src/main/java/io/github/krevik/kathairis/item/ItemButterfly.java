package io.github.krevik.kathairis.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;

/**
 * @author Krevik
 */
public class ItemButterfly extends Item {

	public ItemButterfly(ItemGroup group, Rarity rarity) {
		super(new Properties().group(group).rarity(rarity));
	}

   /* @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote){
            EntityBasicButterfly butterfly=null;
            if(name == KCore.butterfly_common_1.name){
                butterfly=new EntityButterfly(worldIn);
            }
            else if(name == KCore.butterfly_common_2.name){
                butterfly=new EntityButterfly1(worldIn);
            }
            else if(name == KCore.butterfly_common_moth.name){
                butterfly=new EntitySkylight(worldIn);
            }
            else if(name == KCore.butterfly_cloud_shimmer.name){
                butterfly=new EntityCloudShimmer(worldIn);
            }
            else if(name == KCore.butterfly_illukini.name){
                butterfly=new EntityIllukini(worldIn);
            }
            if(butterfly!=null){
                butterfly.setPosition(pos.getX()+hitX,pos.getY()+hitY,pos.getZ()+hitZ);
                worldIn.spawnEntity(butterfly);
                if(!player.isCreative()) {
                    ItemStack itemstack = player.getHeldItem(hand);
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        player.inventory.deleteStack(itemstack);
                    }
                }
            }

        }
        return EnumActionResult.PASS;
    }*/

}
