package net.medsouz.tct.api.objects;

public class Settings {
	private boolean publicInvite;
	private boolean publicMessage;
	private boolean publicServer;
	
	public Settings(boolean invite, boolean message, boolean server) {
		publicInvite = invite;
		publicMessage = message;
		publicServer = server;
	}

	//professional naming conventions
	public boolean canBeInvitedByPublic() {
		return publicInvite;
	}
	
	public boolean canBeMessagedByPublic() {
		return publicMessage;
	}
	
	public boolean canServerBeSeenByPublic() {
		return publicServer;
	}
}
