package com.krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelMysticBird - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelMysticBird extends ModelBase {
    public ModelRenderer Leg2;
    public ModelRenderer Head;
    public ModelRenderer Nose;
    public ModelRenderer Leg1;
    public ModelRenderer Body;
    public ModelRenderer Wing1;
    public ModelRenderer Wing3;
    public ModelRenderer Wing4;
    public ModelRenderer Wing6;
    public ModelRenderer Wing7;
    public ModelRenderer Wing8;
    public ModelRenderer Wing9;
    public ModelRenderer Wing10;
    public ModelRenderer Wing2;
    public ModelRenderer Wing5;

    public ModelMysticBird() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Wing10 = new ModelRenderer(this, 3, 25);
        this.Wing10.setRotationPoint(-1.35F, 17.81F, -3.8F);
        this.Wing10.addBox(0.0F, 0.0F, 0.0F, 5, 0, 11, 0.0F);
        this.setRotateAngle(Wing10, 0.7285004297824331F, 0.27314402793711257F, -0.9560913642424937F);
        this.Wing5 = new ModelRenderer(this, 0, 98);
        this.Wing5.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.Wing5.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.Wing4 = new ModelRenderer(this, 0, 105);
        this.Wing4.setRotationPoint(-2.3F, 16.8F, -2.0F);
        this.Wing4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(Wing4, 0.0F, 0.091106186954104F, 0.0F);
        this.Wing7 = new ModelRenderer(this, -1, 24);
        this.Wing7.setRotationPoint(-0.9F, 17.9F, 5.2F);
        this.Wing7.addBox(0.0F, 0.0F, 0.0F, 0, 5, 12, 0.0F);
        this.setRotateAngle(Wing7, -0.5009094953223726F, 0.22759093446006054F, -1.0016444577195458F);
        this.Wing8 = new ModelRenderer(this, 3, 25);
        this.Wing8.setRotationPoint(-1.4F, 14.9F, -4.7F);
        this.Wing8.addBox(0.0F, 0.0F, 0.0F, 5, 0, 11, 0.0F);
        this.setRotateAngle(Wing8, 0.5009094953223726F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 60);
        this.Head.setRotationPoint(-1.5F, 14.5F, -6.9F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.Wing3 = new ModelRenderer(this, 0, 105);
        this.Wing3.setRotationPoint(2.5F, 17.8F, -2.0F);
        this.Wing3.addBox(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(Wing3, 0.0F, -0.091106186954104F, 0.0F);
        this.Nose = new ModelRenderer(this, 0, 56);
        this.Nose.setRotationPoint(-0.5F, 16.2F, -8.9F);
        this.Nose.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.Wing6 = new ModelRenderer(this, -1, 24);
        this.Wing6.setRotationPoint(1.2F, 17.6F, 5.2F);
        this.Wing6.addBox(0.0F, 0.0F, 0.0F, 0, 5, 12, 0.0F);
        this.setRotateAngle(Wing6, -0.5009094953223726F, -0.18203784098300857F, 1.0016444577195458F);
        this.Wing2 = new ModelRenderer(this, 0, 98);
        this.Wing2.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.Wing2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.Body = new ModelRenderer(this, 0, 80);
        this.Body.setRotationPoint(-2.4F, 16.7F, -3.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 5, 4, 8, 0.0F);
        this.setRotateAngle(Body, -0.091106186954104F, 0.0F, 0.0F);
        this.Leg2 = new ModelRenderer(this, 7, 99);
        this.Leg2.setRotationPoint(1.1F, 20.0F, 0.0F);
        this.Leg2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.Wing1 = new ModelRenderer(this, -1, 24);
        this.Wing1.setRotationPoint(-2.5F, 17.7F, 3.9F);
        this.Wing1.addBox(0.0F, 0.0F, 0.0F, 0, 5, 12, 0.0F);
        this.setRotateAngle(Wing1, 0.0F, 0.18203784098300857F, -1.5707963267948966F);
        this.Wing9 = new ModelRenderer(this, 3, 25);
        this.Wing9.setRotationPoint(-0.2F, 15.31F, -4.5F);
        this.Wing9.addBox(0.0F, 0.0F, 0.0F, 5, 0, 11, 0.0F);
        this.setRotateAngle(Wing9, 0.6373942428283291F, -0.18203784098300857F, 0.9105382707654417F);
        this.Leg1 = new ModelRenderer(this, 7, 99);
        this.Leg1.setRotationPoint(-2.2F, 20.0F, 0.0F);
        this.Leg1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.Wing4.addChild(this.Wing5);
        this.Wing3.addChild(this.Wing2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Wing10.offsetX, this.Wing10.offsetY, this.Wing10.offsetZ);
        GlStateManager.translate(this.Wing10.rotationPointX * f5, this.Wing10.rotationPointY * f5, this.Wing10.rotationPointZ * f5);
        GlStateManager.scale(0.6D, 0.6D, 0.6D);
        GlStateManager.translate(-this.Wing10.offsetX, -this.Wing10.offsetY, -this.Wing10.offsetZ);
        GlStateManager.translate(-this.Wing10.rotationPointX * f5, -this.Wing10.rotationPointY * f5, -this.Wing10.rotationPointZ * f5);
        this.Wing10.render(f5);
        GlStateManager.popMatrix();
        this.Wing4.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Wing7.offsetX, this.Wing7.offsetY, this.Wing7.offsetZ);
        GlStateManager.translate(this.Wing7.rotationPointX * f5, this.Wing7.rotationPointY * f5, this.Wing7.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.6D, 1.0D);
        GlStateManager.translate(-this.Wing7.offsetX, -this.Wing7.offsetY, -this.Wing7.offsetZ);
        GlStateManager.translate(-this.Wing7.rotationPointX * f5, -this.Wing7.rotationPointY * f5, -this.Wing7.rotationPointZ * f5);
        this.Wing7.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Wing8.offsetX, this.Wing8.offsetY, this.Wing8.offsetZ);
        GlStateManager.translate(this.Wing8.rotationPointX * f5, this.Wing8.rotationPointY * f5, this.Wing8.rotationPointZ * f5);
        GlStateManager.scale(0.6D, 0.6D, 0.6D);
        GlStateManager.translate(-this.Wing8.offsetX, -this.Wing8.offsetY, -this.Wing8.offsetZ);
        GlStateManager.translate(-this.Wing8.rotationPointX * f5, -this.Wing8.rotationPointY * f5, -this.Wing8.rotationPointZ * f5);
        this.Wing8.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GlStateManager.translate(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scale(0.8D, 0.9D, 1.0D);
        GlStateManager.translate(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GlStateManager.translate(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        this.Wing3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Nose.offsetX, this.Nose.offsetY, this.Nose.offsetZ);
        GlStateManager.translate(this.Nose.rotationPointX * f5, this.Nose.rotationPointY * f5, this.Nose.rotationPointZ * f5);
        GlStateManager.scale(1.2D, 1.1D, 1.0D);
        GlStateManager.translate(-this.Nose.offsetX, -this.Nose.offsetY, -this.Nose.offsetZ);
        GlStateManager.translate(-this.Nose.rotationPointX * f5, -this.Nose.rotationPointY * f5, -this.Nose.rotationPointZ * f5);
        this.Nose.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Wing6.offsetX, this.Wing6.offsetY, this.Wing6.offsetZ);
        GlStateManager.translate(this.Wing6.rotationPointX * f5, this.Wing6.rotationPointY * f5, this.Wing6.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.6D, 1.0D);
        GlStateManager.translate(-this.Wing6.offsetX, -this.Wing6.offsetY, -this.Wing6.offsetZ);
        GlStateManager.translate(-this.Wing6.rotationPointX * f5, -this.Wing6.rotationPointY * f5, -this.Wing6.rotationPointZ * f5);
        this.Wing6.render(f5);
        GlStateManager.popMatrix();
        this.Body.render(f5);
        this.Leg2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Wing1.offsetX, this.Wing1.offsetY, this.Wing1.offsetZ);
        GlStateManager.translate(this.Wing1.rotationPointX * f5, this.Wing1.rotationPointY * f5, this.Wing1.rotationPointZ * f5);
        GlStateManager.scale(1.0D, 0.6D, 1.0D);
        GlStateManager.translate(-this.Wing1.offsetX, -this.Wing1.offsetY, -this.Wing1.offsetZ);
        GlStateManager.translate(-this.Wing1.rotationPointX * f5, -this.Wing1.rotationPointY * f5, -this.Wing1.rotationPointZ * f5);
        this.Wing1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Wing9.offsetX, this.Wing9.offsetY, this.Wing9.offsetZ);
        GlStateManager.translate(this.Wing9.rotationPointX * f5, this.Wing9.rotationPointY * f5, this.Wing9.rotationPointZ * f5);
        GlStateManager.scale(0.6D, 0.6D, 0.6D);
        GlStateManager.translate(-this.Wing9.offsetX, -this.Wing9.offsetY, -this.Wing9.offsetZ);
        GlStateManager.translate(-this.Wing9.rotationPointX * f5, -this.Wing9.rotationPointY * f5, -this.Wing9.rotationPointZ * f5);
        this.Wing9.render(f5);
        GlStateManager.popMatrix();
        this.Leg1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    float timer=0;
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	timer+=0.1F;
	if(timer>90) {
		timer=0;
	}
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Wing3.rotateAngleZ=(float)(-MathHelper.sin(timer))*f1*2;
    Wing4.rotateAngleZ=(float)(MathHelper.sin(timer))*f1*2;

  }

}
