package j2e.entities;

public class Moderateur extends Utilisateur {

	public Moderateur(String login) {
		super(login);
		
	}
	
	@Override
	void supprimerMessage(Message message) {
		canalCourant.supprimerMessage(message);
	}
	
	@Override
	void accepterAbonne(Utilisateur utilisateur){
		canalCourant.accepterAbonne(utilisateur);
	}
	
	@Override
	void supprimerAbonne(Utilisateur utilisateur){
		canalCourant.supprimerAbonne(utilisateur);
	}
	
	@Override
	void refuserAbonne(Utilisateur utilisateur) {
		canalCourant.refuserAbonne(utilisateur);
	}
}
