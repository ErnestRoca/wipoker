/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;

import controladora.jabber.GestioUsuaris;
import controladora.jabber.JID;

import domini.Ronda;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber {

    public XMPPConnection connexio;
    public Ronda ronda = new Ronda();
    public MultiUserChat sala;
    public PacketFilter pf;
    public boolean logat = false;
    public JID jid;

    public ControladoraJabber(String servidor) throws XMPPException {
        connexio = Connexio.crearConnexio(servidor);         
        
    }

    public void prepararSala(String nomSala, String direccioSala, String alias) {
        //cadena completa per servidor jabberes.org sala@conf.jabberes.org/andres
        String cad = nomSala + "@" + direccioSala + "/" + alias;
        sala = Connexio.crearSalaChat(connexio, cad);
        jid = new JID(cad);
    }
}
