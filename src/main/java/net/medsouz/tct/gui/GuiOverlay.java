package net.medsouz.tct.gui;

import java.util.ArrayList;

import net.medsouz.tct.TinCanTelephony;
import net.medsouz.tct.gui.window.Window;
import net.medsouz.tct.gui.window.WindowProfile;
import net.medsouz.tct.gui.window.WindowSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Gord360
 * @author medsouz
 * 
 */
@SideOnly(Side.CLIENT)
public class GuiOverlay extends GuiScreen {
	String username = Minecraft.getMinecraft().getSession().getUsername();
	protected GuiScreen oldScreen;
	private Window resetWindow = null;
	private ArrayList<Window> windows = new ArrayList<Window>();

	public GuiOverlay(GuiScreen par1GuiScreen) {
		this.oldScreen = par1GuiScreen;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		this.buttonList.clear();
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char par1, int par2) {
		if(par2 == Keyboard.KEY_ESCAPE) {
			Minecraft.getMinecraft().currentScreen = oldScreen;
		}
		if(windows.size() > 0) {
			windows.get(0).keyTyped(par1, par2);
		}
	}
	
	Window getTopWindow(int mouseX, int mouseY){
		Window window = null;
		for(Window w : windows){
			if(mouseX > w.getX() && mouseX < w.getX() + w.getWidth() && mouseY > w.getY() - 16 && mouseY < w.getY() + w.getHeight()){
				window = w;
			}
		}
		return window;
	}
	
	void sidebarButtonPressed(int id) {
		switch (id) {
		case 0://Profile
			for(Window w : windows) { //Don't open the window if it already exists
				if(w instanceof WindowProfile) {
					if(((WindowProfile) w).getUsername() == username) {
						return;
					}
				}
			}
			windows.add(new WindowProfile(this, (width / 2) - (175 / 2), (height / 2) - (115 / 2), 175, 115, username));
			break;
		case 1://Friends
			break;
		case 2://Messages
			break;
		case 3://Groups
			break;
		case 4://Settings
			for(Window w : windows) {
				if(w instanceof WindowSettings) {
				    return;
				}
			}
			windows.add(new WindowSettings(this, username, (width / 2) - (175 / 2), (height / 2) - (115 / 2), 175, 150));
			break;
		case 5://Servers
			break;
		}
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int par3) {
		super.mouseClicked(mouseX, mouseY, par3);
		Window top = getTopWindow(mouseX, mouseY);
		if(top != null) {
			if(mouseX > top.getX() + top.getWidth() - 14 && mouseX < top.getX() + top.getWidth() - 2 && mouseY < top.getY() - 2 && mouseY > top.getY() - 14){
				windows.remove(top);
			}
		}
		for(int x = 0; x < windows.size(); x++){
			Window w = windows.get(x);
			if(top == w){
				resetWindow = w;
				if(mouseX > w.getX() && mouseX < w.getX() + w.getWidth() && mouseY > w.getY() - 16 && mouseY < w.getY()){
					draggedWindow = w;
					mouseXLast = mouseX;
					mouseYLast = mouseY;
				}
				for(GuiButton b : w.getButtonList()) {
					if (b.mousePressed(mc, mouseX, mouseY)){
						b.func_146113_a(mc.getSoundHandler());
						w.onButtonPress(b);
					}
				}
			}
		}
		
		//sidebar buttons
		if(top == null) {//Windows go over the sidebar and should block clicks
			int off = -105;
			for(int x = 0; x < 6; x++){
				off += (iconSpacing + wordSpacing);
				if(mouseX < 50 && mouseY > height / 2 + (off - (iconSpacing + wordSpacing)) && mouseY < height / 2 + off) {
					sidebarButtonPressed(x);
					break;//There is no reason to continue to checking
				}
			}
		}
	}

	private int iconSpacing = 10;
	private int wordSpacing = 25;
	
