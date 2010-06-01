/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;

import controladora.jabber.JID;

import domini.Ronda;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber {

    private XMPPConnection connexio;
    private Ronda ronda = new Ronda();
    private MultiUserChat sala;
    private PacketFilter pf;
    private JID jid;

    public ControladoraJabber() {             
        
    }

    public XMPPConnection getConnexio() {
        return connexio;
    }

    public void setConnexio(XMPPConnection connexio) {
        this.connexio = connexio;
    }

    public MultiUserChat getSala() {
        return sala;
    }
    
    public void prepararSala(String nomSala, String direccioSala, String alias) {
        //cadena completa per servidor jabberes.org sala@conf.jabberes.org/andres
        String cad = nomSala + "@" + direccioSala + "/" + alias;
        sala = Connexio.crearSalaChat(connexio, cad);
        jid = new JID(cad);
    }
}
