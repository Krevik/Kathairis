package com.Krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHowler extends ModelBase
{
  //fields
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Body;
    ModelRenderer Neck;
    ModelRenderer Head;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
  
  public ModelHowler()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Leg1 = new ModelRenderer(this, 0, 20);
      Leg1.addBox(0F, 0F, 0F, 2, 6, 2);
      Leg1.setRotationPoint(-4F, 18F, 0F);
      Leg1.setTextureSize(128, 128);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Leg2 = new ModelRenderer(this, 0, 20);
      Leg2.addBox(0F, 0F, 0F, 2, 6, 2);
      Leg2.setRotationPoint(2F, 18F, 0F);
      Leg2.setTextureSize(128, 128);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 0, 60);
      Body.addBox(0F, 0F, 0F, 8, 8, 4);
      Body.setRotationPoint(-4F, 12F, -4F);
      Body.setTextureSize(128, 128);
      Body.mirror = true;
      setRotation(Body, 0.4758862F, 0F, 0F);
      Neck = new ModelRenderer(this, 0, 80);
      Neck.addBox(0F, 0F, 0F, 2, 3, 2);
      Neck.setRotationPoint(-1F, 9F, -4F);
      Neck.setTextureSize(128, 128);
      Neck.mirror = true;
      setRotation(Neck, 0.5056291F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(0F, 0F, 0F, 6, 6, 6);
      Head.setRotationPoint(-3F, 3F, -6F);
      Head.setTextureSize(128, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Arm1 = new ModelRenderer(this, 0, 40);
      Arm1.addBox(0F, 0F, 0F, 2, 12, 2);
      Arm1.setRotationPoint(4F, 12F, -3F);
      Arm1.setTextureSize(128, 128);
      Arm1.mirror = true;
      setRotation(Arm1, -0.2974289F, 0F, 0F);
      Arm2 = new ModelRenderer(this, 0, 40);
      Arm2.addBox(0F, 0F, 0F, 2, 12, 2);
      Arm2.setRotationPoint(-6F, 12F, -3F);
      Arm2.setTextureSize(128, 128);
      Arm2.mirror = true;
      setRotation(Arm2, -0.2974289F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Leg1.render(f5);
    Leg2.render(f5);
    Body.render(f5);
    Neck.render(f5);
    Head.render(f5);
    Arm1.render(f5);
    Arm2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
	  
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.Leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    this.Leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    
    this.Arm1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    this.Arm2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
  }

}
