package j2e.domain.impl;

import j2e.domain.UtilisateurFinder;
import j2e.domain.UtilisateurManager;
import j2e.entities.Utilisateur;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UtilisateurManagerBean implements UtilisateurManager {

	 @PersistenceContext(unitName = "polytweet-pu")
	    EntityManager entityManager;

	    @EJB
	    UtilisateurFinder finder;

	    public boolean delete(String login) {
	        Utilisateur utilisateur = finder.findUtilisateurByLogin(login);
	        if (utilisateur != null){
	            entityManager.remove(utilisateur);
	            return true;
	        }
	        return false;
	    }

	    public Utilisateur create(String login) {
	        Utilisateur utilisateur = finder.findUtilisateurByLogin(login);
	        if (utilisateur == null){
	            utilisateur = new Utilisateur(login);
	            entityManager.persist(utilisateur);
	        }
	        return utilisateur;
	    }

}
