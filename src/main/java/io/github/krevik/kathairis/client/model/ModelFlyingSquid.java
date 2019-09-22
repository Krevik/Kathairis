package io.github.krevik.kathairis.client.model;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.entity.EntityFlyingSquid;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelSquid - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelFlyingSquid<T extends LivingEntity> extends EntityModel<T> {
    public RendererModel body1;
    public RendererModel body2;
    public RendererModel body3;
    public RendererModel body3_1;
    public RendererModel wingflap1;
    public RendererModel wingflap2;
    public RendererModel tentaclemain;
    public RendererModel tentaclemain_1;
    public RendererModel tentaclemain_2;
    public RendererModel tentaclemain_3;
    public RendererModel tentaclemain_4;
    public RendererModel tentaclemain_5;
    public RendererModel tentaclemain_6;
    public RendererModel tentaclemain_7;
    public RendererModel tentacklesecond;
    public RendererModel tentacklesecond_1;
    public RendererModel tentacklesecond_2;
    public RendererModel tentacklesecond_3;
    public RendererModel tentacklesecond_4;
    public RendererModel tentacklesecond_5;
    public RendererModel tentacklesecond_6;
    public RendererModel tentacklesecond_7;

    public ModelFlyingSquid() {
        this.textureWidth = 120;
        this.textureHeight = 64;
        this.body1 = new RendererModel(this, 0, 0);
        this.body1.setRotationPoint(-7.1F, -12.4F, -7.1F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 13, 14, 13, 0.0F);
        this.tentaclemain_5 = new RendererModel(this, 52, 0);
        this.tentaclemain_5.setRotationPoint(2.4F, 0.0F, -2.5F);
        this.tentaclemain_5.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_5, 0.27314402793711257F, 2.276432943376204F, 0.0F);
        this.tentacklesecond_6 = new RendererModel(this, 52, 0);
        this.tentacklesecond_6.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_6.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_6, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_4 = new RendererModel(this, 52, 0);
        this.tentaclemain_4.setRotationPoint(2.7F, 0.0F, 0.0F);
        this.tentaclemain_4.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_4, 0.27314402793711257F, 1.5707963267948966F, 0.0F);
        this.body3 = new RendererModel(this, 60, 0);
        this.body3.setRotationPoint(-5.5F, -30.1F, -5.6F);
        this.body3.addBox(0.0F, 0.0F, 0.0F, 10, 11, 10, 0.0F);
        this.body2 = new RendererModel(this, 54, 26);
        this.body2.setRotationPoint(-6.5F, -21.5F, -6.6F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 12, 10, 12, 0.0F);
        this.tentaclemain = new RendererModel(this, 52, 0);
        this.tentaclemain.setRotationPoint(2.1F, 0.0F, 3.3F);
        this.tentaclemain.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain, 0.27314402793711257F, 0.8726646259971648F, 0.0F);
        this.tentaclemain_2 = new RendererModel(this, 52, 0);
        this.tentaclemain_2.setRotationPoint(0.2F, 0.0F, -3.8F);
        this.tentaclemain_2.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_2, 0.27314402793711257F, 3.141592653589793F, 0.0F);
        this.tentacklesecond = new RendererModel(this, 52, 0);
        this.tentacklesecond.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_3 = new RendererModel(this, 52, 0);
        this.tentaclemain_3.setRotationPoint(-4.7F, 0.0F, 2.0F);
        this.tentaclemain_3.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_3, 0.27314402793711257F, -0.8726646259971648F, 0.0F);
        this.tentacklesecond_1 = new RendererModel(this, 52, 0);
        this.tentacklesecond_1.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_1.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_1, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentacklesecond_2 = new RendererModel(this, 52, 0);
        this.tentacklesecond_2.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_2.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_2, 0.5918411493512771F, 0.0F, 0.0F);
        this.wingflap2 = new RendererModel(this, 0, 27);
        this.wingflap2.setRotationPoint(-1.1F, -34.7F, -13.2F);
        this.wingflap2.addBox(0.0F, 0.0F, 0.0F, 1, 13, 10, 0.0F);
        this.setRotateAngle(wingflap2, 0.13700834628155487F, 0.0F, 0.0F);
        this.tentacklesecond_5 = new RendererModel(this, 52, 0);
        this.tentacklesecond_5.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_5.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_5, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_6 = new RendererModel(this, 52, 0);
        this.tentaclemain_6.setRotationPoint(-4.0F, 0.0F, -2.1F);
        this.tentaclemain_6.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_6, 0.27314402793711257F, -1.5707963267948966F, 0.0F);
        this.wingflap1 = new RendererModel(this, 0, 27);
        this.wingflap1.setRotationPoint(-1.0F, -36.2F, 2.1F);
        this.wingflap1.addBox(0.0F, 0.0F, 0.0F, 1, 13, 10, 0.0F);
        this.setRotateAngle(wingflap1, -0.136659280431156F, 0.0F, 0.0F);
        this.body3_1 = new RendererModel(this, 22, 29);
        this.body3_1.setRotationPoint(-4.5F, -36.2F, -4.5F);
        this.body3_1.addBox(0.0F, 0.0F, 0.0F, 8, 11, 8, 0.0F);
        this.tentaclemain_1 = new RendererModel(this, 52, 0);
        this.tentaclemain_1.setRotationPoint(-2.2F, 0.0F, 2.7F);
        this.tentaclemain_1.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
        this.setRotateAngle(tentaclemain_1, 0.27314402793711257F, 0.0F, 0.0F);
        this.tentacklesecond_3 = new RendererModel(this, 52, 0);
        this.tentacklesecond_3.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_3.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_3, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentacklesecond_4 = new RendererModel(this, 52, 0);
        this.tentacklesecond_4.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_4.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_4, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentacklesecond_7 = new RendererModel(this, 52, 0);
        this.tentacklesecond_7.setRotationPoint(-0.06F, 14.3F, 0.24F);
        this.tentacklesecond_7.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(tentacklesecond_7, 0.5918411493512771F, 0.0F, 0.0F);
        this.tentaclemain_7 = new RendererModel(this, 52, 0);
        this.tentaclemain_7.setRotationPoint(-2.6F, 0.0F, -4.1F);
        this.tentaclemain_7.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
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
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body1.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_5.offsetX, this.tentaclemain_5.offsetY, this.tentaclemain_5.offsetZ);
        GlStateManager.translatef(this.tentaclemain_5.rotationPointX * f5, this.tentaclemain_5.rotationPointY * f5, this.tentaclemain_5.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_5.offsetX, -this.tentaclemain_5.offsetY, -this.tentaclemain_5.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_5.rotationPointX * f5, -this.tentaclemain_5.rotationPointY * f5, -this.tentaclemain_5.rotationPointZ * f5);
        this.tentaclemain_5.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_4.offsetX, this.tentaclemain_4.offsetY, this.tentaclemain_4.offsetZ);
        GlStateManager.translatef(this.tentaclemain_4.rotationPointX * f5, this.tentaclemain_4.rotationPointY * f5, this.tentaclemain_4.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_4.offsetX, -this.tentaclemain_4.offsetY, -this.tentaclemain_4.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_4.rotationPointX * f5, -this.tentaclemain_4.rotationPointY * f5, -this.tentaclemain_4.rotationPointZ * f5);
        this.tentaclemain_4.render(f5);
        GlStateManager.popMatrix();
        this.body3.render(f5);
        this.body2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain.offsetX, this.tentaclemain.offsetY, this.tentaclemain.offsetZ);
        GlStateManager.translatef(this.tentaclemain.rotationPointX * f5, this.tentaclemain.rotationPointY * f5, this.tentaclemain.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain.offsetX, -this.tentaclemain.offsetY, -this.tentaclemain.offsetZ);
        GlStateManager.translatef(-this.tentaclemain.rotationPointX * f5, -this.tentaclemain.rotationPointY * f5, -this.tentaclemain.rotationPointZ * f5);
        this.tentaclemain.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_2.offsetX, this.tentaclemain_2.offsetY, this.tentaclemain_2.offsetZ);
        GlStateManager.translatef(this.tentaclemain_2.rotationPointX * f5, this.tentaclemain_2.rotationPointY * f5, this.tentaclemain_2.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_2.offsetX, -this.tentaclemain_2.offsetY, -this.tentaclemain_2.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_2.rotationPointX * f5, -this.tentaclemain_2.rotationPointY * f5, -this.tentaclemain_2.rotationPointZ * f5);
        this.tentaclemain_2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_3.offsetX, this.tentaclemain_3.offsetY, this.tentaclemain_3.offsetZ);
        GlStateManager.translatef(this.tentaclemain_3.rotationPointX * f5, this.tentaclemain_3.rotationPointY * f5, this.tentaclemain_3.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_3.offsetX, -this.tentaclemain_3.offsetY, -this.tentaclemain_3.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_3.rotationPointX * f5, -this.tentaclemain_3.rotationPointY * f5, -this.tentaclemain_3.rotationPointZ * f5);
        this.tentaclemain_3.render(f5);
        GlStateManager.popMatrix();
        this.wingflap2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_6.offsetX, this.tentaclemain_6.offsetY, this.tentaclemain_6.offsetZ);
        GlStateManager.translatef(this.tentaclemain_6.rotationPointX * f5, this.tentaclemain_6.rotationPointY * f5, this.tentaclemain_6.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_6.offsetX, -this.tentaclemain_6.offsetY, -this.tentaclemain_6.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_6.rotationPointX * f5, -this.tentaclemain_6.rotationPointY * f5, -this.tentaclemain_6.rotationPointZ * f5);
        this.tentaclemain_6.render(f5);
        GlStateManager.popMatrix();
        this.wingflap1.render(f5);
        this.body3_1.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_1.offsetX, this.tentaclemain_1.offsetY, this.tentaclemain_1.offsetZ);
        GlStateManager.translatef(this.tentaclemain_1.rotationPointX * f5, this.tentaclemain_1.rotationPointY * f5, this.tentaclemain_1.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_1.offsetX, -this.tentaclemain_1.offsetY, -this.tentaclemain_1.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_1.rotationPointX * f5, -this.tentaclemain_1.rotationPointY * f5, -this.tentaclemain_1.rotationPointZ * f5);
        this.tentaclemain_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.tentaclemain_7.offsetX, this.tentaclemain_7.offsetY, this.tentaclemain_7.offsetZ);
        GlStateManager.translatef(this.tentaclemain_7.rotationPointX * f5, this.tentaclemain_7.rotationPointY * f5, this.tentaclemain_7.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.0D, 1.2D);
        GlStateManager.translatef(-this.tentaclemain_7.offsetX, -this.tentaclemain_7.offsetY, -this.tentaclemain_7.offsetZ);
        GlStateManager.translatef(-this.tentaclemain_7.rotationPointX * f5, -this.tentaclemain_7.rotationPointY * f5, -this.tentaclemain_7.rotationPointZ * f5);
        this.tentaclemain_7.render(f5);
        GlStateManager.popMatrix();
    }

    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    private float degToRad(float deg) {
    	return Kathairis.getHelper().degToRad(deg);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5)
    {
    	super.setRotationAngles(entity, limbSwing, limbSwingAmount, age, f3, f4, f5);
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
}
