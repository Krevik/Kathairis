package io.github.krevik.kathairis.client.model;


import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.entity.EntityCactiSpore;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelSpikeyPlant - Undefined
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelCactiSpore<T extends LivingEntity> extends AgeableModel<T> {
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
        this.Spike6.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike6, 1.5707963267948966F, -2.356194490192345F, 0.0F);
        this.Spike3 = new ModelRenderer(this, 0, 0);
        this.Spike3.setRotationPoint(0.5F, 19.0F, 0.5F);
        this.Spike3.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike3, 0.9599310885968813F, 0.7853981633974483F, 0.0F);
        this.Spike9 = new ModelRenderer(this, 0, 0);
        this.Spike9.setRotationPoint(-1.0F, 20.0F, 0.0F);
        this.Spike9.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike9, 0.7853981633974483F, -1.5707963267948966F, 0.0F);
        this.Spike10 = new ModelRenderer(this, 0, 0);
        this.Spike10.setRotationPoint(0.0F, 20.0F, -1.0F);
        this.Spike10.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike10, 0.7853981633974483F, -3.141592653589793F, 0.0F);
        this.Petal1 = new ModelRenderer(this, 6, 25);
        this.Petal1.setRotationPoint(0.0F, -7.0F, -4.0F);
        this.Petal1.func_228301_a_(-2.0F, 0.0F, -6.0F, 4, 0, 6, 0.0F);
        this.setRotateAngle(Petal1, -0.3490658503988659F, 0.0F, 0.0F);
        this.Body4 = new ModelRenderer(this, 2, 25);
        this.Body4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body4.func_228301_a_(-6.0F, -5.0F, -4.0F, 1, 10, 8, 0.0F);
        this.Petal2 = new ModelRenderer(this, 18, 25);
        this.Petal2.setRotationPoint(4.0F, -7.0F, 0.0F);
        this.Petal2.func_228301_a_(0.0F, 0.0F, -2.0F, 6, 0, 4, 0.0F);
        this.setRotateAngle(Petal2, 0.0F, 0.0F, -0.3490658503988659F);
        this.Body = new ModelRenderer(this, 2, 3);
        this.Body.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Body.func_228301_a_(-5.0F, -6.0F, -5.0F, 10, 12, 10, 0.0F);
        this.Spike7 = new ModelRenderer(this, 0, 0);
        this.Spike7.setRotationPoint(1.0F, 18.0F, 1.0F);
        this.Spike7.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike7, 1.5707963267948966F, 0.7853981633974483F, 0.0F);
        this.Petal4 = new ModelRenderer(this, 18, 25);
        this.Petal4.setRotationPoint(-4.0F, -7.0F, 0.0F);
        this.Petal4.func_228301_a_(0.0F, 0.0F, -2.0F, 6, 0, 4, 0.0F);
        this.setRotateAngle(Petal4, 0.0F, 3.141592653589793F, 0.3490658503988659F);
        this.Spike12 = new ModelRenderer(this, 0, 0);
        this.Spike12.setRotationPoint(1.0F, 20.0F, 0.0F);
        this.Spike12.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike12, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
        this.Spike2 = new ModelRenderer(this, 0, 0);
        this.Spike2.setRotationPoint(-0.5F, 19.0F, -0.5F);
        this.Spike2.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike2, 0.9599310885968813F, -2.356194490192345F, 0.0F);
        this.Spike5 = new ModelRenderer(this, 0, 0);
        this.Spike5.setRotationPoint(-1.0F, 18.0F, 1.0F);
        this.Spike5.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike5, 1.5707963267948966F, -0.7853981633974483F, 0.0F);
        this.Body3 = new ModelRenderer(this, 38, 0);
        this.Body3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body3.func_228301_a_(-4.0F, -5.0F, 5.0F, 8, 10, 1, 0.0F);
        this.Body1 = new ModelRenderer(this, 38, 0);
        this.Body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body1.func_228301_a_(-4.0F, -5.0F, -6.0F, 8, 10, 1, 0.0F);
        this.Spike4 = new ModelRenderer(this, 0, 0);
        this.Spike4.setRotationPoint(0.5F, 19.0F, -0.5F);
        this.Spike4.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike4, 0.9599310885968813F, 2.356194490192345F, 0.0F);
        this.Body2 = new ModelRenderer(this, 2, 25);
        this.Body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body2.func_228301_a_(5.0F, -5.0F, -4.0F, 1, 10, 8, 0.0F);
        this.Spike1 = new ModelRenderer(this, 0, 0);
        this.Spike1.setRotationPoint(-0.5F, 19.0F, 0.5F);
        this.Spike1.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike1, 0.9599310885968813F, -0.7853981633974483F, 0.0F);
        this.Spike8 = new ModelRenderer(this, 0, 0);
        this.Spike8.setRotationPoint(1.0F, 19.0F, -1.0F);
        this.Spike8.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike8, 1.5707963267948966F, 2.356194490192345F, 0.0F);
        this.Spike11 = new ModelRenderer(this, 0, 0);
        this.Spike11.setRotationPoint(0.0F, 20.0F, 1.0F);
        this.Spike11.func_228301_a_(-0.5F, -12.0F, -0.5F, 1, 12, 1, 0.0F);
        this.setRotateAngle(Spike11, 0.7853981633974483F, 0.0F, 0.0F);
        this.Petal3 = new ModelRenderer(this, 6, 25);
        this.Petal3.setRotationPoint(0.0F, -7.0F, 4.0F);
        this.Petal3.func_228301_a_(-2.0F, 0.0F, -6.0F, 4, 0, 6, 0.0F);
        this.setRotateAngle(Petal3, -0.3490658503988659F, -3.141592653589793F, 0.0F);
        this.FlowerBud = new ModelRenderer(this, 0, 45);
        this.FlowerBud.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.FlowerBud.func_228301_a_(-4.0F, -8.0F, -4.0F, 8, 2, 8, 0.0F);
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
    public void func_225597_a_(T entity, float p_225597_2_, float p_225597_3_, float age, float p_225597_5_, float p_225597_6_) {
        setRotateAngle(Petal1,helper.degToRad(-20)- MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F,0,0);
        setRotateAngle(Petal2,0,0,helper.degToRad(-20)- MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F);
        setRotateAngle(Petal3,helper.degToRad(-20)- MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F,helper.degToRad(-180),0);
        setRotateAngle(Petal4,0,helper.degToRad(180),helper.degToRad(20)+ MathHelper.abs(MathHelper.sin(age * 0.006662F))*0.8F);
        if(entity instanceof EntityCactiSpore) {
            /*EntityCactiSpore living = (EntityCactiSpore) entity;
            int divider = 100;
            float multiplier = 0.4f;
            Spike1.offsetX = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike1.offsetZ = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike2.offsetX = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike2.offsetZ = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike3.offsetX = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike3.offsetZ = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike4.offsetX = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike4.offsetZ = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike5.offsetX = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike5.offsetZ = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike6.offsetX = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike6.offsetZ = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike7.offsetX = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike7.offsetZ = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike8.offsetX = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike8.offsetZ = +MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;

            Spike9.offsetX = MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            // Spike9.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            //Spike10.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            Spike10.offsetZ = MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            //Spike11.offsetY=+MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
            Spike11.offsetZ = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            Spike12.offsetX = -MathHelper.clamp((float) (Math.atan((float) living.getSpikeTimer() / divider)), 0, 1) * multiplier;
            //Spike12.offsetY=MathHelper.clamp((float)(Math.atan((float)living.getSpikeTimer()/divider)),0,1);
               */
        }
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return null;
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(Spike6,Spike3,Spike9,Spike10,Body,Spike7,Spike12,Spike2,Spike5,Spike4,Spike1,Spike8,
                Spike11);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();

}
