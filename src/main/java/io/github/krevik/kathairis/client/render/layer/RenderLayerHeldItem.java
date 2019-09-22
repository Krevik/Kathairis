package io.github.krevik.kathairis.client.render.layer;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.client.model.ModelGaznowel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayerHeldItem<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {


   public RenderLayerHeldItem(IEntityRenderer<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void render(T entitylivingbaseIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == HandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (this.getEntityModel().isChild)
            {
                float f = 0.5F;
                GlStateManager.translatef(0.0F, 0.75F, 0.0F);
                GlStateManager.scaled(0.5F, 0.5F, 0.5F);
            }

            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }


    private void renderHeldItem(LivingEntity entityLiving, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide)
    {
        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (entityLiving.isSneaking())
            {
                GlStateManager.translatef(0.0F, 0.2F, 0.0F);
            }
            this.translateToHand(handSide);
            GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag=false;
            GlStateManager.translatef((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
            GlStateManager.scaled(0.75f,0.75f,0.75f);
            if(entityLiving instanceof LivingEntity){
                LivingEntity entity = (LivingEntity) entityLiving;
                if(entity.getCombatTracker().getCombatDuration()>0){
                    GlStateManager.translatef(-0.1F,-1.3F,0.4F);
                    GlStateManager.rotatef(45F, -4.0F, 0F, 0F);
                    GlStateManager.rotatef(45F, 0F, 0.0F, -1F);
                }else{
                   GlStateManager.translatef(0.0F,-0.8F,+0.075F);
                }
            }
            Minecraft.getInstance().getItemRenderer().renderItem(stack,entityLiving, transformType, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(HandSide p_191361_1_)
    {
        ((ModelGaznowel)this.getEntityModel()).postRenderArm(0.0625F, p_191361_1_);
    }


    public boolean shouldCombineTextures()
    {
        return false;
    }
}