package net.medsouz.tct.api;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.medsouz.tct.api.objects.DataType;
import net.medsouz.tct.api.objects.Friend;
import net.medsouz.tct.networking.packet.Packet5RequestData;
import net.medsouz.tct.networking.packet.PacketManager;

/**
 * @author medsouz
 *
 */
public class FriendManager {
	
	private static List<Friend> friends = new ArrayList<Friend>();
	private static int numOnline = 0;
	
	
	/**
	 * Refreshes the friends list.
	 */
	public static void updateFriends(DataOutputStream out) {
		Packet5RequestData getFriends = new Packet5RequestData();
		getFriends.dataType = DataType.FRIENDS;
		PacketManager.sendPacket(getFriends, out);
	}
	
	/**
	 * @return List containing all of the Player's friends
	 */
	public static List<Friend> getFriends() {
		return friends;
	}
	
	/**
	 * @return Number of online friends
	 */
	public static int getOnlineFriendsCount(){
		return numOnline;	
	}

	public static void setFriends(List<Friend> friendList) {
		friends = friendList;
	}
}
