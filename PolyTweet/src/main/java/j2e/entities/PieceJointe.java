package j2e.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "piece_jointe")
public class PieceJointe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "fichier")
	private File fichier;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@Column(name = "message")
	private Message message;
	
	public PieceJointe() {}
	
	public PieceJointe(File fichier, Message message) {
		this.fichier = fichier;
		this.message = message;
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
			return (pj.getFichier().equals(this.getFichier())
					&& pj.getMessage().equals(this.getMessage()));
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
