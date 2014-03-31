package j2e.domain.impl;


import j2e.domain.MessageFinder;
import j2e.entities.Message;

import javax.ejb.Stateless;
@Stateless
public class MessageFinderBean extends FinderBean implements MessageFinder {
	
	public Message findMessageById(Long id) {
		try {
			return createdQueryWithOneParameter(Message.class,"id",id).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
}
