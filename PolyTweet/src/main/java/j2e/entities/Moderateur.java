package j2e.entities;

public class Moderateur extends Utilisateur {

	public Moderateur(String login) {
		super(login);
		
	}
	
	@Override
	void supprimerMessage(Message message, Canal canal) {
		canal.supprimerMessage(message);
	}
	
	@Override
	void accepterAbonne(Utilisateur utilisateur, Canal canal){
		canal.accepterAbonne(utilisateur);
	}
	
	@Override
	void supprimerAbonne(Utilisateur utilisateur, Canal canal){
		canal.supprimerAbonne(utilisateur);
	}
	
	@Override
	void refuserAbonne(Utilisateur utilisateur, Canal canal) {
		canal.refuserAbonne(utilisateur);
	}
}
