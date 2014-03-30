package j2e.entities;


public class Proprietaire extends Moderateur {

	public Proprietaire(String login) {
		super(login);
		
	}
	
	@Override
	void ajouterModerateur(Moderateur moderateur, Canal canal){
		canal.ajouterModerateur(moderateur);
	}
	
	@Override
	void supprimerModerateur(Moderateur moderateur, Canal canal){
		canal.supprimerModerateur(moderateur);
	}
	

}
