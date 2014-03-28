package j2e.entities;

import j2e.application.*;
import java.io.Serializable;
import java.util.Set;
import java.lang.*;

public class Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String login;
	
	
	public Canal canalCourant;
	
	public Utilisateur(String login){
		this.login=login;
	}
	

	void ajouterMessage(Message message){
		canalCourant.ajouterMessage(message);
	}
	
	void supprimerMessage(Message message) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void ajouterModerateur(Moderateur moderateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void supprimerModerateur(Moderateur moderateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void demanderAbonnement(Utilisateur utilisateur){
		canalCourant.demanderAbonnement(utilisateur);
	}
	
	void accepterAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void supprimerAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void refuserAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	 Set<Message> consulterMessages(){
		 
		return canalCourant.consulterMessages();
	}
	 
	void supprimerCanal() throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}

}
