package com.Krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * BigTurtle.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelBigTurtle extends ModelBase {
    public ModelRenderer Noga1;
    public ModelRenderer Noga2;
    public ModelRenderer Noga3;
    public ModelRenderer Noga4;
    public ModelRenderer Tulow1;
    public ModelRenderer Tulow2;
    public ModelRenderer Tulow3;
    public ModelRenderer Glowa;

    public ModelBigTurtle() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Tulow1 = new ModelRenderer(this, 0, 0);
        this.Tulow1.setRotationPoint(-7.0F, 15.0F, -7.0F);
        this.Tulow1.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
        this.Noga2 = new ModelRenderer(this, 0, 100);
        this.Noga2.setRotationPoint(5.0F, 19.0F, -8.0F);
        this.Noga2.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Glowa = new ModelRenderer(this, 70, 0);
        this.Glowa.setRotationPoint(-3.0F, 13.0F, -13.0F);
        this.Glowa.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.Noga1 = new ModelRenderer(this, 0, 100);
        this.Noga1.setRotationPoint(-8.0F, 19.0F, 5.0F);
        this.Noga1.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Noga3 = new ModelRenderer(this, 0, 100);
        this.Noga3.setRotationPoint(-8.0F, 19.0F, -8.0F);
        this.Noga3.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Noga4 = new ModelRenderer(this, 0, 100);
        this.Noga4.setRotationPoint(5.0F, 19.0F, 5.0F);
        this.Noga4.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Tulow2 = new ModelRenderer(this, 0, 50);
        this.Tulow2.setRotationPoint(-8.0F, 15.0F, -7.0F);
        this.Tulow2.addBox(0.0F, 0.0F, 0.0F, 16, 1, 15, 0.0F);
        this.Tulow3 = new ModelRenderer(this, 0, 70);
        this.Tulow3.setRotationPoint(-7.0F, 11.0F, -7.0F);
        this.Tulow3.addBox(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Tulow1.render(f5);
        this.Noga2.render(f5);
        this.Glowa.render(f5);
        this.Noga1.render(f5);
        this.Noga3.render(f5);
        this.Noga4.render(f5);
        this.Tulow2.render(f5);
        this.Tulow3.render(f5);
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
        this.Noga1.rotateAngleX = MathHelper.cos(limbSwing * 2F) * 1.4F * limbSwingAmount;
        this.Noga2.rotateAngleX = MathHelper.cos(limbSwing * 2F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.Noga3.rotateAngleX = MathHelper.cos(limbSwing * 2F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.Noga4.rotateAngleX = MathHelper.cos(limbSwing * 2F) * 1.4F * limbSwingAmount;
    }
}
