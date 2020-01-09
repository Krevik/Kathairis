package io.github.krevik.kathairis.client.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * cloud oister - HK DB
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelCloudOister<T extends LivingEntity> extends EntityModel<T> {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer body2;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape1_10;
    public ModelRenderer shape1_11;
    public ModelRenderer body2_1;
    public ModelRenderer shape1_12;
    public ModelRenderer body2_2;
    public ModelRenderer shape1_13;

    public ModelCloudOister() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1_10 = new ModelRenderer(this, 0, 0);
        this.shape1_10.setRotationPoint(1.1F, 22.6F, -2.1F);
        this.shape1_10.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(shape1_10, -0.18378317023500287F, 2.457423586808016F, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 0, 0);
        this.shape1_3.setRotationPoint(-0.7F, 23.7F, -1.0F);
        this.shape1_3.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(shape1_3, 0.0F, 0.36425021489121656F, -0.27314402793711257F);
        this.shape1_11 = new ModelRenderer(this, 0, 3);
        this.shape1_11.setRotationPoint(2.0F, 22.7F, -2.2F);
        this.shape1_11.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.setRotateAngle(shape1_11, -0.40980330836826856F, 3.141592653589793F, 0.0F);
        this.shape1_12 = new ModelRenderer(this, 0, 0);
        this.shape1_12.setRotationPoint(0.7F, 0.0F, 1.0F);
        this.shape1_12.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(shape1_12, 0.0F, 0.36425021489121656F, 0.40980330836826856F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-1.4F, 23.1F, -4.0F);
        this.shape1.func_228301_a_(0.0F, 0.0F, 1.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(shape1, -0.136659280431156F, -0.6373942428283291F, 0.7285004297824331F);
        this.shape1_6 = new ModelRenderer(this, 18, 4);
        this.shape1_6.setRotationPoint(1.2F, 21.4F, 0.5F);
        this.shape1_6.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape1_6, 0.0F, 0.36425021489121656F, 0.0F);
        this.shape1_8 = new ModelRenderer(this, 0, 0);
        this.shape1_8.setRotationPoint(-1.0F, 22.7F, -5.8F);
        this.shape1_8.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(shape1_8, 0.18203784098300857F, 0.0F, 0.0F);
        this.shape1_13 = new ModelRenderer(this, 0, 0);
        this.shape1_13.setRotationPoint(0.8F, 0.4F, 0.9F);
        this.shape1_13.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(shape1_13, 0.136659280431156F, 0.27314402793711257F, 0.31869712141416456F);
        this.shape1_1 = new ModelRenderer(this, 0, 0);
        this.shape1_1.setRotationPoint(-1.0F, 23.3F, -2.2F);
        this.shape1_1.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.shape1_7 = new ModelRenderer(this, 14, 4);
        this.shape1_7.setRotationPoint(-2.6F, 21.4F, 0.2F);
        this.shape1_7.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape1_7, 0.0F, -0.36425021489121656F, 0.0F);
        this.shape1_9 = new ModelRenderer(this, 0, 0);
        this.shape1_9.setRotationPoint(0.7F, 22.6F, -3.4F);
        this.shape1_9.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(shape1_9, -0.18378317023500287F, -2.4586453172844123F, 0.0F);
        this.body2_2 = new ModelRenderer(this, 0, 0);
        this.body2_2.setRotationPoint(1.7F, -0.2F, 0.1F);
        this.body2_2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(body2_2, 0.0F, 0.7285004297824331F, 0.5009094953223726F);
        this.shape1_5 = new ModelRenderer(this, 16, 0);
        this.shape1_5.setRotationPoint(-1.1F, 20.8F, -0.3F);
        this.shape1_5.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 0);
        this.shape1_4.setRotationPoint(-1.9F, 23.2F, -2.0F);
        this.shape1_4.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.setRotateAngle(shape1_4, 0.0F, -0.36425021489121656F, 0.27314402793711257F);
        this.body2_1 = new ModelRenderer(this, 0, 0);
        this.body2_1.setRotationPoint(-1.3F, 0.2F, -1.2F);
        this.body2_1.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(body2_1, 0.0F, -0.6829473363053812F, -0.5009094953223726F);
        this.shape1_2 = new ModelRenderer(this, 0, 0);
        this.shape1_2.setRotationPoint(0.5F, 23.75F, -1.9F);
        this.shape1_2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(shape1_2, -0.136659280431156F, 0.6829473363053812F, -0.7285004297824331F);
        this.body2 = new ModelRenderer(this, 0, 0);
        this.body2.setRotationPoint(-0.9F, 22.3F, -3.0F);
        this.body2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
        this.setRotateAngle(body2, 1.2747884856566583F, 0.0F, 0.0F);
        this.body2.addChild(this.shape1_12);
        this.body2_1.addChild(this.shape1_13);
        this.body2.addChild(this.body2_2);
        this.body2.addChild(this.body2_1);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_10.offsetX, this.shape1_10.offsetY, this.shape1_10.offsetZ);
        GlStateManager.translatef(this.shape1_10.rotationPointX * f5, this.shape1_10.rotationPointY * f5, this.shape1_10.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.6D, 1.0D);
        GlStateManager.translatef(-this.shape1_10.offsetX, -this.shape1_10.offsetY, -this.shape1_10.offsetZ);
        GlStateManager.translatef(-this.shape1_10.rotationPointX * f5, -this.shape1_10.rotationPointY * f5, -this.shape1_10.rotationPointZ * f5);
        this.shape1_10.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_3.offsetX, this.shape1_3.offsetY, this.shape1_3.offsetZ);
        GlStateManager.translatef(this.shape1_3.rotationPointX * f5, this.shape1_3.rotationPointY * f5, this.shape1_3.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.6D, 1.0D);
        GlStateManager.translatef(-this.shape1_3.offsetX, -this.shape1_3.offsetY, -this.shape1_3.offsetZ);
        GlStateManager.translatef(-this.shape1_3.rotationPointX * f5, -this.shape1_3.rotationPointY * f5, -this.shape1_3.rotationPointZ * f5);
        this.shape1_3.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_11.offsetX, this.shape1_11.offsetY, this.shape1_11.offsetZ);
        GlStateManager.translatef(this.shape1_11.rotationPointX * f5, this.shape1_11.rotationPointY * f5, this.shape1_11.rotationPointZ * f5);
        GlStateManager.scaled(1.1D, 0.8D, 1.0D);
        GlStateManager.translatef(-this.shape1_11.offsetX, -this.shape1_11.offsetY, -this.shape1_11.offsetZ);
        GlStateManager.translatef(-this.shape1_11.rotationPointX * f5, -this.shape1_11.rotationPointY * f5, -this.shape1_11.rotationPointZ * f5);
        this.shape1_11.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1.offsetX, this.shape1.offsetY, this.shape1.offsetZ);
        GlStateManager.translatef(this.shape1.rotationPointX * f5, this.shape1.rotationPointY * f5, this.shape1.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.7D, 1.0D);
        GlStateManager.translatef(-this.shape1.offsetX, -this.shape1.offsetY, -this.shape1.offsetZ);
        GlStateManager.translatef(-this.shape1.rotationPointX * f5, -this.shape1.rotationPointY * f5, -this.shape1.rotationPointZ * f5);
        this.shape1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_6.offsetX, this.shape1_6.offsetY, this.shape1_6.offsetZ);
        GlStateManager.translatef(this.shape1_6.rotationPointX * f5, this.shape1_6.rotationPointY * f5, this.shape1_6.rotationPointZ * f5);
        GlStateManager.scaled(1.3D, 1.3D, 1.3D);
        GlStateManager.translatef(-this.shape1_6.offsetX, -this.shape1_6.offsetY, -this.shape1_6.offsetZ);
        GlStateManager.translatef(-this.shape1_6.rotationPointX * f5, -this.shape1_6.rotationPointY * f5, -this.shape1_6.rotationPointZ * f5);
        this.shape1_6.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_8.offsetX, this.shape1_8.offsetY, this.shape1_8.offsetZ);
        GlStateManager.translatef(this.shape1_8.rotationPointX * f5, this.shape1_8.rotationPointY * f5, this.shape1_8.rotationPointZ * f5);
        GlStateManager.scaled(1.1D, 0.8D, 1.0D);
        GlStateManager.translatef(-this.shape1_8.offsetX, -this.shape1_8.offsetY, -this.shape1_8.offsetZ);
        GlStateManager.translatef(-this.shape1_8.rotationPointX * f5, -this.shape1_8.rotationPointY * f5, -this.shape1_8.rotationPointZ * f5);
        this.shape1_8.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_1.offsetX, this.shape1_1.offsetY, this.shape1_1.offsetZ);
        GlStateManager.translatef(this.shape1_1.rotationPointX * f5, this.shape1_1.rotationPointY * f5, this.shape1_1.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.7D, 1.0D);
        GlStateManager.translatef(-this.shape1_1.offsetX, -this.shape1_1.offsetY, -this.shape1_1.offsetZ);
        GlStateManager.translatef(-this.shape1_1.rotationPointX * f5, -this.shape1_1.rotationPointY * f5, -this.shape1_1.rotationPointZ * f5);
        this.shape1_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_7.offsetX, this.shape1_7.offsetY, this.shape1_7.offsetZ);
        GlStateManager.translatef(this.shape1_7.rotationPointX * f5, this.shape1_7.rotationPointY * f5, this.shape1_7.rotationPointZ * f5);
        GlStateManager.scaled(1.3D, 1.3D, 1.3D);
        GlStateManager.translatef(-this.shape1_7.offsetX, -this.shape1_7.offsetY, -this.shape1_7.offsetZ);
        GlStateManager.translatef(-this.shape1_7.rotationPointX * f5, -this.shape1_7.rotationPointY * f5, -this.shape1_7.rotationPointZ * f5);
        this.shape1_7.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_9.offsetX, this.shape1_9.offsetY, this.shape1_9.offsetZ);
        GlStateManager.translatef(this.shape1_9.rotationPointX * f5, this.shape1_9.rotationPointY * f5, this.shape1_9.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.6D, 1.0D);
        GlStateManager.translatef(-this.shape1_9.offsetX, -this.shape1_9.offsetY, -this.shape1_9.offsetZ);
        GlStateManager.translatef(-this.shape1_9.rotationPointX * f5, -this.shape1_9.rotationPointY * f5, -this.shape1_9.rotationPointZ * f5);
        this.shape1_9.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_5.offsetX, this.shape1_5.offsetY, this.shape1_5.offsetZ);
        GlStateManager.translatef(this.shape1_5.rotationPointX * f5, this.shape1_5.rotationPointY * f5, this.shape1_5.rotationPointZ * f5);
        GlStateManager.scaled(0.35D, 0.35D, 0.35D);
        GlStateManager.translatef(-this.shape1_5.offsetX, -this.shape1_5.offsetY, -this.shape1_5.offsetZ);
        GlStateManager.translatef(-this.shape1_5.rotationPointX * f5, -this.shape1_5.rotationPointY * f5, -this.shape1_5.rotationPointZ * f5);
        this.shape1_5.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_4.offsetX, this.shape1_4.offsetY, this.shape1_4.offsetZ);
        GlStateManager.translatef(this.shape1_4.rotationPointX * f5, this.shape1_4.rotationPointY * f5, this.shape1_4.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.6D, 1.0D);
        GlStateManager.translatef(-this.shape1_4.offsetX, -this.shape1_4.offsetY, -this.shape1_4.offsetZ);
        GlStateManager.translatef(-this.shape1_4.rotationPointX * f5, -this.shape1_4.rotationPointY * f5, -this.shape1_4.rotationPointZ * f5);
        this.shape1_4.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.shape1_2.offsetX, this.shape1_2.offsetY, this.shape1_2.offsetZ);
        GlStateManager.translatef(this.shape1_2.rotationPointX * f5, this.shape1_2.rotationPointY * f5, this.shape1_2.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.7D, 1.0D);
        GlStateManager.translatef(-this.shape1_2.offsetX, -this.shape1_2.offsetY, -this.shape1_2.offsetZ);
        GlStateManager.translatef(-this.shape1_2.rotationPointX * f5, -this.shape1_2.rotationPointY * f5, -this.shape1_2.rotationPointZ * f5);
        this.shape1_2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.body2.offsetX, this.body2.offsetY, this.body2.offsetZ);
        GlStateManager.translatef(this.body2.rotationPointX * f5, this.body2.rotationPointY * f5, this.body2.rotationPointZ * f5);
        GlStateManager.scaled(1.0D, 0.7D, 1.2D);
        GlStateManager.translatef(-this.body2.offsetX, -this.body2.offsetY, -this.body2.offsetZ);
        GlStateManager.translatef(-this.body2.rotationPointX * f5, -this.body2.rotationPointY * f5, -this.body2.rotationPointZ * f5);
        this.body2.render(f5);
        GlStateManager.popMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5)
    {
     super.setRotationAngles(entity, limbSwing, limbSwingAmount, age, f3, f4, f5);
    }
}
