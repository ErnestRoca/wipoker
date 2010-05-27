/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;
import controladora.jabber.GestioUsuaris;
import domini.Ronda;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.ParticipantStatusListener;
import org.jivesoftware.smackx.muc.UserStatusListener;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber implements PacketListener, ParticipantStatusListener {

    public XMPPConnection connexio;
    public GestioUsuaris gu;
    public Ronda ronda = new Ronda();
    MultiUserChat sala;

    public ControladoraJabber(String servidor) {
        connexio = Connexio.crearConnexio(servidor);
        gu = new GestioUsuaris();

    }
    //metodo de packetListener

    public void processPacket(Packet packet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void crearSala() throws XMPPException {
        if (connexio.isConnected()) {
            sala = new MultiUserChat(connexio, "wipoker");
        }
        sala.join("peracho87");
    }

    //metodo de participantStatusListener
    public void joined(String string) {
        System.out.println("jugador afegit");
    }

    public void left(String string) {
        System.out.println("un usuario es va");
    }

    public void kicked(String string, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void voiceGranted(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void voiceRevoked(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void banned(String string, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void membershipGranted(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void membershipRevoked(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moderatorGranted(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moderatorRevoked(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ownershipGranted(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ownershipRevoked(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void adminGranted(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void adminRevoked(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nicknameChanged(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        ControladoraJabber cj = new ControladoraJabber("jabberes.org");

    }
}
