package io.github.krevik.kathairis.client.model;


import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.entity.EntityCactiSpore;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelSpikeyPlant - Undefined
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelCactiSpore<T extends LivingEntity> extends EntityModel<T> {
    public RendererModel Body;
    public RendererModel Spike1;
    public RendererModel Spike2;
    public RendererModel Spike3;
    public RendererModel Spike4;
    public RendererModel Spike5;
    public RendererModel Spike6;
    public RendererModel Spike7;
    public RendererModel Spike8;
    public RendererModel Spike9;
    public RendererModel Spike10;
    public RendererModel Spike11;
    public RendererModel Spike12;
    public RendererModel Body1;
    public RendererModel Body2;
    public RendererModel Body3;
    public RendererModel Body4;
    public RendererModel FlowerBud;
    public RendererModel Petal1;
    public RendererModel Petal2;
    public RendererModel Petal3;
    public RendererModel Petal4;

    public ModelCactiSpore() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Spike6 = new RendererModel(this, 0, 0);
        this.Spike6.setRotationPoint(-1.0F, 18.0F, -1.0F);
        this.Spike6.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike6, 1.5707963267948966F, -2.356194490192345F, 0.0F);
        this.Spike3 = new RendererModel(this, 0, 0);
        this.Spike3.setRotationPoint(0.5F, 19.0F, 0.5F);
        this.Spike3.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike3, 0.9599310885968813F, 0.7853981633974483F, 0.0F);
        this.Spike9 = new RendererModel(this, 0, 0);
        this.Spike9.setRotationPoint(-1.0F, 20.0F, 0.0F);
        this.Spike9.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike9, 0.7853981633974483F, -1.5707963267948966F, 0.0F);
        this.Spike10 = new RendererModel(this, 0, 0);
        this.Spike10.setRotationPoint(0.0F, 20.0F, -1.0F);
        this.Spike10.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike10, 0.7853981633974483F, -3.141592653589793F, 0.0F);
        this.Petal1 = new RendererModel(this, 6, 25);
        this.Petal1.setRotationPoint(0.0F, -7.0F, -4.0F);
        this.Petal1.addBox(-2.0F, 0.0F, -6.0F, 4, 0, 6, 0.0F);
        this.setRotateAngle(Petal1, -0.3490658503988659F, 0.0F, 0.0F);
        this.Body4 = new RendererModel(this, 2, 25);
        this.Body4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body4.addBox(-6.0F, -5.0F, -4.0F, 1, 10, 8, 0.0F);
        this.Petal2 = new RendererModel(this, 18, 25);
        this.Petal2.setRotationPoint(4.0F, -7.0F, 0.0F);
        this.Petal2.addBox(0.0F, 0.0F, -2.0F, 6, 0, 4, 0.0F);
        this.setRotateAngle(Petal2, 0.0F, 0.0F, -0.3490658503988659F);
        this.Body = new RendererModel(this, 2, 3);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.addBox(-5.0F, -6.0F, -5.0F, 10, 12, 10, 0.0F);
        this.Spike7 = new RendererModel(this, 0, 0);
        this.Spike7.setRotationPoint(1.0F, 18.0F, 1.0F);
        this.Spike7.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike7, 1.5707963267948966F, 0.7853981633974483F, 0.0F);
        this.Petal4 = new RendererModel(this, 18, 25);
        this.Petal4.setRotationPoint(-4.0F, -7.0F, 0.0F);
        this.Petal4.addBox(0.0F, 0.0F, -2.0F, 6, 0, 4, 0.0F);
        this.setRotateAngle(Petal4, 0.0F, 3.141592653589793F, 0.3490658503988659F);
        this.Spike12 = new RendererModel(this, 0, 0);
        this.Spike12.setRotationPoint(1.0F, 20.0F, 0.0F);
        this.Spike12.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike12, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
        this.Spike2 = new RendererModel(this, 0, 0);
        this.Spike2.setRotationPoint(-0.5F, 19.0F, -0.5F);
        this.Spike2.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike2, 0.9599310885968813F, -2.356194490192345F, 0.0F);
        this.Spike5 = new RendererModel(this, 0, 0);
        this.Spike5.setRotationPoint(-1.0F, 18.0F, 1.0F);
        this.Spike5.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike5, 1.5707963267948966F, -0.7853981633974483F, 0.0F);
        this.Body3 = new RendererModel(this, 38, 0);
        this.Body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body3.addBox(-4.0F, -5.0F, 5.0F, 8, 10, 1, 0.0F);
        this.Body1 = new RendererModel(this, 38, 0);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.addBox(-4.0F, -5.0F, -6.0F, 8, 10, 1, 0.0F);
        this.Spike4 = new RendererModel(this, 0, 0);
        this.Spike4.setRotationPoint(0.5F, 19.0F, -0.5F);
        this.Spike4.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike4, 0.9599310885968813F, 2.356194490192345F, 0.0F);
        this.Body2 = new RendererModel(this, 2, 25);
        this.Body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body2.addBox(5.0F, -5.0F, -4.0F, 1, 10, 8, 0.0F);
        this.Spike1 = new RendererModel(this, 0, 0);
        this.Spike1.setRotationPoint(-0.5F, 19.0F, 0.5F);
        this.Spike1.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike1, 0.9599310885968813F, -0.7853981633974483F, 0.0F);
        this.Spike8 = new RendererModel(this, 0, 0);
        this.Spike8.setRotationPoint(1.0F, 19.0F, -1.0F);
        this.Spike8.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike8, 1.5707963267948966F, 2.356194490192345F, 0.0F);
        this.Spike11 = new RendererModel(this, 0, 0);
        this.Spike11.setRotationPoint(0.0F, 20.0F, 1.0F);
        this.Spike11.addBox(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike11, 0.7853981633974483F, 0.0F, 0.0F);
        this.Petal3 = new RendererModel(this, 6, 25);
        this.Petal3.setRotationPoint(0.0F, -7.0F, 4.0F);
        this.Petal3.addBox(-2.0F, 0.0F, -6.0F, 4, 0, 6, 0.0F);
        this.setRotateAngle(Petal3, -0.3490658503988659F, -3.141592653589793F, 0.0F);
        this.FlowerBud = new RendererModel(this, 0, 45);
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
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
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
    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();

    @Override
    public void setRotationAngles(T entity, float f, float f1, float age, float f3, float f4, float f5)
    {
        super.setRotationAngles(entity, f, f1, age, f3, f4, f5);
        setRotateAngle(Petal1,helper.degToRad(-20)- MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F,0,0);
        setRotateAngle(Petal2,0,0,helper.degToRad(-20)- MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F);
        setRotateAngle(Petal3,helper.degToRad(-20)- MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F,helper.degToRad(-180),0);
        setRotateAngle(Petal4,0,helper.degToRad(180),helper.degToRad(20)+ MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F);
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

            Spike9.offsetX= MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            // Spike9.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            //Spike10.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            Spike10.offsetZ= MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            //Spike11.offsetY=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            Spike11.offsetZ=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            Spike12.offsetX=-MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1)*multiplier;
            //Spike12.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);

        }
    }
}
