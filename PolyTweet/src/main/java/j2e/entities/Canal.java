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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CANAUX")
public class Canal implements Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "CANAUX_tag")
    private String tag;

    @Column(name = "CANAUX_type")
    private TypeCanal type;
    
    @OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="canal")
    private Set<Message> messages;

    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
    		name = "ABONNEMENTS",
    		joinColumns = @JoinColumn(name = "ABONNEMENTS_canal"),
    		inverseJoinColumns = @JoinColumn(name = "ABONNEMENTS_utilisateur")
    		)
    private Set<Utilisateur> abonnes;
    
    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
    		name = "ATTENTES",
    		joinColumns = @JoinColumn(name = "ATTENTES_canal"),
    		inverseJoinColumns = @JoinColumn(name = "ATTENTES_utilisateur")
    		)
    private Set<Utilisateur> attente;

    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
    		name = "MODERATEURS",
    		joinColumns = @JoinColumn(name = "MODERATEURS_canal"),
    		inverseJoinColumns = @JoinColumn(name = "MODERATEURS_utilisateur")
    		)
    private Set<Utilisateur> moderateurs;
    
    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
    		name = "PROPRIETAIRES",
    		joinColumns = @JoinColumn(name = "PROPRIETAIRES_canal"),
    		inverseJoinColumns = @JoinColumn(name = "PROPRIETAIRES_utilisateur")
    		)
    private Set<Utilisateur> proprietaires;
    
    public Canal() {}
    
    public Canal(String tag, TypeCanal type, Utilisateur createur) {
		this.setTag(tag);
		this.setType(type);
	    this.setMessages(new HashSet<Message>());
	    this.setAbonnes(new HashSet<Utilisateur>());
	    this.setAttente(new HashSet<Utilisateur>());
	    this.setModerateurs(new HashSet<Utilisateur>());
	    this.setProprietaires(new HashSet<Utilisateur>());
	    abonnes.add(createur);
	    moderateurs.add(createur);
	    proprietaires.add(createur);
	}

	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag=tag;
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
	
	public void setAttente(Set<Utilisateur> attente) {
		this.attente=attente;
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
	
	public Set<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(Set<Message> messages) {
		this.messages=messages;
	}

	public Set<Utilisateur> getAbonnes() {
		return abonnes;
	}
	
	public void setAbonnes(Set<Utilisateur> abonnes) {
		this.abonnes=abonnes;
	}

	public Set<Utilisateur> getModerateurs() {
		return moderateurs;
	}
	
	public void setModerateurs(Set<Utilisateur> moderateurs) {
		this.moderateurs=moderateurs;
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
	
	public void setProprietaires(Set<Utilisateur> proprietaires) {
		this.proprietaires=proprietaires;
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
        return "Canal{" +
                "tag = " + tag +
                ", type = " + type +
                "}";
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
}


