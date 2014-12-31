package net.medsouz.tct.api;

import java.util.ArrayList;
import java.util.List;

import net.medsouz.tct.TinCanTelephony;
import net.medsouz.tct.api.objects.DataType;
import net.medsouz.tct.api.objects.Friend;
import net.medsouz.tct.api.objects.Chat;
import net.medsouz.tct.api.objects.Message;
import net.medsouz.tct.networking.packet.Packet2AddFriend;
import net.medsouz.tct.networking.packet.Packet5RequestData;
import net.medsouz.tct.networking.packet.PacketManager;

public class MessageManager {
	//Chat is conversation with one person
	//Message is single exchange
	private static List<Chat> Chats = new ArrayList<Chat>();
	//This is for Messages, not Chats
	private static int numUnread = 0;
	
	
	/**
	 * Refreshes the friends list.
	 */
	public static void updateFriends() {
		if(TinCanTelephony.instance.getConnection() != null) {
			Packet5RequestData getFriends = new Packet5RequestData();
			getFriends.dataType = DataType.FRIENDS;
			PacketManager.sendPacket(getFriends, TinCanTelephony.instance.getConnection().out);
		}
	}
	
	/**
	 * @return List of all messages
	 */
	public static List<Chat> getMessages() {
		return Chats;
	}

	public static int getUnread(){
		return numUnread;
	}
	/**
	 * @param friendList list of friends, this should be taken from Packet4ListFriends
	 */
	public static void setFriends(List<Chat> chatList) {
		Chats = chatList;
		numUnread = 0;
		for(Chat c: Chats) {
			
		}
	}
	
	/**
	 * This method can be used to send a friend request or accept a friend request, this logic is handled automatically by the server. The username is case insensitive.
	 * @param name Username of the player you want to send a friend request to or accept a friend request from.
	 */
	public static void addFriend(String name) {
		if(TinCanTelephony.instance.getConnection() != null) {
			Packet2AddFriend add = new Packet2AddFriend();
			add.friendName = name;
			PacketManager.sendPacket(add, TinCanTelephony.instance.getConnection().out);
		}
	}
}
