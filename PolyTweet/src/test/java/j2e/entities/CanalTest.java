package j2e.entities;

import static org.junit.Assert.*;
import j2e.application.TypeCanal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
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
	
	Utilisateur utilisateur;
	Canal canal1;
	Canal canal2;
	
	@Before
	public void setup() throws Exception {
		utilisateur = new Utilisateur("test");
		canal1 = new Canal("public",TypeCanal.PUBLIC, utilisateur);
		canal2 = new Canal("prive",TypeCanal.PRIVE, utilisateur);
		manager.persist(utilisateur);
		manager.persist(canal1);
		manager.persist(canal2);
	}
	
	@After
	public void cleanup() throws Exception {
		try { manager.detach(utilisateur); } catch (Exception e) {}
		try { manager.detach(canal1); } catch (Exception e) {}
		try { manager.detach(canal2); } catch (Exception e) {}
		utilisateur=null;
		canal1=null;
		canal2=null;
	}
	
	@Test
	public void testAdd() throws Exception {
		Canal test = new Canal("test", TypeCanal.PUBLIC, utilisateur);
		manager.persist(test);
		
		assertTrue(manager.contains(canal1));
		assertTrue(manager.contains(canal2));
		assertTrue(manager.contains(utilisateur));
		assertTrue(manager.contains(test));
    }

	
}
