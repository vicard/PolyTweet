package j2e.entities;

import static org.junit.Assert.*;

import java.util.List;

import j2e.application.TypeCanal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CanalTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(Canal.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("META-INF/persistence.xml",
						"persistence.xml");
	}

	@PersistenceContext
	EntityManager manager;

	@Inject
	UserTransaction transaction;
	
	@Test
	public void testAjout() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		Canal c1 = new Canal("c1", TypeCanal.PUBLIC, u1);
		Canal c2 = new Canal("c2", TypeCanal.PRIVE, u1);

		transaction.begin();
        try {
            manager.persist(u1);
            manager.persist(c1);
            assertTrue(manager.contains(c1)); // manager contient le canal persisté
            assertFalse(manager.contains(new Canal("new",TypeCanal.PUBLIC,u1))); // manager ne contient pas un canal non créé
            assertFalse(manager.contains(c2)); // manager ne contient pas un canal non persisté
            
            manager.remove(c1);
            manager.remove(u1);
        } finally {
            transaction.commit();
        }
        Utilisateur u2 = new Utilisateur("u2");
        Canal c3 = new Canal("c3",TypeCanal.PUBLIC, u2);
        try {
        	manager.persist(c3);
        } catch (PersistenceException pe) {
        } finally {
        	assertFalse(manager.contains(c3)); // impossible de persister c3 car u2 n'a pas été persisté avant
        }
	}

    @Test
    public void testRechercheParTag() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		Canal c1 = new Canal("c1", TypeCanal.PUBLIC, u1);
		
        transaction.begin();
        try {
        	manager.persist(u1);
        	manager.persist(c1);
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Canal> criteria = builder.createQuery(Canal.class);
            Root<Canal> from = criteria.from(Canal.class) ;
            criteria.select(from);
            criteria.where(builder.equal(from.get("tag"), "c1"));
            TypedQuery<Canal> query = manager.createQuery(criteria);
            List<Canal> result =  query.getResultList();
            // La recherche du tag "c1" trouve 1 résultat dont le tag est "c1"
            assertEquals(result.size(),1);
            assertEquals(result.get(0).getTag(), "c1");
            
            criteria.where(builder.equal(from.get("tag"), "innexistant"));
            query = manager.createQuery(criteria);
            result =  query.getResultList();
            // La recherche du tag "c2" trouve 0 résultat
            assertEquals(result.size(),0);
            
            manager.remove(c1);
            manager.remove(u1);
        } finally {
            transaction.commit();
        }
    }
	
    @Test
    public void suppressionTest() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		Canal c1 = new Canal("c1", TypeCanal.PUBLIC, u1);
		    	
    	transaction.begin();
    	try {
    		manager.persist(u1);
    		manager.persist(c1);
    		assertTrue(manager.contains(c1));
    		manager.remove(c1);
    		assertFalse(manager.contains(c1));
    		manager.remove(u1);
    	} finally {
    		transaction.commit();
    	}
    }
}
