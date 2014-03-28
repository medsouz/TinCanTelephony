package net.medsouz.tct.networking.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import net.medsouz.tct.api.objects.Friend;

public class Packet4ListFriends extends Packet{
	
	public int getID() {
		return 4;
	}

	public String getName() {
		return "List Friends";
	}
	
	@Override
	public byte[] writeData() {
		return null;
	}

	@Override
	public Object readData(byte[] data) {
		try{
			List<Friend> friends = new ArrayList<Friend>();
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			DataInputStream dis = new DataInputStream(bais);
			int len = dis.readInt();
			for(int f = 0; f < len; f++){
				friends.add(new Friend(dis.readUTF(), dis.readUTF(), false));
			}
			dis.close();
			return friends;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
