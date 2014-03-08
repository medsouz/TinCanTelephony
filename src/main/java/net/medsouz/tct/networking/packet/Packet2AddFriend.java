package net.medsouz.tct.networking.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Packet2AddFriend extends Packet{

	public String friendName;
	
	@Override
	public int getID() {
		return 2;
	}

	@Override
	public String getName() {
		return "Add Friend";
	}

	@Override
	public byte[] writeData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeUTF(friendName);
			dos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Override
	public Object readData(byte[] data) {
		return null;
	}

}
