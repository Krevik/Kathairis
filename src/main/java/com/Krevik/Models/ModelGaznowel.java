package com.Krevik.Models;

import com.Krevik.Main.FunctionHelper;
import com.Krevik.Main.KCore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelGaznowel - Undefined
 * Created using Tabula 7.0.0
 */
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelGaznowel - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelGaznowel extends ModelBase {
    public ModelRenderer Head1;
    public ModelRenderer Body1;
    public ModelRenderer RightArm1;
    public ModelRenderer LeftArm1;
    public ModelRenderer RightLeg1;
    public ModelRenderer LeftLeg1;
    public ModelRenderer Cloud1;
    public ModelRenderer Head3;
    public ModelRenderer RightEyeHole;
    public ModelRenderer LeftEyeHole;
    public ModelRenderer NoseHole;
    public ModelRenderer Head2;
    public ModelRenderer Body2;
    public ModelRenderer RightArm2;
    public ModelRenderer LeftArm2;
    public ModelRenderer RightLeg2;
    public ModelRenderer LeftLeg2;
    public ModelRenderer Cloud2;
    public ModelRenderer Cloud3;
    public ModelRenderer Cloud4;
    public ModelRenderer Cloud5;
    public ModelRenderer Cloud6;

    public ModelGaznowel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Cloud4 = new ModelRenderer(this, 28, 0);
        this.Cloud4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloud4.addBox(-4.0F, -2.0F, -6.0F, 6, 2, 7, 0.0F);
        this.RightArm1 = new ModelRenderer(this, 42, 18);
        this.RightArm1.setRotationPoint(-3.5F, -3.0F, 0.0F);
        this.RightArm1.addBox(-1.5F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(RightArm1, 0.5235987755982988F, 0.08726646259971647F, 0.4363323129985824F);
        this.Cloud3 = new ModelRenderer(this, 0, 6);
        this.Cloud3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloud3.addBox(-6.0F, -3.0F, -2.0F, 5, 4, 8, 0.0F);
        this.LeftArm1 = new ModelRenderer(this, 42, 18);
        this.LeftArm1.mirror = true;
        this.LeftArm1.setRotationPoint(3.5F, -3.0F, 0.0F);
        this.LeftArm1.addBox(-0.5F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(LeftArm1, 0.5235987755982988F, -0.08726646259971647F, -0.4363323129985824F);
        this.LeftLeg2 = new ModelRenderer(this, 51, 28);
        this.LeftLeg2.mirror = true;
        this.LeftLeg2.setRotationPoint(0.0F, 8.5F, -0.5F);
        this.LeftLeg2.addBox(-1.5F, 1.5F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(LeftLeg2, 1.5707963267948966F, 0.0F, 0.0F);
        this.RightLeg2 = new ModelRenderer(this, 51, 28);
        this.RightLeg2.setRotationPoint(0.0F, 8.5F, -0.5F);
        this.RightLeg2.addBox(-1.5F, 1.5F, -1.5F, 3, 8, 3, 0.0F);
        this.setRotateAngle(RightLeg2, 1.5707963267948966F, 0.0F, 0.0F);
        this.Cloud5 = new ModelRenderer(this, 26, 9);
        this.Cloud5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloud5.addBox(0.0F, -6.0F, -5.0F, 8, 3, 6, 0.0F);
        this.Head2 = new ModelRenderer(this, 0, 38);
        this.Head2.setRotationPoint(0.0F, 0.0F, -2.0F);
        this.Head2.addBox(-3.0F, 0.0F, -6.0F, 6, 2, 6, 0.0F);
        this.setRotateAngle(Head2, 0.04363323129985824F, 0.0F, 0.0F);
        this.LeftEyeHole = new ModelRenderer(this, 32, 62);
        this.LeftEyeHole.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftEyeHole.addBox(-4.0F, -4.0F, -4.0F, 3, 1, 1, 0.0F);
        this.Body2 = new ModelRenderer(this, 40, 53);
        this.Body2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.Body2.addBox(-4.0F, 0.0F, -2.0F, 8, 7, 4, 0.0F);
        this.Head1 = new ModelRenderer(this, 0, 50);
        this.Head1.setRotationPoint(0.0F, -4.0F, -1.0F);
        this.Head1.addBox(-4.0F, -8.0F, -4.0F, 8, 6, 8, 0.0F);
        this.setRotateAngle(Head1, 0.0F, 0.0F, -0.17453292519943295F);
        this.LeftArm2 = new ModelRenderer(this, 42, 28);
        this.LeftArm2.mirror = true;
        this.LeftArm2.setRotationPoint(0.5F, 7.0F, 0.0F);
        this.LeftArm2.addBox(-1.0F, 1.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(LeftArm2, -1.5707963267948966F, 0.0F, 0.0F);
        this.LeftLeg1 = new ModelRenderer(this, 51, 15);
        this.LeftLeg1.mirror = true;
        this.LeftLeg1.setRotationPoint(2.0F, 5.5F, 0.0F);
        this.LeftLeg1.addBox(-1.5F, 0.0F, -2.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(LeftLeg1, -0.7853981633974483F, -0.2617993877991494F, 0.0F);
        this.RightEyeHole = new ModelRenderer(this, 32, 62);
        this.RightEyeHole.mirror = true;
        this.RightEyeHole.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightEyeHole.addBox(1.0F, -4.0F, -4.0F, 3, 1, 1, 0.0F);
        this.Cloud1 = new ModelRenderer(this, 0, 26);
        this.Cloud1.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.Cloud1.addBox(-5.0F, -5.0F, -4.0F, 10, 4, 8, 0.0F);
        this.Cloud2 = new ModelRenderer(this, 0, 18);
        this.Cloud2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloud2.addBox(0.0F, -4.0F, 1.0F, 7, 4, 4, 0.0F);
        this.Head3 = new ModelRenderer(this, 0, 46);
        this.Head3.setRotationPoint(0.0F, -2.0F, 4.0F);
        this.Head3.addBox(-4.0F, 0.0F, -2.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Head3, 0.04363323129985824F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 44, 46);
        this.Body1.setRotationPoint(0.0F, -4.0F, -0.5F);
        this.Body1.addBox(-3.0F, 0.0F, -2.0F, 6, 3, 4, 0.0F);
        this.setRotateAngle(Body1, 0.17453292519943295F, 0.0F, 0.0F);
        this.RightLeg1 = new ModelRenderer(this, 51, 15);
        this.RightLeg1.setRotationPoint(-2.0F, 5.5F, 0.0F);
        this.RightLeg1.addBox(-1.5F, 0.0F, -2.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(RightLeg1, -0.7853981633974483F, 0.2617993877991494F, 0.0F);
        this.RightArm2 = new ModelRenderer(this, 42, 28);
        this.RightArm2.setRotationPoint(-0.5F, 7.0F, 0.0F);
        this.RightArm2.addBox(-1.0F, 1.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(RightArm2, -1.5707963267948966F, 0.0F, 0.0F);
        this.NoseHole = new ModelRenderer(this, 32, 60);
        this.NoseHole.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.NoseHole.addBox(-1.0F, -3.0F, -4.0F, 2, 1, 1, 0.0F);
        this.Cloud6 = new ModelRenderer(this, 22, 18);
        this.Cloud6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloud6.addBox(1.0F, -1.0F, 2.0F, 5, 3, 5, 0.0F);
        this.Cloud1.addChild(this.Cloud4);
        this.Cloud1.addChild(this.Cloud3);
        this.LeftLeg1.addChild(this.LeftLeg2);
        this.RightLeg1.addChild(this.RightLeg2);
        this.Cloud1.addChild(this.Cloud5);
        this.Head3.addChild(this.Head2);
        this.Head1.addChild(this.LeftEyeHole);
        this.Body1.addChild(this.Body2);
        this.LeftArm1.addChild(this.LeftArm2);
        this.Head1.addChild(this.RightEyeHole);
        this.Cloud1.addChild(this.Cloud2);
        this.Head1.addChild(this.Head3);
        this.RightArm1.addChild(this.RightArm2);
        this.Head1.addChild(this.NoseHole);
        this.Cloud1.addChild(this.Cloud6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.RightArm1.render(f5);
        this.LeftArm1.render(f5);
        this.Head1.render(f5);
        this.LeftLeg1.render(f5);
        this.Cloud1.render(f5);
        this.Body1.render(f5);
        this.RightLeg1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    float distanceFromCenterMultiplier=0.05f;
    FunctionHelper helper = KCore.functionHelper;
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, age, f3, f4, f5, entity);
        Cloud1.offsetX=MathHelper.sin(age*0.05f)*distanceFromCenterMultiplier;
        Cloud1.offsetY=-MathHelper.cos(age*0.05f)*distanceFromCenterMultiplier;
        Cloud2.offsetX=MathHelper.sin(age*0.005f)*distanceFromCenterMultiplier;
        Cloud2.offsetZ=-MathHelper.sin(age*0.005f)*distanceFromCenterMultiplier;
        Cloud3.offsetX=MathHelper.sin(age*0.018f)*distanceFromCenterMultiplier;
        Cloud3.offsetZ=-MathHelper.sin(age*0.012f)*distanceFromCenterMultiplier;
        Cloud4.offsetX=MathHelper.sin(age*0.016f)*distanceFromCenterMultiplier;
        Cloud4.offsetZ=-MathHelper.sin(age*0.013f)*distanceFromCenterMultiplier;
        Cloud5.offsetX=MathHelper.sin(age*0.015f)*distanceFromCenterMultiplier;
        Cloud5.offsetZ=-MathHelper.sin(age*0.015f)*distanceFromCenterMultiplier;
        Cloud6.offsetX=MathHelper.sin(age*0.012f)*distanceFromCenterMultiplier;
        Cloud6.offsetZ=-MathHelper.sin(age*0.08f)*distanceFromCenterMultiplier;

        RightArm1.rotateAngleY=helper.degToRad(5)+MathHelper.cos(age*0.05f)*0.05f;
        RightArm1.rotateAngleX=helper.degToRad(30)+MathHelper.sin(age*0.05f)*0.05f;
        LeftArm1.rotateAngleY=helper.degToRad(-5)+MathHelper.cos(age*0.05f)*0.05f;
        LeftArm1.rotateAngleX=helper.degToRad(30)-MathHelper.sin(age*0.05f)*0.05f;
    }
}
