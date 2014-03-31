package j2e.entities;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class UtilisateurTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(Utilisateur.class.getPackage())
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
		Utilisateur u2 = new Utilisateur("u2");

		transaction.begin();
        try {
            manager.persist(u1);
            assertTrue(manager.contains(u1)); // manager contient l'utilisateur persisté
            assertFalse(manager.contains(new Utilisateur("nouveau"))); // manager ne contient pas un utilisateur non créé
            assertFalse(manager.contains(u2)); // manager ne contient pas un utilisateur non persisté
            
            manager.remove(u1);
        } finally {
            transaction.commit();
        }
	}

    @Test
    public void testRechercheParLogin() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		
        transaction.begin();
        try {
        	manager.persist(u1);
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> criteria = builder.createQuery(Utilisateur.class);
            Root<Utilisateur> from = criteria.from(Utilisateur.class) ;
            criteria.select(from);
            criteria.where(builder.equal(from.get("login"), "u1"));
            TypedQuery<Utilisateur> query = manager.createQuery(criteria);
            List<Utilisateur> result =  query.getResultList();
            // La recherche du login "u1" trouve 1 résultat dont le login est "u1"
            assertEquals(result.size(),1);
            assertEquals(result.get(0).getLogin(), "u1");
            
            criteria.where(builder.equal(from.get("login"), "innexistant"));
            query = manager.createQuery(criteria);
            result =  query.getResultList();
            // La recherche du login "innexistant" trouve 0 résultat
            assertEquals(result.size(),0);
            
            manager.remove(u1);
        } finally {
            transaction.commit();
        }
    }
	
    @Test
    public void suppressionTest() throws Exception {
		Utilisateur u1 = new Utilisateur("u1");
		    	
    	transaction.begin();
    	try {
    		manager.persist(u1);
    		assertTrue(manager.contains(u1));
    		manager.remove(u1);
    		assertFalse(manager.contains(u1));
    	} finally {
    		transaction.commit();
    	}
    }
}
