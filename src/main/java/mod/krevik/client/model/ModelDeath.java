package mod.krevik.client.model;

import mod.krevik.entity.EntityDeath;
import mod.krevik.util.FunctionHelper;
import mod.krevik.KCore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * ModelDeath - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelDeath extends ModelBase {
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Body1;
    public ModelRenderer RightArm1;
    public ModelRenderer LeftArm1;
    public ModelRenderer Neck;
    public ModelRenderer RightLegLayer;
    public ModelRenderer LeftLegLayer;
    public ModelRenderer RightArmLayer;
    public ModelRenderer LeftArmLayer;
    public ModelRenderer BodyLayer;
    public ModelRenderer CapeMiddle;
    public ModelRenderer RightFoot;
    public ModelRenderer LeftFoot;
    public ModelRenderer Bobs;
    public ModelRenderer Body2;
    public ModelRenderer Hips;
    public ModelRenderer RightArm2;
    public ModelRenderer shape28;
    public ModelRenderer shape28_1;
    public ModelRenderer shape28_2;
    public ModelRenderer shape28_3;
    public ModelRenderer shape28_4;
    public ModelRenderer LeftArm2;
    public ModelRenderer Head;
    public ModelRenderer Hoodie;
    public ModelRenderer RightCape;
    public ModelRenderer LeftCape;

    public ModelDeath() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.RightLeg = new ModelRenderer(this, 110, 0);
        this.RightLeg.setRotationPoint(-3.4F, 7.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.5F, 4, 15, 5, 0.0F);
        this.setRotateAngle(RightLeg, 0.0F, 0.17453292519943295F, 0.0F);
        this.shape28_4 = new ModelRenderer(this, 0, 77);
        this.shape28_4.setRotationPoint(-4.0F, 2.0F, 0.0F);
        this.shape28_4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
        this.LeftArmLayer = new ModelRenderer(this, 0, 0);
        this.LeftArmLayer.mirror = true;
        this.LeftArmLayer.setRotationPoint(8.5F, -12.5F, 0.0F);
        this.LeftArmLayer.addBox(-2.0F, -2.0F, -2.5F, 5, 12, 5, 0.25F);
        this.setRotateAngle(LeftArmLayer, -1.3089969389957472F, -0.17453292519943295F, 0.0F);
        this.CapeMiddle = new ModelRenderer(this, 0, 38);
        this.CapeMiddle.setRotationPoint(0.0F, -15.0F, 4.0F);
        this.CapeMiddle.addBox(-7.0F, 0.0F, 0.0F, 14, 34, 1, 0.0F);
        this.shape28_2 = new ModelRenderer(this, 0, 73);
        this.shape28_2.setRotationPoint(1.0F, 1.0F, 0.0F);
        this.shape28_2.addBox(0.0F, 0.0F, 0.0F, 11, 3, 1, 0.0F);
        this.LeftArm1 = new ModelRenderer(this, 114, 33);
        this.LeftArm1.mirror = true;
        this.LeftArm1.setRotationPoint(8.5F, -12.5F, 0.0F);
        this.LeftArm1.addBox(-1.5F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.setRotateAngle(LeftArm1, -1.3089969389957472F, -0.17453292519943295F, 0.0F);
        this.LeftArm2 = new ModelRenderer(this, 102, 50);
        this.LeftArm2.mirror = true;
        this.LeftArm2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.LeftArm2.addBox(-1.5F, -2.0F, -12.0F, 3, 4, 10, 0.0F);
        this.setRotateAngle(LeftArm2, 1.0471975511965976F, 0.3839724354387525F, 0.0F);
        this.RightCape = new ModelRenderer(this, 30, 38);
        this.RightCape.setRotationPoint(-7.0F, 0.0F, 1.0F);
        this.RightCape.addBox(-6.0F, 0.0F, -1.0F, 6, 34, 1, 0.0F);
        this.setRotateAngle(RightCape, 0.0F, -0.4363323129985824F, 0.0F);
        this.Hips = new ModelRenderer(this, 60, 31);
        this.Hips.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.Hips.addBox(-6.5F, 0.0F, -3.0F, 13, 3, 6, 0.0F);
        this.setRotateAngle(Hips, -0.03490658503988659F, 0.0F, 0.0F);
        this.LeftLegLayer = new ModelRenderer(this, 0, 17);
        this.LeftLegLayer.mirror = true;
        this.LeftLegLayer.setRotationPoint(3.4F, 7.0F, 0.0F);
        this.LeftLegLayer.addBox(-3.0F, 0.0F, -3.0F, 6, 15, 6, 0.25F);
        this.setRotateAngle(LeftLegLayer, 0.0F, -0.17453292519943295F, 0.0F);
        this.shape28_1 = new ModelRenderer(this, 0, 87);
        this.shape28_1.setRotationPoint(14.0F, 3.0F, 0.0F);
        this.shape28_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.Neck = new ModelRenderer(this, 102, 120);
        this.Neck.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.Neck.addBox(-3.5F, -3.0F, -2.0F, 7, 4, 4, 0.0F);
        this.LeftFoot = new ModelRenderer(this, 96, 21);
        this.LeftFoot.mirror = true;
        this.LeftFoot.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.LeftFoot.addBox(-3.5F, 0.0F, -5.5F, 7, 3, 9, 0.0F);
        this.Body2 = new ModelRenderer(this, 62, 18);
        this.Body2.setRotationPoint(0.0F, 12.0F, 0.0F);
        this.Body2.addBox(-4.5F, 0.0F, -3.0F, 9, 7, 6, 0.0F);
        this.setRotateAngle(Body2, 0.03490658503988659F, 0.0F, 0.0F);
        this.shape28_3 = new ModelRenderer(this, 0, 73);
        this.shape28_3.setRotationPoint(12.0F, 2.0F, 0.0F);
        this.shape28_3.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.Head = new ModelRenderer(this, 0, 106);
        this.Head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Head.addBox(-6.0F, -11.0F, -5.5F, 12, 11, 11, 0.0F);
        this.LeftCape = new ModelRenderer(this, 30, 38);
        this.LeftCape.mirror = true;
        this.LeftCape.setRotationPoint(7.0F, 0.0F, 1.0F);
        this.LeftCape.addBox(0.0F, 0.0F, -1.0F, 6, 34, 1, 0.0F);
        this.setRotateAngle(LeftCape, 0.0F, 0.4363323129985824F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 110, 0);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(3.4F, 7.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.5F, 4, 15, 5, 0.0F);
        this.setRotateAngle(LeftLeg, 0.0F, -0.17453292519943295F, 0.0F);
        this.Hoodie = new ModelRenderer(this, 46, 100);
        this.Hoodie.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hoodie.addBox(-7.0F, -13.0F, -6.2F, 14, 14, 14, 0.0F);
        this.RightArm2 = new ModelRenderer(this, 102, 50);
        this.RightArm2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.RightArm2.addBox(-1.5F, -2.0F, -12.0F, 3, 4, 10, 0.0F);
        this.setRotateAngle(RightArm2, 0.3490658503988659F, 0.08726646259971647F, 0.0F);
        this.Bobs = new ModelRenderer(this, 44, 0);
        this.Bobs.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Bobs.addBox(-6.5F, 3.0F, -4.0F, 13, 5, 1, 0.0F);
        this.RightArmLayer = new ModelRenderer(this, 0, 0);
        this.RightArmLayer.setRotationPoint(-8.5F, -12.5F, 0.0F);
        this.RightArmLayer.addBox(-3.0F, -2.0F, -2.5F, 5, 12, 5, 0.25F);
        this.setRotateAngle(RightArmLayer, -0.4363323129985824F, 0.0F, 0.0F);
        this.shape28 = new ModelRenderer(this, 55, 63);
        this.shape28.setRotationPoint(27.6F, -9.0F, -16.1F);
        this.shape28.addBox(0.0F, 0.0F, 0.0F, 1, 39, 1, 0.0F);
        this.setRotateAngle(shape28, 0.20943951023931953F, 0.15707963267948966F, 1.330289955870078F);
        this.RightFoot = new ModelRenderer(this, 96, 21);
        this.RightFoot.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.RightFoot.addBox(-3.5F, 0.0F, -5.5F, 7, 3, 9, 0.0F);
        this.BodyLayer = new ModelRenderer(this, 68, 40);
        this.BodyLayer.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.BodyLayer.addBox(-6.5F, 0.0F, -3.5F, 13, 12, 7, 0.25F);
        this.setRotateAngle(BodyLayer, -0.017453292519943295F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 66, 0);
        this.Body1.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.Body1.addBox(-6.5F, 0.0F, -3.0F, 13, 12, 6, 0.0F);
        this.setRotateAngle(Body1, -0.017453292519943295F, 0.0F, 0.0F);
        this.RightLegLayer = new ModelRenderer(this, 0, 17);
        this.RightLegLayer.setRotationPoint(-3.4F, 7.0F, 0.0F);
        this.RightLegLayer.addBox(-3.0F, 0.0F, -3.0F, 6, 15, 6, 0.25F);
        this.setRotateAngle(RightLegLayer, 0.0F, 0.17453292519943295F, 0.0F);
        this.RightArm1 = new ModelRenderer(this, 114, 33);
        this.RightArm1.setRotationPoint(-8.5F, -12.5F, 0.0F);
        this.RightArm1.addBox(-1.5F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.setRotateAngle(RightArm1, -0.4363323129985824F, -0.08726646259971647F, 0.0F);
        this.shape28_3.addChild(this.shape28_4);
        this.shape28.addChild(this.shape28_2);
        this.LeftArm1.addChild(this.LeftArm2);
        this.CapeMiddle.addChild(this.RightCape);
        this.Body2.addChild(this.Hips);
        this.shape28.addChild(this.shape28_1);
        this.LeftLeg.addChild(this.LeftFoot);
        this.Body1.addChild(this.Body2);
        this.shape28.addChild(this.shape28_3);
        this.Neck.addChild(this.Head);
        this.CapeMiddle.addChild(this.LeftCape);
        this.Head.addChild(this.Hoodie);
        this.RightArm1.addChild(this.RightArm2);
        this.Body1.addChild(this.Bobs);
        this.RightArm2.addChild(this.shape28);
        this.RightLeg.addChild(this.RightFoot);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.RightLeg.render(f5);
        this.LeftArmLayer.render(f5);
        this.CapeMiddle.render(f5);
        this.LeftArm1.render(f5);
        this.LeftLegLayer.render(f5);
        this.Neck.render(f5);
        this.LeftLeg.render(f5);
        this.RightArmLayer.render(f5);
        this.BodyLayer.render(f5);
        this.Body1.render(f5);
        this.RightLegLayer.render(f5);
        this.RightArm1.render(f5);
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
    float[] basicPositionRightArm2 = {helper.degToRad(20),helper.degToRad(5),helper.degToRad(0)};
    float[] basicPositionRightArm1 = {helper.degToRad(-25),helper.degToRad(-5),helper.degToRad(0)};
    float[] basicPositionLeftArm2 = {helper.degToRad(60),helper.degToRad(22),helper.degToRad(0)};
    float[] basicPositionLeftArm1 = {helper.degToRad(-75),helper.degToRad(-10),helper.degToRad(0)};
    float[] basicPositionScythe = {helper.degToRad(12),helper.degToRad(10),helper.degToRad(75)};

    float[] scytheAttackPosition1RightArm2 = {helper.degToRad(67),helper.degToRad(5),helper.degToRad(0)};
    float[] scytheAttackPosition1RightArm1 = {helper.degToRad(-102),helper.degToRad(7),helper.degToRad(33)};
    float[] scytheAttackPosition1LeftArm2 = {helper.degToRad(95),helper.degToRad(22),helper.degToRad(0)};
    float[] scytheAttackPosition1LeftArm1 = {helper.degToRad(-109),helper.degToRad(5),helper.degToRad(1)};
    float[] scytheAttackPosition1Scythe = {helper.degToRad(12),helper.degToRad(10),helper.degToRad(75)};

    float[] scytheAttackPosition2RightArm2 = {helper.degToRad(67),helper.degToRad(5),helper.degToRad(0)};
    float[] scytheAttackPosition2RightArm1 = {helper.degToRad(-28),helper.degToRad(9),helper.degToRad(60)};
    float[] scytheAttackPosition2LeftArm2 = {helper.degToRad(52),helper.degToRad(22),helper.degToRad(0)};
    float[] scytheAttackPosition2LeftArm1 = {helper.degToRad(-33),helper.degToRad(5),helper.degToRad(1)};
    float[] scytheAttackPosition2Scythe = {helper.degToRad(12),helper.degToRad(10),helper.degToRad(75)};

    /*float[] rightArmScytheAttackStage1 = {helper.degToRad(-23.86f),helper.degToRad(-3.58f),helper.degToRad(0f)};
    float[] leftArmScytheAttackStage1 = {helper.degToRad(-52.82f),helper.degToRad(-7.39f),helper.degToRad(0f)};
    float[] rightArmScytheAttackStage2 = {helper.degToRad(-58.24f),helper.degToRad(44.23f),helper.degToRad(7.87f)};
    float[] leftArmScytheAttackStage2 = {helper.degToRad(-84.30f),helper.degToRad(11.26f),helper.degToRad(0f)};
    float[] rightArmScytheAttackStage3 = {helper.degToRad(-19.15f),helper.degToRad(-20.14f),helper.degToRad(7.87f)};
    float[] leftArmScytheAttackStage3 = {helper.degToRad(-42.92f),helper.degToRad(-30.12f),helper.degToRad(0f)};
    float[] rightArmScytheAttackStage4=rightArmScytheAttackStage1;
    float[] leftArmScytheAttackStage4=leftArmScytheAttackStage1;*/

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float age, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, age, f3, f4, f5, entity);
        this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / limbSwing;
        this.RightLegLayer.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / limbSwing;

        this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / limbSwing;
        this.LeftLegLayer.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / limbSwing;

        //just holding the scythe
       /* setRotateAngle(LeftArm1,helper.degToRad(-52.82f),helper.degToRad(-7.39f),helper.degToRad(0f));
        setRotateAngle(RightArm1,helper.degToRad(-23.86f),helper.degToRad(-3.58f),helper.degToRad(0f));
        setRotateAngle(RightArm2,helper.degToRad(3.27f),helper.degToRad(-0.32f),helper.degToRad(0f));
        //a bit of arms movement during holding the scythe
        setRotateAngle(LeftArm1,helper.degToRad(-52.82f)+MathHelper.sin(age*0.05f)*0.04f,helper.degToRad(-7.39f),helper.degToRad(0f));
        setRotateAngle(LeftArmLayer,LeftArm1.rotateAngleX,LeftArm1.rotateAngleY,LeftArm2.rotateAngleZ);
        setRotateAngle(RightArm1,helper.degToRad(-23.86f)+MathHelper.sin(age*0.05f)*0.04f,helper.degToRad(-3.58f),helper.degToRad(0f));
        setRotateAngle(RightArmLayer,RightArm1.rotateAngleX,RightArm1.rotateAngleY,RightArm2.rotateAngleZ);*/

       //breathing
        Body1.rotateAngleX=MathHelper.sin(age*0.05f)*0.04f;
        Body2.rotateAngleX=-MathHelper.sin(age*0.05f)*0.06f;
        Hips.rotateAngleX=MathHelper.sin(age*0.05f)*0.02f;


        setRotateAngle(LeftArm1,basicPositionLeftArm1[0],basicPositionLeftArm1[1],basicPositionLeftArm1[2]);
        setRotateAngle(LeftArm2,basicPositionLeftArm2[0],basicPositionLeftArm2[1],basicPositionLeftArm2[2]);
        setRotateAngle(RightArm1,basicPositionRightArm1[0],basicPositionRightArm1[1],basicPositionRightArm1[2]);
        setRotateAngle(RightArm2,basicPositionRightArm2[0],basicPositionRightArm2[1],basicPositionRightArm2[2]);
        setRotateAngle(shape28,basicPositionScythe[0],basicPositionScythe[1],basicPositionScythe[2]);

        if(entity instanceof EntityDeath){
            EntityDeath death = (EntityDeath) entity;
            if(death.getIsUsingSomeAttack()){
                int scytheAttackTimer=death.getScytheAttackTimer();
                if(scytheAttackTimer>-1){
                    if(scytheAttackTimer>30&&scytheAttackTimer<=45){
                        setRotateAngle(LeftArm1, basicPositionLeftArm1[0]+((scytheAttackPosition1LeftArm1[0]-basicPositionLeftArm1[0])/(scytheAttackTimer-30)),basicPositionLeftArm1[1]+((scytheAttackPosition1LeftArm1[1]-basicPositionLeftArm1[1])/(scytheAttackTimer-30)),basicPositionLeftArm1[2]+((scytheAttackPosition1LeftArm1[2]-basicPositionLeftArm1[2])/(scytheAttackTimer-30)));
                        setRotateAngle(LeftArm2, basicPositionLeftArm2[0]+((scytheAttackPosition1LeftArm2[0]-basicPositionLeftArm2[0])/(scytheAttackTimer-30)),basicPositionLeftArm2[1]+((scytheAttackPosition1LeftArm2[1]-basicPositionLeftArm2[1])/(scytheAttackTimer-30)),basicPositionLeftArm2[2]+((scytheAttackPosition1LeftArm2[2]-basicPositionLeftArm2[2])/(scytheAttackTimer-30)));
                        setRotateAngle(RightArm1, basicPositionRightArm1[0]+((scytheAttackPosition1RightArm1[0]-basicPositionRightArm1[0])/(scytheAttackTimer-30)),basicPositionRightArm1[1]+((scytheAttackPosition1RightArm1[1]-basicPositionRightArm1[1])/(scytheAttackTimer-30)),basicPositionRightArm1[2]+((scytheAttackPosition1RightArm1[2]-basicPositionRightArm1[2])/(scytheAttackTimer-30)));
                        setRotateAngle(RightArm2, basicPositionRightArm2[0]+((scytheAttackPosition1RightArm2[0]-basicPositionRightArm2[0])/(scytheAttackTimer-30)),basicPositionRightArm2[1]+((scytheAttackPosition1RightArm2[1]-basicPositionRightArm2[1])/(scytheAttackTimer-30)),basicPositionRightArm2[2]+((scytheAttackPosition1RightArm2[2]-basicPositionRightArm2[2])/(scytheAttackTimer-30)));

                    }
                    if(scytheAttackTimer>15&&scytheAttackTimer<=30){
                        setRotateAngle(LeftArm1, scytheAttackPosition1LeftArm1[0]+((scytheAttackPosition2LeftArm1[0]-scytheAttackPosition1LeftArm1[0])/(scytheAttackTimer-15)),scytheAttackPosition1LeftArm1[1]+((scytheAttackPosition2LeftArm1[1]-scytheAttackPosition1LeftArm1[1])/(scytheAttackTimer-15)),scytheAttackPosition1LeftArm1[2]+((scytheAttackPosition2LeftArm1[2]-scytheAttackPosition1LeftArm1[2])/(scytheAttackTimer-15)));
                        setRotateAngle(LeftArm2, scytheAttackPosition1LeftArm2[0]+((scytheAttackPosition2LeftArm2[0]-scytheAttackPosition1LeftArm2[0])/(scytheAttackTimer-15)),scytheAttackPosition1LeftArm2[1]+((scytheAttackPosition2LeftArm2[1]-scytheAttackPosition1LeftArm2[1])/(scytheAttackTimer-15)),scytheAttackPosition1LeftArm2[2]+((scytheAttackPosition2LeftArm2[2]-scytheAttackPosition1LeftArm2[2])/(scytheAttackTimer-15)));
                        setRotateAngle(RightArm1, scytheAttackPosition1RightArm1[0]+((scytheAttackPosition2RightArm1[0]-scytheAttackPosition1RightArm1[0])/(scytheAttackTimer-15)),scytheAttackPosition1RightArm1[1]+((scytheAttackPosition2RightArm1[1]-scytheAttackPosition1RightArm1[1])/(scytheAttackTimer-15)),scytheAttackPosition1RightArm1[2]+((scytheAttackPosition2RightArm1[2]-scytheAttackPosition1RightArm1[2])/(scytheAttackTimer-15)));
                        setRotateAngle(RightArm2, scytheAttackPosition1RightArm2[0]+((scytheAttackPosition2RightArm2[0]-scytheAttackPosition1RightArm2[0])/(scytheAttackTimer-15)),scytheAttackPosition1RightArm2[1]+((scytheAttackPosition2RightArm2[1]-scytheAttackPosition1RightArm2[1])/(scytheAttackTimer-15)),scytheAttackPosition1RightArm2[2]+((scytheAttackPosition2RightArm2[2]-scytheAttackPosition1RightArm2[2])/(scytheAttackTimer-15)));
                    }
                    if(scytheAttackTimer>000&&scytheAttackTimer<=15){
                        setRotateAngle(LeftArm1, scytheAttackPosition2LeftArm1[0]+((basicPositionLeftArm1[0]-scytheAttackPosition2LeftArm1[0])/(scytheAttackTimer)),scytheAttackPosition2LeftArm1[1]+((basicPositionLeftArm1[1]-scytheAttackPosition2LeftArm1[1])/(scytheAttackTimer)),scytheAttackPosition2LeftArm1[2]+((basicPositionLeftArm1[2]-scytheAttackPosition2LeftArm1[2])/(scytheAttackTimer)));
                        setRotateAngle(LeftArm2, scytheAttackPosition2LeftArm2[0]+((basicPositionLeftArm2[0]-scytheAttackPosition2LeftArm2[0])/(scytheAttackTimer)),scytheAttackPosition2LeftArm2[1]+((basicPositionLeftArm2[1]-scytheAttackPosition2LeftArm2[1])/(scytheAttackTimer)),scytheAttackPosition2LeftArm2[2]+((basicPositionLeftArm2[2]-scytheAttackPosition2LeftArm2[2])/(scytheAttackTimer)));
                        setRotateAngle(RightArm1, scytheAttackPosition2RightArm1[0]+((basicPositionRightArm1[0]-scytheAttackPosition2RightArm1[0])/(scytheAttackTimer)),scytheAttackPosition2RightArm1[1]+((basicPositionRightArm1[1]-scytheAttackPosition2RightArm1[1])/(scytheAttackTimer)),scytheAttackPosition2RightArm1[2]+((basicPositionRightArm1[2]-scytheAttackPosition2RightArm1[2])/(scytheAttackTimer)));
                        setRotateAngle(RightArm2, scytheAttackPosition2RightArm2[0]+((basicPositionRightArm2[0]-scytheAttackPosition2RightArm2[0])/(scytheAttackTimer)),scytheAttackPosition2RightArm2[1]+((basicPositionRightArm2[1]-scytheAttackPosition2RightArm2[1])/(scytheAttackTimer)),scytheAttackPosition2RightArm2[2]+((basicPositionRightArm2[2]-scytheAttackPosition2RightArm2[2])/(scytheAttackTimer)));
                    }
                }
                int dissolutionAttackTime=death.getDissolutionAttackTimer();
                if(dissolutionAttackTime>-1){

                }
            }
        }

        setRotateAngle(LeftArmLayer,LeftArm1.rotateAngleX,LeftArm1.rotateAngleY,LeftArm1.rotateAngleZ);
        setRotateAngle(RightArmLayer,RightArm1.rotateAngleX,RightArm1.rotateAngleY,RightArm1.rotateAngleZ);



    }


    public void postRenderArm(float scale, EnumHandSide side)
    {
        this.getArmForSide(side).postRender(scale);
    }
    protected ModelRenderer getArmForSide(EnumHandSide side)
    {
        return side == EnumHandSide.LEFT ? this.LeftArm1 : this.RightArm1;
    }

}

