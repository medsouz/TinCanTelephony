package net.medsouz.tct.networking;

import java.io.DataInputStream;

public class InputThread extends Thread{
	
	private TCTConnection conn;
	private DataInputStream in;
	
	public InputThread(TCTConnection connection, DataInputStream inputStream){
		super("TinCanTelephony - Input Thread");
		in = inputStream;
		conn = connection;
	}
	
    @Override
    public void run() {
    	
    }
}
