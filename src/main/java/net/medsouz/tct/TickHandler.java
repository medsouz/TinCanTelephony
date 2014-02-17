package net.medsouz.tct;

import net.medsouz.tct.api.FriendManager;
import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.RenderHelper;
import net.minecraft.client.Minecraft;
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
	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		// Don't hardcode this
		if (!(mc.currentScreen != null && (mc.currentScreen instanceof GuiOverlay))) {
			RenderHelper.drawImage(new ResourceLocation("tct", "textures/person.png"), 5, 4, 16, 16, 0.5f);
			Minecraft.getMinecraft().fontRenderer.drawString(Integer.toString(FriendManager.getOnlineFriendsCount()), 17, 15, 0xFFFFFF);
			RenderHelper.drawImage(new ResourceLocation("tct", "textures/envelope.png"), 5, 23, 16, 16, 0.5f);
			Minecraft.getMinecraft().fontRenderer.drawString("0", 17, 32, 0xFFFFFF);
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
			if (mc.currentScreen != null && !(mc.currentScreen instanceof GuiOverlay) || mc.currentScreen == null) {
				mc.func_147108_a(new GuiOverlay(mc.currentScreen));
			}
		}
	}
}
