/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;

import controladora.jabber.GestioUsuaris;
import controladora.jabber.JID;

import domini.Ronda;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.UserStatusListener;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber implements ConnectionListener, UserStatusListener {

    public XMPPConnection connexio;
    public GestioUsuaris gu;
    public Ronda ronda = new Ronda();
    private MultiUserChat sala;
    private PacketFilter pf;
    private boolean logat = false;
    private JID jid;

    public ControladoraJabber(String servidor) throws XMPPException {
        connexio = Connexio.crearConnexio(servidor);
        gu = new GestioUsuaris();
        gu.conectar(connexio);
        gu.ferLogin(connexio, "perachoandres", "apa45787385c");
        logat = true;
    }
    //metodo de packetListener

    public void prepararSala() {
        sala = Connexio.crearSalaChat(connexio, "sala@conf.jabberes.org/andres");        
    }

    public static void crearJoc() {
        Connexio.crearSalaJoc("sala@conf.jabberes.org/andres");
    }
    
    public static void main(String[] args) {
        try {
            ControladoraJabber cj = new ControladoraJabber("jabberes.org");
            cj.gu.desconnectar(cj.connexio);
            cj.gu.conectar(cj.connexio);            
            System.out.println(cj.connexio.getHost() + cj.connexio.getPort());
            System.out.println(cj.connexio.getServiceName());
            System.out.println(cj.connexio.getAccountManager().supportsAccountCreation());
            //cj.connexio.getAccountManager().createAccount("perachoandres", "apa45787385c");
            cj.connexio.login("perachoandres", "apa45787385c");
            cj.prepararSala();
            cj.sala.create("sala@conf.jabberes.org/andres");
            MultiUserChat.isServiceEnabled(cj.connexio, "andres");
            cj.sala.join("perachoandres", "apa45787385c");
            System.out.println(cj.sala.getMembers().isEmpty());
            
        } catch (XMPPException ex) {
            Logger.getLogger(ControladoraJabber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connectionClosed() {
        System.out.println("s'ha tancat la connexio");
    }

    public void connectionClosedOnError(Exception excptn) {
        System.out.println("error en la connexio");
    }

    public void reconnectingIn(int i) {
        System.out.println("reconnectant");
    }

    public void reconnectionSuccessful() {
        System.out.println("reconnectat");
    }

    public void reconnectionFailed(Exception excptn) {
        System.out.println("error en reconnexio");
    }

    public void kicked(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void voiceGranted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void voiceRevoked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void banned(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void membershipGranted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void membershipRevoked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moderatorGranted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moderatorRevoked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ownershipGranted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ownershipRevoked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void adminGranted() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void adminRevoked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
