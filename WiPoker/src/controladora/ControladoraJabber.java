/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

import controladora.jabber.Connexio;
import domini.Ronda;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber implements PacketListener {

    public XMPPConnection connexio;
    public Ronda ronda = new Ronda();
    MultiUserChat sala;
    
    public ControladoraJabber(String servidor) {
        connexio = Connexio.crearConnexio(servidor);
        sala = new MultiUserChat(connexio, "sala");
    }

    public void iniciar() {



    }

    public void processPacket(Packet packet) {
        if (packet.getProperty("tipus").equals("carta")) {
            

        }
    }



}
