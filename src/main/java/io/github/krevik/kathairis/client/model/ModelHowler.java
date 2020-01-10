package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.entity.EntityHowler;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;

/**
 * Howler - HKhugo
 * Created using Tabula 7.0.0
 */
public class ModelHowler<T extends LivingEntity> extends AgeableModel<EntityHowler> {
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
    this.FrontLeftLeg3 = new ModelRenderer(this, 24, 18);
    this.FrontLeftLeg3.setRotationPoint(0.01F, 5.25F, 0.0F);
    this.FrontLeftLeg3.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.Body2 = new ModelRenderer(this, 52, 14);
    this.Body2.setRotationPoint(0.0F, 5.0F, -0.5F);
    this.Body2.func_228301_a_(-3.0F, 0.0F, -3.0F, 6, 3, 6, 0.0F);
    this.setRotateAngle(Body2, -0.17453292519943295F, 0.0F, 0.0F);
    this.RightEar2 = new ModelRenderer(this, 16, 26);
    this.RightEar2.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.RightEar2.func_228301_a_(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
    this.FrontLeftFeet = new ModelRenderer(this, 29, 31);
    this.FrontLeftFeet.setRotationPoint(0.0F, 5.25F, 0.5F);
    this.FrontLeftFeet.func_228301_a_(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(FrontLeftFeet, -1.5707963267948966F, 0.0F, 0.0F);
    this.Head1 = new ModelRenderer(this, 0, 0);
    this.Head1.setRotationPoint(0.0F, 13.0F, -6.8F);
    this.Head1.func_228301_a_(-3.0F, -2.5F, -4.0F, 6, 5, 5, 0.0F);
    this.Body1 = new ModelRenderer(this, 49, 0);
    this.Body1.setRotationPoint(0.0F, 13.0F, -6.0F);
    this.Body1.func_228301_a_(-4.0F, 0.0F, -3.5F, 8, 6, 7, 0.0F);
    this.setRotateAngle(Body1, 1.6845917940249266F, 0.0F, 0.0F);
    this.BehindLeftLeg3 = new ModelRenderer(this, 0, 36);
    this.BehindLeftLeg3.setRotationPoint(0.01F, 3.5F, 0.0F);
    this.BehindLeftLeg3.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    this.FrontLeftLeg1 = new ModelRenderer(this, 32, 10);
    this.FrontLeftLeg1.setRotationPoint(-3.5F, 12.0F, -3.0F);
    this.FrontLeftLeg1.func_228301_a_(-3.0F, -2.0F, -2.0F, 3, 4, 4, 0.0F);
    this.Head3 = new ModelRenderer(this, 0, 16);
    this.Head3.setRotationPoint(0.0F, 2.25F, -3.5F);
    this.Head3.func_228301_a_(-1.5F, 0.0F, -4.0F, 3, 1, 4, 0.0F);
    this.Head2 = new ModelRenderer(this, 0, 10);
    this.Head2.setRotationPoint(0.0F, 2.25F, -4.0F);
    this.Head2.func_228301_a_(-1.5F, -2.0F, -4.0F, 3, 2, 4, 0.0F);
    this.Body3 = new ModelRenderer(this, 52, 24);
    this.Body3.setRotationPoint(-0.01F, 2.25F, 0.0F);
    this.Body3.func_228301_a_(-3.0F, 0.0F, -3.0F, 6, 6, 6, 0.0F);
    this.setRotateAngle(Body3, -0.17453292519943295F, 0.0F, 0.0F);
    this.BehindRightLeg1 = new ModelRenderer(this, 3, 24);
    this.BehindRightLeg1.setRotationPoint(2.0F, 14.1F, 4.0F);
    this.BehindRightLeg1.func_228301_a_(0.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
    this.LeftEar1 = new ModelRenderer(this, 23, 29);
    this.LeftEar1.setRotationPoint(-2.25F, -1.5F, -1.5F);
    this.LeftEar1.func_228301_a_(-0.5F, -3.0F, -1.0F, 1, 3, 2, 0.0F);
    this.setRotateAngle(LeftEar1, -0.5009094953223726F, -0.40980330836826856F, 0.0F);
    this.FrontRightFeet = new ModelRenderer(this, 29, 26);
    this.FrontRightFeet.setRotationPoint(0.0F, 5.25F, 0.5F);
    this.FrontRightFeet.func_228301_a_(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(FrontRightFeet, -1.5707963267948966F, 0.0F, -0.0F);
    this.FrontRightLeg1 = new ModelRenderer(this, 17, 10);
    this.FrontRightLeg1.setRotationPoint(3.5F, 12.0F, -3.0F);
    this.FrontRightLeg1.func_228301_a_(0.0F, -2.0F, -2.0F, 3, 4, 4, 0.0F);
    this.BehindRightFeet = new ModelRenderer(this, 28, 37);
    this.BehindRightFeet.setRotationPoint(0.0F, 4.75F, 0.25F);
    this.BehindRightFeet.func_228301_a_(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(BehindRightFeet, -1.5707963267948966F, 0.0F, 0.0F);
    this.BehindLeftLeg2 = new ModelRenderer(this, 23, 3);
    this.BehindLeftLeg2.setRotationPoint(-1.5F, 0.6F, 0.0F);
    this.BehindLeftLeg2.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
    this.FrontRightLeg3 = new ModelRenderer(this, 40, 18);
    this.FrontRightLeg3.setRotationPoint(-0.01F, 5.25F, 0.0F);
    this.FrontRightLeg3.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.BehindLeftLeg1 = new ModelRenderer(this, 0, 30);
    this.BehindLeftLeg1.setRotationPoint(-2.0F, 14.1F, 4.0F);
    this.BehindLeftLeg1.func_228301_a_(-3.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
    this.RightEar1 = new ModelRenderer(this, 16, 29);
    this.RightEar1.setRotationPoint(2.25F, -1.5F, -1.5F);
    this.RightEar1.func_228301_a_(-0.5F, -3.0F, -1.0F, 1, 3, 2, 0.0F);
    this.setRotateAngle(RightEar1, -0.5009094953223726F, 0.40980330836826856F, 0.0F);
    this.LeftEar2 = new ModelRenderer(this, 23, 26);
    this.LeftEar2.setRotationPoint(0.0F, -3.0F, 0.0F);
    this.LeftEar2.func_228301_a_(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
    this.BehindRightLeg2 = new ModelRenderer(this, 32, 3);
    this.BehindRightLeg2.setRotationPoint(1.5F, 0.6F, 0.0F);
    this.BehindRightLeg2.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
    this.FrontRightLeg2 = new ModelRenderer(this, 32, 18);
    this.FrontRightLeg2.setRotationPoint(1.5F, 0.5F, 0.0F);
    this.FrontRightLeg2.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.BehindLeftFeet = new ModelRenderer(this, 20, 37);
    this.BehindLeftFeet.setRotationPoint(0.0F, 4.75F, 0.25F);
    this.BehindLeftFeet.func_228301_a_(-1.5F, -1.0F, 0.0F, 3, 4, 1, 0.0F);
    this.setRotateAngle(BehindLeftFeet, -1.5707963267948966F, 0.0F, 0.0F);
    this.FrontLeftLeg2 = new ModelRenderer(this, 16, 18);
    this.FrontLeftLeg2.setRotationPoint(-1.5F, 0.5F, 0.0F);
    this.FrontLeftLeg2.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
    this.BehindRightLeg3 = new ModelRenderer(this, 12, 35);
    this.BehindRightLeg3.setRotationPoint(-0.01F, 3.5F, 0.0F);
    this.BehindRightLeg3.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
    this.Tail = new ModelRenderer(this, 42, 27);
    this.Tail.setRotationPoint(0.0F, 5.5F, 0.5F);
    this.Tail.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
    this.setRotateAngle(Tail, -0.7853981633974483F, 0.0F, 0.0F);
    this.FrontLeftLeg2.addChild(this.FrontLeftLeg3);
    this.Body1.addChild(this.Body2);
    this.RightEar1.addChild(this.RightEar2);
    this.FrontLeftLeg3.addChild(this.FrontLeftFeet);
    this.BehindLeftLeg2.addChild(this.BehindLeftLeg3);
    this.Head1.addChild(this.Head3);
    this.Head1.addChild(this.Head2);
    this.Body2.addChild(this.Body3);
    this.Head1.addChild(this.LeftEar1);
    this.FrontRightLeg3.addChild(this.FrontRightFeet);
    this.BehindRightLeg3.addChild(this.BehindRightFeet);
    this.BehindLeftLeg1.addChild(this.BehindLeftLeg2);
    this.FrontRightLeg2.addChild(this.FrontRightLeg3);
    this.Head1.addChild(this.RightEar1);
    this.LeftEar1.addChild(this.LeftEar2);
    this.BehindRightLeg1.addChild(this.BehindRightLeg2);
    this.FrontRightLeg1.addChild(this.FrontRightLeg2);
    this.BehindLeftLeg3.addChild(this.BehindLeftFeet);
    this.FrontLeftLeg1.addChild(this.FrontLeftLeg2);
    this.BehindRightLeg2.addChild(this.BehindRightLeg3);
    this.Body3.addChild(this.Tail);
  }

  @Override
  public void func_225597_a_(EntityHowler entity, float limbSwing, float limbSwingAmount, float age, float f3, float f4) {
    float timer = entity.getAnimTimer();
    float timerTail = entity.getAnimTimerTail();
    if(timerTail<101){
      Tail.rotateAngleZ=Tail3RZ[0]+(Tail3RZ[1]-Tail3RZ[0])*(timerTail)/100f;
    }
    if(timerTail>100&&timerTail<201){
      Tail.rotateAngleZ=Tail3RZ[1]+(Tail3RZ[2]-Tail3RZ[1])*(timerTail-100)/100f;
    }
    if(timerTail>200){
      Tail.rotateAngleZ=Tail3RZ[2]+(Tail3RZ[3]-Tail3RZ[2])*(timerTail-200)/100f;
    }

    if(timer<101){
      FrontRightLeg1.rotateAngleX=FrontRightLeg1R[1]*timer/100;
      FrontRightLeg3.rotateAngleX=FrontRightLeg3R[1]*timer/100;
      FrontLeftLeg1.rotateAngleX=FrontLeftLeg1R[1]*timer/100;
      FrontLeftLeg3.rotateAngleX=FrontLeftLeg3R[1]*timer/100;
      BehindRightLeg1.rotateAngleX=BehindRightLeg1R[1]*timer/100;
      BehindRightLeg3.rotateAngleX=BehindRightLeg3R[1]*timer/100;
      BehindLeftLeg1.rotateAngleX=BehindLeftLeg1R[1]*timer/100;
      BehindLeftLeg3.rotateAngleX=BehindLeftLeg3R[1]*timer/100;
      if(entity.getShouldAnimJaw()){
        Head3.rotateAngleX=Head3R[0];
      }else{
        Head3.rotateAngleX=0;
      }
    }
    if(timer>100&&timer<201){
      FrontRightLeg1.rotateAngleX=FrontRightLeg1R[1]+(FrontRightLeg1R[2]-FrontRightLeg1R[1])*(timer-100)/100f;
      FrontRightLeg3.rotateAngleX=FrontRightLeg3R[1]+(FrontRightLeg3R[2]-FrontRightLeg3R[1])*(timer-100)/100f;
      FrontLeftLeg1.rotateAngleX=FrontLeftLeg1R[1]+(FrontLeftLeg1R[2]-FrontLeftLeg1R[1])*(timer-100)/100f;
      FrontLeftLeg3.rotateAngleX=FrontLeftLeg3R[1]+(FrontLeftLeg3R[2]-FrontLeftLeg3R[1])*(timer-100)/100f;
      BehindRightLeg1.rotateAngleX=BehindRightLeg1R[1]+(BehindRightLeg1R[2]-BehindRightLeg1R[1])*(timer-100)/100f;
      BehindRightLeg3.rotateAngleX=BehindRightLeg3R[1]+(BehindRightLeg3R[2]-BehindRightLeg3R[1])*(timer-100)/100f;
      BehindLeftLeg1.rotateAngleX=BehindLeftLeg1R[1]+(BehindLeftLeg1R[2]-BehindLeftLeg1R[1])*(timer-100)/100f;
      BehindLeftLeg3.rotateAngleX=BehindLeftLeg3R[1]+(BehindLeftLeg3R[2]-BehindLeftLeg3R[1])*(timer-100)/100f;
      if(entity.getShouldAnimJaw()) {
        Head3.rotateAngleX=Head3R[0]+(Head3R[1]-Head3R[0])*(timer-100)/100f;
      }else{
        Head3.rotateAngleX=0;
      }
    }
    if(timer>200){
      FrontRightLeg1.rotateAngleX=FrontRightLeg1R[2]+(FrontRightLeg1R[3]-FrontRightLeg1R[2])*(timer-200)/100f;
      FrontRightLeg3.rotateAngleX=FrontRightLeg3R[2]+(FrontRightLeg3R[3]-FrontRightLeg3R[2])*(timer-200)/100f;
      FrontLeftLeg1.rotateAngleX=FrontLeftLeg1R[2]+(FrontLeftLeg1R[3]-FrontLeftLeg1R[2])*(timer-200)/100f;
      FrontLeftLeg3.rotateAngleX=FrontLeftLeg3R[2]+(FrontLeftLeg3R[3]-FrontLeftLeg3R[2])*(timer-200)/100f;
      BehindRightLeg1.rotateAngleX=BehindRightLeg1R[2]+(BehindRightLeg1R[3]-BehindRightLeg1R[2])*(timer-200)/100f;
      BehindRightLeg3.rotateAngleX=BehindRightLeg3R[2]+(BehindRightLeg3R[3]-BehindRightLeg3R[2])*(timer-200)/100f;
      BehindLeftLeg1.rotateAngleX=BehindLeftLeg1R[2]+(BehindLeftLeg1R[3]-BehindLeftLeg1R[2])*(timer-200)/100f;
      BehindLeftLeg3.rotateAngleX=BehindLeftLeg3R[2]+(BehindLeftLeg3R[3]-BehindLeftLeg3R[2])*(timer-200)/100f;
      if(entity.getShouldAnimJaw()){
        Head3.rotateAngleX=Head3R[1]+(Head3R[2]-Head3R[1])*(timer-200)/100f;
      }else{
        Head3.rotateAngleX=0;
      }
    }
  }

  @Override
  protected Iterable<ModelRenderer> func_225602_a_() {
    return null;
  }

  @Override
  protected Iterable<ModelRenderer> func_225600_b_() {
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.998F);
    RenderSystem.disableBlend();
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.998F);
    RenderSystem.disableBlend();
    return ImmutableList.of(Head1,Body1,FrontLeftLeg1,BehindRightLeg1,FrontRightLeg1,BehindLeftLeg1);
  }

  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }


  //walking animation key frames
  //
  FunctionHelper helper = Kathairis.getHelper();
  float[] FrontRightLeg3R = {0,helper.degToRad(23.48f),0,0};
  float[] FrontRightLeg1R = {0,helper.degToRad(-49.57f),0,0};
  float[] BehindLeftLeg1R = {0,helper.degToRad(52.17f),0,0};
  float[] BehindLeftLeg3R = {0,helper.degToRad(-18.26f),0,0};
  float[] FrontLeftLeg3R = {0,0,helper.degToRad(26.09f),0};
  float[] BehindRightLeg3R = {0,0,helper.degToRad(-20.87f),0};
  float[] FrontLeftLeg1R = {0,0,helper.degToRad(-52.17f),0};
  float[] BehindRightLeg1R = {0,0,helper.degToRad(46.96f),0};
  float[] Head3R = {0,helper.degToRad(25f),0};

  float[] Tail3RZ = {0,helper.degToRad(-32f),helper.degToRad(32f),0};


}
