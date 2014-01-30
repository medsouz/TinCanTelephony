package net.medsouz.tct.networking.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * @author medsouz
 *
 */
public class Packet0PlayerLogin extends Packet{

	public String username = "";
	public String sessionID = "";
	public String token = "";
	
	public int getID() {
		return 0;
	}

	public String getName() {
		return "Player Login";
	}

	@Override
	public byte[] writeData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeUTF(username);
			dos.writeUTF(sessionID);
			dos.writeUTF(token);
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