	private Window draggedWindow = null;
	private int mouseXLast, mouseYLast;
	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int mouseX, int mouseY, float par3) {
		if(oldScreen != null) {
			oldScreen.drawScreen(0, 0, par3);
		}
		this.drawGradientRect(0, 0, width, height, -0x0FEFEFF0, -0x7FEFEFF0);
		
		//Sidebar Background
		TinCanTelephony.curTheme.drawSidebar(this);
		
		//Icons
		//Profile
		int off = -105;
		RenderHelper.drawImage(RenderHelper.downloadImage("https://minotar.net/helm/"+username+"/32.png"), 9, height / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Profile", 25, height / 2 + off, 0xFFFFFF);
		//Friends
		off += iconSpacing;
		RenderHelper.drawParticle(80, 9, height / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Friends", 25, height / 2 + off, 0xFFFFFF);
		//Messages
		off += iconSpacing;
		RenderHelper.drawItemIcon(339, 9, height / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Messages", 25, height / 2 + off, 0xFFFFFF);
		//Groups
		off += iconSpacing;
		RenderHelper.drawItemIcon(267, 9, height / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Groups", 25, height / 2 + off, 0xFFFFFF);
		//Settings
		off += iconSpacing;
		RenderHelper.drawItemIcon(257, 9, height / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Settings", 25, height / 2 + off, 0xFFFFFF);
		//Servers
		off += iconSpacing;
		RenderHelper.drawItemIcon(399, 9, height / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Servers", 25, height / 2 + off, 0xFFFFFF);
		
		//put the most recently moved window on top
		if(resetWindow != null){
			windows.remove(resetWindow);
			windows.add(windows.size(), resetWindow);
			resetWindow = null;
		}
		
		for(int x = windows.size() - 1; x >= 0; x--){//drag priority is the opposite of draw priority
			Window w = windows.get(x);
			if(Mouse.isButtonDown(0)) {
				if (draggedWindow == w) {
					int diffX = mouseX - mouseXLast;
					int diffY = mouseY - mouseYLast;
					w.setPosition(w.getX() + diffX, w.getY() + diffY);
					mouseXLast = mouseX;
					mouseYLast = mouseY;
				}
			} else {
				draggedWindow = null;
			}
		}
		
		for(Window w : windows) {
			TinCanTelephony.curTheme.drawWindow(w);
			this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, w.getTitle(), w.getX() + w.getWidth() / 2, w.getY() - 12, 0xFFFFFF);
			w.drawWindowContents();
			int x = -1, y = -1;
			if(getTopWindow(mouseX, mouseY) == w) {//only give the real mouse position to top buttons, this prevents the back window from highlighting buttons.
				x = mouseX;
				y = mouseY;
			}
			for(GuiButton b : w.getButtonList()) {
				b.drawButton(mc, x, y);
				GL11.glColor3f(1f, 1f, 1f);//fix color leak
			}
		}
		super.drawScreen(mouseX, mouseY, par3);
	}
	
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		if(oldScreen != null) {
			oldScreen.setWorldAndResolution(mc, width, height);
		}
		super.setWorldAndResolution(mc, width, height);
	}
	
	protected void actionPerformed(GuiButton b) {
		for(Window w : windows) {
			w.onButtonPress(b);
		}
	}
	
	public ArrayList<Window> getWindows() {
		return windows;
	}
	
	/**
	 * Needed in order for drawGradientRect to be accessed from sub classes
	 */
	@Override
	public void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float f = (float)(par5 >> 24 & 255) / 255.0F;
        float f1 = (float)(par5 >> 16 & 255) / 255.0F;
        float f2 = (float)(par5 >> 8 & 255) / 255.0F;
        float f3 = (float)(par5 & 255) / 255.0F;
        float f4 = (float)(par6 >> 24 & 255) / 255.0F;
        float f5 = (float)(par6 >> 16 & 255) / 255.0F;
        float f6 = (float)(par6 >> 8 & 255) / 255.0F;
        float f7 = (float)(par6 & 255) / 255.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(f1, f2, f3, f);
        tessellator.addVertex((double)par3, (double)par2, (double)this.zLevel);
        tessellator.addVertex((double)par1, (double)par2, (double)this.zLevel);
        tessellator.setColorRGBA_F(f5, f6, f7, f4);
        tessellator.addVertex((double)par1, (double)par4, (double)this.zLevel);
        tessellator.addVertex((double)par3, (double)par4, (double)this.zLevel);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

}