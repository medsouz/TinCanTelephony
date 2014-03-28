package net.medsouz.tct.api.objects;

public class Friend {
	private String username;
	private String status;
	private boolean isOnline;

	public Friend(String name, String stat, boolean online) {
		username = name;
		status = stat;
		isOnline = online;
	}
	
	/**
	 * Gets the username of this friend.
	 * @return Friend's username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets current status of the friend.
	 * Ex: Playing online at UberEpicMinecraftServer.net
	 * @return Status string.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Tells you if the friend is online or offline.
	 * @return True if user is online, false if offline.
	 */
	public boolean isOnline() {
		return isOnline;
	}
}
