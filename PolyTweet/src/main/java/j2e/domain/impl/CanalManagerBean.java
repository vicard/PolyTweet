package j2e.domain.impl;

import j2e.application.TypeCanal;
import j2e.domain.CanalFinder;
import j2e.domain.CanalManager;
import j2e.domain.MessageFinder;
import j2e.entities.Canal;
import j2e.entities.Message;
import j2e.entities.Utilisateur;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CanalManagerBean implements CanalManager {

	@PersistenceContext
	EntityManager entityManager;

	@EJB
	CanalFinder finder;

	@EJB
	MessageFinder messageFinder;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean supprimer(String tag) {
		Canal canal = finder.findCanalByTag(tag);
		if (canal != null){
			entityManager.remove(canal);
			return true;
		}
		return false;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Canal creer(String tag, TypeCanal type, Utilisateur proprietaire) {
		Canal canal = finder.findCanalByTag(tag);
		if (canal == null) {
			Canal c = new Canal(tag,type,proprietaire);
			entityManager.merge(c);
			return c;
		}
		return canal;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean ajouterMessage(Message message, Canal canal){
		canal.ajouterMessage(message);
		entityManager.merge(canal);
		return true;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean supprimerMessage(Message message, Canal canal){
		Message m = messageFinder.findMessageById(message.getId());
		if(m!=null){
			canal.supprimerMessage(message);
			entityManager.remove(m);
		}
		return true;
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
