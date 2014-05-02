using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using ClassLibrary1;

namespace PolyTweetWCF
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom de classe "Service1" dans le code, le fichier svc et le fichier de configuration.
    // REMARQUE : pour lancer le client test WCF afin de tester ce service, sélectionnez Service1.svc ou Service1.svc.cs dans l'Explorateur de solutions et démarrez le débogage.
    public class Service1 : IServicePolyTweet
    {
        public string GetData(int value)
        {
            return string.Format("You entered: {0}", value);
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }

        public bool Connexion(string login, string password)
        {
            return UtilisateurDAO.Instance.Connexion(login,password) != null;

        }

        public bool ajouterUtilisateur(string login, string password)
        {
            return UtilisateurDAO.Instance.ajouterUtilisateur(login, password);
        }

        public bool supprimerUtilisateur(string login, string password)
        {
            return UtilisateurDAO.Instance.supprimerUtilisateur(login, password);
        }

        public bool findUtilisateur(string login)
        {
            return UtilisateurDAO.Instance.findUtilisateur(login) != null;

        }
    }
}
