package mod.krevik.client.model;

import mod.krevik.KCore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * sky ray - HKhugo
 * Created using Tabula 7.0.0
 */
public class ModelSkyray extends ModelBase {
    public ModelRenderer body1;
    public ModelRenderer body1_1;
    public ModelRenderer body1_2;
    public ModelRenderer body1_3;
    public ModelRenderer Tail1;
    public ModelRenderer Wing21;
    public ModelRenderer Wing11;
    public ModelRenderer body1_4;
    public ModelRenderer body1_5;
    public ModelRenderer body1_6;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer Tail5;
    public ModelRenderer Wing22;
    public ModelRenderer Wing23;
    public ModelRenderer Wing24;
    public ModelRenderer Wing25;
    public ModelRenderer Wing26;
    public ModelRenderer Wing12;
    public ModelRenderer Wing13;
    public ModelRenderer Wing14;
    public ModelRenderer Wing15;
    public ModelRenderer Wing16;

    public ModelSkyray() {
        this.textureWidth = 350;
        this.textureHeight = 246;
        this.body1 = new ModelRenderer(this, 0, 22);
        this.body1.setRotationPoint(1.0F, 16.0F, -13.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 31, 5, 24, 0.0F);
        this.Tail1 = new ModelRenderer(this, 86, 0);
        this.Tail1.setRotationPoint(-17.5F, 17.6F, -1.0F);
        this.Tail1.addBox(-7.5F, -3.5F, -5.0F, 15, 7, 10, 0.0F);
        this.body1_6 = new ModelRenderer(this, 0, 12);
        this.body1_6.setRotationPoint(30.3F, 15.5F, -5.8F);
        this.body1_6.addBox(0.0F, 0.0F, 0.0F, 3, 6, 9, 0.0F);
        this.body1_1 = new ModelRenderer(this, 221, 66);
        this.body1_1.setRotationPoint(-15.0F, 16.0F, -10.0F);
        this.body1_1.addBox(0.0F, 0.0F, 0.0F, 29, 6, 18, 0.0F);
        this.Wing14 = new ModelRenderer(this, 2, 51);
        this.Wing14.setRotationPoint(16.1F, -0.1F, -0.7F);
        this.Wing14.addBox(0.0F, 0.0F, 0.0F, 12, 3, 10, 0.0F);
        this.setRotateAngle(Wing14, 0.0F, 1.1383037381507017F, 0.0F);
        this.body1_5 = new ModelRenderer(this, 0, 0);
        this.body1_5.setRotationPoint(30.0F, 14.0F, -9.0F);
        this.body1_5.addBox(0.0F, 0.0F, 0.0F, 12, 6, 4, 0.0F);
        this.Wing26 = new ModelRenderer(this, 129, 19);
        this.Wing26.setRotationPoint(8.0F, 0.0F, 1.0F);
        this.Wing26.addBox(0.0F, 0.0F, 0.0F, 20, 4, 4, 0.0F);
        this.Wing25 = new ModelRenderer(this, 0, 64);
        this.Wing25.setRotationPoint(12.0F, 0.0F, 2.0F);
        this.Wing25.addBox(0.0F, 0.0F, 0.0F, 8, 3, 6, 0.0F);
        this.Wing16 = new ModelRenderer(this, 129, 19);
        this.Wing16.setRotationPoint(8.0F, 0.0F, 1.0F);
        this.Wing16.addBox(0.0F, 0.0F, 0.0F, 20, 4, 4, 0.0F);
        this.Wing23 = new ModelRenderer(this, 188, 18);
        this.Wing23.setRotationPoint(17.8F, -1.4F, 3.0F);
        this.Wing23.addBox(0.0F, 0.0F, 0.0F, 26, 3, 26, 0.0F);
        this.setRotateAngle(Wing23, 0.0F, -0.7766715171374766F, 0.0F);
        this.Wing12 = new ModelRenderer(this, 2, 150);
        this.Wing12.setRotationPoint(15.1F, 0.1F, -20.7F);
        this.Wing12.addBox(0.0F, -1.5F, -22.0F, 36, 3, 44, 0.0F);
        this.setRotateAngle(Wing12, 0.0F, -0.6365215782023319F, 0.0F);
        this.Wing21 = new ModelRenderer(this, 118, 55);
        this.Wing21.setRotationPoint(14.9F, 19.5F, 6.2F);
        this.Wing21.addBox(0.0F, -1.5F, -18.5F, 33, 3, 37, 0.0F);
        this.setRotateAngle(Wing21, 0.0F, -1.8668041679331349F, 0.0F);
        this.Wing11 = new ModelRenderer(this, 118, 110);
        this.Wing11.setRotationPoint(14.9F, 19.5F, -8.2F);
        this.Wing11.addBox(0.0F, -1.5F, -18.5F, 33, 3, 37, 0.0F);
        this.setRotateAngle(Wing11, 0.0F, 1.8657569703819383F, 0.0F);
        this.Wing24 = new ModelRenderer(this, 2, 51);
        this.Wing24.setRotationPoint(26.3F, -0.1F, 22.2F);
        this.Wing24.addBox(0.0F, 0.0F, 0.0F, 12, 3, 10, 0.0F);
        this.setRotateAngle(Wing24, 0.0F, -1.1383037381507017F, 0.0F);
        this.Wing13 = new ModelRenderer(this, 238, 101);
        this.Wing13.setRotationPoint(-0.3F, -1.4F, -22.0F);
        this.Wing13.addBox(0.0F, 0.0F, 0.0F, 26, 3, 26, 0.0F);
        this.setRotateAngle(Wing13, 0.0F, 0.7766715171374766F, 0.0F);
        this.body1_2 = new ModelRenderer(this, 110, 27);
        this.body1_2.setRotationPoint(2.0F, 13.0F, -11.0F);
        this.body1_2.addBox(0.0F, 0.0F, 0.0F, 29, 4, 20, 0.0F);
        this.body1_3 = new ModelRenderer(this, 238, 47);
        this.body1_3.setRotationPoint(-12.5F, 13.2F, -8.0F);
        this.body1_3.addBox(0.0F, 0.0F, 0.0F, 15, 4, 14, 0.0F);
        this.Tail4 = new ModelRenderer(this, 250, 0);
        this.Tail4.setRotationPoint(-21.9F, 0.0F, 0.0F);
        this.Tail4.addBox(-37.0F, -2.0F, -2.0F, 37, 4, 4, 0.0F);
        this.Wing22 = new ModelRenderer(this, 2, 51);
        this.Wing22.setRotationPoint(15.1F, 0.1F, 20.7F);
        this.Wing22.addBox(0.0F, -1.5F, -22.0F, 36, 3, 44, 0.0F);
        this.setRotateAngle(Wing22, 0.0F, 0.6365215782023319F, 0.0F);
        this.body1_4 = new ModelRenderer(this, 32, 0);
        this.body1_4.setRotationPoint(30.0F, 14.0F, 3.0F);
        this.body1_4.addBox(0.0F, 0.0F, 0.0F, 12, 6, 4, 0.0F);
        this.Tail3 = new ModelRenderer(this, 145, 0);
        this.Tail3.setRotationPoint(-13.9F, 0.0F, 0.0F);
        this.Tail3.addBox(-22.0F, -2.5F, -2.5F, 22, 5, 5, 0.0F);
        this.Tail2 = new ModelRenderer(this, 200, 0);
        this.Tail2.setRotationPoint(-7.3F, 0.0F, 0.0F);
        this.Tail2.addBox(-14.0F, -2.5F, -2.5F, 14, 5, 5, 0.0F);
        this.Wing15 = new ModelRenderer(this, 0, 64);
        this.Wing15.setRotationPoint(12.0F, 0.0F, 2.0F);
        this.Wing15.addBox(0.0F, 0.0F, 0.0F, 8, 3, 6, 0.0F);
        this.Tail5 = new ModelRenderer(this, 250, 18);
        this.Tail5.setRotationPoint(-36.9F, 0.0F, 0.0F);
        this.Tail5.addBox(-21.0F, 0.0F, -11.0F, 21, 0, 22, 0.0F);
        this.Wing13.addChild(this.Wing14);
        this.Wing25.addChild(this.Wing26);
        this.Wing24.addChild(this.Wing25);
        this.Wing15.addChild(this.Wing16);
        this.Wing22.addChild(this.Wing23);
        this.Wing11.addChild(this.Wing12);
        this.Wing23.addChild(this.Wing24);
        this.Wing12.addChild(this.Wing13);
        this.Tail3.addChild(this.Tail4);
        this.Wing21.addChild(this.Wing22);
        this.Tail2.addChild(this.Tail3);
        this.Tail1.addChild(this.Tail2);
        this.Wing14.addChild(this.Wing15);
        this.Tail4.addChild(this.Tail5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body1.render(f5);
        this.Tail1.render(f5);
        this.body1_6.render(f5);
        this.body1_1.render(f5);
        this.body1_5.render(f5);
        this.Wing21.render(f5);
        this.Wing11.render(f5);
        this.body1_2.render(f5);
        this.body1_3.render(f5);
        this.body1_4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5, Entity entity)
    {
    	super.setRotationAngles(limbSwing, limbSwingAmount, age, f3, f4, f5, entity);
        Wing11.rotateAngleZ=MathHelper.sin((age/10))*0.2f;
        Wing21.rotateAngleZ=MathHelper.sin((age/10))*0.2f;
        
        Wing11.rotateAngleY= KCore.functionHelper.degToRad(106.9f)+MathHelper.sin((age/10))*0.2f;
        Wing21.rotateAngleY=-KCore.functionHelper.degToRad(106.9f)-MathHelper.sin((age/10))*0.2f;
        
        Tail2.rotateAngleY=MathHelper.sin((age/10) *0.8f)*0.13f;
        Tail3.rotateAngleY=MathHelper.sin((age/10) *0.8f)*0.14f;
        Tail4.rotateAngleY=MathHelper.sin((age/10) *0.8f)*0.18f;
        
        Tail5.rotateAngleX=-MathHelper.sin((age/10) *0.8f)*1f;
        
        Tail4.rotateAngleZ=MathHelper.sin((age/10) *1.6f)*0.2f;



    }
}
