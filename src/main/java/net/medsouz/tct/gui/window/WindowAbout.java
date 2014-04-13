package net.medsouz.tct.gui.window;

import java.awt.Desktop;
import java.net.URI;
import cpw.mods.fml.common.Loader;
import net.medsouz.tct.TinCanTelephony;
import net.medsouz.tct.gui.GuiOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * @author medsouz
 *
 */
public class WindowAbout extends Window {

	GuiButton github = new GuiButton(1, 0, 0, 80, 20, "Github");
	GuiButton support = new GuiButton(1, 0, 0, 80, 20, "Chat");
	
	public WindowAbout(GuiOverlay g, int x, int y) {
		super(g, "About", x, y, 175, 110);
		buttonList.add(github);
		buttonList.add(support);
	}

	@Override
	public void drawWindowContents() {
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "TinCanTelephony's goal is to", xPos + 5, yPos + 5, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "create a social platform inside", xPos + 5, yPos + 15, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "Minecraft. If you would like to", xPos + 5, yPos + 25, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "help develop the mod you can", xPos + 5, yPos + 35, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "visit our Github page. If you need", xPos + 5, yPos + 45, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "help feel free to visit our chat.", xPos + 5, yPos + 55, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "TCT v\u00A7l"+TinCanTelephony.VERSION+"\u00A7f for \u00A7l"+Loader.instance().getMCVersionString()+"\u00A7f", xPos + 5, yPos + height - 35, 0xFFFFFF);
		github.xPosition = xPos + 5;
		github.yPosition = yPos + height - 25;
		support.xPosition = xPos + 90;
		support.yPosition = yPos + height - 25;
	}

	@Override
	public void onClose() {
		
	}

	@Override
	public void onButtonPress(GuiButton button) {
		if(button == github) {
			openURL("https://github.com/medsouz/TinCanTelephony");
		}
		if (button == support ) {
			openURL("http://webchat.esper.net/?channels=tct");
		}
	}

	@Override
	public void keyTyped(char c, int id) {
		
	}
	
	public void openURL(String URL) {
		try {
			Desktop.getDesktop().browse(new URI(URL));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
