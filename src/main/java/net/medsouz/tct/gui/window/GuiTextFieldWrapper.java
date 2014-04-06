package net.medsouz.tct.gui.window;


 
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
 
public class GuiTextFieldWrapper extends GuiTextField {
 
	public GuiTextFieldWrapper(FontRenderer par1FontRenderer, int par2, int par3, int par4, int par5) {
		super(par1FontRenderer, par2, par3, par4, par5);
	}
 
	public void setXPos(int x) {
		ReflectionHelper.setPrivateValue(GuiTextField.class, this, x, new String[] {"f", "xPosition", "field_146209_f"});
	}
	
	public void setYPos(int y) {
		ReflectionHelper.setPrivateValue(GuiTextField.class, this, y, new String[] {"g", "yPosition", "field_146210_g"});
	}
}