package j2e.domain.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public abstract class FinderBean {

	 @PersistenceContext(unitName = "polytweet-pu")
	    private EntityManager entityManager;

	    <T> TypedQuery<T> createdQueryWithOneParameter(Class<T> cls, String attributeName, Object value){

	        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = builder.createQuery(cls);
	        Root<T> from = criteria.from(cls) ;
	        criteria.select(from);
	        criteria.where(builder.equal(from.get(attributeName), value));
	        return entityManager.createQuery(criteria);
	    }
}
