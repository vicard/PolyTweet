package j2e.domain;

import j2e.entities.Moderateur;

public interface ModerateurFinder {
		
	public Moderateur findModerateurByLogin(String login);

}
