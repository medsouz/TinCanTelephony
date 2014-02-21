package net.medsouz.tct.gui;

/**
 * @author medsouz
 *
 */
public abstract class Screen extends Window{
	public Screen(GuiOverlay g, int x, int y, int w, int h) {
		super(g, "", x, y, w, h);
	}

	public abstract void drawScreen(GuiOverlay overlay, int x, int y, int w, int h);
}
