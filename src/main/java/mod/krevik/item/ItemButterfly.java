package mod.krevik.item;

import mod.krevik.KCore;
import mod.krevik.entity.butterfly.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemButterfly extends BaseItem {


    EnumRarity rarity;
    public ItemButterfly(String name, CreativeTabs tab, EnumRarity rarity1) {
        super(name,tab);
        rarity=rarity1;
    }


    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return rarity;
    }


    @Override
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
    }


}