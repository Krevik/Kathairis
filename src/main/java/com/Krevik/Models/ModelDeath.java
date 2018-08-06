package com.Krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelDeath - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelDeath extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer LeftArm;
    public ModelRenderer RightArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Kijak;
    public ModelRenderer Ostrze;

    public ModelDeath() {
        this.textureWidth = 256;
        this.textureHeight = 124;
        this.Head = new ModelRenderer(this, 4, 0);
        this.Head.setRotationPoint(-4.0F, -32.0F, -2.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 16, 16, 16, 0.0F);
        this.LeftArm = new ModelRenderer(this, 142, 41);
        this.LeftArm.setRotationPoint(-13.0F, -22.0F, 0.0F);
        this.LeftArm.addBox(-3.0F, -2.0F, -2.0F, 8, 24, 8, 0.0F);
        this.setRotateAngle(LeftArm, -1.2566370614359172F, 0.0F, 0.0F);
        this.RightLeg = new ModelRenderer(this, 42, 82);
        this.RightLeg.setRotationPoint(1.9F, 0.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 8, 24, 8, 0.0F);
        this.Body = new ModelRenderer(this, 81, 0);
        this.Body.setRotationPoint(-4.0F, -24.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 16, 24, 8, 0.25F);
        this.Kijak = new ModelRenderer(this, 0, 0);
        this.Kijak.setRotationPoint(-0.0F, 6.0F, -40.0F);
        this.Kijak.addBox(0.0F, 0.0F, 0.0F, 1, 82, 1, 0.0F);
        this.setRotateAngle(Kijak, 1.2566370614359172F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 87);
        this.LeftLeg.setRotationPoint(-6.0F, 0.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 8, 24, 8, 0.0F);
        this.RightArm = new ModelRenderer(this, 144, 0);
        this.RightArm.setRotationPoint(9.0F, -22.0F, 0.0F);
        this.RightArm.addBox(-1.0F, -2.0F, -2.0F, 8, 24, 8, 0.0F);
        this.Ostrze = new ModelRenderer(this, 15, 42);
        this.Ostrze.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.Ostrze.addBox(0.0F, 0.0F, 0.0F, 31, 8, 1, 0.0F);
        this.LeftArm.addChild(this.Kijak);
        this.Kijak.addChild(this.Ostrze);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Head.render(f5);
        this.LeftArm.render(f5);
        this.RightLeg.render(f5);
        this.Body.render(f5);
        this.LeftLeg.render(f5);
        this.RightArm.render(f5);
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
    }
}
