package j2e.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import j2e.application.TypeCanal;
import j2e.domain.impl.CanalFinderBean;
import j2e.domain.impl.CanalManagerBean;
import j2e.entities.Canal;
import j2e.entities.Utilisateur;

import javax.ejb.EJB;

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
public class UtilisateurManagerTest {

	@EJB
    private UtilisateurManager utilisateurManager;

    @EJB
    private UtilisateurFinder utilisateurFinder;
    
    @EJB
    private CanalFinder canalFinder;

    @EJB
    private CanalManager canalManager;


    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("META-INF/persistence.xml", "persistence.xml")
                .addPackage(CanalManager.class.getPackage())
                .addPackage(CanalManagerBean.class.getPackage())
                .addPackage(Utilisateur.class.getPackage())
            	.addPackage(Canal.class.getPackage())
            	.addPackage(CanalFinderBean.class.getPackage())
            	.addPackage(CanalFinder.class.getPackage());
    }
    private final String userTest = "userTest";
    private final String userTest2 = "userTest2";

    @Before
    public void setUp() throws Exception {
    	utilisateurManager.create(userTest);
    	utilisateurManager.create(userTest2);
		canalManager.creer("tagTest",TypeCanal.PUBLIC, userTest2);
    }

    @After
    public void cleanUp() throws Exception {
        utilisateurManager.delete(userTest);
        utilisateurManager.delete(userTest2);
        canalManager.supprimer("tagTest");
    }
    
    @Test
    public void testCreate() throws Exception {
        assertNull(utilisateurFinder.findUtilisateurByLogin("user"));
        Utilisateur user1 = utilisateurManager.create("user");
        Utilisateur user2 = utilisateurManager.create("user");
        assertEquals(user1,user2);

        assertEquals(user1.getLogin(), "user");

        Utilisateur found = utilisateurFinder.findUtilisateurByLogin("user");
        assertEquals(found, user1);
    }

    @Test
    public void testDelete() throws Exception{
        assertNotNull(utilisateurFinder.findUtilisateurByLogin(userTest));
        assertTrue(utilisateurManager.delete(userTest));
        assertNull(utilisateurFinder.findUtilisateurByLogin(userTest));
        assertFalse(utilisateurManager.delete(userTest));
    }
    
    @Test
    public void testDemandeAbonnement() throws Exception{
    	Utilisateur receveur =utilisateurFinder.findUtilisateurByLogin(userTest);
    	Canal canal =canalFinder.findCanalByTag("tagTest");
    	
        assertTrue(utilisateurManager.demandeAbonnement(receveur,"tagTest"));
        assertFalse(utilisateurManager.demandeAbonnement(receveur,"tagTest"));
        assertTrue(utilisateurFinder.findUtilisateurByLogin(userTest).getCanalAttente().toArray()[0].equals(canal));
        assertTrue(canalFinder.findCanalByTag("tagTest").getAttente().toArray()[0].equals(receveur));
    }

}
