package j2e.domain;

import java.util.List;

import j2e.entities.Message;

public interface MessageFinder {

	public Message findMessageById(Long id);
	public List<Message> findAllMessage();
	
}
