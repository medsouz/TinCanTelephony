package net.medsouz.tct.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;

/**
 * @author medsouz
 *
 */
public abstract class Window {
	protected int xPos, yPos, width, height;
	private String title;
	protected GuiOverlay overlay;
	protected ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
	
	public Window(GuiOverlay g, String t, int x, int y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		title = t;
		overlay = g;
	}
	
	public abstract void drawWindowContents();
	
	public abstract void onClose();
	
	public abstract void onButtonPress(GuiButton button);
	
	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public void setDimensions(int w, int h) {
		width = w;
		height = h;
	}
	
	public void setTitle(String t) {
		title = t;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<GuiButton> getButtonList() {
		return buttonList;
	}
}
