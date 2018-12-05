package com.Krevik.Gui;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Entities.EntityStrangeWanderer;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketDeathHandlerServer;
import com.Krevik.Networking.PacketSpawnDeathServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiOldMan extends GuiScreen {

	
	Minecraft mc = Minecraft.getMinecraft();
	private final int ImageHeight = 340, ImageWidth = 704, ImageScale = 250;
	private static final ResourceLocation GUITextures = new ResourceLocation("mystic:textures/gui/oldman.png");

	private static int mode=0;
	private GenericButton Next;
	private GenericButton Back;
	private GenericButton Knowledge;
	private GenericButton Power;
	private GenericButton Adventure;
	private EntityPlayer player;
	int margin=15;


	private EntityStrangeWanderer oldman;

	public GuiOldMan(EntityStrangeWanderer entity, EntityPlayer player1){
		oldman=entity;
		player=player1;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		int offLeft = (width - ImageWidth) / 2;
		int offTop = 0;
		buttonList.add(Next = new GenericButton(ImageWidth/2+margin*2, (int)(height-ImageHeight/2-margin+margin), 80, 20, "Next"));
		buttonList.add(Back = new GenericButton(ImageWidth/2+margin*2, (int)(height-ImageHeight/2-margin+margin*3), 60, 20, "Back"));
		buttonList.add(Knowledge = new GenericButton(ImageWidth/2+margin*8, (int)(height-ImageHeight/2-margin+margin), 80, 20, "Knowledge"));
		buttonList.add(Power = new GenericButton(ImageWidth/2+margin*8, (int)(height-ImageHeight/2-margin+margin*3), 60, 20, "Power"));
		buttonList.add(Adventure = new GenericButton(ImageWidth/2+margin*8, (int)(height-ImageHeight/2-margin+margin*5), 60, 20, "Adventure"));

	}

	@Override
	public void updateScreen() {
		Back.visible=true;
		Back.enabled=true;
		Next.visible = false;
		Next.enabled = false;
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
		//Death Screen
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
		GlStateManager.scale(0.8f,0.8f,0.8f);
		fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color);
		GlStateManager.scale(1.25f,1.25,1.25f);
	}

	public void drawCenteredString(String someString,FontRenderer fontRendererIn, String text, int x, int y, int color)
	{
		GlStateManager.scale(0.8f,0.8f,0.8f);
		fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color);
		GlStateManager.scale(1.25f,1.25,1.25f);
	}



	@Override
	public void drawScreen(int parWidth, int parHeight, float particle) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableColorMaterial();
		this.mc.getTextureManager().bindTexture(GUITextures);
		int offLeft = (int) ((200) / 2.0F);
		int offTop = 0;
		int scale = Minecraft.getMinecraft().gameSettings.guiScale;
		drawModalRectWithCustomSizedTexture(margin, height-ImageHeight/2-margin, 0, 0, ImageWidth/2,ImageHeight/2,ImageWidth/2,ImageHeight/2);
		//fontRenderer.drawString("Welcome to the Kathairis!", (int) (width / 2.0) - 55, 20, 0X353f51);
		this.drawString(fontRenderer, "Mysterious Stranger", (int)(margin*1.65), (int) ((int)height-ImageHeight/2-margin*0.4), 0X191414);
		if(mode==0){
			this.drawString(fontRenderer, "Well hello there, "+ player.getName() +".", (int) (margin*1.75), height-ImageHeight/2+margin, 0X747474);
			this.drawString(fontRenderer, "It has been a long time since someone has walked", (int) (margin*1.75), (int)height-ImageHeight/2+margin*2, 0X747474);
			this.drawString(fontRenderer, "through the gateway itself and walked this world.", (int) (margin*1.75), (int)height-ImageHeight/2+margin*3, 0X747474);
			this.drawString(fontRenderer, "I am sure you have many questions, ", (int) (margin*1.75), (int)height-ImageHeight/2+margin*4, 0X747474);
			this.drawString(fontRenderer, "but perhaps we should discuss more important matters.", (int) (margin*1.75), (int)height-ImageHeight/2+margin*5, 0X747474);
		}
		if(mode==1) {
			this.drawString(fontRenderer, "What is it that you seek to find here?", (int) (margin*1.75), (int)height-ImageHeight/2+margin, 0X747474);
		}
		if(mode==2) {
			this.drawString(fontRenderer, "There is plenty knowledge to be found in Kathairis,", (int)(margin*1.75), (int)height-ImageHeight/2+margin, 0X747474);
			this.drawString(fontRenderer, "land of pure magic and rich histories.", (int)(margin*1.75), (int)height-ImageHeight/2+margin*2, 0X747474);
			this.drawString(fontRenderer, "Much of this you will find in your", (int)(margin*1.75), (int)height-ImageHeight/2+margin*3, 0X747474);
			this.drawString(fontRenderer, "travels through-out these lands.", (int)(margin*1.75), (int)height-ImageHeight/2+margin*4, 0X747474);

		}
		if(mode==3){
			this.drawString(fontRenderer, "Dominance comes easily to those capable enough", (int)(margin*1.75), (int)height-ImageHeight/2+margin, 0X747474);
			this.drawString(fontRenderer, "to tame these wild lands. Much darkness has", (int)(margin*1.75), (int)height-ImageHeight/2+margin*2, 0X747474);
			this.drawString(fontRenderer, "erupted in past years, and there shall always ", (int)(margin*1.75), (int)height-ImageHeight/2+margin*3, 0X747474);
			this.drawString(fontRenderer, "be plenty of beasts to sate you thirst for", (int)(margin*1.75), (int)height-ImageHeight/2+margin*4, 0X747474);
			this.drawString(fontRenderer, "power and strength.", (int)(margin*1.75), (int)height-ImageHeight/2+margin*5, 0X747474);


		}
		if(mode==4){
			this.drawString(fontRenderer, "The lands you have entered are ones filled with", (int)(margin*1.75), (int)height-ImageHeight/2+margin, 0X747474);
			this.drawString(fontRenderer, "equal expanse as the land you hail from.", (int)(margin*1.75), (int)height-ImageHeight/2+margin*2, 0X747474);
			this.drawString(fontRenderer, "There will be many new discoveries", (int)(margin*1.75), (int)height-ImageHeight/2+margin*3, 0X747474);
			this.drawString(fontRenderer, "and trials that will lie before you,", (int)(margin*1.75), (int)height-ImageHeight/2+margin*4, 0X747474);
			this.drawString(fontRenderer, "and endless stories for you to write of your own adventures.", (int)(margin*1.75), (int)height-ImageHeight/2+margin*5, 0X747474);
		}

		super.drawScreen(parWidth, parHeight, particle);
	}
	@Override
	protected void keyTyped(char typedChar, int keyCode){
	if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
		Minecraft.getMinecraft().player.closeScreen();			

	}
		
	}
	
	@Override
	protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) { }

	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == Next){
			if(Next.visible&&Next.enabled) {
				mode=1;
			}
		}
		if(button == Back){
				if(mode==0) {
					Minecraft.getMinecraft().player.closeScreen();
				}
				if(mode==1){
					mode=0;
				}
				if(mode==2||mode==3||mode==4){
					mode=1;
				}
		}
		if(button==Knowledge){
			mode=2;
		}
		if(button==Power){
			mode=3;
		}
		if(button==Adventure){
			mode=4;
		}
		/*if(button == Death){
			if(Death.visible&&Death.enabled) {
				mode=2;
			}
		}
		if(button == Fight){
			
			IMessage message1 = new PacketSpawnDeathServer();
			KetherPacketHandler.CHANNEL.sendToServer(message1);

				IMessage message2 = new PacketDeathHandlerServer(true,false,false);
				KetherPacketHandler.CHANNEL.sendToServer(message2);

		}*/
		
	}

	@Override
	public void onGuiClosed() {


		mode=0;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@SideOnly(Side.CLIENT)
   	static class GenericButton extends GuiButton{
		public GenericButton(int x, int y, int width, int height, String text) {
			super(1, x, y, width, height, text);
		}
	}
}