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
	
	@Override
	public void ajouterProprietaire(Utilisateur proprietaire, Canal canal){
		canal.ajouterProprietaire(proprietaire);
	}
	
	@Override
	public void supprimerProprietaire(Utilisateur proprietaire, Canal canal){
		canal.supprimerProprietaire(proprietaire);
	}

}
