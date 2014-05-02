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
using System.Windows.Shapes;

namespace PolyTweetWPF
{
    /// <summary>
    /// Logique d'interaction pour mainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private string login;

        private Dictionary<string, Canal> canaux;
        private void InitData()
        {

            Message m1 = new Message("admin", DateTime.Now, "message 1");
            Message m2 = new Message("moi", DateTime.Now.AddDays(-1), "message 2");
            Message m3 = new Message("toi", DateTime.Now.AddMonths(-1), "message 3");
            Message m4 = new Message("admin", DateTime.Now.AddDays(-10), "message 4");

            Canal c1 = new Canal("canal public 1", true);
            c1.addMessage(m1);
            c1.addMessage(m2);
            Canal c2 = new Canal("canal public 2", true);
            c2.addMessage(m2);
            c2.addMessage(m3);
            Canal c3 = new Canal("canal prive 1", false);
            c3.addMessage(m1);
            c3.addMessage(m4);

            canaux = new Dictionary<string, Canal>();
            canaux.Add(c1.tag, c1);
            canaux.Add(c2.tag, c2);
            canaux.Add(c3.tag, c3);
        }

        public MainWindow() : this("")
        {}

        public MainWindow(string login)
        {
            this.login = login;
            InitializeComponent();
            InitData();
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;

            if (login != "")
            {
                labelCoucou.Content = "Bienvenue, " + login + " !";
                buttonConnexion.Content = "Se déconnecter";
                newMessageBox.Visibility = Visibility.Visible;
                newMessageLabel.Visibility = Visibility.Visible;
                newMessageButton.Visibility = Visibility.Visible;
                newCanalButton.Visibility = Visibility.Visible;
                newCanalIsPublic.Visibility = Visibility.Visible;
                newcanalLabel.Visibility = Visibility.Visible;
                newCanalTag.Visibility = Visibility.Visible;
                DataGridMessage.Margin = new Thickness(10,350,10,10);
            }
            foreach (var c in canaux)
            {
                if (c.Value.isPublic || login != "")
                    ComboBoxCanaux.Items.Add(c.Key);
            }

        }

        private void refreshMessages(object sender, RoutedEventArgs e)
        {
            DataGridMessage.ItemsSource = canaux[ComboBoxCanaux.SelectedItem.ToString()].messages.AsParallel();
        }

        private void buttonConnexion_Click(object sender, RoutedEventArgs e)
        {
            if (login == "")
            {
                LoginWindow loginWindow = new LoginWindow();
                this.Close();
                loginWindow.Show();
            }
            else
            {
                MainWindow mainWindow = new MainWindow();
                this.Close();
                mainWindow.Show();
            }
        }
        private void buttonCreerCanal_Click(object sender, RoutedEventArgs e)
        {
            string newTag = newCanalTag.Text; 
            if (!canaux.ContainsKey(newTag))
            {
                bool newIsPublic = newCanalIsPublic.IsChecked.Value && newCanalIsPublic.IsChecked.HasValue;
                Canal tmp = new Canal(newTag, newIsPublic);
                canaux.Add(tmp.tag,tmp);
                if (newIsPublic || login!="") ComboBoxCanaux.Items.Add(newTag);
                ResultCreationCanal.Content = "Canal créé avec succès !";
            }
            else
            {
                ResultCreationCanal.Content = "Ce tag est déjà utilisé !";
            }
        }
        private void buttonNewMessage_Click(object sender, RoutedEventArgs e)
        {
            if (ComboBoxCanaux.SelectedValue != null)
            {
                Message m = new Message(login, DateTime.Now, newMessageBox.Text);
                canaux[ComboBoxCanaux.SelectedItem.ToString()].addMessage(m);
                refreshMessages(sender, e);
            }
        }
    }
}
