package j2e.entities;

import static org.junit.Assert.*;
import j2e.application.TypeCanal;

import java.io.File;
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
public class PieceJointeTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(PieceJointe.class.getPackage())
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
		PieceJointe pj1 = new PieceJointe(new File("file1"), m1);
		PieceJointe pj2 = new PieceJointe(new File("file2"),m1);
		transaction.begin();
        try {
            manager.persist(u1);
            manager.persist(c1);
            manager.persist(m1);
            manager.persist(pj1);
            assertTrue(manager.contains(pj1)); // manager contient la pièce jointe persistée
            assertFalse(manager.contains(new PieceJointe(new File("nouveau"),m1))); // manager ne contient pas une pièce jointe non créée
            assertFalse(manager.contains(pj2)); // manager ne contient pas une pièce jointe non persistée

            manager.remove(pj1);
            manager.remove(m1);
            manager.remove(c1);
            manager.remove(u1);
        } finally {
            transaction.commit();
        }
		Message m2 = new Message("message 2",c1,u1);
		PieceJointe pj3 = new PieceJointe(new File("file3"), m2);
        try {
        	manager.persist(pj3);
        } catch (PersistenceException pe) {
        } finally {
        	assertFalse(manager.contains(pj3)); // impossible de persister pj3 car c2 n'a pas été persisté avant
        }
	}

    @Test
    public void testRechercheParMessage() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		Canal c1 = new Canal("c1", TypeCanal.PUBLIC, u1);
		Message m1 = new Message("message 1",c1,u1);
		PieceJointe pj1 = new PieceJointe(new File("file1"), m1);

        transaction.begin();
        try {
        	manager.persist(u1);
        	manager.persist(c1);
        	manager.persist(m1);
        	manager.persist(pj1);
        	
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<PieceJointe> criteria = builder.createQuery(PieceJointe.class);
            Root<PieceJointe> from = criteria.from(PieceJointe.class) ;
            criteria.select(from);
            criteria.where(builder.equal(from.get("message"), m1));
            TypedQuery<PieceJointe> query = manager.createQuery(criteria);
            List<PieceJointe> result =  query.getResultList();
            // La recherche de message "m1" trouve 1 résultat dont le message "m1"
            assertEquals(result.size(),1);
            assertEquals(result.get(0).getMessage(), m1);
            
            criteria.where(builder.equal(from.get("message"), new Message("nouveau",c1,u1)));
            query = manager.createQuery(criteria);
            result =  query.getResultList();
            // La recherche du message "nouveau" trouve 0 résultat
            assertEquals(result.size(),0);
            
            manager.remove(pj1);
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
		PieceJointe pj1 = new PieceJointe(new File("file1"), m1);

    	transaction.begin();
    	try {
    		manager.persist(u1);
    		manager.persist(c1);
    		manager.persist(m1);
    		manager.persist(pj1);
    		assertTrue(manager.contains(pj1));
    		manager.remove(pj1);
    		assertFalse(manager.contains(pj1));
    		
    		manager.remove(m1);
    		manager.remove(c1);
    		manager.remove(u1);
    	} finally {
    		transaction.commit();
    	}
    }
}