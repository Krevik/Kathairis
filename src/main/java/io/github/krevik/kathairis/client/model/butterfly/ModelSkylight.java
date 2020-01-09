package io.github.krevik.kathairis.client.model.butterfly;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelButterfly - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelSkylight<T extends LivingEntity> extends EntityModel<T> {
    public ModelRenderer Head;
    public ModelRenderer Wing4;
    public ModelRenderer Wing3;
    public ModelRenderer lowerbody;
    public ModelRenderer Body;
    public ModelRenderer Body_1;
    public ModelRenderer Head1;
    public ModelRenderer Head2;
    public ModelRenderer Czulko;
    public ModelRenderer Czulko_1;
    public ModelRenderer Wing2;
    public ModelRenderer Wing1;

    public ModelSkylight() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Wing2 = new ModelRenderer(this, 62, 0);
        this.Wing2.setRotationPoint(-0.0F, 0.0F, 5.0F);
        this.Wing2.func_228301_a_(-21.0F, 0.0F, 0.0F, 21, 0, 27, 0.0F);
        this.Wing3 = new ModelRenderer(this, 14, 46);
        this.Wing3.setRotationPoint(1.7F, 17.0F, -1.4F);
        this.Wing3.func_228301_a_(0.0F, 0.0F, -6.1F, 26, 0, 18, 0.0F);
        this.setRotateAngle(Wing3, 0.0F, 0.0F, -0.03665191429188092F);
        this.lowerbody = new ModelRenderer(this, 0, 46);
        this.lowerbody.setRotationPoint(-1.4F, 15.9F, 1.0F);
        this.lowerbody.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.Czulko = new ModelRenderer(this, 13, 29);
        this.Czulko.setRotationPoint(0.8F, 16.7F, -7.6F);
        this.Czulko.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 17, 0.0F);
        this.setRotateAngle(Czulko, 0.0F, -2.0943951023931953F, 0.31869712141416456F);
        this.Head = new ModelRenderer(this, 0, 38);
        this.Head.setRotationPoint(-2.3F, 15.5F, -8.9F);
        this.Head.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.Czulko_1 = new ModelRenderer(this, 13, 29);
        this.Czulko_1.setRotationPoint(1.6F, 15.6F, -5.4F);
        this.Czulko_1.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 17, 0.0F);
        this.setRotateAngle(Czulko_1, 0.0F, 2.0943951023931953F, -0.31869712141416456F);
        this.Wing4 = new ModelRenderer(this, -12, 0);
        this.Wing4.setRotationPoint(0.0F, 17.0F, -1.4F);
        this.Wing4.func_228301_a_(-25.0F, 0.0F, -6.0F, 26, 0, 18, 0.0F);
        this.Head2 = new ModelRenderer(this, 18, 36);
        this.Head2.setRotationPoint(-2.6F, 15.0F, -10.0F);
        this.Head2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.Head1 = new ModelRenderer(this, 18, 36);
        this.Head1.setRotationPoint(0.4F, 15.0F, -10.0F);
        this.Head1.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.Wing1 = new ModelRenderer(this, 59, 27);
        this.Wing1.setRotationPoint(0.0F, 0.0F, 5.7F);
        this.Wing1.func_228301_a_(0.0F, 0.0F, 0.0F, 20, 0, 27, 0.0F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(-2.0F, 16.0F, -8.1F);
        this.Body.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
        this.Body_1 = new ModelRenderer(this, 0, 18);
        this.Body_1.setRotationPoint(-2.2F, 15.4F, -1.1F);
        this.Body_1.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
        this.Wing4.addChild(this.Wing2);
        this.Wing3.addChild(this.Wing1);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Wing3.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.lowerbody.offsetX, this.lowerbody.offsetY, this.lowerbody.offsetZ);
        GlStateManager.translatef(this.lowerbody.rotationPointX * f5, this.lowerbody.rotationPointY * f5, this.lowerbody.rotationPointZ * f5);
        GlStateManager.scaled(1.6D, 1.2D, 1.0D);
        GlStateManager.translatef(-this.lowerbody.offsetX, -this.lowerbody.offsetY, -this.lowerbody.offsetZ);
        GlStateManager.translatef(-this.lowerbody.rotationPointX * f5, -this.lowerbody.rotationPointY * f5, -this.lowerbody.rotationPointZ * f5);
        this.lowerbody.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Czulko.offsetX, this.Czulko.offsetY, this.Czulko.offsetZ);
        GlStateManager.translatef(this.Czulko.rotationPointX * f5, this.Czulko.rotationPointY * f5, this.Czulko.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 1.0D, 0.5D);
        GlStateManager.translatef(-this.Czulko.offsetX, -this.Czulko.offsetY, -this.Czulko.offsetZ);
        GlStateManager.translatef(-this.Czulko.rotationPointX * f5, -this.Czulko.rotationPointY * f5, -this.Czulko.rotationPointZ * f5);
        this.Czulko.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Head.offsetX, this.Head.offsetY, this.Head.offsetZ);
        GlStateManager.translatef(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.1D, 1.0D);
        GlStateManager.translatef(-this.Head.offsetX, -this.Head.offsetY, -this.Head.offsetZ);
        GlStateManager.translatef(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Czulko_1.offsetX, this.Czulko_1.offsetY, this.Czulko_1.offsetZ);
        GlStateManager.translatef(this.Czulko_1.rotationPointX * f5, this.Czulko_1.rotationPointY * f5, this.Czulko_1.rotationPointZ * f5);
        GlStateManager.scaled(0.5D, 1.0D, 0.5D);
        GlStateManager.translatef(-this.Czulko_1.offsetX, -this.Czulko_1.offsetY, -this.Czulko_1.offsetZ);
        GlStateManager.translatef(-this.Czulko_1.rotationPointX * f5, -this.Czulko_1.rotationPointY * f5, -this.Czulko_1.rotationPointZ * f5);
        this.Czulko_1.render(f5);
        GlStateManager.popMatrix();
        this.Wing4.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Head2.offsetX, this.Head2.offsetY, this.Head2.offsetZ);
        GlStateManager.translatef(this.Head2.rotationPointX * f5, this.Head2.rotationPointY * f5, this.Head2.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.1D, 1.0D);
        GlStateManager.translatef(-this.Head2.offsetX, -this.Head2.offsetY, -this.Head2.offsetZ);
        GlStateManager.translatef(-this.Head2.rotationPointX * f5, -this.Head2.rotationPointY * f5, -this.Head2.rotationPointZ * f5);
        this.Head2.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Head1.offsetX, this.Head1.offsetY, this.Head1.offsetZ);
        GlStateManager.translatef(this.Head1.rotationPointX * f5, this.Head1.rotationPointY * f5, this.Head1.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 1.1D, 1.0D);
        GlStateManager.translatef(-this.Head1.offsetX, -this.Head1.offsetY, -this.Head1.offsetZ);
        GlStateManager.translatef(-this.Head1.rotationPointX * f5, -this.Head1.rotationPointY * f5, -this.Head1.rotationPointZ * f5);
        this.Head1.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Body.offsetX, this.Body.offsetY, this.Body.offsetZ);
        GlStateManager.translatef(this.Body.rotationPointX * f5, this.Body.rotationPointY * f5, this.Body.rotationPointZ * f5);
        GlStateManager.scaled(1.1D, 0.7D, 1.0D);
        GlStateManager.translatef(-this.Body.offsetX, -this.Body.offsetY, -this.Body.offsetZ);
        GlStateManager.translatef(-this.Body.rotationPointX * f5, -this.Body.rotationPointY * f5, -this.Body.rotationPointZ * f5);
        this.Body.render(f5);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Body_1.offsetX, this.Body_1.offsetY, this.Body_1.offsetZ);
        GlStateManager.translatef(this.Body_1.rotationPointX * f5, this.Body_1.rotationPointY * f5, this.Body_1.rotationPointZ * f5);
        GlStateManager.scaled(1.2D, 0.8D, 1.6D);
        GlStateManager.translatef(-this.Body_1.offsetX, -this.Body_1.offsetY, -this.Body_1.offsetZ);
        GlStateManager.translatef(-this.Body_1.rotationPointX * f5, -this.Body_1.rotationPointY * f5, -this.Body_1.rotationPointZ * f5);
        this.Body_1.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();

    @Override
    public void setRotationAngles(T entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
	      super.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
	    	if(entity.getMotion().y!=0D||entity.getMotion().x!=0D||entity.getMotion().z!=0D) {
	        	Wing4.rotateAngleZ= MathHelper.sin(f2*0.6f);
	        	Wing3.rotateAngleZ=-MathHelper.sin(f2*0.6f);
	    	}else {
	        	Wing4.rotateAngleZ= helper.degToRad(70)- MathHelper.sin(f2*0.05f)*0.05f;
	        	Wing3.rotateAngleZ=-helper.degToRad(70)+ MathHelper.sin(f2*0.05f)*0.05f;
	    	}
    }
}
