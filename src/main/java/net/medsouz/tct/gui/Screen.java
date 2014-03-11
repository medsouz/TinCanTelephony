package net.medsouz.tct.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;

/**
 * @author medsouz
 *
 */
public abstract class Screen {
	protected GuiOverlay overlay;
	protected ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
	
	public Screen(GuiOverlay g) {
		overlay = g;
	}

	public abstract void drawScreen(int x, int y, int w, int h);
	
	public ArrayList<GuiButton> getButtonList() {
		return buttonList;
	}
}
