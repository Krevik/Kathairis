package io.github.krevik.kathairis.client.gui;


import com.mojang.blaze3d.platform.GlStateManager;
import io.github.krevik.kathairis.util.networking.PacketHandler;
import io.github.krevik.kathairis.util.networking.packets.PacketServerGivePlayerEthereal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import static io.github.krevik.kathairis.util.ModReference.MOD_ID;

@OnlyIn(Dist.CLIENT)
public class GuiOldMan extends Screen {


    Minecraft mc = Minecraft.getInstance();
    private int shifter1 = 200, ImageWidth = 704, ImageScale = 250;
    private static final ResourceLocation GUITextures = new ResourceLocation(MOD_ID,"textures/gui/oldman.png");
    private static int mode=0;
    private Button Next;
    private Button Back;
    private Button Knowledge;
    private Button Power;
    private Button Adventure;
    private Button I_Want_Ethereal;
    int margin=15;
    private String guiTitle;
    private String nextButtonTitle;
    private String backButtonTitle;
    private String knowledge_Button_Title;
    private String power_Button_Title;
    private String adventure_Button_Title;
    private String i_Want_Ethereal_Button_Title;
    private String hello_Player_Title;
    private String asked_question;
    private String[] main_page_lines=new String[8];
    private String[] adventure_lines=new String[8];
    private String[] power_lines=new String[8];
    private String[] knowledge_lines=new String[8];
    private String[] i_want_ethereal_lines=new String[8];
    private int guiScale;

    public GuiOldMan(){
        super(new StringTextComponent("kathairis:gui_old_man"));

    }

    @Override
    protected void init() {
        super.init();
        this.minecraft.keyboardListener.enableRepeatEvents(true);
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
        for(int i=0;i<=7;i++){
            i_want_ethereal_lines[i]=I18n.format("gui.old_man.i_want_ethereal_line_"+i);
        }
        asked_question=I18n.format("gui.old_man.asked_question");
        hello_Player_Title=I18n.format("gui.old_man.hello_player_title");
        nextButtonTitle=I18n.format("gui.next_button_title");
        backButtonTitle=I18n.format("gui.back_button_title");
        knowledge_Button_Title=I18n.format("gui.old_man.button_title.knowledge");
        power_Button_Title=I18n.format("gui.old_man.button_title.power");
        adventure_Button_Title=I18n.format("gui.old_man.button_title.adventure");
        i_Want_Ethereal_Button_Title=I18n.format("gui.old_man.button_title.i_want_ethereal");

        mode=0;
        buttons.clear();
        this.Next = this.addButton(new Button(this.width / 2 - 100,280, 98, 20, nextButtonTitle, (p_214274_1_) -> {
            this.setMode(1);
        }));
        this.Back = this.addButton(new Button(this.width / 2,280, 98, 20, backButtonTitle, (p_214274_1_) -> {
            if(mode==0) {
                Minecraft.getInstance().player.closeScreen();
            }
            if(mode==1){
                GuiOldMan.this.setMode(0);
            }
            if(mode==2||mode==3||mode==4){
                GuiOldMan.this.setMode(1);
            }
            if(mode==5){
                Minecraft.getInstance().player.closeScreen();
            }
        }));
        this.Knowledge = this.addButton(new Button(this.width / 2 + 100,100, 98, 20, knowledge_Button_Title, (p_214274_1_) -> {
            GuiOldMan.this.setMode(2);
        }));
        this.Power = this.addButton(new Button(this.width / 2 + 100,120, 98, 20,  power_Button_Title, (p_214274_1_) -> {
            GuiOldMan.this.setMode(3);
        }));
        this.Adventure = this.addButton(new Button(this.width / 2 + 100,140, 98, 20,  adventure_Button_Title, (p_214274_1_) -> {
            GuiOldMan.this.setMode(4);
        }));
        this.I_Want_Ethereal = this.addButton(new Button(this.width / 2 + 150,160, 98, 20, i_Want_Ethereal_Button_Title, (p_214274_1_) -> {
            GuiOldMan.this.setMode(5);
            PacketHandler.sendToServer(new PacketServerGivePlayerEthereal());
        }));
    }

