package io.github.krevik.kathairis.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class ModelMythicStoneSign extends Model
{
    /** The board on a sign that has the writing on it. */
    public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);
    /** The stick a sign stands on. */
    public ModelRenderer signStick;

    public ModelMythicStoneSign(Function<ResourceLocation, RenderType> p_i225947_1_) {
        super(p_i225947_1_);
    }

    /*public ModelMythicStoneSign()
    {
        this.signBoard.func_228301_a_(-12.0F, -14.0F, -1.0F, 24, 12, 2, 0.0F);
        this.signStick = new ModelRenderer(this, 0, 14);
        this.signStick.func_228301_a_(-1.0F, -2.0F, -1.0F, 2, 14, 2, 0.0F);
    }*/

    /**
     * Renders the sign model through TileEntitySignRenderer
     */
    //TODO
   /* public void renderSign()
    {
        this.signBoard.render(0.0625F);
        this.signStick.render(0.0625F);
    }*/

    @Override
    public void func_225598_a_(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {

    }
}