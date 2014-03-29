package net.medsouz.tct.networking.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.medsouz.tct.api.objects.DataType;

/**
 * @author medsouz
 *
 */
public class Packet5RequestData extends Packet{

	public DataType dataType;
	
	public int getID() {
		return 5;
	}

	public String getName() {
		return "Request Data";
	}

	@Override
	public byte[] writeData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeUTF(dataType.toString());
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
