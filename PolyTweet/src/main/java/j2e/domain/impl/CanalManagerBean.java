package j2e.domain.impl;

import j2e.application.TypeCanal;
import j2e.domain.CanalFinder;
import j2e.domain.CanalManager;
import j2e.domain.MessageFinder;
import j2e.domain.UtilisateurFinder;
import j2e.entities.Canal;
import j2e.entities.Message;
import j2e.entities.Utilisateur;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CanalManagerBean implements CanalManager {

	@PersistenceContext
	EntityManager entityManager;

	@EJB
	CanalFinder finder;
	
	@EJB
	UtilisateurFinder utilisateurFinder;

	@EJB
	MessageFinder messageFinder;

	public boolean supprimer(String tag) {
		Canal canal = finder.findCanalByTag(tag);
		if (canal != null){
			entityManager.remove(canal);
			return true;
		}
		return false;
	}

	
	public Canal creer(String tag, TypeCanal type, String proprietaireId) {
		Canal canal = finder.findCanalByTag(tag);
		if (canal == null) {
			Utilisateur proprietaire = utilisateurFinder.findUtilisateurByLogin(proprietaireId);
			canal = new Canal(tag,type,proprietaire);
			proprietaire.getCanalProprietaires().add(canal);
			entityManager.persist(canal);
			entityManager.persist(proprietaire);
		}
		return canal;
	}

	
	public boolean ajouterMessage(Message message, Canal canal){
		canal.ajouterMessage(message);
		entityManager.merge(canal);
		return true;
	}

	
	public boolean supprimerMessage(Message message, Canal canal){
		Message m = messageFinder.findMessageById(message.getId());
		if(m!=null){
			canal.supprimerMessage(message);
			entityManager.remove(m);
		}
		return true;
	}

}
