package io.github.krevik.kathairis.client.model;

import com.google.common.collect.ImmutableList;
import io.github.krevik.kathairis.Kathairis;
import io.github.krevik.kathairis.util.FunctionHelper;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * gecko - HKhugo
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelGecko<T extends LivingEntity> extends AgeableModel<T> {

    @Override
    protected Iterable<ModelRenderer> func_225602_a_() {
        return ImmutableList.of(head_1);
    }

    @Override
    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(legmain_4,
                legmain_3,
                legmain_2,
                jawtop,
                head_1,
                legmain_1,
                jawbottom,
                body_1,
                tail_1,
                tail_2,
                tail_3);
    }

    public ModelRenderer legmain_4;
    public ModelRenderer legmain_3;
    public ModelRenderer legmain_2;
    public ModelRenderer jawtop;
    public ModelRenderer head_1;
    public ModelRenderer legmain_1;
    public ModelRenderer jawbottom;
    public ModelRenderer body_1;
    public ModelRenderer tail_1;
    public ModelRenderer tail_2;
    public ModelRenderer tail_3;
    public ModelRenderer legmain_4Child;
    public ModelRenderer legmain_3Child;
    public ModelRenderer legmain_2Child;
    public ModelRenderer legmain_1Child;

    public ModelGecko() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head_1 = new ModelRenderer(this, 42, 0);
        this.head_1.setRotationPoint(-2.4F, 17.5F, -8.4F);
        this.head_1.func_228301_a_(0.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
        this.legmain_2Child = new ModelRenderer(this, 0, 4);
        this.legmain_2Child.setRotationPoint(-0.10000000149011612F, 1.399999976158142F, 0.5F);
        this.legmain_2Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_2Child, -0.9105382561683655F, 0.04555309191346169F, -0.04555309191346169F);
        this.jawbottom = new ModelRenderer(this, 17, 5);
        this.jawbottom.setRotationPoint(-1.9F, 20.57F, -9.7F);
        this.jawbottom.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.tail_1 = new ModelRenderer(this, 0, 16);
        this.tail_1.setRotationPoint(-1.9F, 19.1F, 0.5F);
        this.tail_1.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        this.setRotateAngle(tail_1, 0.27314402793711257F, 0.0F, 0.0F);
        this.body_1 = new ModelRenderer(this, 0, 23);
        this.body_1.setRotationPoint(-1.9F, 19.1F, -3.9F);
        this.body_1.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 3, 5, 0.0F);
        this.legmain_2 = new ModelRenderer(this, 0, 0);
        this.legmain_2.setRotationPoint(0.4F, 21.75F, -2.9F);
        this.legmain_2.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_2, -0.0F, 0.22759093446006054F, -0.7740535232594852F);
        this.tail_2 = new ModelRenderer(this, 0, 10);
        this.tail_2.setRotationPoint(-1.4F, 18.5F, 2.8F);
        this.tail_2.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(tail_2, 0.6373942428283291F, 0.0F, 0.0F);
        this.legmain_4Child = new ModelRenderer(this, 0, 4);
        this.legmain_4Child.setRotationPoint(-0.10000000149011612F, 1.399999976158142F, 0.5F);
        this.legmain_4Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_4Child, -1.1838568449020386F, 0.04555309191346169F, -0.04555309191346169F);
        this.legmain_1 = new ModelRenderer(this, 0, 0);
        this.legmain_1.setRotationPoint(-2.2F, 20.95F, -3.0F);
        this.legmain_1.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_1, 0.0F, -0.18203784098300857F, 0.7740535232594852F);
        this.tail_3 = new ModelRenderer(this, 11, 11);
        this.tail_3.setRotationPoint(-1.4F, 17.5F, 4.6F);
        this.tail_3.func_228301_a_(0.0F, 0.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail_3, 1.2292353921796064F, 0.004537856055185257F, 0.0F);
        this.legmain_4 = new ModelRenderer(this, 0, 0);
        this.legmain_4.setRotationPoint(-2.0F, 21.0F, 0.4F);
        this.legmain_4.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_4, 0.0F, -0.18203784098300857F, 0.7740535232594852F);
        this.jawtop = new ModelRenderer(this, 17, 0);
        this.jawtop.setRotationPoint(-1.9F, 19.57F, -9.7F);
        this.jawtop.func_228301_a_(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.legmain_3 = new ModelRenderer(this, 0, 0);
        this.legmain_3.setRotationPoint(0.4F, 21.65F, 0.4F);
        this.legmain_3.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_3, -0.0F, 0.22759093446006054F, -0.7740535232594852F);
        this.legmain_1Child = new ModelRenderer(this, 0, 4);
        this.legmain_1Child.setRotationPoint(-0.10000000149011612F, 1.399999976158142F, 0.5F);
        this.legmain_1Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_1Child, -0.9105382561683655F, 0.04555309191346169F, -0.04555309191346169F);
        this.legmain_3Child = new ModelRenderer(this, 0, 4);
        this.legmain_3Child.setRotationPoint(-0.10000000149011612F, 1.399999976158142F, 0.5F);
        this.legmain_3Child.func_228301_a_(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(legmain_3Child, -1.1838568449020386F, 0.04555309191346169F, -0.04555309191346169F);
        this.legmain_2.addChild(this.legmain_2Child);
        this.legmain_4.addChild(this.legmain_4Child);
        this.legmain_1.addChild(this.legmain_1Child);
        this.legmain_3.addChild(this.legmain_3Child);
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    FunctionHelper helper = Kathairis.getHelper();
    @Override
    public void func_225597_a_(T entity, float limbSwing, float limbSwingAmount, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

        this.legmain_1.rotateAngleX = (helper.degToRad(41.74F))+ MathHelper.sin(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.legmain_2.rotateAngleX = (helper.degToRad(49.57F))+ MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legmain_3.rotateAngleX = (helper.degToRad(49.57F))+ MathHelper.sin(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.legmain_4.rotateAngleX = (helper.degToRad(41.74F))+ MathHelper.sin(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

    }
}
