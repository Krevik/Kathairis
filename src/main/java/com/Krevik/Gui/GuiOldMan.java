package com.Krevik.Gui;

import org.lwjgl.opengl.GL11;

import com.Krevik.Dimension.KetherDataStorage;
import com.Krevik.Entities.EntityStrangeWanderer;
import com.Krevik.Main.KCore;
import com.Krevik.Networking.KetherPacketHandler;
import com.Krevik.Networking.PacketDeathHandlerServer;
import com.Krevik.Networking.PacketGivePlayerLightSword;
import com.Krevik.Networking.PacketSpawnDeathServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiOldMan extends GuiScreen {

	
	Minecraft mc = Minecraft.getMinecraft();
	private final int ImageHeight = 250, ImageWidth = 250, ImageScale = 250;
	private static final ResourceLocation GUITextures = new ResourceLocation("mystic:textures/gui/oldman.png");

	private static int mode=0;
	private GenericButton Lets_Start;
	private GenericButton Back;
	private GenericButton Death;
	private GenericButton Fight;

	private EntityStrangeWanderer oldman;

	public GuiOldMan(EntityStrangeWanderer entity){
		oldman=entity;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		int offLeft = (width - ImageWidth) / 2;
		int offTop = 0;
		buttonList.add(Lets_Start = new GenericButton(offLeft + (145 - 140) + (60 / 3)-20, 42 + offTop, 80, 20, "Where am I?"));
		buttonList.add(Back = new GenericButton(offLeft + (145 - 140) + (60 / 3)+65, 42 + offTop+180, 60, 20, "Back"));
		buttonList.add(Death = new GenericButton(offLeft + (145 - 140) + (60 / 3)+65, 42 + offTop, 80, 20, "Death?"));
		buttonList.add(Fight = new GenericButton(offLeft + (145 - 140) + (60 / 3)+65, 42 + offTop+155, 60, 20, "Fight!"));

	}

	@Override
	public void updateScreen() {
		Back.visible=true;
		Back.enabled=true;
		Lets_Start.visible = false;
		Lets_Start.enabled = false;
		Death.visible=false;
		Death.enabled=false;
		Fight.visible=false;
		Fight.enabled=false;
		//main Screen
		if(mode==0) {
			Lets_Start.visible = true;
			Lets_Start.enabled = true;
			Death.visible=true;
			Death.enabled=true;
		}
		//Lets_start screen
		if(mode==1) {
			Lets_Start.visible = false;
			Lets_Start.enabled = false;
		}
		//Death Screen
		if(mode==2) {
			if(!KetherDataStorage.getDataInstance(mc.player.world).getIsDeathSpawned()&&!KetherDataStorage.getDataInstance(mc.player.world).getIsDeathDefeated()) {
				Fight.visible=true;
				Fight.enabled=true;
			}
		}
	}
	
    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color)
    {
        fontRendererIn.drawString(text, (x - fontRendererIn.getStringWidth(text) / 2), y, color);
    }

	@Override
	public void drawScreen(int parWidth, int parHeight, float particle) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableColorMaterial();
		this.mc.getTextureManager().bindTexture(GUITextures);

		int offLeft = (int) ((width - ImageWidth) / 2.0F);
		int offTop = 0;
		drawModalRectWithCustomSizedTexture(offLeft, offTop, 0, 0, ImageScale,ImageScale,ImageScale,ImageScale);
		fontRenderer.drawString("Welcome to the Kathairis!", (int) (width / 2.0) - 55, 20, 0X353f51); 

		if(mode==1) {
			this.drawCenteredString(fontRenderer, "I went through mountains,", (int) (width / 2.0), 30, 0Xbfbfbf);
			this.drawCenteredString(fontRenderer, "I went through villages and rivers", (int) (width / 2.0), 40, 0Xbfbfbf);
			this.drawCenteredString(fontRenderer, "I've been in Nether and End", (int) (width / 2.0), 50, 0Xbfbfbf);
			this.drawCenteredString(fontRenderer, "I've fought with dragons", (int) (width / 2.0), 60, 0Xbfbfbf);
			this.drawCenteredString(fontRenderer, "I've got to Heaven", (int) (width / 2.0), 70, 0Xbfbfbf);
			this.drawCenteredString(fontRenderer, "Now I am here as you are", (int) (width / 2.0), 80, 0Xbfbfbf);
			this.drawCenteredString(fontRenderer, "Kathairis - our destiny", (int) (width / 2.0), 90, 0Xbfbfbf);
			}
		if(mode==2) {
			if(!KetherDataStorage.getDataInstance(mc.player.world).getIsDeathSpawned()&&!KetherDataStorage.getDataInstance(mc.player.world).getIsDeathDefeated()) {
				this.drawCenteredString(fontRenderer, "The Death have reached our land", (int) (width / 2.0), 30, 0X00253D);
				this.drawCenteredString(fontRenderer, "Probably because of me, cause", (int) (width / 2.0), 40, 0X00253D);
				this.drawCenteredString(fontRenderer, "I cheated her once", (int) (width / 2.0), 50, 0X00253D);
				this.drawCenteredString(fontRenderer, "You must send her back!", (int) (width / 2.0), 60, 0X00253D);
				this.drawCenteredString(fontRenderer, "Take this sword of light!", (int) (width / 2.0), 70, 0X00253D);
				this.drawCenteredString(fontRenderer, "She's at: X:666, Z:666", (int) (width / 2.0), 80, 0X00253D);
			}
			if(KetherDataStorage.getDataInstance(mc.player.world).getIsDeathSpawned()&&!KetherDataStorage.getDataInstance(mc.player.world).getIsDeathDefeated()) {
				this.drawCenteredString(fontRenderer, "Hurry up! Do it!", (int) (width / 2.0), 30, 0X00253D);
				this.drawCenteredString(fontRenderer, "She's at: X:666, Z:666", (int) (width / 2.0), 40, 0X00253D);
			}
			if(KetherDataStorage.getDataInstance(mc.player.world).getIsDeathSpawned()&&KetherDataStorage.getDataInstance(mc.player.world).getIsDeathDefeated()) {
				this.drawCenteredString(fontRenderer, "Thanks god you did it!", (int) (width / 2.0), 30, 0X00253D);
			}
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
		if(button == Lets_Start){
			if(Lets_Start.visible&&Lets_Start.enabled) {
				mode=1;
			}
		}
		if(button == Back){
			if(mode==0) {
				Minecraft.getMinecraft().player.closeScreen();			
				}
			if(mode==1) {
				mode=0;
			}
			if(mode==2) {
				mode=0;
			}
		}
		if(button == Death){
			if(Death.visible&&Death.enabled) {
				mode=2;
			}
		}
		if(button == Fight){
			IMessage message = new PacketGivePlayerLightSword();
			KetherPacketHandler.CHANNEL.sendToServer(message);
			
			IMessage message1 = new PacketSpawnDeathServer();
			KetherPacketHandler.CHANNEL.sendToServer(message1);

				IMessage message2 = new PacketDeathHandlerServer(true,false,false);
				KetherPacketHandler.CHANNEL.sendToServer(message2);

		}
		
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