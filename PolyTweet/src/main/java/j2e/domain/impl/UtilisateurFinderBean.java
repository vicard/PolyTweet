package j2e.domain.impl;

import j2e.domain.CanalFinder;
import j2e.domain.UtilisateurFinder;
import j2e.entities.Moderateur;
import j2e.entities.Proprietaire;
import j2e.entities.Utilisateur;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UtilisateurFinderBean extends FinderBean<Utilisateur> implements UtilisateurFinder {

	@PersistenceContext
	EntityManager entityManager;
	
	@EJB
	CanalFinder canalFinder;

	public Proprietaire findProprietaireByLogin(String login){
		try {
			return createdQueryWithOneParameter("id",id).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
	public Moderateur findModerateurByLogin(String login){
		
	}
	public Utilisateur findUtilisateurByLogin(String login){
		
	}
	public Utilisateur findAttenteByLogin(String login){
		
	}
	public Set<Utilisateur> findAllAttente(){
		
	}



}
