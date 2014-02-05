package net.medsouz.tct.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiProfile extends GuiScreen {
	String username = Minecraft.getMinecraft().getSession().getUsername();
	protected GuiScreen oldScreen;

	public GuiProfile(GuiScreen par1GuiScreen) {
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
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3) {
		this.func_146276_q_();
		this.drawCenteredString(this.field_146289_q, username,
				this.field_146294_l / 2, 17, 16777215);
		super.drawScreen(par1, par2, par3);
	}

	public void addButtonListener(GuiButton button) {

		this.field_146297_k.func_147108_a(this.oldScreen);
	}
}