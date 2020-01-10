package io.github.krevik.kathairis.client.render.layer;

/*@OnlyIn(Dist.CLIENT)
public class RenderLayerHeldItem<T extends LivingEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {

   public RenderLayerHeldItem(IEntityRenderer<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void func_225628_a_(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T entitylivingbaseIn, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        boolean flag = entitylivingbaseIn.getPrimaryHand() == HandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();

        if (!itemstack.isEmpty() || !itemstack1.isEmpty())
        {
            RenderSystem.pushMatrix();

            if (this.getEntityModel().isChild)
            {
                float f = 0.5F;
                RenderSystem.translatef(0.0F, 0.75F, 0.0F);
                RenderSystem.scaled(0.5F, 0.5F, 0.5F);
            }

            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT);
            RenderSystem.popMatrix();
        }
    }


    private void renderHeldItem(LivingEntity entityLiving, ItemStack stack, ItemCameraTransforms.TransformType transformType, HandSide handSide)
    {
        if (!stack.isEmpty())
        {
            RenderSystem.pushMatrix();

            if (entityLiving.isSneaking())
            {
                RenderSystem.translatef(0.0F, 0.2F, 0.0F);
            }
            this.translateToHand(handSide);
            RenderSystem.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
            RenderSystem.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag=false;
            RenderSystem.translatef((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
            RenderSystem.scaled(0.75f,0.75f,0.75f);
            if(entityLiving instanceof LivingEntity){
                LivingEntity entity = (LivingEntity) entityLiving;
                if(entity.getCombatTracker().getCombatDuration()>0){
                    RenderSystem.translatef(-0.1F,-1.3F,0.4F);
                    RenderSystem.rotatef(45F, -4.0F, 0F, 0F);
                    RenderSystem.rotatef(45F, 0F, 0.0F, -1F);
                }else{
                    RenderSystem.translatef(0.0F,-0.8F,+0.075F);
                }
            }
            Minecraft.getInstance().getItemRenderer().renderItem(stack,entityLiving, transformType, flag);
            RenderSystem.popMatrix();
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
}*/