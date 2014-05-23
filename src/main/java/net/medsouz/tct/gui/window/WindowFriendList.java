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
		search.setMaxStringLength(100);
		search.setEnableBackgroundDrawing(true);
		search.setFocused(true);
		search.setText("Username");
		search.setCanLoseFocus(true);
		Keyboard.enableRepeatEvents(true);
		buttonList.add(add);
		FriendManager.updateFriends();
	}

	public GuiTextFieldWrapper search;
	GuiButton add = new GuiButton(1, 0, 0, 40, 20, "Add");

	public int pagesize, pagenum = 1;

	@Override
	public void drawWindowContents() {
		add.xPosition = xPos + 115;
		add.yPosition = yPos + 85;
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
			FriendManager.addFriend(search.getText());
		}
	}

	public void keyTyped(char c, int id) {
		search.textboxKeyTyped(c, id);
	}

}
