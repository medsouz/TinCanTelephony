package net.medsouz.tct;

import net.medsouz.tct.networking.TCTConnection;
import net.medsouz.tct.networking.packet.PacketManager;
import net.medsouz.tct.theme.Theme;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

/**
 * @author medsouz
 *
 */
@Mod(modid = Properties.MODID, version = Properties.VERSION)
public class TinCanTelephony {
	
	public static Properties properties;
	public static KeyBinding overlayKey = new KeyBinding("Open Overlay", Keyboard.KEY_P, "TinCanTelephony");
	public static Theme curTheme = properties.THEME_CLASSIC;
	
	@Instance("TCT")
	public static TinCanTelephony instance;
	
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

	public TCTConnection getConnection() {
		return connection;
	}
	
	public void setTheme(Theme newTheme)
	{
		this.curTheme = newTheme;
	}
}
