package j2e.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "texte")
	private String texte;
	
	@Column(name = "date")
	private Date date;
	
	@OneToMany(mappedBy = "message")
	private Set<PieceJointe> piecesJointes;
	
	@ManyToOne
	@Column(name = "canal")
	private Canal canal;

	@ManyToOne
	@Column(name = "auteur")
	private Utilisateur auteur;

	public Message(String texte, Set<PieceJointe> piecesJointes, Canal canal,
			Utilisateur auteur) {
		this.texte = texte;
		this.piecesJointes = piecesJointes;
		this.canal = canal;
		this.auteur = auteur;
		date = new Date();
	}

	public long getId() {
		return id;
	}

	public String getTexte() {
		return texte;
	}

	public Date getDate() {
		return date;
	}

	public Set<PieceJointe> getPiecesJointes() {
		return piecesJointes;
	}

	public Canal getCanal() {
		return canal;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}
	
	
}
