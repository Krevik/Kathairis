package io.github.krevik.kathairis.client.model.butterfly;

import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Ruby Sile - Hkhugo
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelRubySile<T extends LivingEntity> extends EntityModel<T> {
    public RendererModel Head;
    public RendererModel Wing4;
    public RendererModel Wing3;
    public RendererModel lowerbody;
    public RendererModel Czulko1;
    public RendererModel Body;

    public ModelRubySile() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Wing4 = new RendererModel(this, 11, 3);
        this.Wing4.setRotationPoint(0.6F, 22.0F, -12.6F);
        this.Wing4.addBox(0.0F, 0.0F, 0.0F, 15, 0, 27, 0.0F);
        this.Wing3 = new RendererModel(this, 60, 33);
        this.Wing3.setRotationPoint(-0.6F, 22.0F, -12.6F);
        this.Wing3.addBox(-15.0F, 0.0F, 0.0F, 15, 0, 27, 0.0F);
        this.Body = new RendererModel(this, 0, 18);
        this.Body.setRotationPoint(-2.0F, 21.0F, -6.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
        this.Head = new RendererModel(this, 0, 38);
        this.Head.setRotationPoint(-2.0F, 20.5F, -10.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.Czulko1 = new RendererModel(this, 0, 30);
        this.Czulko1.setRotationPoint(-3.0F, 19.1F, -14.7F);
        this.Czulko1.addBox(0.0F, 0.0F, 0.0F, 6, 0, 6, 0.0F);
        this.setRotateAngle(Czulko1, -0.2617993877991494F, 0.0F, 0.0F);
        this.lowerbody = new RendererModel(this, 0, 46);
        this.lowerbody.setRotationPoint(-1.0F, 21.5F, 0.8F);
        this.lowerbody.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
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
    public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();
    @Override
    public void setRotationAngles(T entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
    	super.setRotationAngles(entity,f, f1, f2, f3, f4, f5);
    	if(entity.getMotion().y!=0D||entity.getMotion().x!=0D||entity.getMotion().z!=0D) {
        	Wing4.rotateAngleZ= MathHelper.sin(f2*0.6f);
        	Wing3.rotateAngleZ=-MathHelper.sin(f2*0.6f);
    	}else {
        	Wing4.rotateAngleZ=-helper.degToRad(70)- MathHelper.sin(f2*0.05f)*0.05f;
        	Wing3.rotateAngleZ= helper.degToRad(70)+ MathHelper.sin(f2*0.05f)*0.05f;
    	}
    }
}
