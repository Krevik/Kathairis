package mod.krevik.Potion;

import mod.krevik.Main.ClientProxy;
import mod.krevik.Main.KCore;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class DissolutionPotion extends Potion {

    public DissolutionPotion(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.renderEngine.bindTexture(ClientProxy.dissolution_potion);
        net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 16, 16, 16, 16);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.renderEngine.bindTexture(ClientProxy.dissolution_potion);
        net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 16, 16, 16, 16);
    }

    /*@Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        entityLivingBaseIn.motionZ=0;
        entityLivingBaseIn.motionY=-1f;
        entityLivingBaseIn.motionZ=0;
        entityLivingBaseIn.rotationYaw=entityLivingBaseIn.prevRotationYaw;
        entityLivingBaseIn.rotationPitch=entityLivingBaseIn.prevRotationPitch;
        entityLivingBaseIn.rotationYawHead=entityLivingBaseIn.prevRotationYawHead;
    }*/
}