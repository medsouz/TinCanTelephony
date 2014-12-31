package net.medsouz.tct.gui.window;

import net.medsouz.tct.api.FriendManager;
import net.medsouz.tct.api.objects.Friend;
import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.RenderHelper;
import net.medsouz.tct.gui.window.element.GuiTextFieldWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.input.Keyboard;

public class WindowFriendList extends Window {

	public WindowFriendList(GuiOverlay g, String t, int x, int y, int w, int h) {
		super(g, "Friends", x, y, w, h);
		search = new GuiTextFieldWrapper(Minecraft.getMinecraft().fontRenderer, xPos, yPos, 100, 20);
		add = new GuiButton(1, xPos, yPos, 40, 20, "Add");
		search.setMaxStringLength(100);
		search.setEnableBackgroundDrawing(true);
		search.setFocused(true);
		search.setText("Username");
		search.setCanLoseFocus(false);
		Keyboard.enableRepeatEvents(true);
	}

	public GuiTextFieldWrapper search;
	GuiButton add;

	public int pagesize, pagenum = 1;

	@Override
	public void drawWindowContents() {
		add.xPosition = xPos + 115;
		add.yPosition = yPos + 85;
		buttonList.add(add);
		search.setXPos(xPos + 10);
		search.setYPos(yPos + height - 30);
		search.drawTextBox();
		search.updateCursorCounter();
		// /What it will look like:
		for (int fr = 0; fr < FriendManager.getFriends().size(); fr++) {
			Friend f = FriendManager.getFriends().get(fr);
			RenderHelper.drawImage(RenderHelper.downloadImage("https://minotar.net/helm/"+ f.getUsername() +"/32.png"), xPos + 5, (yPos)+(fr*40), 32, 32);
			overlay.drawString(Minecraft.getMinecraft().fontRenderer, f.getUsername(), xPos + 40, (yPos) + (fr * 40), 0xFFFFFF);
			overlay.drawString(Minecraft.getMinecraft().fontRenderer, f.getStatus(), xPos + 40, (yPos) + (fr * 40) + 10, (f.getStatus().toLowerCase().equals("offline")) ? 0xFF0000 : 0x33FF00);
		}
	}

	public void onClose() {
		Keyboard.enableRepeatEvents(false);
	}

	public void onButtonPress(GuiButton button) {
		if (button.equals(add)) {
			for (Window w : this.overlay.getWindows()) { // Don't open the window if it already exists
				if (w instanceof WindowProfile) {
					if (((WindowProfile) w).getUsername() == search.getText()) {
						return;
					}
				}
			}
			this.overlay.getWindows().add(new WindowProfile(this.overlay, (this.overlay.width / 2) - (175 / 2), (this.overlay.height / 2) - (115 / 2), 175, 115, search.getText()));
		}
	}

	public void keyTyped(char c, int id) {
		if(search.getText().equals("Username")) search.setText("");
		search.textboxKeyTyped(c, id);
	}

}
