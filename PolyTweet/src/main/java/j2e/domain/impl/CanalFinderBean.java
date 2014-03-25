package j2e.domain.impl;


import j2e.domain.CanalFinder;
import j2e.entities.Canal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



public class CanalFinderBean extends FinderBean<Canal> implements CanalFinder  {

	@PersistenceContext
	private EntityManager entityManager;

	
	public Canal findCanalByTag(String tag) {
		try {
			return createdQueryWithOneParameter("tag",tag).getSingleResult();
		} catch (Exception ex){
			return null;
		}
	}
	
	
    public Set<Canal> findAllCanal(){
    	List<Canal> canaux = createdQuery().getResultList();
        return new HashSet<Canal> (canaux);
    }

}
