package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelMysticBird - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelMysticBird<T extends LivingEntity> extends AgeableModel<T> {

    public ModelRenderer Leg2;
    public ModelRenderer Head;
    public ModelRenderer Leg1;
    public ModelRenderer Wing6;
    public ModelRenderer Wing4;
    public ModelRenderer Wing3;
    public ModelRenderer Wing9;
    public ModelRenderer Nose;
    public ModelRenderer Wing8;
    public ModelRenderer Wing7;
    public ModelRenderer Wing10;
    public ModelRenderer Body;
    public ModelRenderer Wing1;
    public ModelRenderer Wing4Child;
    public ModelRenderer Wing3Child;

    public ModelMysticBird() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Wing7 = new ModelRenderer(this, -1, 24);
        this.Wing7.setRotationPoint(-0.8999999761581421F, 17.899999618530273F, 5.199999809265137F);
        this.Wing7.func_228301_a_(0.0F, 0.0F, 0.0F, 0, 5, 12, 0.0F);
        this.setRotateAngle(Wing7, -0.5009095072746276F, 0.22759093344211578F, -1.001644492149353F);
        this.Body = new ModelRenderer(this, 0, 80);
        this.Body.setRotationPoint(-2.4000000953674316F, 16.700000762939453F, -3.0F);
        this.Body.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 4, 8, 0.0F);
        this.setRotateAngle(Body, -0.09110618382692338F, 0.0F, 0.0F);
        this.Wing10 = new ModelRenderer(this, 3, 25);
        this.Wing10.setRotationPoint(-1.350000023841858F, 17.809999465942383F, -3.799999952316284F);
        this.Wing10.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 11, 0.0F);
        this.setRotateAngle(Wing10, 0.7285004258155823F, 0.27314403653144836F, -0.9560913443565369F);
        this.Leg2 = new ModelRenderer(this, 7, 99);
        this.Leg2.setRotationPoint(1.100000023841858F, 20.0F, 0.0F);
        this.Leg2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.Leg1 = new ModelRenderer(this, 7, 99);
        this.Leg1.setRotationPoint(-2.200000047683716F, 20.0F, 0.0F);
        this.Leg1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 60);
        this.Head.setRotationPoint(-1.5F, 14.5F, -6.900000095367432F);
        this.Head.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.Nose = new ModelRenderer(this, 0, 56);
        this.Nose.setRotationPoint(-0.5F, 16.200000762939453F, -8.899999618530273F);
        this.Nose.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.Wing8 = new ModelRenderer(this, 3, 25);
        this.Wing8.setRotationPoint(-1.399999976158142F, 14.899999618530273F, -4.699999809265137F);
        this.Wing8.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 11, 0.0F);
        this.setRotateAngle(Wing8, 0.5009095072746276F, 0.0F, 0.0F);
        this.Wing6 = new ModelRenderer(this, -1, 24);
        this.Wing6.setRotationPoint(1.2000000476837158F, 17.600000381469727F, 5.199999809265137F);
        this.Wing6.func_228301_a_(0.0F, 0.0F, 0.0F, 0, 5, 12, 0.0F);
        this.setRotateAngle(Wing6, -0.5009095072746276F, -0.1820378452539444F, 1.001644492149353F);
        this.Wing4 = new ModelRenderer(this, 0, 105);
        this.Wing4.setRotationPoint(-3.6F, 16.8F, -1.5F);
        this.Wing4.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(Wing4, 0.0F, 0.091106186954104F, 0.0F);
        this.Wing3Child = new ModelRenderer(this, 0, 98);
        this.Wing3Child.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.Wing3Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.Wing3 = new ModelRenderer(this, 0, 105);
        this.Wing3.setRotationPoint(2.6F, 16.8F, -1.5F);
        this.Wing3.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 7, 2, 0.0F);
        this.setRotateAngle(Wing3, 0.0F, -0.091106186954104F, -0.0F);
        this.Wing1 = new ModelRenderer(this, -1, 24);
        this.Wing1.setRotationPoint(-2.5F, 17.700000762939453F, 3.9000000953674316F);
        this.Wing1.func_228301_a_(0.0F, 0.0F, 0.0F, 0, 5, 12, 0.0F);
        this.setRotateAngle(Wing1, 0.0F, 0.1820378452539444F, -1.5707963705062866F);
        this.Wing4Child = new ModelRenderer(this, 0, 98);
        this.Wing4Child.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.Wing4Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.Wing9 = new ModelRenderer(this, 3, 25);
        this.Wing9.setRotationPoint(-0.2F, 13.91F, -4.7F);
        this.Wing9.func_228301_a_(0.0F, 0.0F, 0.0F, 5, 0, 11, 0.0F);
        this.setRotateAngle(Wing9, 0.6373942428283291F, -0.18203784098300857F, 0.9105382707654417F);
        this.Wing3.addChild(this.Wing3Child);
        this.Wing4.addChild(this.Wing4Child);
    }

    float timer=0;

    @Override
    public void func_225597_a_(T entity, float f, float f1, float f2, float f3, float f4) {
        timer+=0.1F;
        if(timer>90) {
            timer=0;
        }
        Wing3.rotateAngleZ= -MathHelper.sin(timer) *f1*2;
        Wing4.rotateAngleZ= MathHelper.sin(timer) *f1*2;
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return ImmutableList.of(Head
        );
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        //TODO it's rendering additional quad above bird
        return ImmutableList.of(Leg2,
                Head,
                Leg1,
                Wing6,
                Wing4,
                Wing3,
                Wing9,
                Nose,
                Wing8,
                Wing7,
                Wing10,
                Body,
                Wing1
                //Wing4Child,
                //Wing3Child
                );
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
