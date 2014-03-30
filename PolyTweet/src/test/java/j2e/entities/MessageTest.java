package j2e.entities;

import static org.junit.Assert.*;
import j2e.application.TypeCanal;

import java.util.List;

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
public class MessageTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(Message.class.getPackage())
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
		Message m1 = new Message("message 1",c1,u1);
		Message m2 = new Message("message 2",c1,u1);
		transaction.begin();
        try {
            manager.persist(u1);
            manager.persist(c1);
            manager.persist(m1);
            assertTrue(manager.contains(m1)); // manager contient le message persisté
            assertFalse(manager.contains(new Message("nouveau",c1,u1))); // manager ne contient pas un message non créé
            assertFalse(manager.contains(m2)); // manager ne contient pas un message non persisté

            manager.remove(m1);
            manager.remove(c1);
            manager.remove(u1);
        } finally {
            transaction.commit();
        }
        Canal c3 = new Canal("c3",TypeCanal.PUBLIC, u1);
		Message m3 = new Message("message 3",c1,u1);
        try {
        	manager.persist(m3);
        } catch (PersistenceException pe) {
        } finally {
        	assertFalse(manager.contains(c3)); // impossible de persister m3 car c3 n'a pas été persisté avant
        }
	}

    @Test
    public void testRechercheParCanal() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		Canal c1 = new Canal("c1", TypeCanal.PUBLIC, u1);
		Message m1 = new Message("message 1",c1,u1);
        transaction.begin();
        try {
        	manager.persist(u1);
        	manager.persist(c1);
        	manager.persist(m1);
        	
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
            Root<Message> from = criteria.from(Message.class) ;
            criteria.select(from);
            criteria.where(builder.equal(from.get("canal"), c1));
            TypedQuery<Message> query = manager.createQuery(criteria);
            List<Message> result =  query.getResultList();
            // La recherche du canal "c1" trouve 1 résultat dont le canal "c1"
            assertEquals(result.size(),1);
            assertEquals(result.get(0).getCanal(), c1);
            
            criteria.where(builder.equal(from.get("canal"), new Canal("nouveau",TypeCanal.PRIVE,u1)));
            query = manager.createQuery(criteria);
            result =  query.getResultList();
            // La recherche du canal "nouveau" trouve 0 résultat
            assertEquals(result.size(),0);
            
            manager.remove(m1);
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
		Message m1 = new Message("message 1",c1,u1);
    	
    	transaction.begin();
    	try {
    		manager.persist(u1);
    		manager.persist(c1);
    		manager.persist(m1);
    		assertTrue(manager.contains(m1));
    		manager.remove(m1);
    		assertFalse(manager.contains(m1));
    		
    		manager.remove(c1);
    		manager.remove(u1);
    	} finally {
    		transaction.commit();
    	}
    }
}

