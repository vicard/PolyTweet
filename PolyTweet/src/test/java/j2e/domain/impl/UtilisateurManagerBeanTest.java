package j2e.domain.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import j2e.domain.UtilisateurFinder;
import j2e.domain.UtilisateurManager;
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
public class UtilisateurManagerBeanTest {

	@EJB
    private UtilisateurManager manager;

    @EJB
    private UtilisateurFinder finder;


    private final static String userToDeleteName = "userToDelete";

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
        manager.create("toto");
    }

    @Test
    public void testCreate() throws Exception {
        assertNull(finder.findByLogin("user"));
        Utilisateur user1 = manager.create("user","mdp","toto","tata");
        Utilisateur user2 = manager.create("user","mdp","toto","tata");
        assertEquals(user1,user2);

        assertEquals(user1.getLogin(), "user");

        Utilisateur found = finder.findByLogin("user");
        assertEquals(found, user1);
    }

    @Test
    public void testDelete() throws Exception{
        assertNotNull(finder.findByLogin(userToDeleteName));
        assertTrue(manager.delete(userToDeleteName));
        assertNull(finder.findByLogin(userToDeleteName));
        assertFalse(manager.delete(userToDeleteName));
    }

    @After
    public void clean() throws Exception {
        manager.delete(userToDeleteName);
    }


}
