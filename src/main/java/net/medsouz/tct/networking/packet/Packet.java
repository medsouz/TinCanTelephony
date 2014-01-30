package net.medsouz.tct.networking.packet;

/**
 * @author medsouz
 *
 */
public abstract class Packet {
	
	/**
	 * Allows one to get the ID number of a packet.
	 * @return ID number of the packet
	 */
	public abstract int getID();

	/**
	 * Get the english name of the packet.
	 * @return Packet name string
	 */
	public abstract String getName();

	/**
	 * Converts the packet into a byte array.
	 * @return Byte array containing the packet
	 */
	public abstract byte[] writeData();

	/**
	 * Converts byte array created by writeData back into usable data.
	 * @param data
	 * @return
	 */
	public abstract Object readData(byte[] data);
}
