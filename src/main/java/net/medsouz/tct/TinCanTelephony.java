package net.medsouz.tct;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * @author medsouz
 *
 */
@Mod(modid = TinCanTelephony.MODID, version = TinCanTelephony.VERSION)
public class TinCanTelephony {
	public static final String MODID = "TCT";
	public static final String VERSION = "0.1";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(new TickHandler());
	}
}
