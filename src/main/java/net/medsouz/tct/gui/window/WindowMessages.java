package net.medsouz.tct.gui.window;

import net.medsouz.tct.api.MessageManager;
import net.medsouz.tct.gui.GuiOverlay;
import net.minecraft.client.gui.GuiButton;

/**
 * @author medsouz
 *
 */
public class WindowMessages extends Window {
	private static int numUnread = getUnreadCount();
	
	
	public WindowMessages(GuiOverlay g, String s, int x, int y, int w, int h) {
		super(g, "Messages - " + numUnread, x, y, w, h);
	}

	@Override
	public void drawWindowContents() {
		
	}

	@Override
	public void onClose() {
		
	}

	@Override
	public void onButtonPress(GuiButton button) {
		
	}
	
	@Override
	public void keyTyped(char c, int id) {
		
	}

	public static int getUnreadCount() {
		return MessageManager.getUnread();
	}

}
