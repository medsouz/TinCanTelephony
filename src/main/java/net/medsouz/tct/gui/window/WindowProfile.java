package net.medsouz.tct.gui.window;

import cpw.mods.fml.common.Loader;
import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * @author medsouz
 *
 */
public class WindowProfile extends Window {
	
	String username;
	GuiButton addFriend = new GuiButton(1, 0, 0, 80, 20, "Add Friend");
	GuiButton sendMessage = new GuiButton(1, 0, 0, 80, 20, "Send Message");
	GuiButton join = new GuiButton(1, 0, 0, 70, 20, "Join Server");
	GuiButton invite = new GuiButton(1, 0, 0, 40, 20, "Invite");
	GuiButton viewMods = new GuiButton(1, 0, 0, 80, 20, "View Mods");
	GuiButton groups = new GuiButton(1, 0, 0, 80, 20, "View Groups");
	
	public WindowProfile(GuiOverlay g, int x, int y, int w, int h, String user) {
		super(g, "Profile - "+user, x, y, w, h);
		buttonList.add(addFriend);
		buttonList.add(sendMessage);
		buttonList.add(join);
		buttonList.add(invite);
		buttonList.add(viewMods);
		buttonList.add(groups);
		username = user;
	}

	@Override
	public void drawWindowContents() {
		RenderHelper.drawPlayer(username, (int) (xPos + width - (width * 0.1F) - 10), (int) (yPos + (width * 0.2F)), width * 0.2F);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7a\u00A7lOnline\u00A7f - Multiplayer", xPos + 5, yPos + 5, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "\u00A7oPlaying on 127.0.0.1", xPos + 5, yPos + 15, 0xFFFFFF);
		join.xPosition = xPos + 5;
		join.yPosition = yPos + 26;
		invite.xPosition = xPos + 80;
		invite.yPosition = yPos + 26;
		//TODO: obtain these values from the server
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "Playing \u00A7l"+Loader.instance().getMCVersionString()+"\u00A7f", xPos + 5, yPos + 50, 0xFFFFFF);
		overlay.drawString(Minecraft.getMinecraft().fontRenderer, "Currently using \u00A7l"+Loader.instance().getModList().size()+"\u00A7f mods", xPos + 5, yPos + 60, 0xFFFFFF);
		viewMods.xPosition = xPos + 5;
		viewMods.yPosition = yPos + 70;
		groups.xPosition = xPos + 90;
		groups.yPosition = yPos + 70;
		sendMessage.xPosition = xPos + 5;
		sendMessage.yPosition = yPos + height - 22;
		addFriend.xPosition = xPos + 90;
		addFriend.yPosition = yPos + height - 22;
	}

	@Override
	public void onClose() {
		
	}

	@Override
	public void onButtonPress(GuiButton button) {
		
	}

}
