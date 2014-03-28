package j2e.entities;

import j2e.application.*;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "login")
	private String login;
	
	protected Canal canalCourant;

	@OneToMany(mappedBy = "auteur")
	private Set<Message> messagesEnvoyes;
	
	@ManyToMany(mappedBy = "abonnes")
	private Set<Canal> canalAbonnes;
	
	@ManyToMany(mappedBy = "attente")
	private Set<Canal> canalAttente;
	
	@ManyToMany(mappedBy = "moderateurs")
	private Set<Canal> canalModerateurs;
	
	@ManyToMany(mappedBy = "proprietaires")
	private Set<Canal> canalProprietaires;
	
	public Utilisateur(String login){
		this.login=login;
	}
	
	public Utilisateur() {}
	
	public String getLogin() {
		return login;
	}

	public Canal getCanalCourant() {
		return canalCourant;
	}

	public Set<Message> getMessagesEnvoyes() {
		return messagesEnvoyes;
	}

	public Set<Canal> getCanalAbonnes() {
		return canalAbonnes;
	}

	public Set<Canal> getCanalAttente() {
		return canalAttente;
	}

	public Set<Canal> getCanalModerateurs() {
		return canalModerateurs;
	}

	public Set<Canal> getCanalProprietaires() {
		return canalProprietaires;
	}

	void ajouterMessage(Message message){
		canalCourant.ajouterMessage(message);
	}
	
	void supprimerMessage(Message message) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void ajouterModerateur(Moderateur moderateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void supprimerModerateur(Moderateur moderateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void demanderAbonnement(Utilisateur utilisateur){
		canalCourant.demanderAbonnement(utilisateur);
	}
	
	void accepterAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void supprimerAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	void refuserAbonne(Utilisateur utilisateur) throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	 Set<Message> consulterMessages(){
		 
		return canalCourant.consulterMessages();
	}
	 
	void supprimerCanal() throws NotAllowedException{
		throw new NotAllowedException("Vous n'avez pas les droits suffisants pour effectuer cet action");
	}
	
	   @Override
	    public boolean equals(Object obj) {
	        if (obj instanceof Utilisateur){
	            Utilisateur u = (Utilisateur)obj;
	            return u.getLogin().equals(this.getLogin());
	        }
	        return false;
	    }

}
