package j2e.domain.impl;

import j2e.domain.CanalFinder;
import j2e.domain.UtilisateurFinder;
import j2e.domain.UtilisateurManager;
import j2e.entities.Canal;
import j2e.entities.Utilisateur;

import java.util.Iterator;
import java.util.Set;

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
	    
	    @EJB
	    CanalFinder canalFinder;

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
	    
	    public boolean subscribedToChannel(Utilisateur utilisateur, String tagChannel) {
	        //if(role.compareTo(UserRole.USER_ROLE_CONNECTED) == 0){
	            Canal canal = canalFinder.findCanalByTag(tagChannel);
	            entityManager.merge(utilisateur);
	            System.out.println(utilisateur.getCanalAbonnes());
	            for(Canal c : utilisateur.getCanalAbonnes()) if(c.equals(canal)) return false;
	            canal.getAbonnes().add(utilisateur);
	            utilisateur.getCanalAbonnes().add(canal);

	            entityManager.merge(utilisateur);
	            entityManager.merge(canal);
	            return true;
	        //}
	        // else user is not connected or he has already subscribed to this channel

	        //return false;
	    }

}
