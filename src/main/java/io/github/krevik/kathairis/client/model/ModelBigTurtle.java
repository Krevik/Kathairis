package io.github.krevik.kathairis.client.model;


import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * BigTurtle.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelBigTurtle<T extends LivingEntity> extends AgeableModel<T> {

    public ModelRenderer Noga1;
    public ModelRenderer Noga2;
    public ModelRenderer Noga3;
    public ModelRenderer Noga4;
    public ModelRenderer Tulow1;
    public ModelRenderer Tulow2;
    public ModelRenderer Tulow3;
    public ModelRenderer Glowa;

    public ModelBigTurtle() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Tulow1 = new ModelRenderer(this, 0, 0);
        this.Tulow1.setRotationPoint(-7.0F, 15.0F, -7.0F);
        this.Tulow1.func_228301_a_(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
        this.Noga2 = new ModelRenderer(this, 0, 100);
        this.Noga2.setRotationPoint(5.0F, 19.0F, -8.0F);
        this.Noga2.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Glowa = new ModelRenderer(this, 70, 0);
        this.Glowa.setRotationPoint(-3.0F, 13.0F, -13.0F);
        this.Glowa.func_228301_a_(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
        this.Noga1 = new ModelRenderer(this, 0, 100);
        this.Noga1.setRotationPoint(-8.0F, 19.0F, 5.0F);
        this.Noga1.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Noga3 = new ModelRenderer(this, 0, 100);
        this.Noga3.setRotationPoint(-8.0F, 19.0F, -8.0F);
        this.Noga3.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Noga4 = new ModelRenderer(this, 0, 100);
        this.Noga4.setRotationPoint(5.0F, 19.0F, 5.0F);
        this.Noga4.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Tulow2 = new ModelRenderer(this, 0, 50);
        this.Tulow2.setRotationPoint(-8.0F, 15.0F, -7.0F);
        this.Tulow2.func_228301_a_(0.0F, 0.0F, 0.0F, 16, 1, 15, 0.0F);
        this.Tulow3 = new ModelRenderer(this, 0, 70);
        this.Tulow3.setRotationPoint(-7.0F, 11.0F, -7.0F);
        this.Tulow3.func_228301_a_(0.0F, 0.0F, 0.0F, 14, 4, 14, 0.0F);
    }

    @Override
    public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.Noga1.rotateAngleX = MathHelper.cos(limbSwing * 2F) * 1.4F * limbSwingAmount;
        this.Noga2.rotateAngleX = MathHelper.cos(limbSwing * 2F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.Noga3.rotateAngleX = MathHelper.cos(limbSwing * 2F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.Noga4.rotateAngleX = MathHelper.cos(limbSwing * 2F) * 1.4F * limbSwingAmount;
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return ImmutableList.of(Glowa);
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(Tulow1,Noga2,Glowa,Noga1,Noga3,Noga4,Tulow2,Tulow3);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;

    }
}
