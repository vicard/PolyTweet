package j2e.entities;

import j2e.application.TypeCanal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "canal")
public class Canal implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "tag")
    private String tag;

    @Column(name = "type")
    private TypeCanal type;
    
    @OneToMany(mappedBy="canal")
    private Set<Message> messages;

    @ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
    private Set<Utilisateur> abonnes;
    
    @ManyToMany(fetch=FetchType.LAZY)
    private Set<Utilisateur> attente;

    @ManyToMany(fetch=FetchType.LAZY)
    private Set<Utilisateur> moderateurs;
    
    @ManyToMany(fetch=FetchType.LAZY)
    private Set<Utilisateur> proprietaires;
    
    public Canal() {}
    
    public Canal(String tag, TypeCanal type, Utilisateur createur) {
		this.tag = tag;
		this.type = type;
	    messages = new HashSet<Message>();
	    abonnes = new HashSet<Utilisateur>();
	    abonnes.add(createur);
	    attente = new HashSet<Utilisateur>();
	    moderateurs = new HashSet<Utilisateur>();
	    moderateurs.add(createur);
	    proprietaires = new HashSet<Utilisateur>();
	    proprietaires.add(createur);
	}

	public String getTag() {
		return tag;
	}
        
	public TypeCanal getType() {
		return type;
	}

	public void setType(TypeCanal type) {
		this.type = type;
	}

	public Set<Message> consulterMessages(){
		return messages;
	}
	
	public void ajouterMessage(Message message){
        this.messages.add(message);
	}
	
	public void supprimerMessage(Message message){
		this.messages.remove(message);
	}	

	public Set<Utilisateur> getAttente() {
		return attente;
	}

	public void demanderAbonnement(Utilisateur utilisateur){
		this.attente.add(utilisateur);
	}
	
	public void accepterAbonne(Utilisateur utilisateur){
		this.attente.remove(utilisateur);
		this.abonnes.add(utilisateur);
	}
	
	public void refuserAbonne(Utilisateur utilisateur) {
		this.attente.remove(utilisateur);
	}	
	
	public void supprimerAbonne(Utilisateur utilisateur){
		this.abonnes.remove(utilisateur);
	}

	public Set<Utilisateur> getAbonnes() {
		return abonnes;
	}

	public Set<Utilisateur> getModerateurs() {
		return moderateurs;
	}

	public void ajouterModerateur(Utilisateur moderateur){
		this.moderateurs.add(moderateur);
	}
	
	public void supprimerModerateur(Utilisateur moderateur){
		this.moderateurs.remove(moderateur);
	}

	public Set<Utilisateur> getProprietaires() {
		return proprietaires;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Canal){
            Canal c = (Canal)obj;
            return c.getTag().equals(this.getTag());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "tag = " + tag +
                ", type = " + type +
                "}";
    }
    
}


