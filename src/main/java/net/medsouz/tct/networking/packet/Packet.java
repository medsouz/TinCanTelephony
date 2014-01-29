package net.medsouz.tct.networking.packet;

public abstract class Packet {
	
	public abstract int getID();

	public abstract String getName();

	public abstract byte[] writeData();

	public abstract Object readData(byte[] data);
}
