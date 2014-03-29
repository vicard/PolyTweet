package j2e.domain.impl;


import j2e.domain.CanalFinder;
import j2e.domain.UtilisateurManager;
import j2e.entities.Canal;
import j2e.entities.Utilisateur;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Local(CanalFinder.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CanalFinderBean extends FinderBean<Canal> implements CanalFinder  {

	@PersistenceContext
	private EntityManager entityManager;

	   
	public Canal findCanalByTag(String tag) {
		try {
			return createdQueryWithOneParameter2("tag",tag).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
	
	protected TypedQuery<Canal> createdQueryWithOneParameter2(String attributeName, Object value){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Canal> criteria = builder.createQuery(Canal.class);
        Root<Canal> from = criteria.from(Canal.class) ;
        criteria.select(from);
        criteria.where(builder.equal(from.get(attributeName), value));
        TypedQuery<Canal> query = entityManager.createQuery(criteria);

        return query;
    }
	
	
    public Set<Canal> findAllCanal(){
    	List<Canal> canaux = createdQuery().getResultList();
        return new HashSet<Canal> (canaux);
    }

}
