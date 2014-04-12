package net.medsouz.tct.gui.window;

import net.medsouz.tct.TinCanTelephony;
import net.medsouz.tct.api.objects.Settings;
import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.networking.packet.Packet6Settings;
import net.medsouz.tct.networking.packet.PacketManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * Settings window
 * 
 * @author Ri5ux
 * 
 */
public class WindowSettings extends Window {

	GuiButton publicMessages = new GuiButton(0, 0, 0, 80, 20, "Public"),
			privateMessages = new GuiButton(1, 0, 0, 80, 20, "Private"),
			publicServer = new GuiButton(2, 0, 0, 80, 20, "Public"),
			privateServer = new GuiButton(3, 0, 0, 80, 20, "Private"),
			publicInvite = new GuiButton(4, 0, 0, 80, 20, "Public"),
			privateInvite = new GuiButton(5, 0, 0, 80, 20, "Private"),
			confirm = new GuiButton(6, 0, 0, 80, 20, "Confirm"),
			cancel = new GuiButton(7, 0, 0, 80, 20, "Cancel");

	public WindowSettings(GuiOverlay g, String t, int x, int y, int w, int h) {
		super(g, "Settings", x, y, w, h);
		buttonList.add(publicMessages);
		buttonList.add(privateMessages);
		buttonList.add(publicServer);
		buttonList.add(privateServer);
		buttonList.add(publicInvite);
		buttonList.add(privateInvite);
		buttonList.add(confirm);
		buttonList.add(cancel);
		
		Settings current = TinCanTelephony.instance.getSettings();
		if(current != null) {
			publicMessages.enabled = !current.canBeMessagedByPublic();
			privateMessages.enabled = !publicMessages.enabled;
			publicServer.enabled = !current.canServerBeSeenByPublic();
			privateServer.enabled = !publicServer.enabled;
			publicInvite.enabled = !current.canBeInvitedByPublic();
			privateInvite.enabled = !publicInvite.enabled;
		} else {
			publicMessages.enabled = false;
			publicServer.enabled = false;
			publicInvite.enabled = false;
		}
	}

	@Override
	public void drawWindowContents() {
		publicMessages.xPosition = xPos + 5;
		publicMessages.yPosition = yPos + 20;

		privateMessages.xPosition = xPos + 89;
		privateMessages.yPosition = yPos + 20;

		publicServer.xPosition = xPos + 5;
		publicServer.yPosition = yPos + 55;

		privateServer.xPosition = xPos + 89;
		privateServer.yPosition = yPos + 55;

		publicInvite.xPosition = xPos + 5;
		publicInvite.yPosition = yPos + 90;

		privateInvite.xPosition = xPos + 89;
		privateInvite.yPosition = yPos + 90;
		
		confirm.xPosition = xPos + 5;
		confirm.yPosition = yPos + 115;
		
		cancel.xPosition = xPos + 89;
		cancel.yPosition = yPos + 115;

		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Private Messages", xPos + 6, yPos + 8, 0xFFFFFF);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Current Server", xPos + 6, yPos + 43, 0xFFFFFF);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Server Invites", xPos + 6, yPos + 78, 0xFFFFFF);
	}

	@Override
	public void onClose() {

	}

	@Override
	public void onButtonPress(GuiButton button) {
		if (button.id == 0) {
			//System.out.println("Anyone can send me messages!");
			publicMessages.enabled = false;
			privateMessages.enabled = true;
		}
		if (button.id == 1) {
			//System.out.println("Only friends can send me messages!");
			publicMessages.enabled = true;
			privateMessages.enabled = false;
		}
		if (button.id == 2) {
			//System.out.println("Anyone can see what server I'm on!");
			publicServer.enabled = false;
			privateServer.enabled = true;
		}
		if (button.id == 3) {
			//System.out.println("Only friends can see what server I'm on!");
			publicServer.enabled = true;
			privateServer.enabled = false;
		}
		if (button.id == 4) {
			//System.out.println("Anyone can invite me to a server!");
			publicInvite.enabled = false;
			privateInvite.enabled = true;
		}
		if (button.id == 5) {
			//System.out.println("Only friends can invite me to a server!");
			publicInvite.enabled = true;
			privateInvite.enabled = false;
		}
		if(button.id == 6) {
			Packet6Settings settings = new Packet6Settings();
			Settings s = new Settings(!publicInvite.enabled, !publicMessages.enabled, !publicServer.enabled);
			settings.settings = s;
			PacketManager.sendPacket(settings, TinCanTelephony.instance.getConnection().out);
			TinCanTelephony.instance.setSettings(s);
			overlay.closeWindow(this);
		}
		if(button.id == 7) {
			overlay.closeWindow(this);
		}
	}

	@Override
	public void keyTyped(char c, int id) {

	}
}
