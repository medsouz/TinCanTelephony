package net.medsouz.tct.theme;

import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.RenderHelper;
import net.medsouz.tct.gui.window.Window;

public class ThemeClassic extends Theme {

	public ThemeClassic() {
		super("Classic", 0);
	}

	@Override
	public void drawSidebar(GuiOverlay gui) {
		this.overlay = gui;
		
		RenderHelper.drawBlockSide(2, 2, 0, gui.height / 2 - 115, 50, 50, 1, 1);
		RenderHelper.drawBlockSide(2, 0, 0, gui.height / 2 - 65, 50, 180, 1, 150 / 50);
	}

	@Override
	public void drawWindow(Window w) {
		RenderHelper.drawBlockSide(44, 2, w.getX(), w.getY() - 16, w.getWidth(), 16, w.getWidth() / 50f, 0.5f);//background
		RenderHelper.drawBlockSide(5, 0, w.getX(), w.getY(), w.getWidth(), w.getHeight(), w.getWidth() / 50f, w.getHeight() / 50f);//title bar
		RenderHelper.drawBlockSide(46, 2, w.getX() + w.getWidth() - 14, w.getY() - 14, 12, 12);//close button
		
	}
}
