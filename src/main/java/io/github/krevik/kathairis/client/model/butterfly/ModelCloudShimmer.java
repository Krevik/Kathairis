package io.github.krevik.kathairis.client.model.butterfly;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelButterfly - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelCloudShimmer<T extends LivingEntity> extends AgeableModel<T> {
    public ModelRenderer Head;
    public ModelRenderer Wing4;
    public ModelRenderer Wing3;
    public ModelRenderer lowerbody;
    public ModelRenderer Czulko1;
    public ModelRenderer Body;

    public ModelCloudShimmer() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.Wing3 = new ModelRenderer(this, 47, 30);
        this.Wing3.setRotationPoint(0.0F, 17.0F, -12.6F);
        this.Wing3.func_228301_a_(-22.0F, 0.0F, 0.0F, 21, 0, 34, 0.0F);
        this.lowerbody = new ModelRenderer(this, 0, 46);
        this.lowerbody.setRotationPoint(-1.0F, 16.5F, 1.0F);
        this.lowerbody.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.Body = new ModelRenderer(this, 0, 18);
        this.Body.setRotationPoint(-2.0F, 16.0F, -7.0F);
        this.Body.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
        this.Wing4 = new ModelRenderer(this, 4, 0);
        this.Wing4.setRotationPoint(0.8F, 17.0F, -12.6F);
        this.Wing4.func_228301_a_(0.0F, 0.0F, 0.0F, 21, 0, 34, 0.0F);
        this.Czulko1 = new ModelRenderer(this, 0, 30);
        this.Czulko1.setRotationPoint(-3.0F, 14.1F, -14.7F);
        this.Czulko1.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 0, 6, 0.0F);
        this.setRotateAngle(Czulko1, -0.2617993877991494F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 38);
        this.Head.setRotationPoint(-2.0F, 15.5F, -10.0F);
        this.Head.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
    }

    @Override
    public void func_225597_a_(T entity, float f, float f1, float f2, float f3, float f4) {
        if(entity.getMotion().y!=0D||entity.getMotion().x!=0D||entity.getMotion().z!=0D) {
            Wing4.rotateAngleZ= MathHelper.sin(f2*0.6f);
            Wing3.rotateAngleZ=-MathHelper.sin(f2*0.6f);
        }else {
            Wing4.rotateAngleZ=-helper.degToRad(70)- MathHelper.sin(f2*0.05f)*0.05f;
            Wing3.rotateAngleZ= helper.degToRad(70)+ MathHelper.sin(f2*0.05f)*0.05f;
        }
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return null;
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(Wing3,lowerbody,Body,Wing4,Czulko1,Head);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();

}
