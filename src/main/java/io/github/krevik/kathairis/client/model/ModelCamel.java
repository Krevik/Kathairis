package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.entity.EntityCamel;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

/**
 * ModelHorse - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelCamel<T extends LivingEntity> extends AgeableModel<T> {
    public ModelRenderer Tail1;
    public ModelRenderer Lejce2;
    public ModelRenderer Lejce1;
    public ModelRenderer FrontLeftUpperLeg;
    public ModelRenderer Garb1;
    public ModelRenderer Head1;
    public ModelRenderer Garb2;
    public ModelRenderer FrontRightUpperLeg;
    public ModelRenderer NakladkaNaOgon;
    public ModelRenderer Body2;
    public ModelRenderer SiodloGlowne;
    public ModelRenderer Neck1;
    public ModelRenderer Neck2;
    public ModelRenderer BackRightUpperLeg;
    public ModelRenderer Body1;
    public ModelRenderer BackLeftUpperLeg;
    public ModelRenderer FrontLeftUpperLegChild;
    public ModelRenderer Head1Child;
    public ModelRenderer Head1Child_1;
    public ModelRenderer Head1Child_2;
    public ModelRenderer Head1Child_3;
    public ModelRenderer FrontRightUpperLegChild;
    public ModelRenderer SiodloGlowneChild;
    public ModelRenderer SiodloGlowneChild_1;
    public ModelRenderer SiodloGlowneChild_2;
    public ModelRenderer SiodloGlowneChild_3;
    public ModelRenderer SiodloGlowneChild_4;
    public ModelRenderer SiodloGlowneChild_5;
    public ModelRenderer BackRightUpperLegChild;
    public ModelRenderer BackLeftUpperLegChild;

    public ModelCamel() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Body1 = new ModelRenderer(this, 3, 36);
        this.Body1.setRotationPoint(0.0F, 11.300000190734863F, 9.0F);
        this.Body1.func_228301_a_(-5.0F, -8.0F, -19.0F, 10, 10, 21, 0.0F);
        this.Tail1 = new ModelRenderer(this, 44, 0);
        this.Tail1.setRotationPoint(0.5F, 4.400000095367432F, 9.899999618530273F);
        this.Tail1.func_228301_a_(-1.0F, -1.0F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(Tail1, -0.8651596903800965F, 0.0F, 0.0F);
        this.SiodloGlowneChild_1 = new ModelRenderer(this, 80, 9);
        this.SiodloGlowneChild_1.setRotationPoint(-0.5F, 0.20000000298023224F, 0.0F);
        this.SiodloGlowneChild_1.func_228301_a_(-4.0F, -1.0F, 3.0F, 8, 1, 2, 0.0F);
        this.BackLeftUpperLegChild = new ModelRenderer(this, 96, 43);
        this.BackLeftUpperLegChild.setRotationPoint(-1.0F, 7.0F, -1.5F);
        this.BackLeftUpperLegChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.NakladkaNaOgon = new ModelRenderer(this, 37, 7);
        this.NakladkaNaOgon.setRotationPoint(-1.0F, 5.300000190734863F, 13.0F);
        this.NakladkaNaOgon.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 9, 0.0F);
        this.setRotateAngle(NakladkaNaOgon, -1.3962633609771726F, 0.0F, 0.0F);
        this.Neck1 = new ModelRenderer(this, 0, 68);
        this.Neck1.setRotationPoint(-2.4000000953674316F, 1.7999999523162842F, -13.5F);
        this.Neck1.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 5, 8, 0.0F);
        this.setRotateAngle(Neck1, -0.4098033010959626F, 0.0F, 0.0F);
        this.Head1 = new ModelRenderer(this, 0, 0);
        this.Head1.setRotationPoint(-2.299999952316284F, -7.900000095367432F, -20.299999237060547F);
        this.Head1.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 5, 7, 0.0F);
        this.SiodloGlowne = new ModelRenderer(this, 80, 0);
        this.SiodloGlowne.setRotationPoint(0.699999988079071F, 0.0F, -4.5F);
        this.SiodloGlowne.func_228301_a_(-5.0F, 0.0F, -3.0F, 9, 1, 8, 0.0F);
        this.BackRightUpperLegChild = new ModelRenderer(this, 78, 43);
        this.BackRightUpperLegChild.setRotationPoint(-2.0F, 7.0F, -1.5F);
        this.BackRightUpperLegChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.BackLeftUpperLeg = new ModelRenderer(this, 96, 29);
        this.BackLeftUpperLeg.setRotationPoint(-4.0F, 9.0F, 8.0F);
        this.BackLeftUpperLeg.func_228301_a_(-1.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
        this.FrontLeftUpperLeg = new ModelRenderer(this, 60, 29);
        this.FrontLeftUpperLeg.setRotationPoint(-4.099999904632568F, 9.0F, -6.300000190734863F);
        this.FrontLeftUpperLeg.func_228301_a_(-1.100000023841858F, -1.0F, -2.0999999046325684F, 3, 8, 4, 0.0F);
        this.SiodloGlowneChild_4 = new ModelRenderer(this, 70, 0);
        this.SiodloGlowneChild_4.setRotationPoint(4.5F, 0.10000000149011612F, 0.5F);
        this.SiodloGlowneChild_4.func_228301_a_(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.SiodloGlowneChild_5 = new ModelRenderer(this, 74, 4);
        this.SiodloGlowneChild_5.setRotationPoint(-5.5F, 0.0F, 0.5F);
        this.SiodloGlowneChild_5.func_228301_a_(-0.5F, 6.0F, -1.0F, 1, 2, 2, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 68);
        this.Body2.setRotationPoint(-4.0F, 3.5999999046325684F, -10.300000190734863F);
        this.Body2.func_228301_a_(0.0F, 0.0F, 0.0F, 8, 8, 5, 0.0F);
        this.BackRightUpperLeg = new ModelRenderer(this, 78, 29);
        this.BackRightUpperLeg.setRotationPoint(4.0F, 9.0F, 8.0F);
        this.BackRightUpperLeg.func_228301_a_(-2.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
        this.Lejce2 = new ModelRenderer(this, 44, 5);
        this.Lejce2.setRotationPoint(0.20000000298023224F, 2.0F, -16.299999237060547F);
        this.Lejce2.func_228301_a_(-2.5999999046325684F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
        this.setRotateAngle(Lejce2, -0.31869712471961975F, 0.0F, 0.0F);
        this.Head1Child = new ModelRenderer(this, 0, 0);
        this.Head1Child.setRotationPoint(-0.6000000238418579F, -1.0F, 7.5F);
        this.Head1Child.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(Head1Child, 0.0F, 0.9105382561683655F, 0.0F);
        this.Head1Child_1 = new ModelRenderer(this, 24, 25);
        this.Head1Child_1.setRotationPoint(0.5F, 1.600000023841858F, -5.0F);
        this.Head1Child_1.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 3, 5, 0.0F);
        this.FrontLeftUpperLegChild = new ModelRenderer(this, 60, 41);
        this.FrontLeftUpperLegChild.setRotationPoint(-1.100000023841858F, 7.0F, -1.600000023841858F);
        this.FrontLeftUpperLegChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.FrontRightUpperLegChild = new ModelRenderer(this, 44, 41);
        this.FrontRightUpperLegChild.setRotationPoint(-1.899999976158142F, 7.0F, -1.600000023841858F);
        this.FrontRightUpperLegChild.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.SiodloGlowneChild_2 = new ModelRenderer(this, 106, 9);
        this.SiodloGlowneChild_2.setRotationPoint(-0.5F, 0.0F, 0.0F);
        this.SiodloGlowneChild_2.func_228301_a_(-1.5F, -1.0F, -3.0F, 3, 1, 2, 0.0F);
        this.SiodloGlowneChild_3 = new ModelRenderer(this, 74, 0);
        this.SiodloGlowneChild_3.setRotationPoint(4.5F, 0.0F, 0.5F);
        this.SiodloGlowneChild_3.func_228301_a_(-0.5F, 6.0F, -1.0F, 1, 2, 2, 0.0F);
        this.Garb2 = new ModelRenderer(this, 34, 68);
        this.Garb2.setRotationPoint(-4.0F, 0.6000000238418579F, 1.899999976158142F);
        this.Garb2.func_228301_a_(0.0F, 0.0F, 0.0F, 8, 3, 8, 0.0F);
        this.Lejce1 = new ModelRenderer(this, 44, 10);
        this.Lejce1.setRotationPoint(0.30000001192092896F, 2.5999999046325684F, -16.5F);
        this.Lejce1.func_228301_a_(2.5999999046325684F, -6.0F, -6.0F, 0, 3, 16, 0.0F);
        this.setRotateAngle(Lejce1, -0.31869712471961975F, 0.0F, 0.0F);
        this.Garb1 = new ModelRenderer(this, 34, 68);
        this.Garb1.setRotationPoint(-4.0F, 0.6000000238418579F, -7.099999904632568F);
        this.Garb1.func_228301_a_(0.0F, 0.0F, -0.20000000298023224F, 8, 3, 8, 0.0F);
        this.Head1Child_3 = new ModelRenderer(this, 25, 17);
        this.Head1Child_3.setRotationPoint(1.0F, 0.699999988079071F, -4.699999809265137F);
        this.Head1Child_3.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 5, 0.0F);
        this.FrontRightUpperLeg = new ModelRenderer(this, 44, 29);
        this.FrontRightUpperLeg.setRotationPoint(4.0F, 9.0F, -6.300000190734863F);
        this.FrontRightUpperLeg.func_228301_a_(-1.899999976158142F, -1.0F, -2.0999999046325684F, 3, 8, 4, 0.0F);
        this.Neck2 = new ModelRenderer(this, 0, 68);
        this.Neck2.setRotationPoint(-2.0F, -4.5F, -13.699999809265137F);
        this.Neck2.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 10, 0.0F);
        this.setRotateAngle(Neck2, -1.3203415870666504F, 0.0F, 0.0F);
        this.SiodloGlowneChild = new ModelRenderer(this, 80, 0);
        this.SiodloGlowneChild.setRotationPoint(-5.5F, 0.0F, 0.5F);
        this.SiodloGlowneChild.func_228301_a_(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.Head1Child_2 = new ModelRenderer(this, 0, 0);
        this.Head1Child_2.setRotationPoint(4.5F, -1.0F, 5.599999904632568F);
        this.Head1Child_2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(Head1Child_2, 0.0F, -0.9105382561683655F, 0.0F);
        this.SiodloGlowne.addChild(this.SiodloGlowneChild_1);
        this.BackLeftUpperLeg.addChild(this.BackLeftUpperLegChild);
        this.BackRightUpperLeg.addChild(this.BackRightUpperLegChild);
        this.SiodloGlowne.addChild(this.SiodloGlowneChild_4);
        this.SiodloGlowne.addChild(this.SiodloGlowneChild_5);
        this.Head1.addChild(this.Head1Child);
        this.Head1.addChild(this.Head1Child_1);
        this.FrontLeftUpperLeg.addChild(this.FrontLeftUpperLegChild);
        this.FrontRightUpperLeg.addChild(this.FrontRightUpperLegChild);
        this.SiodloGlowne.addChild(this.SiodloGlowneChild_2);
        this.SiodloGlowne.addChild(this.SiodloGlowneChild_3);
        this.Head1.addChild(this.Head1Child_3);
        this.SiodloGlowne.addChild(this.SiodloGlowneChild);
        this.Head1.addChild(this.Head1Child_2);
    }

    @Override
    public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.BackRightUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.BackLeftUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.FrontRightUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.FrontLeftUpperLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        if(entity instanceof EntityCamel){
            EntityCamel camel = (EntityCamel)entity;
            SiodloGlowne.showModel=camel.isHorseSaddled();
            SiodloGlowneChild.showModel=camel.isHorseSaddled();
            SiodloGlowneChild_1.showModel=camel.isHorseSaddled();
            SiodloGlowneChild_2.showModel=camel.isHorseSaddled();
            SiodloGlowneChild_3.showModel=camel.isHorseSaddled();
            SiodloGlowneChild_4.showModel=camel.isHorseSaddled();
            SiodloGlowneChild_5.showModel=camel.isHorseSaddled();
            NakladkaNaOgon.showModel=camel.isHorseSaddled();
            Lejce1.showModel=camel.isBeingRidden();
            Lejce2.showModel=camel.isBeingRidden();
        }
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
        return ImmutableList.of(Head1);
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(Tail1, Lejce2, Lejce1, FrontLeftUpperLeg, Garb1, Garb2, FrontRightUpperLeg,
                NakladkaNaOgon, Body2, SiodloGlowne, Neck1, Neck2, BackRightUpperLeg, Body1, BackLeftUpperLeg,
                FrontLeftUpperLegChild, FrontRightUpperLegChild,
                SiodloGlowneChild, SiodloGlowneChild_1, SiodloGlowneChild_2, SiodloGlowneChild_3, SiodloGlowneChild_4,
                SiodloGlowneChild_5, BackRightUpperLegChild,
                BackLeftUpperLegChild);
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
