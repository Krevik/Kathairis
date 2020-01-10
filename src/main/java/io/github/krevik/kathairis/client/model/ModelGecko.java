package io.github.krevik.kathairis.client.model;

import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * gecko - HKhugo
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelGecko<T extends LivingEntity> extends AgeableModel<T> {
    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return null;
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return null;
    }

    @Override
    public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }
    /*public ModelRenderer head_1;
    public ModelRenderer body_1;
    public ModelRenderer legmain_1;
    public ModelRenderer legmain_2;
    public ModelRenderer legmain_3;
    public ModelRenderer legmain_4;
    public ModelRenderer tail_1;
    public ModelRenderer tail_2;
    public ModelRenderer tail_3;
    public ModelRenderer jawtop;
    public ModelRenderer jawbottom;
    public ModelRenderer legtwo_1;
    public ModelRenderer legtwo_2;
    public ModelRenderer legtwo_3;
    public ModelRenderer legtwo_4;

    public ModelGecko() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.jawtop = new ModelRenderer(this, 17, 0);
        this.jawtop.setRotationPoint(-1.0F, 20.68F, -9.7F);
        this.jawtop.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.legtwo_2 = new ModelRenderer(this, 0, 4);
        this.legtwo_2.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_2, -0.9105382707654417F, 0.045553093477052F, -0.045553093477052F);
        this.tail_1 = new ModelRenderer(this, 0, 23);
        this.tail_1.setRotationPoint(-1.15F, 20.6F, -1.2F);
        this.tail_1.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.setRotateAngle(tail_1, 0.27314402793711257F, 0.0F, 0.0F);
        this.body_1 = new ModelRenderer(this, 26, 16);
        this.body_1.setRotationPoint(-1.5F, 20.5F, -5.8F);
        this.body_1.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 4, 9, 0.0F);
        this.tail_2 = new ModelRenderer(this, 0, 15);
        this.tail_2.setRotationPoint(-0.8F, 20.4F, 0.5F);
        this.tail_2.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        this.setRotateAngle(tail_2, 0.6373942428283291F, 0.0F, 0.0F);
        this.legtwo_1 = new ModelRenderer(this, 0, 4);
        this.legtwo_1.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_1, -0.9105382707654417F, 0.045553093477052F, -0.045553093477052F);
        this.legtwo_3 = new ModelRenderer(this, 0, 4);
        this.legtwo_3.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_3.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_3, -1.1838568316277536F, 0.045553093477052F, -0.045553093477052F);
        this.head_1 = new ModelRenderer(this, 42, 0);
        this.head_1.setRotationPoint(-1.25F, 19.95F, -8.4F);
        this.head_1.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
        this.legmain_3 = new ModelRenderer(this, 0, 0);
        this.legmain_3.setRotationPoint(0.9F, 22.85F, -2.0F);
        this.legmain_3.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_3, 0.8651597102135892F, 0.22759093446006054F, -0.7740535232594852F);
        this.legtwo_4 = new ModelRenderer(this, 0, 4);
        this.legtwo_4.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_4.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_4, -1.1838568316277536F, 0.045553093477052F, -0.045553093477052F);
        this.tail_3 = new ModelRenderer(this, 11, 11);
        this.tail_3.setRotationPoint(-0.6F, 20.0F, 2.1F);
        this.tail_3.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail_3, 1.2292353921796064F, 0.004537856055185257F, 0.0F);
        this.jawbottom = new ModelRenderer(this, 17, 5);
        this.jawbottom.setRotationPoint(-1.0F, 21.47F, -9.7F);
        this.jawbottom.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.legmain_4 = new ModelRenderer(this, 0, 0);
        this.legmain_4.setRotationPoint(-1.7F, 22.1F, -2.1F);
        this.legmain_4.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_4, 0.7285004297824331F, -0.18203784098300857F, 0.7740535232594852F);
        this.legmain_2 = new ModelRenderer(this, 0, 0);
        this.legmain_2.setRotationPoint(0.7F, 22.85F, -5.6F);
        this.legmain_2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_2, 0.8651597102135892F, 0.22759093446006054F, -0.7740535232594852F);
        this.legmain_1 = new ModelRenderer(this, 0, 0);
        this.legmain_1.setRotationPoint(-1.5F, 22.1F, -5.8F);
        this.legmain_1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_1, 0.7285004297824331F, -0.18203784098300857F, 0.7740535232594852F);
        this.legmain_2.addChild(this.legtwo_2);
        this.legmain_1.addChild(this.legtwo_1);
        this.legmain_3.addChild(this.legtwo_3);
        this.legmain_4.addChild(this.legtwo_4);
    }

    @Override
    public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return null;
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return null;
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        RenderSystem.pushMatrix();
        GlStateManager.translatef(this.jawtop.offsetX, this.jawtop.offsetY, this.jawtop.offsetZ);
        GlStateManager.translatef(this.jawtop.rotationPointX * f5, this.jawtop.rotationPointY * f5, this.jawtop.rotationPointZ * f5);
        GlStateManager.scaled(0.6D, 0.8D, 0.6D);
        GlStateManager.translatef(-this.jawtop.offsetX, -this.jawtop.offsetY, -this.jawtop.offsetZ);
        GlStateManager.translatef(-this.jawtop.rotationPointX * f5, -this.jawtop.rotationPointY * f5, -this.jawtop.rotationPointZ * f5);
        this.jawtop.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tail_1.offsetX, this.tail_1.offsetY, this.tail_1.offsetZ);
        GlStateManager.translatef(this.tail_1.rotationPointX * f5, this.tail_1.rotationPointY * f5, this.tail_1.rotationPointZ * f5);
        GlStateManager.scaled(0.6D, 0.6D, 0.6D);
        GlStateManager.translatef(-this.tail_1.offsetX, -this.tail_1.offsetY, -this.tail_1.offsetZ);
        GlStateManager.translatef(-this.tail_1.rotationPointX * f5, -this.tail_1.rotationPointY * f5, -this.tail_1.rotationPointZ * f5);
        this.tail_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.body_1.offsetX, this.body_1.offsetY, this.body_1.offsetZ);
        GlStateManager.translatef(this.body_1.rotationPointX * f5, this.body_1.rotationPointY * f5, this.body_1.rotationPointZ * f5);
        GlStateManager.scaled(0.6D, 0.65D, 0.6D);
        GlStateManager.translatef(-this.body_1.offsetX, -this.body_1.offsetY, -this.body_1.offsetZ);
        GlStateManager.translatef(-this.body_1.rotationPointX * f5, -this.body_1.rotationPointY * f5, -this.body_1.rotationPointZ * f5);
        this.body_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tail_2.offsetX, this.tail_2.offsetY, this.tail_2.offsetZ);
        GlStateManager.translatef(this.tail_2.rotationPointX * f5, this.tail_2.rotationPointY * f5, this.tail_2.rotationPointZ * f5);
        GlStateManager.scaled(0.6D, 0.6D, 0.6D);
        GlStateManager.translatef(-this.tail_2.offsetX, -this.tail_2.offsetY, -this.tail_2.offsetZ);
        GlStateManager.translatef(-this.tail_2.rotationPointX * f5, -this.tail_2.rotationPointY * f5, -this.tail_2.rotationPointZ * f5);
        this.tail_2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.head_1.offsetX, this.head_1.offsetY, this.head_1.offsetZ);
        GlStateManager.translatef(this.head_1.rotationPointX * f5, this.head_1.rotationPointY * f5, this.head_1.rotationPointZ * f5);
        GlStateManager.scaled(0.6D, 0.6D, 0.6D);
        GlStateManager.translatef(-this.head_1.offsetX, -this.head_1.offsetY, -this.head_1.offsetZ);
        GlStateManager.translatef(-this.head_1.rotationPointX * f5, -this.head_1.rotationPointY * f5, -this.head_1.rotationPointZ * f5);
        this.head_1.render(f5);
        GlStateManager.popMatrix();
        this.legmain_3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tail_3.offsetX, this.tail_3.offsetY, this.tail_3.offsetZ);
        GlStateManager.translatef(this.tail_3.rotationPointX * f5, this.tail_3.rotationPointY * f5, this.tail_3.rotationPointZ * f5);
        GlStateManager.scaled(0.65D, 0.65D, 0.65D);
        GlStateManager.translatef(-this.tail_3.offsetX, -this.tail_3.offsetY, -this.tail_3.offsetZ);
        GlStateManager.translatef(-this.tail_3.rotationPointX * f5, -this.tail_3.rotationPointY * f5, -this.tail_3.rotationPointZ * f5);
        this.tail_3.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.jawbottom.offsetX, this.jawbottom.offsetY, this.jawbottom.offsetZ);
        GlStateManager.translatef(this.jawbottom.rotationPointX * f5, this.jawbottom.rotationPointY * f5, this.jawbottom.rotationPointZ * f5);
        GlStateManager.scaled(0.6D, 0.8D, 0.6D);
        GlStateManager.translatef(-this.jawbottom.offsetX, -this.jawbottom.offsetY, -this.jawbottom.offsetZ);
        GlStateManager.translatef(-this.jawbottom.rotationPointX * f5, -this.jawbottom.rotationPointY * f5, -this.jawbottom.rotationPointZ * f5);
        this.jawbottom.render(f5);
        GlStateManager.popMatrix();
        this.legmain_4.render(f5);
        this.legmain_2.render(f5);
        this.legmain_1.render(f5);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();
    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(entity, limbSwing, limbSwingAmount, f2, f3, f4, f5);
        this.legmain_1.rotateAngleX = (helper.degToRad(41.74F))+ MathHelper.sin(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legmain_2.rotateAngleX = (helper.degToRad(49.57F))+ MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legmain_3.rotateAngleX = (helper.degToRad(49.57F))+ MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legmain_4.rotateAngleX = (helper.degToRad(41.74F))+ MathHelper.sin(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

    }*/
}
