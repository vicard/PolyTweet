package j2e.domain.impl;

import j2e.domain.CanalFinder;
import j2e.domain.UtilisateurFinder;
import j2e.entities.Utilisateur;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class UtilisateurFinderBean extends FinderBean<Utilisateur> implements UtilisateurFinder {

	@PersistenceContext
	EntityManager entityManager;
	
	@EJB
	CanalFinder canalFinder;
	
	protected TypedQuery<Utilisateur> createdQueryWithOneParameter2(String attributeName, Object value){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> criteria = builder.createQuery(Utilisateur.class);
        Root<Utilisateur> from = criteria.from(Utilisateur.class) ;
        criteria.select(from);
        criteria.where(builder.equal(from.get(attributeName), value));
        TypedQuery<Utilisateur> query = entityManager.createQuery(criteria);

        return query;
    }


	public Utilisateur findUtilisateurByLogin(String login){
		try {
			return createdQueryWithOneParameter2("login",login).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
	
	//public Utilisateur findAttenteByLogin(String login){
	//	
	//}
	//public Set<Utilisateur> findAllAttente(){
		//
	//}



}
