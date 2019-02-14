package mod.krevik.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * slimebasse! - Hugo
 * Created using Tabula 7.0.0
 */
public class ModelFungite extends ModelBase {
    public ModelRenderer leg15;
    public ModelRenderer leg25;
    public ModelRenderer body_2;
    public ModelRenderer body_3;
    public ModelRenderer body_5;
    public ModelRenderer mouth_layer;
    public ModelRenderer arm_8;
    public ModelRenderer arm_4;
    public ModelRenderer leg15_1;
    public ModelRenderer Cap_base;
    public ModelRenderer cap_top;
    public ModelRenderer arm_6;
    public ModelRenderer body_4;
    public ModelRenderer body_1;
    public ModelRenderer arm_1;
    public ModelRenderer arm_2;
    public ModelRenderer leg45;
    public ModelRenderer leg35;
    public ModelRenderer arm_5;
    public ModelRenderer arm_7;
    public ModelRenderer arm_3;

    public ModelFungite() {
        this.textureWidth = 120;
        this.textureHeight = 64;
        this.arm_6 = new ModelRenderer(this, 0, 27);
        this.arm_6.setRotationPoint(2.4F, 0.0F, -0.6F);
        this.arm_6.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.setRotateAngle(arm_6, 0.0F, 0.36425021489121656F, -1.8668041679331349F);
        this.leg25 = new ModelRenderer(this, 38, 31);
        this.leg25.setRotationPoint(-5.7F, 7.9F, 0.1F);
        this.leg25.addBox(0.0F, 0.0F, 0.0F, 4, 9, 4, 0.0F);
        this.setRotateAngle(leg25, 0.31869712141416456F, 1.1838568316277536F, 0.5009094953223726F);
        this.arm_4 = new ModelRenderer(this, 62, 21);
        this.arm_4.setRotationPoint(-6.8F, 5.9F, 0.7F);
        this.arm_4.addBox(0.0F, 0.0F, 0.0F, 9, 3, 3, 0.0F);
        this.setRotateAngle(arm_4, 0.6829473363053812F, 2.5497515042385164F, -0.5009094953223726F);
        this.body_4 = new ModelRenderer(this, 28, 0);
        this.body_4.setRotationPoint(-8.5F, 0.2F, -0.1F);
        this.body_4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.arm_2 = new ModelRenderer(this, 40, 21);
        this.arm_2.setRotationPoint(-6.8F, 0.0F, 0.9F);
        this.arm_2.addBox(0.0F, 0.0F, 0.0F, 8, 3, 3, 0.0F);
        this.setRotateAngle(arm_2, 0.0F, 2.5497515042385164F, 0.0F);
        this.body_1 = new ModelRenderer(this, 68, 44);
        this.body_1.setRotationPoint(-7.0F, 7.7F, -3.3F);
        this.body_1.addBox(0.0F, 0.0F, 0.0F, 12, 4, 6, 0.0F);
        this.setRotateAngle(body_1, 0.0F, 0.0F, 0.18203784098300857F);
        this.arm_1 = new ModelRenderer(this, 0, 21);
        this.arm_1.setRotationPoint(-7.0F, -0.7F, 0.1F);
        this.arm_1.addBox(0.0F, 0.0F, 0.0F, 9, 3, 3, 0.0F);
        this.setRotateAngle(arm_1, 0.5009094953223726F, -3.141592653589793F, 0.7740535232594852F);
        this.mouth_layer = new ModelRenderer(this, 0, 46);
        this.mouth_layer.setRotationPoint(-7.4F, -3.0F, -2.4F);
        this.mouth_layer.addBox(0.0F, 0.0F, 0.0F, 11, 14, 4, 0.0F);
        this.leg35 = new ModelRenderer(this, 54, 32);
        this.leg35.setRotationPoint(2.9F, 7.9F, 4.0F);
        this.leg35.addBox(0.0F, 0.0F, 0.0F, 3, 8, 4, 0.0F);
        this.setRotateAngle(leg35, 0.0F, 2.9140017191297325F, 0.0F);
        this.body_5 = new ModelRenderer(this, 30, 55);
        this.body_5.setRotationPoint(-7.3F, -4.3F, -3.2F);
        this.body_5.addBox(0.0F, 0.0F, 0.0F, 11, 3, 6, 0.0F);
        this.arm_5 = new ModelRenderer(this, 26, 30);
        this.arm_5.setRotationPoint(6.8F, 1.7F, -0.6F);
        this.arm_5.addBox(0.0F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.setRotateAngle(arm_5, 0.5462880558742251F, 0.0F, -0.7285004297824331F);
        this.body_3 = new ModelRenderer(this, 64, 54);
        this.body_3.setRotationPoint(-7.7F, -2.85F, -3.3F);
        this.body_3.addBox(0.0F, 0.0F, 0.0F, 12, 4, 6, 0.0F);
        this.setRotateAngle(body_3, 0.136659280431156F, 0.045553093477052F, 0.22759093446006054F);
        this.arm_8 = new ModelRenderer(this, 0, 40);
        this.arm_8.setRotationPoint(2.3F, 0.0F, -1.6F);
        this.arm_8.addBox(0.0F, 0.0F, 0.0F, 10, 3, 3, 0.0F);
        this.setRotateAngle(arm_8, 0.045553093477052F, 0.045553093477052F, 0.40980330836826856F);
        this.leg45 = new ModelRenderer(this, 84, 29);
        this.leg45.setRotationPoint(0.0F, 6.4F, 0.0F);
        this.leg45.addBox(0.0F, 0.0F, 0.0F, 4, 12, 3, 0.0F);
        this.setRotateAngle(leg45, 0.091106186954104F, 0.024434609527920613F, 0.05235987755982988F);
        this.leg15 = new ModelRenderer(this, 68, 33);
        this.leg15.setRotationPoint(2.1F, 6.2F, -2.8F);
        this.leg15.addBox(0.0F, 0.0F, 0.0F, 4, 7, 4, 0.0F);
        this.setRotateAngle(leg15, -0.5462880558742251F, -0.8651597102135892F, 0.0F);
        this.arm_3 = new ModelRenderer(this, 24, 21);
        this.arm_3.setRotationPoint(4.4F, 0.7F, -0.4F);
        this.arm_3.addBox(0.0F, 0.0F, 0.0F, 5, 3, 3, 0.0F);
        this.setRotateAngle(arm_3, 0.22759093446006054F, 0.18203784098300857F, 0.0F);
        this.body_2 = new ModelRenderer(this, 30, 44);
        this.body_2.setRotationPoint(-8.6F, 5.8F, -3.4F);
        this.body_2.addBox(0.0F, 0.0F, 0.0F, 13, 5, 6, 0.0F);
        this.setRotateAngle(body_2, 0.18203784098300857F, 0.0F, -0.31869712141416456F);
        this.cap_top = new ModelRenderer(this, 79, 4);
        this.cap_top.setRotationPoint(-7.0F, -16.6F, -6.0F);
        this.cap_top.addBox(0.0F, 0.0F, 0.0F, 10, 3, 10, 0.0F);
        this.leg15_1 = new ModelRenderer(this, 2, 9);
        this.leg15_1.setRotationPoint(-4.9F, -10.7F, -4.0F);
        this.leg15_1.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.Cap_base = new ModelRenderer(this, 27, 1);
        this.Cap_base.setRotationPoint(-8.5F, -13.6F, -7.5F);
        this.Cap_base.addBox(0.0F, 0.0F, 0.0F, 13, 4, 13, 0.0F);
        this.arm_7 = new ModelRenderer(this, 12, 29);
        this.arm_7.setRotationPoint(-1.1F, 10.4F, 2.5F);
        this.arm_7.addBox(0.0F, 0.0F, 0.0F, 4, 8, 3, 0.0F);
        this.setRotateAngle(arm_7, -0.9105382707654417F, -0.045553093477052F, -1.2747884856566583F);
        this.leg25.addChild(this.leg35);
        this.arm_4.addChild(this.arm_5);
        this.leg15.addChild(this.leg45);
        this.arm_2.addChild(this.arm_3);
        this.arm_6.addChild(this.arm_7);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.arm_6.render(f5);
        this.leg25.render(f5);
        this.arm_4.render(f5);
        this.body_4.render(f5);
        this.arm_2.render(f5);
        this.body_1.render(f5);
        this.arm_1.render(f5);
        this.mouth_layer.render(f5);
        this.body_5.render(f5);
        this.body_3.render(f5);
        this.arm_8.render(f5);
        this.leg15.render(f5);
        this.body_2.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.cap_top.offsetX, this.cap_top.offsetY, this.cap_top.offsetZ);
        GlStateManager.translate(this.cap_top.rotationPointX * f5, this.cap_top.rotationPointY * f5, this.cap_top.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.cap_top.offsetX, -this.cap_top.offsetY, -this.cap_top.offsetZ);
        GlStateManager.translate(-this.cap_top.rotationPointX * f5, -this.cap_top.rotationPointY * f5, -this.cap_top.rotationPointZ * f5);
        this.cap_top.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leg15_1.offsetX, this.leg15_1.offsetY, this.leg15_1.offsetZ);
        GlStateManager.translate(this.leg15_1.rotationPointX * f5, this.leg15_1.rotationPointY * f5, this.leg15_1.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.leg15_1.offsetX, -this.leg15_1.offsetY, -this.leg15_1.offsetZ);
        GlStateManager.translate(-this.leg15_1.rotationPointX * f5, -this.leg15_1.rotationPointY * f5, -this.leg15_1.rotationPointZ * f5);
        this.leg15_1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Cap_base.offsetX, this.Cap_base.offsetY, this.Cap_base.offsetZ);
        GlStateManager.translate(this.Cap_base.rotationPointX * f5, this.Cap_base.rotationPointY * f5, this.Cap_base.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.Cap_base.offsetX, -this.Cap_base.offsetY, -this.Cap_base.offsetZ);
        GlStateManager.translate(-this.Cap_base.rotationPointX * f5, -this.Cap_base.rotationPointY * f5, -this.Cap_base.rotationPointZ * f5);
        this.Cap_base.render(f5);
        GlStateManager.popMatrix();
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
        this.arm_7.rotateAngleX=(float)-20+(MathHelper.sin((float)(f2*0.1))*0.2f);
        //this.arm_7.rotateAngleZ=(float)-30+(MathHelper.sin((float)(f2*0.1))*0.05f);
        this.arm_6.rotateAngleZ=(float)-90+(MathHelper.sin((float)(f2*0.1))*0.1f);
        this.arm_1.rotateAngleY=(float)-3.1067+(MathHelper.sin((float)(f2*0.1))*0.1f);
        this.arm_2.rotateAngleZ= +(MathHelper.sin((float)(f2*0.1))*0.1f);
        this.arm_8.rotateAngleY=(float)0.045553093477052+(MathHelper.sin((float)(f2*0.1))*0.1f);
        this.arm_4.rotateAngleZ=(float)-0.5009094953223726+(MathHelper.sin((float)(f2*0.1))*0.1f);
        
        this.leg15.rotateAngleX = -0.5462880558742251F+MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.6F * limbSwingAmount;
        this.leg25.rotateAngleY = 1.1838568316277536F+MathHelper.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount;
        this.leg15.rotateAngleY = -0.2617993878f;
        this.leg25.rotateAngleX = 0.31415926536f-MathHelper.sin(limbSwing) * limbSwingAmount+1;
        this.leg25.rotateAngleZ = 0.5235987756F-MathHelper.sin(limbSwing) * limbSwingAmount+1;

    }
}
