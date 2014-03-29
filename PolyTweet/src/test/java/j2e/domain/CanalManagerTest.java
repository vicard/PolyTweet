package j2e.domain;

import static org.junit.Assert.*;
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
public class CanalManagerTest {
	
	 @EJB
	 private CanalManager canalManager;

	 @EJB
	 private CanalFinder canalFinder;
	 
	 @EJB
	 private UtilisateurManager utilisateurManager;
	 
	 @EJB
	 private UtilisateurFinder utilisateurFinder;
	 
	 private Utilisateur utilisateur;

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
	
	@Before
    public void setUp() throws Exception {
		utilisateurManager.create("toto");
		utilisateur=utilisateurFinder.findUtilisateurByLogin("toto");
		
		
    }

    @Test
    public void testCreate() throws Exception {
    	
    	Canal canal = canalManager.creer("tag",TypeCanal.PUBLIC,utilisateur);
        //canal = canalManager.creer("test",TypeCanal.PUBLIC,utilisateur);
    	assertNull(canalFinder.findCanalByTag("user"));
    	//assertEquals(canal.getTag(),"test");
    	Canal found = canalFinder.findCanalByTag("tag");
    	assertEquals(found,canal);
    	assertEquals(found.getProprietaires().contains(utilisateur),true);
    }


    @After
    public void clean() throws Exception {
        //canalManager.supprimer("tag");
    }

}

