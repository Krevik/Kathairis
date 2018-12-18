package com.krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelPlayer - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelSteveGhost extends ModelBase {
    public ModelRenderer RightArm;
    public ModelRenderer LeftLeg;
    public ModelRenderer Head;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftArm;
    public ModelRenderer Body;

    public ModelSteveGhost() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RightArm = new ModelRenderer(this, 48, 48);
        this.RightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.RightArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.setRotateAngle(RightArm, -0.4363323129985824F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 32);
        this.LeftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.RightLeg = new ModelRenderer(this, 0, 48);
        this.RightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.Head = new ModelRenderer(this, 24, 8);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.LeftArm = new ModelRenderer(this, 40, 27);
        this.LeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.LeftArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftArm, -0.4363323129985824F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 16, 32);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.RightArm.render(f5);
        this.LeftLeg.render(f5);
        this.RightLeg.render(f5);
        this.Head.render(f5);
        this.LeftArm.render(f5);
        this.Body.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float ff = 1.0F;
        this.RightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F / ff;
        this.LeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F / ff;
        this.RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 / ff;
        this.LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1 / ff;
    }
}
