package net.medsouz.tct.gui.window;

import net.medsouz.tct.gui.GuiOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * Used for testing the Window API
 * @author medsouz
 *
 */
public class WindowTest extends Window {

	GuiButton button1 = new GuiButton(1, 0, 0, 80, 20, "TEST BUTTON A");
	GuiButton button2 = new GuiButton(1, 0, 0, 80, 20, "TEST BUTTON B");
	
	public WindowTest(GuiOverlay g, String t, int x, int y, int w, int h) {
		super(g, t, x, y, w, h);
		buttonList.add(button1);
		buttonList.add(button2);
	}

	@Override
	public void drawWindowContents() {
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("This window is for debugging", xPos + 1, yPos, 0xFFFFFF);
		button1.xPosition = xPos + 1;
		button1.yPosition = yPos + 15;
		button2.xPosition = xPos + 89;
		button2.yPosition = yPos + 15;
	}

	@Override
	public void onClose() {

	}

	@Override
	public void onButtonPress(GuiButton button) {
		if(button == button1){
			System.out.println("Test button A pressed!");
		}
		if(button == button2){
			System.out.println("Test button B pressed!");
		}
	}

}
