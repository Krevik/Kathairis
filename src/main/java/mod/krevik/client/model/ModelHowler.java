package mod.krevik.client.model;

import mod.krevik.entity.EntityHowler;
import mod.krevik.util.FunctionHelper;
import mod.krevik.KCore;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Howler - HKhugo
 * Created using Tabula 7.0.0
 */
public class ModelHowler extends ModelBase {
  public ModelRenderer FrontRightLeg1;
  public ModelRenderer Body1;
  public ModelRenderer Head1;
  public ModelRenderer BehindRightLeg1;
  public ModelRenderer BehindLeftLeg1;
  public ModelRenderer FrontLeftLeg1;
  public ModelRenderer FrontRightLeg2;
  public ModelRenderer FrontRightLeg3;
  public ModelRenderer FrontRightFeet;
  public ModelRenderer Body2;
  public ModelRenderer Body3;
  public ModelRenderer Tail;
  public ModelRenderer RightEar1;
  public ModelRenderer LeftEar1;
  public ModelRenderer Head2;
  public ModelRenderer Head3;
  public ModelRenderer RightEar2;
  public ModelRenderer LeftEar2;
  public ModelRenderer BehindRightLeg2;
  public ModelRenderer BehindRightLeg3;
  public ModelRenderer BehindRightFeet;
  public ModelRenderer BehindLeftLeg2;
  public ModelRenderer BehindLeftLeg3;
  public ModelRenderer BehindLeftFeet;
  public ModelRenderer FrontLeftLeg2;
  public ModelRenderer FrontLeftLeg3;
  public ModelRenderer FrontLeftFeet;

