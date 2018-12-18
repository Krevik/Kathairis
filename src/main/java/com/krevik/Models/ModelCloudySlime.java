package com.krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCloudySlime extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape1_10;
    public ModelRenderer shape1_11;
    public ModelRenderer shape1_12;
    public ModelRenderer shape1_13;
    public ModelRenderer shape1_14;
    public ModelRenderer shape1_15;

    public ModelCloudySlime() {
        this.textureWidth = 120;
        this.textureHeight = 64;
        this.shape1_2 = new ModelRenderer(this, 0, 0);
        this.shape1_2.setRotationPoint(1.0F, 16.499999999999968F, -13.0F);
        this.shape1_2.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(shape1_2, 0.0F, 0.0F, 0.8196066167365371F);
        this.shape1_6 = new ModelRenderer(this, 0, 0);
        this.shape1_6.setRotationPoint(3.0F, 13.499999999999975F, -6.0F);
        this.shape1_6.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
        this.setRotateAngle(shape1_6, 0.36425021489121656F, 0.27314402793711257F, 0.136659280431156F);
        this.shape1_7 = new ModelRenderer(this, 0, 0);
        this.shape1_7.setRotationPoint(5.0F, 10.499999999999986F, -13.0F);
        this.shape1_7.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(shape1_7, 0.091106186954104F, -0.136659280431156F, 0.40980330836826856F);
        this.shape1_8 = new ModelRenderer(this, 0, 0);
        this.shape1_8.setRotationPoint(-11.0F, 12.499999999999979F, 3.2F);
        this.shape1_8.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
        this.setRotateAngle(shape1_8, 0.0F, 0.40980330836826856F, 0.31869712141416456F);
        this.shape1_15 = new ModelRenderer(this, 0, 0);
        this.shape1_15.setRotationPoint(0.0F, 11.499999999999982F, 6.0F);
        this.shape1_15.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(shape1_15, 0.136659280431156F, 0.0F, 0.0F);
        this.shape1_3 = new ModelRenderer(this, 0, 0);
        this.shape1_3.setRotationPoint(-9.0F, 17.499999999999982F, -10.0F);
        this.shape1_3.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.shape1_10 = new ModelRenderer(this, 0, 0);
        this.shape1_10.setRotationPoint(0.0F, 15.799999999999963F, 7.0F);
        this.shape1_10.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(shape1_10, 0.40980330836826856F, -0.045553093477052F, 0.6373942428283291F);
        this.shape1_9 = new ModelRenderer(this, 0, 0);
        this.shape1_9.setRotationPoint(4.0F, 12.499999999999979F, 7.0F);
        this.shape1_9.addBox(0.0F, 0.0F, 0.0F, 7, 7, 7, 0.0F);
        this.setRotateAngle(shape1_9, -0.40980330836826856F, 0.36425021489121656F, 0.0F);
        this.shape1_4 = new ModelRenderer(this, 0, 30);
        this.shape1_4.setRotationPoint(-7.5F, 2.4999999999999916F, -6.8F);
        this.shape1_4.addBox(0.0F, 0.0F, 0.0F, 14, 14, 16, 0.0F);
        this.setRotateAngle(shape1_4, 1.5707963267948966F, 0.0F, 1.5707963267948966F);
        this.shape1_13 = new ModelRenderer(this, 0, 0);
        this.shape1_13.setRotationPoint(-2.0F, 13.499999999999975F, -2.0F);
        this.shape1_13.addBox(0.0F, 0.0F, 0.0F, 8, 4, 9, 0.0F);
        this.setRotateAngle(shape1_13, 0.0F, 2.504198410761464F, 0.0F);
        this.shape1_12 = new ModelRenderer(this, 0, 0);
        this.shape1_12.setRotationPoint(-15.0F, 15.499999999999966F, -3.0F);
        this.shape1_12.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(shape1_12, 0.0F, 0.5918411493512771F, -0.22759093446006054F);
        this.shape1_1 = new ModelRenderer(this, 60, 30);
        this.shape1_1.setRotationPoint(-6.7F, 2.4999999999999916F, -7.9F);
        this.shape1_1.addBox(0.0F, 0.0F, 0.0F, 14, 14, 16, 0.0F);
        this.shape1 = new ModelRenderer(this, 63, 30);
        this.shape1.setRotationPoint(-6.6F, 1.4999999999999873F, -6.8F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 14, 16, 14, 0.0F);
        this.shape1_11 = new ModelRenderer(this, 0, 0);
        this.shape1_11.setRotationPoint(-12.0F, 12.499999999999979F, -11.0F);
        this.shape1_11.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(shape1_11, 0.091106186954104F, 0.31869712141416456F, 0.0F);
        this.shape1_14 = new ModelRenderer(this, 0, 0);
        this.shape1_14.setRotationPoint(3.0F, 19.500000000000004F, 9.0F);
        this.shape1_14.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.shape1_5 = new ModelRenderer(this, 0, 0);
        this.shape1_5.setRotationPoint(-8.5F, 14.29999999999997F, -9.0F);
        this.shape1_5.addBox(0.0F, 0.0F, 0.0F, 18, 7, 19, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1_2.render(f5);
        this.shape1_6.render(f5);
        this.shape1_7.render(f5);
        this.shape1_8.render(f5);
        this.shape1_15.render(f5);
        this.shape1_3.render(f5);
        this.shape1_10.render(f5);
        this.shape1_9.render(f5);
        this.shape1_4.render(f5);
        this.shape1_13.render(f5);
        this.shape1_12.render(f5);
        this.shape1_1.render(f5);
        this.shape1.render(f5);
        this.shape1_11.render(f5);
        this.shape1_14.render(f5);
        this.shape1_5.render(f5);
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
