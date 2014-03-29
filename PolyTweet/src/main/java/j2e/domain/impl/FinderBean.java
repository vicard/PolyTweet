package j2e.domain.impl;

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
public class FinderBean<T> {

	@PersistenceContext
    private EntityManager entityManager;
	
	protected Class<T> clazz;
	
    protected TypedQuery<T> createdQueryWithOneParameter(String attributeName, Object value){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> from = criteria.from(clazz) ;
        criteria.select(from);
        criteria.where(builder.equal(from.get(attributeName), value));
        TypedQuery<T> query = entityManager.createQuery(criteria);

        return query;
    }
    
    protected TypedQuery<T> createdQuery(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> from = criteria.from(clazz) ;
        criteria.select(from);
        TypedQuery<T> query = entityManager.createQuery(criteria);

        return query;
    }
}
