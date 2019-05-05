package io.github.krevik.kathairis.client.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class GuiOldMan extends GuiScreen {


    Minecraft mc = Minecraft.getInstance();
    private final int ImageHeight = 340, ImageWidth = 704, ImageScale = 250;
    private static final ResourceLocation GUITextures = new ResourceLocation(MOD_ID,"textures/gui/oldman.png");
    private static int mode=0;
    private GuiButton Next;
    private GuiButton Back;
    private GuiButton Knowledge;
    private GuiButton Power;
    private GuiButton Adventure;
    int margin=15;


    public GuiOldMan(){
    }

    public void setMode(int mode1){
        mode=mode1;
    }

    @Override
    public void initGui() {
        mode=0;
        buttons.clear();
        this.addButton(Next = new GuiButton(1,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin), 80, 20, "Next"){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                   GuiOldMan.this.setMode(1);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Back = new GuiButton(2,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin*3), 60, 20, "Back"){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                if(mode==0) {
                    Minecraft.getInstance().player.closeScreen();
                }
                if(mode==1){
                    GuiOldMan.this.setMode(0);
                }
                if(mode==2||mode==3||mode==4){
                    GuiOldMan.this.setMode(1);
                }
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Knowledge = new GuiButton(3,ImageWidth/2+margin*8, (height-ImageHeight/2-margin+margin), 80, 20, "Knowledge"){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiOldMan.this.setMode(2);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Power = new GuiButton(4,ImageWidth/2+margin*8, (height-ImageHeight/2-margin+margin*3), 60, 20, "Power"){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiOldMan.this.setMode(3);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Adventure = new GuiButton(5,ImageWidth/2+margin*8, (height-ImageHeight/2-margin+margin*5), 60, 20, "Adventure"){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiOldMan.this.setMode(4);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });

    }

    @Override
    public void tick() {
        Back.visible=true;
        Back.enabled=true;

        Knowledge.visible=false;
        Knowledge.enabled=false;
        Power.visible=false;
        Power.enabled=false;
        Adventure.visible=false;
        Adventure.enabled=false;
        //main Screen
        if(mode==0) {
            Next.visible = true;
            Next.enabled = true;
        }
        //Lets_start screen
        if(mode==1) {
            Next.visible = false;
            Next.enabled = false;
            Knowledge.visible=true;
            Knowledge.enabled=true;
            Power.visible=true;
            Power.enabled=true;
            Adventure.visible=true;
            Adventure.enabled=true;
        }

        if(mode==2||mode==3||mode==4) {
            Next.visible = false;
            Next.enabled = false;
            Knowledge.visible=false;
            Knowledge.enabled=false;
            Power.visible=false;
            Power.enabled=false;
            Adventure.visible=false;
            Adventure.enabled=false;
        }

    }

    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color)
    {
        GlStateManager.scalef(0.8f,0.8f,0.8f);
        fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color);
        GlStateManager.scalef(1.25f,1.25f,1.25f);
    }



    @Override
    public void render(int parWidth, int parHeight, float particle) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableColorMaterial();
        this.mc.getTextureManager().bindTexture(GUITextures);
        int scale = Minecraft.getInstance().gameSettings.guiScale;
        drawModalRectWithCustomSizedTexture(margin, height-ImageHeight/2-margin, 0, 0, ImageWidth/2,ImageHeight/2,ImageWidth/2,ImageHeight/2);
        this.drawString(fontRenderer, "Mysterious Stranger", (int)(margin*1.65), (int) (height -ImageHeight/2-margin*0.4), 0X191414);
        if(mode==0){
            this.drawString(fontRenderer, "Well hello there, "+ Minecraft.getInstance().player.getName().getFormattedText() +".", (int) (margin*1.75), height-ImageHeight/2+margin, 0X747474);
            this.drawString(fontRenderer, "It has been a long time since someone has walked", (int) (margin*1.75), height -ImageHeight/2+margin*2, 0X747474);
            this.drawString(fontRenderer, "through the gateway itself and walked this world.", (int) (margin*1.75), height -ImageHeight/2+margin*3, 0X747474);
            this.drawString(fontRenderer, "I am sure you have many questions, ", (int) (margin*1.75), height -ImageHeight/2+margin*4, 0X747474);
            this.drawString(fontRenderer, "but perhaps we should discuss more important matters.", (int) (margin*1.75), height -ImageHeight/2+margin*5, 0X747474);
        }
        if(mode==1) {
            this.drawString(fontRenderer, "What is it that you seek to find here?", (int) (margin*1.75), height -ImageHeight/2+margin, 0X747474);
        }
        if(mode==2) {
            this.drawString(fontRenderer, "There is plenty knowledge to be found in Kathairis,", (int)(margin*1.75), height -ImageHeight/2+margin, 0X747474);
            this.drawString(fontRenderer, "land of pure magic and rich histories.", (int)(margin*1.75), height -ImageHeight/2+margin*2, 0X747474);
            this.drawString(fontRenderer, "Much of this you will find in your", (int)(margin*1.75), height -ImageHeight/2+margin*3, 0X747474);
            this.drawString(fontRenderer, "travels through-out these lands.", (int)(margin*1.75), height -ImageHeight/2+margin*4, 0X747474);

        }
        if(mode==3){
            this.drawString(fontRenderer, "Dominance comes easily to those capable enough", (int)(margin*1.75), height -ImageHeight/2+margin, 0X747474);
            this.drawString(fontRenderer, "to tame these wild lands. Much darkness has", (int)(margin*1.75), height -ImageHeight/2+margin*2, 0X747474);
            this.drawString(fontRenderer, "erupted in past years, and there shall always ", (int)(margin*1.75), height -ImageHeight/2+margin*3, 0X747474);
            this.drawString(fontRenderer, "be plenty of beasts to sate you thirst for", (int)(margin*1.75), height -ImageHeight/2+margin*4, 0X747474);
            this.drawString(fontRenderer, "power and strength.", (int)(margin*1.75), height -ImageHeight/2+margin*5, 0X747474);


        }
        if(mode==4){
            this.drawString(fontRenderer, "The lands you have entered are ones filled with", (int)(margin*1.75), height -ImageHeight/2+margin, 0X747474);
            this.drawString(fontRenderer, "equal expanse as the land you hail from.", (int)(margin*1.75), height -ImageHeight/2+margin*2, 0X747474);
            this.drawString(fontRenderer, "There will be many new discoveries", (int)(margin*1.75), height -ImageHeight/2+margin*3, 0X747474);
            this.drawString(fontRenderer, "and trials that will lie before you,", (int)(margin*1.75), height -ImageHeight/2+margin*4, 0X747474);
            this.drawString(fontRenderer, "and endless stories for you to write of your own adventures.", (int)(margin*1.75), height -ImageHeight/2+margin*5, 0X747474);
        }

        super.render(parWidth, parHeight, particle);
    }


    @Override
    public void onGuiClosed() {
        mode=0;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}