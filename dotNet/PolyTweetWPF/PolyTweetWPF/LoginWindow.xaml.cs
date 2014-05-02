using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace PolyTweetWPF
{
    /// <summary>
    /// Logique d'interaction pour LoginWindow.xaml
    /// </summary>
    public partial class LoginWindow : Window
    {
        private Service1.ServicePolyTweetClient client;
        public LoginWindow()
        {
            InitializeComponent();
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            client = new Service1.ServicePolyTweetClient();
        }
        private void buttonConnexion_Click(object sender, RoutedEventArgs e)
        {
            if (client.Connexion(textBoxLogin.Text, passwordBoxPassword.Password))
                ErrorLabel.Content = "Connexion réussie : Bonjour " + textBoxLogin.Text;
            else
                ErrorLabel.Content = "Connexion échouée";
        }
        private void buttonInscription_Click(object sender, RoutedEventArgs e)
        {
            if (client.ajouterUtilisateur(textBoxLogin.Text, passwordBoxPassword.Password))
                ErrorLabel.Content = "Inscription réussie";
            else
                ErrorLabel.Content = "Inscription échouée";

        }
        private void buttonAnnuler_Click(object sender, RoutedEventArgs e)
        {
            MainWindow mainWindow = new MainWindow();
            mainWindow.Show();
            this.Close();
        }

    }
}
