package j2e.entities;

public class Moderateur extends Utilisateur {

	public Moderateur(String login) {
		super(login);
		
	}
	
	@Override
	public void supprimerMessage(Message message, Canal canal) {
		canal.supprimerMessage(message);
	}
	
	@Override
	public void accepterAbonne(Utilisateur utilisateur, Canal canal){
		canal.accepterAbonne(utilisateur);
	}
	
	@Override
	public void supprimerAbonne(Utilisateur utilisateur, Canal canal){
		canal.supprimerAbonne(utilisateur);
	}
	
	@Override
	public void refuserAbonne(Utilisateur utilisateur, Canal canal) {
		canal.refuserAbonne(utilisateur);
	}
}
