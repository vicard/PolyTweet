package j2e.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PIECESJOINTES")
public class PieceJointe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PIECESJOINTES_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "PIECESJOINTES_fichier")
	private File fichier;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "PIECESJOINTES_message")
	private Message message;
	
	public PieceJointe() {}
	
	public PieceJointe(File fichier, Message message) {
		this.fichier = fichier;
		this.message = message;
	}

	public long getId() {
		return id;
	}
	
	public File getFichier() {
		return fichier;
	}

	public Message getMessage() {
		return message;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PieceJointe){
			PieceJointe pj = (PieceJointe)obj;
			return pj.getId()==this.getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "PieceJointe{"
				+ " fichier = "+ fichier
				+ ", message = " + message 
				+ "}";
	}

}
