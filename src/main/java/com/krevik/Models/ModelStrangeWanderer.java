package com.krevik.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * MysteriousWanderer.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelStrangeWanderer extends ModelBase {
    public ModelRenderer flower;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer stick;
    public ModelRenderer backpack1;
    public ModelRenderer backpack2;
    public ModelRenderer hat1;
    public ModelRenderer beard;

    public ModelStrangeWanderer() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setRotationPoint(-0.5F, 0.8F, -3.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 9, 12, 4, 0.0F);
        this.setRotateAngle(body, 0.2617993877991494F, -0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 16, 48);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.hat1 = new ModelRenderer(this, 0, 32);
        this.hat1.setRotationPoint(-5.0F, -5.5F, -8.0F);
        this.hat1.addBox(0.0F, 0.0F, 0.0F, 10, 1, 10, 0.0F);
        this.rightarm = new ModelRenderer(this, 32, 48);
        this.rightarm.setRotationPoint(-5.0F, 2.0F, -2.0F);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(rightarm, -0.04363323129985824F, -0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 42, 16);
        this.leftarm.setRotationPoint(5.0F, 2.5F, -3.0F);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(leftarm, -0.7285004297824331F, -0.0F, 0.0F);
        this.flower = new ModelRenderer(this, 49, -2);
        this.flower.setRotationPoint(5.0F, 1.0F, -11.7F);
        this.flower.addBox(0.0F, 0.0F, 0.0F, 0, 3, 3, 0.0F);
        this.setRotateAngle(flower, 0.0F, 0.7740535232594852F, -0.31869712141416456F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.5F, -3.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.beard = new ModelRenderer(this, 24, 0);
        this.beard.setRotationPoint(-2.5F, 0.0F, -6.5F);
        this.beard.addBox(0.0F, 0.0F, 0.0F, 5, 5, 0, 0.0F);
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.stick = new ModelRenderer(this, 58, 0);
        this.stick.setRotationPoint(5.5F, 2.0F, -10.5F);
        this.stick.addBox(0.0F, 0.0F, 0.0F, 1, 22, 1, 0.0F);
        this.setRotateAngle(stick, 0.08726646259971647F, -0.0F, 0.0F);
        this.backpack1 = new ModelRenderer(this, 42, 32);
        this.backpack1.setRotationPoint(-3.5F, 0.1F, -1.2F);
        this.backpack1.addBox(0.0F, 0.0F, 0.0F, 7, 3, 3, 0.0F);
        this.setRotateAngle(backpack1, 0.2617993877991494F, 0.0F, 0.0F);
        this.backpack2 = new ModelRenderer(this, 0, 43);
        this.backpack2.setRotationPoint(-3.5F, 3.0F, -0.3F);
        this.backpack2.addBox(0.0F, 0.0F, 0.0F, 7, 7, 2, 0.0F);
        this.setRotateAngle(backpack2, 0.2617993877991494F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
        this.leftleg.render(f5);
        this.hat1.render(f5);
        this.rightarm.render(f5);
        this.leftarm.render(f5);
        this.flower.render(f5);
        this.head.render(f5);
        this.beard.render(f5);
        this.rightleg.render(f5);
        this.stick.render(f5);
        this.backpack1.render(f5);
        this.backpack2.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
