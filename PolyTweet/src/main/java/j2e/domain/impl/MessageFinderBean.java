package j2e.domain.impl;


import j2e.domain.MessageFinder;
import j2e.entities.Message;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class MessageFinderBean extends FinderBean implements MessageFinder {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	public Message findMessageById(Long id) {
		try {
			return createdQueryWithOneParameter(Message.class,"id",id).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
	
	
  /*  public Set<Message> findAllMessage(){
    	List<Message> listeMessages = createdQuery().getResultList();
        return new HashSet<Message> (listeMessages);
    }
    */
}
