/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;

import controladora.jabber.GestioUsuaris;

import domini.Aposta;
import domini.Ronda;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber {

    public XMPPConnection connexio;
    public GestioUsuaris gu;
    public Ronda ronda = new Ronda();
    MultiUserChat sala;

    public ControladoraJabber(String servidor) throws XMPPException {
        connexio = Connexio.crearConnexio(servidor);
        gu = new GestioUsuaris();
        gu.conectar(connexio);
        gu.ferLogin(connexio, "perachoandres", "apa45787385c");
    }
    //metodo de packetListener

    public void crearSala() throws XMPPException {
        if (connexio.isConnected()) {
            sala = new MultiUserChat(connexio, "wipoker");
            Message m = sala.createMessage();
            

            //sala.join("peracho87", "apa45787385c");
        }
    }

    public static void main(String[] args) {
        try {
            ControladoraJabber cj = new ControladoraJabber("jabberes.org");
            cj.connexio.connect();
            System.out.println(cj.connexio.getHost() + cj.connexio.getPort());
            System.out.println(cj.connexio.getServiceName());
            System.out.println(cj.connexio.getAccountManager().supportsAccountCreation());
            //cj.connexio.getAccountManager().createAccount("perachoandres", "apa45787385c");
            cj.connexio.login("perachoandres", "apa45787385c");
            cj.crearSala();
            
        } catch (XMPPException ex) {
            Logger.getLogger(ControladoraJabber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
