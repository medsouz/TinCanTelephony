package net.medsouz.tct.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

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
			in = new InputThread(this, new DataInputStream(socket.getInputStream()));
			in.start();
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
