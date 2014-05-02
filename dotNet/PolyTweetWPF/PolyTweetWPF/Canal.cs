using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PolyTweetWPF
{
    public class Canal
    {
        public string tag { get; set; }
        public bool isPublic { get; set; }
        public List<Message> messages { get; set; }

        public Canal(string tag, bool isPublic)
        {
            this.tag = tag;
            this.isPublic = isPublic;
            this.messages = new List<Message>();
        }
        public void addMessage(Message m)
        {
            messages.Add(m);
        }
        public void removeMessage(Message m)
        {
            messages.Remove(m);
        }
    }
}
