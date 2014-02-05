package net.medsouz.tct;

import net.medsouz.tct.gui.GuiProfile;
import net.medsouz.tct.gui.OverlayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
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
	GuiProfile profile = new GuiProfile(new GuiMainMenu());
	private boolean overlayDebounce = false;

	/**
	 * Used to render the overlay.
	 * 
	 * @param event
	 *            The render tick event
	 */
	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		// Don't hardcode this
		if (mc.currentScreen instanceof GuiMainMenu) {

			OverlayRenderer.drawImage(new ResourceLocation("tct","/textures/Emerald.png"), 5, 5, 16, 16, .75f);
			//OverlayRenderer.drawImage(new ResourceLocation("tct", "/textures/username.png"), 26, 5, 16, 16, 1f);
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
			if (!overlayDebounce) {
				overlayDebounce = true;
				mc.func_147108_a(profile);
			}
		} else {
			overlayDebounce = false;
		}
	}
}
