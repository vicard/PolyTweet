package j2e.domain.impl;

import j2e.domain.CanalFinder;
import j2e.domain.UtilisateurFinder;
import j2e.entities.Utilisateur;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UtilisateurFinderBean extends FinderBean implements UtilisateurFinder {

	@PersistenceContext
	EntityManager entityManager;
	
	@EJB
	CanalFinder canalFinder;


	public Utilisateur findUtilisateurByLogin(String login){
		try {
			return createdQueryWithOneParameter(Utilisateur.class,"login",login).getSingleResult();
		} catch (Exception ex){
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
