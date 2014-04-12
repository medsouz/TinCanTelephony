package net.medsouz.tct.networking.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.medsouz.tct.api.objects.Settings;

public class Packet6Settings extends Packet{
	
	public Settings settings;

	public int getID() {
		return 6;
	}

	public String getName() {
		return "Settings";
	}

	@Override
	public byte[] writeData() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try{
			dos.writeBoolean(settings.canBeInvitedByPublic());
			dos.writeBoolean(settings.canBeMessagedByPublic());
			dos.writeBoolean(settings.canServerBeSeenByPublic());
			dos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Override
	public Object readData(byte[] data) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			DataInputStream dis = new DataInputStream(bais);
			Settings s = new Settings(dis.readBoolean(), dis.readBoolean(), dis.readBoolean());
			dis.close();
			return s;
		} catch (Exception err) {
			err.printStackTrace();
		}
		return null;
	}
}
