package j2e.domain.impl;

import j2e.domain.MessageFinder;
import j2e.entities.Canal;
import j2e.entities.Message;
import j2e.entities.Utilisateur;

import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MessageManagerBean {
	
	 @PersistenceContext
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

	    
	    public Message create(long id, String text,Canal canal,List<PieceJointe>){
	        Message message = new Message(id, text, canal, new Date());
	        entityManager.persist(message);
	        return message ;
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
