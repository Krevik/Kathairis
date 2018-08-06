package com.Krevik.Models.Butterflies;

import com.Krevik.Main.KCore;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * Ruby Sile - Hkhugo
 * Created using Tabula 7.0.0
 */
public class ModelRubySile extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Wing4;
    public ModelRenderer Wing3;
    public ModelRenderer lowerbody;
    public ModelRenderer Czulko1;
    public ModelRenderer Body;

    public ModelRubySile() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Wing4 = new ModelRenderer(this, 11, 3);
        this.Wing4.setRotationPoint(0.6F, 22.0F, -12.6F);
        this.Wing4.addBox(0.0F, 0.0F, 0.0F, 15, 0, 27, 0.0F);
        this.Wing3 = new ModelRenderer(this, 60, 33);
        this.Wing3.setRotationPoint(-0.6F, 22.0F, -12.6F);
        this.Wing3.addBox(-15.0F, 0.0F, 0.0F, 15, 0, 27, 0.0F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(-2.0F, 21.0F, -6.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
        this.Head = new ModelRenderer(this, 0, 38);
        this.Head.setRotationPoint(-2.0F, 20.5F, -10.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.Czulko1 = new ModelRenderer(this, 0, 30);
        this.Czulko1.setRotationPoint(-3.0F, 19.1F, -14.7F);
        this.Czulko1.addBox(0.0F, 0.0F, 0.0F, 6, 0, 6, 0.0F);
        this.setRotateAngle(Czulko1, -0.2617993877991494F, 0.0F, 0.0F);
        this.lowerbody = new ModelRenderer(this, 0, 46);
        this.lowerbody.setRotationPoint(-1.0F, 21.5F, 0.8F);
        this.lowerbody.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Wing4.render(f5);
        this.Wing3.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
        this.Czulko1.render(f5);
        this.lowerbody.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
    	super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    	if(entity.motionY!=0D||entity.motionX!=0D||entity.motionZ!=0D) {
        	Wing4.rotateAngleZ=MathHelper.sin(f2*0.6f);
        	Wing3.rotateAngleZ=-MathHelper.sin(f2*0.6f);	
    	}else {
        	Wing4.rotateAngleZ=-KCore.instance.functionHelper.degToRad(70)-MathHelper.sin(f2*0.05f)*0.05f;
        	Wing3.rotateAngleZ=KCore.instance.functionHelper.degToRad(70)+MathHelper.sin(f2*0.05f)*0.05f;	
    	}
    }
}
