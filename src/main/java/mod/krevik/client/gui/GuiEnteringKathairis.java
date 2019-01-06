package mod.krevik.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnteringKathairis extends GuiScreen
{
    public void initGui()
    {
        this.buttonList.clear();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawBackground(0);
        this.drawCenteredString(this.fontRenderer, I18n.format("welcome.to.the.kathairis!"), this.width / 2, this.height / 2 - 50, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }
}