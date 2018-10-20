package com.Krevik.Gui;

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
		buttonList.add(Next = new GenericButton(ImageWidth/4+margin*2, (int)(ImageHeight/2-margin), 80, 20, "Next"));
		buttonList.add(Back = new GenericButton(ImageWidth/4+margin*2, (int)(ImageHeight/2+margin/2), 60, 20, "Back"));
		buttonList.add(Knowledge = new GenericButton(ImageWidth/4+margin*8, (int)(ImageHeight/2-margin), 80, 20, "Knowledge"));
		buttonList.add(Power = new GenericButton(ImageWidth/4+margin*8, (int)(ImageHeight/2+margin/2), 60, 20, "Power"));
		buttonList.add(Adventure = new GenericButton(ImageWidth/4+margin*8, (int)(ImageHeight/2+margin*2), 60, 20, "Adventure"));

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
		GlStateManager.scale(0.4f,0.4f,0.4f);
		fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color);
		GlStateManager.scale(2.5f,2.5f,2.5f);
	}

	public void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color)
	{
		GlStateManager.scale(0.4f,0.4f,0.4f);
		fontRendererIn.drawString(text, (int)x, (int)y, color);
		GlStateManager.scale(2.5f,2.5f,2.5f);
	}

	public void drawCenteredString(String someString,FontRenderer fontRendererIn, String text, int x, int y, int color)
	{
		GlStateManager.scale(0.4f,0.4f,0.4f);
		fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color);
		GlStateManager.scale(2.5f,2.5f,2.5f);
	}



	@Override
	public void drawScreen(int parWidth, int parHeight, float particle) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableColorMaterial();
		this.mc.getTextureManager().bindTexture(GUITextures);
		int offLeft = (int) ((200) / 2.0F);
		int offTop = 0;
		drawModalRectWithCustomSizedTexture(margin, ImageHeight/2-15, 0, 0, ImageWidth/4,ImageHeight/4,ImageWidth/4,ImageHeight/4);
		//fontRenderer.drawString("Welcome to the Kathairis!", (int) (width / 2.0) - 55, 20, 0X353f51);
		this.drawCenteredString("someString", fontRenderer, "Mysterious Stranger", (int) ImageWidth/10+(int)(margin*2f), (int)(ImageHeight*1.18f), 0X191414);

		if(mode==0){
			this.drawString(fontRenderer, "Well hello there, "+ player.getName() +". It has been a long time since someone ", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f), 0X747474);
			this.drawString(fontRenderer, "has walked through the gateway itself and walked this world. I am sure", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin), 0X747474);
			this.drawString(fontRenderer, "you have many questions, but perhaps we should discuss more important", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin*2), 0X747474);
			this.drawString(fontRenderer, "matters.", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin*3), 0X747474);
		}
		if(mode==1) {
			this.drawCenteredString(fontRenderer, "What is it that you seek to find here?", (int) ImageWidth/3+(int)(margin), (int)(ImageHeight*1.25f), 0X747474);
		}
		if(mode==2) {
			this.drawString(fontRenderer, "There is plenty knowledge to be found in Kathairis, land of pure", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f), 0X747474);
			this.drawString(fontRenderer, "magic and rich histories. Much of this you will find in your travels", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin), 0X747474);
			this.drawString(fontRenderer, "through-out these lands.", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin*2), 0X747474);
		}
		if(mode==3){
			this.drawString(fontRenderer, "Dominance comes easily to those capable enough to tame these wild", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f), 0X747474);
			this.drawString(fontRenderer, "lands. Much darkness has erupted in past years, and there shall always", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin), 0X747474);
			this.drawString(fontRenderer, "be plenty of beasts to sate you thirst for power and strength.", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin*2), 0X747474);
		}
		if(mode==4){
			this.drawString(fontRenderer, "The lands you have entered are ones filled with equal expanse as the", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f), 0X747474);
			this.drawString(fontRenderer, "land you hail from. There will be many new discoveries and trials that", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin), 0X747474);
			this.drawString(fontRenderer, "will lie before you, and endless stories for you to write of your own", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin*2), 0X747474);
			this.drawString(fontRenderer, "adventures.", (int) ImageWidth/10+(int)(margin), (int)(ImageHeight*1.25f+margin*3), 0X747474);
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