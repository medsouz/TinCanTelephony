package net.medsouz.tct.networking;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import net.medsouz.tct.api.FriendManager;
import net.medsouz.tct.api.objects.Friend;
import net.medsouz.tct.networking.packet.PacketManager;

public class InputThread extends Thread{
	
	private TCTConnection conn;
	private DataInputStream in;
	
	public InputThread(TCTConnection connection, DataInputStream inputStream){
		super("TinCanTelephony - Input Thread");
		in = inputStream;
		conn = connection;
	}
	
    @Override
    public void run() {
    	int packetId;
    	try {
    		while((packetId = in.readInt()) != -1){
				int len = in.readInt();
				byte[] data = new byte[len];
				in.read(data, 0, len);
				if(parsePacket(packetId, data)){//returns false if the connection needs to be killed
					break;
				}
    		}
    		conn.getSocket().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	System.out.println("Connection closed");
    }

	/**
	 * Processes the packet.
	 * @param packetId
	 * @param data
	 * @return Returns true if the client should disconnect.
	 */
	private boolean parsePacket(int packetId, byte[] data) {
		switch(packetId){
			case 1:
				String msg = (String)PacketManager.readPacket(packetId, data);
                System.out.println("Disconnected with message: "+msg);
				return true;
			case 3:
				String username = (String)PacketManager.readPacket(packetId, data);
				System.out.println("User " + username + " not found!");
				break;
			case 4:
				FriendManager.setFriends((List<Friend>) PacketManager.readPacket(packetId, data));
				break;
			default:
				System.out.println("Recieved invalid packet! (ID == " + packetId + ")");
				return true;
		}
		return false;
	}
}
