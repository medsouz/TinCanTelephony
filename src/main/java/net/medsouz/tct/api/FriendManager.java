package net.medsouz.tct.api;

import java.util.ArrayList;
import java.util.List;

import net.medsouz.tct.api.objects.Friend;

/**
 * @author medsouz
 *
 */
public class FriendManager {
	
	private static List<Friend> friends = new ArrayList<Friend>();
	
	/**
	 * Refreshes the friends list.
	 */
	public static void updateFriends() {
		//TODO: Networking
	}
	
	/**
	 * @return List containing all of the Player's friends
	 */
	public static List<Friend> getFriends() {
		return friends;
	}
}
