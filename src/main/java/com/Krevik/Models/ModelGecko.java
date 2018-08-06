package com.Krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * gecko - HKhugo
 * Created using Tabula 7.0.0
 */
public class ModelGecko extends ModelBase {
    public ModelRenderer head_1;
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
        this.legmain_3 = new ModelRenderer(this, 0, 0);
        this.legmain_3.setRotationPoint(2.4F, 22.85F, -1.6F);
        this.legmain_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_3, 0.8651597102135892F, 0.22759093446006054F, -0.7740535232594852F);
        this.body_1 = new ModelRenderer(this, 0, 23);
        this.body_1.setRotationPoint(0.0F, 20.5F, -5.8F);
        this.body_1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
        this.tail_1 = new ModelRenderer(this, 0, 16);
        this.tail_1.setRotationPoint(0.25F, 20.6F, -1.4F);
        this.tail_1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(tail_1, 0.27314402793711257F, 0.0F, 0.0F);
        this.tail_3 = new ModelRenderer(this, 11, 11);
        this.tail_3.setRotationPoint(0.8F, 19.8F, 2.2F);
        this.tail_3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail_3, 1.2292353921796064F, 0.004537856055185257F, 0.0F);
        this.head_1 = new ModelRenderer(this, 42, 0);
        this.head_1.setRotationPoint(0.25F, 20.2F, -8.4F);
        this.head_1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
        this.jawbottom = new ModelRenderer(this, 17, 5);
        this.jawbottom.setRotationPoint(0.5F, 21.57F, -9.7F);
        this.jawbottom.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.legmain_4 = new ModelRenderer(this, 0, 0);
        this.legmain_4.setRotationPoint(-0.2F, 22.1F, -1.8F);
        this.legmain_4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_4, 0.7285004297824331F, -0.18203784098300857F, 0.7740535232594852F);
        this.legtwo_4 = new ModelRenderer(this, 0, 4);
        this.legtwo_4.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_4, -1.1838568316277536F, 0.045553093477052F, -0.045553093477052F);
        this.legtwo_3 = new ModelRenderer(this, 0, 4);
        this.legtwo_3.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_3, -1.1838568316277536F, 0.045553093477052F, -0.045553093477052F);
        this.legmain_1 = new ModelRenderer(this, 0, 0);
        this.legmain_1.setRotationPoint(-0.1F, 22.1F, -5.5F);
        this.legmain_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_1, 0.7285004297824331F, -0.18203784098300857F, 0.7740535232594852F);
        this.jawtop = new ModelRenderer(this, 17, 0);
        this.jawtop.setRotationPoint(0.5F, 20.77F, -9.7F);
        this.jawtop.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.legtwo_2 = new ModelRenderer(this, 0, 4);
        this.legtwo_2.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_2, -0.9105382707654417F, 0.045553093477052F, -0.045553093477052F);
        this.legmain_2 = new ModelRenderer(this, 0, 0);
        this.legmain_2.setRotationPoint(2.4F, 22.85F, -5.4F);
        this.legmain_2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_2, 0.8651597102135892F, 0.22759093446006054F, -0.7740535232594852F);
        this.tail_2 = new ModelRenderer(this, 0, 10);
        this.tail_2.setRotationPoint(0.5F, 20.6F, 0.0F);
        this.tail_2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(tail_2, 0.6373942428283291F, 0.0F, 0.0F);
        this.legtwo_1 = new ModelRenderer(this, 0, 4);
        this.legtwo_1.setRotationPoint(-0.1F, 1.4F, 0.5F);
        this.legtwo_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legtwo_1, -0.9105382707654417F, 0.045553093477052F, -0.045553093477052F);
        this.legmain_4.addChild(this.legtwo_4);
        this.legmain_3.addChild(this.legtwo_3);
        this.legmain_2.addChild(this.legtwo_2);
        this.legmain_1.addChild(this.legtwo_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.legmain_3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body_1.offsetX, this.body_1.offsetY, this.body_1.offsetZ);
        GlStateManager.translate(this.body_1.rotationPointX * f5, this.body_1.rotationPointY * f5, this.body_1.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.9D, 1.0D);
        GlStateManager.translate(-this.body_1.offsetX, -this.body_1.offsetY, -this.body_1.offsetZ);
        GlStateManager.translate(-this.body_1.rotationPointX * f5, -this.body_1.rotationPointY * f5, -this.body_1.rotationPointZ * f5);
        this.body_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.tail_1.offsetX, this.tail_1.offsetY, this.tail_1.offsetZ);
        GlStateManager.translate(this.tail_1.rotationPointX * f5, this.tail_1.rotationPointY * f5, this.tail_1.rotationPointZ * f5);
        GlStateManager.scale(0.8D, 0.9D, 0.8D);
        GlStateManager.translate(-this.tail_1.offsetX, -this.tail_1.offsetY, -this.tail_1.offsetZ);
        GlStateManager.translate(-this.tail_1.rotationPointX * f5, -this.tail_1.rotationPointY * f5, -this.tail_1.rotationPointZ * f5);
        this.tail_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.tail_3.offsetX, this.tail_3.offsetY, this.tail_3.offsetZ);
        GlStateManager.translate(this.tail_3.rotationPointX * f5, this.tail_3.rotationPointY * f5, this.tail_3.rotationPointZ * f5);
        GlStateManager.scale(0.7D, 0.7D, 0.7D);
        GlStateManager.translate(-this.tail_3.offsetX, -this.tail_3.offsetY, -this.tail_3.offsetZ);
        GlStateManager.translate(-this.tail_3.rotationPointX * f5, -this.tail_3.rotationPointY * f5, -this.tail_3.rotationPointZ * f5);
        this.tail_3.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.head_1.offsetX, this.head_1.offsetY, this.head_1.offsetZ);
        GlStateManager.translate(this.head_1.rotationPointX * f5, this.head_1.rotationPointY * f5, this.head_1.rotationPointZ * f5);
        GlStateManager.scale(0.6D, 0.6D, 0.6D);
        GlStateManager.translate(-this.head_1.offsetX, -this.head_1.offsetY, -this.head_1.offsetZ);
        GlStateManager.translate(-this.head_1.rotationPointX * f5, -this.head_1.rotationPointY * f5, -this.head_1.rotationPointZ * f5);
        this.head_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.jawbottom.offsetX, this.jawbottom.offsetY, this.jawbottom.offsetZ);
        GlStateManager.translate(this.jawbottom.rotationPointX * f5, this.jawbottom.rotationPointY * f5, this.jawbottom.rotationPointZ * f5);
        GlStateManager.scale(0.6D, 0.8D, 0.6D);
        GlStateManager.translate(-this.jawbottom.offsetX, -this.jawbottom.offsetY, -this.jawbottom.offsetZ);
        GlStateManager.translate(-this.jawbottom.rotationPointX * f5, -this.jawbottom.rotationPointY * f5, -this.jawbottom.rotationPointZ * f5);
        this.jawbottom.render(f5);
        GlStateManager.popMatrix();
        this.legmain_4.render(f5);
        this.legmain_1.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.jawtop.offsetX, this.jawtop.offsetY, this.jawtop.offsetZ);
        GlStateManager.translate(this.jawtop.rotationPointX * f5, this.jawtop.rotationPointY * f5, this.jawtop.rotationPointZ * f5);
        GlStateManager.scale(0.6D, 0.8D, 0.6D);
        GlStateManager.translate(-this.jawtop.offsetX, -this.jawtop.offsetY, -this.jawtop.offsetZ);
        GlStateManager.translate(-this.jawtop.rotationPointX * f5, -this.jawtop.rotationPointY * f5, -this.jawtop.rotationPointZ * f5);
        this.jawtop.render(f5);
        GlStateManager.popMatrix();
        this.legmain_2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.tail_2.offsetX, this.tail_2.offsetY, this.tail_2.offsetZ);
        GlStateManager.translate(this.tail_2.rotationPointX * f5, this.tail_2.rotationPointY * f5, this.tail_2.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.9D, 1.0D);
        GlStateManager.translate(-this.tail_2.offsetX, -this.tail_2.offsetY, -this.tail_2.offsetZ);
        GlStateManager.translate(-this.tail_2.rotationPointX * f5, -this.tail_2.rotationPointY * f5, -this.tail_2.rotationPointZ * f5);
        this.tail_2.render(f5);
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
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float f2, float f3, float f4, float f5, Entity entity)
    {	
    	super.setRotationAngles(limbSwing, limbSwingAmount, f2, f3, f4, f5, entity);
        this.legmain_1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legmain_2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legmain_3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legmain_4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
    
    
}