  public ModelHowler() {
    this.textureWidth = 80;
    this.textureHeight = 45;
    this.BehindRightLeg2 = new ModelRenderer(this, 32, 3);
    this.BehindRightLeg2.setRotationPoint(1.5F, 1.25F, 0.0F);
    this.BehindRightLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
    this.setRotateAngle(BehindRightLeg2, 0.08726646259971647F, 0.0F, 0.0F);
    this.Head3 = new ModelRenderer(this, 0, 16);
    this.Head3.setRotationPoint(0.0F, 2.25F, -3.5F);
    this.Head3.addBox(-1.5F, 0.0F, -4.0F, 3, 1, 4, 0.0F);
    this.Body1 = new ModelRenderer(this, 49, 0);
    this.Body1.setRotationPoint(0.0F, 13.0F, -6.0F);
    this.Body1.addBox(-4.0F, 0.0F, -3.5F, 8, 6, 7, 0.0F);
    this.setRotateAngle(Body1, 1.6845917940249266F, 0.0F, 0.0F);
    this.BehindRightLeg1 = new ModelRenderer(this, 3, 24);
    this.BehindRightLeg1.setRotationPoint(2.0F, 14.1F, 4.0F);
    this.BehindRightLeg1.addBox(0.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
    this.setRotateAngle(BehindRightLeg1, -0.4363323129985824F, 0.0F, 0.0F);
    this.FrontLeftLeg1 = new ModelRenderer(this, 32, 10);
    this.FrontLeftLeg1.setRotationPoint(-3.5F, 12.0F, -3.0F);
    this.FrontLeftLeg1.addBox(-3.0F, -2.0F, -2.0F, 3, 4, 4, 0.0F);
    this.setRotateAngle(FrontLeftLeg1, -0.4363323129985824F, 0.0F, 0.0F);
    this.Head2 = new ModelRenderer(this, 0, 10);
    this.Head2.setRotationPoint(0.0F, 2.25F, -4.0F);
    this.Head2.addBox(-1.5F, -2.0F, -4.0F, 3, 2, 4, 0.0F);
    this.LeftEar2 = new ModelRenderer(this, 23, 26);
    this.LeftEar2.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.LeftEar2.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
    this.FrontLeftLeg3 = new ModelRenderer(this, 24, 18);
    this.FrontLeftLeg3.setRotationPoint(0.01F, 5.25F, 0.5F);
    this.FrontLeftLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.setRotateAngle(FrontLeftLeg3, -0.7853981633974483F, 0.0F, 0.0F);
    this.RightEar2 = new ModelRenderer(this, 16, 26);
    this.RightEar2.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.RightEar2.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
    this.FrontLeftFeet = new ModelRenderer(this, 29, 31);
    this.FrontLeftFeet.setRotationPoint(0.0F, 5.25F, 0.5F);
    this.FrontLeftFeet.addBox(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(FrontLeftFeet, -0.9599310885968813F, 0.0F, 0.0F);
    this.Body3 = new ModelRenderer(this, 52, 24);
    this.Body3.setRotationPoint(-0.01F, 2.25F, 0.0F);
    this.Body3.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6, 0.0F);
    this.setRotateAngle(Body3, -0.17453292519943295F, 0.0F, 0.0F);
    this.BehindRightFeet = new ModelRenderer(this, 28, 37);
    this.BehindRightFeet.setRotationPoint(0.0F, 4.75F, 0.25F);
    this.BehindRightFeet.addBox(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(BehindRightFeet, -1.8325957145940461F, 0.0F, 0.0F);
    this.BehindLeftLeg2 = new ModelRenderer(this, 23, 3);
    this.BehindLeftLeg2.setRotationPoint(-1.5F, 1.25F, 0.0F);
    this.BehindLeftLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
    this.setRotateAngle(BehindLeftLeg2, 0.08726646259971647F, 0.0F, 0.0F);
    this.BehindLeftLeg3 = new ModelRenderer(this, 0, 36);
    this.BehindLeftLeg3.setRotationPoint(0.01F, 3.5F, 0.0F);
    this.BehindLeftLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    this.setRotateAngle(BehindLeftLeg3, 0.6108652381980153F, 0.0F, 0.0F);
    this.FrontRightLeg1 = new ModelRenderer(this, 17, 10);
    this.FrontRightLeg1.setRotationPoint(3.5F, 12.0F, -3.0F);
    this.FrontRightLeg1.addBox(0.0F, -2.0F, -2.0F, 3, 4, 4, 0.0F);
    this.setRotateAngle(FrontRightLeg1, -0.4363323129985824F, 0.0F, 0.0F);
    this.Tail = new ModelRenderer(this, 42, 27);
    this.Tail.setRotationPoint(0.0F, 5.5F, 0.5F);
    this.Tail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
    this.setRotateAngle(Tail, -0.7853981633974483F, 0.0F, 0.0F);
    this.RightEar1 = new ModelRenderer(this, 16, 29);
    this.RightEar1.setRotationPoint(2.25F, -1.5F, -1.5F);
    this.RightEar1.addBox(-0.5F, -3.0F, -1.0F, 1, 3, 2, 0.0F);
    this.setRotateAngle(RightEar1, -0.5009094953223726F, 0.40980330836826856F, 0.0F);
    this.BehindRightLeg3 = new ModelRenderer(this, 12, 35);
    this.BehindRightLeg3.setRotationPoint(-0.01F, 3.5F, 0.0F);
    this.BehindRightLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    this.setRotateAngle(BehindRightLeg3, 0.6108652381980153F, 0.0F, 0.0F);
    this.FrontRightLeg3 = new ModelRenderer(this, 40, 18);
    this.FrontRightLeg3.setRotationPoint(-0.01F, 5.25F, 0.5F);
    this.FrontRightLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.setRotateAngle(FrontRightLeg3, -0.7853981633974483F, 0.0F, 0.0F);
    this.FrontLeftLeg2 = new ModelRenderer(this, 16, 18);
    this.FrontLeftLeg2.setRotationPoint(-1.5F, 1.5F, 0.0F);
    this.FrontLeftLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.setRotateAngle(FrontLeftLeg2, 0.6108652381980153F, 0.0F, 0.0F);
    this.Head1 = new ModelRenderer(this, 0, 0);
    this.Head1.setRotationPoint(0.0F, 13.0F, -6.8F);
    this.Head1.addBox(-3.0F, -2.5F, -4.0F, 6, 5, 5, 0.0F);
    this.FrontRightFeet = new ModelRenderer(this, 29, 26);
    this.FrontRightFeet.setRotationPoint(0.0F, 5.25F, 0.5F);
    this.FrontRightFeet.addBox(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(FrontRightFeet, -0.9599310885968813F, 0.0F, -0.0F);
    this.Body2 = new ModelRenderer(this, 52, 14);
    this.Body2.setRotationPoint(0.0F, 5.0F, -0.5F);
    this.Body2.addBox(-3.0F, 0.0F, -3.0F, 6, 3, 6, 0.0F);
    this.setRotateAngle(Body2, -0.17453292519943295F, 0.0F, 0.0F);
    this.FrontRightLeg2 = new ModelRenderer(this, 32, 18);
    this.FrontRightLeg2.setRotationPoint(1.5F, 1.5F, 0.0F);
    this.FrontRightLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.setRotateAngle(FrontRightLeg2, 0.6108652381980153F, 0.0F, 0.0F);
    this.BehindLeftLeg1 = new ModelRenderer(this, 0, 30);
    this.BehindLeftLeg1.setRotationPoint(-2.0F, 14.1F, 4.0F);
    this.BehindLeftLeg1.addBox(-3.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
    this.setRotateAngle(BehindLeftLeg1, -0.4363323129985824F, 0.0F, 0.0F);
    this.LeftEar1 = new ModelRenderer(this, 23, 29);
    this.LeftEar1.setRotationPoint(-2.25F, -1.5F, -1.5F);
    this.LeftEar1.addBox(-0.5F, -3.0F, -1.0F, 1, 3, 2, 0.0F);
    this.setRotateAngle(LeftEar1, -0.5009094953223726F, -0.40980330836826856F, 0.0F);
    this.BehindLeftFeet = new ModelRenderer(this, 20, 37);
    this.BehindLeftFeet.setRotationPoint(0.0F, 4.75F, 0.25F);
    this.BehindLeftFeet.addBox(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(BehindLeftFeet, -1.8325957145940461F, 0.0F, 0.0F);
    this.BehindRightLeg1.addChild(this.BehindRightLeg2);
    this.Head1.addChild(this.Head3);
    this.Head1.addChild(this.Head2);
    this.LeftEar1.addChild(this.LeftEar2);
    this.FrontLeftLeg2.addChild(this.FrontLeftLeg3);
    this.RightEar1.addChild(this.RightEar2);
    this.FrontLeftLeg3.addChild(this.FrontLeftFeet);
    this.Body2.addChild(this.Body3);
    this.BehindRightLeg3.addChild(this.BehindRightFeet);
    this.BehindLeftLeg1.addChild(this.BehindLeftLeg2);
    this.BehindLeftLeg2.addChild(this.BehindLeftLeg3);
    this.Body3.addChild(this.Tail);
    this.Head1.addChild(this.RightEar1);
    this.BehindRightLeg2.addChild(this.BehindRightLeg3);
    this.FrontRightLeg2.addChild(this.FrontRightLeg3);
    this.FrontLeftLeg1.addChild(this.FrontLeftLeg2);
    this.FrontRightLeg3.addChild(this.FrontRightFeet);
    this.Body1.addChild(this.Body2);
    this.FrontRightLeg1.addChild(this.FrontRightLeg2);
    this.Head1.addChild(this.LeftEar1);
    this.BehindLeftLeg3.addChild(this.BehindLeftFeet);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.Body1.render(f5);
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GlStateManager.color(1.0F, 1.0F, 1.0F, 0.998F);
    this.BehindRightLeg1.render(f5);
    GlStateManager.disableBlend();
    this.FrontLeftLeg1.render(f5);
    this.FrontRightLeg1.render(f5);
    this.Head1.render(f5);
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GlStateManager.color(1.0F, 1.0F, 1.0F, 0.998F);
    this.BehindLeftLeg1.render(f5);
    GlStateManager.disableBlend();
  }

  /**
   * This is a helper function from Tabula to set the rotation of model parts
   */
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }

  FunctionHelper helper = KCore.instance.functionHelper;
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(limbSwing,limbSwingAmount,age,f3,f4,f5,entity);
      if(entity instanceof EntityHowler){
        EntityHowler model = (EntityHowler) entity;
        //FrontRightLeg2.rotateAngleX=helper.degToRad(35)-MathHelper.sin(limbSwing*0.16f)*limbSwingAmount;
        //FrontRightLeg3.rotateAngleX=helper.degToRad(-45)+MathHelper.sin(limbSwing*0.16f)*limbSwingAmount;
        //FrontLeftLeg2.rotateAngleX=helper.degToRad(35)+MathHelper.sin(limbSwing*0.16f)*limbSwingAmount;
        //FrontLeftLeg3.rotateAngleX=helper.degToRad(-45)-MathHelper.sin(limbSwing*0.16f)*

       /* FrontRightLeg2.rotateAngleX=helper.degToRad(35)-MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*2f*limbSwingAmount,0,100);
        FrontRightLeg3.rotateAngleX=helper.degToRad(-45)+MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*0.62f*limbSwingAmount,0,100);
        FrontLeftLeg2.rotateAngleX=helper.degToRad(35)-MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*2f*limbSwingAmount,0,100);
        FrontLeftLeg3.rotateAngleX=helper.degToRad(-45)+MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*0.62f*limbSwingAmount,0,100);

        BehindRightLeg2.rotateAngleX=helper.degToRad(5)-MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*2f*limbSwingAmount,-100,0);
        BehindRightLeg3.rotateAngleX=helper.degToRad(35)+MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*0.62f*limbSwingAmount,-100,0);
        BehindLeftLeg2.rotateAngleX=helper.degToRad(5)-MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*2f*limbSwingAmount,-100,0);
        BehindLeftLeg3.rotateAngleX=helper.degToRad(35)+MathHelper.clamp(MathHelper.sin(limbSwing*0.8f)*0.62f*limbSwingAmount,-100,0);
        */

        FrontRightLeg1.rotateAngleX=helper.degToRad(-25)-MathHelper.sin(limbSwing*0.4f)*1f*limbSwingAmount;
        //FrontRightLeg2.rotateAngleX=helper.degToRad(35)-MathHelper.abs(MathHelper.sin(limbSwing*0.3f)*1f)*limbSwingAmount;
        //FrontRightLeg3.rotateAngleX=helper.degToRad(-45)+MathHelper.abs(MathHelper.sin(limbSwing*0.3f)*2f)*limbSwingAmount;
        FrontLeftLeg1.rotateAngleX=helper.degToRad(-25)+MathHelper.sin(limbSwing*0.4f)*1f*limbSwingAmount;
       // FrontLeftLeg2.rotateAngleX=helper.degToRad(35)-MathHelper.abs(MathHelper.cos(limbSwing*0.3f)*1f)*limbSwingAmount;
        //FrontLeftLeg3.rotateAngleX=helper.degToRad(-45)+MathHelper.abs(MathHelper.cos(limbSwing*0.3f)*1f)*limbSwingAmount;

        FrontRightLeg3.offsetZ=-0.02f;
        FrontLeftLeg3.offsetZ=-0.02f;

        BehindRightLeg1.rotateAngleX=helper.degToRad(-25)+MathHelper.sin(limbSwing*0.4f)*1f*limbSwingAmount;
       // BehindRightLeg2.rotateAngleX=helper.degToRad(5)+MathHelper.abs(MathHelper.cos(limbSwing*0.3f)*1f)*limbSwingAmount;
        //BehindRightLeg3.rotateAngleX=helper.degToRad(35)-MathHelper.abs(MathHelper.cos(limbSwing*0.3f)*1f)*limbSwingAmount;
        BehindLeftLeg1.rotateAngleX=helper.degToRad(-25)-MathHelper.sin(limbSwing*0.4f)*1f*limbSwingAmount;
        //BehindLeftLeg2.rotateAngleX=helper.degToRad(5)+MathHelper.abs(MathHelper.sin(limbSwing*0.3f)*1f)*limbSwingAmount;
        //BehindLeftLeg3.rotateAngleX=helper.degToRad(35)-MathHelper.abs(MathHelper.sin(limbSwing*0.3f)*1f)*limbSwingAmount;


          if (model.getShouldAnimTail()) {
            Tail.rotateAngleY = (MathHelper.sin((model.getAnimTimer() / 10) * 0.6F));
          } else {
            Tail.rotateAngleY = 0;
          }
          if(model.getAttackTarget()==null){
            LeftEar1.rotateAngleX=helper.degToRad(-28.7f)+MathHelper.abs(MathHelper.sin(age*model.shouldAnimEars*0.1f)*0.2f);
            RightEar1.rotateAngleX=helper.degToRad(-28.7f)+MathHelper.abs(MathHelper.sin(age*model.shouldAnimEars*0.1f)*0.2f);
          }

      }
  }
}
