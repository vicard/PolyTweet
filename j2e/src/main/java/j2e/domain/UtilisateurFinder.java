package j2e.domain;

import j2e.entities.Utilisateur;

public interface UtilisateurFinder {

	public Utilisateur findUtilisateurByLogin(String login);
}
