package mod.krevik.client.model;

import mod.krevik.entity.EntityCactiSpore;
import mod.krevik.util.FunctionHelper;
import mod.krevik.KCore;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelSpikeyPlant - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCactiSpore extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Spike1;
    public ModelRenderer Spike2;
    public ModelRenderer Spike3;
    public ModelRenderer Spike4;
    public ModelRenderer Spike5;
    public ModelRenderer Spike6;
    public ModelRenderer Spike7;
    public ModelRenderer Spike8;
    public ModelRenderer Spike9;
    public ModelRenderer Spike10;
    public ModelRenderer Spike11;
    public ModelRenderer Spike12;
    public ModelRenderer Body1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer FlowerBud;
    public ModelRenderer Petal1;
    public ModelRenderer Petal2;
    public ModelRenderer Petal3;
    public ModelRenderer Petal4;

    public ModelCactiSpore() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Spike6 = new ModelRenderer(this, 0, 0);
        this.Spike6.setRotationPoint(-1.0F, 18.0F, -1.0F);
        this.Spike6.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike6, 1.5707963267948966F, -2.356194490192345F, 0.0F);
        this.Spike3 = new ModelRenderer(this, 0, 0);
        this.Spike3.setRotationPoint(0.5F, 19.0F, 0.5F);
        this.Spike3.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike3, 0.9599310885968813F, 0.7853981633974483F, 0.0F);
        this.Spike9 = new ModelRenderer(this, 0, 0);
        this.Spike9.setRotationPoint(-1.0F, 20.0F, 0.0F);
        this.Spike9.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike9, 0.7853981633974483F, -1.5707963267948966F, 0.0F);
        this.Spike10 = new ModelRenderer(this, 0, 0);
        this.Spike10.setRotationPoint(0.0F, 20.0F, -1.0F);
        this.Spike10.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike10, 0.7853981633974483F, -3.141592653589793F, 0.0F);
        this.Petal1 = new ModelRenderer(this, 6, 25);
        this.Petal1.setRotationPoint(0.0F, -7.0F, -4.0F);
        this.Petal1.addBox(-2.0F, 0.0F, -6.0F, 4, 0, 6, 0.0F);
        this.setRotateAngle(Petal1, -0.3490658503988659F, 0.0F, 0.0F);
        this.Body4 = new ModelRenderer(this, 2, 25);
        this.Body4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body4.addBox(-6.0F, -5.0F, -4.0F, 1, 10, 8, 0.0F);
        this.Petal2 = new ModelRenderer(this, 18, 25);
        this.Petal2.setRotationPoint(4.0F, -7.0F, 0.0F);
        this.Petal2.addBox(0.0F, 0.0F, -2.0F, 6, 0, 4, 0.0F);
        this.setRotateAngle(Petal2, 0.0F, 0.0F, -0.3490658503988659F);
        this.Body = new ModelRenderer(this, 2, 3);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-5.0F, -6.0F, -5.0F, 10, 12, 10, 0.0F);
        this.Spike7 = new ModelRenderer(this, 0, 0);
        this.Spike7.setRotationPoint(1.0F, 18.0F, 1.0F);
        this.Spike7.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike7, 1.5707963267948966F, 0.7853981633974483F, 0.0F);
        this.Petal4 = new ModelRenderer(this, 18, 25);
        this.Petal4.setRotationPoint(-4.0F, -7.0F, 0.0F);
        this.Petal4.addBox(0.0F, 0.0F, -2.0F, 6, 0, 4, 0.0F);
        this.setRotateAngle(Petal4, 0.0F, 3.141592653589793F, 0.3490658503988659F);
        this.Spike12 = new ModelRenderer(this, 0, 0);
        this.Spike12.setRotationPoint(1.0F, 20.0F, 0.0F);
        this.Spike12.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike12, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
        this.Spike2 = new ModelRenderer(this, 0, 0);
        this.Spike2.setRotationPoint(-0.5F, 19.0F, -0.5F);
        this.Spike2.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike2, 0.9599310885968813F, -2.356194490192345F, 0.0F);
        this.Spike5 = new ModelRenderer(this, 0, 0);
        this.Spike5.setRotationPoint(-1.0F, 18.0F, 1.0F);
        this.Spike5.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike5, 1.5707963267948966F, -0.7853981633974483F, 0.0F);
        this.Body3 = new ModelRenderer(this, 38, 0);
        this.Body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body3.addBox(-4.0F, -5.0F, 5.0F, 8, 10, 1, 0.0F);
        this.Body1 = new ModelRenderer(this, 38, 0);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.addBox(-4.0F, -5.0F, -6.0F, 8, 10, 1, 0.0F);
        this.Spike4 = new ModelRenderer(this, 0, 0);
        this.Spike4.setRotationPoint(0.5F, 19.0F, -0.5F);
        this.Spike4.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike4, 0.9599310885968813F, 2.356194490192345F, 0.0F);
        this.Body2 = new ModelRenderer(this, 2, 25);
        this.Body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body2.addBox(5.0F, -5.0F, -4.0F, 1, 10, 8, 0.0F);
        this.Spike1 = new ModelRenderer(this, 0, 0);
        this.Spike1.setRotationPoint(-0.5F, 19.0F, 0.5F);
        this.Spike1.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike1, 0.9599310885968813F, -0.7853981633974483F, 0.0F);
        this.Spike8 = new ModelRenderer(this, 0, 0);
        this.Spike8.setRotationPoint(1.0F, 19.0F, -1.0F);
        this.Spike8.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike8, 1.5707963267948966F, 2.356194490192345F, 0.0F);
        this.Spike11 = new ModelRenderer(this, 0, 0);
        this.Spike11.setRotationPoint(0.0F, 20.0F, 1.0F);
        this.Spike11.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike11, 0.7853981633974483F, 0.0F, 0.0F);
        this.Petal3 = new ModelRenderer(this, 6, 25);
        this.Petal3.setRotationPoint(0.0F, -7.0F, 4.0F);
        this.Petal3.addBox(-2.0F, 0.0F, -6.0F, 4, 0, 6, 0.0F);
        this.setRotateAngle(Petal3, -0.3490658503988659F, -3.141592653589793F, 0.0F);
        this.FlowerBud = new ModelRenderer(this, 0, 45);
        this.FlowerBud.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.FlowerBud.addBox(-4.0F, -8.0F, -4.0F, 8, 2, 8, 0.0F);
        this.FlowerBud.addChild(this.Petal1);
        this.Body.addChild(this.Body4);
        this.FlowerBud.addChild(this.Petal2);
        this.FlowerBud.addChild(this.Petal4);
        this.Body.addChild(this.Body3);
        this.Body.addChild(this.Body1);
        this.Body.addChild(this.Body2);
        this.FlowerBud.addChild(this.Petal3);
        this.Body.addChild(this.FlowerBud);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Spike6.render(f5);
        this.Spike3.render(f5);
        this.Spike9.render(f5);
        this.Spike10.render(f5);
        this.Body.render(f5);
        this.Spike7.render(f5);
        this.Spike12.render(f5);
        this.Spike2.render(f5);
        this.Spike5.render(f5);
        this.Spike4.render(f5);
        this.Spike1.render(f5);
        this.Spike8.render(f5);
        this.Spike11.render(f5);
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
    public void setRotationAngles(float f, float f1, float age, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, age, f3, f4, f5, entity);
        setRotateAngle(Petal1,helper.degToRad(-20)-MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F,0,0);
        setRotateAngle(Petal2,0,0,helper.degToRad(-20)-MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F);
        setRotateAngle(Petal3,helper.degToRad(-20)-MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F,helper.degToRad(-180),0);
        setRotateAngle(Petal4,0,helper.degToRad(180),helper.degToRad(20)+MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F);
        if(entity instanceof EntityCactiSpore){
            EntityCactiSpore living = (EntityCactiSpore) entity;
                    int divider=100;
                    float multiplier=0.4f;
            Spike1.offsetX=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike1.offsetZ=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike2.offsetX=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike2.offsetZ=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike3.offsetX=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike3.offsetZ=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike4.offsetX=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike4.offsetZ=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike5.offsetX=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike5.offsetZ=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike6.offsetX=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike6.offsetZ=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike7.offsetX=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike7.offsetZ=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike8.offsetX=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike8.offsetZ=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;

            Spike9.offsetX=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            // Spike9.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            //Spike10.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            Spike10.offsetZ=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            //Spike11.offsetY=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            Spike11.offsetZ=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike12.offsetX=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            //Spike12.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);

        }
    }
}
