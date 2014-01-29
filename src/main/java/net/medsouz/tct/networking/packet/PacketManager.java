package net.medsouz.tct.networking.packet;

import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author medsouz
 *
 */
public class PacketManager {
	
	@SuppressWarnings("rawtypes")
	public static List<Class> packets = new ArrayList<Class>();
	
	public static byte[] endPacket = "\r\n".getBytes();
	
	/**
	 * Registers packets so that they can be recognized.
	 */
	public static void registerPackets() {
		packets.add(Packet0PlayerLogin.class);
		packets.add(Packet1Disconnect.class);
	}
	
	/**
	 * Converts Packet into a byte array and sends it through the output stream.
	 * @param p The packet you wish to send
	 * @param out The stream pointing to the destination
	 */
	public static void sendPacket(Packet p, DataOutputStream out) {
		if(out != null){
			try{
				out.writeInt(p.getID());
				byte[] data = p.writeData();
				out.writeInt(data.length);
	 			out.write(data, 0, data.length);
	 			out.write(endPacket, 0 , endPacket.length);
				out.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Converts a byte array into the Packet object associated with the ID provided.
	 * @param id Packet ID
	 * @param data Byte array containing the packet
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object readPacket(int id, byte[] data) {
		Object dat = null;
		try{
			Class packClass = packets.get(id);
			Class[] args = {byte[].class};
			Method m = packClass.getMethod("readData", args);
			dat = m.invoke(packClass.newInstance(), new Object[] {data});
		}catch(Exception err){
			err.printStackTrace();
		}
		return dat;
	}
	
	/**
	 * Gives you a readable name from a packet ID
	 * @param id ID number of the packet
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getPacketName(int id) {
		String name = "Unknown";
		try{
			Class packClass = packets.get(id);
			Method m = packClass.getMethod("getName");
			name = (String) m.invoke(packClass.newInstance());
		}catch(Exception err){
			err.printStackTrace();
		}
		return name;
	}
}
