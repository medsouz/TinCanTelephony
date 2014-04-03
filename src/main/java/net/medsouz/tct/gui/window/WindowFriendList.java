package net.medsouz.tct.gui.window;

import java.util.List;

import net.medsouz.tct.api.FriendManager;
import net.medsouz.tct.api.objects.Friend;
import net.medsouz.tct.gui.GuiOverlay;
import net.medsouz.tct.gui.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class WindowFriendList extends Window {

	public WindowFriendList(GuiOverlay g, String t, int x, int y, int w, int h) {
		super(g, "Friends List", x, y, w, h);
		friends.add(OREOSpartan);
		friends.add(medsouz);
		
	}
	public GuiTextField search;

    
	Friend medsouz = new Friend("Medsouz", "Online", true);
	Friend OREOSpartan = new Friend("OREOSpartan", "Offline", false);

	public List<Friend> friends = FriendManager.getFriends();
	
	
	
	@Override
	public void drawWindowContents() {
		search  = new GuiTextField(Minecraft.getMinecraft().fontRenderer, xPos, yPos , 10, 10);
		search.setMaxStringLength(100);
	    search.setEnableBackgroundDrawing(false);
	    search.setFocused(true);
	    search.setText("Enter Player Name");
	    search.setCanLoseFocus(false);
	///What it will look like:
	    for(int fr = 0; fr < friends.size(); fr++){
			Friend f = friends.get(fr);
			RenderHelper.drawImage(RenderHelper.downloadImage("https://minotar.net/helm/"+ f.getUsername() +"/32.png"), xPos + 5, (yPos)+(fr*40), 32, 32);
			overlay.drawString(Minecraft.getMinecraft().fontRenderer, f.getUsername(), xPos + 40, (yPos)+(fr*40), 0xFFFFFF);
			overlay.drawString(Minecraft.getMinecraft().fontRenderer, f.getStatus(), xPos + 40, (yPos)+(fr*40) + 10, (f.getStatus().toLowerCase().equals("offline")) ?  0xFF0000 : 0x33FF00);
		}
		}

	@Override
	public void onClose() {
		
	}

	@Override
	public void onButtonPress(GuiButton button) {
		
	}

}
