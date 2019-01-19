package mod.krevik.item;

import mod.krevik.entity.butterfly.EntityBasicButterfly;
import mod.krevik.util.CreativeTabsMystic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemButterflyCatcher extends BaseItem {

    public ItemButterflyCatcher(String Name){
        super(Name, CreativeTabsMystic.tools);
        setMaxStackSize(1);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if(entity instanceof EntityBasicButterfly){
            EntityBasicButterfly butterfly = (EntityBasicButterfly) entity;
            butterfly.posX=999;
            butterfly.posY=-10;
            butterfly.posZ=999;
            butterfly.setDead();
            return true;
        }
        return false;
    }

}
