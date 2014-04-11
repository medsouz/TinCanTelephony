package net.medsouz.tct.theme;

import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.window.Window;

public class ThemeClear extends Theme {

	public ThemeClear() {
		super("Clear", 1);
	}

	@Override
	public void drawSidebar(GuiOverlay gui) {
		this.overlay = gui;

		/** Sidebar Background **/
		gui.drawGradientRect(0, gui.height / 2 - 115, 50, gui.height / 2 + 115, 0xAA444444, 0x88AAAAAA);
	}

	@Override
	public void drawWindow(Window w) {
		/** Window Contents **/
		getOverlayGui().drawGradientRect(w.getX(), w.getY(), w.getX() + w.getWidth(), w.getY() + w.getHeight(), 0x44999999, 0xAAFFFFFF);
		/** Title Bar **/
		getOverlayGui().drawRect(w.getX(), w.getY() - 16, w.getX() + w.getWidth(), w.getY(), 0xBB999999);
		/** Close Button **/
		getOverlayGui().drawRect(w.getX() + w.getWidth() - 14, w.getY() - 14, w.getX() + w.getWidth() - 2, w.getY() - 2, 0x33000000);
	}
}
