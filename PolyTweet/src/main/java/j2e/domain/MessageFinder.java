package j2e.domain;

import java.util.Set;

import j2e.entities.Message;

public interface MessageFinder {

	public Message findMessageById(Long id);
	public Set<Message> findAllMessage();
	
}
