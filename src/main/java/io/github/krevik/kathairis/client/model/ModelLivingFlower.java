package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelLivingFlower<T extends LivingEntity> extends AgeableModel<T> {
    public ModelRenderer Lisc1;
    public ModelRenderer Lisc2;
    public ModelRenderer Lisc3;
    public ModelRenderer Noga3;
    public ModelRenderer Lisc4;
    public ModelRenderer Noga2;
    public ModelRenderer Lisc5;
    public ModelRenderer Noga1;
    public ModelRenderer MLisc1;
    public ModelRenderer Pylek;
    public ModelRenderer Shape2;
    public ModelRenderer Shape1;
    public ModelRenderer MLisc3;
    public ModelRenderer MLisc2;
    public ModelRenderer MLisc4;

    public ModelLivingFlower() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Lisc2 = new ModelRenderer(this, 4, 13);
        this.Lisc2.setRotationPoint(-1.2000000476837158F, 15.899999618530273F, -0.800000011920929F);
        this.Lisc2.addBox(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc2, 0.0F, -0.5918411612510681F, 0.0F);
        this.Pylek = new ModelRenderer(this, 0, 0);
        this.Pylek.setRotationPoint(0.4000000059604645F, 15.5F, -1.5F);
        this.Pylek.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(Pylek, 0.4553563892841339F, -0.9105382561683655F, 1.6845917701721191F);
        this.Lisc4 = new ModelRenderer(this, 4, 13);
        this.Lisc4.setRotationPoint(0.0F, 15.800000190734863F, 1.2000000476837158F);
        this.Lisc4.addBox(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc4, 0.0F, 1.775697946548462F, 0.0F);
        this.Lisc5 = new ModelRenderer(this, 4, 13);
        this.Lisc5.setRotationPoint(1.2999999523162842F, 15.800000190734863F, 0.0F);
        this.Lisc5.addBox(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc5, 0.0F, 3.1415927410125732F, 0.0F);
        this.Noga2 = new ModelRenderer(this, 30, 0);
        this.Noga2.setRotationPoint(-1.399999976158142F, 18.600000381469727F, -0.5F);
        this.Noga2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Noga2, 0.31869712471961975F, 0.31869712471961975F, 0.0F);
        this.Noga1 = new ModelRenderer(this, 30, 0);
        this.Noga1.setRotationPoint(-1.0F, 20.899999618530273F, 0.0F);
        this.Noga1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.Lisc3 = new ModelRenderer(this, 4, 13);
        this.Lisc3.setRotationPoint(0.10000000149011612F, 16.0F, -1.7000000476837158F);
        this.Lisc3.addBox(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc3, 0.0F, -1.8212510347366333F, 0.0F);
        this.Noga3 = new ModelRenderer(this, 30, 0);
        this.Noga3.setRotationPoint(-0.5F, 15.600000381469727F, -0.20000000298023224F);
        this.Noga3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(Noga3, 0.0F, 0.7740535140037537F, 0.31869712471961975F);
        this.Shape1 = new ModelRenderer(this, 32, 17);
        this.Shape1.setRotationPoint(-0.699999988079071F, 20.200000762939453F, -0.5F);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 7, 0, 3, 0.0F);
        this.setRotateAngle(Shape1, 0.0F, 0.0F, -0.6373942494392395F);
        this.MLisc2 = new ModelRenderer(this, 0, 7);
        this.MLisc2.setRotationPoint(0.30000001192092896F, 15.899999618530273F, 1.2999999523162842F);
        this.MLisc2.addBox(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc2, 0.34906584024429316F, 1.5934855937957764F, 0.0F);
        this.MLisc1 = new ModelRenderer(this, 0, 7);
        this.MLisc1.setRotationPoint(-1.399999976158142F, 15.899999618530273F, 0.30000001192092896F);
        this.MLisc1.addBox(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc1, 0.34906584024429316F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 32, 17);
        this.Shape2.setRotationPoint(-3.4000000953674316F, 20.200000762939453F, 0.0F);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 7, 0, 3, 0.0F);
        this.setRotateAngle(Shape2, 0.0F, 0.04380776360630989F, 0.7285004258155823F);
        this.MLisc4 = new ModelRenderer(this, 0, 7);
        this.MLisc4.setRotationPoint(-0.4000000059604645F, 15.899999618530273F, -1.2000000476837158F);
        this.MLisc4.addBox(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc4, 0.34906584024429316F, -1.5707963705062866F, 0.0F);
        this.Lisc1 = new ModelRenderer(this, 4, 13);
        this.Lisc1.setRotationPoint(-1.2000000476837158F, 15.800000190734863F, 0.699999988079071F);
        this.Lisc1.addBox(0.0F, 0.0F, 0.0F, 6, 0, 14, 0.0F);
        this.setRotateAngle(Lisc1, 0.0F, 0.5918411612510681F, 0.0F);
        this.MLisc3 = new ModelRenderer(this, 0, 7);
        this.MLisc3.setRotationPoint(1.100000023841858F, 16.0F, -0.4000000059604645F);
        this.MLisc3.addBox(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
        this.setRotateAngle(MLisc3, 0.34906584024429316F, 3.1415927410125732F, 0.0F);
    }

    @Override
    public void render(T t, float v, float v1, float v2, float v3, float v4) {
        this.Lisc1.rotateAngleX= MathHelper.abs(MathHelper.sin(v2 * 0.006662F))*1.4F;
        this.Lisc2.rotateAngleX= MathHelper.abs(MathHelper.sin(v2 * 0.006662F))*1.4F;
        this.Lisc3.rotateAngleX= MathHelper.abs(MathHelper.sin(v2 * 0.006662F))*1.4F;
        this.Lisc4.rotateAngleX= MathHelper.abs(MathHelper.sin(v2 * 0.006662F))*1.4F;
        this.Lisc5.rotateAngleX= MathHelper.abs(MathHelper.sin(v2 * 0.006662F))*1.4F;
    }

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return ImmutableList.of(Pylek);
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(Lisc1,
                Lisc2,
                Lisc3,
                Noga3,
                Lisc4,
                Noga2,
                Lisc5,
                Noga1,
                MLisc1,
                Pylek,
                Shape2,
                Shape1,
                MLisc3,
                MLisc2,
                MLisc4);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
