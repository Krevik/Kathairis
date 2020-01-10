package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

/**
 * ModelHorse - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelCamel<T extends LivingEntity> extends AgeableModel<T> {
    public ModelRenderer Head1;
    public ModelRenderer Lejce1;
    public ModelRenderer Lejce2;
    public ModelRenderer SiodloGlowne;
    public ModelRenderer FrontRightUpperLeg;
    public ModelRenderer FrontLeftUpperLeg;
    public ModelRenderer Body1;
    public ModelRenderer BackRightUpperLeg;
    public ModelRenderer Tail1;
    public ModelRenderer BackLeftUpperLeg;
    public ModelRenderer Body2;
    public ModelRenderer Neck1;
    public ModelRenderer Neck2;
    public ModelRenderer Garb1;
    public ModelRenderer Garb2;
    public ModelRenderer NakladkaNaOgon;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer Head2;
    public ModelRenderer Nose;
    public ModelRenderer Siodlo2;
    public ModelRenderer Siodlo3;
    public ModelRenderer Siodlo7;
    public ModelRenderer Siodlo5;
    public ModelRenderer Siodlo6;
    public ModelRenderer Siodlo4;
    public ModelRenderer FrontRightDownLeg;
    public ModelRenderer FrontLeftDownLeg;
    public ModelRenderer BackRightDownLeg;
    public ModelRenderer BackLeftDownLeg;

    public ModelCamel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Neck1 = new ModelRenderer(this, 0, 68);
        this.Neck1.setRotationPoint(-2.4F, 1.8F, -13.5F);
        this.Neck1.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 5, 8, 0.0F);
        this.setRotateAngle(Neck1, -0.40980330836826856F, 0.0F, 0.0F);
        this.Siodlo7 = new ModelRenderer(this, 80, 0);
        this.Siodlo7.setRotationPoint(-5.5F, 0.0F, 0.5F);
        this.Siodlo7.func_228301_a_(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.Siodlo4 = new ModelRenderer(this, 80, 9);
        this.Siodlo4.setRotationPoint(-0.5F, 0.2F, 0.0F);
        this.Siodlo4.func_228301_a_(-4.0F, -1.0F, 3.0F, 8, 1, 2, 0.0F);
        this.LeftEar = new ModelRenderer(this, 0, 0);
        this.LeftEar.setRotationPoint(-0.6F, -1.0F, 7.5F);
        this.LeftEar.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(LeftEar, 0.0F, 0.9105382707654417F, 0.0F);
        this.FrontRightDownLeg = new ModelRenderer(this, 44, 41);
        this.FrontRightDownLeg.setRotationPoint(-1.9F, 7.0F, -1.6F);
        this.FrontRightDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.Garb2 = new ModelRenderer(this, 34, 68);
        this.Garb2.setRotationPoint(-4.0F, 0.6F, 1.9F);
        this.Garb2.func_228301_a_(0.0F, 0.0F, 0.0F, 8, 3, 8, 0.0F);
        this.BackLeftDownLeg = new ModelRenderer(this, 96, 43);
        this.BackLeftDownLeg.setRotationPoint(-1.0F, 7.0F, -1.5F);
        this.BackLeftDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.Head2 = new ModelRenderer(this, 24, 25);
        this.Head2.setRotationPoint(0.5F, 1.6F, -5.0F);
        this.Head2.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 3, 5, 0.0F);
        this.Neck2 = new ModelRenderer(this, 0, 68);
        this.Neck2.setRotationPoint(-2.0F, -4.5F, -13.7F);
        this.Neck2.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 10, 0.0F);
        this.setRotateAngle(Neck2, -1.3203415791337103F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 3, 36);
        this.Body1.setRotationPoint(0.0F, 11.3F, 9.0F);
        this.Body1.func_228301_a_(-5.0F, -8.0F, -19.0F, 10, 10, 21, 0.0F);
        this.FrontLeftDownLeg = new ModelRenderer(this, 60, 41);
        this.FrontLeftDownLeg.setRotationPoint(-1.1F, 7.0F, -1.6F);
        this.FrontLeftDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.BackRightDownLeg = new ModelRenderer(this, 78, 43);
        this.BackRightDownLeg.setRotationPoint(-2.0F, 7.0F, -1.5F);
        this.BackRightDownLeg.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 68);
        this.Body2.setRotationPoint(-4.0F, 3.6F, -10.3F);
        this.Body2.func_228301_a_(0.0F, 0.0F, 0.0F, 8, 8, 5, 0.0F);
        this.RightEar = new ModelRenderer(this, 0, 0);
        this.RightEar.setRotationPoint(4.5F, -1.0F, 5.6F);
        this.RightEar.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(RightEar, 0.0F, -0.9105382707654417F, 0.0F);
        this.FrontLeftUpperLeg = new ModelRenderer(this, 60, 29);
        this.FrontLeftUpperLeg.setRotationPoint(-4.1F, 9.0F, -6.3F);
        this.FrontLeftUpperLeg.func_228301_a_(-1.1F, -1.0F, -2.1F, 3, 8, 4, 0.0F);
        this.Nose = new ModelRenderer(this, 25, 17);
        this.Nose.setRotationPoint(1.0F, 0.7F, -4.7F);
        this.Nose.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 5, 0.0F);
        this.BackRightUpperLeg = new ModelRenderer(this, 78, 29);
        this.BackRightUpperLeg.setRotationPoint(4.0F, 9.0F, 8.0F);
        this.BackRightUpperLeg.func_228301_a_(-2.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
        this.Tail1 = new ModelRenderer(this, 44, 0);
        this.Tail1.setRotationPoint(0.5F, 4.4F, 9.9F);
        this.Tail1.func_228301_a_(-1.0F, -1.0F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Tail1, -0.8651597102135892F, 0.0F, 0.0F);
        this.Siodlo6 = new ModelRenderer(this, 106, 9);
        this.Siodlo6.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.Siodlo6.func_228301_a_(-1.5F, -1.0F, -3.0F, 3, 1, 2, 0.0F);
        this.Lejce2 = new ModelRenderer(this, 44, 5);
        this.Lejce2.setRotationPoint(0.2F, 2.0F, -16.3F);
        this.Lejce2.func_228301_a_(-2.6F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
        this.setRotateAngle(Lejce2, -0.31869712141416456F, 0.0F, 0.0F);
        this.BackLeftUpperLeg = new ModelRenderer(this, 96, 29);
        this.BackLeftUpperLeg.setRotationPoint(-4.0F, 9.0F, 8.0F);
        this.BackLeftUpperLeg.func_228301_a_(-1.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
        this.Head1 = new ModelRenderer(this, 0, 0);
        this.Head1.setRotationPoint(-2.3F, -7.9F, -20.3F);
        this.Head1.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 5, 7, 0.0F);
        this.Garb1 = new ModelRenderer(this, 34, 68);
        this.Garb1.setRotationPoint(-4.0F, 0.6F, -7.1F);
        this.Garb1.func_228301_a_(0.0F, 0.0F, -0.2F, 8, 3, 8, 0.0F);
        this.Siodlo2 = new ModelRenderer(this, 74, 0);
        this.Siodlo2.setRotationPoint(4.5F, 0.0F, 0.5F);
        this.Siodlo2.func_228301_a_(-0.5F, 6.0F, -1.0F, 1, 2, 2, 0.0F);
        this.FrontRightUpperLeg = new ModelRenderer(this, 44, 29);
        this.FrontRightUpperLeg.setRotationPoint(4.0F, 9.0F, -6.3F);
        this.FrontRightUpperLeg.func_228301_a_(-1.9F, -1.0F, -2.1F, 3, 8, 4, 0.0F);
        this.Siodlo3 = new ModelRenderer(this, 70, 0);
        this.Siodlo3.setRotationPoint(4.5F, 0.1F, 0.5F);
        this.Siodlo3.func_228301_a_(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.NakladkaNaOgon = new ModelRenderer(this, 37, 7);
        this.NakladkaNaOgon.setRotationPoint(-1.0F, 5.3F, 13.0F);
        this.NakladkaNaOgon.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(NakladkaNaOgon, -1.3962634015954636F, 0.0F, 0.0F);
        this.Siodlo5 = new ModelRenderer(this, 74, 4);
        this.Siodlo5.setRotationPoint(-5.5F, 0.0F, 0.5F);
        this.Siodlo5.func_228301_a_(-0.5F, 6.0F, -1.0F, 1, 2, 2, 0.0F);
        this.Lejce1 = new ModelRenderer(this, 44, 10);
        this.Lejce1.setRotationPoint(0.3F, 2.6F, -16.5F);
        this.Lejce1.func_228301_a_(2.6F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
        this.setRotateAngle(Lejce1, -0.31869712141416456F, 0.0F, 0.0F);
        this.SiodloGlowne = new ModelRenderer(this, 80, 0);
        this.SiodloGlowne.setRotationPoint(0.7F, 0.0F, -4.5F);
        this.SiodloGlowne.func_228301_a_(-5.0F, 0.0F, -3.0F, 9, 1, 8, 0.0F);
        this.SiodloGlowne.addChild(this.Siodlo7);
        this.SiodloGlowne.addChild(this.Siodlo4);
        this.Head1.addChild(this.LeftEar);
        this.FrontRightUpperLeg.addChild(this.FrontRightDownLeg);
        this.BackLeftUpperLeg.addChild(this.BackLeftDownLeg);
        this.Head1.addChild(this.Head2);
        this.FrontLeftUpperLeg.addChild(this.FrontLeftDownLeg);
        this.BackRightUpperLeg.addChild(this.BackRightDownLeg);
        this.Head1.addChild(this.RightEar);
        this.Head1.addChild(this.Nose);
        this.SiodloGlowne.addChild(this.Siodlo6);
        this.SiodloGlowne.addChild(this.Siodlo2);
        this.SiodloGlowne.addChild(this.Siodlo3);
        this.SiodloGlowne.addChild(this.Siodlo5);
    }

    @Override
    public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float p_225597_4_, float p_225597_5_, float p_225597_6_) {


        this.BackRightUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.BackLeftUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.FrontRightUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.FrontLeftUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        if(!shouldAnimateTail) {
            if(random.nextInt(750)==0) {
                shouldAnimateTail=true;
            }
        }
        if(shouldAnimateTail) {
            timer++;
            Tail1.rotateAngleZ=(MathHelper.sin((timer/25)*0.6F));
            if(timer>400) {
                timer=0;
                shouldAnimateTail=false;
                Tail1.rotateAngleZ=0;
            }
        }
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return null;
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        /*RenderSystem.pushMatrix();
        RenderSystem.translatef(this.Garb2.offsetX, this.Garb2.offsetY, this.Garb2.offsetZ);
        RenderSystem.translatef(this.Garb2.rotationPointX * f5, this.Garb2.rotationPointY * f5, this.Garb2.rotationPointZ * f5);
        RenderSystem.scaled(1.0D, 1.0D, 0.9D);
        RenderSystem.translatef(-this.Garb2.offsetX, -this.Garb2.offsetY, -this.Garb2.offsetZ);
        RenderSystem.translatef(-this.Garb2.rotationPointX * f5, -this.Garb2.rotationPointY * f5, -this.Garb2.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.Body1.offsetX, this.Body1.offsetY, this.Body1.offsetZ);
        RenderSystem.translatef(this.Body1.rotationPointX * f5, this.Body1.rotationPointY * f5, this.Body1.rotationPointZ * f5);
        RenderSystem.scaled(1.0D, 1.0D, 0.9D);
        RenderSystem.translatef(-this.Body1.offsetX, -this.Body1.offsetY, -this.Body1.offsetZ);
        RenderSystem.translatef(-this.Body1.rotationPointX * f5, -this.Body1.rotationPointY * f5, -this.Body1.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.Garb1.offsetX, this.Garb1.offsetY, this.Garb1.offsetZ);
        RenderSystem.translatef(this.Garb1.rotationPointX * f5, this.Garb1.rotationPointY * f5, this.Garb1.rotationPointZ * f5);
        RenderSystem.scaled(1.0D, 1.0D, 0.9D);
        RenderSystem.translatef(-this.Garb1.offsetX, -this.Garb1.offsetY, -this.Garb1.offsetZ);
        RenderSystem.translatef(-this.Garb1.rotationPointX * f5, -this.Garb1.rotationPointY * f5, -this.Garb1.rotationPointZ * f5);
        RenderSystem.popMatrix();*/

        return ImmutableList.of(Neck1,Garb2,Neck2,Body1,Body2,FrontLeftUpperLeg,BackRightUpperLeg,Tail1,BackLeftUpperLeg,
                Head1,Garb1,FrontRightUpperLeg,SiodloGlowne,Lejce1,NakladkaNaOgon,Lejce2);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    protected boolean shouldAnimateTail=false;
    protected float timer=0;
    protected Random random = new Random();
}
