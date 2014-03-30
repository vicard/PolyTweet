package j2e.domain.impl;

import j2e.domain.UtilisateurFinder;
import j2e.entities.Utilisateur;

import javax.ejb.Stateless;

@Stateless
public class UtilisateurFinderBean extends FinderBean implements UtilisateurFinder {

	public Utilisateur findUtilisateurByLogin(String login){
		try {
			return createdQueryWithOneParameter(Utilisateur.class,"login",login).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
	
	 public Utilisateur findById(String id)
	    {
	        try {
	            return createdQueryWithOneParameter(Utilisateur.class, "utilisateurId", id).getSingleResult();
	        } catch (Exception e) {
	            return null;
	        }
	    }
	
	//public Utilisateur findAttenteByLogin(String login){
	//	
	//}
	//public Set<Utilisateur> findAllAttente(){
		//
	//}



}
