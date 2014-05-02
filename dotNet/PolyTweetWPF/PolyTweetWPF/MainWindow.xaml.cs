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
    using Message = Dictionary<string, object>;
    /// <summary>
    /// Logique d'interaction pour mainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public readonly static string AuteurKey = "auteur";
        public readonly static string DateKey = "date";
        public readonly static string TexteKey = "texte";
        public readonly static string PublicKey = "public";

        public Dictionary<string, List<Message>> CanauxPublic { get; set; }
        public Dictionary<string, List<Message>> CanauxPrive { get; set; }
        public Message m1;
        public Message m2;
        public Message m3;
        public Message m4;

        private Message creerMessage(string auteur, DateTime date, string texte)
        {
            Message m = new Message();
            m.Add(AuteurKey, auteur);
            m.Add(DateKey, date);
            m.Add(TexteKey, texte);
            return m;
        }

        private void InitData()
        {
            CanauxPublic = new Dictionary<string, List<Message>>();
            CanauxPrive = new Dictionary<string, List<Message>>();
            m1 = creerMessage("admin", DateTime.Now, "message 1");
            m2 = creerMessage("moi", DateTime.Now.AddDays(-1), "message 2");
            m3 = creerMessage("toi", DateTime.Now.AddMonths(-1), "message 3");
            m4 = creerMessage("admin", DateTime.Now.AddDays(-10), "message 4");

            CanauxPublic.Add("canal 1", new List<Message>() { m1, m2 });
            CanauxPublic.Add("canal 2", new List<Message>() { m2, m3 });
            CanauxPrive.Add("canal 3", new List<Message>() { m1, m4 });
        }

        public MainWindow()
        {
            InitializeComponent();
            InitData();
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            ComboBoxCanaux.ItemsSource = CanauxPublic.Keys;
            foreach (string s in new Message().Keys)
                this.DataGridMessage.Columns.Add(new DataGridTextColumn { Header = s });

            this.DataGridMessage.ItemsSource = m1.Values;
        }
        private void buttonConnexion_Click(object sender, RoutedEventArgs e)
        {
            labelCoucou.Content = "test";
            LoginWindow loginWindow = new LoginWindow();
            this.Close();
            loginWindow.Show();
        }
    }
}
