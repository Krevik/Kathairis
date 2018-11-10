package com.Krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;


/**
 * ModelDeath - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelDeath extends ModelBase {
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Body1;
    public ModelRenderer RightArm1;
    public ModelRenderer LeftArm1;
    public ModelRenderer Neck;
    public ModelRenderer RightLegLayer;
    public ModelRenderer LeftLegLayer;
    public ModelRenderer RightArmLayer;
    public ModelRenderer LeftArmLayer;
    public ModelRenderer BodyLayer;
    public ModelRenderer CapeMiddle;
    public ModelRenderer RightFoot;
    public ModelRenderer LeftFoot;
    public ModelRenderer Bobs;
    public ModelRenderer Body2;
    public ModelRenderer Hips;
    public ModelRenderer RightArm2;
    public ModelRenderer LeftArm2;
    public ModelRenderer Head;
    public ModelRenderer Hoodie;
    public ModelRenderer RightCape;
    public ModelRenderer LeftCape;

    public ModelDeath() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Hips = new ModelRenderer(this, 60, 31);
        this.Hips.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.Hips.addBox(-6.5F, 0.0F, -3.0F, 13, 3, 6, 0.0F);
        this.setRotateAngle(Hips, -0.03490658503988659F, 0.0F, 0.0F);
        this.RightCape = new ModelRenderer(this, 30, 44);
        this.RightCape.setRotationPoint(-7.0F, 0.0F, 1.0F);
        this.RightCape.addBox(-6.0F, 0.0F, -1.0F, 6, 34, 1, 0.0F);
        this.setRotateAngle(RightCape, 0.0F, -0.4363323129985824F, 0.0F);
        this.Body2 = new ModelRenderer(this, 62, 18);
        this.Body2.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Body2.addBox(-4.5F, 0.0F, -3.0F, 9, 7, 6, 0.0F);
        this.setRotateAngle(Body2, 0.03490658503988659F, 0.0F, 0.0F);
        this.Hoodie = new ModelRenderer(this, 46, 100);
        this.Hoodie.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hoodie.addBox(-7.0F, -13.0F, -6.2F, 14, 14, 14, 0.0F);
        this.RightLeg = new ModelRenderer(this, 110, 0);
        this.RightLeg.setRotationPoint(-3.4F, 7.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.5F, 4, 15, 5, 0.0F);
        this.setRotateAngle(RightLeg, 0.0F, 0.17453292519943295F, 0.0F);
        this.LeftFoot = new ModelRenderer(this, 96, 21);
        this.LeftFoot.mirror = true;
        this.LeftFoot.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.LeftFoot.addBox(-3.5F, 0.0F, -5.5F, 7, 3, 9, 0.0F);
        this.RightFoot = new ModelRenderer(this, 96, 21);
        this.RightFoot.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.RightFoot.addBox(-3.5F, 0.0F, -5.5F, 7, 3, 9, 0.0F);
        this.RightArm2 = new ModelRenderer(this, 102, 50);
        this.RightArm2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.RightArm2.addBox(-1.5F, -2.0F, -12.0F, 3, 4, 10, 0.0F);
        this.setRotateAngle(RightArm2, 1.5707963267948966F, 0.0F, 0.0F);
        this.RightArmLayer = new ModelRenderer(this, 0, 6);
        this.RightArmLayer.setRotationPoint(-8.5F, -12.5F, 0.0F);
        this.RightArmLayer.addBox(-3.0F, -2.0F, -2.5F, 5, 12, 5, 0.25F);
        this.RightLegLayer = new ModelRenderer(this, 0, 23);
        this.RightLegLayer.setRotationPoint(-3.4F, 7.0F, 0.0F);
        this.RightLegLayer.addBox(-3.0F, 0.0F, -3.0F, 6, 15, 6, 0.25F);
        this.setRotateAngle(RightLegLayer, 0.0F, 0.17453292519943295F, 0.0F);
        this.LeftArmLayer = new ModelRenderer(this, 0, 6);
        this.LeftArmLayer.mirror = true;
        this.LeftArmLayer.setRotationPoint(8.5F, -12.5F, 0.0F);
        this.LeftArmLayer.addBox(-2.0F, -2.0F, -2.5F, 5, 12, 5, 0.25F);
        this.CapeMiddle = new ModelRenderer(this, 0, 44);
        this.CapeMiddle.setRotationPoint(0.0F, -15.0F, 4.0F);
        this.CapeMiddle.addBox(-7.0F, 0.0F, 0.0F, 14, 34, 1, 0.0F);
        this.Body1 = new ModelRenderer(this, 66, 0);
        this.Body1.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.Body1.addBox(-6.5F, 0.0F, -3.0F, 13, 12, 6, 0.0F);
        this.setRotateAngle(Body1, -0.017453292519943295F, 0.0F, 0.0F);
        this.Neck = new ModelRenderer(this, 102, 120);
        this.Neck.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.Neck.addBox(-3.5F, -3.0F, -2.0F, 7, 4, 4, 0.0F);
        this.LeftCape = new ModelRenderer(this, 30, 44);
        this.LeftCape.mirror = true;
        this.LeftCape.setRotationPoint(7.0F, 0.0F, 1.0F);
        this.LeftCape.addBox(0.0F, 0.0F, -1.0F, 6, 34, 1, 0.0F);
        this.setRotateAngle(LeftCape, 0.0F, 0.4363323129985824F, 0.0F);
        this.RightArm1 = new ModelRenderer(this, 114, 33);
        this.RightArm1.setRotationPoint(-8.5F, -12.5F, 0.0F);
        this.RightArm1.addBox(-1.5F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.Bobs = new ModelRenderer(this, 44, 0);
        this.Bobs.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bobs.addBox(-6.5F, 3.0F, -4.0F, 13, 5, 1, 0.0F);
        this.LeftArm1 = new ModelRenderer(this, 114, 33);
        this.LeftArm1.mirror = true;
        this.LeftArm1.setRotationPoint(8.5F, -12.5F, 0.0F);
        this.LeftArm1.addBox(-1.5F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 110, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(3.4F, 7.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.5F, 4, 15, 5, 0.0F);
        this.setRotateAngle(LeftLeg, 0.0F, -0.17453292519943295F, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 68, 40);
        this.BodyLayer.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.BodyLayer.addBox(-6.5F, 0.0F, -3.5F, 13, 12, 7, 0.25F);
        this.setRotateAngle(BodyLayer, -0.017453292519943295F, 0.0F, 0.0F);
        this.LeftLegLayer = new ModelRenderer(this, 0, 23);
        this.LeftLegLayer.mirror = true;
        this.LeftLegLayer.setRotationPoint(3.4F, 7.0F, 0.0F);
        this.LeftLegLayer.addBox(-3.0F, 0.0F, -3.0F, 6, 15, 6, 0.25F);
        this.setRotateAngle(LeftLegLayer, 0.0F, -0.17453292519943295F, 0.0F);
        this.LeftArm2 = new ModelRenderer(this, 102, 50);
        this.LeftArm2.mirror = true;
        this.LeftArm2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.LeftArm2.addBox(-1.5F, -2.0F, -12.0F, 3, 4, 10, 0.0F);
        this.setRotateAngle(LeftArm2, 1.5707963267948966F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 106);
        this.Head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Head.addBox(-6.0F, -11.0F, -5.5F, 12, 11, 11, 0.0F);
        this.Body2.addChild(this.Hips);
        this.CapeMiddle.addChild(this.RightCape);
        this.Body1.addChild(this.Body2);
        this.Head.addChild(this.Hoodie);
        this.LeftLeg.addChild(this.LeftFoot);
        this.RightLeg.addChild(this.RightFoot);
        this.RightArm1.addChild(this.RightArm2);
        this.CapeMiddle.addChild(this.LeftCape);
        this.Body1.addChild(this.Bobs);
        this.LeftArm1.addChild(this.LeftArm2);
        this.Neck.addChild(this.Head);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.RightLeg.render(f5);
        this.RightArmLayer.render(f5);
        this.RightLegLayer.render(f5);
        this.LeftArmLayer.render(f5);
        this.CapeMiddle.render(f5);
        this.Body1.render(f5);
        this.Neck.render(f5);
        this.RightArm1.render(f5);
        this.LeftArm1.render(f5);
        this.LeftLeg.render(f5);
        this.BodyLayer.render(f5);
        this.LeftLegLayer.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, age, f3, f4, f5, entity);
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / limbSwing;
        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / limbSwing;
    }

    public void postRenderArm(float scale, EnumHandSide side)
    {
        this.getArmForSide(side).postRender(scale);
    }
    protected ModelRenderer getArmForSide(EnumHandSide side)
    {
        return side == EnumHandSide.LEFT ? this.LeftArm2 : this.RightArm2;
    }
}

