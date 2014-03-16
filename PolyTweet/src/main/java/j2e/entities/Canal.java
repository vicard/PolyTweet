package j2e.entities;

import j2e.application.PolyTweet;
import j2e.application.TypeCanal;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "canal")
public class Canal implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    public String tag;

    @Column(name = "type")
    public TypeCanal type;
    
    @ManyToOne
    public PolyTweet polytweet;
    
    @OneToMany
    public Set<Message> messages = new HashSet<Message>();

    @ManyToMany
    public Set<Utilisateur> abonnes = new HashSet<Utilisateur>();
    
    @ManyToMany
    public Set<Utilisateur> attente = new HashSet<Utilisateur>();

    @ManyToMany
    public Set<Moderateur> moderateurs = new HashSet<Moderateur>();
    
    @ManyToMany
    public Set<Proprietaire> proprietaires = new HashSet<Proprietaire>();
    
    
	void ajouterMessage(Message message){
        this.messages.add(message);
	}
	
	void editerMessage(Message message){
		
	}
	
	void supprimerMessage(Message message){
		this.messages.remove(message);
	}
	
	void ajouterModerateur(Moderateur moderateur){
		this.moderateurs.add(moderateur);
	}
	
	void supprimerModerateur(Moderateur moderateur){
		this.moderateurs.remove(moderateur);
	}
	
	void demanderAbonnement(Utilisateur utilisateur){
		this.attente.add(utilisateur);
	}
	
	void accepterAbonne(Utilisateur utilisateur){
		this.attente.remove(utilisateur);
		this.abonnes.add(utilisateur);
	}
	
	void supprimerAbonne(Utilisateur utilisateur){
		this.abonnes.remove(utilisateur);
	}
	
	void refuserAbonne(Utilisateur utilisateur) {
		this.attente.remove(utilisateur);
	}
	
	 Set<Message> consulterMessages(){
		 
		return null;
	}
}
