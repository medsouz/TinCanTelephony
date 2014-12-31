package net.medsouz.tct;

import org.lwjgl.input.Keyboard;

import net.medsouz.tct.api.FriendManager;
import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.RenderHelper;
import net.medsouz.tct.gui.window.Window;
import net.medsouz.tct.networking.TCTConnection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

/**
 * @author medsouz
 * @author Gord360
 */
public class TickHandler {
	Minecraft mc = Minecraft.getMinecraft();
	/**
	 * Used to render the overlay.
	 * 
	 * @param event
	 *            The render tick event
	 */
	Gui gui = new Gui();
	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		// Don't hardcode this
		if (!(mc.currentScreen != null && (mc.currentScreen instanceof GuiOverlay))) {
			if(mc.currentScreen instanceof net.minecraft.client.gui.GuiMainMenu){
				//FontRenderer, String, int, int, int
				//string, x, y, color, dropShadow
				if(TCTConnection.isConnected){
					RenderHelper.drawItemIcon(388, 0, 0, 16, 16);
					gui.drawString(mc.fontRenderer, "Connected! (" + FriendManager.getOnlineFriendsCount() + ")", 17, 5, 0xFFFFFF);
					
				}
				else if(!TCTConnection.isConnected){
					gui.drawString(mc.fontRenderer, "Not connected", 17, 5, 0xFFFFFF);
					RenderHelper.drawItemIcon(331, 0, 0, 16, 16);
				}
			}
		}
	}

	/**
	 * Used to handle user input
	 * 
	 * @param event
	 *            The client tick event
	 */
	@SubscribeEvent
	public void clientTick(ClientTickEvent event) {
		if (GameSettings.isKeyDown(TinCanTelephony.overlayKey)) {
			if ((mc.currentScreen != null && !(mc.currentScreen instanceof GuiOverlay) || mc.currentScreen == null) && !Keyboard.areRepeatEventsEnabled()) {
				mc.displayGuiScreen(new GuiOverlay(mc.currentScreen));
			}
		}
	}
}
