package net.medsouz.tct.theme;

import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.window.Window;

public abstract class Theme {

	/**
	 * Theme Support
	 * @author Ri5ux
	 */
	
	protected String themeName;
	protected int id;
	protected GuiOverlay overlay;
	
	public Theme(String name, int id) {
		this.themeName = name;
		this.id = id;
	}

	public abstract void drawSidebar(GuiOverlay gui);
	
	public abstract void drawWindow(Window w);
	
	public String getThemeName()
	{
		return this.themeName;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public GuiOverlay getOverlayGui()
	{
		return this.overlay;
	}
}
