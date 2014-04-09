package net.medsouz.tct.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import net.medsouz.tct.networking.packet.Packet0PlayerLogin;
import net.medsouz.tct.networking.packet.PacketManager;
import net.minecraft.client.Minecraft;

/**
 * @author medsouz
 *
 */
public class TCTConnection {

	public InputThread in;
	public DataOutputStream out;
	private Socket socket;

	public TCTConnection() {
		try {
			socket = new Socket("localhost", 9999);
			in = new InputThread(this, new DataInputStream(getSocket().getInputStream()));
			in.start();
			out = new DataOutputStream(getSocket().getOutputStream());
			Packet0PlayerLogin login = new Packet0PlayerLogin();
			login.username = Minecraft.getMinecraft().getSession().getUsername();
			login.sessionID = Minecraft.getMinecraft().getSession().getSessionID();
			PacketManager.sendPacket(login, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}
}
