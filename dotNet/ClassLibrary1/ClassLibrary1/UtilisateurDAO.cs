using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.Entity;

namespace ClassLibrary1
{
    public class UtilisateurDAO
    {
        private static UtilisateurDAO instance;

        public static UtilisateurDAO Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new UtilisateurDAO();
                }
                return instance;
            }
        }

        /// <summary> 
        /// Liste tous les utilisateurs 
        /// </summary> 
        public List<Utilisateur> getListUtilisateurs()
        {
            using (PolytweetModele modele = new PolytweetModele())
            {
                var requete = from utilisateur in modele.Utilisateur
                              select utilisateur;
                return requete.ToList<Utilisateur>();
            }
        } 

        /// <summary> 
        /// Connexion 
        /// </summary> 
        public Utilisateur Connexion(String login, String password)
        {
            using (PolytweetModele modele = new PolytweetModele())
            {
                var requete = from utilisateur in modele.Utilisateur
                              where utilisateur.login == login
                              select utilisateur;
                try
                {
                    Utilisateur u = requete.Single<Utilisateur>();
                    if (u.password == password)
                        return u;
                    else return null;
                }
                catch (Exception)
                {
                    return null;
                }

            }

        }

        /// <summary> 
        /// Ajouter un utilisateur 
        /// </summary> 
        public bool ajouterUtilisateur(String log, String pwd)
        {
            using (PolytweetModele modele = new PolytweetModele())
            {
                modele.Utilisateur.Add(new Utilisateur
                {
                    login = log,
                    password = pwd
                });
                try
                {
                    modele.SaveChanges();
                    return true;
                }
                catch (Exception) {
                    return false;
                }
                
            }

        }

        /// <summary> 
        /// Supprimer un utilisateur 
        /// </summary> 
        public bool supprimerUtilisateur(String log, String pwd)
        {
            using (PolytweetModele modele = new PolytweetModele())
            {
                var requete = from utilisateur in modele.Utilisateur
                              where utilisateur.login == log
                              select utilisateur;
                try
                {
                    Utilisateur u = requete.Single<Utilisateur>();
                    modele.Utilisateur.Remove(u);
                    modele.SaveChanges();
                    return true;
                }
                catch (Exception)
                {
                    return false;
                }
            }
        }

        public Utilisateur findUtilisateur(String log)
        {
            using (PolytweetModele modele = new PolytweetModele())
            {
                var requete = from utilisateur in modele.Utilisateur
                              where utilisateur.login == log
                              select utilisateur;
                try
                {
                    return requete.Single<Utilisateur>(); ;
                }
                catch (Exception)
                {
                    return null;
                }
            }
        }
        
    }
}
