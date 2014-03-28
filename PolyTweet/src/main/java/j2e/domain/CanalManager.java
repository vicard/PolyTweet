package j2e.domain;

import j2e.application.TypeCanal;
import j2e.entities.Canal;
import j2e.entities.Message;
import j2e.entities.Utilisateur;

public interface CanalManager {
	
	public boolean supprimer(String tag);
	public Canal creer(String tag,TypeCanal type, Utilisateur proprietaire);
	public boolean ajouterMessage(Message message, Canal canal);
	

}
