package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.entity.EntityFlyingSquid;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelSquid - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelFlyingSquid<T extends LivingEntity> extends AgeableModel<T> {
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer body3_1;
    public ModelRenderer wingflap1;
    public ModelRenderer wingflap2;
    public ModelRenderer tentaclemain;
    public ModelRenderer tentaclemain_1;
    public ModelRenderer tentaclemain_2;
    public ModelRenderer tentaclemain_3;
    public ModelRenderer tentaclemain_4;
    public ModelRenderer tentaclemain_5;
    public ModelRenderer tentaclemain_6;
    public ModelRenderer tentaclemain_7;
    public ModelRenderer tentacklesecond;
    public ModelRenderer tentacklesecond_1;
    public ModelRenderer tentacklesecond_2;
    public ModelRenderer tentacklesecond_3;
    public ModelRenderer tentacklesecond_4;
    public ModelRenderer tentacklesecond_5;
    public ModelRenderer tentacklesecond_6;
    public ModelRenderer tentacklesecond_7;

    public ModelFlyingSquid() {
        this.textureWidth = 120;
        this.textureHeight = 64;
        this.body1 = new ModelRenderer(this, 0, 0);
        this.body1.setRotationPoint(-7.1F, -12.4F, -7.1F);
        this.body1.func_228301_a_(0.0F, 0.0F, 0.0F, 13, 14, 13, 0.0F);
        this.tentaclemain_5 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_5.setRotationPoint(2.4F, 0.0F, -2.5F);
        this.tentaclemain_5.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_5, 0.27314402793711257F, 2.276432943376204F, 0.0F);
        this.tentacklesecond_6 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_6.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_6.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_6, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_4 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_4.setRotationPoint(2.7F, 0.0F, 0.0F);
        this.tentaclemain_4.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_4, 0.27314402793711257F, 1.5707963267948966F, 0.0F);
        this.body3 = new ModelRenderer(this, 60, 0);
        this.body3.setRotationPoint(-5.5F, -30.1F, -5.6F);
        this.body3.func_228301_a_(0.0F, 0.0F, 0.0F, 10, 11, 10, 0.0F);
        this.body2 = new ModelRenderer(this, 54, 26);
        this.body2.setRotationPoint(-6.5F, -21.5F, -6.6F);
        this.body2.func_228301_a_(0.0F, 0.0F, 0.0F, 12, 10, 12, 0.0F);
        this.tentaclemain = new ModelRenderer(this, 52, 0);
        this.tentaclemain.setRotationPoint(2.1F, 0.0F, 3.3F);
        this.tentaclemain.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain, 0.27314402793711257F, 0.8726646259971648F, 0.0F);
        this.tentaclemain_2 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_2.setRotationPoint(0.2F, 0.0F, -3.8F);
        this.tentaclemain_2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_2, 0.27314402793711257F, 3.141592653589793F, 0.0F);
        this.tentacklesecond = new ModelRenderer(this, 52, 0);
        this.tentacklesecond.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_3 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_3.setRotationPoint(-4.7F, 0.0F, 2.0F);
        this.tentaclemain_3.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_3, 0.27314402793711257F, -0.8726646259971648F, 0.0F);
        this.tentacklesecond_1 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_1.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_1.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_1, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentacklesecond_2 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_2.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_2, 0.5918411493512771F, 0.0F, 0.0F);
        this.wingflap2 = new ModelRenderer(this, 0, 27);
        this.wingflap2.setRotationPoint(-1.1F, -34.7F, -13.2F);
        this.wingflap2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 13, 10, 0.0F);
        this.setRotateAngle(wingflap2, 0.13700834628155487F, 0.0F, 0.0F);
        this.tentacklesecond_5 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_5.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_5.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_5, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_6 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_6.setRotationPoint(-4.0F, 0.0F, -2.1F);
        this.tentaclemain_6.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_6, 0.27314402793711257F, -1.5707963267948966F, 0.0F);
        this.wingflap1 = new ModelRenderer(this, 0, 27);
        this.wingflap1.setRotationPoint(-1.0F, -36.2F, 2.1F);
        this.wingflap1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 13, 10, 0.0F);
        this.setRotateAngle(wingflap1, -0.136659280431156F, 0.0F, 0.0F);
        this.body3_1 = new ModelRenderer(this, 22, 29);
        this.body3_1.setRotationPoint(-4.5F, -36.2F, -4.5F);
        this.body3_1.func_228301_a_(0.0F, 0.0F, 0.0F, 8, 11, 8, 0.0F);
        this.tentaclemain_1 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_1.setRotationPoint(-2.2F, 0.0F, 2.7F);
        this.tentaclemain_1.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_1, 0.27314402793711257F, 0.0F, 0.0F);
        this.tentacklesecond_3 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_3.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_3.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_3, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentacklesecond_4 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_4.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_4.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_4, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentacklesecond_7 = new ModelRenderer(this, 52, 0);
        this.tentacklesecond_7.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_7.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_7, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_7 = new ModelRenderer(this, 52, 0);
        this.tentaclemain_7.setRotationPoint(-2.6F, 0.0F, -4.1F);
        this.tentaclemain_7.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_7, 0.27314402793711257F, -2.4586453172844123F, 0.0F);
        this.tentaclemain_6.addChild(this.tentacklesecond_6);
        this.tentaclemain.addChild(this.tentacklesecond);
        this.tentaclemain_1.addChild(this.tentacklesecond_1);
        this.tentaclemain_2.addChild(this.tentacklesecond_2);
        this.tentaclemain_5.addChild(this.tentacklesecond_5);
        this.tentaclemain_3.addChild(this.tentacklesecond_3);
        this.tentaclemain_4.addChild(this.tentacklesecond_4);
        this.tentaclemain_7.addChild(this.tentacklesecond_7);
    }

    @Override
    public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float age, float p_225597_5_, float p_225597_6_) {
        if(entity instanceof EntityFlyingSquid) {
            EntityFlyingSquid squid = (EntityFlyingSquid) entity;
            float animTimer = squid.animTravelTime;
            float animTimer2 = squid.animTravelTime2;





            if(squid.isHoldingPlayer()) {
                tentaclemain.rotateAngleX=degToRad(15.65f);
                tentaclemain_1.rotateAngleX=degToRad(15.65f);
                tentaclemain_2.rotateAngleX=degToRad(15.65f);
                tentaclemain_3.rotateAngleX=degToRad(15.65f);
                tentaclemain_4.rotateAngleX=degToRad(15.65f);
                tentaclemain_6.rotateAngleX=degToRad(15.65f);
                tentaclemain_5.rotateAngleX=degToRad(15.65f);
                tentaclemain_7.rotateAngleX=degToRad(15.65f);

                tentacklesecond_5.rotateAngleX=degToRad(-42f);
                tentacklesecond_1.rotateAngleX=degToRad(-42f);
                tentacklesecond_7.rotateAngleX=degToRad(-42f);
                tentacklesecond_3.rotateAngleX=degToRad(-42f);
                tentacklesecond.rotateAngleX=degToRad(-42f);
                tentacklesecond_2.rotateAngleX=degToRad(-42f);
                tentacklesecond_4.rotateAngleX=degToRad(-42f);
                tentacklesecond_6.rotateAngleX=degToRad(-42f);
            }else {
                tentaclemain.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_1.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_2.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_3.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_4.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_6.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_5.rotateAngleX=degToRad(15.65f)+animTimer2/100;
                tentaclemain_7.rotateAngleX=degToRad(15.65f)+animTimer2/100;

                tentacklesecond_5.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond_1.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond_7.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond_3.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond_2.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond_4.rotateAngleX=degToRad(33.91f)+animTimer/100;
                tentacklesecond_6.rotateAngleX=degToRad(33.91f)+animTimer/100;
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
        RenderSystem.translatef(this.tentaclemain_5.offsetX, this.tentaclemain_5.offsetY, this.tentaclemain_5.offsetZ);
        RenderSystem.translatef(this.tentaclemain_5.rotationPointX * f5, this.tentaclemain_5.rotationPointY * f5, this.tentaclemain_5.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_5.offsetX, -this.tentaclemain_5.offsetY, -this.tentaclemain_5.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_5.rotationPointX * f5, -this.tentaclemain_5.rotationPointY * f5, -this.tentaclemain_5.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain_4.offsetX, this.tentaclemain_4.offsetY, this.tentaclemain_4.offsetZ);
        RenderSystem.translatef(this.tentaclemain_4.rotationPointX * f5, this.tentaclemain_4.rotationPointY * f5, this.tentaclemain_4.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_4.offsetX, -this.tentaclemain_4.offsetY, -this.tentaclemain_4.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_4.rotationPointX * f5, -this.tentaclemain_4.rotationPointY * f5, -this.tentaclemain_4.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain.offsetX, this.tentaclemain.offsetY, this.tentaclemain.offsetZ);
        RenderSystem.translatef(this.tentaclemain.rotationPointX * f5, this.tentaclemain.rotationPointY * f5, this.tentaclemain.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain.offsetX, -this.tentaclemain.offsetY, -this.tentaclemain.offsetZ);
        RenderSystem.translatef(-this.tentaclemain.rotationPointX * f5, -this.tentaclemain.rotationPointY * f5, -this.tentaclemain.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain_2.offsetX, this.tentaclemain_2.offsetY, this.tentaclemain_2.offsetZ);
        RenderSystem.translatef(this.tentaclemain_2.rotationPointX * f5, this.tentaclemain_2.rotationPointY * f5, this.tentaclemain_2.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_2.offsetX, -this.tentaclemain_2.offsetY, -this.tentaclemain_2.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_2.rotationPointX * f5, -this.tentaclemain_2.rotationPointY * f5, -this.tentaclemain_2.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain_3.offsetX, this.tentaclemain_3.offsetY, this.tentaclemain_3.offsetZ);
        RenderSystem.translatef(this.tentaclemain_3.rotationPointX * f5, this.tentaclemain_3.rotationPointY * f5, this.tentaclemain_3.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_3.offsetX, -this.tentaclemain_3.offsetY, -this.tentaclemain_3.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_3.rotationPointX * f5, -this.tentaclemain_3.rotationPointY * f5, -this.tentaclemain_3.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain_6.offsetX, this.tentaclemain_6.offsetY, this.tentaclemain_6.offsetZ);
        RenderSystem.translatef(this.tentaclemain_6.rotationPointX * f5, this.tentaclemain_6.rotationPointY * f5, this.tentaclemain_6.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_6.offsetX, -this.tentaclemain_6.offsetY, -this.tentaclemain_6.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_6.rotationPointX * f5, -this.tentaclemain_6.rotationPointY * f5, -this.tentaclemain_6.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain_1.offsetX, this.tentaclemain_1.offsetY, this.tentaclemain_1.offsetZ);
        RenderSystem.translatef(this.tentaclemain_1.rotationPointX * f5, this.tentaclemain_1.rotationPointY * f5, this.tentaclemain_1.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_1.offsetX, -this.tentaclemain_1.offsetY, -this.tentaclemain_1.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_1.rotationPointX * f5, -this.tentaclemain_1.rotationPointY * f5, -this.tentaclemain_1.rotationPointZ * f5);
        RenderSystem.popMatrix();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(this.tentaclemain_7.offsetX, this.tentaclemain_7.offsetY, this.tentaclemain_7.offsetZ);
        RenderSystem.translatef(this.tentaclemain_7.rotationPointX * f5, this.tentaclemain_7.rotationPointY * f5, this.tentaclemain_7.rotationPointZ * f5);
        RenderSystem.scaled(1.2D, 1.0D, 1.2D);
        RenderSystem.translatef(-this.tentaclemain_7.offsetX, -this.tentaclemain_7.offsetY, -this.tentaclemain_7.offsetZ);
        RenderSystem.translatef(-this.tentaclemain_7.rotationPointX * f5, -this.tentaclemain_7.rotationPointY * f5, -this.tentaclemain_7.rotationPointZ * f5);
        RenderSystem.popMatrix();*/

        return ImmutableList.of(body1,tentaclemain_5,tentaclemain_4,body3,body2,tentaclemain,tentaclemain_2,
                tentaclemain_3,wingflap2,tentaclemain_6,wingflap1,body3_1,tentaclemain_1,tentaclemain_7);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    private float degToRad(float deg) {
    	return Kathairis.getHelper().degToRad(deg);
    }
}
