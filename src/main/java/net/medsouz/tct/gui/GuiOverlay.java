package net.medsouz.tct.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

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

	public GuiOverlay(GuiScreen par1GuiScreen) {
		this.oldScreen = par1GuiScreen;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.field_146292_n.clear();
		//Add button - this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100,	this.field_146295_m / 4 + 96 + 12, "Hallo"));

	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char par1, int par2) {
		if(par2 == Keyboard.KEY_ESCAPE) {
			Minecraft.getMinecraft().currentScreen = oldScreen;
		}
	}

	private int iconSpacing = 10;
	private int wordSpacing = 25;
	
	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int mouseX, int mouseY, float par3) {
		if(oldScreen != null) {
			oldScreen.drawScreen(0, 0, par3);
		}
		this.drawGradientRect(0, 0, this.field_146294_l, this.field_146295_m, -0x3FEFEFF0, -0x2FEFEFF0);
		//Background
		RenderHelper.drawBlockSide(2, 2, 0, this.field_146295_m / 2 - 100, 50, 50, 1, 1);
		RenderHelper.drawBlockSide(2, 0, 0, this.field_146295_m / 2 - 50, 50, 140, 1, 150 / 50);
		//Icons
		//Profile
		int off = -90;
		RenderHelper.drawImage(RenderHelper.downloadImage("https://minotar.net/avatar/"+username+"/32.png"), 9, this.field_146295_m / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Profile", 25, this.field_146295_m / 2 + off, 0xFFFFFF);
		//Friends
		off += iconSpacing;
		RenderHelper.drawParticle(80, 9, this.field_146295_m / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Friends", 25, this.field_146295_m / 2 + off, 0xFFFFFF);
		//Messages
		off += iconSpacing;
		RenderHelper.drawItemIcon(339, 9, this.field_146295_m / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Messages", 25, this.field_146295_m / 2 + off, 0xFFFFFF);
		//Groups
		off += iconSpacing;
		RenderHelper.drawItemIcon(267, 9, this.field_146295_m / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Groups", 25, this.field_146295_m / 2 + off, 0xFFFFFF);
		//Settings
		off += iconSpacing;
		RenderHelper.drawItemIcon(257, 9, this.field_146295_m / 2 + off, 32, 32);
		off += wordSpacing;
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Settings", 25, this.field_146295_m / 2 + off, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, par3);
	}

	public void addButtonListener(GuiButton button) {
		this.field_146297_k.func_147108_a(this.oldScreen);
	}
	
	public void func_146280_a(Minecraft mc, int width, int height) {
		if(oldScreen != null) {
			oldScreen.func_146280_a(mc, width, height);
		}
		super.func_146280_a(mc, width, height);
	}
}