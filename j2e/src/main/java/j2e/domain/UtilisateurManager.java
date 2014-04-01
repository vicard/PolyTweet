package j2e.domain;

import java.util.Set;

import j2e.entities.Message;
import j2e.entities.Utilisateur;

public interface UtilisateurManager {
	
	public boolean delete(String login);
	public Utilisateur create(String login);
	public boolean demandeAbonnement(Utilisateur utilisateur, String tagChannel);
    public boolean accepterAbonnement(Utilisateur donneur,Utilisateur receveur, String tagCanal);
    public boolean refuserAbonnement(Utilisateur donneur,Utilisateur receveur, String tagCanal);
	public boolean ajouterModerateur(Utilisateur donneur, Utilisateur receveur,String tagCanal);
	public boolean supprimerModerateur(Utilisateur donneur, Utilisateur receveur,String tagCanal);
	public boolean ajouterProprietaire(Utilisateur donneur, Utilisateur receveur,String tagCanal);
	public boolean supprimerProprietaire(Utilisateur donneur, Utilisateur receveur,String tagCanal);
    public boolean ajouterMessage(Utilisateur utilisateur, Message message, String tagCanal);
    public boolean supprimerMessage(Utilisateur utilisateur, Message message, String tagCanal);
    public Set<Message> consulterMessages(Utilisateur utilisateur, String tagCanal);



}
