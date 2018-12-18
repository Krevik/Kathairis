package mod.krevik.Gui;

import org.lwjgl.opengl.GL11;

import mod.krevik.Entities.EntityDeath;
import mod.krevik.Networking.KetherPacketHandler;
import mod.krevik.Networking.PacketDeathHandlerServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiDeath extends GuiScreen {

	
	Minecraft mc = Minecraft.getMinecraft();
	private final int ImageHeight = 250, ImageWidth = 250, ImageScale = 250;
	private static final ResourceLocation GUITextures = new ResourceLocation("mystic:textures/gui/death.png");
	private static int mode=0;
	private GenericButton Back;
	private GenericButton Fight;
	private EntityDeath death;

	public GuiDeath(EntityDeath dh){
		death=dh;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		int offLeft = (width - ImageWidth) / 2;
		int offTop = 0;
		buttonList.add(Back = new GenericButton(1,162-7, 78+75, 10, 10, "Back"));
		buttonList.add(Fight = new GenericButton(1,162-7, 108+100, 10, 10, "Fight!"));
	}

	@Override
	public void updateScreen() {
		Back.visible=true;
		Back.enabled=true;
		Fight.visible=true;
		Fight.enabled=true;
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
		fontRenderer.drawString("I've been waiting for you mortal!", (int) (width / 2.0) - 70, 20, 0Xe6e6e6); 

		super.drawScreen(parWidth, parHeight, particle);
	}
	@Override
	protected void keyTyped(char typedChar, int keyCode){
	if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode())
		Minecraft.getMinecraft().player.closeScreen();			
	}
	@Override
	protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) { }

	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == Back){
			Minecraft.getMinecraft().player.closeScreen();			
		}
		if(button == Fight) {
			IMessage message = new PacketDeathHandlerServer(true,true,false);
				KetherPacketHandler.CHANNEL.sendToServer(message);
				Minecraft.getMinecraft().player.closeScreen();			
		}

		
		
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		mode=0;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@SideOnly(Side.CLIENT)
   	static class GenericButton extends GuiButton{
	    /** Button width in pixels */
	    public int width;
	    /** Button height in pixels */
	    public int height;
	    /** The x position of this control. */
	    public int x;
	    /** The y position of this control. */
	    public int y;
	    /** The string displayed on this control. */
	    public String displayString;
	    public int id;
	    /** True if this control is enabled, false to disable. */
	    public boolean enabled;
	    /** Hides the button completely if false. */
	    public boolean visible;
	    protected boolean hovered;
	    public int packedFGColour; //FML

	    public GenericButton(int buttonId, int x, int y, String buttonText)
	    {
	        super(buttonId, x, y, 162, 30, buttonText);
	    }

	    public GenericButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
	    {
	    	super(buttonId, x, y, 162, 30, buttonText);
	        this.width = 162;
	        this.height = 30;
	        this.enabled = true;
	        this.visible = true;
	        this.id = buttonId;
	        this.x = x;
	        this.y = y;
	        this.displayString = buttonText;
	    }

	    /**
	     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
	     * this button.
	     */
	    protected int getHoverState(boolean mouseOver)
	    {
	        int i = 1;

	        if (!this.enabled)
	        {
	            i = 0;
	        }
	        else if (mouseOver)
	        {
	            i = 2;
	        }

	        return i;
	    }

	    /**
	     * Draws this button to the screen.
	     */
	    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	    {
	        if (this.visible)
	        {
	            FontRenderer fontrenderer = mc.fontRenderer;
	            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	            int i = this.getHoverState(this.hovered);
	            GlStateManager.enableBlend();
	            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	            this.mouseDragged(mc, mouseX, mouseY);
	            int j = 14737632;

	            if (packedFGColour != 0)
	            {
	                j = packedFGColour;
	            }
	            else
	            if (!this.enabled)
	            {
	                j = 10526880;
	            }
	            else if (this.hovered)
	            {
	                j = 16777120;
	            }

	            this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
	        }
	    }

	    /**
	     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
	     */
	    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
	    {
	    }

	    /**
	     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
	     */
	    public void mouseReleased(int mouseX, int mouseY)
	    {
	    }

	    /**
	     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
	     * e).
	     */
	    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
	    {
	        return this.enabled && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	    }

	    /**
	     * Whether the mouse cursor is currently over the button.
	     */
	    public boolean isMouseOver()
	    {
	        return this.hovered;
	    }

	    public void drawButtonForegroundLayer(int mouseX, int mouseY)
	    {
	    }

	    public void playPressSound(SoundHandler soundHandlerIn)
	    {
	        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	    }

	    public int getButtonWidth()
	    {
	        return this.width;
	    }

	    public void setWidth(int width)
	    {
	        this.width = width;
	    }
	}
}