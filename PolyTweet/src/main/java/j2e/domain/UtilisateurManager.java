package j2e.domain;

import j2e.entities.Utilisateur;

public interface UtilisateurManager {
	
	public boolean delete(String login);
	public Utilisateur create(String login);

}
