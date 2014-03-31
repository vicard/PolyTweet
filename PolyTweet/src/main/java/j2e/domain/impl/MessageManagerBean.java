package j2e.domain.impl;

import j2e.domain.MessageFinder;
import j2e.entities.Canal;
import j2e.entities.Message;
import j2e.entities.Utilisateur;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageManagerBean {
	
	 @PersistenceContext(unitName = "polytweet-pu")
	    EntityManager entityManager;

	    @EJB
	    MessageFinder finder;

	    
	    public boolean delete(long id) {
	        Message message = finder.findMessageById(id);
	        if (message != null){
	            entityManager.remove(message);
	            return true;
	        }
	        return false;
	    }

	    public Message create(String texte, Canal canal, Utilisateur auteur){
	        Message message = new Message(texte, canal, auteur);
	        entityManager.persist(message);
	        return message;
	    }

	    @PostConstruct
	    public void initialize() {
	        System.out.println("Initializing MessageManager");
	    }

	    @PreDestroy
	    public void cleanup() {
	        System.out.println("Destroying MessageManager");
	    }

}
