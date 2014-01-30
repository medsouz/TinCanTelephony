package net.medsouz.tct;

import org.lwjgl.input.Keyboard;

import net.medsouz.tct.networking.TCTConnection;
import net.medsouz.tct.networking.packet.PacketManager;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

/**
 * @author medsouz
 *
 */
@Mod(modid = TinCanTelephony.MODID, version = TinCanTelephony.VERSION)
public class TinCanTelephony {
	public static final String MODID = "TCT";
	public static final String VERSION = "0.1";
	
	public static KeyBinding overlayKey = new KeyBinding("Open Overlay", Keyboard.KEY_P, "TinCanTelephony");
	
	private TCTConnection connection;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){//Only run on the client
			FMLCommonHandler.instance().bus().register(new TickHandler());
			PacketManager.registerPackets();
			connection = new TCTConnection();
			ClientRegistry.registerKeyBinding(overlayKey);
		}
	}
}
