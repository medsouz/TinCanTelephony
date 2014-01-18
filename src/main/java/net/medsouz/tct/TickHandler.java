package net.medsouz.tct;

import net.medsouz.tct.gui.OverlayRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

/**
 * @author medsouz
 */
public class TickHandler {

	/**
	 * Used to render the overlay.
	 * 
	 * @param event
	 *            The render tick event
	 */
	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		//TODO: Don't hardcode this
		OverlayRenderer.drawImage(new ResourceLocation("tct", "textures/person.png"), 5, 4, 16, 16, 0.5f);
		Minecraft.getMinecraft().fontRenderer.drawString("1", 17, 15, 0xFFFFFF);
		OverlayRenderer.drawImage(new ResourceLocation("tct", "textures/envelope.png"), 5, 23, 16, 16, 0.5f);
		Minecraft.getMinecraft().fontRenderer.drawString("1", 17, 32, 0xFFFFFF);
	}

	/**
	 * Used to handle user input
	 * 
	 * @param event
	 *            The client tick event
	 */
	@SubscribeEvent
	public void clientTick(ClientTickEvent event) {
		
	}
}
