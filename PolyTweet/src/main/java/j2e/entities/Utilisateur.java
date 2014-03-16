package j2e.entities;

import j2e.application.*;
import java.io.Serializable;
import java.util.Set;
import java.lang.*;

public class Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String login;
	
	public Utilisateur(String login){
		this.login=login;
	}
	
	void ajouterMessage(Message message){
		
	}
	
	void editerMessage(Message message){
		
	}
	
	void supprimerMessage(Message message){
		
	}
	
	void ajouterModerateur(Moderateur moderateur){
	
	}
	
	void supprimerModerateur(Moderateur moderateur){
	
	}
	
	void demanderAbonnement(Utilisateur utilisateur){
		
	}
	
	void accepterAbonne(Utilisateur utilisateur){
		
	}
	
	void supprimerAbonne(Utilisateur utilisateur){
		
	}
	
	void refuserAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	 Set<Message> consulterMessages(){
		 
		return 0;
	}

}
