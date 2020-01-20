package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.entity.EntityBison;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelBison<T extends LivingEntity> extends AgeableModel<T> {
    public ModelRenderer Body1;
    public ModelRenderer FrontRightUpperLeg;
    public ModelRenderer FrontLeftUpperLeg;
    public ModelRenderer Head1;
    public ModelRenderer HornLeft;
    public ModelRenderer Body2;
    public ModelRenderer Head2;
    public ModelRenderer Tail1;
    public ModelRenderer RightThigh;
    public ModelRenderer LeftThigh;
    public ModelRenderer HornRight;
    public ModelRenderer FrontRightDownLeg;
    public ModelRenderer FrontLeftDownLeg;
    public ModelRenderer Tail2;
    public ModelRenderer BackRightUpperLeg;
    public ModelRenderer BackRightDownLeg;
    public ModelRenderer BackLeftUpperLeg;
    public ModelRenderer BackLeftDownLeg;

    public ModelBison() {
        this.textureWidth = 120;
        this.textureHeight = 64;
        this.Tail1 = new ModelRenderer(this, 62, 0);
        this.Tail1.setRotationPoint(-0.5F, 4.8F, 10.6F);
        this.Tail1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(Tail1, 0.31869712141416456F, 0.0F, 0.0F);
        this.HornLeft = new ModelRenderer(this, 22, 0);
        this.HornLeft.setRotationPoint(-10.1F, -0.7F, -7.9F);
        this.HornLeft.func_228301_a_(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(HornLeft, 0.0F, 0.0F, 1.5707963267948966F);
        this.BackLeftDownLeg = new ModelRenderer(this, 62, 16);
        this.BackLeftDownLeg.setRotationPoint(-1.5F, 4.0F, -1.5F);
        this.BackLeftDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.Tail2 = new ModelRenderer(this, 66, 0);
        this.Tail2.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Tail2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.LeftThigh = new ModelRenderer(this, 42, 32);
        this.LeftThigh.setRotationPoint(-6.2F, 7.8F, 6.2F);
        this.LeftThigh.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F);
        this.Head2 = new ModelRenderer(this, 75, 0);
        this.Head2.setRotationPoint(-0.5F, 5.4F, -8.1F);
        this.Head2.func_228301_a_(-4.0F, -4.0F, -6.0F, 9, 5, 6, 0.0F);
        this.BackLeftUpperLeg = new ModelRenderer(this, 0, 16);
        this.BackLeftUpperLeg.setRotationPoint(2.0F, 2.5F, 2.0F);
        this.BackLeftUpperLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.HornRight = new ModelRenderer(this, 22, 0);
        this.HornRight.setRotationPoint(-1.3F, -0.7F, -7.0F);
        this.HornRight.func_228301_a_(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(HornRight, 0.0F, 0.0F, 1.5707963267948966F);
        this.FrontLeftUpperLeg = new ModelRenderer(this, 0, 16);
        this.FrontLeftUpperLeg.setRotationPoint(-4.0F, 12.8F, -4.7F);
        this.FrontLeftUpperLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.RightThigh = new ModelRenderer(this, 42, 32);
        this.RightThigh.setRotationPoint(1.9F, 7.8F, 6.2F);
        this.RightThigh.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F);
        this.Body1 = new ModelRenderer(this, 18, 4);
        this.Body1.setRotationPoint(0.6F, 6.7F, 4.8F);
        this.Body1.func_228301_a_(-6.0F, -10.0F, -7.0F, 11, 17, 11, 0.0F);
        this.setRotateAngle(Body1, 1.5707963267948966F, 0.0F, 0.0F);
        this.BackRightUpperLeg = new ModelRenderer(this, 0, 16);
        this.BackRightUpperLeg.setRotationPoint(2.0F, 2.5F, 2.0F);
        this.BackRightUpperLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Head1 = new ModelRenderer(this, 0, 0);
        this.Head1.setRotationPoint(0.0F, 7.6F, -8.0F);
        this.Head1.func_228301_a_(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.BackRightDownLeg = new ModelRenderer(this, 62, 16);
        this.BackRightDownLeg.setRotationPoint(-1.5F, 4.0F, -1.5F);
        this.BackRightDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.FrontRightDownLeg = new ModelRenderer(this, 62, 16);
        this.FrontRightDownLeg.setRotationPoint(0.5F, 4.0F, 0.5F);
        this.FrontRightDownLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 3, 7, 3, 0.0F);
        this.FrontRightUpperLeg = new ModelRenderer(this, 0, 16);
        this.FrontRightUpperLeg.setRotationPoint(4.0F, 12.9F, -4.7F);
        this.FrontRightUpperLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 32);
        this.Body2.setRotationPoint(-1.1F, 7.6F, 2.0F);
        this.Body2.func_228301_a_(-6.0F, -10.0F, -7.0F, 14, 10, 14, 0.0F);
        this.setRotateAngle(Body2, 1.5707963267948966F, 0.0F, 0.0F);
        this.FrontLeftDownLeg = new ModelRenderer(this, 62, 16);
        this.FrontLeftDownLeg.setRotationPoint(-1.5F, 4.0F, -1.5F);
        this.FrontLeftDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.BackLeftUpperLeg.addChild(this.BackLeftDownLeg);
        this.Tail1.addChild(this.Tail2);
        this.LeftThigh.addChild(this.BackLeftUpperLeg);
        this.RightThigh.addChild(this.BackRightUpperLeg);
        this.BackRightUpperLeg.addChild(this.BackRightDownLeg);
        this.FrontRightUpperLeg.addChild(this.FrontRightDownLeg);
        this.FrontLeftUpperLeg.addChild(this.FrontLeftDownLeg);
    }

    @Override
    public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float p_225597_4_, float p_225597_5_, float p_225597_6_) {


        this.RightThigh.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F) * 1F * limbSwingAmount;
        this.LeftThigh.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F + (float)Math.PI) * 1F * limbSwingAmount;
        this.FrontRightUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F + (float)Math.PI) * 1F * limbSwingAmount;
        this.FrontLeftUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.3662F) * 1F * limbSwingAmount;

        if(entity instanceof EntityBison) {
            EntityBison bison = (EntityBison) entity;
            if (bison.getShouldAnimTail()) {
                Tail1.rotateAngleZ = (MathHelper.sin((bison.getAnimTimer() / 25) * 0.6F));
            } else {
                Tail1.rotateAngleZ = 0;
            }
        }
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return ImmutableList.of(Head1);
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        /*RenderSystem.pushMatrix();
        RenderSystem.translatef(this.HornLeft.offsetX, this.HornLeft.offsetY, this.HornLeft.offsetZ);
        RenderSystem.translatef(this.HornLeft.rotationPointX * f5, this.HornLeft.rotationPointY * f5, this.HornLeft.rotationPointZ * f5);
        RenderSystem.scaled(1.6D, 1.2D, 1.0D);
        RenderSystem.translatef(-this.HornLeft.offsetX, -this.HornLeft.offsetY, -this.HornLeft.offsetZ);
        RenderSystem.translatef(-this.HornLeft.rotationPointX * f5, -this.HornLeft.rotationPointY * f5, -this.HornLeft.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.LeftThigh.offsetX, this.LeftThigh.offsetY, this.LeftThigh.offsetZ);
        RenderSystem.translatef(this.LeftThigh.rotationPointX * f5, this.LeftThigh.rotationPointY * f5, this.LeftThigh.rotationPointZ * f5);
        RenderSystem.scaled(1.1D, 1.2D, 1.2D);
        RenderSystem.translatef(-this.LeftThigh.offsetX, -this.LeftThigh.offsetY, -this.LeftThigh.offsetZ);
        RenderSystem.translatef(-this.LeftThigh.rotationPointX * f5, -this.LeftThigh.rotationPointY * f5, -this.LeftThigh.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.HornRight.offsetX, this.HornRight.offsetY, this.HornRight.offsetZ);
        RenderSystem.translatef(this.HornRight.rotationPointX * f5, this.HornRight.rotationPointY * f5, this.HornRight.rotationPointZ * f5);
        RenderSystem.scaled(1.6D, 1.2D, 1.0D);
        RenderSystem.translatef(-this.HornRight.offsetX, -this.HornRight.offsetY, -this.HornRight.offsetZ);
        RenderSystem.translatef(-this.HornRight.rotationPointX * f5, -this.HornRight.rotationPointY * f5, -this.HornRight.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.RightThigh.offsetX, this.RightThigh.offsetY, this.RightThigh.offsetZ);
        RenderSystem.translatef(this.RightThigh.rotationPointX * f5, this.RightThigh.rotationPointY * f5, this.RightThigh.rotationPointZ * f5);
        RenderSystem.scaled(1.1D, 1.2D, 1.2D);
        RenderSystem.translatef(-this.RightThigh.offsetX, -this.RightThigh.offsetY, -this.RightThigh.offsetZ);
        RenderSystem.translatef(-this.RightThigh.rotationPointX * f5, -this.RightThigh.rotationPointY * f5, -this.RightThigh.rotationPointZ * f5);
        RenderSystem.popMatrix();*/
        return ImmutableList.of(Body1,FrontRightUpperLeg,FrontLeftUpperLeg,Head1,HornLeft,Body2,Head2,Tail1,RightThigh,
                LeftThigh,HornRight,FrontRightDownLeg,FrontLeftDownLeg,Tail2,BackRightUpperLeg,BackRightDownLeg,
                BackLeftUpperLeg,BackLeftDownLeg);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

