package net.medsouz.tct.networking.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author medsouz
 *
 */
public class Packet1Disconnect extends Packet{

	public String message;
	
	public int getID() {
		return 1;
	}

	public String getName() {
		return "Disconnect";
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
			String msg = dis.readUTF();
			dis.close();
			return msg;
		}catch(Exception err){
			err.printStackTrace();
		}
		return null;
	}
}