    public void setMode(int mode1){
        mode=mode1;
    }

    @Override
    public void tick() {
        guiScale=mc.gameSettings.guiScale;
        if(guiScale==1){
            shifter1=900;
            Back.y=280;
            Next.y=280;
        }
        if(guiScale==2){
            shifter1=350;
            Back.y=280;
            Next.y=280;
        }
        if(guiScale==3){
            shifter1=150;
            Back.y=280;
            Next.y=280;
        }
        if(guiScale==4){
            shifter1=200;
            Back.y=150;
            Next.y=150;
        }
        Back.visible=true;
        Back.active=true;
        Knowledge.visible=false;
        Knowledge.active=false;
        Power.visible=false;
        Power.active=false;
        Adventure.visible=false;
        Adventure.active=false;
        I_Want_Ethereal.visible=false;
        I_Want_Ethereal.active=false;
        //main Screen
        if(mode==0) {
            Next.visible = true;
            Next.active = true;
        }
        //Lets_start screen
        if(mode==1) {
            Next.visible = false;
            Next.active = false;
            Knowledge.visible=true;
            Knowledge.active=true;
            Power.visible=true;
            Power.active=true;
            Adventure.visible=true;
            Adventure.active=true;
        }

        if(mode==2||mode==3||mode==4) {
            Next.visible = false;
            Next.active = false;
            Knowledge.visible=false;
            Knowledge.active=false;
            Power.visible=false;
            Power.active=false;
            Adventure.visible=false;
            Adventure.active=false;
            if(mode==3){
                I_Want_Ethereal.visible=true;
                I_Want_Ethereal.active=true;
            }
        }

        if(mode==5){
            I_Want_Ethereal.visible=false;
            I_Want_Ethereal.active=false;
            Next.visible = false;
            Next.active = false;
            Knowledge.visible=false;
            Knowledge.active=false;
            Power.visible=false;
            Power.active=false;
            Adventure.visible=false;
            Adventure.active=false;
        }
    }

    @Override
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color)
    {
        fontRendererIn.drawString(text, (width/2-fontRendererIn.getStringWidth(text)/2), y, color);
    }

    @Override
    public void render(int parWidth, int parHeight, float particle) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableColorMaterial();
        //this.mc.getTextureManager().bindTexture(GUITextures);
        this.renderBackground();
        //TODO //drawModalRectWithCustomSizedTexture(margin, height-shifter1/2-margin, 0, 0, ImageWidth/2,shifter1/2,ImageWidth/2,shifter1/2);
        this.drawCenteredString(this.font, guiTitle, (int)(0), (int) (height - shifter1 - margin*0.4), 0XFFFFFF);
        if(mode==0){
            this.drawCenteredString(this.font, hello_Player_Title +", "+ Minecraft.getInstance().player.getName().getFormattedText() +".", (int) (0), height- shifter1 +margin, 0X747474);
            for(int i=2;i<=9;i++){
                this.drawCenteredString(this.font, main_page_lines[i-2], (int) (0), height - shifter1 +margin*i, 0X747474);
            }
        }
        if(mode==1) {
            this.drawCenteredString(this.font, asked_question, (int) (0), height - shifter1 +margin, 0X747474);
        }
        if(mode==2) {
            for(int i=1;i<=8;i++){
                this.drawCenteredString(this.font, knowledge_lines[i-1], (int) (0), height - shifter1 +margin*i, 0X747474);
            }
        }
        if(mode==3){
            for(int i=1;i<=8;i++){
                this.drawCenteredString(this.font, power_lines[i-1], (int) (0), height - shifter1 +margin*i, 0X747474);
            }
        }
        if(mode==4){
            for(int i=1;i<=8;i++){
                this.drawCenteredString(this.font, adventure_lines[i-1], (int) (0), height - shifter1 +margin*i, 0X747474);
            }
        }
        if(mode==5){
            for(int i=1;i<=8;i++){
                this.drawCenteredString(this.font, i_want_ethereal_lines[i-1], (int) (0), height - shifter1 +margin*i, 0X747474);
            }
        }
        super.render(parWidth, parHeight, particle);
    }

    @Override
    public void onClose() {
        mode=0;
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}