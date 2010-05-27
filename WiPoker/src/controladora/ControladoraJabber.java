/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;
import controladora.jabber.GestioUsuaris;
import domini.Ronda;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
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
    }
    //metodo de packetListener

    public void crearSala() throws XMPPException {
        if (connexio.isConnected()) {
            sala = new MultiUserChat(connexio, "wipoker");
        }
    }
}
