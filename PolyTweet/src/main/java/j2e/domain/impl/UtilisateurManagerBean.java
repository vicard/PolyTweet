package j2e.domain.impl;

import java.util.HashSet;
import java.util.Set;

import j2e.application.TypeCanal;
import j2e.domain.CanalFinder;
import j2e.domain.UtilisateurFinder;
import j2e.domain.UtilisateurManager;
import j2e.entities.Canal;
import j2e.entities.Message;
import j2e.entities.Utilisateur;
import j2e.entities.Moderateur;
import j2e.entities.Proprietaire;

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
	    
	    public boolean demandeAbonnement(Utilisateur utilisateur, String tagCanal) {
	            Canal canal = canalFinder.findCanalByTag(tagCanal);
	            entityManager.merge(utilisateur);
	            for(Canal c : utilisateur.getCanalAbonnes()) 
	            	if(c.equals(canal)) 
	            		return false;
	            for(Canal c : utilisateur.getCanalAttente())
	            	if(c.equals(canal))
	            		return false;
	            canal.getAttente().add(utilisateur);
	            utilisateur.getCanalAttente().add(canal);

	            entityManager.merge(utilisateur);
	            entityManager.merge(canal);
	            return true;
	    }
	    
	    public boolean accepterAbonnement(Utilisateur donneur,Utilisateur receveur, String tagCanal){
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(donneur);
            entityManager.merge(receveur);
            boolean donneurModerateur = false;
            boolean receveurAttente = false;
            for(Canal c : donneur.getCanalModerateurs()) 
            	if(c.equals(canal)) 
            		donneurModerateur=true;
            for(Canal c : receveur.getCanalAttente())
            	if(c.equals(canal))
            		receveurAttente = true;
            
            if(receveurAttente && donneurModerateur){
					((Moderateur)donneur).accepterAbonne(receveur,canal);
					return true;
            }
	    	return false;	    	
	    }
	    
	    public boolean refuserAbonnement(Utilisateur donneur,Utilisateur receveur, String tagCanal){
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(donneur);
            entityManager.merge(receveur);
            boolean donneurModerateur = false;
            boolean receveurAttente = false;
            for(Canal c : donneur.getCanalModerateurs()) 
            	if(c.equals(canal)) 
            		donneurModerateur=true;
            for(Canal c : receveur.getCanalAttente())
            	if(c.equals(canal))
            		receveurAttente = true;
            
            if(receveurAttente && donneurModerateur){
					((Moderateur)donneur).refuserAbonne(receveur,canal);
					return true;
            }
	    	return false;	    	
	    }
	    
	    public boolean ajouterModerateur(Utilisateur donneur,Utilisateur receveur, String tagCanal){	    	
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(donneur);
            entityManager.merge(receveur);
            System.out.println("test " +canal);
            System.out.println("test " + donneur.getCanalProprietaires());
            System.out.println(donneur.getCanalProprietaires().contains(canal));
            if(donneur.getCanalProprietaires().contains(canal)){
					((Proprietaire)donneur).ajouterModerateur(receveur,canal);
					return true;
            }
	    	return false;
	    }
	    
	    public boolean supprimerModerateur(Utilisateur donneur,Utilisateur receveur, String tagCanal){
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(donneur);
            entityManager.merge(receveur);
            if(donneur.getCanalProprietaires().contains(canal) && receveur.getCanalModerateurs().contains(canal)){
					((Proprietaire)donneur).supprimerModerateur(receveur,canal);
					return true;
            }
	    	return false;
	    }
	    
	    public boolean ajouterProprietaire(Utilisateur donneur,Utilisateur receveur, String tagCanal){
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(donneur);
            entityManager.merge(receveur);
            if(donneur.getCanalProprietaires().contains(canal)){
					((Proprietaire)donneur).ajouterProprietaire(receveur,canal);
					return true;
            }
	    	return false;
	    }
	    
	    public boolean supprimerProprietaire(Utilisateur donneur,Utilisateur receveur, String tagCanal){
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(donneur);
            entityManager.merge(receveur);
            if(donneur.getCanalProprietaires().contains(canal) && receveur.getCanalModerateurs().contains(canal)){
					((Proprietaire)donneur).supprimerProprietaire(receveur,canal);
					return true;
            }
	    	return false;
	    }

	    public boolean ajouterMessage(Utilisateur utilisateur, Message message, String tagCanal){
            Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(utilisateur);
            boolean abonne = false;
            for(Canal c : utilisateur.getCanalAbonnes()) 
            	if(c.equals(canal)) 
            		return true;
            
            if(!abonne) return false;
            
            canal.getMessages().add(message);
            utilisateur.getMessagesEnvoyes().add(message);

            entityManager.merge(utilisateur);
            entityManager.merge(canal);
            return true;
	    }
	    
	    public boolean supprimerMessage(Utilisateur utilisateur, Message message, String tagCanal){
	    	Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(utilisateur);
            if(utilisateur.getCanalModerateurs().contains(canal) && canal.getMessages().contains(message)){
					((Moderateur)utilisateur).supprimerMessage(message, canal);
					return true;
            }
	    	return false;
	    }
	    
	    public Set<Message> consulterMessages(Utilisateur utilisateur, String tagCanal){
	    	Set<Message> messages = new HashSet<Message>();
            Canal canal = canalFinder.findCanalByTag(tagCanal);
            entityManager.merge(utilisateur);
            boolean abonne = false;
            for(Canal c : utilisateur.getCanalAbonnes()) 
            	if(c.equals(canal)) 
            		abonne=true;
            if((!abonne) && canal.getType()==TypeCanal.PRIVE) return messages;
            
            messages = utilisateur.consulterMessages(canal);

            entityManager.merge(utilisateur);
            entityManager.merge(canal);
            return messages;
	    }
}
