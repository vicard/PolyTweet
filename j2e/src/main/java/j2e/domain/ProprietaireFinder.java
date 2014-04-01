package j2e.domain;

import j2e.entities.Proprietaire;

public interface ProprietaireFinder {
	
	public Proprietaire findProprietaireByLogin(String login);

}
