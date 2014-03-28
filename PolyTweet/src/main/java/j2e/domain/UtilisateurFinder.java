package j2e.domain;

import j2e.entities.Utilisateur;

import java.util.Set;

public interface UtilisateurFinder {

	public Utilisateur findUtilisateurByLogin(String login);
	//public Utilisateur findAttenteByLogin(String login);
	//public Set<Utilisateur> findAllAttente();
	
}
