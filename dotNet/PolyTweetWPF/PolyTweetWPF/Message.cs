using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PolyTweetWPF
{
    public class Message
    {
        public string auteur { get; set; }
        public string texte { get; set; }
        public DateTime date { get; set; }

        public Message(string auteur, DateTime date, string texte)
        {
            this.auteur = auteur;
            this.texte = texte;
            this.date = date;
        }
    }
}
