package io.github.krevik.kathairis.client.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelLivingFlower<T extends LivingEntity> extends EntityModel<T> {
    public ModelRenderer Pylek;
    public ModelRenderer Noga2;
    public ModelRenderer Noga1;
    public ModelRenderer Noga3;
    public ModelRenderer Lisc1;
    public ModelRenderer Lisc2;
    public ModelRenderer Lisc3;
    public ModelRenderer Lisc4;
    public ModelRenderer Lisc5;
    public ModelRenderer MLisc1;
    public ModelRenderer MLisc2;
    public ModelRenderer MLisc3;
    public ModelRenderer MLisc4;
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;

    public ModelLivingFlower() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Shape2 = new ModelRenderer(this, 32, 17);
        this.Shape2.setRotationPoint(-3.4F, 20.2F, 0.0F);
        this.Shape2.func_228301_a_(0.0F, 0.0F, 0.0F, 7, 0, 3, 0.0F);
        this.setRotateAngle(Shape2, 0.0F, 0.043807764225057666F, 0.7285004297824331F);
        this.Noga3 = new ModelRenderer(this, 30, 0);
        this.Noga3.setRotationPoint(-0.5F, 15.6F, -0.2F);
        this.Noga3.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Noga3, 0.0F, 0.7740535232594852F, 0.31869712141416456F);
        this.Lisc3 = new ModelRenderer(this, 4, 13);
        this.Lisc3.setRotationPoint(0.1F, 16.0F, -1.7F);
        this.Lisc3.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc3, 0.0F, -1.8212510744560826F, 0.0F);
        this.Pylek = new ModelRenderer(this, 0, 0);
        this.Pylek.setRotationPoint(0.4F, 15.5F, -1.5F);
        this.Pylek.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(Pylek, 0.4553564018453205F, -0.9105382707654417F, 1.6845917940249266F);
        this.MLisc4 = new ModelRenderer(this, 0, 7);
        this.MLisc4.setRotationPoint(-0.4F, 15.9F, -1.2F);
        this.MLisc4.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc4, 0.3490658503988659F, -1.5707963267948966F, 0.0F);
        this.Noga1 = new ModelRenderer(this, 30, 0);
        this.Noga1.setRotationPoint(-1.0F, 20.9F, 0.0F);
        this.Noga1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.Lisc2 = new ModelRenderer(this, 4, 13);
        this.Lisc2.setRotationPoint(-1.2F, 15.9F, -0.8F);
        this.Lisc2.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc2, 0.0F, -0.5918411493512771F, 0.0F);
        this.Lisc1 = new ModelRenderer(this, 4, 13);
        this.Lisc1.setRotationPoint(-1.2F, 15.8F, 0.7F);
        this.Lisc1.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc1, 0.0F, 0.5918411493512771F, 0.0F);
        this.MLisc1 = new ModelRenderer(this, 0, 7);
        this.MLisc1.setRotationPoint(-1.4F, 15.9F, 0.3F);
        this.MLisc1.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc1, 0.3490658503988659F, 0.0F, 0.0F);
        this.Shape1 = new ModelRenderer(this, 32, 17);
        this.Shape1.setRotationPoint(-0.7F, 20.2F, -0.5F);
        this.Shape1.func_228301_a_(0.0F, 0.0F, 0.0F, 7, 0, 3, 0.0F);
        this.setRotateAngle(Shape1, 0.0F, 0.0F, -0.6373942428283291F);
        this.MLisc2 = new ModelRenderer(this, 0, 7);
        this.MLisc2.setRotationPoint(0.3F, 15.9F, 1.3F);
        this.MLisc2.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc2, 0.3490658503988659F, 1.593485607070823F, 0.0F);
        this.MLisc3 = new ModelRenderer(this, 0, 7);
        this.MLisc3.setRotationPoint(1.1F, 16.0F, -0.4F);
        this.MLisc3.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc3, 0.3490658503988659F, 3.141592653589793F, 0.0F);
        this.Noga2 = new ModelRenderer(this, 30, 0);
        this.Noga2.setRotationPoint(-1.4F, 18.6F, -0.5F);
        this.Noga2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Noga2, 0.31869712141416456F, 0.31869712141416456F, 0.0F);
        this.Lisc4 = new ModelRenderer(this, 4, 13);
        this.Lisc4.setRotationPoint(0.0F, 15.8F, 1.2F);
        this.Lisc4.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc4, 0.0F, 1.7756979809790308F, 0.0F);
        this.Lisc5 = new ModelRenderer(this, 4, 13);
        this.Lisc5.setRotationPoint(1.3F, 15.8F, 0.0F);
        this.Lisc5.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc5, 0.0F, 3.141592653589793F, 0.0F);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Shape2.offsetX, this.Shape2.offsetY, this.Shape2.offsetZ);
        GlStateManager.translatef(this.Shape2.rotationPointX * f5, this.Shape2.rotationPointY * f5, this.Shape2.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Shape2.offsetX, -this.Shape2.offsetY, -this.Shape2.offsetZ);
        GlStateManager.translatef(-this.Shape2.rotationPointX * f5, -this.Shape2.rotationPointY * f5, -this.Shape2.rotationPointZ * f5);
        this.Shape2.render(f5);
        GlStateManager.popMatrix();
        this.Noga3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Lisc3.offsetX, this.Lisc3.offsetY, this.Lisc3.offsetZ);
        GlStateManager.translatef(this.Lisc3.rotationPointX * f5, this.Lisc3.rotationPointY * f5, this.Lisc3.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Lisc3.offsetX, -this.Lisc3.offsetY, -this.Lisc3.offsetZ);
        GlStateManager.translatef(-this.Lisc3.rotationPointX * f5, -this.Lisc3.rotationPointY * f5, -this.Lisc3.rotationPointZ * f5);
        this.Lisc3.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Pylek.offsetX, this.Pylek.offsetY, this.Pylek.offsetZ);
        GlStateManager.translatef(this.Pylek.rotationPointX * f5, this.Pylek.rotationPointY * f5, this.Pylek.rotationPointZ * f5);
        GlStateManager.scaled(0.8D, 0.8D, 0.8D);
        GlStateManager.translatef(-this.Pylek.offsetX, -this.Pylek.offsetY, -this.Pylek.offsetZ);
        GlStateManager.translatef(-this.Pylek.rotationPointX * f5, -this.Pylek.rotationPointY * f5, -this.Pylek.rotationPointZ * f5);
        this.Pylek.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.MLisc4.offsetX, this.MLisc4.offsetY, this.MLisc4.offsetZ);
        GlStateManager.translatef(this.MLisc4.rotationPointX * f5, this.MLisc4.rotationPointY * f5, this.MLisc4.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.MLisc4.offsetX, -this.MLisc4.offsetY, -this.MLisc4.offsetZ);
        GlStateManager.translatef(-this.MLisc4.rotationPointX * f5, -this.MLisc4.rotationPointY * f5, -this.MLisc4.rotationPointZ * f5);
        this.MLisc4.render(f5);
        GlStateManager.popMatrix();
        this.Noga1.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Lisc2.offsetX, this.Lisc2.offsetY, this.Lisc2.offsetZ);
        GlStateManager.translatef(this.Lisc2.rotationPointX * f5, this.Lisc2.rotationPointY * f5, this.Lisc2.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Lisc2.offsetX, -this.Lisc2.offsetY, -this.Lisc2.offsetZ);
        GlStateManager.translatef(-this.Lisc2.rotationPointX * f5, -this.Lisc2.rotationPointY * f5, -this.Lisc2.rotationPointZ * f5);
        this.Lisc2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Lisc1.offsetX, this.Lisc1.offsetY, this.Lisc1.offsetZ);
        GlStateManager.translatef(this.Lisc1.rotationPointX * f5, this.Lisc1.rotationPointY * f5, this.Lisc1.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Lisc1.offsetX, -this.Lisc1.offsetY, -this.Lisc1.offsetZ);
        GlStateManager.translatef(-this.Lisc1.rotationPointX * f5, -this.Lisc1.rotationPointY * f5, -this.Lisc1.rotationPointZ * f5);
        this.Lisc1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.MLisc1.offsetX, this.MLisc1.offsetY, this.MLisc1.offsetZ);
        GlStateManager.translatef(this.MLisc1.rotationPointX * f5, this.MLisc1.rotationPointY * f5, this.MLisc1.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.MLisc1.offsetX, -this.MLisc1.offsetY, -this.MLisc1.offsetZ);
        GlStateManager.translatef(-this.MLisc1.rotationPointX * f5, -this.MLisc1.rotationPointY * f5, -this.MLisc1.rotationPointZ * f5);
        this.MLisc1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Shape1.offsetX, this.Shape1.offsetY, this.Shape1.offsetZ);
        GlStateManager.translatef(this.Shape1.rotationPointX * f5, this.Shape1.rotationPointY * f5, this.Shape1.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Shape1.offsetX, -this.Shape1.offsetY, -this.Shape1.offsetZ);
        GlStateManager.translatef(-this.Shape1.rotationPointX * f5, -this.Shape1.rotationPointY * f5, -this.Shape1.rotationPointZ * f5);
        this.Shape1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.MLisc2.offsetX, this.MLisc2.offsetY, this.MLisc2.offsetZ);
        GlStateManager.translatef(this.MLisc2.rotationPointX * f5, this.MLisc2.rotationPointY * f5, this.MLisc2.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.MLisc2.offsetX, -this.MLisc2.offsetY, -this.MLisc2.offsetZ);
        GlStateManager.translatef(-this.MLisc2.rotationPointX * f5, -this.MLisc2.rotationPointY * f5, -this.MLisc2.rotationPointZ * f5);
        this.MLisc2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.MLisc3.offsetX, this.MLisc3.offsetY, this.MLisc3.offsetZ);
        GlStateManager.translatef(this.MLisc3.rotationPointX * f5, this.MLisc3.rotationPointY * f5, this.MLisc3.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.MLisc3.offsetX, -this.MLisc3.offsetY, -this.MLisc3.offsetZ);
        GlStateManager.translatef(-this.MLisc3.rotationPointX * f5, -this.MLisc3.rotationPointY * f5, -this.MLisc3.rotationPointZ * f5);
        this.MLisc3.render(f5);
        GlStateManager.popMatrix();
        this.Noga2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Lisc4.offsetX, this.Lisc4.offsetY, this.Lisc4.offsetZ);
        GlStateManager.translatef(this.Lisc4.rotationPointX * f5, this.Lisc4.rotationPointY * f5, this.Lisc4.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Lisc4.offsetX, -this.Lisc4.offsetY, -this.Lisc4.offsetZ);
        GlStateManager.translatef(-this.Lisc4.rotationPointX * f5, -this.Lisc4.rotationPointY * f5, -this.Lisc4.rotationPointZ * f5);
        this.Lisc4.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Lisc5.offsetX, this.Lisc5.offsetY, this.Lisc5.offsetZ);
        GlStateManager.translatef(this.Lisc5.rotationPointX * f5, this.Lisc5.rotationPointY * f5, this.Lisc5.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 0.5D, 0.5D);
        GlStateManager.translatef(-this.Lisc5.offsetX, -this.Lisc5.offsetY, -this.Lisc5.offsetZ);
        GlStateManager.translatef(-this.Lisc5.rotationPointX * f5, -this.Lisc5.rotationPointY * f5, -this.Lisc5.rotationPointZ * f5);
        this.Lisc5.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
      public void setRotationAngles(T entity, float f, float f1, float age, float f3, float f4, float f5 )
      {
        super.setRotationAngles(entity, f, f1, age, f3, f4, f5);
        this.Lisc1.rotateAngleX= MathHelper.abs(MathHelper.sin(age * 0.006662F))*1.4F;
        this.Lisc2.rotateAngleX= MathHelper.abs(MathHelper.sin(age * 0.006662F))*1.4F;
        this.Lisc3.rotateAngleX= MathHelper.abs(MathHelper.sin(age * 0.006662F))*1.4F;
        this.Lisc4.rotateAngleX= MathHelper.abs(MathHelper.sin(age * 0.006662F))*1.4F;
        this.Lisc5.rotateAngleX= MathHelper.abs(MathHelper.sin(age * 0.006662F))*1.4F;
      }
}
