package io.github.krevik.kathairis.item;

import io.github.krevik.kathairis.entity.EntityMysticWandShoot;
import io.github.krevik.kathairis.init.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemMysticWand extends Item {
    public ItemMysticWand(ItemGroup group) {
        super(new Properties().group(group).maxStackSize(1).defaultMaxDamage(100).rarity(Rarity.RARE));
    }

    public int getItemEnchantability() {
        return 0;
    }

    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_) {
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if(!world.isRemote) {
            EntityMysticWandShoot bullet = new EntityMysticWandShoot(world);
            bullet.setPosition(player.posX + player.getForward().x, player.posY + player.getEyeHeight() + player.getForward().y, player.posZ + player.getForward().z);
            bullet.setMotion(new Vec3d(player.getForward().normalize().x,player.getForward().normalize().y,player.getForward().normalize().z));
            world.addEntity(bullet);
            player.getHeldItem(hand).damageItem(1, player, (p_220045_0_) -> {
                p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
            });
        }
        world.playSound(player,player.getPosition(), ModSounds.MAGIC_SHOOT, SoundCategory.PLAYERS,1,1);
        return super.onItemRightClick(world, player, hand);
    }
}
