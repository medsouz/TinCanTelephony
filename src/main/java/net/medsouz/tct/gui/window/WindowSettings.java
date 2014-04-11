package net.medsouz.tct.gui.window;

import net.medsouz.tct.TinCanTelephony;
import net.medsouz.tct.gui.GuiOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * Settings window
 * 
 * @author Ri5ux
 * 
 */
public class WindowSettings extends Window {

	GuiButton button1 = new GuiButton(0, 0, 0, 80, 20, "Public"),
			button2 = new GuiButton(1, 0, 0, 80, 20, "Private"),
			button3 = new GuiButton(2, 0, 0, 80, 20, "Public"),
			button4 = new GuiButton(3, 0, 0, 80, 20, "Private"),
			button5 = new GuiButton(4, 0, 0, 80, 20, "Public"),
			button6 = new GuiButton(5, 0, 0, 80, 20, "Private"),
			button7 = new GuiButton(6, 0, 0, 80, 20, "Classic"),
			button8 = new GuiButton(7, 0, 0, 80, 20, "Clear");

	public WindowSettings(GuiOverlay g, String t, int x, int y, int w, int h) {
		super(g, "Settings", x, y, w, h);
		buttonList.add(button1);
		buttonList.add(button2);
		buttonList.add(button3);
		buttonList.add(button4);
		buttonList.add(button5);
		buttonList.add(button6);
		buttonList.add(button7);
		buttonList.add(button8);

		button1.enabled = false;
		button3.enabled = false;
		button5.enabled = false;
		button7.enabled = false;
	}

	@Override
	public void drawWindowContents() {
		button1.xPosition = xPos + 5;
		button1.yPosition = yPos + 20;

		button2.xPosition = xPos + 89;
		button2.yPosition = yPos + 20;

		button3.xPosition = xPos + 5;
		button3.yPosition = yPos + 55;

		button4.xPosition = xPos + 89;
		button4.yPosition = yPos + 55;

		button5.xPosition = xPos + 5;
		button5.yPosition = yPos + 90;

		button6.xPosition = xPos + 89;
		button6.yPosition = yPos + 90;
		
		button7.xPosition = xPos + 5;
		button7.yPosition = yPos + 125;

		button8.xPosition = xPos + 89;
		button8.yPosition = yPos + 125;

		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Private Messages", xPos + 6, yPos + 8, 0xFFFFFF);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Current Server", xPos + 6, yPos + 43, 0xFFFFFF);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Server Invites", xPos + 6, yPos + 78, 0xFFFFFF);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Theme", xPos + 6, yPos + 113, 0xFFFFFF);
		
		if (TinCanTelephony.curTheme == TinCanTelephony.properties.THEME_CLASSIC)
		{
			button7.enabled = false;
			button8.enabled = true;
		}
		
		if (TinCanTelephony.curTheme == TinCanTelephony.properties.THEME_CLEAR)
		{
			button7.enabled = true;
			button8.enabled = false;
		}
	}

	@Override
	public void onClose() {

	}

	@Override
	public void onButtonPress(GuiButton button) {
		if (button.id == 0) {
			System.out.println("Anyone can send me messages!");
			button1.enabled = false;
			button2.enabled = true;
		}
		if (button.id == 1) {
			System.out.println("Only friends can send me messages!");
			button1.enabled = true;
			button2.enabled = false;
		}
		if (button.id == 2) {
			System.out.println("Anyone can see what server I'm on!");
			button3.enabled = false;
			button4.enabled = true;
		}
		if (button.id == 3) {
			System.out.println("Only friends can see what server I'm on!");
			button3.enabled = true;
			button4.enabled = false;
		}
		if (button.id == 4) {
			System.out.println("Anyone can invite me to a server!");
			button5.enabled = false;
			button6.enabled = true;
		}
		if (button.id == 5) {
			System.out.println("Only friends can invite me to a server!");
			button5.enabled = true;
			button6.enabled = false;
		}
		if (button.id == 6) {
			button7.enabled = false;
			button8.enabled = true;
			
			TinCanTelephony.instance.setTheme(TinCanTelephony.properties.THEME_CLASSIC);
		}
		if (button.id == 7) {
			button7.enabled = true;
			button8.enabled = false;
			TinCanTelephony.instance.setTheme(TinCanTelephony.properties.THEME_CLEAR);
		}
	}

	@Override
	public void keyTyped(char c, int id) {

	}
}
