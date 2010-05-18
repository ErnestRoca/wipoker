/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

/**
 *
 * @author wida45787385
 */
public class ChatJoc {

    private XMPPConnection connexio;

    public ChatJoc() {

    }

    public void crear(String servidor) {
        Connexio con = new Connexio(servidor);
        connexio = con.crearConnexio(servidor);
        ChatManager ch = connexio.getChatManager();
    }

    public void processarMissatge(ChatManager cm, String destinatari, String missatge) {
        cm.createChat(destinatari, new MessageListener() {

            public void processMessage(Chat arg0, Message arg1) {
                //gui.getLABELCHAT.SetText arg1\n;
            }
        });
    }
}
