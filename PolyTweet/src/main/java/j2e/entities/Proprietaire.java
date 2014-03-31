package j2e.entities;


public class Proprietaire extends Moderateur {

	public Proprietaire(String login) {
		super(login);
		
	}
	
	@Override
	public void ajouterModerateur(Utilisateur moderateur, Canal canal){
		canal.ajouterModerateur(moderateur);
	}
	
	@Override
	public void supprimerModerateur(Utilisateur moderateur, Canal canal){
		canal.supprimerModerateur(moderateur);
	}
	

}
