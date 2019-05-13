package io.github.krevik.kathairis.client.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
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
    private String guiTitle;
    private String nextButtonTitle;
    private String backButtonTitle;
    private String knowledge_Button_Title;
    private String power_Button_Title;
    private String adventure_Button_Title;
    private String hello_Player_Title;
    private String asked_question;
    private String[] main_page_lines=new String[8];
    private String[] adventure_lines=new String[8];
    private String[] power_lines=new String[8];
    private String[] knowledge_lines=new String[8];

    public GuiOldMan(){
    }

    public void setMode(int mode1){
        mode=mode1;
    }

    @Override
    public void initGui() {
        guiTitle=I18n.format("gui.old_man.title");
        for(int i=0;i<=7;i++){
            main_page_lines[i]=I18n.format("gui.old_man.main_page.line_"+i);
        }
        for(int i=0;i<=7;i++){
            power_lines[i]=I18n.format("gui.old_man.power_line_"+i);
        }
        for(int i=0;i<=7;i++){
            knowledge_lines[i]=I18n.format("gui.old_man.knowledge_line_"+i);
        }
        for(int i=0;i<=7;i++){
            adventure_lines[i]=I18n.format("gui.old_man.adventure_line_"+i);
        }
        asked_question=I18n.format("gui.old_man.asked_question");
        hello_Player_Title=I18n.format("gui.old_man.hello_player_title");
        nextButtonTitle=I18n.format("gui.next_button_title");
        backButtonTitle=I18n.format("gui.back_button_title");
        knowledge_Button_Title=I18n.format("gui.old_man.button_title.knowledge");
        power_Button_Title=I18n.format("gui.old_man.button_title.power");
        adventure_Button_Title=I18n.format("gui.old_man.button_title.adventure");

        mode=0;
        buttons.clear();
        this.addButton(Next = new GuiButton(1,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin), 80, 20, nextButtonTitle){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                   GuiOldMan.this.setMode(1);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Back = new GuiButton(2,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin*9), 60, 20, backButtonTitle){
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
        this.addButton(Knowledge = new GuiButton(3,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin*3), 80, 20, knowledge_Button_Title){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiOldMan.this.setMode(2);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Power = new GuiButton(4,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin*5), 60, 20, power_Button_Title){
            @Override
            public void onClick(double p_194829_1_, double p_194829_3_) {
                GuiOldMan.this.setMode(3);
                super.onClick(p_194829_1_, p_194829_3_);
            }
        });
        this.addButton(Adventure = new GuiButton(5,ImageWidth/2+margin*2, (height-ImageHeight/2-margin+margin*7), 60, 20, adventure_Button_Title){
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
        this.drawString(fontRenderer, guiTitle, (int)(margin*1.65), (int) (height -ImageHeight/2-margin*0.4), 0X191414);
        if(mode==0){
            this.drawString(fontRenderer, hello_Player_Title +", "+ Minecraft.getInstance().player.getName().getFormattedText() +".", (int) (margin*1.75), height-ImageHeight/2+margin, 0X747474);
            for(int i=2;i<=9;i++){
                this.drawString(fontRenderer, main_page_lines[i-2], (int) (margin*1.75), height -ImageHeight/2+margin*i, 0X747474);
            }
        }
        if(mode==1) {
            this.drawString(fontRenderer, asked_question, (int) (margin*1.75), height -ImageHeight/2+margin, 0X747474);
        }
        if(mode==2) {
            for(int i=1;i<=8;i++){
                this.drawString(fontRenderer, knowledge_lines[i-1], (int) (margin*1.75), height -ImageHeight/2+margin*i, 0X747474);
            }
        }
        if(mode==3){
            for(int i=1;i<=8;i++){
                this.drawString(fontRenderer, power_lines[i-1], (int) (margin*1.75), height -ImageHeight/2+margin*i, 0X747474);
            }
        }
        if(mode==4){
            for(int i=1;i<=8;i++){
                this.drawString(fontRenderer, adventure_lines[i-1], (int) (margin*1.75), height -ImageHeight/2+margin*i, 0X747474);
            }
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