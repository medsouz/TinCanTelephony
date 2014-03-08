package net.medsouz.tct.networking.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class Packet3PlayerNotFound extends Packet {

	@Override
	public int getID() {
		return 3;
	}

	@Override
	public String getName() {
		return "Player Not Found";
	}

	@Override
	public byte[] writeData() {
		return null;
	}

	@Override
	public Object readData(byte[] data) {
		try{
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			DataInputStream dis = new DataInputStream(bais);
			String username = dis.readUTF();
			dis.close();
			return username;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}

}
