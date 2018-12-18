package com.krevik.Models;
import com.krevik.Main.KCore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelPoisonousScorpion - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelPoisonousScorpion extends ModelBase {
    public ModelRenderer Tail9;
    public ModelRenderer Tail4;
    public ModelRenderer Tail15;
    public ModelRenderer Tail1;
    public ModelRenderer Tail13;
    public ModelRenderer Tail12;
    public ModelRenderer Tail2;
    public ModelRenderer Szczypce11;
    public ModelRenderer Body;
    public ModelRenderer Leg61;
    public ModelRenderer Szczypce21;
    public ModelRenderer Body_1;
    public ModelRenderer Leg61_1;
    public ModelRenderer Leg61_2;
    public ModelRenderer Leg61_3;
    public ModelRenderer Leg2;
    public ModelRenderer Leg2_1;
    public ModelRenderer Leg2_2;
    public ModelRenderer Leg2_3;
    public ModelRenderer Tail4_1;
    public ModelRenderer Szczypce11Child;
    public ModelRenderer Szczypce11ChildChild;
    public ModelRenderer Szczypce11ChildChild_1;
    public ModelRenderer Leg61Child;
    public ModelRenderer Szczypce21Child;
    public ModelRenderer Szczypce21ChildChild;
    public ModelRenderer Szczypce21ChildChild_1;
    public ModelRenderer Leg61Child_1;
    public ModelRenderer Leg61Child_2;
    public ModelRenderer Leg61Child_3;
    public ModelRenderer Leg31Child;
    public ModelRenderer Leg31Child_1;
    public ModelRenderer Leg31Child_2;
    public ModelRenderer Leg31Child_3;

    public ModelPoisonousScorpion() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Tail12 = new ModelRenderer(this, 75, 0);
        this.Tail12.setRotationPoint(-1.5F, -9.7F, 6.0F);
        this.Tail12.addBox(0.0F, 0.0F, 0.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(Tail12, 0.045553093477052F, -0.0F, 0.0F);
        this.Tail15 = new ModelRenderer(this, 35, 0);
        this.Tail15.setRotationPoint(-0.5F, -5.3F, 3.5F);
        this.Tail15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
        this.setRotateAngle(Tail15, -1.4114477660878142F, 0.0F, 0.0F);
        this.Leg31Child_3 = new ModelRenderer(this, 0, 50);
        this.Leg31Child_3.setRotationPoint(-0.1F, 3.2F, 0.0F);
        this.Leg31Child_3.addBox(-8.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg31Child_3, 0.0F, 0.0F, 0.36425021489121656F);
        this.Leg61Child_1 = new ModelRenderer(this, 0, 50);
        this.Leg61Child_1.setRotationPoint(6.0F, 0.8F, 0.0F);
        this.Leg61Child_1.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg61Child_1, 0.0F, 0.0F, -0.36425021489121656F);
        this.Leg31Child_2 = new ModelRenderer(this, 0, 50);
        this.Leg31Child_2.setRotationPoint(-0.1F, 3.2F, 0.0F);
        this.Leg31Child_2.addBox(-8.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg31Child_2, 0.0F, 0.0F, 0.36425021489121656F);
        this.Leg31Child_1 = new ModelRenderer(this, 0, 50);
        this.Leg31Child_1.setRotationPoint(-0.1F, 3.2F, 0.0F);
        this.Leg31Child_1.addBox(-8.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg31Child_1, 0.0F, 0.0F, 0.36425021489121656F);
        this.Leg61 = new ModelRenderer(this, 0, 70);
        this.Leg61.setRotationPoint(4.0F, 18.0F, -7.2F);
        this.Leg61.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg61, 0.0F, 0.27314402793711257F, -0.091106186954104F);
        this.Szczypce21 = new ModelRenderer(this, 6, 106);
        this.Szczypce21.setRotationPoint(3.0F, 19.0F, -9.5F);
        this.Szczypce21.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8, 0.0F);
        this.setRotateAngle(Szczypce21, -3.141592653589793F, -0.8196066167365371F, 0.0F);
        this.Szczypce11 = new ModelRenderer(this, 6, 106);
        this.Szczypce11.setRotationPoint(-4.0F, 19.0F, -8.0F);
        this.Szczypce11.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8, 0.0F);
        this.setRotateAngle(Szczypce11, 3.141592653589793F, 0.8196066167365371F, 0.0F);
        this.Leg2_1 = new ModelRenderer(this, 0, 70);
        this.Leg2_1.setRotationPoint(-3.5F, 17.2F, -3.6F);
        this.Leg2_1.addBox(-8.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg2_1, 0.0F, -0.091106186954104F, 0.091106186954104F);
        this.Szczypce21Child = new ModelRenderer(this, 0, 80);
        this.Szczypce21Child.setRotationPoint(2.4F, -1.0F, 8.8F);
        this.Szczypce21Child.addBox(0.0F, 0.0F, 0.0F, 6, 4, 4, 0.0F);
        this.setRotateAngle(Szczypce21Child, -3.141592653589793F, -0.5462880558742251F, -3.141592653589793F);
        this.Szczypce21ChildChild_1 = new ModelRenderer(this, 0, 0);
        this.Szczypce21ChildChild_1.setRotationPoint(5.5F, 1.0F, 4.0F);
        this.Szczypce21ChildChild_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(Szczypce21ChildChild_1, 0.0F, -1.4114477660878142F, 0.0F);
        this.Tail4 = new ModelRenderer(this, 40, 37);
        this.Tail4.setRotationPoint(-2.0F, 6.0F, 14.7F);
        this.Tail4.addBox(0.0F, 0.0F, 0.0F, 4, 4, 7, 0.0F);
        this.setRotateAngle(Tail4, 1.593485607070823F, 0.0F, 0.0F);
        this.Szczypce11Child = new ModelRenderer(this, 0, 80);
        this.Szczypce11Child.setRotationPoint(4.7F, -1.1F, 11.8F);
        this.Szczypce11Child.addBox(0.0F, 0.0F, 0.0F, 6, 4, 4, 0.0F);
        this.setRotateAngle(Szczypce11Child, -3.141592653589793F, 0.5462880558742251F, 3.141592653589793F);
        this.Szczypce11ChildChild_1 = new ModelRenderer(this, 0, 0);
        this.Szczypce11ChildChild_1.setRotationPoint(0.0F, 2.0F, 6.0F);
        this.Szczypce11ChildChild_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.setRotateAngle(Szczypce11ChildChild_1, 0.0F, 1.1383037381507017F, 0.0F);
        this.Szczypce21ChildChild = new ModelRenderer(this, 0, 7);
        this.Szczypce21ChildChild.setRotationPoint(6.0F, 1.0F, 1.9F);
        this.Szczypce21ChildChild.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(Szczypce21ChildChild, 0.0F, -0.7285004297824331F, 0.0F);
        this.Tail2 = new ModelRenderer(this, 43, 52);
        this.Tail2.setRotationPoint(-1.5F, 11.8F, 13.7F);
        this.Tail2.addBox(0.0F, 0.0F, 0.0F, 3, 3, 7, 0.0F);
        this.setRotateAngle(Tail2, 1.3658946726107624F, 0.0F, 0.0F);
        this.Leg61Child_2 = new ModelRenderer(this, 0, 50);
        this.Leg61Child_2.setRotationPoint(6.0F, 0.8F, 0.0F);
        this.Leg61Child_2.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg61Child_2, 0.0F, 0.0F, -0.36425021489121656F);
        this.Tail13 = new ModelRenderer(this, 0, 34);
        this.Tail13.setRotationPoint(-2.5F, -7.1F, 8.6F);
        this.Tail13.addBox(0.0F, 0.0F, 0.0F, 5, 4, 6, 0.0F);
        this.setRotateAngle(Tail13, -2.367539130330308F, -0.0F, 0.0F);
        this.Leg61Child_3 = new ModelRenderer(this, 0, 50);
        this.Leg61Child_3.setRotationPoint(6.0F, 0.8F, 0.0F);
        this.Leg61Child_3.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg61Child_3, 0.0F, 0.0F, -0.36425021489121656F);
        this.Leg2_3 = new ModelRenderer(this, 0, 70);
        this.Leg2_3.setRotationPoint(-3.5F, 17.2F, 6.0F);
        this.Leg2_3.addBox(-8.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg2_3, 0.0F, 0.36425021489121656F, 0.091106186954104F);
        this.Leg61_2 = new ModelRenderer(this, 0, 70);
        this.Leg61_2.setRotationPoint(3.5F, 18.0F, 2.0F);
        this.Leg61_2.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg61_2, 0.0F, -0.136659280431156F, -0.091106186954104F);
        this.Tail1 = new ModelRenderer(this, 50, 0);
        this.Tail1.setRotationPoint(-2.0F, 14.8F, 9.0F);
        this.Tail1.addBox(0.0F, 0.0F, 0.0F, 4, 4, 7, 0.0F);
        this.setRotateAngle(Tail1, 0.7740535232594852F, -0.0F, 0.0F);
        this.Body_1 = new ModelRenderer(this, 65, 29);
        this.Body_1.setRotationPoint(-4.5F, 14.0F, -11.0F);
        this.Body_1.addBox(0.0F, 0.0F, 0.0F, 9, 6, 13, 0.0F);
        this.Leg2 = new ModelRenderer(this, 0, 70);
        this.Leg2.setRotationPoint(-3.5F, 17.2F, -7.2F);
        this.Leg2.addBox(-8.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg2, 0.0F, -0.27314402793711257F, 0.091106186954104F);
        this.Leg61_1 = new ModelRenderer(this, 0, 70);
        this.Leg61_1.setRotationPoint(4.0F, 18.0F, -2.8F);
        this.Leg61_1.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg61_1, 0.0F, 0.091106186954104F, -0.091106186954104F);
        this.Tail9 = new ModelRenderer(this, 50, 13);
        this.Tail9.setRotationPoint(-2.0F, -3.8F, 14.6F);
        this.Tail9.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, 0.0F);
        this.setRotateAngle(Tail9, 2.5497515042385164F, -0.0F, 0.0F);
        this.Leg2_2 = new ModelRenderer(this, 0, 70);
        this.Leg2_2.setRotationPoint(-3.5F, 17.2F, 2.0F);
        this.Leg2_2.addBox(-8.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg2_2, 0.0F, 0.136659280431156F, 0.091106186954104F);
        this.Leg61Child = new ModelRenderer(this, 0, 50);
        this.Leg61Child.setRotationPoint(6.0F, 0.8F, 0.0F);
        this.Leg61Child.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg61Child, 0.0F, 0.0F, -0.36425021489121656F);
        this.Leg31Child = new ModelRenderer(this, 0, 50);
        this.Leg31Child.setRotationPoint(-0.1F, 3.2F, 0.0F);
        this.Leg31Child.addBox(-8.0F, 0.0F, 0.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(Leg31Child, 0.0F, 0.0F, 0.36425021489121656F);
        this.Leg61_3 = new ModelRenderer(this, 0, 70);
        this.Leg61_3.setRotationPoint(3.5F, 18.0F, 6.0F);
        this.Leg61_3.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(Leg61_3, 0.0F, -0.36425021489121656F, -0.091106186954104F);
        this.Szczypce11ChildChild = new ModelRenderer(this, 0, 7);
        this.Szczypce11ChildChild.setRotationPoint(-2.8F, 1.0F, 3.5F);
        this.Szczypce11ChildChild.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
        this.setRotateAngle(Szczypce11ChildChild, 0.0F, 0.4363323129985824F, 0.0F);
        this.Tail4_1 = new ModelRenderer(this, 50, 25);
        this.Tail4_1.setRotationPoint(-1.5F, 0.0F, 15.1F);
        this.Tail4_1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(Tail4_1, 1.8212510744560826F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 71, 51);
        this.Body.setRotationPoint(-4.0F, 14.5F, 0.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 8, 5, 12, 0.0F);
        this.Leg2_3.addChild(this.Leg31Child_3);
        this.Leg61_1.addChild(this.Leg61Child_1);
        this.Leg2_2.addChild(this.Leg31Child_2);
        this.Leg2_1.addChild(this.Leg31Child_1);
        this.Szczypce21.addChild(this.Szczypce21Child);
        this.Szczypce21Child.addChild(this.Szczypce21ChildChild_1);
        this.Szczypce11.addChild(this.Szczypce11Child);
        this.Szczypce11Child.addChild(this.Szczypce11ChildChild_1);
        this.Szczypce21Child.addChild(this.Szczypce21ChildChild);
        this.Leg61_2.addChild(this.Leg61Child_2);
        this.Leg61_3.addChild(this.Leg61Child_3);
        this.Leg61.addChild(this.Leg61Child);
        this.Leg2.addChild(this.Leg31Child);
        this.Szczypce11Child.addChild(this.Szczypce11ChildChild);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Tail12.render(f5);
        this.Tail15.render(f5);
        this.Leg61.render(f5);
        this.Szczypce21.render(f5);
        this.Szczypce11.render(f5);
        this.Leg2_1.render(f5);
        this.Tail4.render(f5);
        this.Tail2.render(f5);
        this.Tail13.render(f5);
        this.Leg2_3.render(f5);
        this.Leg61_2.render(f5);
        this.Tail1.render(f5);
        this.Body_1.render(f5);
        this.Leg2.render(f5);
        this.Leg61_1.render(f5);
        this.Tail9.render(f5);
        this.Leg2_2.render(f5);
        this.Leg61_3.render(f5);
        this.Tail4_1.render(f5);
        this.Body.render(f5);
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
      Leg61.rotateAngleZ= KCore.instance.functionHelper.degToRad(-5.22f) +MathHelper.cos(f+0.4f)+0.01f*0.01f;
      Leg2_1.rotateAngleZ= KCore.instance.functionHelper.degToRad(5.22f) - MathHelper.cos(f+0.4f)+0.01f*0.01f;
      Leg61_2.rotateAngleZ= KCore.instance.functionHelper.degToRad(-5.22f) + MathHelper.cos(f+0.4f)+0.01f*0.01f;
      Leg2_3.rotateAngleZ= KCore.instance.functionHelper.degToRad(5.22f) - MathHelper.cos(f+0.4f)+0.01f*0.01f;

        Leg2.rotateAngleZ= KCore.instance.functionHelper.degToRad(5.22f) - MathHelper.cos(f*2+0.4f)+0.01f*0.01f;
        Leg2_2.rotateAngleZ= KCore.instance.functionHelper.degToRad(5.22f) - MathHelper.cos(f*2+0.4f)+0.01f*0.01f;
        Leg61_1.rotateAngleZ= KCore.instance.functionHelper.degToRad(-5.22f) + MathHelper.cos(f*2+0.4f)+0.01f*0.01f;
        Leg61_3.rotateAngleZ= KCore.instance.functionHelper.degToRad(-5.22f) + MathHelper.cos(f*2+0.4f)+0.01f*0.01f;
    }
}
