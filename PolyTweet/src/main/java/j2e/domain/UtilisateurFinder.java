package j2e.domain;

import java.util.Set;

import j2e.entities.Moderateur;
import j2e.entities.Proprietaire;
import j2e.entities.Utilisateur;

public interface UtilisateurFinder {

	public Proprietaire findProprietaireByLogin(String login);	
	public Moderateur findModerateurByLogin(String login);
	public Utilisateur findUtilisateurByLogin(String login);
	public Utilisateur findAttenteByLogin(String login);
	public Set<Utilisateur> findAllAttente();
	
}
