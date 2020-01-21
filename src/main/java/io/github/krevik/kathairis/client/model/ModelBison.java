package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.entity.EntityBison;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
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
    public ModelRenderer RightThigh;
    public ModelRenderer Body2;
    public ModelRenderer HornLeft;
    public ModelRenderer HornRight;
    public ModelRenderer LeftThigh;
    public ModelRenderer Tail1;
    public ModelRenderer FrontLeftUpperLeg;
    public ModelRenderer Head1;
    public ModelRenderer Head2;
    public ModelRenderer Body1;
    public ModelRenderer FrontRightUpperLeg;
    public ModelRenderer RightThighChild;
    public ModelRenderer RightThighChildChild;
    public ModelRenderer LeftThighChild;
    public ModelRenderer LeftThighChildChild;
    public ModelRenderer Tail1Child;
    public ModelRenderer FrontLeftUpperLegChild;
    public ModelRenderer FrontRightUpperLegChild;

    public ModelBison() {
        this.textureWidth = 120;
        this.textureHeight = 64;
        this.FrontLeftUpperLeg = new ModelRenderer(this, 0, 16);
        this.FrontLeftUpperLeg.setRotationPoint(-4.0F, 12.800000190734863F, -4.699999809265137F);
        this.FrontLeftUpperLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Head1 = new ModelRenderer(this, 0, 0);
        this.Head1.setRotationPoint(0.0F, 7.599999904632568F, -8.0F);
        this.Head1.func_228301_a_(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.Tail1Child = new ModelRenderer(this, 66, 0);
        this.Tail1Child.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.Tail1Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.FrontRightUpperLeg = new ModelRenderer(this, 0, 16);
        this.FrontRightUpperLeg.setRotationPoint(4.0F, 12.899999618530273F, -4.699999809265137F);
        this.FrontRightUpperLeg.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Tail1 = new ModelRenderer(this, 62, 0);
        this.Tail1.setRotationPoint(-0.5F, 4.800000190734863F, 10.600000381469727F);
        this.Tail1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 10, 1, 0.0F);
        this.setRotateAngle(Tail1, 0.31869712471961975F, 0.0F, 0.0F);
        this.LeftThighChildChild = new ModelRenderer(this, 62, 16);
        this.LeftThighChildChild.setRotationPoint(-1.5F, 4.0F, -1.5F);
        this.LeftThighChildChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.HornLeft = new ModelRenderer(this, 22, 0);
        this.HornLeft.setRotationPoint(-9.5F, -0.7F, -7.9F);
        this.HornLeft.func_228301_a_(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(HornLeft, 0.0F, 0.0F, 1.5707963267948966F);
        this.LeftThighChild = new ModelRenderer(this, 0, 16);
        this.LeftThighChild.setRotationPoint(2.0F, 5.2F, 2.0F);
        this.LeftThighChild.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.RightThighChild = new ModelRenderer(this, 0, 16);
        this.RightThighChild.setRotationPoint(2.0F, 5.2F, 2.0F);
        this.RightThighChild.func_228301_a_(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
        this.FrontRightUpperLegChild = new ModelRenderer(this, 62, 16);
        this.FrontRightUpperLegChild.setRotationPoint(0.5F, 4.0F, 0.5F);
        this.FrontRightUpperLegChild.func_228301_a_(-2.0F, 0.0F, -2.0F, 3, 7, 3, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 32);
        this.Body2.setRotationPoint(-1.100000023841858F, 7.599999904632568F, 2.0F);
        this.Body2.func_228301_a_(-6.0F, -10.0F, -7.0F, 14, 10, 14, 0.0F);
        this.setRotateAngle(Body2, 1.5707963705062866F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 18, 4);
        this.Body1.setRotationPoint(0.6000000238418579F, 6.699999809265137F, 4.800000190734863F);
        this.Body1.func_228301_a_(-6.0F, -10.0F, -7.0F, 11, 17, 11, 0.0F);
        this.setRotateAngle(Body1, 1.5707963705062866F, 0.0F, 0.0F);
        this.LeftThigh = new ModelRenderer(this, 42, 32);
        this.LeftThigh.setRotationPoint(-6.199999809265137F, 7.800000190734863F, 6.199999809265137F);
        this.LeftThigh.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F);
        this.RightThighChildChild = new ModelRenderer(this, 62, 16);
        this.RightThighChildChild.setRotationPoint(-1.5F, 4.0F, -1.5F);
        this.RightThighChildChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.FrontLeftUpperLegChild = new ModelRenderer(this, 62, 16);
        this.FrontLeftUpperLegChild.setRotationPoint(-1.5F, 4.0F, -1.5F);
        this.FrontLeftUpperLegChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.HornRight = new ModelRenderer(this, 22, 0);
        this.HornRight.setRotationPoint(2.5F, -0.7F, -7.0F);
        this.HornRight.func_228301_a_(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(HornRight, 0.0F, 0.0F, 1.5707963267948966F);
        this.Head2 = new ModelRenderer(this, 75, 0);
        this.Head2.setRotationPoint(-0.5F, 5.400000095367432F, -8.100000381469727F);
        this.Head2.func_228301_a_(-4.0F, -4.0F, -6.0F, 9, 5, 6, 0.0F);
        this.RightThigh = new ModelRenderer(this, 42, 32);
        this.RightThigh.setRotationPoint(1.899999976158142F, 7.800000190734863F, 6.199999809265137F);
        this.RightThigh.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F);
        this.Tail1.addChild(this.Tail1Child);
        this.LeftThighChild.addChild(this.LeftThighChildChild);
        this.LeftThigh.addChild(this.LeftThighChild);
        this.RightThigh.addChild(this.RightThighChild);
        this.FrontRightUpperLeg.addChild(this.FrontRightUpperLegChild);
        this.RightThighChild.addChild(this.RightThighChildChild);
        this.FrontLeftUpperLeg.addChild(this.FrontLeftUpperLegChild);
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
        return ImmutableList.of(RightThigh,
                Body2,
                HornLeft,
                HornRight,
                LeftThigh,
                Tail1,
                FrontLeftUpperLeg,
                Head1,
                Head2,
                Body1,
                FrontRightUpperLeg,
                RightThighChild,
                RightThighChildChild,
                LeftThighChild,
                LeftThighChildChild,
                Tail1Child,
                FrontLeftUpperLegChild,
                FrontRightUpperLegChild);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

