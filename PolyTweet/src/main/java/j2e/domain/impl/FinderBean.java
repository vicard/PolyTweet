package j2e.domain.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



public class FinderBean<T> {

	@PersistenceContext
    private EntityManager entityManager;
	
	private T valeur;
	
    protected TypedQuery<T> createdQueryWithOneParameter(String attributeName, Object value){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) valeur.getClass());
        Root<T> from = criteria.from((Class<T>) valeur) ;
        criteria.select(from);
        criteria.where(builder.equal(from.get(attributeName), value));
        TypedQuery<T> query = entityManager.createQuery(criteria);

        return query;
    }
    
    protected TypedQuery<T> createdQuery(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) valeur.getClass());
        Root<T> from = criteria.from((Class<T>) valeur.getClass()) ;
        criteria.select(from);
        TypedQuery<T> query = entityManager.createQuery(criteria);

        return query;
    }
}
