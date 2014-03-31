package j2e.domain;

import j2e.entities.Utilisateur;

public interface UtilisateurManager {
	
	public boolean delete(String login);
	public Utilisateur create(String login);
	public boolean subscribedToChannel(Utilisateur utilisateur, String tagChannel);
	public boolean ajouterModerateur(Utilisateur donneur, Utilisateur receveur,String tagCanal);

}
