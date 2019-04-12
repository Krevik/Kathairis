package io.github.krevik.kathairis.client.render.layer;

import io.github.krevik.kathairis.client.model.ModelGaznowel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayerHeldItem implements LayerRenderer<EntityLivingBase>
{
    protected final RenderLivingBase<?> livingEntityRenderer;

    public RenderLayerHeldItem(RenderLivingBase<?> livingEntityRendererIn)
    {
        this.livingEntityRenderer = livingEntityRendererIn;
    }

    private void renderHeldItem(EntityLivingBase entityLiving, ItemStack stack, ItemCameraTransforms.TransformType transformType, EnumHandSide handSide)
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
            if(entityLiving instanceof EntityLiving){
                EntityLiving entity = (EntityLiving) entityLiving;
                if(entity.getAttackTarget()!=null){
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

    protected void translateToHand(EnumHandSide p_191361_1_)
    {
        ((ModelGaznowel)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, p_191361_1_);
    }


    @Override
    public void render(EntityLivingBase entitylivingbaseIn, float v, float v1, float v2, float v3, float v4, float v5, float v6) {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
        {
            GlStateManager.pushMatrix();

            if (this.livingEntityRenderer.getMainModel().isChild)
            {
                float f = 0.5F;
                GlStateManager.translatef(0.0F, 0.75F, 0.0F);
                GlStateManager.scaled(0.5F, 0.5F, 0.5F);
            }

            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}