package j2e.domain;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import j2e.application.TypeCanal;
import j2e.domain.impl.UtilisateurManagerBean;
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
    private CanalManager canalManager;

    private final String userTest = "userTest";
    private final String userMDP = "mdp";

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("META-INF/persistence.xml", "persistence.xml")
                .addPackage(UtilisateurManager.class.getPackage())
                .addPackage(UtilisateurManagerBean.class.getPackage());
    }

    @Before
    public void setUp() throws Exception {
        utilisateurManager.create(userTest);
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
    public void testSubscribed() throws Exception{
        Utilisateur user = utilisateurManager.create("login");
        Utilisateur user2 = utilisateurManager.create("login2");

        canalManager.creer("tag",TypeCanal.PUBLIC,user.getLogin());

        assertTrue(utilisateurManager.subscribedToChannel(user2,"tag"));
        assertFalse(utilisateurManager.subscribedToChannel(user2,"tag"));
        System.out.println(user2.getCanalAbonnes());


        //already subscribed
        //assertFalse(utilisateurManager.subscribedToChannel(user,"tag"));
        //is already the owner
        //assertFalse(utilisateurManager.subscribedToChannel(user,"tag"));
    }
/*
    @Test
    public void testModerate() throws Exception{
        User user = manager.create("login","mdp","toto","toto");
        User user2 = manager.create("login2","mdp","toto","toto");

        channelManager.create("tag",false,user.getUserId());

        user2 = manager.logIn(user2.getLogin(),user2.getPassword());
        assertTrue(manager.becomeModeratorOfChannel(user2,"tag"));


        //already moderate
        assertFalse(manager.becomeModeratorOfChannel(user2,"tag"));
        //is already the owner
        assertFalse(manager.becomeModeratorOfChannel(user, "tag"));
    }
	*/
    @After
    public void clean() throws Exception {
        utilisateurManager.delete("toto");
    }

}
